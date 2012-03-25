
import java.util.ArrayList;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
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
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.GregorianCalendar;

public class VenueBooking_VenueWeekView extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table weekViewTable;
	private TableColumn tblclmnTimeSlot;
	private TableColumn[] tblclmn = new TableColumn[7];
	private DateTime preferredEventDate;
	private Color cellColor;
	private List selectedDateTimeList;
	
	private DatabaseReader db = new DatabaseReader();
	private Venue selectedVenue = new Venue();
	
	private ArrayList<Date> dateInAWeekList = new ArrayList<Date>();  
	                // an arrayList containing the the dates of the seven days in the week shown at the top row of the table
	private ArrayList<BookedDateTime> bookedDateTimeList = new ArrayList<BookedDateTime>();
	                // an arrayList storing the booked date and time in the format of BookedDateTime

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VenueBooking_VenueWeekView(Composite parent, int style, int venueIndex) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(10, 0, 888, 496);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		selectedVenue = db.getVenues().get(venueIndex);
		
		weekViewTable = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		weekViewTable.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		weekViewTable.setBounds(10, 53, 630, 365);
		toolkit.adapt(weekViewTable);
		toolkit.paintBordersFor(weekViewTable);
		weekViewTable.setHeaderVisible(true);
		weekViewTable.setLinesVisible(true);
		
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
		
		
		
		/* trials to remove row highlighting 
		weekViewTable.addListener(SWT.MouseEnter, new Listener(){
			@Override
			public void handleEvent(Event event){
				return; // do nothing;
			}
		});
		
		Listener[] listeners = weekViewTable.getListeners(SWT.MouseEnter);
		for(int j=0; j<listeners.length; j++){
			weekViewTable.removeListener(SWT.MouseEnter, listeners[j]);
			System.out.println(listeners[j].toString());
		} 
		*/
		
		
		
		
		weekViewTable.addListener(SWT.MouseDoubleClick, new chooseTime());
		cellColor = weekViewTable.getDisplay().getSystemColor(SWT.COLOR_LIST_SELECTION);

		Label lblEnterPreferredEvent = new Label(composite, SWT.NONE);
		lblEnterPreferredEvent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblEnterPreferredEvent.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblEnterPreferredEvent.setText("Preferred event date");
		lblEnterPreferredEvent.setBounds(10, 24, 144, 23);
		toolkit.adapt(lblEnterPreferredEvent, true, true);
		
		preferredEventDate = new DateTime(composite, SWT.BORDER);
		preferredEventDate.setBounds(160, 24, 80, 24);
		toolkit.adapt(preferredEventDate);
		toolkit.paintBordersFor(preferredEventDate);
		
		Button btnCheck = new Button(composite, SWT.NONE);
		btnCheck.setBounds(259, 23, 75, 25);
		toolkit.adapt(btnCheck, true, true);
		btnCheck.setText("Check");
		btnCheck.addSelectionListener(new checkBookingInfo());
		
		Label lblSelectedDateAnd = new Label(composite, SWT.NONE);
		lblSelectedDateAnd.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblSelectedDateAnd.setBounds(646, 53, 160, 23);
		toolkit.adapt(lblSelectedDateAnd, true, true);
		lblSelectedDateAnd.setText("Selected date and time");
		
		selectedDateTimeList = new List(composite, SWT.BORDER | SWT.V_SCROLL);
		selectedDateTimeList.setBounds(646, 82, 232, 153);
		toolkit.adapt(selectedDateTimeList, true, true);
		
		Button btnDelete = new Button(composite, SWT.NONE);
		btnDelete.setBounds(646, 249, 75, 25);
		toolkit.adapt(btnDelete, true, true);
		btnDelete.setText("Delete");
		btnDelete.addSelectionListener(new deleteSelectedDateTime());
		
		Button btnGoBooking = new Button(composite, SWT.NONE);
		btnGoBooking.setBounds(727, 249, 151, 25);
		toolkit.adapt(btnGoBooking, true, true);
		btnGoBooking.setText("Go to the booking page");
		
		Label lblNotePleaseSelect = new Label(composite, SWT.NONE);
		lblNotePleaseSelect.setBounds(10, 424, 441, 62);
		toolkit.adapt(lblNotePleaseSelect, true, true);
		lblNotePleaseSelect.setText("Note: 1. Double click a cell to select the corresponding time slot.\n" +
		                            "          2. Select more cells if you need to book more than 1 hour.");
		btnGoBooking.addSelectionListener(new goBooking());
	}
	
	/* Button selection adapters */
	public class checkBookingInfo extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			/* Clear the week view time table before start a new bookingInfo check */
			weekViewTable.removeAll();
			
			/*Fill up time slots in the table */
			tblclmnTimeSlot.setText("Time/Date");
			for (int i = 6; i<23; i++){
				TableItem item = new TableItem(weekViewTable, SWT.NULL);
				item.setText(0, String.format("%02d", i) + ":00" + ":00");
			}
			
			/* Get the preferred event date */
			Calendar calendar = new GregorianCalendar();
			calendar.clear();
			calendar.set(preferredEventDate.getYear(), preferredEventDate.getMonth(), preferredEventDate.getDay());
			
			/* Arrange the week view table */
			int day_of_week;
			tblclmn[3].setToolTipText("Prefered date");
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
			
			/* Update the table color highlighting from the list of selected date and time*/
			for (int k=0; k<selectedDateTimeList.getItemCount(); k++){
				BookedDateTime dt = BookedDateTime.parseBookingDateTime(selectedDateTimeList.getItem(k));
				for(int i=0; i<weekViewTable.getItemCount(); i++){
					TableItem item = weekViewTable.getItem(i);
					if(Time.parseHour(item.getText(0)) == dt.getTimeStart().getHour()){ // locate the time row
						for(int j=0; j<dateInAWeekList.size(); j++){
							if(dateInAWeekList.get(j).isEqualTo(dt.getDate())){ // locate the date column
								item.setBackground(j+1, cellColor);
							}
						}
					}
				}	
			}
			
			
			/* Get bookingInfo of the venue from database */
			ArrayList<VenueBookingInfo> bookingInfoList = db.getVenueBookingInfo(selectedVenue);
			if (!bookingInfoList.isEmpty()){
				for (int i=0; i<bookingInfoList.size(); i++){
					if(bookingInfoList.get(i).getStatus() == MACRO.PENDING || bookingInfoList.get(i).getStatus() == MACRO.APPROVED){
					    BookedDateTime dateTime = bookingInfoList.get(i).getDateTime();
					    for (int j=0; j<dateInAWeekList.size(); j++){
					    	if(dateInAWeekList.get(j).isEqualTo(dateTime.getDate())){ // find the booked date
					    		for(int k=0; k<weekViewTable.getItemCount();k++){
					    			if(Time.parseHour(weekViewTable.getItem(k).getText(0)) == dateTime.getTimeStart().getHour()){ // find the booked time
					    				for (int t=0; t<(dateTime.getTimeEnd().getHour()-dateTime.getTimeStart().getHour()); t++){
					    					weekViewTable.getItem(k).setText(j+1, bookingInfoList.get(i).getApplicant().getOrganization());
					    					k++; // move to the next hour in the week view time table
					    				}
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
			case 1: tblclmn.setText("Sun " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH)+1)); break;
			case 2: tblclmn.setText("Mon " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH)+1)); break;
			case 3: tblclmn.setText("Tue " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH)+1)); break;
			case 4: tblclmn.setText("Wed " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH)+1)); break;
			case 5: tblclmn.setText("Thu " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH)+1)); break;
			case 6: tblclmn.setText("Fri " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH)+1)); break;
			case 7: tblclmn.setText("Sat " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH)+1)); break;
			}
		}
	}
	
	
	public class chooseTime implements Listener {
		  public void handleEvent(Event event) {
			  Point pt = new Point(event.x, event.y);
			  TableItem item = weekViewTable.getItem(pt);
			  if (item == null)    return;
			  for (int i = 0; i < weekViewTable.getColumnCount(); i++) {
				  Rectangle rect = item.getBounds(i);
			      if (rect.contains(pt) && item.getText(i).isEmpty()){ // choose only the empty time slots
			    	  item.setBackground(i, cellColor);
			    	  weekViewTable.deselectAll();
			    	  Time selectedTimeStart = new Time(Time.parseHour(item.getText(0)), 0, 0); // find the selected timeStart
			    	  Time selectedTimeEnd = new Time(selectedTimeStart.getHour()+1, 0, 0); // fix the corresponding timeEnd;
			    	  Date selectedDate = dateInAWeekList.get(i-1); //find the selected date;
			    	  BookedDateTime selectedDateTime = new BookedDateTime(selectedDate, selectedTimeStart, selectedTimeEnd);
			    	  selectedDateTimeList.add(selectedDateTime.toString());
			    	  bookedDateTimeList.add(selectedDateTime);
			      }
			      else weekViewTable.deselectAll(); // clear the redundant selection
			  }
		  }
	}
	
	
	public class deleteSelectedDateTime extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e){
			int index = selectedDateTimeList.getSelectionIndex();
			if(index >=0 && index < selectedDateTimeList.getItemCount()){
				/* update the table color highlighting */
				BookedDateTime toBeDeleted = BookedDateTime.parseBookingDateTime(selectedDateTimeList.getItem(index));
				for(int i=0; i<weekViewTable.getItemCount(); i++){
					TableItem item = weekViewTable.getItem(i);
					if(Time.parseHour(item.getText(0)) == toBeDeleted.getTimeStart().getHour()){ // locate the time row
						for(int j=0; j<dateInAWeekList.size(); j++){
							if(dateInAWeekList.get(j).isEqualTo(toBeDeleted.getDate())){ // locate the date column
								item.setBackground(j+1, null);
							}
						}
					}
				}
				/* update the list */
				selectedDateTimeList.remove(index);
				bookedDateTimeList.remove(index);
				
			}	
		}	
	}

	public class goBooking extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e){
			if(selectedDateTimeList.getItemCount()!= 0){ // at least one time slot is selected
				Shell vBookingShell = new Shell(getDisplay());
				VenueBooking_VenueBooking vBookingPage  = new VenueBooking_VenueBooking(vBookingShell, SWT.None, selectedVenue, bookedDateTimeList);
				getParent().dispose();
				vBookingPage.pack();
				vBookingShell.pack();
				vBookingShell.open();
			}
		}
	}
}

