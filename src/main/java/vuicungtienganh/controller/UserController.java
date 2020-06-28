package vuicungtienganh.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vuicungtienganh.entity.Role;
import vuicungtienganh.entity.User;
import vuicungtienganh.service.RoleService;
import vuicungtienganh.service.UserService;

@Controller
@RequestMapping("/user")
@Validated
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@GetMapping("")
	public String showUsers(Model model) {
		List<User> listUser = userService.findAll();
		model.addAttribute("users", listUser);
		return "user/users";
	}
	
	@GetMapping("/{username}")
	public String showUserByUsername(@PathVariable("username") String username,
			Model model) {
		User user = userService.findByUserName(username);
		
		if(user == null) {
			return "redirect:/user";
		}
		
		model.addAttribute("user", user);
		return "user/profile";
	}
	@PostMapping("/delete")
	public String deleteUser(@RequestParam("userCheckboxes") List<Integer> ids) {
		userService.deleteByIds(ids);
		return "redirect:/users";
	}
	@GetMapping("/insert")
	public String showInsertUserForm(Model model) {
		User newUser = new User();
		model.addAttribute("newUser", newUser);
		List<Role> roles = roleService.findAllRole();
		model.addAttribute("roles", roles);
		
		return "user/insert-user";
	}
	@GetMapping("/edit/{id}")
	public String showEditUserForm(@PathVariable("id") int id,
			Model model) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		List<Role> roles = roleService.findAllRole();
		List<Role> rolesUser = user.getRoles();
		model.addAttribute("roles", roles);
		List<Map<Role,Boolean>> listRolesUserMap = new ArrayList<Map<Role, Boolean>>();
		int rolesUserSize = roles.size();
		
		
		for(int i = 0; i < rolesUserSize; i++) {
			boolean value = false;
			Role key = roles.get(i);
			Map<Role, Boolean> mapRoleUser = new HashMap<Role,Boolean>();
			
			if(rolesUser.contains(key)) {
				value = true;
			}
			
			mapRoleUser.put(key, value);
			listRolesUserMap.add(mapRoleUser);
		}
		
		model.addAttribute("rolesUser", listRolesUserMap);
		return "user/edit-user";
	}
	@PostMapping("/processUpdateUserForm")
	public String processUpdateUserForm(
			@ModelAttribute("user") User user,
			@RequestParam List<Integer> roleIds,
			BindingResult theBindingResult, 
			Model theModel) {
		
		ArrayList<Role> selectedRoles = new ArrayList<Role>();
		for(int roleId : roleIds) {
			Role selectedRole = roleService.findRoleById(roleId);
			selectedRoles.add(selectedRole);
		}
		
		user.setRoles(selectedRoles);
		userService.updateUser(user.getId(),user.getFirstName(),user.getLastName(),
				user.getUserName(),user.getEmail(),user.getRoles());
		
		return "redirect:/user";
	}
	@PostMapping("/processInsertUserForm")
	public String processInsertUserForm(
			@Valid @ModelAttribute("newUser") User user,
			@RequestParam List<Integer> roleIds, 
			BindingResult theBindingResult, 
			Model theModel) {
		ArrayList<Role> selectedRoles = new ArrayList<Role>();
		for(int roleId : roleIds) {
			Role selectedRole = roleService.findRoleById(roleId);
			selectedRoles.add(selectedRole);
		}
		user.setRoles(selectedRoles);
		userService.save(user);
		
		return "redirect:/user";
	}

	
	
}
