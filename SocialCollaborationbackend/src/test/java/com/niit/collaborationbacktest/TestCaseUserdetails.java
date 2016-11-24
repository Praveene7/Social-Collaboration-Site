package com.niit.collaborationbacktest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.UserdetailsDAO;
import com.niit.model.Userdetails;

import junit.framework.Assert;

public class TestCaseUserdetails {

	@Autowired
	UserdetailsDAO userdetailsDAO;
	
	@Autowired
	Userdetails userdetails;
	
	AnnotationConfigApplicationContext context;
	
	@Before
	public void init()
	{
	context = new AnnotationConfigApplicationContext();
	context.scan("com.niit");
	context.refresh();
	
	userdetailsDAO = (UserdetailsDAO) context.getBean("userdetailsDAO");
	userdetails = (Userdetails) context.getBean("userdetails");
	}
	
	
/*@Test
	public void deleteuserTestCase()
	{
		userdetails.setUserid("user01");
	boolean flag = userdetailsDAO.delete(id);
	assertEquals("deleteuserTestCase",flag, true);
	}*/
@Test
	public void userAddTestCase()
	{
		userdetails.setUserid("user02");
		userdetails.setUsername("Ram");
		userdetails.setEmail("ram@gmail.com");
		userdetails.setMobile("7401581209");
		userdetails.setAddress("Trichy");
		userdetails.setPassword("ramkumar");
		userdetails.setRole("ROLE_STUDENT");
		
		assertEquals("userAddTestCase", userdetailsDAO.save(userdetails),true);

		
	}
	
	@Test
	public void updateUserTestCase()
	{
		userdetails.setUserid("user03");
	//userdetails.setMobile("7845961230");
	userdetails.setPassword("123456");
		userdetails.setAddress("mumbai");
		userdetails.setEmail("ravi92@yahoo.com");
	
	assertEquals("updateUserTestCase", userdetailsDAO.update(userdetails), false);
	}
	@Test
	public void getAllUsers()
	{
		Assert.assertEquals("getallusers",1, userdetailsDAO.list().size());
	}
@Test	
	public void categoryGetTestCase()
	{
		userdetails = userdetailsDAO.get("user02");
		System.out.println(userdetails.getAddress());
		System.out.println(userdetails.getUsername());
		
		assertEquals(userdetails.getUsername(), "Ram");
	}

}
