package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;

/**
 * @author Administrator
 * 数据服务接口
 */
public interface UserDao {
	
	//通过用户id查询权限列表
	public List<TPermission> queryByUid(int id);
	//通过条件查询
	public List<TUser> queryByCon(QueryUser qu);
	//分页查询
	public List<TUser> queryByPage(QueryUser query);
	//查询总条数
	public int queryCount(QueryUser query);
	//批量删除
	public void deleteByIds(int[] ids);
	//修改
	public void updateUser(TUser user);
	//新增 返回主键
	public int insertUser(TUser user);
	//删除一条数据
	public void deleteById(int id);
	//查询一条数据
	public TUser queryById(int id);
	
}
