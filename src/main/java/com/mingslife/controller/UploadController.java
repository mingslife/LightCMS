package com.mingslife.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.mingslife.web.controller.BaseController;

@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController {
	@ResponseBody
	@RequestMapping(value = "/image", method = RequestMethod.POST)
	public Map<String, Object> image(HttpServletRequest request) {
		Map<String, String> applicationMap = (Map<String, String>) application.getAttribute("application");
		String uploadPath = applicationMap.get("uploadPath");
		String uploadRoot = applicationMap.get("uploadRoot");
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(application);
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 记录上传过程起始时的时间，用来计算上传时间
				int pre = (int) System.currentTimeMillis();
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String fileName = file.getOriginalFilename();
					/*String md5;
					try {
						System.out.println("MD5计算开始: " + System.currentTimeMillis());
						md5 = DigestUtils.md5Hex(file.getBytes());
						System.out.println("MD5计算结束: " + System.currentTimeMillis());
						System.out.println("MD5: " + md5);
					} catch (Exception e) {
						e.printStackTrace();
						md5 = null;
					}*/
					
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (fileName.trim().length() != 0) {
						System.out.println("File Name: " + file.getOriginalFilename());
						System.out.println("File Size: " + file.getSize());
						System.out.println("Content Type: " + file.getContentType());
						// 重命名上传后的文件名
						String relativePath = "/images/" + generateRealPath() + "/" + generateRealName() + "_" + fileName;
						String realPath = uploadPath + relativePath;
						String url = uploadRoot + relativePath;
						jsonMap.put("name", fileName);
						jsonMap.put("url", url);
						// 定义上传路径
						File localFile = new File(realPath);
						System.out.println(localFile.getAbsolutePath());
						if (!localFile.exists()) {
							localFile.mkdirs();
						}
						try {
							file.transferTo(localFile);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				// 记录上传该文件后的时间
				int finaltime = (int) System.currentTimeMillis();
				System.out.println(finaltime - pre);
			}
		}
		return jsonMap;
	}

	@ResponseBody
	@RequestMapping(value = "/image/{year}/{month}/{date}/{uuid}", method = RequestMethod.GET)
	public void image(@PathVariable String year, @PathVariable String month, @PathVariable String date, @PathVariable String uuid) {
//		String referer = request.getHeader("Referer");
//		System.out.println(referer);
//		Pattern pattern = Pattern.compile("(?<//|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
//		Matcher matcher = pattern.matcher("https://www.baidu.com/index.php");
//		if (matcher.find()) {
//			System.out.println(matcher.group());
//		}
		String uploadPath = (String) application.getAttribute("uploadPath");
//		String uploadRoot = (String) application.getAttribute("uploadRoot");
		File file = new File(uploadPath + "/" + year + "/" + month + "/" + date + "/" + uuid);
		try {
			FileInputStream inputStream = new FileInputStream(file);
			OutputStream outputStream = response.getOutputStream();
			response.setContentType("image/png");
			byte[] buffer = new byte[102400];
			int len = 0;
			while ((len = inputStream.read(buffer, 0, 102400)) != -1) {
				outputStream.write(buffer, 0, len);
				outputStream.flush();
			}
//			byte[] data = new byte[(int) file.length()];
//			int length = inputStream.read(data);
			inputStream.close();
//			outputStream.write(data);
//			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String generateRealPath() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date now = new Date();
		return simpleDateFormat.format(now);
	}

	private String generateRealName() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
