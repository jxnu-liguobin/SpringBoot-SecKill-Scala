package cn.edu.jxnu.seckill.access

import cn.edu.jxnu.seckill.domain.SeckillUser

/**
 * 用户上下文
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
package object UserContext {

    private var userHolder: ThreadLocal[SeckillUser] = new ThreadLocal[SeckillUser]()

    def setUser(user: SeckillUser) {
        userHolder.set(user)
    }

    def getUser(): SeckillUser = userHolder.get()

}