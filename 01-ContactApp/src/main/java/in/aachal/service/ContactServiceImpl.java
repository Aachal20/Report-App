package in.aachal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.aachal.entity.Contact;
import in.aachal.repository.ContactRepository;

@Service
public class ContactServiceImpl implements IContactService{

	//Inject In memory proxy class
	@Autowired
	private ContactRepository contactRepo;

	@Override
	public String upsert(Contact contact) {
		Contact status = contactRepo.save(contact);
		return "CONTACT SAVED SUCCESSFULLY";
	}

	@Override
	public Contact  getContactById(int cid) {
		Optional<Contact> findById = contactRepo.findById(cid);
		if(findById.isPresent()) {
			Contact contact = findById.get();
			return contact;
		}
		return null;
	}

	@Override
	public List<Contact> getAllContact() {
		List<Contact> findAll = contactRepo.findAll();
		return findAll;
	}

	@Override
	public String deleteContactByID(int cid) {
		//contactRepo.deleteById(cid);
		Optional<Contact> contact= 	contactRepo.findById(cid);
		if(contact.isPresent()) {	
			Contact contact1 = contact.get();
			contact1.setActiveSw("inactive");
			contactRepo.save(contact1);
		}
		return "CONTACT DELETED SUCCESSFULLY";

	}


}
