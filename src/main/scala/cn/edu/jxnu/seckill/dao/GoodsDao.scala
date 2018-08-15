package cn.edu.jxnu.seckill.dao

import java.util.{List => JavaList}

import cn.edu.jxnu.seckill.domain.SeckillGoods
import cn.edu.jxnu.seckill.vo.GoodsVo
import org.apache.ibatis.annotations.{Param, Select, Update}

import scala.collection.immutable.{List => _}
import scala.language.implicitConversions

/**
 * 商品dao
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
trait GoodsDao {

    /**
     * 查询出所有商品视图对象
     */
    @Select(Array("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.seckill_price from seckill_goods mg left join goods g on mg.goods_id = g.id"))
    def listGoodsVo(): JavaList[GoodsVo]

    /**
     * 根据商品id=秒杀的商品id,查询出商品视图对象
     */
    @Select(Array("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.seckill_price from seckill_goods mg left join goods g on mg.goods_id = g.id where g.id = #{goodsId}"))
    def getGoodsVoByGoodsId(@Param("goodsId") goodsId: Long): GoodsVo

    /**
     * 库存减1
     */
    @Update(Array("update seckill_goods set stock_count = stock_count - 1 where goods_id = #{goodsId} and stock_count > 0"))
    def reduceStock(g: SeckillGoods): Integer

    /**
     * 库存修改
     */
    @Update(Array("update seckill_goods set stock_count = #{stockCount} where goods_id = #{goodsId}"))
    def resetStock(g: SeckillGoods): Integer

}