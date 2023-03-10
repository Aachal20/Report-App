package in.aachal.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchResponse {

	private Long caseNum;
	private String planName;
	private String planStatus;
	private Double benifitAmt;
	private LocalDate startDate;
	private LocalDate endDate;
	private String denialReason;
	private String holderName;
	private Long holderSsn;
}
