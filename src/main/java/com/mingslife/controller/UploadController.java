package com.mingslife.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mingslife.model.Image;
import com.mingslife.service.IImageService;
import com.mingslife.web.controller.BaseController;

@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController {
	private static final int IMAGE_MAX_SIZE = 1000;
	private static final double IMAGE_QUALITY = 0.8;

	@Autowired
	private IImageService imageService;

	@ResponseBody
	@RequestMapping(value = "/image", method = RequestMethod.POST)
	public Map<String, Object> image(HttpServletRequest request, @RequestParam(value = "compress", defaultValue = "true") boolean compress) {
		@SuppressWarnings("unchecked")
		Map<String, String> applicationMap = (Map<String, String>) application.getAttribute("application");
		String uploadPath = applicationMap.get("uploadPath");
		String uploadRoot = applicationMap.get("uploadRoot");
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");
		String fileName;
		if (file != null && (fileName = file.getOriginalFilename()).trim().length() != 0) {
			System.out.println("File Name: " + file.getOriginalFilename());
			System.out.println("File Size: " + file.getSize());
			System.out.println("Content Type: " + file.getContentType());
			
			String contentType = file.getContentType();
			String suffix;
			if (contentType.equals("image/jpeg")) {
				suffix = ".jpg";
			} else if (contentType.equals("image/gif")) {
				suffix = ".gif";
			} else if (contentType.equals("image/png")) {
				suffix = ".png";
			} else if (contentType.equals("image/bmp")) {
				suffix = ".bmp";
			} else {
				suffix = "";
			}
			
			String md5 = null;
			Image image = null;
			try {
				if (compress) {
					// 压缩
					String sourceMd5 = DigestUtils.md5Hex(file.getBytes());
					image = imageService.findBySourceMd5(sourceMd5);
					if (image == null) {
						String savePath = generateRealPath();
						String saveFileName = generateRealName();
						String saveFullFileName = saveFileName + suffix;
						String saveRealPath = uploadPath + "/images/" + savePath;
						String url = uploadRoot + "/images/" + savePath + "/" + saveFullFileName;
						String imagePath = savePath + "/" + saveFullFileName;
						
						BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
						int width = bufferedImage.getWidth();
						int height = bufferedImage.getHeight();
						
						int max = Math.max(width, height);
						if (max > IMAGE_MAX_SIZE) {
							width = (int) Math.ceil(width * 1.0 / max * IMAGE_MAX_SIZE);
							height = (int) Math.ceil(height * 1.0 / max * IMAGE_MAX_SIZE);
						}
						
						File localFile = new File(saveRealPath, saveFullFileName);
						if (!localFile.exists() || localFile.isDirectory()) {
							File parentFile = localFile.getParentFile();
							if (!parentFile.exists()) {
								parentFile.mkdirs();
							}
							localFile.createNewFile();
						}
						
						System.out.println(width + "x" + height);
						Thumbnails.of(bufferedImage).size(width, height).outputQuality(IMAGE_QUALITY).toFile(localFile);
						
						md5 = DigestUtils.md5Hex(new FileInputStream(localFile));
						
						image = new Image();
						image.setName(fileName);
						image.setPath(imagePath);
						image.setUrl(url);
						image.setSize(localFile.length());
						image.setContentType(contentType);
						image.setMd5(md5);
						image.setSourceMd5(sourceMd5); // 有原图MD5，即本身是缩略图
						image.setWidth(width);
						image.setHeight(height);
						imageService.save(image);
					}
				} else {
					// 不压缩
					md5 = DigestUtils.md5Hex(file.getBytes());
					image = imageService.findByMd5(md5);
					if (image == null) {
						// 图片不存在
						String savePath = generateRealPath();
						String saveFileName = generateRealName();
						String saveFullFileName = saveFileName + suffix;
						String saveRealPath = uploadPath + "/images/" + savePath;
						String url = uploadRoot + "/images/" + savePath + "/" + saveFullFileName;
						String imagePath = savePath + "/" + saveFullFileName;
						
						BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
						int width = bufferedImage.getWidth();
						int height = bufferedImage.getHeight();
						
						File localFile = new File(saveRealPath, saveFullFileName);
						if (!localFile.exists() || localFile.isDirectory()) {
							File parentFile = localFile.getParentFile();
							if (!parentFile.exists()) {
								parentFile.mkdirs();
							}
							localFile.createNewFile();
						}
						
						file.transferTo(localFile);
						
						image = new Image();
						image.setName(fileName);
						image.setPath(imagePath);
						image.setUrl(url);
						image.setSize(file.getSize());
						image.setContentType(contentType);
						image.setMd5(md5);
						image.setSourceMd5(null); // 无原图MD5，即本身就是原图
						image.setWidth(width);
						image.setHeight(height);
						imageService.save(image);
					}
				}
				
				jsonMap.put("id", image.getId());
				jsonMap.put("name", fileName);
				jsonMap.put("url", image.getUrl());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return jsonMap;
	}
	/*@ResponseBody
	@RequestMapping(value = "/image0", method = RequestMethod.POST)
	public Map<String, Object> image0(HttpServletRequest request) {
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
					String md5;
					try {
						System.out.println("MD5计算开始: " + System.currentTimeMillis());
						md5 = DigestUtils.md5Hex(file.getBytes());
						System.out.println("MD5计算结束: " + System.currentTimeMillis());
						System.out.println("MD5: " + md5);
					} catch (Exception e) {
						e.printStackTrace();
						md5 = null;
					}
					
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
							Thread.sleep(1000L);
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
	}*/

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
