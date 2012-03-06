import com.ibm.icu.text.DecimalFormat;


public class LabelCashFlow {
	
	private DecimalFormat df = new DecimalFormat("#.00"); // to format a double to two decimal palces 
	
	public void label(){
		double currentBudget = Double.parseDouble(EventPlanning_Budget.lblRemainingAmount.getText());
		double moneyReceived = Double.parseDouble(EventPlanning_Budget.lblReceivedAmount.getText());
		double moneySpent = Double.parseDouble(EventPlanning_Budget.lblSpentAmount.getText());
		double newBudget = moneyReceived - moneySpent;
		double change = newBudget - currentBudget;
		double stillHave = Double.parseDouble(EventPlanning_Budget.lblYouStillHave_Amount.getText());
		
		stillHave += change;
		EventPlanning_Budget.lblYouStillHave_Amount.setText(String.valueOf(df.format(stillHave)));
		EventPlanning_Budget.lblRemainingAmount.setText(String.valueOf(df.format(newBudget)));
		
		EventPlanning_Budget.lblReceivedAmount1.setText(EventPlanning_Budget.lblReceivedAmount.getText());
		EventPlanning_Budget.lblSpentAmount1.setText(EventPlanning_Budget.lblSpentAmount.getText());
	}
}
