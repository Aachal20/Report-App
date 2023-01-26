package in.aachal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.aachal.entity.Contact;
import in.aachal.service.ContactServiceImpl;

@RestController
@RequestMapping("/phoneBook")
public class ContactController {

	
	@Autowired
	private ContactServiceImpl service;

	@PostMapping("/contact")
	public ResponseEntity<String> saveContact(@RequestBody Contact contact){
		String saveContact = service.upsert(contact);
		return new ResponseEntity(saveContact , HttpStatus.CREATED);

	}

	@GetMapping("/contact/{cid}")
	public ResponseEntity<Contact> getContact(@PathVariable int cid){
		Contact contact = service.getContactById(cid);
		return new ResponseEntity(contact , HttpStatus.CREATED);

	}


	@GetMapping("/contacts")
	public ResponseEntity<List<Contact>> getAllContact(){
		List<Contact> allContact = service.getAllContact();
		return new ResponseEntity(allContact , HttpStatus.CREATED);

	}


	@DeleteMapping("/contact/{cid}")
	public ResponseEntity<String> deleteContact(@PathVariable int cid){
		String deleteContact = service.deleteContactByID(cid);
		return new ResponseEntity(deleteContact , HttpStatus.CREATED);

	}
}
