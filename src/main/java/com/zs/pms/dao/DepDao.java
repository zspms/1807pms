package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TDep;

public interface DepDao {
	//根据上级部门获取下级部门pid
	public List<TDep> queryByPid(int pid);

}
