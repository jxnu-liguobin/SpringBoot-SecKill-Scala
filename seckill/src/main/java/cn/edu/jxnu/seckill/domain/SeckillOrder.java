package cn.edu.jxnu.seckill.domain;

import lombok.Data;

@Data
public class SeckillOrder {

	private Long id;
	private Long userId;
	private Long orderId;
	private Long goodsId;

	@Override
	public String toString() {
		return "SeckillOrder{" + "id=" + id + ", userId=" + userId + ", orderId=" + orderId + ", goodsId=" + goodsId
				+ '}';
	}
}
