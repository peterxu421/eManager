import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AllocOfManpowerAddItem extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text text_eP_actual_allocOfManpower_task;
	private Text text_eP_actual_allocOfManpower_assignedTo;
	private Text text_eP_actual_allocOfManpower_date;
	private Text text_eP_actual_allocOfManpower_dateDue;
	private Text text_eP_actual_allocOfManpower_done;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public AllocOfManpowerAddItem(Composite parent, int style, Table table_1) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		setLayout(null);

		Composite comp_eP_actual_allocOfManpower_AddPage = new Composite(this, SWT.NONE);
		comp_eP_actual_allocOfManpower_AddPage.setBounds(56, 10, 372, 290);
		comp_eP_actual_allocOfManpower_AddPage.setLayout(null);
		toolkit.adapt(comp_eP_actual_allocOfManpower_AddPage);
		toolkit.paintBordersFor(comp_eP_actual_allocOfManpower_AddPage);

		Label lbl_eP_actual_allocOfManpower_task = new Label(comp_eP_actual_allocOfManpower_AddPage, SWT.NONE);
		lbl_eP_actual_allocOfManpower_task.setBounds(35, 34, 54, 17);
		toolkit.adapt(lbl_eP_actual_allocOfManpower_task, true, true);
		lbl_eP_actual_allocOfManpower_task.setText("Task");

		Label lbl_eP_actual_allocOfManpower_assignedTo = new Label(comp_eP_actual_allocOfManpower_AddPage, SWT.NONE);
		lbl_eP_actual_allocOfManpower_assignedTo.setBounds(36, 68, 72, 17);
		toolkit.adapt(lbl_eP_actual_allocOfManpower_assignedTo, true, true);
		lbl_eP_actual_allocOfManpower_assignedTo.setText("Assigned To");

		Label lbl_eP_actual_allocOfManpower_dateAssigned = new Label(comp_eP_actual_allocOfManpower_AddPage, SWT.NONE);
		lbl_eP_actual_allocOfManpower_dateAssigned.setBounds(35, 106, 81, 20);
		toolkit.adapt(lbl_eP_actual_allocOfManpower_dateAssigned, true, true);
		lbl_eP_actual_allocOfManpower_dateAssigned.setText("Date Assigned");

		Label lbl_eP_actual_allocOfManpower_dateDue = new Label(comp_eP_actual_allocOfManpower_AddPage, SWT.NONE);
		lbl_eP_actual_allocOfManpower_dateDue.setBounds(35, 143, 54, 17);
		toolkit.adapt(lbl_eP_actual_allocOfManpower_dateDue, true, true);
		lbl_eP_actual_allocOfManpower_dateDue.setText("Date Due");
		
		Label lbl_eP_actual_allocOfManpower_done = new Label(comp_eP_actual_allocOfManpower_AddPage, SWT.NONE);
		lbl_eP_actual_allocOfManpower_done.setText("Done");
		lbl_eP_actual_allocOfManpower_done.setBounds(35, 183, 54, 17);
		toolkit.adapt(lbl_eP_actual_allocOfManpower_done, true, true);

		text_eP_actual_allocOfManpower_task = new Text(comp_eP_actual_allocOfManpower_AddPage, SWT.BORDER);
		text_eP_actual_allocOfManpower_task.setBounds(152, 31, 125, 23);
		toolkit.adapt(text_eP_actual_allocOfManpower_task, true, true);

		text_eP_actual_allocOfManpower_assignedTo = new Text(comp_eP_actual_allocOfManpower_AddPage, SWT.BORDER);
		text_eP_actual_allocOfManpower_assignedTo.setBounds(153, 65, 125, 23);
		toolkit.adapt(text_eP_actual_allocOfManpower_assignedTo, true, true);

		text_eP_actual_allocOfManpower_date = new Text(comp_eP_actual_allocOfManpower_AddPage, SWT.BORDER);
		text_eP_actual_allocOfManpower_date.setBounds(152, 103, 125, 23);
		toolkit.adapt(text_eP_actual_allocOfManpower_date, true, true);

		text_eP_actual_allocOfManpower_dateDue = new Text(comp_eP_actual_allocOfManpower_AddPage, SWT.BORDER);
		text_eP_actual_allocOfManpower_dateDue.setBounds(152, 140, 125, 23);
		toolkit.adapt(text_eP_actual_allocOfManpower_dateDue, true, true);
		
		text_eP_actual_allocOfManpower_done = new Text(comp_eP_actual_allocOfManpower_AddPage, SWT.BORDER);
		text_eP_actual_allocOfManpower_done.setBounds(152, 180, 125, 23);
		toolkit.adapt(text_eP_actual_allocOfManpower_done, true, true);

		Button btn_eP_actual_allocOfManpower_add = new Button(comp_eP_actual_allocOfManpower_AddPage, SWT.NONE);
		btn_eP_actual_allocOfManpower_add.addSelectionListener(new AllocOfManpowerAddNewItem(table_1));
		btn_eP_actual_allocOfManpower_add.setBounds(87, 219, 66, 27);
		toolkit.adapt(btn_eP_actual_allocOfManpower_add, true, true);
		btn_eP_actual_allocOfManpower_add.setText("Add");

		Button btn_eP_actual_allocOfManpower_cancel = new Button(comp_eP_actual_allocOfManpower_AddPage, SWT.NONE);
		btn_eP_actual_allocOfManpower_cancel.addSelectionListener(new AllocOfManpowerCancel());
		btn_eP_actual_allocOfManpower_cancel.setBounds(211, 219, 66, 27);
		toolkit.adapt(btn_eP_actual_allocOfManpower_cancel, true, true);
		btn_eP_actual_allocOfManpower_cancel.setText("Cancel");

	}

	public class AllocOfManpowerAddNewItem extends SelectionAdapter {
		Table table_1;

		public AllocOfManpowerAddNewItem(Table table_1) {
			this.table_1 = table_1;
		}

		public void widgetSelected(SelectionEvent e) {
			String[] itineraryArray = new String[5];
			if (!text_eP_actual_allocOfManpower_task.getText().isEmpty()) {
				itineraryArray[0] = text_eP_actual_allocOfManpower_task.getText();
			}
			if (!text_eP_actual_allocOfManpower_assignedTo.getText().isEmpty()) {
				itineraryArray[1] = text_eP_actual_allocOfManpower_assignedTo.getText();
			}
			if (!text_eP_actual_allocOfManpower_date.getText().isEmpty()) {
				itineraryArray[2] = text_eP_actual_allocOfManpower_date.getText();
			}
			if (!text_eP_actual_allocOfManpower_dateDue.getText().isEmpty()) {
				itineraryArray[3] = text_eP_actual_allocOfManpower_dateDue.getText();
			}
			if (!text_eP_actual_allocOfManpower_dateDue.getText().isEmpty()) {
				itineraryArray[4] = text_eP_actual_allocOfManpower_done.getText();
			}
			if (!text_eP_actual_allocOfManpower_task.getText().isEmpty() && !text_eP_actual_allocOfManpower_assignedTo.getText().isEmpty()
					&& !text_eP_actual_allocOfManpower_date.getText().isEmpty()
					&& !text_eP_actual_allocOfManpower_dateDue.getText().isEmpty()
					&& !text_eP_actual_allocOfManpower_done.getText().isEmpty()) {
				TableItem item = new TableItem(table_1, SWT.NULL);
				for (int i = 0; i < 5; i++) {
					item.setText(i, itineraryArray[i]);
				}
				getParent().dispose();
			}
		}
	}
	public class AllocOfManpowerCancel extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
