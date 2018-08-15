package cn.edu.jxnu.seckill.dao

import cn.edu.jxnu.seckill.domain.SeckillUser
import org.apache.ibatis.annotations.{Param, Select, Update}

import scala.language.implicitConversions

/**
 * 秒杀用户dao
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
trait SeckillUserDao {

    /**
     * 根据id查询秒杀用户
     */
    @Select(Array("select * from seckill_user where id = #{id}"))
    def getById(@Param("id") id: Long): SeckillUser

    /**
     * 根据id和密码更新秒杀用户
     */
    @Update(Array("update seckill_user set password = #{password} where id = #{id}"))
    def update(toBeUpdate: SeckillUser)
}