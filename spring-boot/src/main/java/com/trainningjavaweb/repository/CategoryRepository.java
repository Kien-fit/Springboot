package com.trainningjavaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trainningjavaweb.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
	CategoryEntity findOneByCode(String code);
}
