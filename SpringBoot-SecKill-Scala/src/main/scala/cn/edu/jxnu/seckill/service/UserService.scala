package cn.edu.jxnu.seckill.service

import scala.language.implicitConversions
import org.springframework.stereotype.Service
import cn.edu.jxnu.seckill.dao.UserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import cn.edu.jxnu.seckill.domain.User

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