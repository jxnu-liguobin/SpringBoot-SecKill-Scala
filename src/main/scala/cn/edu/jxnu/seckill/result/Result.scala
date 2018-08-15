package cn.edu.jxnu.seckill.result

import scala.beans.BeanProperty
import scala.language.implicitConversions

/**
 * 返回结果
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
class Result[T]() {

    @BeanProperty
    var code: Integer = _

    @BeanProperty
    var msg: String = _

    @BeanProperty
    var data: T = _

    /**
     * 均是不能被外部使用的
     */
    private def this(code: Integer, msg: String, data: T) {
        this()
        this.code = code
        this.msg = msg
        this.data = data
    }

    //带数据的构造
    private def this(data: T) {
        this()
        this.code = 0
        this.msg = "success"
        this.data = data
    }

    //带状态码消息的构造
    private def this(msg: CodeMsg) {
        this()
        if (msg == null) {
            //TODO Scala这里不能写return
        }
        this.code = msg.getCode()
        this.msg = msg.getMsg()
    }

}
//伴生对象，实现Java的静态方法
object Result {

    def success[T](data: T): Result[T] = {
        return new Result[T](data)

    }

    def error[T](cm: CodeMsg): Result[T] = {
        return new Result[T](cm)
    }
    //根据需要在这实现自己的重载方法
}