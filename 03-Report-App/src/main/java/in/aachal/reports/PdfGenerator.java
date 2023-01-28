package in.aachal.reports;

import java.awt.Color;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import in.aachal.response.SearchResponse;

public class PdfGenerator {

	public void generatePdf(List<SearchResponse> response , HttpServletResponse  httpResponse) throws Exception {

		Document document = new Document();

		PdfWriter writer = PdfWriter.getInstance(document,httpResponse.getOutputStream());

		Font font = new Font(Font.COURIER , 17 , Font.BOLD , Color.DARK_GRAY);

		document.open();

		Paragraph para = new Paragraph("Eligibility Details "  , font);
		document.add(para);


		PdfPTable table = new PdfPTable(9);

		table.addCell("S.NO");
		table.addCell("Holder Name");
		table.addCell("Holder SSN");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");
		table.addCell("Benefit Amount");
		table.addCell("Denial Reason");


		int sno=1;
		for(SearchResponse record  : response) {

			table.addCell(String.valueOf(sno));
			table.addCell(record.getHolderName());
			table.addCell(String.valueOf(record.getHolderSsn()));
			table.addCell(record.getPlanName());
			table.addCell(record.getPlanStatus());
			table.addCell(String.valueOf(record.getStartDate()));
			table.addCell(String.valueOf(record.getEndDate()));
			table.addCell(String.valueOf(record.getBenifitAmt()));
			table.addCell(record.getDenialReason());
			sno++;
		}
		document.add(table);
		document.close();
		writer.close();
	}
}
