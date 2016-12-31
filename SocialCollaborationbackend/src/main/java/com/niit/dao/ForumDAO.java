package com.niit.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.model.Forum;


@Repository
public interface ForumDAO {

	public boolean save(Forum forum);
	public boolean update(Forum forum);
	public boolean delete(int id);
	public List<Forum> list();
	public Forum get(int id);
}
