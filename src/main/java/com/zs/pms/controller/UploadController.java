package com.zs.pms.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传下载控制器
 * @author Administrator
 *
 */
@Controller
public class UploadController {
	
	
	/**
	 * 普通文件上传
	 * @param file 上传的文件 (和add.jsp中input的name属性值一致)
	 * @param req	用来获取物理路径
	 * @return 返回上传的文件路径(filename)
	 */
	@RequestMapping("/upload/common.do")
	@ResponseBody
	public String commonUpload(MultipartFile file , HttpServletRequest req) {
		//使用UUID算法生成前缀
		String pfix=UUID.randomUUID().toString();
		//生成文件名 前缀+原始文件名
		String filename=pfix+file.getOriginalFilename();
		//获得upload文件夹的物理路径
		String path=req.getRealPath("/upload");
		//生成目标文件  File.separator分隔符
		File dfile=new File(path+File.separator+filename);
		try {
			//将上传文件写入到目标文件
			file.transferTo(dfile);
			//返回文件名
			return filename;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
	
}
