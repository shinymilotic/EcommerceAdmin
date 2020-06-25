package vuicungtienganh.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vuicungtienganh.entity.Role;
import vuicungtienganh.entity.User;

@Repository
public class UserDAOHibernateImpl implements UserDAO {
	// define field for entity manager
	private EntityManager entityManager;
	
	//set up constructor injection
	@Autowired
	public UserDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public User findByUserName(String userName) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<User> theQuery = currentSession.createQuery("from User where username=:uUsername", User.class);
		theQuery.setParameter("uUsername", userName);
		User theUser = null;
		
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		String queryString = "FROM User";
		Query<User> theQuery = currentSession.createQuery(queryString, User.class);
		List<User> users = theQuery.getResultList();
		return users;
	}
	@Override
	public void save(User theUser) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create the user ... finally LOL
		currentSession.saveOrUpdate(theUser);
	}

	@Override
	public void deleteByUserName(String userName) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query query = currentSession.createQuery("DELETE FROM User WHERE username = :uUsername");
		
		query.setParameter("uUsername", userName);
		query.executeUpdate();
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query query = currentSession.createQuery("DELETE FROM User WHERE id = :uId");
		
		query.setParameter("uId", id);
		query.executeUpdate();
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.update(user);
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		User user = currentSession.get(User.class, id);
		return user;
	}

	@Override
	public List<String> getUsernameByRoleName(String roleName) {
		Session currSession = entityManager.unwrap(Session.class);
		String queryString = "SELECT u.userName "
				+"FROM User u "
				+ "JOIN u.roles r "
				+ "WHERE r.name=:uRoleName";
		Query<String> theQuery = currSession.createQuery(queryString, String.class);
		theQuery.setParameter("uRoleName", roleName);
		List<String> usernames = theQuery.getResultList();
		return usernames;
	}
	
	
}
