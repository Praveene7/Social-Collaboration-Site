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

import com.niit.dao.FriendDAO;
import com.niit.dao.UserdetailsDAO;
import com.niit.model.Userdetails;
@RestController
public class UserController {
	
	private static final Logger log=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserdetailsDAO userdetailsDAO;
	@Autowired
	Userdetails userdetails;
	@Autowired
	FriendDAO friendDAO;
	
	//for list
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public ResponseEntity<List<Userdetails>> listAllUsers(){
		log.debug("-->Calling method listAllUsers");
		System.out.println("Calling method listAllUsers");
		List<Userdetails> user=userdetailsDAO.list();
		System.out.println("Out of userlist");
		if(user.isEmpty()){
			return new ResponseEntity<List<Userdetails>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Userdetails>>(user,HttpStatus.OK);
		
	}
	//to create users
	@RequestMapping(value="/createusers/", method=RequestMethod.POST)
	public ResponseEntity<Userdetails> createusers(@RequestBody Userdetails userdetails){
		log.debug("-->Calling method createUsers");
		if(userdetailsDAO.get(userdetails.getUserid())==null){
			userdetailsDAO.save(userdetails);
			return new ResponseEntity<Userdetails>(userdetails,HttpStatus.OK);
		}
		log.debug("-->User already exist"+userdetails.getUserid());
		return new ResponseEntity<Userdetails>(userdetails,HttpStatus.OK);
		}

	//to get user by user id
	@RequestMapping(value="/user/{userid}",method=RequestMethod.GET)
	public ResponseEntity<Userdetails> getuser(@PathVariable("userid")String id)
	{
	log.debug("-->calling get method");
	Userdetails userdetails=userdetailsDAO.get(id);
	if(userdetails==null)
	{
		log.debug("-->User does not exist");
		userdetails = new Userdetails();
		userdetails.setErrorCode("404");
		userdetails.setErrorMessage("User not found");
		return new ResponseEntity<Userdetails>(userdetails,HttpStatus.NOT_FOUND);
	}
	log.debug("-->User exist");
	return new ResponseEntity<Userdetails>(userdetails,HttpStatus.OK);
	}
	
	//update user by user id
	@RequestMapping(value="/user/{username}",method=RequestMethod.PUT)
	public ResponseEntity<Userdetails> updateuser(@PathVariable("username")String username)
	{
	log.debug("-->calling update method");
	if(userdetailsDAO.get(username)==null)
	{
		log.debug("-->User does not exist");
		userdetails = new Userdetails();
		userdetails.setErrorCode("404");
		userdetails.setErrorMessage("User not found");
		return new ResponseEntity<Userdetails>(userdetails,HttpStatus.NOT_FOUND);
	}
	userdetailsDAO.update(userdetails);
	log.debug("-->User updated successfully");
	return new ResponseEntity<Userdetails>(userdetails,HttpStatus.OK);
	
}
	//delete user
	@RequestMapping(value="/user/{userid}",method=RequestMethod.DELETE)
	public ResponseEntity<Userdetails> deleteuser(@PathVariable("userid")String id)
	{
		log.debug("-->calling delete method");
		Userdetails userdetails=userdetailsDAO.get(id);
		if(userdetails==null)
		{
			log.debug("-->User does not exist");
			userdetails = new Userdetails();
			userdetails.setErrorCode("404");
			userdetails.setErrorMessage("Blog not found");
			return new ResponseEntity<Userdetails>(userdetails,HttpStatus.NOT_FOUND);
		}
		userdetailsDAO.delete(id);
		log.debug("-->User deleted successfully");
		return new ResponseEntity<Userdetails>(userdetails,HttpStatus.OK);
		}
	
	
	//authentication
	@RequestMapping(value="/user/authenticate",method=RequestMethod.POST)
	public ResponseEntity<Userdetails> authenticateuser(@RequestBody Userdetails userdetails,HttpSession session)
	{
		log.debug("-->calling authenticate method");
	System.out.println("calling authenticate method");
		userdetails=userdetailsDAO.authenticate(userdetails.getUsername(), userdetails.getPassword());
		if(userdetails==null)
		{
			log.debug("-->User does not exist");
			System.out.println("User does not exist");
			userdetails = new Userdetails();
			userdetails.setErrorCode("404");
			userdetails.setErrorMessage("Invalid Credentials, Please enter vaild credentials");
	}
		else
		{
			userdetails.setErrorCode("200");
			log.debug("-->User exist with above credentials");
			System.out.println("User exist with above credentials");
			session.setAttribute("loggedInUser",userdetails);
			session.setAttribute("loggedInUserId", userdetails.getUsername());
			friendDAO.setOnLine(userdetails.getUsername());
			userdetailsDAO.setOnLine(userdetails.getUsername());
		}
		return new ResponseEntity<Userdetails>(userdetails,HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/logout",method=RequestMethod.GET)
	public ResponseEntity<Userdetails> logout(HttpSession session)
	{
		System.out.println("logout method");
		Userdetails loggedInUser = (Userdetails) session.getAttribute("loggedInUser");
		userdetails= userdetailsDAO.authenticate(loggedInUser.getUsername(), loggedInUser.getPassword());
		friendDAO.setOffLine(userdetails.getUsername());
		userdetailsDAO.setOffLine(userdetails.getUsername());
		session.invalidate();
		return new ResponseEntity<Userdetails>(userdetails,HttpStatus.OK);
	}
}