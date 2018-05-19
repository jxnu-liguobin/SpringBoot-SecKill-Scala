package cn.edu.jxnu.seckill.dao

import scala.language.implicitConversions
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Param
import cn.edu.jxnu.seckill.domain.SeckillOrder
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.SelectKey
import cn.edu.jxnu.seckill.domain.OrderInfo
import org.apache.ibatis.annotations.Delete

/**
 * 订单dao
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
trait OrderDao {

    @Select(Array("select * from seckill_order where user_id=#{userId} and goods_id=#{goodsId}"))
    def getSeckillOrderByUserIdGoodsId(@Param("userId") userId: Long, @Param("goodsId") goodsId: Long): SeckillOrder;

    @Insert(Array("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date) "
        + "values(#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )"))
    @SelectKey(keyColumn   = "id", keyProperty = "id", resultType = classOf[Long], before = false, statement = Array("SELECT LAST_INSERT_ID()"))
    def insert(orderInfo: OrderInfo): Long;

    @Insert(Array("insert into seckill_order(user_id, goods_id, order_id) values(#{userId}, #{goodsId}, #{orderId})"))
    def insertSeckillOrder(seckillOrder: SeckillOrder): Integer;

    @Select(Array("select * from order_info where id = #{orderId}"))
    def getOrderById(@Param("orderId") orderId: Long): OrderInfo;

    @Delete(Array("delete from order_info"))
    def deleteOrders();

    @Delete(Array("delete from seckill_order"))
    def deleteSeckillaOrders();

}