package cn.edu.jxnu.seckill.dao

import scala.language.implicitConversions
import scala.collection.JavaConversions._
import scala.collection.immutable.{ List => _, _ }
import java.util.{ List => JavaList }
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Update
import cn.edu.jxnu.seckill.domain.SeckillGoods
import cn.edu.jxnu.seckill.vo.GoodsVo

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