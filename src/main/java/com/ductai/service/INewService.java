package com.ductai.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ductai.dto.NewsDTO;

@Service
public interface INewService {
	List<NewsDTO> findAll(Pageable pageable);

	int getTotalItem();
	NewsDTO findByID(Long id);
	NewsDTO save(NewsDTO news);
	void delete(long[] ids);
	NewsDTO getTheHOTNews();
	List<NewsDTO> findAll();
	List<NewsDTO> getAllItemsToWebPage();
	Date getModifiedDate(Long id);
	
}
