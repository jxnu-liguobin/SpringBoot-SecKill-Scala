package cn.edu.jxnu.seckill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.jxnu.seckill.dao.UserDao;
import cn.edu.jxnu.seckill.domain.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public User getById(int id) {
		return userDao.getById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean tx() {
		User u1 = new User();
		u1.setId(2);
		u1.setName("22222");
		userDao.insert(u1);

		return true;
	}
}
