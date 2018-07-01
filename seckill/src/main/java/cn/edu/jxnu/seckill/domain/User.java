package cn.edu.jxnu.seckill.domain;

import lombok.Data;

@Data
public class User {

	private int id;
	private String name;

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", name='" + name + '\'' + '}';
	}
}
