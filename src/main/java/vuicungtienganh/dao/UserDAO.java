package vuicungtienganh.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import vuicungtienganh.entity.User;
@Repository
public interface UserDAO {
	public List<User> findAll();
    public User findByUserName(String userName);
    public User findById(int id);
    public void deleteByUserName(String userName);
    public void deleteById(int id);
    public void save(User user);
    public void updateUser(User user);
	public List<String> getUsernameByRoleName(String roleName);
}
