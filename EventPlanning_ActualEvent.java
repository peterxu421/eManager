import java.util.ArrayList;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class EventPlanning_ActualEvent extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table table_eventPlanning_actualEvent_tableItinerary;
	private Table table_eventPlanning_actualEvent_participants;
	private Table table_eventPlanning_actualEvents_allocOfManpower;
	private Table table_eventPlanning_actualEvents_facilitators;
	private Event event;
	private ArrayList<Itinerary> itineraryList;
	private ArrayList<ManpowerAllocation> manpowerList;
	private ArrayList<Facilitator> facilitatorList;
	private ArrayList<Participant> participantList;
	private int index;
	// Attributes for each table.
	private String[] stringArrayItinerary = { "Description", "Date", "Time",
			"Done" };
	private int[] signatureItinerary = { MACRO.TEXT, MACRO.DATE, MACRO.TIME,
			MACRO.CHECK };
	private String[] stringArrayAllocationOfManpower = { "Task", "Assigned To",
			"Date", "Done" };
	private int[] signatureAllocationOfManpower = { MACRO.TEXT, MACRO.TEXT,
			MACRO.DATE, MACRO.CHECK };
	private String[] stringArrayFacilitator = { "Name", "Year", "Faculty",
			"Postion" };
	private int[] signatureFacilitator = { MACRO.TEXT, MACRO.INT, MACRO.TEXT,
			MACRO.TEXT };
	private String[] stringArrayParticipant = { "Name", "Year", "Faculty",
			"Food Type" };
	private int[] signatureParticipant = { MACRO.TEXT, MACRO.INT, MACRO.TEXT };

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public EventPlanning_ActualEvent(Composite parent, int style, Event event) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.event = event;

		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(10, 10, 686, 329);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);

		TabFolder folder_eventPlanning_actualEvent = new TabFolder(composite,
				SWT.NONE);
		// tabFolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		folder_eventPlanning_actualEvent.setToolTipText("Itinerary");
		folder_eventPlanning_actualEvent.setBounds(10, 10, 576, 305);
		toolkit.adapt(folder_eventPlanning_actualEvent);
		toolkit.paintBordersFor(folder_eventPlanning_actualEvent);

		TabItem tab_eventPlanning_actualEvent_itinerary = new TabItem(
				folder_eventPlanning_actualEvent, SWT.NONE);
		tab_eventPlanning_actualEvent_itinerary.setText("Itinerary");

		Composite comp_eventPlanning_actualEvent_Itinerary = new Composite(
				folder_eventPlanning_actualEvent, SWT.NONE);
		tab_eventPlanning_actualEvent_itinerary
				.setControl(comp_eventPlanning_actualEvent_Itinerary);
		toolkit.paintBordersFor(comp_eventPlanning_actualEvent_Itinerary);

		table_eventPlanning_actualEvent_tableItinerary = new Table(
				comp_eventPlanning_actualEvent_Itinerary, SWT.BORDER
						| SWT.FULL_SELECTION);
		table_eventPlanning_actualEvent_tableItinerary.setBounds(10, 10, 446,
				255);
		toolkit.adapt(table_eventPlanning_actualEvent_tableItinerary);
		toolkit.paintBordersFor(table_eventPlanning_actualEvent_tableItinerary);
		table_eventPlanning_actualEvent_tableItinerary.setHeaderVisible(true);
		table_eventPlanning_actualEvent_tableItinerary.setLinesVisible(true);

		// itinerary
		TableColumn col_eventPlanning_actualEvent__itinerary_description = new TableColumn(
				table_eventPlanning_actualEvent_tableItinerary, SWT.CENTER);
		col_eventPlanning_actualEvent__itinerary_description.setWidth(196);
		col_eventPlanning_actualEvent__itinerary_description
				.setText("Description");

		TableColumn col_eventPlanning_actualEvent__itinerary_date = new TableColumn(
				table_eventPlanning_actualEvent_tableItinerary, SWT.NONE);
		col_eventPlanning_actualEvent__itinerary_date.setWidth(93);
		col_eventPlanning_actualEvent__itinerary_date.setText("Date");

		TableColumn col_eventPlanning_actualEvent__itinerary_time = new TableColumn(
				table_eventPlanning_actualEvent_tableItinerary, SWT.NONE);
		col_eventPlanning_actualEvent__itinerary_time.setWidth(93);
		col_eventPlanning_actualEvent__itinerary_time.setText("Time");

		TableColumn col_eventPlanning_actualEvent__itinerary_done = new TableColumn(
				table_eventPlanning_actualEvent_tableItinerary, SWT.NONE);
		col_eventPlanning_actualEvent__itinerary_done.setWidth(59);
		col_eventPlanning_actualEvent__itinerary_done.setText("Done");

		Button btn_eventPlanning_actualEvent_Itinerary_add = new Button(
				comp_eventPlanning_actualEvent_Itinerary, SWT.NONE);
		btn_eventPlanning_actualEvent_Itinerary_add
				.addSelectionListener(new ItineraryAddItemPage());
		btn_eventPlanning_actualEvent_Itinerary_add.setBounds(462, 10, 80, 27);
		toolkit.adapt(btn_eventPlanning_actualEvent_Itinerary_add, true, true);
		btn_eventPlanning_actualEvent_Itinerary_add.setText("Add ");

		Button btn_eventPlanning_actualEvent_Itinerary_del = new Button(
				comp_eventPlanning_actualEvent_Itinerary, SWT.NONE);
		btn_eventPlanning_actualEvent_Itinerary_del
				.addSelectionListener(new ItineraryDeleteItem());
		btn_eventPlanning_actualEvent_Itinerary_del.setBounds(462, 45, 80, 27);
		toolkit.adapt(btn_eventPlanning_actualEvent_Itinerary_del, true, true);
		btn_eventPlanning_actualEvent_Itinerary_del.setText("Delete");

		Button btn_eventPlanning_actualEvent_Itinerary_edit = new Button(
				comp_eventPlanning_actualEvent_Itinerary, SWT.NONE);
		btn_eventPlanning_actualEvent_Itinerary_edit
				.addSelectionListener(new ItineraryEditItemPage());
		btn_eventPlanning_actualEvent_Itinerary_edit.setBounds(462, 78, 80, 27);
		toolkit.adapt(btn_eventPlanning_actualEvent_Itinerary_edit, true, true);
		btn_eventPlanning_actualEvent_Itinerary_edit.setText("Edit ");

		// alloc of manpower
		TabItem tab_eventPlanning_actualEvent_allocOfManpower = new TabItem(
				folder_eventPlanning_actualEvent, SWT.NONE);
		tab_eventPlanning_actualEvent_allocOfManpower
				.setText("Allocation of manpower");

		Composite composite_eventPlanning_actualEvents_allocOfManpower = new Composite(
				folder_eventPlanning_actualEvent, SWT.NONE);
		tab_eventPlanning_actualEvent_allocOfManpower
				.setControl(composite_eventPlanning_actualEvents_allocOfManpower);
		toolkit.paintBordersFor(composite_eventPlanning_actualEvents_allocOfManpower);

		table_eventPlanning_actualEvents_allocOfManpower = new Table(
				composite_eventPlanning_actualEvents_allocOfManpower,
				SWT.BORDER | SWT.FULL_SELECTION);
		table_eventPlanning_actualEvents_allocOfManpower.setLinesVisible(true);
		table_eventPlanning_actualEvents_allocOfManpower.setHeaderVisible(true);
		table_eventPlanning_actualEvents_allocOfManpower.setBounds(10, 10, 457,
				255);
		toolkit.adapt(table_eventPlanning_actualEvents_allocOfManpower);
		toolkit.paintBordersFor(table_eventPlanning_actualEvents_allocOfManpower);

		TableColumn col_eventPlanning_actualEvents_allocOfManpower_task = new TableColumn(
				table_eventPlanning_actualEvents_allocOfManpower, SWT.CENTER);
		col_eventPlanning_actualEvents_allocOfManpower_task.setWidth(217);
		col_eventPlanning_actualEvents_allocOfManpower_task.setText("Task");

		TableColumn col_eventPlanning_actualEvents_allocOfManpower_assignedTo = new TableColumn(
				table_eventPlanning_actualEvents_allocOfManpower, SWT.CENTER);
		col_eventPlanning_actualEvents_allocOfManpower_assignedTo.setWidth(97);
		col_eventPlanning_actualEvents_allocOfManpower_assignedTo
				.setText("Assigned To");

		TableColumn col_eventPlanning_actualEvents_allocOfManpower_dateDue = new TableColumn(
				table_eventPlanning_actualEvents_allocOfManpower, SWT.CENTER);
		col_eventPlanning_actualEvents_allocOfManpower_dateDue.setWidth(96);
		col_eventPlanning_actualEvents_allocOfManpower_dateDue
				.setText("Date Due");

		TableColumn col_eventPlanning_actualEvents_allocOfManpower_done = new TableColumn(
				table_eventPlanning_actualEvents_allocOfManpower, SWT.CENTER);
		col_eventPlanning_actualEvents_allocOfManpower_done.setWidth(42);
		col_eventPlanning_actualEvents_allocOfManpower_done.setText("Done");

		Button btn_eventPlanning_actualEvent_AllocationManpower_add = new Button(
				composite_eventPlanning_actualEvents_allocOfManpower, SWT.NONE);
		btn_eventPlanning_actualEvent_AllocationManpower_add
				.addSelectionListener(new AllocOfManpowerAddItemPage());
		btn_eventPlanning_actualEvent_AllocationManpower_add.setText("Add");
		btn_eventPlanning_actualEvent_AllocationManpower_add.setBounds(473, 10,
				80, 27);
		toolkit.adapt(btn_eventPlanning_actualEvent_AllocationManpower_add,
				true, true);

		Button btn_eventPlanning_actualEvent_AllocationManpower_del = new Button(
				composite_eventPlanning_actualEvents_allocOfManpower, SWT.NONE);
		btn_eventPlanning_actualEvent_AllocationManpower_del
				.addSelectionListener(new AllocOfManpowerDelItem());
		btn_eventPlanning_actualEvent_AllocationManpower_del.setText("Delete");
		btn_eventPlanning_actualEvent_AllocationManpower_del.setBounds(473, 43,
				80, 27);
		toolkit.adapt(btn_eventPlanning_actualEvent_AllocationManpower_del,
				true, true);

		Button btn_eventPlanning_actualEvent_AllocationManpower_edit = new Button(
				composite_eventPlanning_actualEvents_allocOfManpower, SWT.NONE);
		btn_eventPlanning_actualEvent_AllocationManpower_edit
				.addSelectionListener(new AllocOfManpowerEditItemPage());
		btn_eventPlanning_actualEvent_AllocationManpower_edit.setText("Edit");
		btn_eventPlanning_actualEvent_AllocationManpower_edit.setBounds(473,
				76, 80, 27);
		toolkit.adapt(btn_eventPlanning_actualEvent_AllocationManpower_edit,
				true, true);

		// facilitators
		TabItem tab_eventPlanning_actualEvent_facilitators = new TabItem(
				folder_eventPlanning_actualEvent, SWT.NONE);
		tab_eventPlanning_actualEvent_facilitators.setText("Facilitators");

		Composite composite_eventPlanning_actualEvents_facilitators = new Composite(
				folder_eventPlanning_actualEvent, SWT.NONE);
		tab_eventPlanning_actualEvent_facilitators
				.setControl(composite_eventPlanning_actualEvents_facilitators);
		toolkit.paintBordersFor(composite_eventPlanning_actualEvents_facilitators);

		table_eventPlanning_actualEvents_facilitators = new Table(
				composite_eventPlanning_actualEvents_facilitators, SWT.BORDER
						| SWT.FULL_SELECTION);
		table_eventPlanning_actualEvents_facilitators.setLinesVisible(true);
		table_eventPlanning_actualEvents_facilitators.setHeaderVisible(true);
		table_eventPlanning_actualEvents_facilitators.setBounds(10, 10, 418,
				255);
		toolkit.adapt(table_eventPlanning_actualEvents_facilitators);
		toolkit.paintBordersFor(table_eventPlanning_actualEvents_facilitators);

		TableColumn col_eventPlanning_actualEvents_facilitators_name = new TableColumn(
				table_eventPlanning_actualEvents_facilitators, SWT.CENTER);
		col_eventPlanning_actualEvents_facilitators_name.setWidth(161);
		col_eventPlanning_actualEvents_facilitators_name.setText("Name");

		TableColumn col_eventPlanning_actualEvents_facilitators_year = new TableColumn(
				table_eventPlanning_actualEvents_facilitators, SWT.NONE);
		col_eventPlanning_actualEvents_facilitators_year.setWidth(43);
		col_eventPlanning_actualEvents_facilitators_year.setText("Year");

		TableColumn col_eventPlanning_actualEvents_facilitators_interestedIn = new TableColumn(
				table_eventPlanning_actualEvents_facilitators, SWT.NONE);
		col_eventPlanning_actualEvents_facilitators_interestedIn.setWidth(82);
		col_eventPlanning_actualEvents_facilitators_interestedIn
				.setText("Faculty");

		TableColumn col_eventPlanning_actualEvents_facilitators_status = new TableColumn(
				table_eventPlanning_actualEvents_facilitators, SWT.NONE);
		col_eventPlanning_actualEvents_facilitators_status.setWidth(149);
		col_eventPlanning_actualEvents_facilitators_status
				.setText("Interested In(Pos)");

		Button btnFacilitatorsAddItem = new Button(
				composite_eventPlanning_actualEvents_facilitators, SWT.NONE);
		btnFacilitatorsAddItem
				.addSelectionListener(new FacilitatorAddItemPage());
		btnFacilitatorsAddItem.setText("Add ");
		btnFacilitatorsAddItem.setBounds(445, 10, 80, 27);
		toolkit.adapt(btnFacilitatorsAddItem, true, true);

		Button btnFacilitatorsDelItem = new Button(
				composite_eventPlanning_actualEvents_facilitators, SWT.NONE);
		btnFacilitatorsDelItem
				.addSelectionListener(new FacilitatorDeleteItem());
		btnFacilitatorsDelItem.setText("Delete");
		btnFacilitatorsDelItem.setBounds(445, 43, 80, 27);
		toolkit.adapt(btnFacilitatorsDelItem, true, true);

		Button btnFacilitatorsEditItem = new Button(
				composite_eventPlanning_actualEvents_facilitators, SWT.NONE);
		btnFacilitatorsEditItem
				.addSelectionListener(new FacilitatorEditItemPage());
		btnFacilitatorsEditItem.setText("Edit");
		btnFacilitatorsEditItem.setBounds(445, 76, 80, 27);
		toolkit.adapt(btnFacilitatorsEditItem, true, true);

		// participants
		TabItem tab_eventPlanning_actualEvent_participants = new TabItem(
				folder_eventPlanning_actualEvent, SWT.NONE);
		tab_eventPlanning_actualEvent_participants.setText("Participants");

		Composite composite_eventPlanning_actualEvent_participants = new Composite(
				folder_eventPlanning_actualEvent, SWT.NONE);
		tab_eventPlanning_actualEvent_participants
				.setControl(composite_eventPlanning_actualEvent_participants);
		toolkit.paintBordersFor(composite_eventPlanning_actualEvent_participants);

		table_eventPlanning_actualEvent_participants = new Table(
				composite_eventPlanning_actualEvent_participants, SWT.BORDER
						| SWT.FULL_SELECTION);
		table_eventPlanning_actualEvent_participants
				.setBounds(10, 10, 408, 255);
		toolkit.adapt(table_eventPlanning_actualEvent_participants);
		toolkit.paintBordersFor(table_eventPlanning_actualEvent_participants);
		table_eventPlanning_actualEvent_participants.setHeaderVisible(true);
		table_eventPlanning_actualEvent_participants.setLinesVisible(true);

		TableColumn col_eventPlanning_actualEvent_participants_participants_name = new TableColumn(
				table_eventPlanning_actualEvent_participants, SWT.NONE);
		col_eventPlanning_actualEvent_participants_participants_name
				.setWidth(141);
		col_eventPlanning_actualEvent_participants_participants_name
				.setText("Name");

		TableColumn col_eventPlanning_actualEvent_participants_participants_age = new TableColumn(
				table_eventPlanning_actualEvent_participants, SWT.NONE);
		col_eventPlanning_actualEvent_participants_participants_age
				.setWidth(48);
		col_eventPlanning_actualEvent_participants_participants_age
				.setText("Year");

		TableColumn col_eventPlanning_actualEvent_participants_participants_faculty = new TableColumn(
				table_eventPlanning_actualEvent_participants, SWT.NONE);
		col_eventPlanning_actualEvent_participants_participants_faculty
				.setWidth(115);
		col_eventPlanning_actualEvent_participants_participants_faculty
				.setText("Faculty");

		TableColumn col_eventPlanning_actualEvent_participants_participants_foodType = new TableColumn(
				table_eventPlanning_actualEvent_participants, SWT.NONE);
		col_eventPlanning_actualEvent_participants_participants_foodType
				.setWidth(106);
		col_eventPlanning_actualEvent_participants_participants_foodType
				.setText("Food Type");

		Button btn_eventPlanning_actualEvent_participants_participants_add = new Button(
				composite_eventPlanning_actualEvent_participants, SWT.NONE);
		btn_eventPlanning_actualEvent_participants_participants_add
				.addSelectionListener(new ParticipantAddItemPage());
		btn_eventPlanning_actualEvent_participants_participants_add.setBounds(
				424, 10, 101, 27);
		toolkit.adapt(
				btn_eventPlanning_actualEvent_participants_participants_add,
				true, true);
		btn_eventPlanning_actualEvent_participants_participants_add
				.setText("Add");

		Button btn_eventPlanning_actualEvent_participants_participants_del = new Button(
				composite_eventPlanning_actualEvent_participants, SWT.NONE);
		btn_eventPlanning_actualEvent_participants_participants_del
				.addSelectionListener(new ParticipantDeleteItem());
		btn_eventPlanning_actualEvent_participants_participants_del.setBounds(
				424, 43, 101, 27);
		toolkit.adapt(
				btn_eventPlanning_actualEvent_participants_participants_del,
				true, true);
		btn_eventPlanning_actualEvent_participants_participants_del
				.setText("Delete ");

		Button btn_eventPlanning_actualEvent_participants_participants_edit = new Button(
				composite_eventPlanning_actualEvent_participants, SWT.NONE);
		btn_eventPlanning_actualEvent_participants_participants_edit
				.addSelectionListener(new ParticipantEditItemPage());
		btn_eventPlanning_actualEvent_participants_participants_edit.setBounds(
				424, 76, 101, 27);
		toolkit.adapt(
				btn_eventPlanning_actualEvent_participants_participants_edit,
				true, true);
		btn_eventPlanning_actualEvent_participants_participants_edit
				.setText("Edit ");

		importItineraryData();
		importManpowerAllocationData();
		importFacilitorData();
		importParticipantData();

	}

	public void importItineraryData() {
		DatabaseReader db = new DatabaseReader();
		itineraryList = db.getItinerary(event);

		for (int i = 0; i < itineraryList.size(); i++) {
			TableItem temp = new TableItem(
					table_eventPlanning_actualEvent_tableItinerary, SWT.NULL);
			temp.setText(0, itineraryList.get(i).getItineraryDetails());
			temp.setText(1, itineraryList.get(i).getDate().toString());
			temp.setText(2, itineraryList.get(i).getTime().toString());
			if (itineraryList.get(i).isDone() == true)
				temp.setText(3, "Done");
			else
				temp.setText(3, "Undone");
		}
	}

	public void importManpowerAllocationData() {
		DatabaseReader db = new DatabaseReader();
		manpowerList = db.getManpowerAllocation(event);

		for (int i = 0; i < manpowerList.size(); i++) {
			TableItem temp = new TableItem(
					table_eventPlanning_actualEvents_allocOfManpower, SWT.NULL);
			temp.setText(0, manpowerList.get(i).getTaskDescription());
			temp.setText(1, manpowerList.get(i).getAssignedTo());
			temp.setText(2, manpowerList.get(i).getDate().toString());
			if (manpowerList.get(i).isDone() == true)
				temp.setText(3, "Done");
			else
				temp.setText(3, "Undone");
		}
	}

	public void importFacilitorData() {
		DatabaseReader db = new DatabaseReader();
		facilitatorList = db.getFacilitators(event);

		for (int i = 0; i < facilitatorList.size(); i++) {
			TableItem temp = new TableItem(
					table_eventPlanning_actualEvents_facilitators, SWT.NULL);
			temp.setText(0, facilitatorList.get(i).getName());
			temp.setText(1, Integer.toString(facilitatorList.get(i).getYear()));
			temp.setText(2, facilitatorList.get(i).getFaculty());
			temp.setText(3, facilitatorList.get(i).getPosition());
		}
	}

	public void importParticipantData() {
		DatabaseReader db = new DatabaseReader();
		participantList = db.getParticipants(event);

		for (int i = 0; i < participantList.size(); i++) {
			TableItem temp = new TableItem(
					table_eventPlanning_actualEvent_participants, SWT.NULL);
			temp.setText(0, participantList.get(i).getName());
			temp.setText(1, Integer.toString(participantList.get(i).getYear()));
			temp.setText(2, participantList.get(i).getFaculty());
			temp.setText(3, participantList.get(i).getFoodType());
		}
	}

	// Itinerary start
	public class ItineraryAddItemPage extends SelectionAdapter {

		public void widgetSelected(SelectionEvent e) {
			Shell itineraryAddItemPage = new Shell(getDisplay());
			AbstractAdd itineraryAddItem = new AbstractAdd(
					itineraryAddItemPage, SWT.None, stringArrayItinerary, signatureItinerary) {
				public void onSubmit() {
					// insert to database
					Text tempText=(Text)get(stringArrayItinerary[0]);
					DateTime tempDate=(DateTime)get(stringArrayItinerary[1]);
					Date date = new Date(tempDate.getYear(),tempDate.getMonth(),tempDate.getDay());
					DateTime tempTime=(DateTime)get(stringArrayItinerary[2]);
					Time time = new Time(tempDate.getHours(),tempDate.getMinutes(),tempDate.getSeconds());
					Button tempDone=(Button)get(stringArrayItinerary[3]);
					Itinerary itinerary = new Itinerary(tempText.getText(),
							date, time, tempDone.getSelection());
					db.insertItinerary(event, itinerary);
					itineraryList.add(itinerary);
					// update the table
					TableItem item = new TableItem(
							table_eventPlanning_actualEvent_tableItinerary,
							SWT.NULL);
					for (int i = 0; i < stringArrayItinerary.length; i++) {
						item.setText(i, textList[i].getText());
					}
				}
			};
			itineraryAddItem.pack();
			itineraryAddItemPage.pack();
			itineraryAddItemPage.open();
		}
	}

	public class ItineraryDeleteItem extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (table_eventPlanning_actualEvent_tableItinerary.getColumnCount() != 0) {
				int index = table_eventPlanning_actualEvent_tableItinerary
						.getSelectionIndex();
				if (index < 0
						|| index >= table_eventPlanning_actualEvent_tableItinerary
								.getItemCount()) {
					// Do nothing.
				} else {
					/* update the itinerary table */
					table_eventPlanning_actualEvent_tableItinerary
							.remove(index);
					/* update the database */
					DatabaseReader db = new DatabaseReader();
					db.deleteItinerary(itineraryList.get(index));
					itineraryList.remove(index);
				}
			}
		}
	}

	public class ItineraryEditItemPage extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			index = table_eventPlanning_actualEvent_tableItinerary
					.getSelectionIndex();
			if (index < table_eventPlanning_actualEvent_tableItinerary
					.getItemCount() && index >= 0) {
				Shell itineraryEditItemPage = new Shell(getDisplay());
				AbstractEdit itineraryEditItem = new AbstractEdit(
						itineraryEditItemPage, SWT.None, stringArrayItinerary) {
					// setText
					public void onLoad() {
						for (int i = 0; i < stringArrayItinerary.length; i++) {
							textList[i]
									.setText(table_eventPlanning_actualEvent_tableItinerary
											.getItem(index).getText(i));
						}
					}

					public void onSubmit() {
						Itinerary itinerary = itineraryList.get(index);
						itinerary.setItineraryDetails(textList[0].getText());
						itinerary.setDate(new Date(textList[1].getText()));
						itinerary.setTime(new Time(textList[2].getText()));
						Done done = new Done(textList[3].getText());
						itinerary.setDone(done.isDone());
						// update database
						db.updateItinerary(itinerary);
						// update the table
						for (int i = 0; i < stringArrayItinerary.length; i++) {
							table_eventPlanning_actualEvent_tableItinerary
									.getItem(index).setText(i,
											textList[i].getText());
						}
					}
				};
				itineraryEditItem.pack();
				itineraryEditItemPage.pack();
				itineraryEditItemPage.open();
			}
		}
	}

	// Itinerary end

	// AllocOfManpower start
	public class AllocOfManpowerAddItemPage extends SelectionAdapter {

		public void widgetSelected(SelectionEvent e) {
			Shell allocOfManpowerAddItemPage = new Shell(getDisplay());
			AbstractAdd allocOfManpowerAddItem = new AbstractAdd(
					allocOfManpowerAddItemPage, SWT.None,
					stringArrayAllocationOfManpower) {
				public void onSubmit() {
					// insert to database
					Date date = new Date(textList[2].getText());
					Done done = new Done(textList[3].getText());
					ManpowerAllocation manpowerAllocation = new ManpowerAllocation(
							textList[0].getText(), textList[1].getText(), date,
							done.isDone());
					db.insertManpowerAllocation(event, manpowerAllocation);
					manpowerList.add(manpowerAllocation);
					// update the table
					TableItem item = new TableItem(
							table_eventPlanning_actualEvents_allocOfManpower,
							SWT.NULL);
					for (int i = 0; i < stringArrayAllocationOfManpower.length; i++) {
						item.setText(i, textList[i].getText());
					}
				}
			};
			allocOfManpowerAddItem.pack();
			allocOfManpowerAddItemPage.pack();
			allocOfManpowerAddItemPage.open();
		}
	}

	public class AllocOfManpowerDelItem extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (table_eventPlanning_actualEvents_allocOfManpower
					.getColumnCount() != 0) {
				int index = table_eventPlanning_actualEvents_allocOfManpower
						.getSelectionIndex();
				if (index < 0
						|| index >= table_eventPlanning_actualEvents_allocOfManpower
								.getItemCount()) {
					// Do nothing.
				} else {
					/* update the allocation table */
					table_eventPlanning_actualEvents_allocOfManpower
							.remove(index);
					/* update the database */
					DatabaseReader db = new DatabaseReader();
					db.deleteManpowerAllocation(manpowerList.get(index));
					manpowerList.remove(index);
				}
			}
		}
	}

	public class AllocOfManpowerEditItemPage extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			index = table_eventPlanning_actualEvents_allocOfManpower
					.getSelectionIndex();
			if (index < table_eventPlanning_actualEvents_allocOfManpower
					.getItemCount() && index >= 0) {
				Shell allocOfManpowerEditItemPage = new Shell(getDisplay());
				AbstractEdit allocOfManpowerEditItem = new AbstractEdit(
						allocOfManpowerEditItemPage, SWT.None,
						stringArrayAllocationOfManpower) {
					// setText
					public void onLoad() {
						for (int i = 0; i < stringArrayAllocationOfManpower.length; i++) {
							textList[i]
									.setText(table_eventPlanning_actualEvents_allocOfManpower
											.getItem(index).getText(i));
						}
					}

					public void onSubmit() {
						ManpowerAllocation manpowerAllocation = manpowerList
								.get(index);
						manpowerAllocation.setTaskDescription(textList[0]
								.getText());
						manpowerAllocation.setAssignedTo(textList[1].getText());
						manpowerAllocation.setDate(new Date(textList[2]
								.getText()));
						Done done = new Done(textList[3].getText());
						manpowerAllocation.setDone(done.isDone());
						// update database
						db.updateManpowerAllocation(manpowerAllocation);
						// update the table
						for (int i = 0; i < stringArrayAllocationOfManpower.length; i++) {
							table_eventPlanning_actualEvents_allocOfManpower
									.getItem(index).setText(i,
											textList[i].getText());
						}
					}
				};
				allocOfManpowerEditItem.pack();
				allocOfManpowerEditItemPage.pack();
				allocOfManpowerEditItemPage.open();
			}
		}
	}

	// AllocOfManpower end

	// Facilitators start
	public class FacilitatorAddItemPage extends SelectionAdapter {

		public void widgetSelected(SelectionEvent e) {
			Shell facilitatorAddItemPage = new Shell(getDisplay());
			AbstractAdd facilitatorAddItem = new AbstractAdd(
					facilitatorAddItemPage, SWT.None, stringArrayFacilitator) {
				public void onSubmit() {
					// insert to database
					Facilitator facilitator = new Facilitator(
							textList[0].getText(), Integer.parseInt(textList[1]
									.getText()), textList[2].getText(),
							textList[3].getText());
					db.insertFacilitator(event, facilitator);
					facilitatorList.add(facilitator);
					// update the table
					TableItem item = new TableItem(
							table_eventPlanning_actualEvents_facilitators,
							SWT.NULL);
					for (int i = 0; i < stringArrayFacilitator.length; i++) {
						item.setText(i, textList[i].getText());
					}
				}
			};
			facilitatorAddItem.pack();
			facilitatorAddItemPage.pack();
			facilitatorAddItemPage.open();
		}
	}

	public class FacilitatorDeleteItem extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (table_eventPlanning_actualEvents_facilitators.getColumnCount() != 0) {
				int index = table_eventPlanning_actualEvents_facilitators
						.getSelectionIndex();
				if (index < 0
						|| index >= table_eventPlanning_actualEvents_facilitators
								.getItemCount()) {
					// Do nothing.
				} else {
					table_eventPlanning_actualEvents_facilitators.remove(index);
					DatabaseReader db = new DatabaseReader();
					db.deleteMember(facilitatorList.get(index));
					facilitatorList.remove(index);
				}
			}
		}
	}

	public class FacilitatorEditItemPage extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			index = table_eventPlanning_actualEvents_facilitators
					.getSelectionIndex();
			if (index < table_eventPlanning_actualEvents_facilitators
					.getItemCount() && index >= 0) {
				Shell facilitatorEditItemPage = new Shell(getDisplay());
				AbstractEdit facilitatorEditItem = new AbstractEdit(
						facilitatorEditItemPage, SWT.None,
						stringArrayFacilitator) {
					// setText
					public void onLoad() {
						for (int i = 0; i < stringArrayFacilitator.length; i++) {
							textList[i]
									.setText(table_eventPlanning_actualEvents_facilitators
											.getItem(index).getText(i));
						}
					}

					public void onSubmit() {
						Facilitator facilitator = facilitatorList.get(index);
						facilitator.setName(textList[0].getText());
						facilitator.setYear(Integer.parseInt(textList[1]
								.getText()));
						facilitator.setFaculty(textList[2].getText());
						facilitator.setPosition(textList[3].getText());
						// update database
						db.updateFacilitator(facilitator);
						// update the table
						for (int i = 0; i < stringArrayFacilitator.length; i++) {
							table_eventPlanning_actualEvents_facilitators
									.getItem(index).setText(i,
											textList[i].getText());
						}
					}
				};
				facilitatorEditItem.pack();
				facilitatorEditItemPage.pack();
				facilitatorEditItemPage.open();
			}
		}
	}

	// Facilitators end

	// Participants start
	public class ParticipantAddItemPage extends SelectionAdapter {

		public void widgetSelected(SelectionEvent e) {
			Shell participantAddItemPage = new Shell(getDisplay());
			AbstractAdd participantAddItem = new AbstractAdd(
					participantAddItemPage, SWT.None, stringArrayParticipant) {
				public void onSubmit() {
					// insert to database
					Participant participant = new Participant(
							textList[0].getText(), Integer.parseInt(textList[1]
									.getText()), textList[2].getText(),
							textList[3].getText());
					db.insertParticipant(event, participant);
					participantList.add(participant);
					// update the table
					TableItem item = new TableItem(
							table_eventPlanning_actualEvent_participants,
							SWT.NULL);
					for (int i = 0; i < stringArrayParticipant.length; i++) {
						item.setText(i, textList[i].getText());
					}
				}
			};
			participantAddItem.pack();
			participantAddItemPage.pack();
			participantAddItemPage.open();
		}
	}

	public class ParticipantDeleteItem extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (table_eventPlanning_actualEvent_participants.getColumnCount() != 0) {
				int index = table_eventPlanning_actualEvent_participants
						.getSelectionIndex();
				if (index < 0
						|| index >= table_eventPlanning_actualEvent_participants
								.getItemCount()) {
					// Do nothing.
				} else {
					table_eventPlanning_actualEvent_participants.remove(index);
					DatabaseReader db = new DatabaseReader();
					db.deleteMember(participantList.get(index));
					participantList.remove(index);
				}
			}
		}
	}

	public class ParticipantEditItemPage extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			index = table_eventPlanning_actualEvent_participants
					.getSelectionIndex();
			if (index < table_eventPlanning_actualEvent_participants
					.getItemCount() && index >= 0) {
				Shell participantEditItemPage = new Shell(getDisplay());
				AbstractEdit participantEditItem = new AbstractEdit(
						participantEditItemPage, SWT.None,
						stringArrayParticipant) {
					// setText
					public void onLoad() {
						for (int i = 0; i < stringArrayParticipant.length; i++) {
							textList[i]
									.setText(table_eventPlanning_actualEvent_participants
											.getItem(index).getText(i));
						}
					}

					public void onSubmit() {
						Participant participant = participantList.get(index);
						participant.setName(textList[0].getText());
						participant.setYear(Integer.parseInt(textList[1]
								.getText()));
						participant.setFaculty(textList[2].getText());
						participant.setFoodType(textList[3].getText());
						// update database
						db.updateParticipant(participant);
						// update the table
						for (int i = 0; i < stringArrayParticipant.length; i++) {
							table_eventPlanning_actualEvent_participants
									.getItem(index).setText(i,
											textList[i].getText());
						}
					}
				};
				participantEditItem.pack();
				participantEditItemPage.pack();
				participantEditItemPage.open();
			}
		}
	}
	// Participants end

}
