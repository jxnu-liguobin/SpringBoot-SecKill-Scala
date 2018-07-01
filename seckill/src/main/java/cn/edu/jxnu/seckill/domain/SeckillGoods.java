package cn.edu.jxnu.seckill.domain;

import java.util.Date;

import lombok.Data;

@Data
public class SeckillGoods {

	private Long id;
	private Long goodsId;
	private Double seckillPrice;
	private Integer stockCount;
	private Date startTime;
	private Date endTime;

	@Override
	public String toString() {
		return "SeckillGoods{" + "id=" + id + ", goodsid=" + goodsId + ", stockCount=" + stockCount + ", startTime="
				+ startTime + ", endTime=" + endTime + '}';
	}
}
