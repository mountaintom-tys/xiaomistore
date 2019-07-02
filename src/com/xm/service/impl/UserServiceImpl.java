package com.xm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xm.dao.UserDao;
import com.xm.domain.Order;
import com.xm.domain.Products;
import com.xm.domain.ShoppingCar;
import com.xm.domain.User;
import com.xm.service.UserService;

/**
 * 用户Service接口实现类
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	// 注入UserDao
	@Autowired
	private UserDao userDao;
	// 通过账号和密码查询用户
	@Override
	public User findUser(String usercode, String password) {
		User user = this.userDao.findUser(usercode, password);
		return user;
	}
	@Override
	public int addUser(String uname, String password, String usercode) {
		// TODO Auto-generated method stub
		int row=this.userDao.addUser(uname,password,usercode);
		return row;
	}
	@Override
	public List<ShoppingCar> findShoppingCar(int uid) {
		// TODO Auto-generated method stub
		List<ShoppingCar> shoppingcars=this.userDao.findShoppingCar(uid);
		return shoppingcars;
	}
	@Override
	public void deleteShoppingCar(int sid) {
		// TODO Auto-generated method stub
		this.userDao.deleteShoppingCar(sid);
	}
	@Override
	public void updateShoppingCarAmount(int sid,int amount) {
		// TODO Auto-generated method stub
		this.userDao.updateShoppingCarAmount(sid,amount);
	}
	@Override
	public void addOrder(int sid) {
		// TODO Auto-generated method stub
		this.userDao.addOrder(sid);
	}
	@Override
	public List<Order> findOrder(int uid) {
		// TODO Auto-generated method stub
		List<Order> orders=this.userDao.findOrder(uid);
		return orders;
	}
	@Override
	public void deleteOrder(int oid) {
		// TODO Auto-generated method stub
		this.userDao.deleteOrder(oid);
	}
	@Override
	public List<Products> allProducts() {
		// TODO Auto-generated method stub
		List<Products> products=this.userDao.allProducts();
		return products;
	}
	@Override
	public Products findProduct(int pid) {
		// TODO Auto-generated method stub
		Products product=this.userDao.findProduct(pid);
		return product;
	}
	@Override
	public void chooseAddOrder(int price, int uid, int pid) {
		// TODO Auto-generated method stub
		this.userDao.chooseAddOrder(price,uid,pid);
	}
	@Override
	public void addShoppingCar(int uid, int pid) {
		// TODO Auto-generated method stub
		int renumber=0;
		List<ShoppingCar> shoppingcars=this.userDao.findShoppingCar(uid);
		for(ShoppingCar shoppingcar:shoppingcars) {
			if(shoppingcar.getProducts().getPid()==pid) {
				this.userDao.updateShoppingCarAmount(shoppingcar.getSid(),shoppingcar.getAmount()+1);
				renumber++;
			}
		}
		if(renumber==0) {
			this.userDao.addShoppingCar(uid,pid);
		}
	}
}