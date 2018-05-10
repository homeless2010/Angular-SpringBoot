package com.piedpiper.commons;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 * 
 * @author homeless2010
 *
 */
@Controller
@RequestMapping(value = "/web/commons")
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	@Value(value = "${piedPiper.uploadFilePath}")
	private String filePath;

	@RequestMapping(value = "file", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upload(@RequestParam("file") MultipartFile file) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", "success");
		if (file.isEmpty()) {
			map.put("errorMsg", "文件为空!");
			map.put("type", "error");
		}
		// 文件名
		String fileName = file.getOriginalFilename();
		// 获取文件后缀
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		// 上传目录
		fileName = UUID.randomUUID() + suffixName;
		File dest = new File(filePath + fileName);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			logger.error("文件上传出错", e);
			map.put("errorMsg", e.getMessage());
			map.put("type", "error");
		} catch (IOException e) {
			logger.error("文件上传出错", e);
			map.put("errorMsg", e.getMessage());
			map.put("type", "error");
		}
		return map;
	}

}
