package cn.edu.jxnu.seckill.vo;


import cn.edu.jxnu.seckill.domain.OrderInfo;
import lombok.Data;

@Data
public class OrderDetailVO {
    private GoodsVO goods;
    private OrderInfo order;
}
