package com.ductai.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ductai.converter.NewsConverter;
import com.ductai.dto.NewsDTO;
import com.ductai.entity.NewsEntity;
import com.ductai.repository.CategoryRepository;
import com.ductai.repository.NewsRepository;
import com.ductai.service.INewService;

@Service
public class NewService implements INewService {
	
//	@Autowired
//	private INewDAO newDao;
	
	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private NewsConverter newsConverter;
	
	@Autowired
	private CategoryRepository cateRepository;
	
	
	
	@Override
	public List<NewsDTO> findAll(Pageable pageable) {
		List<NewsDTO> data = new ArrayList<NewsDTO>();
		for(NewsEntity news : newsRepository.findAll(pageable).getContent()) {
			data.add(newsConverter.toDTO(news));
		}
		return data;
	}


	@Override
	public int getTotalItem() {
		return (int) newsRepository.count();
	}


	@Override
	public NewsDTO findByID(Long id) {
		NewsEntity entity = newsRepository.findOne(id);
		return newsConverter.toDTO(entity);
	}

	@Override
	@Transactional
	public NewsDTO save(NewsDTO news) {
		NewsEntity updateNews = new NewsEntity();
		if(news.getId() != null) {
			updateNews = newsConverter.toEntity(newsRepository.findOne(news.getId()), news);
		}else {
			updateNews = newsConverter.toEntity(news);
		}
		updateNews.setCategory(cateRepository.findOneByCode(news.getCategoryCode()));
		return newsConverter.toDTO(newsRepository.save(updateNews));
	}


	@Override
	@Transactional
	public void delete(long[] ids) {
		for(long id : ids) {
			newsRepository.delete(id);
		}
	}


	@Override
	public List<NewsDTO> getAllItemsToWebPage() {
		List<NewsDTO> result = new ArrayList<NewsDTO>();
		for(int i = findAll().size() -2 ; i >=0 ; i--) {
			result.add(findAll().get(i));
		}
		return result;
	}


	@Override
	public List<NewsDTO> findAll() {
		List<NewsDTO> result = new ArrayList<NewsDTO>();
		for(NewsEntity entity : newsRepository.findAll()) {
			result.add(newsConverter.toDTO(entity));
		}
		return result;
	}


	@Override
	public NewsDTO getTheHOTNews() {
		return findAll().get(findAll().size() - 1);
	}


	@Override
	public Date getModifiedDate(Long id) {
		return newsRepository.findOne(id).getModifiedDate();
	}


	

}
