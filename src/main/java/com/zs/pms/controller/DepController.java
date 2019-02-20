package com.zs.pms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.pms.po.TDep;
import com.zs.pms.service.DepService;

@Controller
public class DepController {
	
	@Autowired
	DepService ds;
	
	/**
	 * 通过AJAXS获取二级部门
	 * 方法返回对象或对象集合
	 * 自动以json的形式写入ResponseBody
	 * @return
	 */
	@RequestMapping("/user/getdeps.do")
	@ResponseBody
	public List<TDep> getDeps(int pid) {
		return ds.queryByPid(pid);
	}
	
	
}
