package com.ductai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ductai.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

	CategoryEntity findOneByCode(String code);
}
