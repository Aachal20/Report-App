package in.aachal.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.aachal.entity.EligibilityDtlsEntity;
import in.aachal.repository.EligDtlsRepository;
import in.aachal.request.SearchRequest;
import in.aachal.response.SearchResponse;
@Service
public class ReportServiceImpl implements IReportService {


	@Autowired
	private EligDtlsRepository repository;

	@Override
	public List<String> getPlanNames() {
		return repository.getUniquePlanName();

	}

	@Override
	public List<String> getPlanStatuses() {
		return repository.getUniqueStatuses();
	}

	@Override
	public List<SearchResponse> search(SearchRequest request) {

		List<EligibilityDtlsEntity> eligRecords = null;

		if(isSearchReqEmpty(request)) {
			System.out.println("ReportServiceImpl.search()");
			eligRecords = repository.findAll();
			System.out.println("ReportServiceImpl.search()");
		}//why it is not collecting all the data from my db
		//Help me here
		
		else {


			String planName = request.getPlanName();
			String planStatus = request.getPlanStatus();
			LocalDate startDate = request.getStartDate();
			LocalDate endDate = request.getEndDate();

			EligibilityDtlsEntity entity =new EligibilityDtlsEntity();

			if(planName!=null && !planName.equals("")) {
				//add plan name to where clause
				entity.setPlanName(planName);
			}
			if(planStatus!=null &&! planStatus.equals("")) {
				//add plan status to where clasue
				entity.setPlanStatus(planStatus);
			}
			if(startDate!=null && endDate!=null) {
				//add startDate && endDate to where clasue
				entity.setStartDate(startDate);
				entity.setEndDate(endDate);
			}

			Example<EligibilityDtlsEntity> of = Example.of(entity);
			//List<EligibilityDtlsEntity> findAll = repository.findAll(of);
			eligRecords = repository.findAll(of);
		}
		List<SearchResponse> response = new ArrayList<>();
		for(EligibilityDtlsEntity eligRecord : eligRecords) {
			SearchResponse sr = new SearchResponse();
			BeanUtils.copyProperties(eligRecord, sr);
			response.add(sr);
		}

		return response;

	

	//user can select only plan-name and click on search button
	//user can select only plan-status and click on search button
	//user can select both plan-name and plan-status and click on search button

	//user can select only start-date and end-date click on search button

	//user can select plan-name, start-date and end-date  and click on search button
	//user can select plan-status, start-date and end-date  and click on search button
	//user can select plan-name, plan-status , start-date and end-date  and click on search button

	
	}
	
	private boolean isSearchReqEmpty(SearchRequest request) {
		if(request==null) {
			return true;
		}
		if(request.getPlanName() != null && !request.getPlanName().equals("")) {
			return false;
		}
		if(request.getPlanStatus() != null && !request.getPlanStatus().equals("")) {
			return false;
		}
		if(request.getStartDate() != null && !request.getStartDate().equals("")) {
			return false;
		}
		if(request.getEndDate() != null && !request.getEndDate().equals("")) {
			return false;
		}
		return true;
	}
	
}


