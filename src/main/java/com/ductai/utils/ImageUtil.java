package com.ductai.utils;

import java.io.File;
import java.nio.file.Files;

import org.springframework.stereotype.Component;

@Component
public class ImageUtil {
	public void addImageToFolder(String realPath,String fileName) {
		File file = new File(realPath+"/"+fileName);
		try {
			if(!Files.exists(file.toPath())) {
				file.createNewFile();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
