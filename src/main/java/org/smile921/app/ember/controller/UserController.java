/**
 * 
 */
package org.smile921.app.ember.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.smile921.app.ember.controller.pagination.PaginationFormatting;
import org.smile921.app.ember.controller.pagination.PaginationMultiTypeValuesHelper;
import org.smile921.app.ember.entities.User;
import org.smile921.app.ember.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/api/persons")
public class UserController {

	@Autowired
	private UserRepository personsRepository;

	@Value(("${com.boylegu.paginatio.max-per-page}"))
	Integer maxPerPage;

	@RequestMapping(value = "/sex", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getSexAll() {

		/*
		 * @api {GET} /api/persons/sex Get all sexList
		 * 
		 * @apiName GetAllSexList
		 * 
		 * @apiGroup Info Manage
		 * 
		 * @apiVersion 1.0.0
		 * 
		 * @apiExample {httpie} Example usage:
		 *
		 * http /api/persons/sex
		 *
		 * @apiSuccess {String} label
		 * 
		 * @apiSuccess {String} value
		 */

		ArrayList<Map<String, String>> results = new ArrayList<>();

		for (Object value : personsRepository.findSex()) {

			Map<String, String> sex = new HashMap<>();

			sex.put("label", value.toString());

			sex.put("value", value.toString());

			results.add(sex);
		}

		ResponseEntity<ArrayList<Map<String, String>>> responseEntity = new ResponseEntity<>(results, HttpStatus.OK);

		return responseEntity;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, PaginationMultiTypeValuesHelper> getPersonsAll(
			@RequestParam(value = "page", required = false) Integer pages, @RequestParam("sex") String sex,
			@RequestParam("email") String email) {

		/*
		 * @api {GET} /api/persons Get all or a part of person info
		 * 
		 * @apiName GetAllInfoList
		 * 
		 * @apiGroup Info Manage
		 * 
		 * @apiVersion 1.0.0
		 *
		 * @apiExample {httpie} Example usage: (support combinatorial search)
		 *
		 * All person： http /api/persons
		 *
		 * You can according to 'sex | email' or 'sex & email' http
		 * /api/persons?sex=xxx&email=xx http /api/persons?sex=xxx http
		 * /api/persons?email=xx
		 *
		 * @apiParam {String} sex
		 * 
		 * @apiParam {String} email
		 *
		 * @apiSuccess {String} create_datetime
		 * 
		 * @apiSuccess {String} email
		 * 
		 * @apiSuccess {String} id
		 * 
		 * @apiSuccess {String} phone
		 * 
		 * @apiSuccess {String} sex
		 * 
		 * @apiSuccess {String} username
		 * 
		 * @apiSuccess {String} zone
		 */

		if (pages == null) {

			pages = 1;

		}

		Sort sort = new Sort(Direction.ASC, "id");

		Pageable pageable = new PageRequest(pages - 1, maxPerPage, sort);

		PaginationFormatting paginInstance = new PaginationFormatting();

		return paginInstance.filterQuery(sex, email, pageable);
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserDetail(@PathVariable Long id) {

		/*
		 * @api {GET} /api/persons/detail/:id details info
		 * 
		 * @apiName GetPersonDetails
		 * 
		 * @apiGroup Info Manage
		 * 
		 * @apiVersion 1.0.0
		 *
		 * @apiExample {httpie} Example usage:
		 *
		 * http GET http://127.0.0.1:8000/api/persons/detail/1
		 *
		 * @apiSuccess {String} email
		 * 
		 * @apiSuccess {String} id
		 * 
		 * @apiSuccess {String} phone
		 * 
		 * @apiSuccess {String} sex
		 * 
		 * @apiSuccess {String} username
		 * 
		 * @apiSuccess {String} zone
		 */

		User user = personsRepository.findById(id);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public User updateUser(@PathVariable Long id, @RequestBody User data) {

		/*
		 * @api {PUT} /api/persons/detail/:id update person info
		 * 
		 * @apiName PutPersonDetails
		 * 
		 * @apiGroup Info Manage
		 * 
		 * @apiVersion 1.0.0
		 *
		 * @apiParam {String} phone
		 * 
		 * @apiParam {String} zone
		 *
		 * @apiSuccess {String} create_datetime
		 * 
		 * @apiSuccess {String} email
		 * 
		 * @apiSuccess {String} id
		 * 
		 * @apiSuccess {String} phone
		 * 
		 * @apiSuccess {String} sex
		 * 
		 * @apiSuccess {String} username
		 * 
		 * @apiSuccess {String} zone
		 * 
		 */
		User user = personsRepository.findById(id);

		user.setPhone(data.getPhone());

		user.setZone(data.getZone());

		return personsRepository.save(user);
	}

}
