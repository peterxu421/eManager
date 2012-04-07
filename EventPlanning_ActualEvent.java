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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class EventPlanning_ActualEvent extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table table_eventPlanning_actualEvent_tableItinerary;
	private Table table_eventPlanning_actualEvent_participants;
	private Table table_eventPlanning_actualEvents_allocOfManpower;
	private Table table_eventPlanning_actualEvents_facilitators;
	private Table table_eventPlanning_actualEvents_packingList;
	private Event event;
	private ArrayList<Itinerary> itineraryList;
	private ArrayList<ManpowerAllocation> manpowerList;
	private ArrayList<PackingItem> packingList;
	private ArrayList<Facilitator> facilitatorList;
	private ArrayList<Participant> participantList;
	private int index;
	// Attributes for each table.
	private String[] stringArrayItinerary = { "Description", "Date", "Time",
			"Done" };
	private int[] signatureItinerary = { MACRO.TEXTBIG, MACRO.DATE, MACRO.TIME,
			MACRO.CHECK };
	private String[] stringArrayPackingList = { "Category", "Item", "Quantity",
			"Remarks" };
	private int[] signaturePackingList = { MACRO.TEXT, MACRO.TEXT, MACRO.INT,
			MACRO.TEXTBIG };
	private String[] stringArrayAllocationOfManpower = { "Task", "Assigned To",
			"Date", "Done" };
	private int[] signatureAllocationOfManpower = { MACRO.TEXT,
			MACRO.FACILITATOR, MACRO.DATE, MACRO.CHECK };
	private String[] stringArrayFacilitator = { "Name", "Year", "Faculty",
			"Food Type" };
	private int[] signatureFacilitator = { MACRO.TEXT, MACRO.YEAR,
			MACRO.FACULTY, MACRO.TEXT };
	private String[] stringArrayParticipant = { "Name", "Year", "Faculty",
			"Food Type" };
	private int[] signatureParticipant = { MACRO.TEXT, MACRO.YEAR,
			MACRO.FACULTY, MACRO.TEXT };

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

		TabFolder folder_eventPlanning_actualEvent = new TabFolder(this,
				SWT.NONE);
		folder_eventPlanning_actualEvent.setLocation(0, 0);
		folder_eventPlanning_actualEvent.setSize(673, 500);
		// tabFolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		folder_eventPlanning_actualEvent.setToolTipText("Itinerary");
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
		table_eventPlanning_actualEvent_tableItinerary.setBounds(10, 10, 550,
				400);
		toolkit.adapt(table_eventPlanning_actualEvent_tableItinerary);
		toolkit.paintBordersFor(table_eventPlanning_actualEvent_tableItinerary);
		table_eventPlanning_actualEvent_tableItinerary.setHeaderVisible(true);
		table_eventPlanning_actualEvent_tableItinerary.setLinesVisible(true);

		// itinerary
		TableColumn col_eventPlanning_actualEvent__itinerary_description = new TableColumn(
				table_eventPlanning_actualEvent_tableItinerary, SWT.CENTER);
		col_eventPlanning_actualEvent__itinerary_description.setWidth(220);
		col_eventPlanning_actualEvent__itinerary_description
				.setText("Description");

		TableColumn col_eventPlanning_actualEvent__itinerary_date = new TableColumn(
				table_eventPlanning_actualEvent_tableItinerary, SWT.CENTER);
		col_eventPlanning_actualEvent__itinerary_date.setWidth(120);
		col_eventPlanning_actualEvent__itinerary_date.setText("Date");

		TableColumn col_eventPlanning_actualEvent__itinerary_time = new TableColumn(
				table_eventPlanning_actualEvent_tableItinerary, SWT.CENTER);
		col_eventPlanning_actualEvent__itinerary_time.setWidth(120);
		col_eventPlanning_actualEvent__itinerary_time.setText("Time");

		TableColumn col_eventPlanning_actualEvent__itinerary_done = new TableColumn(
				table_eventPlanning_actualEvent_tableItinerary, SWT.CENTER);
		col_eventPlanning_actualEvent__itinerary_done.setWidth(80);
		col_eventPlanning_actualEvent__itinerary_done.setText("Done");

		Button btn_eventPlanning_actualEvent_Itinerary_add = new Button(
				comp_eventPlanning_actualEvent_Itinerary, SWT.NONE);
		btn_eventPlanning_actualEvent_Itinerary_add
				.addSelectionListener(new ItineraryAddItemPage());
		btn_eventPlanning_actualEvent_Itinerary_add.setBounds(570, 10, 80, 40);
		toolkit.adapt(btn_eventPlanning_actualEvent_Itinerary_add, true, true);
		btn_eventPlanning_actualEvent_Itinerary_add.setText("Add ");

		Button btn_eventPlanning_actualEvent_Itinerary_del = new Button(
				comp_eventPlanning_actualEvent_Itinerary, SWT.NONE);
		btn_eventPlanning_actualEvent_Itinerary_del
				.addSelectionListener(new ItineraryDeleteItem());
		btn_eventPlanning_actualEvent_Itinerary_del.setBounds(570, 60, 80, 40);
		toolkit.adapt(btn_eventPlanning_actualEvent_Itinerary_del, true, true);
		btn_eventPlanning_actualEvent_Itinerary_del.setText("Delete");

		Button btn_eventPlanning_actualEvent_Itinerary_edit = new Button(
				comp_eventPlanning_actualEvent_Itinerary, SWT.NONE);
		btn_eventPlanning_actualEvent_Itinerary_edit
				.addSelectionListener(new ItineraryEditItemPage());
		btn_eventPlanning_actualEvent_Itinerary_edit
				.setBounds(570, 110, 80, 40);
		toolkit.adapt(btn_eventPlanning_actualEvent_Itinerary_edit, true, true);
		btn_eventPlanning_actualEvent_Itinerary_edit.setText("Edit ");

		TabItem tbtmPackingList = new TabItem(folder_eventPlanning_actualEvent,
				SWT.NONE);
		tbtmPackingList.setText("Packing List");

		Composite compositePackingList = new Composite(
				folder_eventPlanning_actualEvent, SWT.NONE);
		tbtmPackingList.setControl(compositePackingList);
		toolkit.paintBordersFor(compositePackingList);

		// PackingList
		table_eventPlanning_actualEvents_packingList = new Table(
				compositePackingList, SWT.BORDER | SWT.FULL_SELECTION);
		table_eventPlanning_actualEvents_packingList
				.setBounds(10, 10, 550, 400);
		toolkit.adapt(table_eventPlanning_actualEvents_packingList);
		toolkit.paintBordersFor(table_eventPlanning_actualEvents_packingList);
		table_eventPlanning_actualEvents_packingList.setHeaderVisible(true);
		table_eventPlanning_actualEvents_packingList.setLinesVisible(true);

		TableColumn tblclmnCategory = new TableColumn(
				table_eventPlanning_actualEvents_packingList, SWT.CENTER);
		tblclmnCategory.setWidth(150);
		tblclmnCategory.setText("Category");

		TableColumn tblclmnItem = new TableColumn(
				table_eventPlanning_actualEvents_packingList, SWT.CENTER);
		tblclmnItem.setWidth(100);
		tblclmnItem.setText("Item");

		TableColumn tblclmnQuantity = new TableColumn(
				table_eventPlanning_actualEvents_packingList, SWT.CENTER);
		tblclmnQuantity.setWidth(80);
		tblclmnQuantity.setText("Quantity");

		TableColumn tblclmnRemark = new TableColumn(
				table_eventPlanning_actualEvents_packingList, SWT.CENTER);
		tblclmnRemark.setWidth(220);
		tblclmnRemark.setText("Remark");

		Button btnPackingListAdd = new Button(compositePackingList, SWT.NONE);
		btnPackingListAdd.addSelectionListener(new PackingListAddItemPage());
		btnPackingListAdd.setText("Add");
		btnPackingListAdd.setBounds(570, 10, 80, 40);
		toolkit.adapt(btnPackingListAdd, true, true);

		Button btnPackingListDelete = new Button(compositePackingList, SWT.NONE);
		btnPackingListDelete.addSelectionListener(new PackingListDeleteItem());
		btnPackingListDelete.setText("Delete");
		btnPackingListDelete.setBounds(570, 60, 80, 40);
		toolkit.adapt(btnPackingListDelete, true, true);

		Button btnPackingListEdit = new Button(compositePackingList, SWT.NONE);
		btnPackingListEdit.addSelectionListener(new PackingListEditItemPage());
		btnPackingListEdit.setText("Edit");
		btnPackingListEdit.setBounds(570, 110, 80, 40);
		toolkit.adapt(btnPackingListEdit, true, true);

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
		table_eventPlanning_actualEvents_allocOfManpower.setBounds(10, 10, 550,
				400);
		toolkit.adapt(table_eventPlanning_actualEvents_allocOfManpower);
		toolkit.paintBordersFor(table_eventPlanning_actualEvents_allocOfManpower);

		TableColumn col_eventPlanning_actualEvents_allocOfManpower_task = new TableColumn(
				table_eventPlanning_actualEvents_allocOfManpower, SWT.CENTER);
		col_eventPlanning_actualEvents_allocOfManpower_task.setWidth(240);
		col_eventPlanning_actualEvents_allocOfManpower_task.setText("Task");

		TableColumn col_eventPlanning_actualEvents_allocOfManpower_assignedTo = new TableColumn(
				table_eventPlanning_actualEvents_allocOfManpower, SWT.CENTER);
		col_eventPlanning_actualEvents_allocOfManpower_assignedTo.setWidth(120);
		col_eventPlanning_actualEvents_allocOfManpower_assignedTo
				.setText("Assigned To");

		TableColumn col_eventPlanning_actualEvents_allocOfManpower_dateDue = new TableColumn(
				table_eventPlanning_actualEvents_allocOfManpower, SWT.CENTER);
		col_eventPlanning_actualEvents_allocOfManpower_dateDue.setWidth(120);
		col_eventPlanning_actualEvents_allocOfManpower_dateDue
				.setText("Date Due");

		TableColumn col_eventPlanning_actualEvents_allocOfManpower_done = new TableColumn(
				table_eventPlanning_actualEvents_allocOfManpower, SWT.CENTER);
		col_eventPlanning_actualEvents_allocOfManpower_done.setWidth(80);
		col_eventPlanning_actualEvents_allocOfManpower_done.setText("Done");

		Button btn_eventPlanning_actualEvent_AllocationManpower_add = new Button(
				composite_eventPlanning_actualEvents_allocOfManpower, SWT.NONE);
		btn_eventPlanning_actualEvent_AllocationManpower_add
				.addSelectionListener(new AllocOfManpowerAddItemPage());
		btn_eventPlanning_actualEvent_AllocationManpower_add.setText("Add");
		btn_eventPlanning_actualEvent_AllocationManpower_add.setBounds(570, 10,
				80, 40);
		toolkit.adapt(btn_eventPlanning_actualEvent_AllocationManpower_add,
				true, true);

		Button btn_eventPlanning_actualEvent_AllocationManpower_del = new Button(
				composite_eventPlanning_actualEvents_allocOfManpower, SWT.NONE);
		btn_eventPlanning_actualEvent_AllocationManpower_del
				.addSelectionListener(new AllocOfManpowerDelItem());
		btn_eventPlanning_actualEvent_AllocationManpower_del.setText("Delete");
		btn_eventPlanning_actualEvent_AllocationManpower_del.setBounds(570, 60,
				80, 40);
		toolkit.adapt(btn_eventPlanning_actualEvent_AllocationManpower_del,
				true, true);

		Button btn_eventPlanning_actualEvent_AllocationManpower_edit = new Button(
				composite_eventPlanning_actualEvents_allocOfManpower, SWT.NONE);
		btn_eventPlanning_actualEvent_AllocationManpower_edit
				.addSelectionListener(new AllocOfManpowerEditItemPage());
		btn_eventPlanning_actualEvent_AllocationManpower_edit.setText("Edit");
		btn_eventPlanning_actualEvent_AllocationManpower_edit.setBounds(570,
				110, 80, 40);
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
		table_eventPlanning_actualEvents_facilitators.setBounds(10, 10, 550,
				400);
		toolkit.adapt(table_eventPlanning_actualEvents_facilitators);
		toolkit.paintBordersFor(table_eventPlanning_actualEvents_facilitators);

		TableColumn col_eventPlanning_actualEvents_facilitators_name = new TableColumn(
				table_eventPlanning_actualEvents_facilitators, SWT.CENTER);
		col_eventPlanning_actualEvents_facilitators_name.setWidth(170);
		col_eventPlanning_actualEvents_facilitators_name.setText("Name");

		TableColumn col_eventPlanning_actualEvents_facilitators_year = new TableColumn(
				table_eventPlanning_actualEvents_facilitators, SWT.CENTER);
		col_eventPlanning_actualEvents_facilitators_year.setWidth(60);
		col_eventPlanning_actualEvents_facilitators_year.setText("Year");

		TableColumn col_eventPlanning_actualEvents_facilitators_interestedIn = new TableColumn(
				table_eventPlanning_actualEvents_facilitators, SWT.CENTER);
		col_eventPlanning_actualEvents_facilitators_interestedIn.setWidth(120);
		col_eventPlanning_actualEvents_facilitators_interestedIn
				.setText("Faculty");

		TableColumn col_eventPlanning_actualEvents_facilitators_status = new TableColumn(
				table_eventPlanning_actualEvents_facilitators, SWT.CENTER);
		col_eventPlanning_actualEvents_facilitators_status.setWidth(200);
		col_eventPlanning_actualEvents_facilitators_status.setText("Food Type");

		Button btnFacilitatorsAddItem = new Button(
				composite_eventPlanning_actualEvents_facilitators, SWT.NONE);
		btnFacilitatorsAddItem
				.addSelectionListener(new FacilitatorAddItemPage());
		btnFacilitatorsAddItem.setText("Add ");
		btnFacilitatorsAddItem.setBounds(570, 10, 80, 40);
		toolkit.adapt(btnFacilitatorsAddItem, true, true);

		Button btnFacilitatorsDelItem = new Button(
				composite_eventPlanning_actualEvents_facilitators, SWT.NONE);
		btnFacilitatorsDelItem
				.addSelectionListener(new FacilitatorDeleteItem());
		btnFacilitatorsDelItem.setText("Delete");
		btnFacilitatorsDelItem.setBounds(570, 60, 80, 40);
		toolkit.adapt(btnFacilitatorsDelItem, true, true);

		Button btnFacilitatorsEditItem = new Button(
				composite_eventPlanning_actualEvents_facilitators, SWT.NONE);
		btnFacilitatorsEditItem
				.addSelectionListener(new FacilitatorEditItemPage());
		btnFacilitatorsEditItem.setText("Edit");
		btnFacilitatorsEditItem.setBounds(570, 110, 80, 40);
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
				.setBounds(10, 10, 550, 400);
		toolkit.adapt(table_eventPlanning_actualEvent_participants);
		toolkit.paintBordersFor(table_eventPlanning_actualEvent_participants);
		table_eventPlanning_actualEvent_participants.setHeaderVisible(true);
		table_eventPlanning_actualEvent_participants.setLinesVisible(true);

		TableColumn col_eventPlanning_actualEvent_participants_participants_name = new TableColumn(
				table_eventPlanning_actualEvent_participants, SWT.CENTER);
		col_eventPlanning_actualEvent_participants_participants_name
				.setWidth(170);
		col_eventPlanning_actualEvent_participants_participants_name
				.setText("Name");

		TableColumn col_eventPlanning_actualEvent_participants_participants_age = new TableColumn(
				table_eventPlanning_actualEvent_participants, SWT.CENTER);
		col_eventPlanning_actualEvent_participants_participants_age
				.setWidth(60);
		col_eventPlanning_actualEvent_participants_participants_age
				.setText("Year");

		TableColumn col_eventPlanning_actualEvent_participants_participants_faculty = new TableColumn(
				table_eventPlanning_actualEvent_participants, SWT.CENTER);
		col_eventPlanning_actualEvent_participants_participants_faculty
				.setWidth(120);
		col_eventPlanning_actualEvent_participants_participants_faculty
				.setText("Faculty");

		TableColumn col_eventPlanning_actualEvent_participants_participants_foodType = new TableColumn(
				table_eventPlanning_actualEvent_participants, SWT.CENTER);
		col_eventPlanning_actualEvent_participants_participants_foodType
				.setWidth(200);
		col_eventPlanning_actualEvent_participants_participants_foodType
				.setText("Food Type");

		Button btn_eventPlanning_actualEvent_participants_participants_add = new Button(
				composite_eventPlanning_actualEvent_participants, SWT.NONE);
		btn_eventPlanning_actualEvent_participants_participants_add
				.addSelectionListener(new ParticipantAddItemPage());
		btn_eventPlanning_actualEvent_participants_participants_add.setBounds(
				570, 10, 80, 40);
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
				570, 60, 80, 40);
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
				570, 110, 80, 40);
		toolkit.adapt(
				btn_eventPlanning_actualEvent_participants_participants_edit,
				true, true);
		btn_eventPlanning_actualEvent_participants_participants_edit
				.setText("Edit ");

		// Fill up all the table
		importItineraryData();
		importPackingListData();
		importManpowerAllocationData();
		importFacilitorData();
		importParticipantData();

	}

	// Fill up itineray table
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
				temp.setText(3, "true");
			else
				temp.setText(3, "false");
		}
	}

	// Fill up PackingList table
	public void importPackingListData() {
		DatabaseReader db = new DatabaseReader();
		packingList = db.getPackingItems(event);

		for (int i = 0; i < packingList.size(); i++) {
			TableItem temp = new TableItem(
					table_eventPlanning_actualEvents_packingList, SWT.NULL);
			temp.setText(0, packingList.get(i).getCategory());
			temp.setText(1, packingList.get(i).getName());
			temp.setText(2, Integer.toString(packingList.get(i).getQuantity()));
			temp.setText(3, packingList.get(i).getRemarks());
		}
	}

	// Fill up ManpowerAllocation table
	public void importManpowerAllocationData() {
		DatabaseReader db = new DatabaseReader();
		manpowerList = db.getManpowerAllocation(event);

		for (int i = 0; i < packingList.size(); i++) {
			TableItem temp = new TableItem(
					table_eventPlanning_actualEvents_allocOfManpower, SWT.NULL);
			temp.setText(0, manpowerList.get(i).getTaskDescription());
			temp.setText(1, manpowerList.get(i).getAssignedTo());
			temp.setText(2, manpowerList.get(i).getDate().toString());
			if (manpowerList.get(i).isDone() == true)
				temp.setText(3, "true");
			else
				temp.setText(3, "false");
		}
	}

	// Fill up Facilitator table
	public void importFacilitorData() {
		DatabaseReader db = new DatabaseReader();
		facilitatorList = db.getFacilitators(event);

		for (int i = 0; i < facilitatorList.size(); i++) {
			TableItem temp = new TableItem(
					table_eventPlanning_actualEvents_facilitators, SWT.NULL);
			temp.setText(0, facilitatorList.get(i).getName());
			temp.setText(1, Integer.toString(facilitatorList.get(i).getYear()));
			temp.setText(2, facilitatorList.get(i).getFaculty());
			temp.setText(3, facilitatorList.get(i).getFoodType());
		}
	}

	// Fill up Participant table
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
	// Add item page
	public class ItineraryAddItemPage extends SelectionAdapter {

		public void widgetSelected(SelectionEvent e) {
			Shell itineraryAddItemPage = new Shell(getDisplay(),
					SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
			AbstractAdd itineraryAddItem = new AbstractAdd(
					itineraryAddItemPage, SWT.None, stringArrayItinerary,
					signatureItinerary,
					table_eventPlanning_actualEvent_tableItinerary) {
				public void onSubmit() {
					// insert to database
					String[] tempList = getStringList();
					Date date = new Date(tempList[1]);
					Time time = new Time(tempList[2]);
					boolean isDone = Boolean.parseBoolean(tempList[3]);
					Itinerary itinerary = new Itinerary(tempList[0], date,
							time, isDone);
					db.insertItinerary(event, itinerary);
					itineraryList.add(itinerary);
					// update the table
					TableItem item = new TableItem(
							table_eventPlanning_actualEvent_tableItinerary,
							SWT.NULL);
					for (int i = 0; i < stringArrayItinerary.length; i++) {
						item.setText(i, tempList[i]);
					}
				}
			};
			itineraryAddItem.pack();
			itineraryAddItemPage.pack();
			itineraryAddItemPage.open();
		}
	}

	// Delete table item
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

	// Edit table item
	public class ItineraryEditItemPage extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			index = table_eventPlanning_actualEvent_tableItinerary
					.getSelectionIndex();
			if (index < table_eventPlanning_actualEvent_tableItinerary
					.getItemCount() && index >= 0) {
				Shell itineraryEditItemPage = new Shell(getDisplay(),
						SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
				AbstractEdit itineraryEditItem = new AbstractEdit(
						itineraryEditItemPage, SWT.None, stringArrayItinerary,
						signatureItinerary) {
					// setText
					public void onLoad() {
						for (int i = 0; i < stringArrayItinerary.length; i++) {
							setData(table_eventPlanning_actualEvent_tableItinerary
									.getItem(index).getText(i),
									signatureItinerary[i], i);
						}
					}

					public void onSubmit() {
						String[] tempList = getStringList();
						Itinerary itinerary = itineraryList.get(index);
						itinerary.setItineraryDetails(tempList[0]);
						itinerary.setDate(new Date(tempList[1]));
						itinerary.setTime(new Time(tempList[2]));
						boolean isDone = Boolean.parseBoolean(tempList[3]);
						itinerary.setDone(isDone);
						// update database
						db.updateItinerary(itinerary);
						// update the table
						for (int i = 0; i < stringArrayItinerary.length; i++) {
							table_eventPlanning_actualEvent_tableItinerary
									.getItem(index).setText(i, tempList[i]);
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

	// PackingList start
	// Add item page
	public class PackingListAddItemPage extends SelectionAdapter {

		public void widgetSelected(SelectionEvent e) {
			Shell packingListAddItemPage = new Shell(getDisplay(),
					SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
			AbstractAdd packingListAddItem = new AbstractAdd(
					packingListAddItemPage, SWT.None, stringArrayPackingList,
					signaturePackingList,
					table_eventPlanning_actualEvents_packingList) {
				public void onSubmit() {
					// insert to database
					String[] tempList = getStringList();
					PackingItem packingItem = new PackingItem(tempList[0],
							tempList[1], Integer.parseInt(tempList[2]),
							tempList[3]);
					db.insertPackingItem(event, packingItem);
					packingList.add(packingItem);
					// update the table
					TableItem item = new TableItem(
							table_eventPlanning_actualEvents_packingList,
							SWT.NULL);
					for (int i = 0; i < stringArrayItinerary.length; i++) {
						item.setText(i, tempList[i]);
					}
				}
			};
			packingListAddItem.pack();
			packingListAddItemPage.pack();
			packingListAddItemPage.open();
		}
	}

	// Delete table item
	public class PackingListDeleteItem extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (table_eventPlanning_actualEvents_packingList.getColumnCount() != 0) {
				int index = table_eventPlanning_actualEvents_packingList
						.getSelectionIndex();
				if (index < 0
						|| index >= table_eventPlanning_actualEvents_packingList
								.getItemCount()) {
					// Do nothing.
				} else {
					/* update the itinerary table */
					table_eventPlanning_actualEvents_packingList.remove(index);
					/* update the database */
					DatabaseReader db = new DatabaseReader();
					db.deletePackingItem(packingList.get(index));
					packingList.remove(index);
				}
			}
		}
	}

	// Edit table item
	public class PackingListEditItemPage extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			index = table_eventPlanning_actualEvents_packingList
					.getSelectionIndex();
			if (index < table_eventPlanning_actualEvents_packingList
					.getItemCount() && index >= 0) {
				Shell packingListEditItemPage = new Shell(getDisplay(),
						SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
				AbstractEdit packingListEditItem = new AbstractEdit(
						packingListEditItemPage, SWT.None,
						stringArrayItinerary, signatureItinerary) {
					// setText
					public void onLoad() {
						for (int i = 0; i < stringArrayPackingList.length; i++) {
							setData(table_eventPlanning_actualEvents_packingList
									.getItem(index).getText(i),
									signaturePackingList[i], i);
						}
					}

					public void onSubmit() {
						String[] tempList = getStringList();
						PackingItem packingItem = packingList.get(index);
						packingItem.setCategory(tempList[0]);
						packingItem.setName(tempList[1]);
						packingItem.setQuantity(Integer.parseInt(tempList[2]));
						packingItem.setRemarks(tempList[3]);
						// update database
						db.updatePackingItem(packingItem);
						// update the table
						for (int i = 0; i < stringArrayPackingList.length; i++) {
							table_eventPlanning_actualEvents_packingList
									.getItem(index).setText(i, tempList[i]);
						}
					}
				};
				packingListEditItem.pack();
				packingListEditItemPage.pack();
				packingListEditItemPage.open();
			}
		}
	}

	// PackingList end

	// AllocOfManpower start
	public class AllocOfManpowerAddItemPage extends SelectionAdapter {

		public void widgetSelected(SelectionEvent e) {
			Shell allocOfManpowerAddItemPage = new Shell(getDisplay(),
					SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
			AbstractAdd allocOfManpowerAddItem = new AbstractAdd(
					allocOfManpowerAddItemPage, SWT.None,
					stringArrayAllocationOfManpower,
					signatureAllocationOfManpower,
					table_eventPlanning_actualEvents_allocOfManpower) {
				public void onSubmit() {
					// insert to database
					String[] tempList = getStringList();
					Date date = new Date(tempList[2]);
					boolean isDone = Boolean.parseBoolean(tempList[3]);
					ManpowerAllocation manpowerAllocation = new ManpowerAllocation(
							tempList[0], tempList[1], date, isDone);
					db.insertManpowerAllocation(event, manpowerAllocation);
					manpowerList.add(manpowerAllocation);
					// update the table
					TableItem item = new TableItem(
							table_eventPlanning_actualEvents_allocOfManpower,
							SWT.NULL);
					for (int i = 0; i < stringArrayAllocationOfManpower.length; i++) {
						item.setText(i, tempList[i]);
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
				Shell allocOfManpowerEditItemPage = new Shell(getDisplay(),
						SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
				AbstractEdit allocOfManpowerEditItem = new AbstractEdit(
						allocOfManpowerEditItemPage, SWT.None,
						stringArrayAllocationOfManpower,
						signatureAllocationOfManpower) {
					// setText
					public void onLoad() {
						for (int i = 0; i < stringArrayAllocationOfManpower.length; i++) {
							setData(table_eventPlanning_actualEvents_allocOfManpower
									.getItem(index).getText(i),
									signatureAllocationOfManpower[i], i);
						}
					}

					public void onSubmit() {
						String[] tempList = getStringList();
						ManpowerAllocation manpowerAllocation = manpowerList
								.get(index);
						manpowerAllocation.setTaskDescription(tempList[0]);
						manpowerAllocation.setAssignedTo(tempList[1]);
						manpowerAllocation.setDate(new Date(tempList[2]));
						boolean isDone = Boolean.parseBoolean(tempList[3]);
						manpowerAllocation.setDone(isDone);
						// update database
						db.updateManpowerAllocation(manpowerAllocation);
						// update the table
						for (int i = 0; i < stringArrayAllocationOfManpower.length; i++) {
							table_eventPlanning_actualEvents_allocOfManpower
									.getItem(index).setText(i, tempList[i]);
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
			Shell facilitatorAddItemPage = new Shell(getDisplay(),
					SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
			AbstractAdd facilitatorAddItem = new AbstractAdd(
					facilitatorAddItemPage, SWT.None, stringArrayFacilitator,
					signatureFacilitator,
					table_eventPlanning_actualEvents_facilitators) {
				public void onSubmit() {
					// insert to database
					String[] tempList = getStringList();
					Facilitator facilitator = new Facilitator(tempList[0],
							Integer.parseInt(tempList[1]), tempList[2],
							tempList[3]);
					db.insertFacilitator(event, facilitator);
					facilitatorList.add(facilitator);
					// update the table
					TableItem item = new TableItem(
							table_eventPlanning_actualEvents_facilitators,
							SWT.NULL);
					for (int i = 0; i < stringArrayFacilitator.length; i++) {
						item.setText(i, tempList[i]);
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
				Shell facilitatorEditItemPage = new Shell(getDisplay(),
						SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
				AbstractEdit facilitatorEditItem = new AbstractEdit(
						facilitatorEditItemPage, SWT.None,
						stringArrayFacilitator, signatureFacilitator) {
					// setText
					public void onLoad() {
						for (int i = 0; i < stringArrayFacilitator.length; i++) {
							setData(table_eventPlanning_actualEvents_facilitators
									.getItem(index).getText(i),
									signatureFacilitator[i], i);
						}
					}

					public void onSubmit() {
						String[] tempList = getStringList();
						Facilitator facilitator = facilitatorList.get(index);
						facilitator.setName(tempList[0]);
						facilitator.setYear(Integer.parseInt(tempList[1]));
						facilitator.setFaculty(tempList[2]);
						facilitator.setPosition(tempList[3]);
						// update database
						db.updateFacilitator(facilitator);
						// update the table
						for (int i = 0; i < stringArrayFacilitator.length; i++) {
							table_eventPlanning_actualEvents_facilitators
									.getItem(index).setText(i, tempList[i]);
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
			Shell participantAddItemPage = new Shell(getDisplay(),
					SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
			AbstractAdd participantAddItem = new AbstractAdd(
					participantAddItemPage, SWT.None, stringArrayParticipant,
					signatureParticipant,
					table_eventPlanning_actualEvent_participants) {
				public void onSubmit() {
					// insert to database
					String[] tempList = getStringList();
					Participant participant = new Participant(tempList[0],
							Integer.parseInt(tempList[1]), tempList[2],
							tempList[3]);
					db.insertParticipant(event, participant);
					participantList.add(participant);
					// update the table
					TableItem item = new TableItem(
							table_eventPlanning_actualEvent_participants,
							SWT.NULL);
					for (int i = 0; i < stringArrayParticipant.length; i++) {
						item.setText(i, tempList[i]);
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
				Shell participantEditItemPage = new Shell(getDisplay(),
						SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
				AbstractEdit participantEditItem = new AbstractEdit(
						participantEditItemPage, SWT.None,
						stringArrayParticipant, signatureParticipant) {
					// setText
					public void onLoad() {
						for (int i = 0; i < stringArrayParticipant.length; i++) {
							setData(table_eventPlanning_actualEvent_participants
									.getItem(index).getText(i),
									signatureParticipant[i], i);
						}
					}

					public void onSubmit() {
						String[] tempList = getStringList();
						Participant participant = participantList.get(index);
						participant.setName(tempList[0]);
						participant.setYear(Integer.parseInt(tempList[1]));
						participant.setFaculty(tempList[2]);
						participant.setFoodType(tempList[3]);
						// update database
						db.updateParticipant(participant);
						// update the table
						for (int i = 0; i < stringArrayParticipant.length; i++) {
							table_eventPlanning_actualEvent_participants
									.getItem(index).setText(i, tempList[i]);
						}
					}
				};
				participantEditItem.pack();
				participantEditItemPage.pack();
				participantEditItemPage.open();
			}
		}
	}
}
