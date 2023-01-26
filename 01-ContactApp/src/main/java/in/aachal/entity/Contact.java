package in.aachal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="CONTACT_DTLS")
@Data
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cid;
	
	@Column(length=20,name="PERSON_NAME")
	private String name;
	
	@Column(length=20,name="EMAIL_ID")
	private String email;
	
	@Column(name="PH_NO")
	private long phno;
	
	@Column(length=20,name="ACTIVE_SW")
	private String activeSw;
}
