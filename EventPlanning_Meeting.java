import java.util.ArrayList;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;


public class EventPlanning_Meeting extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	
	private Event event;
	private ArrayList<Meeting> meetingList;
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public EventPlanning_Meeting(Composite parent, int style, Event event) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.event = event;
		
		FormLayout formLayout = new FormLayout();
		formLayout.marginRight = 30;
		formLayout.marginBottom = 30;
		setLayout(formLayout);
		
		Composite composite = new Composite(this, SWT.NONE);
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(0);
		fd_composite.bottom = new FormAttachment(0, 298);
		fd_composite.right = new FormAttachment(0, 682);
		composite.setLayoutData(fd_composite);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 10, 521, 278);
		toolkit.adapt(table);
		toolkit.paintBordersFor(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnMeetingDetails = new TableColumn(table, SWT.CENTER);
		tblclmnMeetingDetails.setWidth(268);
		tblclmnMeetingDetails.setText("Meeting Details");
		
		TableColumn tblclmnDate = new TableColumn(table, SWT.CENTER);
		tblclmnDate.setWidth(103);
		tblclmnDate.setText("Date");
		
		TableColumn tblclmnTime = new TableColumn(table, SWT.CENTER);
		tblclmnTime.setWidth(103);
		tblclmnTime.setText("Time");
		
		TableColumn tblclmnDone = new TableColumn(table, SWT.CENTER);
		tblclmnDone.setWidth(48);
		tblclmnDone.setText("Done");
		
		
		Button Add = new Button(composite, SWT.NONE);
		Add.setText("Add");
		Add.setBounds(564, 31, 75, 25);
		toolkit.adapt(Add, true, true);
		Add.addSelectionListener(new Add());
		
		Button Delete = new Button(composite, SWT.NONE);
		Delete.setText("Delete");
		Delete.setBounds(564, 81, 75, 25);
		toolkit.adapt(Delete, true, true);
		Delete.addSelectionListener(new Delete());
		
		Button Edit = new Button(composite, SWT.NONE);
		Edit.setText("Edit");
		Edit.setBounds(564, 130, 75, 25);
		toolkit.adapt(Edit, true, true);
		Edit.addSelectionListener(new Edit());
	
        importMeetingData();
	}
	
	public void importMeetingData(){
		DatabaseReader db = new DatabaseReader();
		meetingList = db.getMeetings(event);
		
		/* update the meeting table */
		for(int i=0; i<meetingList.size(); i++){
			TableItem temp = new TableItem(table, SWT.NULL);
			temp.setText(0, meetingList.get(i).getMeetingDetails());
			temp.setText(1, meetingList.get(i).getDate().toString());
			temp.setText(2, meetingList.get(i).getTime().toString());
			if(meetingList.get(i).isDone() == true)
				temp.setText(3, "Yes");
			else temp.setText(3, "No");
		}
	}
	

	class Add extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if(table.getSelectionCount()==0){
				Shell add_meeting_shell = new Shell(getDisplay());
				AddMeetingPage add_meeting_page = new AddMeetingPage(add_meeting_shell, SWT.None, table, event, meetingList);
				add_meeting_page.pack();
				add_meeting_shell.pack();
				add_meeting_shell.open();
			}
			else table.deselectAll(); // get rid of redundant selection
		}
	}
	
	class Delete extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e){
			if (table.getItemCount() != 0){
				int index = table.getSelectionIndex();
				if(index >=0 && index < table.getItemCount()){
					/* update the database */
					DatabaseReader db = new DatabaseReader();
					db.deleteMeeting(meetingList.get(index));
					meetingList.remove(index);
					/* update the meeting table */
					table.remove(index);
				}
			}
		}
	}
	class Edit extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if(table.getSelectionCount()!=0){
				Shell edit_meeting_shell = new Shell(getDisplay());
				AddMeetingPage edit_meeting_page = new AddMeetingPage(edit_meeting_shell, SWT.None, table, event, meetingList);
				edit_meeting_page.pack();
				edit_meeting_shell.pack();
				edit_meeting_shell.open();
			}
		}
	}
}
