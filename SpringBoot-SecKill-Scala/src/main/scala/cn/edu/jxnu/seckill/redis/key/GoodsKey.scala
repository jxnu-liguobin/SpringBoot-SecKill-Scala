package cn.edu.jxnu.seckill.redis.key

import cn.edu.jxnu.seckill.redis.BasePrefix

/**
 * 定义Redis 商品键 前缀
 *
 * @author 梦境迷离.
 * @time 2018年5月20日
 * @version v1.0
 */
class GoodsKey private (expireSe: Integer, prefix: String) extends BasePrefix(expireSe, prefix) {

}

object GoodsKey {

    //获取秒杀商品列表
    final val getGoodsList: GoodsKey = new GoodsKey(60, "gl")

    //获取商品详情
    final val getGoodsDetail: GoodsKey = new GoodsKey(60, "gd")

    //获取秒杀商品库存
    final val getSeckillGoodsStock: GoodsKey = new GoodsKey(0, "gs")
}