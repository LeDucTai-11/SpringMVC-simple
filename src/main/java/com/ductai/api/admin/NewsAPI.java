package com.ductai.api.admin;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ductai.dto.NewsDTO;
import com.ductai.service.CloudinaryService;
import com.ductai.service.INewService;

@RestController(value = "newsAPIOfAdmin")
public class NewsAPI {
	
	@Autowired
	private INewService newService;
	
	
	@Autowired
	private CloudinaryService cloudinaryService;
	
	private static String secure_url;
	
	@PostMapping("/api/news/uploadFiles")
	public String uploadFiles(MultipartHttpServletRequest reqFile) {
		Iterator<String> listFiles = reqFile.getFileNames();
		MultipartFile mpf = reqFile.getFile(listFiles.next());
		Map result = null;
		try {
			result = cloudinaryService.upload(mpf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setSecure_url((String) result.get("secure_url"));
		return "true";
	}
	/*
	public ResponseEntity<Map> upload (@RequestParam MultipartFile multipartFile) throws IOException {
		Map result = cloudinaryService.upload(multipartFile);
		secure_url = (String) result.get("secure_url");
		return new ResponseEntity(result,HttpStatus.OK);
	}*/
	
	public static String getSecure_url() {
		return secure_url;
	}

	public static void setSecure_url(String secure_url) {
		NewsAPI.secure_url = secure_url;
	}

	@DeleteMapping("/api/news/deleteFiles/{id}")
	public ResponseEntity<Map> delete (@PathVariable("id") String id) throws IOException {
		Map result = cloudinaryService.delete(id);
		return new ResponseEntity(result,HttpStatus.OK);
	}
	
	@PostMapping("/api/news") 
	public NewsDTO createNews(@RequestBody NewsDTO news) {
		news.setThumbnail(getSecure_url());
		return newService.save(news);
	}
	
	@PutMapping("/api/news") 
	public NewsDTO updateNews(@RequestBody NewsDTO news) {
		news.setThumbnail(getSecure_url());
		return newService.save(news);
	}
	
	@DeleteMapping("/api/news")
	public void deleteNews(@RequestBody long[] ids) {
		newService.delete(ids);
	}
}
