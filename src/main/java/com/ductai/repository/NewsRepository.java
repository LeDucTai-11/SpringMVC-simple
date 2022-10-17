package com.ductai.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ductai.entity.NewsEntity;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
	
}
