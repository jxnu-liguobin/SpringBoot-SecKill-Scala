package cn.edu.jxnu.seckill.exception

import cn.edu.jxnu.seckill.result.CodeMsg
import scala.language.implicitConversions

/**
 * 全局定义
 *
 * @author 梦境迷离.
 * @time 2018年5月20日
 * @version v1.0
 */
class GlobalException extends RuntimeException {

    private final val serialVersionUID: Long = 1L

    private var cm: CodeMsg = _

    def this(cm: CodeMsg) {
        this()
        this.cm = cm
    }

    def getCm() = cm

    /** 这里直接打印出传入的异常信息.*/
    override def toString() = cm.toString()

} 