package cn.edu.jxnu.seckill.redis.key;

public class OrderKey extends BasePrefix {
	
    private OrderKey(String prefix) {
        super(prefix);
    }

    public static OrderKey getSeckillOrderByUidGid = new OrderKey("soug");

}
