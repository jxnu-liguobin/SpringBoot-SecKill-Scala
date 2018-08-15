package cn.edu.jxnu.seckill.domain

import scala.beans.BeanProperty
import scala.language.implicitConversions

/**
 * 商品
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
class Goods {

    /**
     * BeanProperty 生成与Java兼容的set/get方法，但属性不能为private，设置为包级，则其他包无法访问
     * implicitConversions 支持Scala-Java隐式类型转换
     */
    //如果不使用BeanProperty注解，则可以加private
    //a.name -- 调用 a.name方法
    //a.name = "foo" 调用a.name_ (x:String) = xxx = x
    //商品id
    @BeanProperty
    private[seckill] var id: Long = _

    //商品名称
    @BeanProperty
    private[seckill] var goodsName: String = _

    //商品标题
    @BeanProperty
    private[seckill] var goodsTitle: String = _

    //商品图片
    @BeanProperty
    private[seckill] var goodsImg: String = _

    //商品详情描述
    @BeanProperty
    private[seckill] var goodsDetail: String = _

    //商品单价
    @BeanProperty
    private[seckill] var goodsPrice: String = _

    //商品库存
    @BeanProperty
    private[seckill] var goodsStock: String = _

    /**
     * 重写父类非抽象方法，必须加override
     *
     * 字符拼接的时候 + 不能在最前面
     */
    override def toString(): String = {
        "Goods [id=" + id + ", goodsName=" + goodsName + ", goodsTitle=" + goodsTitle +
            ", goodsImg=" + goodsImg + ", goodsDetail=" + goodsDetail + ", goodsPrice=" + goodsPrice +
            ", goodsStock=" + goodsStock + "]"
    }
}