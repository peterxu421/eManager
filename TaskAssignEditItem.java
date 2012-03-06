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
import org.eclipse.swt.widgets.DateTime;


public class TaskAssignEditItem extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text textTask;
	private Text textAssignTo;
	private Task task;
	private DateTime dateTime;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TaskAssignEditItem(Composite parent, int style, Table table, int index, Task task) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.task = task;
		
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
		
		Label lblDateDue = new Label(compositeCommitteeEdit, SWT.NONE);
		lblDateDue.setText("Date Due");
		lblDateDue.setBounds(35, 124, 54, 17);
		toolkit.adapt(lblDateDue, true, true);
		
		textTask = new Text(compositeCommitteeEdit, SWT.BORDER);
		textTask.setBounds(152, 31, 125, 23);
		toolkit.adapt(textTask, true, true);
		textTask.setText(table.getItem(index).getText(0));
		
		textAssignTo = new Text(compositeCommitteeEdit, SWT.BORDER);
		textAssignTo.setBounds(152, 74, 125, 23);
		toolkit.adapt(textAssignTo, true, true);
		textAssignTo.setText(table.getItem(index).getText(1));
		
		dateTime = new DateTime(compositeCommitteeEdit, SWT.BORDER);
		dateTime.setBounds(152, 124, 88, 24);
		toolkit.adapt(dateTime);
		toolkit.paintBordersFor(dateTime);
		
		Button btnEdit = new Button(compositeCommitteeEdit, SWT.NONE);
		btnEdit.addSelectionListener(new TaskAssignEditOldItem(table, index));
		btnEdit.setText("Edit Item");
		btnEdit.setBounds(87, 183, 66, 27);
		toolkit.adapt(btnEdit, true, true);
		
		Button btnCancel = new Button(compositeCommitteeEdit, SWT.NONE);
		btnCancel.addSelectionListener(new TaskAssignCancel2());
		btnCancel.setText("Cancel");
		btnCancel.setBounds(211, 183, 66, 27);
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
				task.setTaskDesciption(taskAssignArray[0]);
			}
			if (!textAssignTo.getText().isEmpty()) {
				taskAssignArray[1] = textAssignTo.getText();
				table.getItem(index).setText(1, taskAssignArray[1]);
				task.setAssignedTo(taskAssignArray[1]);
			}
			if (!textTask.getText().isEmpty() && !textAssignTo.getText().isEmpty()) {
				Date dateDue = new Date(dateTime.getYear(), dateTime.getMonth(), dateTime.getDay());
				table.getItem(index).setText(2,dateDue.toString());
				DatabaseReader dbReader = new DatabaseReader();
				task.setDateDue(dateDue);
				dbReader.updateTask(task);
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
