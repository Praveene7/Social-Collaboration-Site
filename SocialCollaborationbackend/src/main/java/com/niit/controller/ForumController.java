package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.ForumDAO;

import com.niit.model.Forum;

@RestController
public class ForumController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	ForumDAO forumDAO;
	@Autowired
	Forum forum;

	@RequestMapping(value = "/forums", method = RequestMethod.GET)
	public ResponseEntity<List<Forum>> listAllForums() {
		log.debug("-->Calling method listAllForums");
		System.out.println("-->Calling method listAllForums");
		List<Forum> forum = forumDAO.list();
		
		if (forum.isEmpty()) {
			return new ResponseEntity<List<Forum>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Forum>>(forum, HttpStatus.OK);

	}

	@RequestMapping(value = "/forum/{id}", method = RequestMethod.GET)
	public ResponseEntity<Forum> getuser(@PathVariable("id") int id) {
		log.debug("-->calling get method");
		Forum forum = forumDAO.get(id);
		if (forum == null) {
			forum = new Forum();
			forum.setErrorCode("404");
			forum.setErrorMessage("Forum not found");
			return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}

	@RequestMapping(value = "/createforums/", method = RequestMethod.POST)
	public ResponseEntity<Forum> createblogs(@RequestBody Forum blog, HttpSession session) {
		log.debug("-->Calling method createUsers");
		String loggedInUserid = (String) session.getAttribute("loggedInUserId");
		blog.setUserid(loggedInUserid);
		blog.setStatus('N');
		forumDAO.save(blog);
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}

	@RequestMapping(value = "/forum/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Forum> deleteblog(@PathVariable("id") int id) {
		log.debug("-->calling delete method");
		Forum forum = forumDAO.get(id);
		if (forum == null) {
			log.debug("-->forum does not exist");
			forum = new Forum();
			forum.setErrorCode("404");
			forum.setErrorMessage("Forum not found");
			return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
		}
		forumDAO.delete(id);
		log.debug("-->User deleted successfully");
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}

}
