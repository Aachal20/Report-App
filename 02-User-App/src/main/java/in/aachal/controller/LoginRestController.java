package in.aachal.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.aachal.binding.LoginForm;
import in.aachal.service.UserMgmtServiceImpl;

@RestController
public class LoginRestController {

	@Autowired
	private UserMgmtServiceImpl service;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginForm form ) {
		return service.login(form);
	}
}
