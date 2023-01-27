package in.aachal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.aachal.entity.CountryMasterEntity;

public interface CountryRepository extends JpaRepository<CountryMasterEntity, Integer> {

}
