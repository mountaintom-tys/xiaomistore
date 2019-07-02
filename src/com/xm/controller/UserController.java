package com.xm.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xm.domain.Order;
import com.xm.domain.Products;
import com.xm.domain.ShoppingCar;
import com.xm.domain.User;
import com.xm.service.UserService;

/**
 * 用户控制器类
 */
@Controller
public class UserController {
	// 依赖注入
	@Autowired
	private UserService userService;
	/**
	 * 用户登录
	 */
	@RequestMapping("/xiangqing.action")
	public String xiangqing(String pid,HttpServletRequest request, Model model,HttpSession session) {
		session = request.getSession();
		User user = (User) session.getAttribute("USER_SESSION");
		Products product=userService.findProduct(Integer.parseInt(pid));
		if (user != null) {
			model.addAttribute("status", "欢迎"+user.getUname());
			model.addAttribute("href", "self_info.action");
			model.addAttribute("status1", "");
			model.addAttribute("href1", "");
		}
		else {
			model.addAttribute("status", "登录");
			model.addAttribute("href", "denglu.action");
			model.addAttribute("status1", "注册");
			model.addAttribute("href1", "zhuce.action");
		}
		model.addAttribute("product", product);
		return "xiangqing";
	}
	@RequestMapping("/liebiao.action")
	public String liebiao(HttpServletRequest request, Model model,HttpSession session) {
		session = request.getSession();
		User user = (User) session.getAttribute("USER_SESSION");
		List<Products> products=userService.allProducts();
		// 判断Session中是否有用户数据，如果有，则返回true,继续向下执行
		if (user != null) {
			model.addAttribute("status", "欢迎"+user.getUname());
			model.addAttribute("href", "self_info.action");
		}
		else {
			model.addAttribute("status", "登录");
			model.addAttribute("href", "denglu.action");
		}
		model.addAttribute("products", products);
		return "liebiao";
	}
	@RequestMapping("/deleteOrder.action")
	public void deleteOrder(String oid) {
		userService.deleteOrder(Integer.parseInt(oid));
	}
	@RequestMapping("/chooseAddOrder.action")
	public String chooseAddOrder(String price,String pid,HttpServletRequest request,HttpSession session) {
		session = request.getSession();
		User user = (User) session.getAttribute("USER_SESSION");
		int uid=user.getUid();
		userService.chooseAddOrder(Integer.parseInt(price),uid,Integer.parseInt(pid));
		return "redirect:xiangqing.action?pid="+pid;
	}
	@RequestMapping("/addOrder.action")
	public String addOrder(String sid) {
		userService.addOrder(Integer.parseInt(sid));
		userService.deleteShoppingCar(Integer.parseInt(sid));
		return "redirect:gouwuche.action";
	}
	@RequestMapping("/addShoppingCar.action")
	public String addShoppingCar(String pid,HttpServletRequest request,HttpSession session) {
		session = request.getSession();
		User user = (User) session.getAttribute("USER_SESSION");
		int uid=user.getUid();
		userService.addShoppingCar(uid,Integer.parseInt(pid));
		return "redirect:xiangqing.action?pid="+pid;
	}
	@RequestMapping("/updateShoppingCarAmount.action")
	public String updateShoppingCarAmount(String sid,String amount) {
		userService.updateShoppingCarAmount(Integer.parseInt(sid),Integer.parseInt(amount));
		return "redirect:gouwuche.action";
	}
	@RequestMapping("/deleteShoppingCar.action")
	public String deleteShoppingCar(String sid) {
		userService.deleteShoppingCar(Integer.parseInt(sid));
		return "redirect:gouwuche.action";
	}
	@RequestMapping("/xiaomishangcheng.action")
	public String xiaomishangcheng(HttpServletRequest request, Model model,HttpSession session) {
		session = request.getSession();
		User user = (User) session.getAttribute("USER_SESSION");
		if (user != null) {
			model.addAttribute("status", "欢迎"+user.getUname());
			model.addAttribute("href", "self_info.action");
		}
		else {
			model.addAttribute("status", "登录");
			model.addAttribute("href", "denglu.action");
			model.addAttribute("line", "|");
			model.addAttribute("status1", "注册");
			model.addAttribute("href1", "zhuce.action");
			model.addAttribute("line", "|");
		}
		return "firstpage";
	}
	@RequestMapping("/dingdanzhongxin.action")
	public String dingdanzhongxin(HttpServletRequest request, Model model,HttpSession session) {
		session = request.getSession();
		User user = (User) session.getAttribute("USER_SESSION");
		int uid=user.getUid();
		List<Order> orders=userService.findOrder(uid);
		model.addAttribute("orders",orders);
		return "dingdanzhongxin";
	}
	@RequestMapping("/gouwuche.action")
	public String gouwuche(HttpServletRequest request, Model model,HttpSession session) {
		session = request.getSession();
		User user = (User) session.getAttribute("USER_SESSION");
		int uid=user.getUid();
		List<ShoppingCar> shoppingcars=userService.findShoppingCar(uid);
		int total=0;
		for(ShoppingCar shoppingcar:shoppingcars)
		{
			total=total+shoppingcar.getSubtotal();
		}
		model.addAttribute("shoppingcars",shoppingcars);
		model.addAttribute("total", total);
		return "gouwuche";
	}
	@RequestMapping("/denglu.action")
	public String denglu() {
		return "login";
	}
	@RequestMapping("/zhuce.action")
	public String zhuce() {
		return "register";
	}
	@RequestMapping("/self_info.action")
	public String self_info(HttpServletRequest request, Model model,HttpSession session) {
		session = request.getSession();
		User user = (User) session.getAttribute("USER_SESSION");
		model.addAttribute(user);
		return "self_info";
	}
	@RequestMapping("/login.action")
	public String login(@RequestParam("usercode")String usercode,@RequestParam("password")String password, @RequestParam("yanzhengma")String yanzhengma, Model model, 
                                                          HttpSession session) {
		// 通过账号和密码查询用户
		String yanzhengcode=yanzhengma.toLowerCase();
		if(!yanzhengma.equals("rmcc"))
		{
			JOptionPane.showMessageDialog(null, "验证码错误！", "温馨提示", JOptionPane.ERROR_MESSAGE);
			return "login";
		}
		User user = userService.findUser(usercode, password);
		if(user != null){		
			// 将用户对象添加到Session
			session.setAttribute("USER_SESSION", user);
			// 跳转到主页面
			//return "customer";
			model.addAttribute(user);
			return "redirect:xiaomishangcheng.action";
		}
		JOptionPane.showMessageDialog(null, "账号或密码错误！", "温馨提示", JOptionPane.ERROR_MESSAGE);
         // 返回到登录页面
		return "login";
	}

	@RequestMapping("/register.action")
	public String register(String username,String password,String repassword,String tel, Model model, 
                                                          HttpSession session) {
		// 通过账号和密码查询用户
		if(!password.equals(repassword)) {
			JOptionPane.showMessageDialog(null, "密码输入不一致！", "温馨提示", JOptionPane.ERROR_MESSAGE);
			return "register";
		}
		else
		{
			User user = userService.findUser(tel, password);
			if(user != null){		
			// 用户已存在
				model.addAttribute("msg", "该手机号已被注册！");
				return "register";
				}
			else
			{
				int row=userService.addUser(username,password,tel);
				return "login";
			}
		}
	}

	/**
	 * 模拟其他类中跳转到客户管理页面的方法
	 */
	@RequestMapping(value = "/toCustomer.action")
	public String toCustomer() {
	    return "customer";
	}
	
	/**
	 * 退出登录
	 */
	@RequestMapping(value = "/logout.action")
	public String logout(HttpSession session) {
	    // 清除Session
	    session.invalidate();
	    // 重定向到登录页面的跳转方法
	    return "redirect:login.action";
	}
	/**
	 * 向用户登陆页面跳转
	 */
	@RequestMapping(value = "/login.action", method = RequestMethod.GET)
	public String toLogin() {
	    return "login";
		}
	}
