package com.trainningjavaweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.trainningjavaweb.dto.NewDTO;

public interface INewService {

	NewDTO save(NewDTO newDTO);
	//NewDTO update(NewDTO newDTO);
	void delete(Long[] ids);
	List<NewDTO> findAll();
	List<NewDTO> findAll(Pageable pageable);
	int totalItem();
}
