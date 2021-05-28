package com.trainningjavaweb.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trainningjavaweb.api.output.NewOutput;
import com.trainningjavaweb.dto.NewDTO;
import com.trainningjavaweb.service.impl.NewService;

/*
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NewAPI {
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@ResponseBody
	public NewDTO createNew(@RequestBody NewDTO  model) {
		return model;
	}	
}
*/

@CrossOrigin
@RestController
public class NewAPI {

	@Autowired
	private NewService newService;

	@PostMapping(value = "/new")
	public NewDTO createNew(@RequestBody NewDTO model) {
		return newService.save(model);
	}

	@PutMapping(value = "/new/{id}")
	public NewDTO updateNew(@RequestBody NewDTO model, @PathVariable("id") Long id) {
		model.setId(id);
		return newService.save(model);
	}

	@DeleteMapping(value = "/new")
	public void deleteNew(@RequestBody Long[] ids) {
		newService.delete(ids);
	}

	@GetMapping(value = "/new")
	public NewOutput showNew(@RequestParam(value = "page", required = false) Integer page, 
			@RequestParam(value = "limit", required = false) Integer limit) {
		NewOutput result = new NewOutput();
		if(page != null && limit != null) {
		result.setPage(page);
		Pageable pageable = new PageRequest(page - 1, limit);
		result.setListResult(newService.findAll(pageable));
		result.setTotalPage((int) Math.ceil((double) newService.totalItem() / limit));
		}else{
			result.setListResult(newService.findAll());
		}
		return result;
	}
/*
	@GetMapping(value = "/new")
	public NewOutput showNew(@RequestParam(value = "page", defaultValue = "NONE") String pageStr, 
			@RequestParam(value = "limit", defaultValue = "NONE") String limitStr) {
		NewOutput result = new NewOutput();
		if(!pageStr.equals("NONE") && !limitStr.equals("NONE")) {
			int page = Integer.parseInt(pageStr);
			int limit = Integer.parseInt(limitStr);
			result.setPage(page);
			Pageable pageable = new PageRequest(page - 1, limit);
			result.setListResult(newService.findAll(pageable));
			result.setTotalPage((int) Math.ceil((double) newService.totalItem() / limit));
		}else{
			result.setListResult(newService.findAll());
		}
		return result;
	}
*/
}
