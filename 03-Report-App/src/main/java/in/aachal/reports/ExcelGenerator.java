package in.aachal.reports;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import in.aachal.response.SearchResponse;

public class ExcelGenerator {

	public void generateExcel(List<SearchResponse> response , HttpServletResponse  httpResponse) throws Exception {

		HSSFWorkbook  workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Plans");
		HSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("S.NO");
		headerRow.createCell(1).setCellValue("HOLDER NAME");
		headerRow.createCell(2).setCellValue("HOLDER SSN");
		headerRow.createCell(3).setCellValue("PLAN NAME");
		headerRow.createCell(4).setCellValue("PLAN STATUS");
		headerRow.createCell(5).setCellValue("START DATE");
		headerRow.createCell(6).setCellValue("END DATE");
		headerRow.createCell(7).setCellValue("BENIFIT AMOUNT");
		headerRow.createCell(8).setCellValue("DENIAL REASON");


		int sno=1;
		for(int i = 0 ; i < response.size() ;  i++) {
			HSSFRow dataRow  = sheet.createRow(i+1);
			
			SearchResponse record  = response.get(i);
			
			dataRow.createCell(0).setCellValue(i+1);
			if(record.getHolderName() != null)
				dataRow.createCell(1).setCellValue(record.getHolderName());

			if(record.getHolderSsn() != null)
				dataRow.createCell(2).setCellValue(record.getHolderSsn());
			
			dataRow.createCell(3).setCellValue(record.getPlanName());
			dataRow.createCell(4).setCellValue(record.getPlanStatus());

			if(record.getStartDate() != null)
				dataRow.createCell(5).setCellValue(String.valueOf(record.getStartDate()));

			if(record.getEndDate() != null)
				dataRow.createCell(6).setCellValue(String.valueOf(record.getEndDate()));

			if(record.getBenifitAmt() != null)
				dataRow.createCell(7).setCellValue(record.getBenifitAmt());

			if(record.getDenialReason() != null)
				dataRow.createCell(8).setCellValue(record.getDenialReason());
			/*
			 * dataRow.createCell().setCellValue(record.getHolderName());
			 * dataRow.createCell(0).setCellValue(record.getHolderName());
			 * dataRow.createCell(1).setCellValue(record.getHolderSsn());
			 * dataRow.createCell(2).setCellValue(record.getPlanName());
			 * dataRow.createCell(3).setCellValue(record.getPlanStatus());
			 * dataRow.createCell(4).setCellValue(String.valueOf(record.getStartDate()));
			 * dataRow.createCell(5).setCellValue(String.valueOf(record.getEndDate()));
			 * dataRow.createCell(6).setCellValue(record.getBenifitAmt());
			 * dataRow.createCell(7).setCellValue(record.getDenialReason());
			 */
		}
		workbook.write(httpResponse.getOutputStream());
		workbook.close();

	}
}
