package in.aachal.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.aachal.binding.LoginForm;
import in.aachal.binding.UnlockAccForm;
import in.aachal.binding.UserRegForm;
import in.aachal.entity.CityMasterEntity;
import in.aachal.entity.CountryMasterEntity;
import in.aachal.entity.StateMasterEntity;
import in.aachal.entity.UserAccountEntity;
import in.aachal.repository.CityRepository;
import in.aachal.repository.CountryRepository;
import in.aachal.repository.StateRepository;
import in.aachal.repository.UserAccountRepository;
import in.aachal.util.EmailUtils;

@Service
public class UserMgmtServiceImpl implements IUserMgmtService {

	private static final SecureRandom random = new SecureRandom();

	@Autowired
	private UserAccountRepository userAccRepo;

	@Autowired
	private CountryRepository countryRepo;


	@Autowired
	private StateRepository  stateRepo;


	@Autowired
	private CityRepository cityRepo;


	@Autowired
	private EmailUtils emailUtils;

	@Override
	public String login(LoginForm loginForm) {
		UserAccountEntity entity = userAccRepo.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPwd());

		if(entity==null) {
			return "INVALID CREDENTIALS";
		}

		if(entity!=null && entity.getAccStatus().equals("LOCKED")) {
			return "Your Account Locked";
		}
		return "SUCCESS";
	}


	@Override
	public String emilCheck(String emailId) {
		UserAccountEntity entity = userAccRepo.findByEmail(emailId);
		if(entity==null) {
			return "UNIQUE";
		}
		return "DUPLICATE";
	}


	@Override
	public Map<Integer, String> loadCountries() {
		List<CountryMasterEntity> countries = countryRepo.findAll();

		Map<Integer,String> map = new HashMap<>();

		for(CountryMasterEntity entity : countries) {
			map.put(entity.getCountryId(), entity.getCountryName());
		}

		return map;
	}


	@Override
	public Map<Integer, String> loadStates(Integer countryId) {       //countryId is not a prim key in states Master table
		List<StateMasterEntity> states = stateRepo.findByCountryId(countryId);

		Map<Integer,String> statesMap = new HashMap<>();

		for(StateMasterEntity entity : states) {
			statesMap.put(entity.getStateId(),entity.getStateName());
		}
		return statesMap;
	}


	@Override
	public Map<Integer, String> loadCities(Integer stateId) {
		List<CityMasterEntity> cities = cityRepo.findByStateId(stateId);
		Map<Integer , String> citiesmap = new HashMap();

		for(CityMasterEntity city : cities) {
			citiesmap.put(city.getCityID(),city.getCityName());
		}
		return citiesmap;
	}


	@Override
	public String registerUser(UserRegForm regForm) {

		UserAccountEntity entity = new UserAccountEntity();

		BeanUtils.copyProperties(regForm, entity);

		entity.setAccStatus("LOCKED");

		entity.setPassword(generateRandomPwd());

		UserAccountEntity save = userAccRepo.save(entity);

		String email = regForm.getEmail();
		String subject = "User Registration - Naresh IT";
		String fileName= "UNLOCK-ACC-EMAIL-BODY-TEMPLATE";
		readMailBodyContent(fileName, entity);
		boolean isSent = emailUtils.sendEmail(email, subject, fileName);

		if(save.getUserid()!=null && isSent) {
			return "SUCCESS";
		}
		return "FAIL";

	}


	private String generateRandomPwd() {

		int leftLimit = 48;   //numeral 0
		int rightLimit=122;    //letter z
		int targetStringLength=6;

		return random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		/*
		 * Random random = new Random(); String generatedString = random.ints(leftLimit
		 * , rightLimit+1).filter(i->(i<=57 || i>=65) && (i<=90 || i>=97))
		 * .limit(targetStringLength). collect(StringBuilder::new ,
		 * StringBuilder::appendCodePoint,StringBuilder::append) .toString();
		 * 
		 * return generatedString;
		 */


	}

	private String readMailBodyContent(String fileName , UserAccountEntity entity) {
		String mailBody = null;
		try {
			StringBuffer sb = new StringBuffer();

			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);

			String line = br.readLine();      //reading first line of data
			while(line!=null) {
				sb.append(line);             //appending line data to buffer object
				line =br.readLine();        //reading next line of data
			} 

			mailBody = mailBody.replace("{FNAME}" , entity.getFName());
			mailBody = mailBody.replace("{LNAME}" , entity.getLName());
			mailBody = mailBody.replace("{TEMP-PWD}" , entity.getPassword());
			mailBody = mailBody.replace("{EMAIL}" , entity.getEmail());
			mailBody = mailBody.replace("{PWD}" , entity.getPassword());

			br.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}

	@Override
	public String unlockAccount(UnlockAccForm unlockAccForm) {

		if(!(unlockAccForm.getNewPwd().equals(unlockAccForm.getConfirmNewPwd()))) {
			return " password and confirm password should be same";
		}
		UserAccountEntity entity = userAccRepo.findByEmailAndPassword(unlockAccForm.getEmail(), unlockAccForm.getTempPwd());

		if(entity==null){
			return "Incorect Temporary password";
		}


		entity.setPassword(unlockAccForm.getNewPwd());
		entity.setAccStatus("UNLOCK");
		userAccRepo.save(entity);

		return "ACCOUNT UNLOCKED";
	}


	@Override
	public String forgotPwd(String email) {

		UserAccountEntity entity = userAccRepo.findByEmail(email);
		if(entity==null) {
			return "Invalid Email Id";
		}


		String subject = "/RECOVER-PASSWORD-EMAIL-BODY-TEMPLATE";
		String fileName= "/RECOVER-PASSWORD-EMAIL-BODY-TEMPLATE.txt";

		readMailBodyContent(fileName, entity);

		boolean isSent = emailUtils.sendEmail(email, subject, fileName);

		if(isSent) {
			return "Password sent to registered email";
		}
		return "FAIL";

		//If record avilable send pass to user mail
		//TODO: Email sending
		//return null;
	}


}
