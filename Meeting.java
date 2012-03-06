import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;



public class Meeting extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private final Text MeetingDetails;
	private final Text Date;
	private final Text Time;
	private final Text Done;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Meeting(Composite parent, int style, Table table) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		setLayout(new FormLayout());
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(null);
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(0, 277);
		fd_composite.right = new FormAttachment(0, 440);
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(0, 10);
		composite.setLayoutData(fd_composite);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		int index = table.getSelectionIndex();
		
		Label lblAddAMeeting = new Label(composite, SWT.NONE);
		lblAddAMeeting.setBounds(172, 26, 44, 15);
		toolkit.adapt(lblAddAMeeting, true, true);
		lblAddAMeeting.setText("Meeting");
		
		Label lblMeetingDetails = new Label(composite, SWT.NONE);
		lblMeetingDetails.setBounds(50, 74, 85, 15);
		toolkit.adapt(lblMeetingDetails, true, true);
		lblMeetingDetails.setText("Meeting Details:");
		
		MeetingDetails = new Text(composite, SWT.BORDER);
		MeetingDetails.setBounds(149, 71, 192, 21);
		toolkit.adapt(MeetingDetails, true, true);
		if(index >=0 && index < table.getItemCount())
			MeetingDetails.setText(table.getItem(index).getText(0));
		
		Label lblDate = new Label(composite, SWT.NONE);
		lblDate.setBounds(108, 118, 27, 15);
		toolkit.adapt(lblDate, true, true);
		lblDate.setText("Date:");
		
		Date = new Text(composite, SWT.BORDER);
		Date.setBounds(149, 112, 73, 21);
		toolkit.adapt(Date, true, true);
		if(index >=0 && index < table.getItemCount())
			Date.setText(table.getItem(index).getText(1));
		
		Label lblTime = new Label(composite, SWT.NONE);
		lblTime.setBounds(108, 155, 30, 15);
		toolkit.adapt(lblTime, true, true);
		lblTime.setText("Time:");
		
		Time = new Text(composite, SWT.BORDER);
		Time.setBounds(149, 149, 73, 21);
		toolkit.adapt(Time, true, true);
		if(index >=0 && index < table.getItemCount())
			Time.setText(table.getItem(index).getText(2));
		
		Label lblDone = new Label(composite, SWT.NONE);
		lblDone.setBounds(104, 188, 31, 15);
		toolkit.adapt(lblDone, true, true);
		lblDone.setText("Done:");
		
		Done = new Text(composite, SWT.BORDER);
		Done.setBounds(149, 182, 73, 21);
		toolkit.adapt(Done, true, true);
		if(index >=0 && index < table.getItemCount())
			Done.setText(table.getItem(index).getText(3));
		
		table.deselect(index);  // deselect 
		
		Button btnAdd = new Button(composite, SWT.NONE);
		btnAdd.setBounds(104, 224, 72, 25);
		toolkit.adapt(btnAdd, true, true);
		btnAdd.setText("Add");
		btnAdd.addSelectionListener(new Add(table, index));
		
		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setBounds(188, 224, 73, 25);
		toolkit.adapt(btnCancel, true, true);
		btnCancel.setText("Cancel");
		btnCancel.addSelectionListener(new Cancel());
	}
	
	class Add extends SelectionAdapter {
		Table localTable;
		int localIndex;
		public Add(Table table, int index){
			localTable = table;
			localIndex = index;
		}
		public void widgetSelected(SelectionEvent e) {
			String meetingDetails = "";
			String time = "";
			String date = "";
			String done = "";
			if (!MeetingDetails.getText().isEmpty()) {
				meetingDetails = MeetingDetails.getText();
			}
			if (!Date.getText().isEmpty()) {
				date = Date.getText();
			}
			if (!Done.getText().isEmpty()) {
				done = Done.getText();
			}
			if (!Time.getText().isEmpty()) {
			    time = Time.getText();
			}
			if (!MeetingDetails.getText().isEmpty()
					&& !Date.getText().isEmpty()
					&& !Time.getText().isEmpty()
					&& !Done.getText().isEmpty()) {
				if(localIndex ==-1){
					TableItem item = new TableItem(localTable, SWT.NULL);
					item.setText(0, meetingDetails);
					item.setText(1, date);
					item.setText(2, time);
					item.setText(3, done);
					getParent().dispose();
				}
				else {
					TableItem item = localTable.getItem(localIndex);
					item.setText(0, meetingDetails);
					item.setText(1, date);
					item.setText(2, time);
					item.setText(3, done);
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

