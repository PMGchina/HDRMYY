package com.beamofsoul.springboot.management.util;

import static com.beamofsoul.springboot.management.util.ConfigurationReader.asInteger;
import static com.beamofsoul.springboot.management.util.ConfigurationReader.asString;
import static com.beamofsoul.springboot.management.util.ConfigurationReader.getValue;
import static com.beamofsoul.springboot.management.util.ConfigurationReader.SERVER_CONTEXT_PATH;
import static com.beamofsoul.springboot.management.util.ConfigurationReader.PROJECT_COMPONENT_CKFINDER_UPLOAD_IMAGE_PATH;
import static com.beamofsoul.springboot.management.util.ConfigurationReader.PROJECT_COMPONENT_CKFINDER_MAX_IMAGE_SIZE;

import static com.beamofsoul.springboot.management.util.Callback.CALLBACK_PLACEHOLDER;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Base64;

import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Cleanup;
import lombok.NonNull;

/**
 * @ClassName ImageUtils
 * @Description 处理Image与Base64加密字符串互相转换的工具类
 * @author MingshuJian
 * @Date 2017年3月4日 上午9:07:44
 * @version 1.0.0
 */
public class ImageUtils {

	public static final int MAX_IMAGE_SIZE = 1024 * 1024 * asInteger(getValue(PROJECT_COMPONENT_CKFINDER_MAX_IMAGE_SIZE));
	public static final String JPEG = "jpeg";
	public static final String JPG = "jpg";
	public static final String PNG = "png";
	public static final String GIF = "gif";

	/**
	 * @Title: imageToBase64
	 * @Description: 根据输入图片路径，将图片转换为base64加密字符串
	 * @param path - 图片的路径
	 * @return String - 图片对应的base64加密字符串
	 */
	public static String imageToBase64(String path) {
		byte[] base64 = null;

		try {
			@Cleanup
			InputStream in = new FileInputStream(path);
			base64 = new byte[in.available()];
			in.read(base64);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//需要后续修改，根据具体图片类型拼接具体base64文件头
		return base64 == null ? null : "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(base64);
	}

	/**
	 * @Title: base64ToImage
	 * @Description: 根据base64加密字符串生成图片的字节数组
	 * @param base64 - 图片对应的base64加密字符串
	 * @return byte[] - 图片的字节数组
	 */
	public static byte[] base64ToImage(String base64) {
		byte[] bytes = null;
		if (StringUtils.isBlank(base64)) {
			return bytes;
		}

		try {
			// Base64解码
			bytes = Base64.getDecoder().decode(base64);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}

	/**
	 * @Title: base64ToImage
	 * @Description: 根据base64加密字符串和图片路径生成图片(.jpeg)至该路径
	 * @param base64 - 图片对应的base64加密字符串
	 * @param path - 生成图片的路径
	 * @return boolean - true: 转换成功, false: 转换失败
	 */
	public static boolean base64ToImage(String base64, @NonNull String path, @NonNull String fileName, String suffix) {
		byte[] bytes = base64ToImage(base64);
		if (bytes != null) {
			try {
				//为存储图片创建目录
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();
				}

				//输出图片文件
				@Cleanup
				FileImageOutputStream out = new FileImageOutputStream(new File(generateImageFilePath(path, fileName, suffix)));
				out.write(bytes, 0, bytes.length);
				out.flush();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public static String generateImageFilePath(@NonNull String path, @NonNull String fullFileName) {
		return path + "//" + fullFileName;
	}

	public static String generateImageFilePath(String path, @NonNull String fileName, String suffix) {
		if (suffix == null) {
			suffix = JPEG;
		}
		if (suffix.indexOf(JPG) != -1) {
			suffix = JPG;
		} else if (suffix.indexOf(JPEG) != -1) {
			suffix = JPEG;
		} else if (suffix.indexOf(PNG) != -1) {
			suffix = PNG;
		} else if (suffix.indexOf(GIF) != -1) {
			suffix = GIF;
		} else {
			suffix = JPEG;
		}
		return path == null ? fileName + "." + suffix : path + "//" + fileName + "." + suffix;
	}

	/**
	 * @Title: getClearPhotoString
	 * @Description: 截取base64加密信息头(文件格式信息)，并返回剩余信息
	 * @return String 被截取后的剩余信息
	 */
	public static String getClearPhotoString(String photoString) {
		int delLength = photoString.indexOf(",") + 1;
		return photoString.substring(delLength, photoString.length());
	}

	/**
	 * @Title: getPhotoType
	 * @Description: 根据完整的base64加密信息头获取图片格式
	 * @return String 图片格式
	 */
	public static String getPhotoType(String photoString) {
		int colonIndex = photoString.indexOf(":");
		int semiIndex = photoString.indexOf(";");
		if (colonIndex > 0 && semiIndex > 0) {
			return photoString.substring(colonIndex + 1, semiIndex);
		}
		return null;
	}

	/**
	 * @Title: isEmptyDuringUploading
	 * @Description: 判断在使用ckfinder上传文件时，上传的文件是否不存在或内容为空
	 * @param upload - 上传的文件对象
	 * @param callback - 如果不存在或内容为空则调用的回调函数
	 * @return boolean 是否不存在或内容为空
	 */
	public static boolean isEmptyDuringUploading(MultipartFile upload, Callback callback) {
		if (isEmptyDuringUploading(upload)) {
			final String callFunction = "window.parent.CKEDITOR.tools.callFunction(";
			callback.doCallback(callFunction + CALLBACK_PLACEHOLDER + ",''," + "'请选择要上传的文件');");
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public static boolean isEmptyDuringUploading(MultipartFile upload) {
		return upload == null || upload.isEmpty();
	}

	/**
	 * @Title: uploadImage
	 * @Description: 使用ckfinder上传图片
	 * @param upload - 上传的图片
	 * @param callback - 上传成功或失败后的回调函数
	 * @throws Exception - 序列化图片过程中可能出现的所有异常包括IOException等
	 */
	public static void uploadImage(MultipartFile upload, Callback callback) throws Exception {
		// 获取文件名称和文件类型
		String fileName = upload.getOriginalFilename();
		String contentType = upload.getContentType();
		// 如果上传的文件格式非图片类型，直接响应并提示必须为图片类型
		final String callFunction = "window.parent.CKEDITOR.tools.callFunction(" + CALLBACK_PLACEHOLDER;
		if (!isProperImageType(contentType)) {
			callback.doCallback(callFunction + ",'','文件格式不正确(必须为.jpg/.gif/.bmp/.png文件)');");
			return;
		}
		// 如果上传的图片尺寸超过的允许的最大值，直接响应并提示图片大小不得大于最大值
		if (!isProperImageSize(upload)) {
			callback.doCallback(callFunction + ",'','文件大小不得大于2M');");
			return;
		}
		// 顺利通过各种检查后，将图片保存至配置文件指定的位置，并响应且将页面“图像”选项卡切换至预览上传后的图片
		final String imagePath = asString(getValue(PROJECT_COMPONENT_CKFINDER_UPLOAD_IMAGE_PATH)) + fileName;

		System.out.println("######" + ImageUtils.class.getResource("/").getPath());

		upload.transferTo(new File(ImageUtils.class.getResource("/").getPath() + imagePath));
		// http://localhost:8080/hdrmyy/static/userfiles/images/*.*
		// - 图片上传以后会自动跳转到图像信息选项卡并打开预览，此路径为此时的预览路径
		callback.doCallback(callFunction + ",'" + asString(getValue(SERVER_CONTEXT_PATH)) + "/" + imagePath + "','')");
	}

	public static boolean isProperImageSize(MultipartFile upload) {
		return !(upload.getSize() > MAX_IMAGE_SIZE);
	}

	public static boolean isProperImageType(String contentType) {
		return !(!contentType.equals("image/bmp") && !contentType.equals("image/gif") && !contentType.equals("image/png")
			&& !contentType.equals("image/x-png") && !contentType.equals("image/jpeg") && !contentType.equals("image/pjpeg"));
	}
}
