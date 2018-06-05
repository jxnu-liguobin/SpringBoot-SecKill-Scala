package cn.edu.jxnu.seckill.service

import scala.language.implicitConversions
import scala.collection.JavaConversions._
import scala.collection.immutable.{ List => _, _ }
import java.util.{ List => JavaList }
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import cn.edu.jxnu.seckill.dao.GoodsDao
import cn.edu.jxnu.seckill.vo.GoodsVo
import cn.edu.jxnu.seckill.domain.SeckillGoods

/**
 * 商品服务层
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
@Service
class GoodsService @Autowired() (goodsDao: GoodsDao) {

    /**
     * 查询全部
     */
    def listGoodsVo(): JavaList[GoodsVo] = goodsDao.listGoodsVo()

    /**
     * 根据商品id查询商品视图对象
     */
    def getGoodsVoByGoodsId(id: Long) = goodsDao.getGoodsVoByGoodsId(id)

    /**
     * 修改商品库存
     *
     * 默认每次减1
     */
    def reduceStock(goods: GoodsVo): Boolean = {
        var g = new SeckillGoods()
        g.setGoodsId(goods.getId())
        goodsDao.reduceStock(g) > 0
    }

    /**
     * 恢复库存
     */
    def resetStock(goodsList: JavaList[GoodsVo]) {
        for (goods <- goodsList) {
            val g = new SeckillGoods()
            g.setGoodsId(goods.getId())
            g.setStockCount(goods.getStockCount())
            goodsDao.resetStock(g)
        }
    }

}