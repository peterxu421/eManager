import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;


public class eP_facilitator_AllocOfManpower extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table table_eP_facilitator_allocOfManpower;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public eP_facilitator_AllocOfManpower(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite comp_eP_facilitator_allocOfManpower = new Composite(this, SWT.NONE);
		comp_eP_facilitator_allocOfManpower.setBounds(10, 10, 523, 267);
		toolkit.adapt(comp_eP_facilitator_allocOfManpower);
		toolkit.paintBordersFor(comp_eP_facilitator_allocOfManpower);
		
		table_eP_facilitator_allocOfManpower = new Table(comp_eP_facilitator_allocOfManpower, SWT.BORDER | SWT.FULL_SELECTION);
		table_eP_facilitator_allocOfManpower.setLinesVisible(true);
		table_eP_facilitator_allocOfManpower.setHeaderVisible(true);
		table_eP_facilitator_allocOfManpower.setBounds(10, 10, 418, 255);
		toolkit.adapt(table_eP_facilitator_allocOfManpower);
		toolkit.paintBordersFor(table_eP_facilitator_allocOfManpower);
		
		TableColumn col_eP_facilitator_allocOfManpower_task = new TableColumn(table_eP_facilitator_allocOfManpower, SWT.CENTER);
		col_eP_facilitator_allocOfManpower_task.setWidth(134);
		col_eP_facilitator_allocOfManpower_task.setText("Task");
		
		TableColumn col_eP_facilitator_allocOfManpower_assignedTo = new TableColumn(table_eP_facilitator_allocOfManpower, SWT.NONE);
		col_eP_facilitator_allocOfManpower_assignedTo.setWidth(71);
		col_eP_facilitator_allocOfManpower_assignedTo.setText("Assigned To");
		
		TableColumn col_eP_facilitator_allocOfManpower_dateAssigned = new TableColumn(table_eP_facilitator_allocOfManpower, SWT.NONE);
		col_eP_facilitator_allocOfManpower_dateAssigned.setWidth(85);
		col_eP_facilitator_allocOfManpower_dateAssigned.setText("Date Assigned");
		
		TableColumn col_eP_facilitator_allocOfManpower_dateDue = new TableColumn(table_eP_facilitator_allocOfManpower, SWT.NONE);
		col_eP_facilitator_allocOfManpower_dateDue.setWidth(60);
		col_eP_facilitator_allocOfManpower_dateDue.setText("Date Due");
		
		TableColumn col_eP_facilitator_allocOfManpower_done = new TableColumn(table_eP_facilitator_allocOfManpower, SWT.NONE);
		col_eP_facilitator_allocOfManpower_done.setWidth(62);
		col_eP_facilitator_allocOfManpower_done.setText("Done");
		
		Button btn_eP_facilitator_allocOfManpower_print = new Button(comp_eP_facilitator_allocOfManpower, SWT.NONE);
		btn_eP_facilitator_allocOfManpower_print.setText("Print");
		btn_eP_facilitator_allocOfManpower_print.setBounds(433, 10, 80, 27);
		toolkit.adapt(btn_eP_facilitator_allocOfManpower_print, true, true);

	}

}
