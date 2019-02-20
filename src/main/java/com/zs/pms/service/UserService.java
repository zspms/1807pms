package com.zs.pms.service;

import java.util.List;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;

/**
 * @author Administrator 逻辑层接口
 */
public interface UserService {
	
	//测试
	public void test();

	// 通过用户id查询权限列表
	public List<TPermission> queryByUid(int id);

	// 根据原有权限整理菜单
	public List<TPermission> genMenu(List<TPermission> pers);

	// 通过条件查询
	public List<TUser> queryByCon(QueryUser qu);

	// 批量删除
	public void deleteByIds(int[] ids);

	// 修改
	public void updateUser(TUser user)throws AppException;

	// 新增 返回主键
	public int insertUser (TUser user) throws AppException;
	
	//分页查询
	public List<TUser> queryByPage(int page,QueryUser query);
	
	//查询总页数
	public int queryPageCont(QueryUser query);

	//查询总条数
	public int queryCount(QueryUser query);

	
	// 删除一条数据
	public void deleteById(int id);

	// 查询一条数据
	public TUser queryById(int id);

}
