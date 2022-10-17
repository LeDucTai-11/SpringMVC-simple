package com.ductai.converter;

import org.springframework.stereotype.Component;

import com.ductai.dto.NewsDTO;
import com.ductai.entity.NewsEntity;

@Component
public class NewsConverter {
	
	public NewsDTO toDTO(NewsEntity news) {
		NewsDTO result = new NewsDTO();
		result.setId(news.getId());
		result.setTitle(news.getTitle());
		result.setShortDescription(news.getShortDescription());
		result.setContent(news.getContent());
		result.setThumbnail(news.getThumbnail());
		result.setCategoryCode(news.getCategory().getCode());
		return result;
	}
	
	public NewsEntity toEntity(NewsDTO news) {
		NewsEntity result = new NewsEntity();
		result.setTitle(news.getTitle());
		result.setShortDescription(news.getShortDescription());
		result.setContent(news.getContent());
		result.setThumbnail(news.getThumbnail());
		return result;
	}
	
	public NewsEntity toEntity(NewsEntity result,NewsDTO news) {
		result.setTitle(news.getTitle());
		result.setShortDescription(news.getShortDescription());
		result.setContent(news.getContent());
		result.setThumbnail(news.getThumbnail());
		return result;
	}
}
