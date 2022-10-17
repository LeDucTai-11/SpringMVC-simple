package com.ductai.utils;

import java.util.HashMap;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MessageUtil {
	public Map<String,String> getMessage(String message) {
		Map<String, String> result = new HashMap<String, String>();
		if(message.equals("update_success")) {
			result.put("message", "Cập nhật thông tin bài viết thành công !");
			result.put("alert", "success");
		}else if(message.equals("insert_success")) {
			result.put("message", "Thêm bài viết thành công !");
			result.put("alert", "success");
		}else if(message.equals("error_system")) {
			result.put("message", "Đã có lỗi xảy ra trên hệ thống !");
			result.put("alert", "danger");
		}else if(message.equals("delete_success")) {
			result.put("message", "Đã xóa thành công bài viết !");
			result.put("alert", "success");
		}else if(message.equals("error_system")) {
			result.put("message", "Đã có lỗi xảy ra trên hệ thống !");
			result.put("alert", "danger");
		}
		return result;
	}
}
