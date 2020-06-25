package vuicungtienganh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vuicungtienganh.dao.RoleDao;
import vuicungtienganh.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	
	@Override
	@Transactional
	public List<Role> findAllRole() {
		return roleDao.findAll();
	}

	@Override
	public Role findRoleById(int id) {
		// TODO Auto-generated method stub
		
		return roleDao.findRoleById(id);
	}

}
