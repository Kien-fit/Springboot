package com.trainningjavaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trainningjavaweb.entity.NewEntity;

public interface NewRepository extends JpaRepository<NewEntity, Long> {
	// NewEntity findOne(NewEntity entity);
}
