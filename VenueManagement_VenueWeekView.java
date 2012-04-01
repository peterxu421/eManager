
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;

public class VenueManagement_VenueWeekView extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table weekViewTable;
	private TableColumn tblclmnTimeSlot;
	private TableColumn[] tblclmn = new TableColumn[7];
	private CalendarCombo calendarCombo; 
	
	private DatabaseReader db = new DatabaseReader();
	private Venue selectedVenue = new Venue();
	private ArrayList<Date> dateInAWeekList = new ArrayList<Date>();  
	private Button btnViewAllApplications;
	private Label lblApproved;
	private Label lblApprovedColor;
	private Label lblPending;
	private Label lblPendingColor;
	                // an arrayList containing the the dates of the seven days in the week shown at the top row of the table

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VenueManagement_VenueWeekView(Composite parent, int style, int venueIndex) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		selectedVenue = db.getVenues().get(venueIndex);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(10, 0, 667, 496);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		weekViewTable = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		weekViewTable.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		weekViewTable.setBounds(10, 53, 630, 370);
		toolkit.adapt(weekViewTable);
		toolkit.paintBordersFor(weekViewTable);
		weekViewTable.setHeaderVisible(true);
		weekViewTable.setLinesVisible(true);
		
		weekViewTable.addListener(SWT.MouseDown, new Listener(){
			public void handleEvent(Event event){
				weekViewTable.deselectAll();
			}
		}); // disable table item selection
		
		tblclmnTimeSlot = new TableColumn(weekViewTable, SWT.CENTER);
		tblclmnTimeSlot.setWidth(100);

		for(int i=0; i<7; i++){
			tblclmn[i] = new TableColumn(weekViewTable, SWT.CENTER);
			tblclmn[i].setWidth(75);	
		}
		
		weekViewTable.addListener(SWT.EraseItem, new Listener()
		{
			public void handleEvent(Event event)
			{
				event.detail &= ~SWT.SELECTED;
			}			
		}); // Disable the default table selection

		Label lblEnterPreferredEvent = new Label(composite, SWT.NONE);
		lblEnterPreferredEvent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblEnterPreferredEvent.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblEnterPreferredEvent.setText("Choose the date");
		lblEnterPreferredEvent.setBounds(10, 24, 109, 23);
		toolkit.adapt(lblEnterPreferredEvent, true, true);
		
		Button btnCheck = new Button(composite, SWT.NONE);
		btnCheck.setBounds(240, 22, 75, 25);
		toolkit.adapt(btnCheck, true, true);
		btnCheck.setText("Check");
		btnCheck.addSelectionListener(new checkBookingInfo());
		
		btnViewAllApplications = new Button(composite, SWT.NONE);
		btnViewAllApplications.setBounds(10, 444, 124, 25);
		toolkit.adapt(btnViewAllApplications, true, true);
		btnViewAllApplications.setText("Application History");
		btnViewAllApplications.addSelectionListener(new viewAllApplications());
		
		calendarCombo = new CalendarCombo(composite, SWT.NONE);
		calendarCombo.setBounds(128, 24, 88, 23);
		toolkit.adapt(calendarCombo);
		toolkit.paintBordersFor(calendarCombo);
		calendarCombo.setDate(Calendar.getInstance());
		
		lblApproved = new Label(composite, SWT.NONE);
		lblApproved.setAlignment(SWT.RIGHT);
		lblApproved.setBounds(211, 449, 55, 15);
		toolkit.adapt(lblApproved, true, true);
		lblApproved.setText("Approved");
		
		lblApprovedColor = new Label(composite, SWT.NONE);
		lblApprovedColor.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		lblApprovedColor.setBounds(275, 449, 55, 15);
		
		lblPending = new Label(composite, SWT.NONE);
		lblPending.setAlignment(SWT.RIGHT);
		lblPending.setBounds(363, 449, 55, 15);
		toolkit.adapt(lblPending, true, true);
		lblPending.setText("Pending");
		
		lblPendingColor = new Label(composite, SWT.NONE);
		lblPendingColor.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		lblPendingColor.setBounds(424, 449, 55, 15);
		
	}
	
	/* Button selection adapters */
	public class checkBookingInfo extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			/* Clear the week view time table before start a new bookingInfo check */
			weekViewTable.removeAll();
			
			/*Fill up time slots in the table */
			tblclmnTimeSlot.setText("Time Slot/Date");
			for (int i = 6; i<23; i++){
				TableItem item = new TableItem(weekViewTable, SWT.NULL);
				item.setText(0, String.format("%02d", i) + ":00" + ":00");
			}
			
			/* Get the preferred event date */
			Calendar calendar = new GregorianCalendar();
			calendar.clear();
			calendar = calendarCombo.getDate();
			
			/* Arrange the week view table */
			int day_of_week;
			tblclmn[3].setToolTipText("Chosen date");
			for(int i=0; i<7; i++){
				dateInAWeekList.add(new Date(0,0,0)); // initialize the arraylist
			}
			for(int i=3; i<7; i++){ // right hand side half
				day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
				tblclmnTextFill(day_of_week, calendar, tblclmn[i]);
				Date date = new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH)); // MONTH+1 because MONTH starts from 0 in default
				dateInAWeekList.set(i, date);
				calendar.add(Calendar.DAY_OF_MONTH, 1);  // roll the date forward
			} 
			calendar.add(Calendar.DAY_OF_MONTH, -4); // roll back to the selected date
			for(int i=3; i>=0; i--){
				day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
				tblclmnTextFill(day_of_week, calendar, tblclmn[i]);
				Date date = new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH)); // MONTH+1 because MONTH starts from 0 in default
				dateInAWeekList.set(i, date);
				calendar.add(Calendar.DAY_OF_MONTH, -1); // roll the date backward
			}
			
			
			/* Get bookingInfo of the venue from database */
			ArrayList<VenueBookingApplication> bookingInfoList = db.getVenueBookingInfo(selectedVenue);
			if (!bookingInfoList.isEmpty()){
				for (int i=0; i<bookingInfoList.size(); i++){
					if(bookingInfoList.get(i).getStatus() == MACRO.PENDING || bookingInfoList.get(i).getStatus() == MACRO.APPROVED){
			
					}
				    BookedDateTime dateTime = bookingInfoList.get(i).getDateTime();
				    for (int j=0; j<dateInAWeekList.size(); j++){
				    	if(dateInAWeekList.get(j).isEqualTo(dateTime.getDate())){ // find the booked date
				    		for(int k=0; k<weekViewTable.getItemCount();k++){
				    			if(Time.parseHour(weekViewTable.getItem(k).getText(0)) == dateTime.getTimeStart().getHour()){ // find the booked time
				    				for (int t=0; t<(dateTime.getTimeEnd().getHour()-dateTime.getTimeStart().getHour()); t++){	
				    					if(bookingInfoList.get(i).getStatus() == MACRO.APPROVED){
				    						weekViewTable.getItem(k).setText(j+1, bookingInfoList.get(i).getApplicant().getOrganization());
				    						Color approvedColor = weekViewTable.getDisplay().getSystemColor(SWT.COLOR_GREEN);
				    						weekViewTable.getItem(k).setBackground(j+1, approvedColor);
				    					}	
				    					if(bookingInfoList.get(i).getStatus() == MACRO.PENDING){
				    						weekViewTable.getItem(k).setText(j+1, bookingInfoList.get(i).getApplicant().getOrganization());
				    						Color pendingColor = weekViewTable.getDisplay().getSystemColor(SWT.COLOR_YELLOW);
				    						weekViewTable.getItem(k).setBackground(j+1, pendingColor);
				    					}
				    					k++; // move to the next hour in the week view time table
				    				}
				    			}
				    		}
				    	} 
				    }
				}
			}	
		}
		
		public void tblclmnTextFill(int day_of_week, Calendar calendar, TableColumn tblclmn){
			switch (day_of_week){
			case 1: tblclmn.setText("Sun " + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DAY_OF_MONTH)); break;
			case 2: tblclmn.setText("Mon " + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DAY_OF_MONTH)); break;
			case 3: tblclmn.setText("Tue " + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DAY_OF_MONTH)); break;
			case 4: tblclmn.setText("Wed " + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DAY_OF_MONTH)); break;
			case 5: tblclmn.setText("Thu " + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DAY_OF_MONTH)); break;
			case 6: tblclmn.setText("Fri " + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DAY_OF_MONTH)); break;
			case 7: tblclmn.setText("Sat " + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DAY_OF_MONTH)); break;
			}
		}
	}
	
	public class viewAllApplications extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e){
			Shell viewBookingInfoShell = new Shell(getDisplay());
			Image icon = new Image(getDisplay(), "resources/eManager.png");
			viewBookingInfoShell.setText("eManager");
			viewBookingInfoShell.setImage(icon);
			
			VenueViewBookingInfo viewBookingInfoPage = new VenueViewBookingInfo(viewBookingInfoShell, SWT.None, selectedVenue, weekViewTable);
			viewBookingInfoPage.pack();
			viewBookingInfoShell.pack();
			viewBookingInfoShell.open();
		}
	}
}


