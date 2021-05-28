package com.trainningjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.trainningjavaweb.converter.NewConverter;
import com.trainningjavaweb.dto.NewDTO;
import com.trainningjavaweb.entity.CategoryEntity;
import com.trainningjavaweb.entity.NewEntity;
import com.trainningjavaweb.repository.CategoryRepository;
import com.trainningjavaweb.repository.NewRepository;
import com.trainningjavaweb.service.INewService;

@Service
public class NewService implements INewService {

	@Autowired
	private NewRepository newRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private NewConverter newConverter;
	
/*	
	@Override
	public NewDTO save(NewDTO newDTO) {
		NewEntity newEntity = newConverter.toEntity(newDTO);
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
		newEntity.setCategory(categoryEntity);
		newEntity = newRepository.save(newEntity);
		return newConverter.toDTO(newEntity);
	}
	@Override
	public NewDTO update(NewDTO newDTO) {
		NewEntity oldEntity = newRepository.findOne(newDTO.getId());
		NewEntity newEntity = newConverter.toEntity(newDTO, oldEntity);
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
		newEntity.setCategory(categoryEntity);
		newEntity = newRepository.save(newEntity);
		return newConverter.toDTO(newEntity);
	}
*/	
	@Override
	public NewDTO save(NewDTO newDTO) {
		NewEntity newEntity = newConverter.toEntity(newDTO);
		if (newDTO.getId() != null) {
			NewEntity oldNewEntity = newRepository.findOne(newDTO.getId());
			newEntity = newConverter.toEntity(newDTO, oldNewEntity);
		} else {
			newEntity = newConverter.toEntity(newDTO);
		}
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
		newEntity.setCategory(categoryEntity);
		newEntity = newRepository.save(newEntity);
		return newConverter.toDTO(newEntity);
	}
	
	
	@Override
	public void delete(Long[] ids) {
		for(Long id: ids) {
			newRepository.delete(id);
		}
	}


	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		List<NewDTO> results = new ArrayList<>();
		List<NewEntity> entities = newRepository.findAll(pageable).getContent();
		for( NewEntity entity:entities) {
			NewDTO newDTO = newConverter.toDTO(entity);
			results.add(newDTO);
		}
		return results;
	}

	@Override
	public List<NewDTO> findAll() {
		// TODO Auto-generated method stub
		List<NewDTO> results = new ArrayList<>();
		List<NewEntity> entities = newRepository.findAll();
		for( NewEntity entity:entities) {
			NewDTO newDTO = newConverter.toDTO(entity);
			results.add(newDTO);
		}
		return results;
	}


	@Override
	public int totalItem() {
		// TODO Auto-generated method stub
		return (int)newRepository.count();
	}
	
}
