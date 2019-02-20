package com.zs.test.user;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TDep;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.vo.QueryUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationcontext.xml")
public class TestUserService {
	
	@Autowired
	UserService us;
	
	
	public void test() {
		us.test();
	}
	
	public void testLogin() {
		//测试权限列表
		List<TPermission> list = us.queryByUid(3084);
		for(TPermission per:list) {
			System.out.println(per.getPname());
		}
		System.out.println("----------整理后的菜单------------");
		for(TPermission per1:us.genMenu(list)) {
			//生成一级菜单
			System.out.println("-"+per1.getPname());
			for(TPermission per2:per1.getChildren()) {
				//生成二级菜单
				System.out.println("---"+per2.getPname());
			}
		}
		
	}
	
	
	public void testQuery() {
		//测试查询
		//创建查询对象
		QueryUser qu=new QueryUser();
//		qu.setLoginname("admin");
//		qu.setPassword("123");
//		qu.setSex("男");
//		System.out.println(us.queryByCon(qu).size());
		for(TUser user:us.queryByPage(3, qu)) {
			System.out.println(user.getId()+"==="+user.getLoginname());
		}
		System.out.println("共"+us.queryCount(qu)+"条");
		System.out.println("共"+us.queryPageCont(qu)+"页");
		
	}
	
	public void testDeletes() {
		// 批量删除
		int[] ids= {1,2}; 
		us.deleteByIds(ids);
	}
	
	
	public void testUpdate() {
		//修改
		TUser user=new TUser();
		user.setBirthday(new Date());
		TDep dept=new TDep();
		dept.setId(6);
		user.setDept(dept);
		user.setEmail("update@123.com");
		user.setId(3);
		user.setIsenabled(-1);
		user.setLoginname("update123");
		user.setPassword("update123123");
		user.setPic("update123.jpg");
		user.setRealname("修改123");
		user.setSex("男");
		user.setUpdator(3084);
		
		try {
			us.updateUser(user);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testInsert() {
		//新增 返回主键
		TUser user=new TUser();
		user.setBirthday(new Date());
		TDep dept=new TDep();
		dept.setId(6);
		user.setDept(dept);
		user.setEmail("insert@123.com");
		user.setIsenabled(1);
		user.setLoginname("insert123_tansactional");
		user.setPassword("insert123123");
		user.setPic("insert123.jpg");
		user.setRealname("新增123");
		user.setSex("男");
		user.setCreator(3084);
		try {
			System.out.println(us.insertUser(user));
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testDelete() {
		//删除一条数据
		us.deleteById(4);
	}
	
	public void testQueryById() {
		//查询一条数据
		TUser user = us.queryById(1001);
		System.out.println(user.getLoginname());
		
	}
	
	
	
	
	
	
	
	
	
	
}
