package com.niit.model;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.*;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="Blog")
public class Blog extends BaseDomain{
	@Id
	private int id;
	private String title;
	private String description;
	private String userid;
	private char status;
	private String reason;
	private Date posted_dt;
	
	public Date getPosted_dt() {
		return posted_dt;
	}
	public void setPosted_dt(Date posted_dt) {
		if(posted_dt==null)
		{
			posted_dt= new Date(System.currentTimeMillis());
		}
		this.posted_dt = posted_dt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
		

}
