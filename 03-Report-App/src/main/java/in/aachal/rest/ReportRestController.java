package in.aachal.rest;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.aachal.reports.ExcelGenerator;
import in.aachal.reports.PdfGenerator;
import in.aachal.request.SearchRequest;
import in.aachal.response.SearchResponse;
import in.aachal.service.ReportServiceImpl;

@RestController
public class ReportRestController {

	@Autowired
	private ReportServiceImpl service;
	
	
	@GetMapping("/plan-names")
	public List<String> getPlanNames(){
    return service.getPlanNames();
	}
	
	
	@GetMapping("/plan-statues")
	public List<String> getPlanStatuses(){
    return service.getPlanStatuses();
	}
	
	
	@PostMapping("/search")
	public List<SearchResponse> search(@RequestBody SearchRequest request){
		return service.search(request);
		
		}
	
	@PostMapping("/excel")
	public void generateExcel(@RequestBody SearchRequest request,  HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Plans.csv";
		response.setHeader(headerKey, headerValue);
		
		
		List<SearchResponse> records  = service.search(request);
		ExcelGenerator excel = new ExcelGenerator();
		excel.generateExcel(records, response);
	}
	
	//without filtering collect record
	@GetMapping("/excel1")
	public void generateExcel1(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");   
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Plans.csv";
		response.setHeader(headerKey, headerValue);
		
		
		List<SearchResponse> records  = service.search(null);
		ExcelGenerator excel = new ExcelGenerator();
		excel.generateExcel(records, response);
	}
	
	
	@PostMapping("/pdf")
	public void generatePdf(@RequestBody SearchRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Plans.pdf";
		response.setHeader(headerKey, headerValue);
		
		List<SearchResponse> records = service.search(request);
		PdfGenerator pdfGen = new PdfGenerator();
		pdfGen.generatePdf(records, response);
	}
	
	
	@GetMapping("/pdf1")
	public void generatePdf1(HttpServletResponse response) throws Exception {
		
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Plans.pdf";
		response.setHeader(headerKey, headerValue);
		
		List<SearchResponse> records = service.search(null);
		PdfGenerator pdfGen = new PdfGenerator();
		pdfGen.generatePdf(records, response);
	}
	
	
}
