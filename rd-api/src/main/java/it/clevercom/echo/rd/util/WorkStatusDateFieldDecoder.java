package it.clevercom.echo.rd.util;

import it.clevercom.echo.rd.enums.WorkStatusEnum;

public class WorkStatusDateFieldDecoder {
	
	// here are listed the field name used 
	// to dynamically combine date filter with status enum
	// this class is used with DateIntervalSpecification
	
	final static String requestedDateField = "creationdate";
	final static String scheduledDateField = "scheduleddate";
	final static String acceptedDateField = "acceptancedate";
	final static String executingDateField = "executingdate";
	final static String executedDateField = "executeddate";
	final static String reportingDateField = "reportingdate";
	final static String reportedDateField = "reporteddate";
	final static String deliveredDateField = "delivereddate";
	final static String archivedDateField = "archiveddate";
	final static String signedDateField = "signeddate";
	final static String canceledDateField = "canceleddate";
	
	public static String decodeDateFieldFromWorkStatus(WorkStatusEnum status) {
		if (status == null) return requestedDateField;
		switch (status) {
			case REQUESTED:
				return requestedDateField;
			case SCHEDULED:
				return scheduledDateField;
			case ACCEPTED:
				return acceptedDateField;
			case EXECUTING:
				return executingDateField;
			case EXECUTED:
				return executedDateField;
			case REPORTING:
				return reportingDateField;
			case REPORTED:	
				return reportedDateField;
			case DELIVERED:
				return deliveredDateField;
			case ARCHIVED: 	
				return archivedDateField;
			case SIGNED:
				return signedDateField;
			case CANCELED:
				return canceledDateField;
			default:
				return requestedDateField;
		}
	}
}
