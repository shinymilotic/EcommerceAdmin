package vuicungtienganh.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import vuicungtienganh.entity.Role;
@Repository
public interface RoleDao {
	public Role findRoleByName(String theRoleName);
	public List<Role> findAll();
	public Role findRoleById(int id);
}
