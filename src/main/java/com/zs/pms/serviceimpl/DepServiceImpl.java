package com.zs.pms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.pms.dao.DepDao;
import com.zs.pms.po.TDep;
import com.zs.pms.service.DepService;
@Service
public class DepServiceImpl implements DepService {

	@Autowired
	DepDao dao;
	
	
	/**
	 * 获取二级部门
	 */
	public List<TDep> queryByPid(int pid) {
		
		return dao.queryByPid(pid);
	}

}
