import java.util.ArrayList;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import com.ibm.icu.text.DecimalFormat;
import org.eclipse.swt.widgets.DateTime;

public class AddBudgetAllocationPage extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text item;
	private Text personInCharge;
	private Text cost;
	private DateTime date;

	private Event event;
	private DecimalFormat df = new DecimalFormat("#.##"); // to format a double
															// value to two
															// decimal places

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public AddBudgetAllocationPage(Composite parent, int style, Event event) {
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

		int index = EventPlanning_Budget.AllocationTable.getSelectionIndex();

		Label lblBudgetAllocation = new Label(composite, SWT.NONE);
		lblBudgetAllocation.setBounds(265, 42, 101, 15);
		toolkit.adapt(lblBudgetAllocation, true, true);
		lblBudgetAllocation.setText("Budget Allocation");

		Label lblItem = new Label(composite, SWT.NONE);
		lblItem.setBounds(209, 94, 27, 15);
		toolkit.adapt(lblItem, true, true);
		lblItem.setText("Item:");
		item = new Text(composite, SWT.BORDER);
		item.setBounds(265, 91, 127, 21);
		toolkit.adapt(item, true, true);
		if (index >= 0
				&& index < EventPlanning_Budget.AllocationTable.getItemCount())
			item.setText(EventPlanning_Budget.AllocationTable.getItem(index)
					.getText(0));

		Label lblPersonInCharge = new Label(composite, SWT.NONE);
		lblPersonInCharge.setBounds(145, 130, 91, 15);
		toolkit.adapt(lblPersonInCharge, true, true);
		lblPersonInCharge.setText("Person in charge:");
		personInCharge = new Text(composite, SWT.BORDER);
		personInCharge.setBounds(265, 127, 127, 21);
		toolkit.adapt(personInCharge, true, true);
		if (index >= 0
				&& index < EventPlanning_Budget.AllocationTable.getItemCount())
			personInCharge.setText(EventPlanning_Budget.AllocationTable
					.getItem(index).getText(1));

		Label lblCost = new Label(composite, SWT.NONE);
		lblCost.setAlignment(SWT.RIGHT);
		lblCost.setBounds(175, 168, 61, 15);
		toolkit.adapt(lblCost, true, true);
		lblCost.setText("Cost($):");
		cost = new Text(composite, SWT.BORDER);
		cost.setBounds(265, 165, 127, 21);
		toolkit.adapt(cost, true, true);
		if (index >= 0
				&& index < EventPlanning_Budget.AllocationTable.getItemCount())
			cost.setText(EventPlanning_Budget.AllocationTable.getItem(index)
					.getText(2));

		Label lblDate = new Label(composite, SWT.NONE);
		lblDate.setBounds(209, 203, 27, 15);
		toolkit.adapt(lblDate, true, true);
		lblDate.setText("Date:");
		date = new DateTime(composite, SWT.BORDER);
		date.setBounds(265, 203, 127, 24);
		toolkit.adapt(date);
		toolkit.paintBordersFor(date);
		if (index >= 0
				&& index < EventPlanning_Budget.AllocationTable.getItemCount()) {
			Date dt = Date.parseDate(EventPlanning_Budget.AllocationTable
					.getItem(index).getText(3));
			date.setYear(dt.getYear());
			date.setMonth(dt.getMonth() - 1);
			date.setDay(dt.getDay());
		}

		EventPlanning_Budget.AllocationTable.deselect(index); // deselect

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

		public Add(int index) {
			localIndex = index;
		}

		public void widgetSelected(SelectionEvent e) {
			String _item = "";
			String _personInCharge = "";
			double _cost = 0;
			String _date = "";
			if (!item.getText().isEmpty()) {
				_item = item.getText();
			}
			if (!personInCharge.getText().isEmpty()) {
				_personInCharge = personInCharge.getText();
			}
			if (!cost.getText().isEmpty()) {
				_cost = Double.parseDouble(cost.getText());
			}
			_date = String.format("%04d", date.getYear()) + "-"
					+ String.format("%02d", date.getMonth() + 1) + "-"
					+ String.format("%02d", date.getDay()); // getMonth() + 1
															// since getMonth()
															// returns 0 to 11
			if (!item.getText().isEmpty()
					&& !personInCharge.getText().isEmpty()
					&& !cost.getText().isEmpty()) {
				if (localIndex == -1) {
					TableItem temp = new TableItem(
							EventPlanning_Budget.AllocationTable, SWT.NULL);
					temp.setText(0, _item);
					temp.setText(1, _personInCharge);
					temp.setText(2, String.valueOf(_cost));
					temp.setText(3, _date);
					double currentAmount = Double
							.parseDouble(EventPlanning_Budget.lblYouStillHave_Amount
									.getText());
					EventPlanning_Budget.lblYouStillHave_Amount.setText(String
							.valueOf(df.format(currentAmount - _cost)));

					/* update database */
					DatabaseReader db = new DatabaseReader();
					BudgetAllocation newAllocation = new BudgetAllocation(
							_item, _personInCharge, _cost,
							Date.parseDate(_date));
					db.insertBudgetAllocation(event, newAllocation);

					getParent().dispose();
				} else {
					TableItem temp = EventPlanning_Budget.AllocationTable
							.getItem(localIndex);
					double currentAmount = Double
							.parseDouble(EventPlanning_Budget.lblYouStillHave_Amount
									.getText());
					double deduction = Double.parseDouble(temp.getText(2));
					currentAmount += deduction; // add up the old value
					temp.setText(0, _item);
					temp.setText(1, _personInCharge);
					temp.setText(2, String.valueOf(_cost));
					temp.setText(3, _date);
					EventPlanning_Budget.lblYouStillHave_Amount.setText(String
							.valueOf(df.format(currentAmount - _cost))); // deduct
																			// the
																			// new
																			// value

					/* update database */
					DatabaseReader db = new DatabaseReader();
					BudgetAllocation newAllocation = db.getBudgetAllocation(
							event).get(localIndex);
					newAllocation.setItem(_item);
					newAllocation.setPersonInCharge(_personInCharge);
					newAllocation.setCost(_cost);
					newAllocation.setDate(Date.parseDate(_date));
					db.updateBudgetAllocation(newAllocation);

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
