package cn.edu.jxnu.seckill.domain;

import lombok.Data;

@Data
public class Goods {

	private Long id;
	private String goodsName;
	private String goodsTitle;
	private String goodsImg;
	private String goodsDetail;
	private Double goodsPrice;
	private Integer goodsStock;

	@Override
	public String toString() {
		return "Goods{" + "id=" + id + ", goodsName='" + goodsName + '\'' + ", goodsTitle='" + goodsTitle + '\''
				+ ", goodsImg='" + goodsImg + '\'' + ", goodsDetail='" + goodsDetail + '\'' + ", goodsPrice="
				+ goodsPrice + ", goodsStock=" + goodsStock + '}';
	}
}
