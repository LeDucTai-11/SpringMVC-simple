package com.ductai.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService {

	Cloudinary cloudinary;
	private Map<String,String> valuesMap = new HashMap<>();
	
	public CloudinaryService() {
		valuesMap.put("cloud_name", "do3z6xzso");
		valuesMap.put("api_key", "451491239511316");
		valuesMap.put("api_secret", "qnEJWtOZI8yxCugBvUpVexHqPaY");
		cloudinary = new Cloudinary(valuesMap);
	}
	
	private File convertToFile(MultipartFile mpf) throws IOException {
		File file = new File(mpf.getOriginalFilename());
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(mpf.getBytes());
		fo.close();
		return file;
	}
	
	public Map upload(MultipartFile multipartFile) throws IOException {
		File file = convertToFile(multipartFile);
		Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
		file.delete();
		return result;
	}
	
	public Map delete(String id) throws IOException {
		Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
		return result;
	}
	
}
