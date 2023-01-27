package in.aachal.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.aachal.binding.UserRegForm;
import in.aachal.service.UserMgmtServiceImpl;

@RestController
public class RegistrationRestController {

	@Autowired
	private UserMgmtServiceImpl service;
	
	@GetMapping("/email/{emailId}")
	public String emailCheck(@PathVariable("emaildId") String email) {
		return service.emilCheck(email);
	}
	
	@GetMapping("/contries")
	public Map<Integer, String> getCountries() {
		return service.loadCountries();
	}
	
	@GetMapping("/states/{countryId}")
	public Map<Integer, String> getStates(@PathVariable("countryId") Integer countryId) {
		return service.loadStates(countryId);
	}
	
	@GetMapping("/cities/{stateId}")
	public Map<Integer, String> getcities(@PathVariable("stateId") Integer stateId) {
		return service.loadCities(stateId);
	}
	
	@PostMapping("/user")
	public String  userReg(@RequestBody UserRegForm userForm) {
		return service.registerUser(userForm);
	}
}
