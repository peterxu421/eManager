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
import org.eclipse.swt.widgets.DateTime;


public class AddMeetingPage extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private final Text meetingDetails;
	private final DateTime date;
	private final DateTime time;
	private final Button done;
	
	private Event event;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AddMeetingPage(Composite parent, int style, Table table, Event event) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.event = event;
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
		
		meetingDetails = new Text(composite, SWT.BORDER);
		meetingDetails.setBounds(149, 71, 192, 21);
		toolkit.adapt(meetingDetails, true, true);
		if(index >=0 && index < table.getItemCount())
			meetingDetails.setText(table.getItem(index).getText(0));
		
		Label lblDate = new Label(composite, SWT.NONE);
		lblDate.setBounds(108, 118, 27, 15);
		toolkit.adapt(lblDate, true, true);
		lblDate.setText("Date:");
		
		date = new DateTime(composite, SWT.BORDER);
		date.setBounds(149, 109, 100, 24);
		toolkit.adapt(date);
		toolkit.paintBordersFor(date);
		if(index >=0 && index < table.getItemCount()){
			Date dt = Date.parseDate(table.getItem(index).getText(1));
			date.setYear(dt.getYear());
			date.setMonth(dt.getMonth()-1); 
			date.setDay(dt.getDay());
		}
			
		Label lblTime = new Label(composite, SWT.NONE);
		lblTime.setBounds(108, 155, 30, 15);
		toolkit.adapt(lblTime, true, true);
		lblTime.setText("Time:");
		
		time = new DateTime(composite, SWT.BORDER | SWT.TIME);
		time.setBounds(149, 146, 100, 24);
		toolkit.adapt(time);
		toolkit.paintBordersFor(time);
		if(index >=0 && index < table.getItemCount()){
			Time tm = Time.parseTime(table.getItem(index).getText(2));
			time.setHours(tm.getHour());
			time.setMinutes(tm.getMinute());
			time.setSeconds(tm.getSecond());
		}
		else {
			time.setHours(0);
			time.setMinutes(0);
			time.setSeconds(0);
		}
			
		
		Label lblDone = new Label(composite, SWT.NONE);
		lblDone.setBounds(104, 188, 31, 15);
		toolkit.adapt(lblDone, true, true);
		lblDone.setText("Done:");
		
		done = new Button(composite, SWT.CHECK | SWT.CENTER);
		done.setBounds(149, 187, 85, 16);
		toolkit.adapt(done, true, true);
		if(index >=0 && index < table.getItemCount())
			done.setSelection(table.getItem(index).getText(3) == "Yes");
		
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
			String _meetingDetails = "";
			String _time = "";
			String _date = "";
			String _done = "";
			if (!meetingDetails.getText().isEmpty()) {
				_meetingDetails = meetingDetails.getText();
			}
			
			_time = String.format("%02d",time.getHours()) + ":"
			        + String.format("%02d",time.getMinutes())  + ":" 
					+ String.format("%02d",time.getSeconds()) ;
			_date = String.format("%04d",date.getYear())  + "-"
			        + String.format("%02d", date.getMonth()+1)  + "-" 
					+ String.format("%02d",date.getDay()) ;  // getMonth() + 1 since getMonth() returns 0 to 11
			if (done.getSelection()) _done = "Yes";
			else _done = "No";
           
			if (!meetingDetails.getText().isEmpty()) {
				if(localIndex ==-1){
					/* update the meeting table */
					TableItem item = new TableItem(localTable, SWT.NULL);
					item.setText(0, _meetingDetails);
					item.setText(1, _date);
					item.setText(2, _time);
					item.setText(3, _done.toString());
					
					
					/* update the database */
					DatabaseReader db = new DatabaseReader();
					Meeting newMeeting = new Meeting(_meetingDetails, Date.parseDate(_date), Time.parseTime(_time), _done == "Yes");
					db.insertMeeting(event, newMeeting);	
					
					getParent().dispose();
				}
				else {
					/* update the meeting table */
					TableItem item = localTable.getItem(localIndex);
					item.setText(0, _meetingDetails);
					item.setText(1, _date);
					item.setText(2, _time);
					item.setText(3, _done.toString());
					
					
					/* update the database */
					DatabaseReader db = new DatabaseReader();
					Meeting newMeeting = db.getMeetings(event).get(localIndex);
					newMeeting.setMeetingDetails(_meetingDetails);
					newMeeting.setDate(Date.parseDate(_date));
					newMeeting.setTime(Time.parseTime(_time));
					newMeeting.setDone(_done == "Yes");
					db.updateMeeting(newMeeting);
					
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

