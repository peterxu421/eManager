import java.util.ArrayList;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class EventPlanning_Meeting extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	private Event event;
	private ArrayList<Meeting> meetingList;
	private Table table;
	private String[] stringArray = { "Meeting Detail", "Date", "Time", "Done" };
	private int[] signatureArray = { MACRO.TEXTBIG, MACRO.DATE, MACRO.TIME,
			MACRO.CHECK };
	private int index;

	/**
	 * Create the composite.
	 * 
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

		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(0);
		fd_composite.bottom = new FormAttachment(0, 298);
		fd_composite.right = new FormAttachment(0, 682);
		this.setLayoutData(fd_composite);
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		setLayout(null);

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(0, 0, 543, 45);
		table.setBounds(10, 10, 550, 400);
		toolkit.adapt(table);
		toolkit.paintBordersFor(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnMeetingDetails = new TableColumn(table, SWT.CENTER);
		tblclmnMeetingDetails.setWidth(277);
		tblclmnMeetingDetails.setText("Meeting Details");

		TableColumn tblclmnDate = new TableColumn(table, SWT.CENTER);
		tblclmnDate.setWidth(103);
		tblclmnDate.setText("Date");

		TableColumn tblclmnTime = new TableColumn(table, SWT.CENTER);
		tblclmnTime.setWidth(103);
		tblclmnTime.setText("Time");

		TableColumn tblclmnDone = new TableColumn(table, SWT.CENTER);
		tblclmnDone.setWidth(61);
		tblclmnDone.setText("Done");

		Button Add = new Button(this, SWT.NONE);
		Add.setBounds(0, 0, 36, 27);
		Add.setText("Add");
		Add.setBounds(570, 10, 80, 40);
		toolkit.adapt(Add, true, true);
		Add.addSelectionListener(new Add());

		Button Delete = new Button(this, SWT.NONE);
		Delete.setBounds(0, 0, 49, 27);
		Delete.setText("Delete");
		Delete.setBounds(570, 60, 80, 40);
		toolkit.adapt(Delete, true, true);
		Delete.addSelectionListener(new Delete());

		Button Edit = new Button(this, SWT.NONE);
		Edit.setBounds(0, 0, 34, 27);
		Edit.setText("Edit");
		Edit.setBounds(570, 110, 80, 40);
		toolkit.adapt(Edit, true, true);
		Edit.addSelectionListener(new Edit());

		// Update the table.
		importMeetingData();
	}

	public void importMeetingData() {
		DatabaseReader db = new DatabaseReader();
		meetingList = db.getMeetings(event);

		/* update the meeting table */
		for (int i = 0; i < meetingList.size(); i++) {
			TableItem temp = new TableItem(table, SWT.NULL);
			temp.setText(0, meetingList.get(i).getMeetingDetails());
			temp.setText(1, meetingList.get(i).getDate().toString());
			temp.setText(2, meetingList.get(i).getTime().toString());
			if (meetingList.get(i).isDone() == true)
				temp.setText(3, "true");
			else
				temp.setText(3, "false");
		}
	}

	class Add extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (table.getSelectionCount() == 0) {
				Shell add_meeting_shell = new Shell(getDisplay(),
						SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
				AbstractAdd add_meeting_page = new AbstractAdd(
						add_meeting_shell, SWT.None, stringArray,
						signatureArray, table) {
					public void onSubmit() {
						// insert to database
						String[] tempList = getStringList();
						Date date = new Date(tempList[1]);
						Time time = new Time(tempList[2]);
						boolean isDone = Boolean.parseBoolean(tempList[3]);
						Meeting meeting = new Meeting(tempList[0], date, time,
								isDone);
						db.insertMeeting(event, meeting);
						meetingList.add(meeting);
						// update the table
						TableItem item = new TableItem(table, SWT.None);
						for (int i = 0; i < stringArray.length; i++) {
							item.setText(i, tempList[i]);
						}
					}
				};

				add_meeting_page.pack();
				add_meeting_shell.pack();
				add_meeting_shell.open();
			} else
				table.deselectAll(); // get rid of redundant selection
		}
	}

	class Delete extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (table.getItemCount() != 0) {
				int index = table.getSelectionIndex();
				if (index >= 0 && index < table.getItemCount()) {
					/* update the database */
					DatabaseReader db = new DatabaseReader();
					db.deleteMeeting(meetingList.get(index));
					/* update the meeting table */
					table.remove(index);
				}
			}
		}
	}

	class Edit extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			index = table.getSelectionIndex();
			if (table.getSelectionCount() != 0) {
				Shell edit_meeting_shell = new Shell(getDisplay(),
						SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
				AbstractEdit edit_meeting_page = new AbstractEdit(
						edit_meeting_shell, SWT.None, stringArray,
						signatureArray) {
					// setText
					public void onLoad() {
						for (int i = 0; i < stringArray.length; i++) {
							setData(table.getItem(index).getText(i),
									signatureArray[i], i);
						}
					}

					public void onSubmit() {
						String[] tempList = getStringList();
						Meeting meeting = meetingList.get(index);
						meeting.setMeetingDetails(tempList[0]);
						meeting.setDate(new Date(tempList[1]));
						meeting.setTime(new Time(tempList[2]));
						boolean isDone = Boolean.parseBoolean(tempList[3]);
						meeting.setDone(isDone);
						// update database
						db.updateMeeting(meeting);
						// update the table
						for (int i = 0; i < stringArray.length; i++) {
							table.getItem(index).setText(i, tempList[i]);
						}
					}
				};

				edit_meeting_page.pack();
				edit_meeting_shell.pack();
				edit_meeting_shell.open();
			}
		}
	}
}
