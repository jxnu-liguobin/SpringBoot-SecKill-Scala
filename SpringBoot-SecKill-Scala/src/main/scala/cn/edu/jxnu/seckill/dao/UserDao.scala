package cn.edu.jxnu.seckill.dao

import scala.language.implicitConversions
import cn.edu.jxnu.seckill.domain.User
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Insert

/**
 * 用户dao
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
trait UserDao {

    /**
     * 根据用户id，查询用户
     */
    @Select(Array("select * from user where id = #{id}"))
    def getById(@Param("id") id: Integer): User

    /**
     * 新增用户
     */
    @Insert(Array("insert into user(id,name) values(#{id},#{name})"))
    def insert(@Param("id") id: Integer, @Param("name") name: String): Integer
}