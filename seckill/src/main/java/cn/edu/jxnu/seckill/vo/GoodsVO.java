package cn.edu.jxnu.seckill.vo;

import java.util.Date;

import cn.edu.jxnu.seckill.domain.Goods;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsVO extends Goods {

	private Double seckillPrice;
	private Integer stockCount;
	private Date startTime;
	private Date endTime;

}
