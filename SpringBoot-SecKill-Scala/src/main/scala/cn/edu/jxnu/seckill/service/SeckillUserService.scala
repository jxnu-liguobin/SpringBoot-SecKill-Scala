package cn.edu.jxnu.seckill.service

import org.springframework.stereotype.Service
import cn.edu.jxnu.seckill.dao.SeckillUserDao
import cn.edu.jxnu.seckill.redis.RedisService
import org.springframework.beans.factory.annotation.Autowired
import javax.servlet.http.HttpServletResponse
import cn.edu.jxnu.seckill.vo.LoginVo
import cn.edu.jxnu.seckill.exception.GlobalException
import cn.edu.jxnu.seckill.result.CodeMsg
import javax.validation.Valid
import cn.edu.jxnu.seckill.util.MD5Util
import cn.edu.jxnu.seckill.domain.SeckillUser
import cn.edu.jxnu.seckill.redis.key.SeckillUserKey
import org.apache.commons.lang3.StringUtils
import javax.servlet.http.Cookie
import cn.edu.jxnu.seckill.util.UUIDUtil
import org.slf4j.LoggerFactory

/**
 * 秒杀用户服务层
 *
 * @author 梦境迷离.
 * @time 2018年5月20日
 * @version v1.0
 */
@Service
class SeckillUserService @Autowired() (seckillUserDao: SeckillUserDao,
    redisService: RedisService) {

    /**
     * 登陆
     */
    def login(response: HttpServletResponse, @Valid loginVo: LoginVo): String = {

        if (loginVo == null)
            throw new GlobalException(CodeMsg.SERVER_ERROR)
        val mobile = loginVo.getMobile()
        val formPass = loginVo.getPassword()
        // 判断手机号是否存在
        val user = getById(mobile.toLong)
        if (user == null)
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST)
        // 验证密码
        val dbPass = user.getPassword()
        val saltDB = user.getSalt()
        val calcPass = MD5Util.formPassToDBPass(formPass, saltDB)
        if (!calcPass.equals(dbPass))
            throw new GlobalException(CodeMsg.PASSWORD_ERROR)
        // 生成cookie
        val token = UUIDUtil.uuid()
        addCookie(response, token, user)
        token
    }

    /**
     * 查询秒杀用户
     *
     * 存在，返回
     * 不在，去数据查询，再放假redis
     */
    def getById(id: Long): SeckillUser = {
        // 取缓存
        var user = redisService.get(SeckillUserKey.getById, "" + id, classOf[SeckillUser])
        if (user != null) {
            return user
        }
        user = seckillUserDao.getById(id)
        if (user != null) {
            //更新缓存
            redisService.set(SeckillUserKey.getById, "" + id, user)
        }
        user
    }

    /**
     * token是唯一的，需要传进来，不然无法登陆了
     */
    def updatePassword(token: String, id: Long, formPass: String): Boolean = {
        // 取user
        val user = getById(id)
        if (user == null)
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST)
        // 更新数据库，更新什么字段就传入什么字段，减少传输的数据
        val toBeUpdate = new SeckillUser()
        toBeUpdate.setId(id)
        toBeUpdate.setPassword(MD5Util.formPassToDBPass(formPass, user.getSalt()))
        seckillUserDao.update(toBeUpdate)
        // 清楚缓存再更新
        redisService.delete(SeckillUserKey.getById, "" + id)
        user.setPassword(toBeUpdate.getPassword())
        //更新，如果删除token[uuid唯一]就无法登陆了
        redisService.set(SeckillUserKey.token, token, user)
        true
    }

    /**
     * 设置cookie
     */
    private val addCookie = (response: HttpServletResponse, token: String, user: SeckillUser) => {

        //TODO 这里存在用户
        redisService.set(SeckillUserKey.token, token, user)
        val cookie = new Cookie(SeckillUserService.COOKI_NAME_TOKEN, token)
        //TODO 成功设置到浏览器
        cookie.setMaxAge(SeckillUserKey.token.expireSeconds())
        cookie.setPath("/")
        response.addCookie(cookie)
    }

    /**
     * 根据token获取用户信息
     */
    def getByToken(response: HttpServletResponse, token: String): SeckillUser = {

        if (StringUtils.isEmpty(token))
            return null
        val user = redisService.get(SeckillUserKey.token, token, classOf[SeckillUser])
        // 延长有效期
        if (user != null)
            addCookie(response, token, user)
        user
    }

}

object SeckillUserService {

    final val COOKI_NAME_TOKEN = "token"

}