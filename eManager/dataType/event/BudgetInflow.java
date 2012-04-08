package eManager.dataType.event;

import eManager.dataType.common.Date;

public class BudgetInflow {
	private int inflowID;
	private String sponsor;
	private double amount;
	private Date date;
	private String remarks;

	public BudgetInflow(String sponsor, double amount, Date date, String remarks) {
		this.sponsor = sponsor;
		this.amount = amount;
		this.date = date;
		this.remarks = remarks;
	}

	public BudgetInflow(int inflowID, String sponsor, double amount, Date date,
			String remarks) {
		this.inflowID = inflowID;
		this.sponsor = sponsor;
		this.amount = amount;
		this.date = date;
		this.remarks = remarks;
	}

	public int getInflowID() {
		return inflowID;
	}

	public void setInflowID(int inflowID) {
		this.inflowID = inflowID;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}