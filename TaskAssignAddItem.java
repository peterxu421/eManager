import java.sql.Date;

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

public class TaskAssignAddItem extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text textTask;
	private Text textAssignedTo;
	private Event event;
	private DateTime dateTime;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public TaskAssignAddItem(Composite parent, int style, Table table_1, Event event) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		setLayout(null);
		this.event=event;

		Composite compositeTaskAssignAdd = new Composite(this, SWT.NONE);
		compositeTaskAssignAdd.setBounds(10, 10, 372, 290);
		compositeTaskAssignAdd.setLayout(null);
		toolkit.adapt(compositeTaskAssignAdd);
		toolkit.paintBordersFor(compositeTaskAssignAdd);

		Label lblTask = new Label(compositeTaskAssignAdd, SWT.NONE);
		lblTask.setBounds(35, 34, 27, 17);
		toolkit.adapt(lblTask, true, true);
		lblTask.setText("Task");

		Label lblAssignedTo = new Label(compositeTaskAssignAdd, SWT.NONE);
		lblAssignedTo.setBounds(33, 77, 72, 17);
		toolkit.adapt(lblAssignedTo, true, true);
		lblAssignedTo.setText("Assigned To");

		Label lblDateDue = new Label(compositeTaskAssignAdd, SWT.NONE);
		lblDateDue.setBounds(35, 126, 54, 17);
		toolkit.adapt(lblDateDue, true, true);
		lblDateDue.setText("Date Due");

		textTask = new Text(compositeTaskAssignAdd, SWT.BORDER);
		textTask.setBounds(152, 31, 125, 23);
		toolkit.adapt(textTask, true, true);

		textAssignedTo = new Text(compositeTaskAssignAdd, SWT.BORDER);
		textAssignedTo.setBounds(152, 74, 125, 23);
		toolkit.adapt(textAssignedTo, true, true);

		Button btnAddItem = new Button(compositeTaskAssignAdd, SWT.NONE);
		btnAddItem.addSelectionListener(new TaskAssignAddNewItem(table_1));
		btnAddItem.setBounds(79, 208, 66, 27);
		toolkit.adapt(btnAddItem, true, true);
		btnAddItem.setText("Add Item");

		Button btnCancel = new Button(compositeTaskAssignAdd, SWT.NONE);
		btnCancel.addSelectionListener(new TaskAssignCancel());
		btnCancel.setBounds(208, 208, 66, 27);
		toolkit.adapt(btnCancel, true, true);
		btnCancel.setText("Cancel");
		
		dateTime = new DateTime(compositeTaskAssignAdd, SWT.BORDER);
		dateTime.setBounds(152, 126, 88, 24);
		toolkit.adapt(dateTime);
		toolkit.paintBordersFor(dateTime);

	}

	public class TaskAssignAddNewItem extends SelectionAdapter {
		Table table_1;

		public TaskAssignAddNewItem(Table table_1) {
			this.table_1 = table_1;
		}

		public void widgetSelected(SelectionEvent e) {
			String[] taskAssignArray = new String[3];
			if (!textTask.getText().isEmpty()) {
				taskAssignArray[0] = textTask.getText();
			}
			if (!textAssignedTo.getText().isEmpty()) {
				taskAssignArray[1] = textAssignedTo.getText();
			}
			
			if (!textTask.getText().isEmpty() && !textAssignedTo.getText().isEmpty()) {
				TableItem item = new TableItem(table_1, SWT.NULL);
				for (int i = 0; i < 2; i++) {
					item.setText(i, taskAssignArray[i]);
				}
				//Update the data base.
				DatabaseReader dbReader= new DatabaseReader();
				Date date = new Date(0);
				date.setYear(dateTime.getYear()-1900);
				date.setMonth(dateTime.getMonth());
				date.setDate(dateTime.getDay());
				item.setText(2,date.toString());
				//Task task = new Task(taskAssignArray[0],taskAssignArray[1], date);
				//dbReader.insertTask(event, task);
				getParent().dispose();
			}
		}
	}
	public class TaskAssignCancel extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
