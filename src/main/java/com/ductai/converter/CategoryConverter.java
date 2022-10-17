package com.ductai.converter;

import com.ductai.dto.CategoryDTO;
import com.ductai.entity.CategoryEntity;

public class CategoryConverter {

	public CategoryDTO toDTO(CategoryEntity entity) {
		CategoryDTO cate = new CategoryDTO();
		cate.setId(entity.getId());
		cate.setName(entity.getName());
		cate.setCode(entity.getCode());
		return cate;
	}
	
	public CategoryEntity toEntity(CategoryDTO entity) {
		CategoryEntity cate = new CategoryEntity();
		cate.setName(entity.getName());
		cate.setCode(entity.getCode());
		return cate;
	}
}
