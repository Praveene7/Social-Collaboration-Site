package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Blog;


@EnableTransactionManagement
@Repository("blogDAO")
public class BlogDAOImpl implements BlogDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public BlogDAOImpl(SessionFactory sessionFactory)
	{
	this.sessionFactory = sessionFactory;
	}
	
	private Integer getMaxId()
	{
		String hql= "select max(id) from Blog";
		Query query =sessionFactory.getCurrentSession().createQuery(hql);
		Integer maxId = (Integer) query.uniqueResult();
		return maxId;	
	}
	
@Transactional
	public boolean save(Blog blog)
	{
	try {
		// Session session = sessionFactory.getCurrentSession();
		blog.setId(getMaxId()+1);
		sessionFactory.getCurrentSession().save(blog);
		return true;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return false;
	}
	}


@Transactional
public boolean update(Blog blog)
{
try {

	sessionFactory.getCurrentSession().update(blog);
	return true;
}
catch(Exception e)
{
	e.printStackTrace();
	return false;
}
}



@Transactional
	public boolean delete(int id)
	{
	try {
		
		
		Blog BlogToDelete = new Blog();
			BlogToDelete.setId(id);
			sessionFactory.getCurrentSession().delete(BlogToDelete);
		
		return true;
	}
	catch(Exception e)
	{
		
		e.printStackTrace();
		return false;
	}
	}

@Transactional
public Blog get(int id)
{
	String hql = "from Blog where id= "+" '" +id+ "'";
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	@SuppressWarnings("unchecked")
	List<Blog> list = query.list();
	if(list == null || list.isEmpty())
	{
		return null;
	}
	else
	{
		return list.get(0);
	}
}



@SuppressWarnings("unchecked")
@Transactional
public List<Blog> list()
{
	System.out.println("blogs list");
	String hql = "from Blog";
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	return query.list();
}

}
