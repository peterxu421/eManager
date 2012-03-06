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
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;


public class EventPlanning_Meeting extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table table;
	private Event event;
	private ArrayList<MeetingClass> meetingList;
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
		FormLayout formLayout = new FormLayout();
		formLayout.marginRight = 30;
		formLayout.marginBottom = 30;
		setLayout(formLayout);
		
		this.event = event;
		DatabaseReader db= new DatabaseReader();
		this.meetingList=db.getMeetings(event);
		
		Composite composite = new Composite(this, SWT.NONE);
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(0);
		fd_composite.bottom = new FormAttachment(0, 286);
		fd_composite.right = new FormAttachment(0, 454);
		composite.setLayoutData(fd_composite);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 30, 339, 246);
		toolkit.adapt(table);
		toolkit.paintBordersFor(table);
		
		TableColumn tableColumn_MeetingDetails = new TableColumn(table, SWT.CENTER);
		tableColumn_MeetingDetails.setWidth(175);
		tableColumn_MeetingDetails.setText("Meeting Details");
		
		TableColumn tableColumn_Date = new TableColumn(table, SWT.CENTER);
		tableColumn_Date.setWidth(63);
		tableColumn_Date.setText("Date");
		
		TableColumn tableColumn_Time = new TableColumn(table, SWT.CENTER);
		tableColumn_Time.setWidth(48);
		tableColumn_Time.setText("Time");
		
		TableColumn tableColumn_Done = new TableColumn(table, SWT.CENTER);
		tableColumn_Done.setWidth(49);
		tableColumn_Done.setText("Done");
		
		TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		toolkit.adapt(tableCursor);
		toolkit.paintBordersFor(tableCursor);
		
		
		Button Add = new Button(composite, SWT.NONE);
		Add.setText("Add");
		Add.setBounds(364, 30, 75, 25);
		toolkit.adapt(Add, true, true);
		Add.addSelectionListener(new Add());
		
		Button Delete = new Button(composite, SWT.NONE);
		Delete.setText("Delete");
		Delete.setBounds(364, 61, 75, 25);
		toolkit.adapt(Delete, true, true);
		Delete.addSelectionListener(new Delete());
		
		Button Edit = new Button(composite, SWT.NONE);
		Edit.setText("Edit");
		Edit.setBounds(364, 92, 75, 25);
		toolkit.adapt(Edit, true, true);
		Edit.addSelectionListener(new Edit());
	
		fillTable();
	}
	public void fillTable()
	{
		TableItem item;

		for (int i = 0; i < meetingList.size(); i++) {
			item = new TableItem(table, SWT.NONE);
			item.setText(0, meetingList.get(i).getMeetingDetails());
			item.setText(1, meetingList.get(i).getDate().toString());
			item.setText(2, meetingList.get(i).getTime().toString());
			item.setText(3, meetingList.get(i).isDone()?"Done":"Undone");
		}
	}
	class Add extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if(table.getSelectionCount()==0){
				Shell add_meeting_shell = new Shell(getDisplay());
				Meeting add_meeting_page = new Meeting(add_meeting_shell, SWT.None, table);
				add_meeting_page.pack();
				add_meeting_shell.pack();
				add_meeting_shell.open();
			}
		}
	}
	
	class Delete extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e){
			if (table.getItemCount() != 0){
				int index = table.getSelectionIndex();
				if(index >=0 && index < table.getItemCount()){
					table.remove(index);
				}
			}
		}
	}
	class Edit extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if(table.getSelectionCount()!=0){
				Shell edit_meeting_shell = new Shell(getDisplay());
				Meeting edit_meeting_page = new Meeting(edit_meeting_shell, SWT.None, table);
				edit_meeting_page.pack();
				edit_meeting_shell.pack();
				edit_meeting_shell.open();
			}
		}
	}

}
