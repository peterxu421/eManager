 import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.ibm.icu.text.DecimalFormat;
import org.eclipse.swt.widgets.DateTime;



public class AddInflowPage extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private final Text sponsor;
	private final Text amount;
	private final Text remarks;
	private final DateTime date;
	
	private Event event; 
	private DecimalFormat df = new DecimalFormat("#.00"); // to format a double to two decimal palces 

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AddInflowPage(Composite parent, int style, Event event) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.event = event; 
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(0, 0, 688, 465);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		int index = EventPlanning_Budget.InflowTable.getSelectionIndex();
		
		Label lblCashInflow = new Label(composite, SWT.NONE);
		lblCashInflow.setBounds(265, 42, 101, 15);
		toolkit.adapt(lblCashInflow, true, true);
		lblCashInflow.setText("Cash Inflow");
		
		Label lblSponsor = new Label(composite, SWT.NONE);
		lblSponsor.setAlignment(SWT.RIGHT);
		lblSponsor.setBounds(175, 94, 61, 15);
		toolkit.adapt(lblSponsor, true, true);
		lblSponsor.setText("Sponsor");
		
		sponsor = new Text(composite, SWT.BORDER);
		sponsor.setBounds(265, 91, 127, 21);
		toolkit.adapt(sponsor, true, true);
		if(index >=0 && index < EventPlanning_Budget.InflowTable.getItemCount())
			sponsor.setText(EventPlanning_Budget.InflowTable.getItem(index).getText(0));
		
		Label lblAmount = new Label(composite, SWT.NONE);
		lblAmount.setBounds(175, 130, 61, 15);
		toolkit.adapt(lblAmount, true, true);
		lblAmount.setText("Amount($)");
		
		amount = new Text(composite, SWT.BORDER);
		amount.setBounds(265, 127, 127, 21);
		toolkit.adapt(amount, true, true);
		if(index >=0 && index <EventPlanning_Budget.InflowTable.getItemCount())
			amount.setText(EventPlanning_Budget.InflowTable.getItem(index).getText(1));
		
		Label lblDate = new Label(composite, SWT.NONE);
		lblDate.setText("Date");
		lblDate.setBounds(209, 168, 27, 15);
		toolkit.adapt(lblDate, true, true);
		
		date = new DateTime(composite, SWT.BORDER);
		date.setBounds(265, 159, 127, 24);
		toolkit.adapt(date);
		toolkit.paintBordersFor(date);
		if(index >=0 && index < EventPlanning_Budget.InflowTable.getItemCount()){
			Date dt = Date.parseDate( EventPlanning_Budget.InflowTable.getItem(index).getText(2));
			date.setYear(dt.getYear());
			date.setMonth(dt.getMonth()-1);
			date.setDay(dt.getDay());
		}
		
		Label lblRemarks = new Label(composite, SWT.NONE);
		lblRemarks.setBounds(188, 203, 48, 15);
		toolkit.adapt(lblRemarks, true, true);
		lblRemarks.setText("Remarks");
		
		remarks = new Text(composite, SWT.BORDER);
		remarks.setBounds(265, 200, 127, 21);
		toolkit.adapt(remarks, true, true);
		if(index >=0 && index < EventPlanning_Budget.InflowTable.getItemCount())
			remarks.setText(EventPlanning_Budget.InflowTable.getItem(index).getText(3));
		
		EventPlanning_Budget.InflowTable.deselect(index);  // deselect 
		
		Button btnAdd = new Button(composite, SWT.NONE);
		btnAdd.setBounds(209, 264, 75, 25);
		toolkit.adapt(btnAdd, true, true);
		btnAdd.setText("Add");
		btnAdd.addSelectionListener(new Add(index));
		
		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setBounds(317, 264, 75, 25);
		toolkit.adapt(btnCancel, true, true);
		btnCancel.setText("Cancel");
		btnCancel.addSelectionListener(new Cancel());

	}
	class Add extends SelectionAdapter {
		int localIndex;
		public Add( int index){
			localIndex = index;
		}
		public void widgetSelected(SelectionEvent e) {
			String _sponsor = "";
			double _amount = 0;
			String _date ="";
			String _remarks = "";
			
			if (!sponsor.getText().isEmpty()) {
				_sponsor = sponsor.getText();
			}
			if (!amount.getText().isEmpty()) {
				_amount = Double.parseDouble(amount.getText());
			}
			if (!remarks.getText().isEmpty()) {
			    _remarks = remarks.getText();
			}
			_date = String.format("%04d",date.getYear())  + "-"
			        + String.format("%02d", date.getMonth()+1)  + "-" 
					+ String.format("%02d",date.getDay()) ;  // getMonth() + 1 since getMonth() returns 0 to 11
			if (!sponsor.getText().isEmpty()
					&& !amount.getText().isEmpty()
					&& !remarks.getText().isEmpty()) {
				if(localIndex ==-1){
					TableItem temp = new TableItem(EventPlanning_Budget.InflowTable, SWT.NULL);
					temp.setText(0, _sponsor);
					temp.setText(1, String.valueOf(_amount));
					temp.setText(2, _date);
					temp.setText(3, _remarks);
					double currentAmount = Double.parseDouble(EventPlanning_Budget.lblReceivedAmount.getText());
					EventPlanning_Budget.lblReceivedAmount.setText(String.valueOf(df.format(currentAmount + _amount)));  // the double value is formated to two decimal palces before being converted to a string
					
					/* update database */
					DatabaseReader db = new DatabaseReader();
					Inflow newInflow = new Inflow(_sponsor, _amount,Date.parseDate(_date), _remarks);
					db.insertInflow(event, newInflow);
					
					/* update budget overview section */
					LabelCashFlow lbl = new LabelCashFlow();
                    lbl.label(); 
					
					getParent().dispose();
				}
				else {
					TableItem temp = EventPlanning_Budget.InflowTable.getItem(localIndex);
					double currentAmount = Double.parseDouble(EventPlanning_Budget.lblReceivedAmount.getText());
					double deduction = Double.parseDouble(temp.getText(1));
					currentAmount -= deduction; // deduct the old value
					temp.setText(0, _sponsor);
					temp.setText(1, String.valueOf(_amount));
					temp.setText(2, _date);
					temp.setText(3, _remarks);
					EventPlanning_Budget.lblReceivedAmount.setText(String.valueOf(df.format(currentAmount + _amount))); // add the new value
					
					/* update database */
					DatabaseReader db = new DatabaseReader();
					Inflow newInflow = db.getInflow(event).get(localIndex);
					newInflow.setSponsor(_sponsor);
					newInflow.setAmount(_amount);
					newInflow.setDate(Date.parseDate(_date));
					newInflow.setRemarks(_remarks);
					db.updateInflow(newInflow);
					
					/* update budget overview section */
					LabelCashFlow lbl = new LabelCashFlow();
                    lbl.label(); 
					
					getParent().dispose();
				}
				
			}
		}
	}
	class Cancel extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
