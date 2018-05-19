package cn.edu.jxnu.seckill.domain
import scala.language.implicitConversions
import scala.beans.BeanProperty

/**
 * 商品
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
class Goods {

    /** BeanProperty 生成与Java兼容的set/get方法，但属性不能为private，所以我设置为包级 ，implicitConversions 支持Scala-Java隐式类型转换*/
    //商品id
    @BeanProperty
    private[domain] var id: Long = _

    //商品名称
    @BeanProperty
    private[domain] var goodsName: String = _

    //商品标题
    @BeanProperty
    private[domain] var goodsTitle: String = _

    //商品图片
    @BeanProperty
    private[domain] var goodsImg: String = _

    //商品描述
    @BeanProperty
    private[domain] var goodsDetail: String = _

    //商品价格
    @BeanProperty
    private[domain] var goodsPrice: String = _

    //商品库存
    @BeanProperty
    private[domain] var goodsStock: String = _
}