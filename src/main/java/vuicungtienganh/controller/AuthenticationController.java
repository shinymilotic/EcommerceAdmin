package vuicungtienganh.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import vuicungtienganh.entity.User;
import vuicungtienganh.service.UserService;

@Controller
public class AuthenticationController {
	
    @Autowired
    private UserService userService;

	@GetMapping("/dang-nhap")
	public String showLoginForm(Model model) {
		return "authentication/login";
	}
	
	@GetMapping("/dang-ky")
	public String showRegistrationForm() {
		
		return "authentication/register";
	}
	
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("crmUser") User theCrmUser, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		String userName = theCrmUser.getUserName();
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 return "authentication/register";
	        }

		// check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null){
        	theModel.addAttribute("crmUser", new User());
			theModel.addAttribute("registrationError", "User name already exists.");

        	return "authentication/register";
        }
        
        // create user account        						
        userService.save(theCrmUser);
        
        
        return "index";		
	}
	
	@GetMapping("/dang-xuat")
	public String afterLogout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
		    new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return "redirect:/";  
	}
}
