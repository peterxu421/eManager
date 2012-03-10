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

public class AddOutflowPage extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private final Text item;
	private final Text quantity;
	private final Text type;
	private final Text cost;
	private final DateTime date;

	private Event event;
	private DecimalFormat df = new DecimalFormat("#.00"); // to format a double
															// to two decimal
															// places

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public AddOutflowPage(Composite parent, int style, Event event) {
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
		composite.setBounds(10, 0, 688, 465);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);

		int index = EventPlanning_Budget.OutflowTable.getSelectionIndex();

		Label lblCashOutflow = new Label(composite, SWT.NONE);
		lblCashOutflow.setBounds(265, 42, 101, 15);
		toolkit.adapt(lblCashOutflow, true, true);
		lblCashOutflow.setText("Cash Outflow");

		Label lblItem = new Label(composite, SWT.NONE);
		lblItem.setAlignment(SWT.RIGHT);
		lblItem.setBounds(175, 78, 61, 15);
		toolkit.adapt(lblItem, true, true);

		lblItem.setText("Item:");
		Label lblQuantity = new Label(composite, SWT.NONE);
		lblQuantity.setAlignment(SWT.RIGHT);
		lblQuantity.setBounds(175, 111, 61, 15);
		toolkit.adapt(lblQuantity, true, true);
		lblQuantity.setText("Quantity:");
		Label lblType = new Label(composite, SWT.NONE);
		lblType.setAlignment(SWT.RIGHT);
		lblType.setText("Type");
		lblType.setBounds(135, 146, 101, 15);
		toolkit.adapt(lblType, true, true);

		Label lblDate = new Label(composite, SWT.NONE);
		lblDate.setAlignment(SWT.RIGHT);
		lblDate.setBounds(135, 181, 101, 15);
		toolkit.adapt(lblDate, true, true);

		lblDate.setText("Date of Purchase:");

		Label lblCost = new Label(composite, SWT.NONE);
		lblCost.setAlignment(SWT.RIGHT);
		lblCost.setText("Cost($)");
		lblCost.setBounds(180, 211, 56, 15);
		toolkit.adapt(lblCost, true, true);

		item = new Text(composite, SWT.BORDER);
		item.setBounds(253, 75, 123, 21);
		toolkit.adapt(item, true, true);
		if (index >= 0
				&& index < EventPlanning_Budget.OutflowTable.getItemCount())
			item.setText(EventPlanning_Budget.OutflowTable.getItem(index)
					.getText(0));

		quantity = new Text(composite, SWT.BORDER);
		quantity.setBounds(253, 108, 123, 21);
		toolkit.adapt(quantity, true, true);
		if (index >= 0
				&& index < EventPlanning_Budget.OutflowTable.getItemCount())
			quantity.setText(EventPlanning_Budget.OutflowTable.getItem(index)
					.getText(1));

		type = new Text(composite, SWT.BORDER);
		type.setBounds(253, 143, 123, 21);
		toolkit.adapt(type, true, true);
		if (index >= 0
				&& index < EventPlanning_Budget.OutflowTable.getItemCount())
			type.setText(EventPlanning_Budget.OutflowTable.getItem(index)
					.getText(2));

		date = new DateTime(composite, SWT.BORDER);
		date.setBounds(253, 172, 123, 24);
		toolkit.adapt(date);
		toolkit.paintBordersFor(date);
		if (index >= 0
				&& index < EventPlanning_Budget.OutflowTable.getItemCount()) {
			Date dt = Date.parseDate(EventPlanning_Budget.OutflowTable.getItem(
					index).getText(3));
			date.setYear(dt.getYear());
			date.setMonth(dt.getMonth() - 1);
			date.setDay(dt.getDay());
		}

		cost = new Text(composite, SWT.BORDER);
		cost.setBounds(253, 208, 123, 21);
		toolkit.adapt(cost, true, true);
		if (index >= 0
				&& index < EventPlanning_Budget.OutflowTable.getItemCount())
			cost.setText(EventPlanning_Budget.OutflowTable.getItem(index)
					.getText(4));

		EventPlanning_Budget.OutflowTable.deselect(index); // deselect

		Button btnAdd = new Button(composite, SWT.NONE);
		btnAdd.setBounds(202, 251, 75, 25);
		toolkit.adapt(btnAdd, true, true);
		btnAdd.setText("Add");
		btnAdd.addSelectionListener(new Add(index));

		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setBounds(313, 251, 75, 25);
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
			int _quantity = 0;
			String _type = "";
			String _date = "";
			double _cost = 0;

			if (!item.getText().isEmpty()) {
				_item = item.getText();
			}
			if (!cost.getText().isEmpty()) {
				_cost = Double.parseDouble(cost.getText());
			}
			if (!quantity.getText().isEmpty()) {
				_quantity = Integer.parseInt(quantity.getText());
			}
			if (!type.getText().isEmpty()) {
				_type = type.getText();
			}
			_date = String.format("%04d", date.getYear()) + "-"
					+ String.format("%02d", date.getMonth() + 1) + "-"
					+ String.format("%02d", date.getDay()); // getMonth() + 1
															// since getMonth()
															// returns 0 to 11
			if (!item.getText().isEmpty() && !type.getText().isEmpty()
					&& !quantity.getText().isEmpty()
					&& !cost.getText().isEmpty()) {
				if (localIndex == -1) {
					TableItem temp = new TableItem(
							EventPlanning_Budget.OutflowTable, SWT.NULL);
					temp.setText(0, _item);
					temp.setText(1, String.valueOf(_quantity));
					temp.setText(2, _type);
					temp.setText(3, _date);
					temp.setText(4, String.valueOf(_cost));
					double currentAmount = Double
							.parseDouble(EventPlanning_Budget.lblSpentAmount
									.getText());
					EventPlanning_Budget.lblSpentAmount.setText(String
							.valueOf(df.format(currentAmount + _cost)));

					/* update database */
					DatabaseReader db = new DatabaseReader();
					Outflow newOutflow = new Outflow(_item, _quantity, _type,
							Date.parseDate(_date), _cost);
					db.insertOutflow(event, newOutflow);

					/* update budget overview section */
					LabelCashFlow lbl = new LabelCashFlow();
					lbl.label();

					getParent().dispose();
				} else {
					TableItem temp = EventPlanning_Budget.OutflowTable
							.getItem(localIndex);
					double currentAmount = Double
							.parseDouble(EventPlanning_Budget.lblSpentAmount
									.getText());
					double deduction = Double.parseDouble(temp.getText(4));
					currentAmount -= deduction; // deduct the old value
					temp.setText(0, _item);
					temp.setText(1, String.valueOf(_quantity));
					temp.setText(2, _type);
					temp.setText(3, _date.toString());
					temp.setText(4, String.valueOf(_cost));
					EventPlanning_Budget.lblSpentAmount.setText(String
							.valueOf(df.format(currentAmount + _cost))); // add
																			// the
																			// new
																			// value

					/* update database */
					DatabaseReader db = new DatabaseReader();
					Outflow newOutflow = db.getOutflow(event).get(localIndex);
					newOutflow.setItem(_item);
					newOutflow.setQuantity(_quantity);
					newOutflow.setType(_type);
					newOutflow.setDate(Date.parseDate(_date));
					newOutflow.setCost(_cost);
					db.updateOutflow(newOutflow);

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