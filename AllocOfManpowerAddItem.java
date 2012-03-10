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
import org.eclipse.swt.widgets.DateTime;

public class AllocOfManpowerAddItem extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text task;
	private Text assignedTo;
	private DateTime dueDate;
	private Button done;

	private Event event;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public AllocOfManpowerAddItem(Composite parent, int style, Table table_1,
			Event event) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.event = event;
		setLayout(null);

		Composite comp_eP_actual_allocOfManpower_AddPage = new Composite(this,
				SWT.NONE);
		comp_eP_actual_allocOfManpower_AddPage.setBounds(56, 10, 372, 327);
		comp_eP_actual_allocOfManpower_AddPage.setLayout(null);
		toolkit.adapt(comp_eP_actual_allocOfManpower_AddPage);
		toolkit.paintBordersFor(comp_eP_actual_allocOfManpower_AddPage);

		Label lblPageName = new Label(comp_eP_actual_allocOfManpower_AddPage,
				SWT.NONE);
		lblPageName.setBounds(152, 25, 137, 15);
		toolkit.adapt(lblPageName, true, true);
		lblPageName.setText("Allocation of Manpower");

		Label lbl_eP_actual_allocOfManpower_task = new Label(
				comp_eP_actual_allocOfManpower_AddPage, SWT.NONE);
		lbl_eP_actual_allocOfManpower_task.setAlignment(SWT.RIGHT);
		lbl_eP_actual_allocOfManpower_task.setBounds(62, 65, 54, 17);
		toolkit.adapt(lbl_eP_actual_allocOfManpower_task, true, true);
		lbl_eP_actual_allocOfManpower_task.setText("Task");

		Label lbl_eP_actual_allocOfManpower_assignedTo = new Label(
				comp_eP_actual_allocOfManpower_AddPage, SWT.NONE);
		lbl_eP_actual_allocOfManpower_assignedTo.setAlignment(SWT.RIGHT);
		lbl_eP_actual_allocOfManpower_assignedTo.setBounds(44, 96, 72, 17);
		toolkit.adapt(lbl_eP_actual_allocOfManpower_assignedTo, true, true);
		lbl_eP_actual_allocOfManpower_assignedTo.setText("Assigned To");

<<<<<<< HEAD
		Label lbl_eP_actual_allocOfManpower_dateDue = new Label(comp_eP_actual_allocOfManpower_AddPage, SWT.NONE);
		lbl_eP_actual_allocOfManpower_dateDue.setAlignment(SWT.RIGHT);
		lbl_eP_actual_allocOfManpower_dateDue.setBounds(62, 132, 54, 17);
		toolkit.adapt(lbl_eP_actual_allocOfManpower_dateDue, true, true);
		lbl_eP_actual_allocOfManpower_dateDue.setText("Date Due");
		
		Label lbl_eP_actual_allocOfManpower_done = new Label(comp_eP_actual_allocOfManpower_AddPage, SWT.RIGHT);
=======
		Label lbl_eP_actual_allocOfManpower_dateDue = new Label(
				comp_eP_actual_allocOfManpower_AddPage, SWT.NONE);
		lbl_eP_actual_allocOfManpower_dateDue.setAlignment(SWT.CENTER);
		lbl_eP_actual_allocOfManpower_dateDue.setBounds(62, 132, 54, 17);
		toolkit.adapt(lbl_eP_actual_allocOfManpower_dateDue, true, true);
		lbl_eP_actual_allocOfManpower_dateDue.setText("Date Due");

		Label lbl_eP_actual_allocOfManpower_done = new Label(
				comp_eP_actual_allocOfManpower_AddPage, SWT.RIGHT);
		lbl_eP_actual_allocOfManpower_done.setAlignment(SWT.CENTER);
>>>>>>> 4bafc5eca8f50f40537fe9b82028dc57cadf896a
		lbl_eP_actual_allocOfManpower_done.setText("Done");
		lbl_eP_actual_allocOfManpower_done.setBounds(62, 171, 54, 17);
		toolkit.adapt(lbl_eP_actual_allocOfManpower_done, true, true);

		task = new Text(comp_eP_actual_allocOfManpower_AddPage, SWT.BORDER);
		task.setBounds(152, 59, 125, 23);
		toolkit.adapt(task, true, true);

		assignedTo = new Text(comp_eP_actual_allocOfManpower_AddPage,
				SWT.BORDER);
		assignedTo.setBounds(152, 93, 125, 23);
		toolkit.adapt(assignedTo, true, true);

		dueDate = new DateTime(comp_eP_actual_allocOfManpower_AddPage,
				SWT.BORDER);
		dueDate.setBounds(152, 125, 125, 24);
		toolkit.adapt(dueDate);
		toolkit.paintBordersFor(dueDate);

		done = new Button(comp_eP_actual_allocOfManpower_AddPage, SWT.CHECK);
		done.setBounds(152, 170, 93, 16);
		toolkit.adapt(done, true, true);

		Button btn_eP_actual_allocOfManpower_add = new Button(
				comp_eP_actual_allocOfManpower_AddPage, SWT.NONE);
		btn_eP_actual_allocOfManpower_add
				.addSelectionListener(new AllocOfManpowerAddNewItem(table_1));
		btn_eP_actual_allocOfManpower_add.setBounds(89, 209, 66, 27);
		toolkit.adapt(btn_eP_actual_allocOfManpower_add, true, true);
		btn_eP_actual_allocOfManpower_add.setText("Add Item");

		Button btn_eP_actual_allocOfManpower_cancel = new Button(
				comp_eP_actual_allocOfManpower_AddPage, SWT.NONE);
		btn_eP_actual_allocOfManpower_cancel.setBounds(199, 209, 66, 27);
		toolkit.adapt(btn_eP_actual_allocOfManpower_cancel, true, true);
		btn_eP_actual_allocOfManpower_cancel.setText("Cancel");
		btn_eP_actual_allocOfManpower_cancel.addSelectionListener(new Cancel());

	}

	public class AllocOfManpowerAddNewItem extends SelectionAdapter {
		Table table_1;

		public AllocOfManpowerAddNewItem(Table table_1) {
			this.table_1 = table_1;
		}

		public void widgetSelected(SelectionEvent e) {
			String[] manPowerArray = new String[4];
			if (!task.getText().isEmpty()) {
				manPowerArray[0] = task.getText();
			}
			if (!assignedTo.getText().isEmpty()) {
				manPowerArray[1] = assignedTo.getText();
				manPowerArray[2] = String.format("%02d", dueDate.getYear())
						+ "-" + String.format("%02d", dueDate.getMonth() + 1)
						+ "-" + String.format("%02d", dueDate.getDay()); // getMonth()
																			// returns
																			// 0
																			// to
																			// 11
				if (done.getSelection())
					manPowerArray[3] = "Yes";
				else
					manPowerArray[3] = "No";
				if (!task.getText().isEmpty()
						&& !assignedTo.getText().isEmpty()) {
					/* update the manpower allocation table */
					TableItem item = new TableItem(table_1, SWT.NULL);
					for (int i = 0; i < 4; i++) {
						item.setText(i, manPowerArray[i]);
					}

					/* update the database */
					DatabaseReader db = new DatabaseReader();
					ManpowerAllocation newAlloc = new ManpowerAllocation(
							manPowerArray[0], manPowerArray[1],
							Date.parseDate(manPowerArray[2]),
							manPowerArray[3] == "Yes");
					db.insertManpowerAllocation(event, newAlloc);

					getParent().dispose();
				}
			}
		}
	}

	public class Cancel extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
