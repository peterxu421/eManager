import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;


public class AllocOfManpowerEditItem extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text text_eP_actual_AllocOfManpower_task;
	private Text text_eP_actual_AllocOfManpower_assignedTo;
	private Text text_eP_actual_AllocOfManpower_date;
	private Text text_eP_actual_AllocOfManpower_dateDue;
	private Text text_eP_actual_AllocOfManpower_done;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AllocOfManpowerEditItem(Composite parent, int style, Table table, int index) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite comp_eP_actual_AllocOfManpower_EditPage = new Composite(this, SWT.NONE);
		comp_eP_actual_AllocOfManpower_EditPage.setLayout(null);
		comp_eP_actual_AllocOfManpower_EditPage.setBounds(34, 10, 372, 290);
		toolkit.adapt(comp_eP_actual_AllocOfManpower_EditPage);
		toolkit.paintBordersFor(comp_eP_actual_AllocOfManpower_EditPage);
		
		Label lbl_eP_actual_AllocOfManpower_task = new Label(comp_eP_actual_AllocOfManpower_EditPage, SWT.NONE);
		lbl_eP_actual_AllocOfManpower_task.setText("Task");
		lbl_eP_actual_AllocOfManpower_task.setBounds(35, 34, 54, 17);
		toolkit.adapt(lbl_eP_actual_AllocOfManpower_task, true, true);
		
		Label lbl_eP_actual_AllocOfManpower_assignedTo = new Label(comp_eP_actual_AllocOfManpower_EditPage, SWT.NONE);
		lbl_eP_actual_AllocOfManpower_assignedTo.setText("Assigned To");
		lbl_eP_actual_AllocOfManpower_assignedTo.setBounds(33, 72, 72, 17);
		toolkit.adapt(lbl_eP_actual_AllocOfManpower_assignedTo, true, true);
		
		Label lbl_eP_actual_AllocOfManpower_date = new Label(comp_eP_actual_AllocOfManpower_EditPage, SWT.NONE);
		lbl_eP_actual_AllocOfManpower_date.setText("Date Assigned");
		lbl_eP_actual_AllocOfManpower_date.setBounds(35, 111, 78, 17);
		toolkit.adapt(lbl_eP_actual_AllocOfManpower_date, true, true);
		
		Label lbl_eP_actual_AllocOfManpower_dateDue = new Label(comp_eP_actual_AllocOfManpower_EditPage, SWT.NONE);
		lbl_eP_actual_AllocOfManpower_dateDue.setText("Date Due");
		lbl_eP_actual_AllocOfManpower_dateDue.setBounds(35, 150, 54, 17);
		toolkit.adapt(lbl_eP_actual_AllocOfManpower_dateDue, true, true);
		
		Label lbl_eP_actual_AllocOfManpower_done = new Label(comp_eP_actual_AllocOfManpower_EditPage, SWT.NONE);
		lbl_eP_actual_AllocOfManpower_done.setText("Done");
		lbl_eP_actual_AllocOfManpower_done.setBounds(35, 188, 54, 17);
		toolkit.adapt(lbl_eP_actual_AllocOfManpower_done, true, true);
		
		text_eP_actual_AllocOfManpower_task = new Text(comp_eP_actual_AllocOfManpower_EditPage, SWT.BORDER);
		text_eP_actual_AllocOfManpower_task.setBounds(152, 31, 125, 23);
		toolkit.adapt(text_eP_actual_AllocOfManpower_task, true, true);
		text_eP_actual_AllocOfManpower_task.setText(table.getItem(index).getText(0));
		
		text_eP_actual_AllocOfManpower_assignedTo = new Text(comp_eP_actual_AllocOfManpower_EditPage, SWT.BORDER);
		text_eP_actual_AllocOfManpower_assignedTo.setBounds(152, 69, 125, 23);
		toolkit.adapt(text_eP_actual_AllocOfManpower_assignedTo, true, true);
		text_eP_actual_AllocOfManpower_assignedTo.setText(table.getItem(index).getText(1));
		
		text_eP_actual_AllocOfManpower_date = new Text(comp_eP_actual_AllocOfManpower_EditPage, SWT.BORDER);
		text_eP_actual_AllocOfManpower_date.setBounds(152, 108, 125, 23);
		toolkit.adapt(text_eP_actual_AllocOfManpower_date, true, true);
		text_eP_actual_AllocOfManpower_date.setText(table.getItem(index).getText(2));
		
		text_eP_actual_AllocOfManpower_dateDue = new Text(comp_eP_actual_AllocOfManpower_EditPage, SWT.BORDER);
		text_eP_actual_AllocOfManpower_dateDue.setBounds(152, 147, 125, 23);
		toolkit.adapt(text_eP_actual_AllocOfManpower_dateDue, true, true);
		text_eP_actual_AllocOfManpower_dateDue.setText(table.getItem(index).getText(3));
		
		text_eP_actual_AllocOfManpower_done = new Text(comp_eP_actual_AllocOfManpower_EditPage, SWT.BORDER);
		text_eP_actual_AllocOfManpower_done.setText(table.getItem(index).getText(4));
		text_eP_actual_AllocOfManpower_done.setBounds(152, 185, 125, 23);
		toolkit.adapt(text_eP_actual_AllocOfManpower_done, true, true);
		
		Button btn_eP_actual_AllocOfManpower_edit = new Button(comp_eP_actual_AllocOfManpower_EditPage, SWT.NONE);
		btn_eP_actual_AllocOfManpower_edit.addSelectionListener(new AllocOfManpowerEditOldItem(table, index));
		btn_eP_actual_AllocOfManpower_edit.setText("Edit");
		btn_eP_actual_AllocOfManpower_edit.setBounds(87, 219, 66, 27);
		toolkit.adapt(btn_eP_actual_AllocOfManpower_edit, true, true);
		
		Button btn_eP_actual_AllocOfManpower_cancel = new Button(comp_eP_actual_AllocOfManpower_EditPage, SWT.NONE);
		btn_eP_actual_AllocOfManpower_cancel.addSelectionListener(new AllocOfManpowerCancel2());
		btn_eP_actual_AllocOfManpower_cancel.setText("Cancel");
		btn_eP_actual_AllocOfManpower_cancel.setBounds(211, 219, 66, 27);
		toolkit.adapt(btn_eP_actual_AllocOfManpower_cancel, true, true);

	}
	
	public class AllocOfManpowerEditOldItem extends SelectionAdapter{
		Table table;
		int index;

		public AllocOfManpowerEditOldItem(Table table, int index) {
			this.table = table;
			this.index=index;
		}

		public void widgetSelected(SelectionEvent e) {
			String[] itineraryArray = new String[5];
			if (!text_eP_actual_AllocOfManpower_task.getText().isEmpty()) {
				itineraryArray[0] = text_eP_actual_AllocOfManpower_task.getText();
				table.getItem(index).setText(0, itineraryArray[0]);
			}
			if (!text_eP_actual_AllocOfManpower_assignedTo.getText().isEmpty()) {
				itineraryArray[1] = text_eP_actual_AllocOfManpower_assignedTo.getText();
				table.getItem(index).setText(1, itineraryArray[1]);
			}
			if (!text_eP_actual_AllocOfManpower_date.getText().isEmpty()) {
				itineraryArray[2] = text_eP_actual_AllocOfManpower_date.getText();
				table.getItem(index).setText(2, itineraryArray[2]);
			}
			if (!text_eP_actual_AllocOfManpower_dateDue.getText().isEmpty()) {
				itineraryArray[3] = text_eP_actual_AllocOfManpower_dateDue.getText();
				table.getItem(index).setText(3, itineraryArray[3]);
			}
			if (!text_eP_actual_AllocOfManpower_dateDue.getText().isEmpty()) {
				itineraryArray[4] = text_eP_actual_AllocOfManpower_done.getText();
				table.getItem(index).setText(4, itineraryArray[4]);
			}
			if (!text_eP_actual_AllocOfManpower_task.getText().isEmpty() && !text_eP_actual_AllocOfManpower_assignedTo.getText().isEmpty()
					&& !text_eP_actual_AllocOfManpower_date.getText().isEmpty()
					&& !text_eP_actual_AllocOfManpower_dateDue.getText().isEmpty()
					&& !text_eP_actual_AllocOfManpower_done.getText().isEmpty()) {
				getParent().dispose();
			}
		}
	}
	
	public class AllocOfManpowerCancel2 extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
