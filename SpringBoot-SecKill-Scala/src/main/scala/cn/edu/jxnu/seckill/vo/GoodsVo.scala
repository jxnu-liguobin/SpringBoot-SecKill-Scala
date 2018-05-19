package cn.edu.jxnu.seckill.vo
import scala.language.implicitConversions
import scala.beans.BeanProperty
import cn.edu.jxnu.seckill.domain.Goods
import java.util.Date

/**
 * 商品视图对象
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
class GoodsVo extends Goods {

    //秒杀价格
    @BeanProperty
    var seckillPrice: Double = _;

    //库存
    @BeanProperty
    var stockCount: Integer = _;

    //开始日期
    @BeanProperty
    var startDate: Date = _;

    //结束日期
    @BeanProperty
    var endDate: Date = _;

}