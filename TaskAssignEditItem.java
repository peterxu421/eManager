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


public class TaskAssignEditItem extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text textTask;
	private Text textAssignTo;
	private Text textDate;
	private Text textDateDue;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TaskAssignEditItem(Composite parent, int style, Table table, int index) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite compositeCommitteeEdit = new Composite(this, SWT.NONE);
		compositeCommitteeEdit.setLayout(null);
		compositeCommitteeEdit.setBounds(10, 10, 372, 290);
		toolkit.adapt(compositeCommitteeEdit);
		toolkit.paintBordersFor(compositeCommitteeEdit);
		
		Label lblTask = new Label(compositeCommitteeEdit, SWT.NONE);
		lblTask.setText("Task");
		lblTask.setBounds(35, 34, 27, 17);
		toolkit.adapt(lblTask, true, true);
		
		Label lblAssignTo = new Label(compositeCommitteeEdit, SWT.NONE);
		lblAssignTo.setText("Assigned To");
		lblAssignTo.setBounds(33, 77, 72, 17);
		toolkit.adapt(lblAssignTo, true, true);
		
		Label lblDate = new Label(compositeCommitteeEdit, SWT.NONE);
		lblDate.setText("Date");
		lblDate.setBounds(35, 117, 27, 17);
		toolkit.adapt(lblDate, true, true);
		
		Label lblDateDue = new Label(compositeCommitteeEdit, SWT.NONE);
		lblDateDue.setText("Date Due");
		lblDateDue.setBounds(35, 162, 54, 17);
		toolkit.adapt(lblDateDue, true, true);
		
		textTask = new Text(compositeCommitteeEdit, SWT.BORDER);
		textTask.setBounds(152, 31, 125, 23);
		toolkit.adapt(textTask, true, true);
		textTask.setText(table.getItem(index).getText(0));
		
		textAssignTo = new Text(compositeCommitteeEdit, SWT.BORDER);
		textAssignTo.setBounds(152, 74, 125, 23);
		toolkit.adapt(textAssignTo, true, true);
		textAssignTo.setText(table.getItem(index).getText(1));
		
		textDate = new Text(compositeCommitteeEdit, SWT.BORDER);
		textDate.setBounds(152, 114, 125, 23);
		toolkit.adapt(textDate, true, true);
		textDate.setText(table.getItem(index).getText(2));
		
		textDateDue = new Text(compositeCommitteeEdit, SWT.BORDER);
		textDateDue.setBounds(152, 159, 125, 23);
		toolkit.adapt(textDateDue, true, true);
		textDateDue.setText(table.getItem(index).getText(3));
		
		Button btnEdit = new Button(compositeCommitteeEdit, SWT.NONE);
		btnEdit.addSelectionListener(new TaskAssignEditOldItem(table, index));
		btnEdit.setText("Edit Item");
		btnEdit.setBounds(87, 219, 66, 27);
		toolkit.adapt(btnEdit, true, true);
		
		Button btnCancel = new Button(compositeCommitteeEdit, SWT.NONE);
		btnCancel.addSelectionListener(new TaskAssignCancel2());
		btnCancel.setText("Cancel");
		btnCancel.setBounds(211, 219, 66, 27);
		toolkit.adapt(btnCancel, true, true);

	}
	
	public class TaskAssignEditOldItem extends SelectionAdapter{
		Table table;
		int index;

		public TaskAssignEditOldItem(Table table, int index) {
			this.table = table;
			this.index=index;
		}

		public void widgetSelected(SelectionEvent e) {
			String[] taskAssignArray = new String[4];
			if (!textTask.getText().isEmpty()) {
				taskAssignArray[0] = textTask.getText();
				table.getItem(index).setText(0, taskAssignArray[0]);
			}
			if (!textAssignTo.getText().isEmpty()) {
				taskAssignArray[1] = textAssignTo.getText();
				table.getItem(index).setText(1, taskAssignArray[1]);
			}
			if (!textDate.getText().isEmpty()) {
				taskAssignArray[2] = textDate.getText();
				table.getItem(index).setText(2, taskAssignArray[2]);
			}
			if (!textDateDue.getText().isEmpty()) {
				taskAssignArray[3] = textDateDue.getText();
				table.getItem(index).setText(3, taskAssignArray[3]);
			}
			if (!textTask.getText().isEmpty() && !textAssignTo.getText().isEmpty()
					&& !textDate.getText().isEmpty()
					&& !textDateDue.getText().isEmpty()) {
				getParent().dispose();
			}
		}
	}
	
	public class TaskAssignCancel2 extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
