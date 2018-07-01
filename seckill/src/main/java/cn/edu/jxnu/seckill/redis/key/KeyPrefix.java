package cn.edu.jxnu.seckill.redis.key;

public interface KeyPrefix {
	
	public int expireSeconds();

	public String getPrefix();
}
