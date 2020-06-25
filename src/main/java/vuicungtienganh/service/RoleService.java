package vuicungtienganh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vuicungtienganh.entity.Role;
@Service
public interface RoleService {
	public List<Role> findAllRole();
	public Role findRoleById(int id);
}
