package in.aachal.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.aachal.service.UserMgmtServiceImpl;



@RestController
public class ForgetPwdRestController {

	@Autowired
		private UserMgmtServiceImpl service;
	

	@GetMapping("/fotgotPwd/{emailId}")
	public String forgotPwd(@PathVariable("emailId") String email) {
		return service.forgotPwd(email);
	}
}
