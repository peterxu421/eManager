import java.util.ArrayList;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;

public class EventPlanning_PreEvent extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table tableTaskAssign;
	private Table tableCommittee;
	private Event event;
	private ArrayList<Task> taskList;
	private ArrayList<Organizer> memberList;
	private String[] stringArrayItem = { "Task", "Assigned To", "Date", "Done" };
	private int[] signatureArrayItem = { MACRO.TEXT, MACRO.TEXT, MACRO.DATE,
			MACRO.CHECK };
	private String[] stringArrayMember = { "Name", "Year", "Faculty",
			"Position" };
	private int[] signatureArrayMember = { MACRO.TEXT, MACRO.INT, MACRO.TEXT,
			MACRO.TEXT };
	private int index;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public EventPlanning_PreEvent(Composite parent, int style, Event event) {

		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.event = event;

		Composite compositePreEvent = new Composite(this, SWT.NONE);
		compositePreEvent.setBounds(10, 10, 563, 325);
		toolkit.adapt(compositePreEvent);
		toolkit.paintBordersFor(compositePreEvent);

		TabFolder tabFolderPreEvent = new TabFolder(compositePreEvent, SWT.NONE);
		tabFolderPreEvent.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_RED));
		tabFolderPreEvent.setToolTipText("Task Assignment");
		tabFolderPreEvent.setBounds(10, 10, 543, 305);
		toolkit.adapt(tabFolderPreEvent);
		toolkit.paintBordersFor(tabFolderPreEvent);

		TabItem tbtmTaskAssigned = new TabItem(tabFolderPreEvent, SWT.NONE);
		tbtmTaskAssigned.setText("Task Assigned");

		Composite composite_1 = new Composite(tabFolderPreEvent, SWT.NONE);
		tbtmTaskAssigned.setControl(composite_1);
		toolkit.paintBordersFor(composite_1);

		tableTaskAssign = new Table(composite_1, SWT.BORDER
				| SWT.FULL_SELECTION);
		tableTaskAssign.setBounds(10, 10, 418, 255);
		toolkit.adapt(tableTaskAssign);
		toolkit.paintBordersFor(tableTaskAssign);
		tableTaskAssign.setHeaderVisible(true);
		tableTaskAssign.setLinesVisible(true);

		TableColumn tblclmnTask = new TableColumn(tableTaskAssign, SWT.CENTER);
		tblclmnTask.setWidth(150);
		tblclmnTask.setText("Task");

		TableColumn tblclmnAssignedTo = new TableColumn(tableTaskAssign,
				SWT.NONE);
		tblclmnAssignedTo.setWidth(105);
		tblclmnAssignedTo.setText("Assigned To");

		TableColumn tblclmnDateDue = new TableColumn(tableTaskAssign, SWT.NONE);
		tblclmnDateDue.setWidth(98);
		tblclmnDateDue.setText("Date Due");

		TableColumn tblclmnDone = new TableColumn(tableTaskAssign, SWT.NONE);
		tblclmnDone.setWidth(56);
		tblclmnDone.setText("Done");

		Button btnTaskAssinAddItem = new Button(composite_1, SWT.NONE);
		btnTaskAssinAddItem.addSelectionListener(new TaskAssignAddItemPage());
		btnTaskAssinAddItem.setBounds(445, 10, 80, 27);
		toolkit.adapt(btnTaskAssinAddItem, true, true);
		btnTaskAssinAddItem.setText("Add ");

		Button btnTaskAssignDeleteItem = new Button(composite_1, SWT.NONE);
		btnTaskAssignDeleteItem
				.addSelectionListener(new TaskAssignDeleteItem());
		btnTaskAssignDeleteItem.setBounds(445, 43, 80, 27);
		toolkit.adapt(btnTaskAssignDeleteItem, true, true);
		btnTaskAssignDeleteItem.setText("Delete ");

		Button btnTaskAssignEditItem = new Button(composite_1, SWT.NONE);
		btnTaskAssignEditItem
				.addSelectionListener(new TaskAssignEditItemPage());
		btnTaskAssignEditItem.setBounds(445, 76, 80, 27);
		toolkit.adapt(btnTaskAssignEditItem, true, true);
		btnTaskAssignEditItem.setText("Edit ");

		TabItem tbtmTaskChart = new TabItem(tabFolderPreEvent, SWT.NONE);
		tbtmTaskChart.setText("Task Chart");

		Composite composite_2 = new Composite(tabFolderPreEvent, SWT.NONE);
		tbtmTaskChart.setControl(composite_2);
		toolkit.paintBordersFor(composite_2);
		FillLayout fillLayout = new FillLayout();
		fillLayout.marginWidth = 0;
		composite_2.setLayout(fillLayout);
		TaskChart taskChart = new TaskChart(composite_2, SWT.None, event);
		taskChart.setSize(300, 300);
		taskChart.pack();
		composite_2.pack();

		TabItem tbtmPublicity = new TabItem(tabFolderPreEvent, SWT.NONE);
		tbtmPublicity.setText("Publicity");

		Composite composite_3 = new Composite(tabFolderPreEvent, SWT.NONE);
		tbtmPublicity.setControl(composite_3);
		toolkit.paintBordersFor(composite_3);

		TabItem tbtmCommittee = new TabItem(tabFolderPreEvent, SWT.NONE);
		tbtmCommittee.setText("Committee");

		Composite composite_4 = new Composite(tabFolderPreEvent, SWT.NONE);
		tbtmCommittee.setControl(composite_4);
		toolkit.paintBordersFor(composite_4);

		tableCommittee = new Table(composite_4, SWT.BORDER | SWT.FULL_SELECTION);
		tableCommittee.setBounds(10, 10, 408, 255);
		toolkit.adapt(tableCommittee);
		toolkit.paintBordersFor(tableCommittee);
		tableCommittee.setHeaderVisible(true);
		tableCommittee.setLinesVisible(true);

		TableColumn tblclmnName = new TableColumn(tableCommittee, SWT.NONE);
		tblclmnName.setWidth(84);
		tblclmnName.setText("Name");

		TableColumn tblclmnYear = new TableColumn(tableCommittee, SWT.NONE);
		tblclmnYear.setWidth(56);
		tblclmnYear.setText("Year");

		TableColumn tblclmnFaculty = new TableColumn(tableCommittee, SWT.NONE);
		tblclmnFaculty.setWidth(80);
		tblclmnFaculty.setText("Faculty");

		TableColumn tblclmnPosition = new TableColumn(tableCommittee, SWT.NONE);
		tblclmnPosition.setWidth(118);
		tblclmnPosition.setText("Position");

		Button btnCommitteeAddMember = new Button(composite_4, SWT.NONE);
		btnCommitteeAddMember
				.addSelectionListener(new TaskAssignAddMemberPage());
		btnCommitteeAddMember.setBounds(424, 10, 101, 27);
		toolkit.adapt(btnCommitteeAddMember, true, true);
		btnCommitteeAddMember.setText("Add");

		Button btnCommitteeDeleteMember = new Button(composite_4, SWT.NONE);
		btnCommitteeDeleteMember
				.addSelectionListener(new TaskAssignDeleteMember());
		btnCommitteeDeleteMember.setBounds(424, 43, 101, 27);
		toolkit.adapt(btnCommitteeDeleteMember, true, true);
		btnCommitteeDeleteMember.setText("Delete");

		Button btnCommitteeEditMember = new Button(composite_4, SWT.NONE);
		btnCommitteeEditMember
				.addSelectionListener(new TaskAssignEditMemberPage());
		btnCommitteeEditMember.setBounds(424, 76, 101, 27);
		toolkit.adapt(btnCommitteeEditMember, true, true);
		btnCommitteeEditMember.setText("Edit");
		fillTable();

	}

	public void fillTable() {
		DatabaseReader dbReader = new DatabaseReader();
		taskList = dbReader.getTasks(event);
		memberList = dbReader.getOrganizers(event);
		TableItem item;

		for (int i = 0; i < taskList.size(); i++) {
			item = new TableItem(tableTaskAssign, SWT.NONE);
			item.setText(0, taskList.get(i).getTaskDesciption());
			item.setText(1, taskList.get(i).getAssignedTo());
			item.setText(2, taskList.get(i).getDateDue().toString());
			item.setText(3, taskList.get(i).isDone() ? "Done" : "Undone");
		}

		for (int i = 0; i < memberList.size(); i++) {
			item = new TableItem(tableCommittee, SWT.NONE);
			item.setText(0, memberList.get(i).getName());
			item.setText(1, Integer.toString(memberList.get(i).getYear()));
			item.setText(2, memberList.get(i).getFaculty());
			item.setText(3, memberList.get(i).getPosition());
		}

	}

	public class TaskAssignAddItemPage extends SelectionAdapter {

		public void widgetSelected(SelectionEvent e) {
			Shell taskAssignAddItemPage = new Shell(getDisplay());
			AbstractAdd taskAssignAddItem = new AbstractAdd(
					taskAssignAddItemPage, SWT.None, stringArrayItem, signatureArrayItem) {
				public void onSubmit() {
					// insert to database
					String[] tempList=getStringList();
					Date date = new Date(tempList[2]);
					Done done = new Done(tempList[3]);
					Task task = new Task(tempList[0],
							tempList[1], date, done.isDone());
					db.insertTask(event, task);
					taskList.add(task);
					// update the table
					TableItem item = new TableItem(tableTaskAssign, SWT.NULL);
					for (int i = 0; i < stringArrayItem.length; i++) {
						item.setText(i, tempList[i]);
					}
				}
			};
			taskAssignAddItem.pack();
			taskAssignAddItemPage.pack();
			taskAssignAddItemPage.open();
		}
	}

	public class TaskAssignAddMemberPage extends SelectionAdapter {

		public void widgetSelected(SelectionEvent e) {
			Shell taskAssignAddMemberPage = new Shell(getDisplay());
			AbstractAdd taskAssignAddMember = new AbstractAdd(
					taskAssignAddMemberPage, SWT.None, stringArrayMember, signatureArrayMember) {
				public void onSubmit() {
					// insert to database
					String[] tempList=getStringList();
					Organizer member = new Organizer(tempList[0],
							Integer.parseInt(tempList[1]),
							tempList[2], tempList[3]);
					db.insertOrganizer(event, member);
					memberList.add(member);
					// update the table
					TableItem item = new TableItem(tableCommittee, SWT.NULL);
					for (int i = 0; i < stringArrayMember.length; i++) {
						item.setText(i, tempList[i]);
					}
				}
			};
			taskAssignAddMember.pack();
			taskAssignAddMemberPage.pack();
			taskAssignAddMemberPage.open();
		}
	}

	public class TaskAssignDeleteItem extends SelectionAdapter {

		public void widgetSelected(SelectionEvent e) {
			if (tableTaskAssign.getColumnCount() != 0) {
				int index = tableTaskAssign.getSelectionIndex();
				if (index < 0 || index >= tableTaskAssign.getItemCount()) {
					// Do nothing.
				} else {
					DatabaseReader dbReader = new DatabaseReader();
					dbReader.deleteTask(taskList.get(index));
					tableTaskAssign.remove(index);
				}
			}
		}
	}

	public class TaskAssignDeleteMember extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (tableCommittee.getColumnCount() != 0) {
				int index = tableCommittee.getSelectionIndex();
				if (index < 0 || index >= tableCommittee.getItemCount()) {
					// Do nothing.
				} else {
					DatabaseReader dbReader = new DatabaseReader();
					dbReader.deleteMember(memberList.get(index));
					tableCommittee.remove(index);
				}
			}
		}
	}

	public class TaskAssignEditItemPage extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			index = tableTaskAssign.getSelectionIndex();
			if (index < tableTaskAssign.getItemCount() && index >= 0) {
				Shell taskAssignEditItemPage = new Shell(getDisplay());
				AbstractEdit taskAssignEditItem = new AbstractEdit(
						taskAssignEditItemPage, SWT.None, stringArrayItem, signatureArrayItem) {
					public void onLoad() {
						for (int i = 0; i < stringArrayItem.length; i++) {
							setData(tableTaskAssign.getItem(index)
									.getText(i),signatureArrayItem[i],i);
						}
					}

					public void onSubmit() {
						String[] tempList=getStringList();
						Task task = taskList.get(index);
						task.setTaskDesciption(tempList[0]);
						task.setAssignedTo(tempList[1]);
						task.setDateDue(new Date(tempList[2]));
						Done done = new Done(tempList[3]);
						task.setDone(done.isDone());
						// update database
						db.updateTask(task);
						// update the table
						for (int i = 0; i < stringArrayItem.length; i++) {
							tableTaskAssign.getItem(index).setText(i,
									tempList[i]);
						}
					}
				};
				taskAssignEditItem.pack();
				taskAssignEditItemPage.pack();
				taskAssignEditItemPage.open();
			}
		}
	}

	public class TaskAssignEditMemberPage extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			index = tableCommittee.getSelectionIndex();
			if (index < tableCommittee.getItemCount() && index >= 0) {
				Shell taskAssignEditMemberPage = new Shell(getDisplay());
				AbstractEdit taskAssignEditMember = new AbstractEdit(
						taskAssignEditMemberPage, SWT.None, stringArrayMember,signatureArrayMember) {
					//get data from table
					public void onLoad() {
						for (int i = 0; i < stringArrayMember.length; i++) {
							setData(tableCommittee.getItem(index)
									.getText(i),i);
						}
					}

					public void onSubmit() {
						String[] stringList=getStringList();
						//reset
						Organizer member = memberList.get(index);
						member.setName(stringList[0]);
						member.setYear(Integer.parseInt(stringList[1]));
						member.setFaculty(stringList[2]);
						member.setPosition(stringList[3]);
						// update database
						db.updateOrganizer(member);
						// update the table
						for (int i = 0; i < stringArrayMember.length; i++) {
							tableCommittee.getItem(index).setText(i,
									stringList[i]);
						}
					}
				};
				taskAssignEditMember.pack();
				taskAssignEditMemberPage.pack();
				taskAssignEditMemberPage.open();
			}
		}
	}
}
