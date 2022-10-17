package com.ductai.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ductai.entity.CategoryEntity;
import com.ductai.repository.CategoryRepository;
import com.ductai.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService{
	
	@Autowired 
	private CategoryRepository cateRepository;

	@Override
	public Map<String,String> findAll() {
		Map<String,String> result = new HashMap<String, String>();
		for(CategoryEntity entity : cateRepository.findAll()) {
			result.put(entity.getCode(), entity.getName());
		}
		return result;
	}

}
