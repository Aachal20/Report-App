package in.aachal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.aachal.entity.StateMasterEntity;

public interface StateRepository extends JpaRepository<StateMasterEntity, Integer> {

	//select * from states_master where country_id=?
	public  List<StateMasterEntity> findByCountryId(Integer countryId);
}
