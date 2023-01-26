package in.aachal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import in.aachal.entity.Contact;


public interface IContactService {

	public String upsert(Contact contact);
	public Contact getContactById(int cid);
	public List<Contact> getAllContact();
	public String deleteContactByID(int cid);
	
}
