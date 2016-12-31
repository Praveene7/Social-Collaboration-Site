package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.FriendDAO;

import com.niit.model.Friend;
import com.niit.model.Userdetails;

@RestController
public class FriendController {
	
	@Autowired
	FriendDAO friendDAO;
	@Autowired
	Friend friend;
	
	
	private static final Logger log=LoggerFactory.getLogger(Friend.class);
	@RequestMapping(value="/myFriends", method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> listAllFriend(HttpSession session){
		log.debug("-->Calling method listAllFriends");
		
		Userdetails loggedInUser = (Userdetails) session.getAttribute("loggedInUser");
		System.out.println("Calling method listAllFriends"+loggedInUser.getUsername());
		List<Friend> myfriends = friendDAO.getmyfriends(loggedInUser.getUsername());
		return new ResponseEntity<List<Friend>> (myfriends,HttpStatus.OK);
		
	}
		
	@RequestMapping(value="/addFriend/{friendid}", method=RequestMethod.GET)
	public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendid")String friendid,HttpSession session)
	{
		log.debug("-->Calling method send friend request");
		String loggedInUserid = (String) session.getAttribute("loggedInUserId");
		friend.setUserid(loggedInUserid);
		friend.setFriendid(friendid);
		friend.setStatus("N");
		friend.setIsOnline('N');
		friendDAO.save(friend);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@RequestMapping(value="/unfriend/{friendid}", method=RequestMethod.GET)
	public ResponseEntity<Friend> unfriend(@PathVariable("friendid")String friendid,HttpSession session)
	{
		log.debug("-->Calling method send friend request");
		Userdetails loggedInUser = (Userdetails) session.getAttribute("loggedInUser");	
		friend.setUserid(loggedInUser.getUserid());
		friend.setFriendid(friendid);
		friend.setStatus("U");
		friendDAO.saveOrUpdate(friend);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@RequestMapping(value="/acceptFriend/{id}", method=RequestMethod.GET)
	public ResponseEntity<Friend> acceptFriend(@PathVariable("id")String id,HttpSession session)
	{
		/*log.debug("-->Calling method accept Friend");
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		friend.setUserid(loggedInUserId);
		friend.setFriendid(friendid);
		friend.setStatus("A");
		friendDAO.saveOrUpdate(friend);
		updateRequest(friendid,"A",session);*/
		friendDAO.setStatusAccept(id);
		return new ResponseEntity<Friend> (friend,HttpStatus.OK);
	}
	
	@RequestMapping(value="/rejectFriend/{id}", method=RequestMethod.GET)
	public ResponseEntity<Friend> rejectFriend(@PathVariable("id")String id,HttpSession session)
	{
		/*log.debug("-->Calling method send friend request");
		Userdetails loggedInUser = (Userdetails) session.getAttribute("loggedInUser");	
		friend.setUserid(loggedInUser.getUsername());
		//friend.setFriendid(friendid);
		friend.setStatus("R");
		friendDAO.saveOrUpdate(friend);*/
		friendDAO.setStatusReject(id);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getmyfriendRequest", method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> getFriendRequest(HttpSession session){
		log.debug("-->Calling method listAllFriends");
		
		Userdetails loggedInUser = (Userdetails) session.getAttribute("loggedInUser");
		System.out.println("Calling method listAllFriends"+loggedInUser.getUsername());
		List<Friend> myfriends = friendDAO.getNewFriendrequest(loggedInUser.getUsername());
		return new ResponseEntity<List<Friend>> (myfriends,HttpStatus.OK);
		
	}
	
	
	/*private void updateRequest(String friendid,String status,HttpSession session)
	{
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		friend.setUserid(loggedInUserId);
		friend.setFriendid(friendid);
		friend
		friendDAO.update(friend);
	}*/
	
	@RequestMapping(value="/myfriends/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> getmyFriendsTemp(@PathVariable("id")String id){
		List<Friend> myfriends = friendDAO.getmyfriends(id);
		return new ResponseEntity<List<Friend>> (myfriends,HttpStatus.OK);
	}
}