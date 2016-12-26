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

import com.niit.dao.BlogDAO;

import com.niit.model.Blog;

@RestController
public class BlogController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	BlogDAO blogDAO;
	@Autowired
	Blog blog;

	@RequestMapping(value = "/blogs", method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> listAllBlogs() {
		log.debug("-->Calling method listAllBlogs");
		System.out.println("-->Calling method listAllBlogs");
		List<Blog> blog = blogDAO.list();
		
		if (blog.isEmpty()) {
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Blog>>(blog, HttpStatus.OK);

	}

	@RequestMapping(value = "/blog/{id}", method = RequestMethod.GET)
	public ResponseEntity<Blog> getuser(@PathVariable("id") int id) {
		log.debug("-->calling get method");
		Blog blog = blogDAO.get(id);
		if (blog == null) {
			blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("Blog not found");
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

	@RequestMapping(value = "/createblogs/", method = RequestMethod.POST)
	public ResponseEntity<Blog> createblogs(@RequestBody Blog blog, HttpSession session) {
		log.debug("-->Calling method createUsers");
		String loggedInUserid = (String) session.getAttribute("loggedInUserId");
		blog.setUserid(loggedInUserid);
		blog.setStatus('N');
		blogDAO.save(blog);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

	@RequestMapping(value = "/blog/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Blog> deleteblog(@PathVariable("id") int id) {
		log.debug("-->calling delete method");
		Blog blog = blogDAO.get(id);
		if (blog == null) {
			log.debug("-->blog does not exist");
			blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("Blog not found");
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
		blogDAO.delete(id);
		log.debug("-->User deleted successfully");
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

}
