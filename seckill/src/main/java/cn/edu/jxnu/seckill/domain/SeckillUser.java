package cn.edu.jxnu.seckill.domain;

import java.util.Date;

import lombok.Data;

@Data
public class SeckillUser {

	private Long id;
	private String nickname;
	private String password;
	private String salt;
	private String head;
	private Date registerDate;
	private Date lastLoginDate;
	private Integer loginCount;

	@Override
	public String toString() {
		return "SeckillUser{" + "id=" + id + ", nickname='" + nickname + '\'' + ", password='" + password + '\''
				+ ", salt='" + salt + '\'' + ", head='" + head + '\'' + ", tegisterDate=" + registerDate
				+ ", lastLoginDate=" + lastLoginDate + ", loginCount=" + loginCount + '}';
	}
}
