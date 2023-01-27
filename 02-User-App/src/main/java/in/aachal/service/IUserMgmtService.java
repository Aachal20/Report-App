package in.aachal.service;

import java.util.Map;



import in.aachal.binding.LoginForm;
import in.aachal.binding.UnlockAccForm;
import in.aachal.binding.UserRegForm;

public interface IUserMgmtService {
	
	//Login functinality method
	public String login(LoginForm form);
	
	//Registration Functionality Method
	public String emilCheck(String emailId);
	
	public Map<Integer ,String> loadCountries();
	
	public Map<Integer , String> loadStates(Integer countryId);
	
	public Map<Integer , String> loadCities(Integer stateId);
	
	public String registerUser(UserRegForm regForm);
	
	//Unlock account functionality method
	public String unlockAccount(UnlockAccForm unlockAccForm);
	
	//Forget Password Functionality Method
	public String forgotPwd(String email);

	
	
}
