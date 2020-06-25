package vuicungtienganh.service;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import vuicungtienganh.entity.Role;
import vuicungtienganh.entity.User;

@Validated
@Service
public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);
	public List<User> findAll();
	public void save(User crmUser);
	public void deleteByIds(List<Integer> ids);
	public void updateUser(int id,@NotNull String firstName,@NotNull String lastName, String userName, String email, List<Role> roles);
	public User findById(int id);
	public List<String> getUsernameByRoleName(String roleName);
}