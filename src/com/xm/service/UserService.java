package com.xm.service;

import java.util.List;

import com.xm.domain.Order;
import com.xm.domain.Products;
import com.xm.domain.ShoppingCar;
import com.xm.domain.User;

public interface UserService {
	public User findUser(String usercode,String password);
	public int addUser(String uname,String password,String usercode);
	public List<ShoppingCar> findShoppingCar(int uid);
	public void deleteShoppingCar(int sid);
	public void updateShoppingCarAmount(int sid, int amount);
	public void addOrder(int sid);
	public List<Order> findOrder(int uid);
	public void deleteOrder(int oid);
	public List<Products> allProducts();
	public Products findProduct(int pid);
	public void chooseAddOrder(int price, int uid, int pid);
	public void addShoppingCar(int uid, int pid);
}
