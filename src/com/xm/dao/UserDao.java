package com.xm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xm.domain.Order;
import com.xm.domain.Products;
import com.xm.domain.ShoppingCar;
import com.xm.domain.User;;

public interface UserDao {
	/**
	 * 通过账号和密码查询用户
	 */
	public User findUser(@Param("usercode") String usercode,
			                @Param("password") String password);
	public int addUser(@Param("uname") String uname,@Param("password") String password,
			@Param("usercode") String usercode);
	public List<ShoppingCar> findShoppingCar(@Param("uid") int uid);
	public void deleteShoppingCar(int sid);
	public void updateShoppingCarAmount(@Param("sid") int sid,@Param("amount") int amount);
	public void addOrder(int sid);
	public List<Order> findOrder(int uid);
	public void deleteOrder(int oid);
	public List<Products> allProducts();
	public Products findProduct(int pid);
	public void chooseAddOrder(@Param("price") int price, @Param("uid") int uid,@Param("pid") int pid);
	public void addShoppingCar(@Param("uid") int uid,@Param("pid") int pid);
}
