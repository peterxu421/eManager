package eManager.dataType.event;

import eManager.dataType.common.Date;

public class BudgetPlanning {
	private int budgetID;
	private String item;
	private String personInCharge;
	private double cost;
	private Date date;

	public BudgetPlanning(int budgetID, String item, String personInCharge,
			double cost, Date date) {
		this.budgetID = budgetID;
		this.item = item;
		this.personInCharge = personInCharge;
		this.cost = cost;
		this.date = date;
	}

	public BudgetPlanning(String item, String personInCharge, double cost,
			Date date) {
		this.item = item;
		this.personInCharge = personInCharge;
		this.cost = cost;
		this.date = date;
	}

	public int getBudgetID() {
		return budgetID;
	}

	public void setBudgetID(int budgetID) {
		this.budgetID = budgetID;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getPersonInCharge() {
		return personInCharge;
	}

	public void setPersonInCharge(String personInCharge) {
		this.personInCharge = personInCharge;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}