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

public class AllocOfManpowerEditItem extends Composite {

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
	public AllocOfManpowerEditItem(Composite parent, int style, Table table_1, int index, Event event) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.event=event;
		setLayout(null);

		Composite comp_eP_actual_allocOfManpower_AddPage = new Composite(this, SWT.NONE);
		comp_eP_actual_allocOfManpower_AddPage.setBounds(56, 10, 372, 327);
		comp_eP_actual_allocOfManpower_AddPage.setLayout(null);
		toolkit.adapt(comp_eP_actual_allocOfManpower_AddPage);
		toolkit.paintBordersFor(comp_eP_actual_allocOfManpower_AddPage);
		
		Label lblPageName = new Label(comp_eP_actual_allocOfManpower_AddPage, SWT.NONE);
		lblPageName.setBounds(152, 25, 137, 15);
		toolkit.adapt(lblPageName, true, true);
		lblPageName.setText("Allocation of Manpower");

		Label lbl_eP_actual_allocOfManpower_task = new Label(comp_eP_actual_allocOfManpower_AddPage, SWT.NONE);
		lbl_eP_actual_allocOfManpower_task.setAlignment(SWT.RIGHT);
		lbl_eP_actual_allocOfManpower_task.setBounds(62, 65, 54, 17);
		toolkit.adapt(lbl_eP_actual_allocOfManpower_task, true, true);
		lbl_eP_actual_allocOfManpower_task.setText("Task");

		Label lbl_eP_actual_allocOfManpower_assignedTo = new Label(comp_eP_actual_allocOfManpower_AddPage, SWT.NONE);
		lbl_eP_actual_allocOfManpower_assignedTo.setAlignment(SWT.RIGHT);
		lbl_eP_actual_allocOfManpower_assignedTo.setBounds(44, 93, 72, 17);
		toolkit.adapt(lbl_eP_actual_allocOfManpower_assignedTo, true, true);
		lbl_eP_actual_allocOfManpower_assignedTo.setText("Assigned To");

		Label lbl_eP_actual_allocOfManpower_dateDue = new Label(comp_eP_actual_allocOfManpower_AddPage, SWT.NONE);
		lbl_eP_actual_allocOfManpower_dateDue.setAlignment(SWT.RIGHT);
		lbl_eP_actual_allocOfManpower_dateDue.setBounds(62, 129, 54, 17);
		toolkit.adapt(lbl_eP_actual_allocOfManpower_dateDue, true, true);
		lbl_eP_actual_allocOfManpower_dateDue.setText("Date Due");
		
		Label lbl_eP_actual_allocOfManpower_done = new Label(comp_eP_actual_allocOfManpower_AddPage, SWT.NONE);
		lbl_eP_actual_allocOfManpower_done.setAlignment(SWT.RIGHT);
		lbl_eP_actual_allocOfManpower_done.setText("Done");
		lbl_eP_actual_allocOfManpower_done.setBounds(62, 170, 54, 17);
		toolkit.adapt(lbl_eP_actual_allocOfManpower_done, true, true);

		task = new Text(comp_eP_actual_allocOfManpower_AddPage, SWT.BORDER);
		task.setBounds(152, 59, 125, 23);
		toolkit.adapt(task, true, true);
		if(index >=0 && index < table_1.getItemCount()){
			 task.setText(table_1.getItem(index).getText(0));
		}

		assignedTo = new Text(comp_eP_actual_allocOfManpower_AddPage, SWT.BORDER);
		assignedTo.setBounds(152, 90, 125, 23);
		toolkit.adapt(assignedTo, true, true);
		if(index >=0 && index < table_1.getItemCount()){
			 assignedTo.setText(table_1.getItem(index).getText(1));
		}
		
		dueDate = new DateTime(comp_eP_actual_allocOfManpower_AddPage, SWT.BORDER);
		dueDate.setBounds(152, 129, 125, 24);
		toolkit.adapt(dueDate);
		toolkit.paintBordersFor(dueDate);
		if(index >=0 && index < table_1.getItemCount()){
			Date dt = Date.parseDate(table_1.getItem(index).getText(2));
			dueDate.setYear(dt.getYear());
			dueDate.setMonth(dt.getMonth()-1); 
			dueDate.setDay(dt.getDay());
		}
		
		done = new Button(comp_eP_actual_allocOfManpower_AddPage, SWT.CHECK);
		done.setBounds(152, 169, 93, 16);
		toolkit.adapt(done, true, true);
		if(index >=0 && index < table_1.getItemCount())
			done.setSelection(table_1.getItem(index).getText(3) == "Yes");

		Button btn_eP_actual_allocOfManpower_add = new Button(comp_eP_actual_allocOfManpower_AddPage, SWT.NONE);
		btn_eP_actual_allocOfManpower_add.setBounds(88, 213, 66, 27);
		toolkit.adapt(btn_eP_actual_allocOfManpower_add, true, true);
		btn_eP_actual_allocOfManpower_add.setText("Edit Item");
		btn_eP_actual_allocOfManpower_add.addSelectionListener(new AllocOfManpowerAddNewItem(table_1, index));

		Button btn_eP_actual_allocOfManpower_cancel = new Button(comp_eP_actual_allocOfManpower_AddPage, SWT.NONE);
		btn_eP_actual_allocOfManpower_cancel.setBounds(198, 213, 66, 27);
		toolkit.adapt(btn_eP_actual_allocOfManpower_cancel, true, true);
		btn_eP_actual_allocOfManpower_cancel.setText("Cancel");
		btn_eP_actual_allocOfManpower_cancel.addSelectionListener(new Cancel());

	}

	public class AllocOfManpowerAddNewItem extends SelectionAdapter {
		Table table_1;
		int index;

		public AllocOfManpowerAddNewItem(Table table_1, int index) {
			this.table_1 = table_1;
			this.index = index;
		}

		public void widgetSelected(SelectionEvent e) {
			String[] manPowerArray = new String[4];
			if (!task.getText().isEmpty()) {
				manPowerArray[0] = task.getText();
			}
			if (!assignedTo.getText().isEmpty()) {
				manPowerArray[1] = assignedTo.getText();
				manPowerArray[2] = String.format("%02d",dueDate.getYear()) + "-"
				        + String.format("%02d",dueDate.getMonth()+1)  + "-" 
						+ String.format("%02d",dueDate.getDay()) ; // getMonth() returns 0 to 11
				if (done.getSelection()) manPowerArray[3] = "Yes";
				else manPowerArray[3] = "No";
			if (!task.getText().isEmpty() && !assignedTo.getText().isEmpty()) {
				/* update the manpower allocation table */
				TableItem item = table_1.getItem(index);
				for (int i = 0; i < 4; i++) {
					item.setText(i, manPowerArray[i]);
				}
				
				/* update the database */
				DatabaseReader db = new DatabaseReader();
				ManpowerAllocation newAlloc = db.getManpowerAllocation(event).get(index);
				newAlloc.setTaskDescription(manPowerArray[0]);
				newAlloc.setAssignedTo(manPowerArray[1]);
				newAlloc.setDate(Date.parseDate(manPowerArray[2]));
				newAlloc.setDone(manPowerArray[3]=="Yes");
				db.updateManpowerAllocation(newAlloc);

				getParent().dispose();
				}
			}
		}
	}
	public class Cancel extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
