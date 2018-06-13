package cn.edu.jxnu.seckill.service

import org.springframework.stereotype.Service
import cn.edu.jxnu.seckill.dao.OrderDao
import cn.edu.jxnu.seckill.redis.RedisService
import org.springframework.beans.factory.annotation.Autowired
import cn.edu.jxnu.seckill.domain.SeckillOrder
import cn.edu.jxnu.seckill.redis.key.OrderKey
import org.springframework.transaction.annotation.Transactional
import cn.edu.jxnu.seckill.domain.SeckillUser
import cn.edu.jxnu.seckill.vo.GoodsVo
import cn.edu.jxnu.seckill.domain.OrderInfo
import java.util.Date
import scala.language.implicitConversions

/**
 * 订单服务层
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
@Service
class OrderService @Autowired() (orderDao: OrderDao,
    redisService: RedisService) {

    //是否已经秒杀过【(user.id，goods.id)是数据库的唯一索引，且秒杀时，这个将存进redis】
    val getSeckillOrderByUserIdGoodsId = (userId: Long, goodsId: Long) => {
        redisService.get(OrderKey.getSeckillOrderByUidGid, "" + userId + "_" + goodsId, classOf[SeckillOrder])
    }

    /**
     * 订单创建
     */
    @Transactional
    val createOrder = (user: SeckillUser, goods: GoodsVo) => {
        val orderInfo = new OrderInfo()
        orderInfo.setCreateDate(new Date())
        orderInfo.setDeliveryAddrId(0L)
        orderInfo.setGoodsCount(1)
        orderInfo.setGoodsId(goods.getId())
        orderInfo.setGoodsName(goods.getGoodsName())
        orderInfo.setGoodsPrice(goods.getSeckillPrice())
        orderInfo.setOrderChannel(1)
        orderInfo.setStatus(0)
        orderInfo.setUserId(user.getId())
        orderDao.insert(orderInfo)
        val seckillOrder = new SeckillOrder()
        seckillOrder.setGoodsId(goods.getId())
        seckillOrder.setOrderId(orderInfo.getId())
        seckillOrder.setUserId(user.getId())
        orderDao.insertSeckillOrder(seckillOrder)
        //生成订单的时候写完mysql,也要写进redis中,下次点击将直接去缓存，响应快
        redisService.set(OrderKey.getSeckillOrderByUidGid, "" + user.getId() + "_" + goods.getId(), seckillOrder)
        orderInfo
    }

    /**
     * 根据订单id查询订单信息
     */
    val getOrderById = (orderId: Long) => {
        orderDao.getOrderById(orderId)
    }

    /**
     * 删除订单
     *
     * 先删除订单再删除秒杀订单
     */
    val deleteOrders = () => {
        orderDao.deleteOrders()
        orderDao.deleteSeckillaOrders()
    }

}