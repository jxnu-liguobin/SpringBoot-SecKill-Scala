package cn.edu.jxnu.seckill.redis;

import cn.edu.jxnu.seckill.redis.key.BasePrefix;

public class AccessKey extends BasePrefix {
	private AccessKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}

	public static AccessKey access = new AccessKey(5, "access");

	public static AccessKey withExpire(int expireSeconds) {
		return new AccessKey(expireSeconds, "access");
	}
}
