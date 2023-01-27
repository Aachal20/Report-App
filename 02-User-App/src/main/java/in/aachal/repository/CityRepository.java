package in.aachal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.aachal.entity.CityMasterEntity;

public interface CityRepository extends JpaRepository<CityMasterEntity, Integer> {

	//select * from   cities_Master where state_id=?
	public List<CityMasterEntity> findByStateId(Integer stateId);
}
