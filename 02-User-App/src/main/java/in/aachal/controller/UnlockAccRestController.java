package in.aachal.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.aachal.binding.UnlockAccForm;
import in.aachal.service.UserMgmtServiceImpl;

@RestController
public class UnlockAccRestController {

	@Autowired
	private UserMgmtServiceImpl service;
	
	@PostMapping("/unlock")
	public String unlockAccount(@RequestBody UnlockAccForm form) {
		return service.unlockAccount(form);
	}
}
