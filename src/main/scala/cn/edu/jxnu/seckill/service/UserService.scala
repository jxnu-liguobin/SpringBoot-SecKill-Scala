package cn.edu.jxnu.seckill.service

import cn.edu.jxnu.seckill.dao.UserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import scala.language.implicitConversions

/**
 * 用户服务层
 *
 * @author 梦境迷离.
 * @time 2018年5月20日
 * @version v1.0
 */
@Service
class UserService @Autowired() (userDao: UserDao) {

    /**
     * 查询用户
     */
    def getById(id: Integer) = userDao.getById(id)

    /**
     * 新增
     */
    @Transactional
    def tx() {
        userDao.insert(3, "张三")
        userDao.insert(1, "李四")
    }

}