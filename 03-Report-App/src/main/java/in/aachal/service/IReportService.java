package in.aachal.service;

import java.util.List;

import in.aachal.request.SearchRequest;
import in.aachal.response.SearchResponse;

public interface IReportService {

	public List<String> getPlanNames();
	
	public List<String> getPlanStatuses();
	
	public List<SearchResponse> search(SearchRequest request);
	

	
}
