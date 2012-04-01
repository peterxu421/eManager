import java.util.ArrayList;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableItem;


public class eP_facilitator_AllocOfManpower extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table table_eP_facilitator_allocOfManpower;
	
	private ArrayList<ManpowerAllocation> manpowerList;
	private Event event;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public eP_facilitator_AllocOfManpower(Composite parent, int style, Event event) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.event=event;
		
		table_eP_facilitator_allocOfManpower = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table_eP_facilitator_allocOfManpower.setLinesVisible(true);
		table_eP_facilitator_allocOfManpower.setHeaderVisible(true);
		table_eP_facilitator_allocOfManpower.setBounds(10, 10, 550, 400);
		toolkit.adapt(table_eP_facilitator_allocOfManpower);
		toolkit.paintBordersFor(table_eP_facilitator_allocOfManpower);
		
		TableColumn col_eP_facilitator_allocOfManpower_task = new TableColumn(table_eP_facilitator_allocOfManpower, SWT.CENTER);
		col_eP_facilitator_allocOfManpower_task.setWidth(220);
		col_eP_facilitator_allocOfManpower_task.setText("Task");
		
		TableColumn col_eP_facilitator_allocOfManpower_assignedTo = new TableColumn(table_eP_facilitator_allocOfManpower, SWT.CENTER);
		col_eP_facilitator_allocOfManpower_assignedTo.setWidth(120);
		col_eP_facilitator_allocOfManpower_assignedTo.setText("Assigned To");
		
		TableColumn col_eP_facilitator_allocOfManpower_dateDue = new TableColumn(table_eP_facilitator_allocOfManpower, SWT.CENTER);
		col_eP_facilitator_allocOfManpower_dateDue.setWidth(120);
		col_eP_facilitator_allocOfManpower_dateDue.setText("Date Due");
		
		TableColumn col_eP_facilitator_allocOfManpower_done = new TableColumn(table_eP_facilitator_allocOfManpower, SWT.CENTER);
		col_eP_facilitator_allocOfManpower_done.setWidth(80);
		col_eP_facilitator_allocOfManpower_done.setText("Done");
		
		importManpowerAllocationData();

	}
	public void importManpowerAllocationData(){
		DatabaseReader db = new DatabaseReader();
		manpowerList = db.getManpowerAllocation(event);
		
		for(int i=0; i<manpowerList.size(); i++){
			TableItem temp = new TableItem(table_eP_facilitator_allocOfManpower, SWT.NULL);
			temp.setText(0, manpowerList.get(i).getTaskDescription());
		    temp.setText(1, manpowerList.get(i).getAssignedTo());
		    temp.setText(2, manpowerList.get(i).getDate().toString());
			if(manpowerList.get(i).isDone() == true)
				temp.setText(3, "true");
			else temp.setText(3, "false");
		}
	}
}
