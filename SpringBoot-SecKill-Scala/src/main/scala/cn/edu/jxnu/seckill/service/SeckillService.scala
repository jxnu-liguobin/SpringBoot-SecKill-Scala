package cn.edu.jxnu.seckill.service

import scala.language.implicitConversions
import scala.collection.JavaConversions._
import scala.collection.immutable.{ List => _, _ }
import java.util.{ List => JavaList }
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import cn.edu.jxnu.seckill.redis.RedisService
import org.springframework.transaction.annotation.Transactional
import cn.edu.jxnu.seckill.domain.SeckillUser
import cn.edu.jxnu.seckill.vo.GoodsVo
import cn.edu.jxnu.seckill.domain.OrderInfo
import cn.edu.jxnu.seckill.redis.key.SeckillKey
import cn.edu.jxnu.seckill.util.MD5Util
import cn.edu.jxnu.seckill.util.UUIDUtil
import scala.util.Random
import java.awt.image.BufferedImage
import java.awt.Color
import java.awt.Font
import javax.script.ScriptEngineManager
import org.slf4j.LoggerFactory

/**
 * 秒杀服务层
 *
 * 包含验证码
 *
 * @author 梦境迷离.
 * @time 2018年5月20日
 * @version v1.0
 */
@Service
class SeckillService @Autowired() (goodsService: GoodsService,
    orderService: OrderService, redisService: RedisService) {

    @Transactional
    def seckill(user: SeckillUser, goods: GoodsVo): OrderInfo = {
        // 减库存 下订单 写入秒杀订单
        val success = goodsService.reduceStock(goods)
        if (success) {
            // order_info seckill_order
            orderService.createOrder(user, goods)
        } else {
            //卖完了，记录下
            setGoodsOver(goods.getId())
            return null
        }
    }

    /**
     * 还原环境【测试用】
     */
    def reset(goodsList: JavaList[GoodsVo]) {
        goodsService.resetStock(goodsList)
        orderService.deleteOrders()
    }

    def getSeckillResult(userId: Long, goodsId: Long): Long = {
        val order = orderService.getSeckillOrderByUserIdGoodsId(userId, goodsId)
        if (order != null) { // 秒杀成功
            //返回秒杀商品给前台用来查看订单
            return order.getOrderId()
        } else {
            val isOver = getGoodsOver(goodsId)
            if (isOver) { // 因为卖完了
                return -1
            } else {
                return 0
            }
        }
    }

    def setGoodsOver(goodsId: Long) {
        redisService.set(SeckillKey.isGoodsOver, "" + goodsId, true)
    }

    def getGoodsOver(goodsId: Long): Boolean = {
        redisService.exists(SeckillKey.isGoodsOver, "" + goodsId)
    }

    /**
     * 生成随机uuid，作为获取地址携带的参数
     */
    def createSeckillPath(user: SeckillUser, goodsId: Long): String = {
        if (user == null || goodsId <= 0) {
            return null
        }
        val str = MD5Util.md5(UUIDUtil.uuid() + "123456")
        redisService.set(SeckillKey.getSeckillPath, "" + user.getId() + "_" + goodsId, str)
        str
    }

    /**
     * 判断前端传来的uuid是否与redis中的相同
     */
    def checkPath(user: SeckillUser, goodsId: Long, path: String): Boolean = {
        if (user == null || path == null) {
            return false
        }
        val pathOld = redisService.get(SeckillKey.getSeckillPath, "" + user.getId() + "_" + goodsId, classOf[String])
        path.equals(pathOld)
    }

    def createVerifyCode(user: SeckillUser, goodsId: Long): BufferedImage = {
        if (user == null || goodsId <= 0) {
            return null
        }
        val width = 80
        val height = 32
        // create the image
        val image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val g = image.getGraphics()
        // set the background color
        g.setColor(new Color(0xDCDCDC))
        g.fillRect(0, 0, width, height)
        // draw the border
        g.setColor(Color.black)
        g.drawRect(0, 0, width - 1, height - 1)
        // create a random instance to generate the codes
        val rdm = new Random()
        // make some confusion
        for (i <- 0 until 50) {
            var x = rdm.nextInt(width)
            var y = rdm.nextInt(height)
            g.drawOval(x, y, 0, 0)
        }
        // generate a random code
        val verifyCode = generateVerifyCode(rdm)
        g.setColor(new Color(0, 100, 0))
        g.setFont(new Font("Candara", Font.BOLD, 24))
        g.drawString(verifyCode, 8, 24)
        g.dispose()
        // 把验证码存到redis中
        val rnd = calc(verifyCode)
        redisService.set(SeckillKey.getSeckillVerifyCode, user.getId() + "," + goodsId, rnd)
        // 输出图片
        image
    }

    private def calc(exp: String): Int = {
        try {
            val manager = new ScriptEngineManager()
            val engine = manager.getEngineByName("JavaScript")
            engine.eval(exp).asInstanceOf[Integer]
        } catch {
            case e: Exception =>
                e.printStackTrace()
                0
        }
    }

    private val ops = Array('+', '-', '*')

    private def generateVerifyCode(rdm: Random): String = {
        val num1 = rdm.nextInt(10)
        val num2 = rdm.nextInt(10)
        val num3 = rdm.nextInt(10)
        val op1 = ops(rdm.nextInt(3))
        val op2 = ops(rdm.nextInt(3))
        val exp = "" + num1 + op1 + num2 + op2 + num3
        exp
    }

    def checkVerifyCode(user: SeckillUser, goodsId: Long, verifyCode: Int): Boolean = {
        if (user == null || goodsId <= 0) {
            return false
        }
        val codeOld = redisService.get(SeckillKey.getSeckillVerifyCode, user.getId() + "," + goodsId,
                                       classOf[Integer])
        if (codeOld == null || codeOld - verifyCode != 0) {
            return false
        }
        redisService.delete(SeckillKey.getSeckillVerifyCode, user.getId() + "," + goodsId)
        true
    }

}