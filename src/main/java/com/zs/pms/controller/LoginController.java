package com.zs.pms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.untils.DateUtil;
import com.zs.pms.untils.MD5;
import com.zs.pms.vo.QueryLogin;
import com.zs.pms.vo.QueryUser;

@Controller //这是一个控制器
public class LoginController {
	
	@Autowired
	UserService us;

	/**
	 * 去登录页面
	 * @return
	 */
	@RequestMapping("/tologin.do")
	public String toLogin() {
		return "login";
	}
	
	/**
	 * 登录成功去主页面
	 * @return
	 */
	@RequestMapping("/login.do")
	public String login(QueryLogin login,HttpSession session,ModelMap model) {
		//获得一开始生成的验证码
		String chkcode=(String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		//判断输入的信息和生成的验证码是否一致,如果不一致直接回到登录界面,
		//如果一致接着验证账号密码,区分大小写
		if (!chkcode.equals(login.getChkcode())) {
			model.addAttribute("MSG","验证码输入有误,请重新输入");
			return "login";
		}
		//判断用户名和密码是否输入正确
		//获取登录名和密码
		QueryUser query=new QueryUser();
		query.setLoginname(login.getLoginname());
		//密码需要MD5加密
		MD5 md5=new MD5();
		query.setPassword(md5.getMD5ofStr(login.getPassword()));
//		query.setPassword(login.getPassword());
		query.setIsenabled(1);//设置可用,用来进行查询
		//返回登录的用户
		List<TUser> users = us.queryByCon(query);
		//判断登录是否成功
		if (users==null || users.size()!=1) {
			model.addAttribute("MSG","用户名或密码有误,请重新输入");
			return "login";
		}
		//登录成功 装session
		session.setAttribute("CUSER",users.get(0));
		//去主页面
		return "main";
	}
	
	
	/**
	 * 去顶部页面
	 * @return
	 */
	@RequestMapping("/top.do")
	public String top(HttpSession session) {
		//将登录日期带到页面
		session.setAttribute("TODAY",DateUtil.getStrDate());
		return "top";
	}
	
	/**
	 * 去左侧菜单页面
	 * @return
	 */
	@RequestMapping("/left.do")
	public String menu(HttpSession session,ModelMap model) {
		//获得当前登录用户
		TUser user=(TUser) session.getAttribute("CUSER");
		//获取权限列表
		List<TPermission> list1 = us.queryByUid(user.getId());
		//获得整理后的权限列表 并传回页面
		model.addAttribute("MENU",us.genMenu(list1));
		
		return "left";
	}
	
	/**
	 * 去右侧页面
	 * @return
	 */
	@RequestMapping("/right.do")
	public String main() {
		return "right";
	}
}
