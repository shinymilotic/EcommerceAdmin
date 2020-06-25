package vuicungtienganh.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import vuicungtienganh.dao.RoleDao;
import vuicungtienganh.dao.UserDAO;
import vuicungtienganh.entity.Role;
import vuicungtienganh.entity.User;

@Service
@Validated
public class UserServiceImpl implements UserService {

	// need to inject user dao
	@Autowired
	private UserDAO userDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public List<User> findAll() {
		return userDao.findAll();
	}
	@Override
	@Transactional
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
	}
	
	
	@Override
	@Transactional
	public void save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		
		 // save user in the database
		userDao.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	@Override
	@Transactional
	public void deleteByIds(List<Integer> ids) {
		// TODO Auto-generated method stub
		for(int id : ids) {
			userDao.deleteById(id);
		}
	}
	@Override
	@Transactional
	public void updateUser(int id, String firstName, String lastName, String username,
			String email, List<Role> roles) {
		User user = userDao.findById(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserName(username);
		user.setEmail(email);
		user.setRoles(roles);	
	}

	@Override
	@Transactional
	public User findById(int id) {
		// TODO Auto-generated method stub
		return userDao.findById(id);
	}
	@Override
	public List<String> getUsernameByRoleName(String roleName) {
		// TODO Auto-generated method stub
		return userDao.getUsernameByRoleName(roleName);
	}
	
}

