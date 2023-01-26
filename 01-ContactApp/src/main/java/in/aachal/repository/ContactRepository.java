package in.aachal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.aachal.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
