package com.zs.pms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.pms.dao.UserDao;
import com.zs.pms.exception.AppException;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.untils.Constants;
import com.zs.pms.untils.MD5;
import com.zs.pms.vo.QueryUser;

@Service
@Transactional	//需要开启事务的业务对象
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao dao;
	
	@Override
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("测试框架是否搭建成功");
	}
	
	@Override
	public List<TPermission> queryByUid(int id) {
		// TODO Auto-generated method stub
		//调用UserDao中的通过用户id查询权限列表的方法
		return dao.queryByUid(id);
	}

	@Override
	public List<TPermission> genMenu(List<TPermission> pers) {
		//通过原有权限列表整理成菜单形式
		//创建新容器
		List<TPermission> list=new ArrayList<>();
		//遍历权限列表
		for(TPermission per:pers) {
			//一级菜单(等级为1的是一级菜单)
			if (per.getLev()==1) {
				//加载一级菜单下的二级菜单
				//遍历
				for(TPermission per2:pers) {
					//二级菜单(二级菜单的pid等于一级菜单的id)
					if (per2.getPid()==per.getId()) {
						//添加子权限
						per.addChild(per2);
					}
				}
				//将一级菜单放入新创建的容器中
				list.add(per);
			}
		}
		//返回整理好的集合
		return list;
	}

	@Override
	public List<TUser> queryByCon(QueryUser qu) {
		// 通过条件查询
		return dao.queryByCon(qu);
	}
	// 批量删除
	@Override
	//只要有异常就回滚事务
	@Transactional(rollbackFor=Exception.class)
	public void deleteByIds(int[] ids) {
		// 批量删除
		dao.deleteByIds(ids);
	}
	/**
	 * 修改
	 */
	@Transactional(rollbackFor=Exception.class)
	public void updateUser(TUser user)throws AppException {
		// 修改
		//模拟异常
		if ("".equals(user.getLoginname())) {
			throw new AppException(1004,"登录名不能为空");
		}
		//判断是否需要MD5加密
		if (user.getPassword().length()!=32) {
			MD5 md5=new MD5();
			user.setPassword(md5.getMD5ofStr(user.getPassword()));
		}
		dao.updateUser(user);
	}
	// 新增返回主键
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int insertUser(TUser user) throws AppException {
		MD5 md5=new MD5();
		user.setPassword(md5.getMD5ofStr(user.getPassword()));
		if ("".equals(user.getLoginname())) {
			throw new AppException(1003, "登录名不能为空");
		}
		// 新增返回主键
		dao.insertUser(user);
		//int a=1/0; //测试事务的回滚
		return user.getId();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteById(int id) {
		//删除一条数据
		dao.deleteById(id);
	}

	@Override
	public TUser queryById(int id) {
		//查询一条数据
		return dao.queryById(id);
	}

	@Override
	/**
	 * 分页查询
	 */
	public List<TUser> queryByPage(int page, QueryUser query) {
		//分页查询
		//通过当前页数计算起始数和截止数
		//起始条数 从1开始
		int start = (page-1)*Constants.PAGECONT+1;
		//截止条数
		int end =page*Constants.PAGECONT;
		//将起始数与截止数封装
		query.setStart(start);
		query.setEnd(end);
		
		return dao.queryByPage(query);
	}

	/**
	 * 查询总页数
	 */
	public int queryPageCont(QueryUser query) {
		//获得总页数
		int count=dao.queryCount(query);
		//计算总页数
		if (count%Constants.PAGECONT==0) {
			return count/Constants.PAGECONT;
		} else {
			return count/Constants.PAGECONT+1;
		}
		
	}

	@Override
	public int queryCount(QueryUser query) {
		// 查询总条数
		return dao.queryCount(query);
	}

}
