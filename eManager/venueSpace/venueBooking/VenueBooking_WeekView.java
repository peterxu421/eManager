package eManager.venueSpace.venueBooking;
import java.util.ArrayList;
import java.util.Calendar;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;

import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.nebula.widgets.calendarcombo.ICalendarListener;

import eManager.dataType.common.Date;
import eManager.dataType.common.Time;
import eManager.dataType.venue.BookedDateTime;
import eManager.dataType.venue.Venue;
import eManager.dataType.venue.VenueBookingApplication;
import eManager.database.DatabaseReader;
import eManager.macro.MACRO;

public class VenueBooking_WeekView extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table weekViewTable;
	private TableColumn tblclmnTimeSlot;
	private TableColumn[] tblclmn = new TableColumn[7];
	private Color selectedCellColor;
	private Color notAvailableCellColor;
	private List selectedDateTimeList;
	private CalendarCombo calendarCombo;
	
	private DatabaseReader db = new DatabaseReader();
	private Venue selectedVenue = new Venue();
	
	private ArrayList<Date> dateInAWeekList = new ArrayList<Date>();  
	                // an arrayList containing the the dates of the seven days in the week shown at the top row of the table
	private ArrayList<BookedDateTime> bookedDateTimeList = new ArrayList<BookedDateTime>();
	                // an arrayList storing the booked date and time in the format of BookedDateTime

	public VenueBooking_WeekView(Composite parent, int style, Venue selectedVenue) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.selectedVenue = selectedVenue;
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(10, 0, 888, 496);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
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
		
		weekViewTable.addListener(SWT.MouseDoubleClick, new chooseTime());

		selectedCellColor = weekViewTable.getDisplay().getSystemColor(SWT.COLOR_LIST_SELECTION);
		notAvailableCellColor = weekViewTable.getDisplay().getSystemColor(SWT.COLOR_RED);

		weekViewTable.addListener(SWT.MouseDown, new Listener(){
			public void handleEvent(Event event){
				weekViewTable.deselectAll();
			}
		}); // disable table item selection
		
		Label lblEnterPreferredEvent = new Label(composite, SWT.NONE);
		lblEnterPreferredEvent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblEnterPreferredEvent.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblEnterPreferredEvent.setText("Select preferred event date");
		lblEnterPreferredEvent.setBounds(10, 25, 180, 25);
		toolkit.adapt(lblEnterPreferredEvent, true, true);
		
		Label lblVenueName = new Label(composite, SWT.NONE);
		lblVenueName.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		lblVenueName.setAlignment(SWT.CENTER);
		lblVenueName.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblVenueName.setBounds(350, 25, 250 , 25);
        lblVenueName.setText("<" + selectedVenue.getName() + " @ " + selectedVenue.getLocation() + ">");
	
		
		Label lblSelectedDateAnd = new Label(composite, SWT.NONE);
		lblSelectedDateAnd.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblSelectedDateAnd.setBounds(646, 53, 160, 23);
		toolkit.adapt(lblSelectedDateAnd, true, true);
		lblSelectedDateAnd.setText("Selected date and time");
		
		selectedDateTimeList = new List(composite, SWT.BORDER | SWT.V_SCROLL);
		selectedDateTimeList.setBounds(646, 82, 232, 153);
		toolkit.adapt(selectedDateTimeList, true, true);
		
		Button btnClearAll = new Button(composite, SWT.NONE);
		btnClearAll.setBounds(646, 249, 75, 25);
		toolkit.adapt(btnClearAll, true, true);
		btnClearAll.setText("Clear all");
		btnClearAll.addSelectionListener(new clear());
		
		Button btnGoBooking = new Button(composite, SWT.NONE);
		btnGoBooking.setBounds(727, 249, 151, 25);
		toolkit.adapt(btnGoBooking, true, true);
		btnGoBooking.setText("Go to the booking page");
		btnGoBooking.addSelectionListener(new BookingPage());
		
		Label lblNotePleaseSelect = new Label(composite, SWT.NONE);
		lblNotePleaseSelect.setFont(SWTResourceManager.getFont("Courier New", 9, SWT.NORMAL));
		lblNotePleaseSelect.setBounds(10, 424, 630, 62);
		toolkit.adapt(lblNotePleaseSelect, true, true);
		lblNotePleaseSelect.setText("Note: 1. Double click a cell to select the corresponding time slot.\n" +
		                            "      2. Select more cells if you need to book more than 1 hour.\n" +
				                    "      3. Double click a marked cell again to cancel the selection.");
		
		calendarCombo = new CalendarCombo(composite, SWT.READ_ONLY);
		calendarCombo.setBounds(200, 25, 100, 25);
		toolkit.adapt(calendarCombo);
		toolkit.paintBordersFor(calendarCombo);
		Calendar date = Calendar.getInstance(); // date = today
		date.add(Calendar.DAY_OF_MONTH, -1); // date = yesterday
		calendarCombo.setDisallowBeforeDate(date); // set selectrion not allowed before yesterday (including yesterday)
		calendarCombo.addCalendarListener(new checkBookingInfo());
		
	}
	
	/* Button selection adapters */
	public class checkBookingInfo implements ICalendarListener {
		@Override
		public void dateChanged(Calendar date) {
			/* Clear the week view time table before start a new bookingInfo check */
			weekViewTable.removeAll(); // remove table items
			for(int i=0; i<weekViewTable.getColumnCount(); i++){
				weekViewTable.getColumn(i).setText("");
			}
		
			if (date != null){
				
				/*Fill up time slots in the table */
				tblclmnTimeSlot.setText("Time/Date");
				for (int i = 6; i<23; i++){
					TableItem item = new TableItem(weekViewTable, SWT.NULL);
					//item.setText(0, String.format("%02d", i) + ":00" + ":00");
					item.setText(0, String.format("%02d", i) + ":00" + " - " + String.format("%02d",i+1) + ":00");
				}
				
				/* Arrange the week view table */
				int day_of_week;
				Date _date = new Date();
				tblclmn[3].setToolTipText("Prefered date");
				for(int i=0; i<7; i++){
					dateInAWeekList.add(new Date(0,0,0)); // initialize the arraylist
				}
				for(int i=0; i<7; i++){ // right hand side half
					day_of_week = date.get(Calendar.DAY_OF_WEEK);
					tblclmnTextFill(day_of_week, date, tblclmn[i]);
					_date = new Date(date.get(Calendar.YEAR), date.get(Calendar.MONTH)+1, date.get(Calendar.DAY_OF_MONTH)); // MONTH+1 because MONTH starts from 0 in default
					dateInAWeekList.set(i, _date);
					date.add(Calendar.DAY_OF_MONTH, 1);  // roll the date forward
				} 
//				date.add(Calendar.DAY_OF_MONTH, -4); // roll back to the selected date
//				for(int i=3; i>=0; i--){
//					day_of_week = date.get(Calendar.DAY_OF_WEEK);
//					tblclmnTextFill(day_of_week, date, tblclmn[i]);
//					_date = new Date(date.get(Calendar.YEAR), date.get(Calendar.MONTH)+1, date.get(Calendar.DAY_OF_MONTH)); // MONTH+1 because MONTH starts from 0 in default
//					dateInAWeekList.set(i, _date);
//					date.add(Calendar.DAY_OF_MONTH, -1); // roll the date backward
//				}
//				
				/* Update the table color highlighting from the list of selected date and time*/
				for (int k=0; k<selectedDateTimeList.getItemCount(); k++){
					BookedDateTime dt = BookedDateTime.parseBookedDateTime(selectedDateTimeList.getItem(k));
					for(int i=0; i<weekViewTable.getItemCount(); i++){
						TableItem item = weekViewTable.getItem(i);
						if(Time.parseHour(item.getText(0)) == dt.getTimeStart().getHour()){ // locate the time row
							for(int j=0; j<dateInAWeekList.size(); j++){
								if(dateInAWeekList.get(j).isEqualTo(dt.getDate())){ // locate the date column
									item.setBackground(j+1, selectedCellColor);
								}
							}
						}
					}	
				}
				
				
				/* Get bookingInfo of the venue from database */
				ArrayList<VenueBookingApplication> bookingInfoList = db.getVenueBookingInfo(selectedVenue);
				if (!bookingInfoList.isEmpty()){
					for (int i=0; i<bookingInfoList.size(); i++){
						if(bookingInfoList.get(i).getStatus() == MACRO.PENDING || bookingInfoList.get(i).getStatus() == MACRO.APPROVED){
						    BookedDateTime dateTime = bookingInfoList.get(i).getDateTime();
						    for (int j=0; j<dateInAWeekList.size(); j++){
						    	if(dateInAWeekList.get(j).isEqualTo(dateTime.getDate())){ // find the booked date
						    		for(int k=0; k<weekViewTable.getItemCount();k++){
						    			if(Time.parseHour(weekViewTable.getItem(k).getText(0)) == dateTime.getTimeStart().getHour()){ // find the booked time
						    				for (int t=0; t<(dateTime.getTimeEnd().getHour()-dateTime.getTimeStart().getHour()); t++){
						    					weekViewTable.getItem(k).setText(j+1, "Booked");
						    					weekViewTable.getItem(k).setBackground(j+1, notAvailableCellColor);
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

		}

		@Override
		public void dateRangeChanged(Calendar start, Calendar end) {
			
		}
		@Override
		public void popupClosed() {


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
	
	
	public class chooseTime implements Listener {
		  public void handleEvent(Event event) {
			  Point pt = new Point(event.x, event.y);
			  TableItem item = weekViewTable.getItem(pt);
			  if (item == null)    return;
			  for (int i = 0; i < weekViewTable.getColumnCount(); i++) {
				  Rectangle rect = item.getBounds(i);
				  /* select a time slot and mark the cell*/
			      if (rect.contains(pt) && item.getText(i).isEmpty() && !item.getBackground(i).equals(selectedCellColor)){ // choose only the empty time slots
			    	  item.setBackground(i, selectedCellColor);
			    	  weekViewTable.deselectAll();
			    	  Time selectedTimeStart = new Time(Time.parseHour(item.getText(0)), 0, 0); // find the selected timeStart
			    	  Time selectedTimeEnd = new Time(selectedTimeStart.getHour()+1, 0, 0); // fix the corresponding timeEnd;
			    	  Date selectedDate = dateInAWeekList.get(i-1); //find the selected date;
			    	  BookedDateTime selectedDateTime = new BookedDateTime(selectedDate, selectedTimeStart, selectedTimeEnd);
			    	  selectedDateTimeList.add(selectedDateTime.toString());
			    	  bookedDateTimeList.add(selectedDateTime);
			      }
			      /* deselect a chosen time slot and unmark the cell */
			      else if (rect.contains(pt)  && item.getBackground(i).equals(selectedCellColor)){ // choose only the time slots with marked cell
			    	  item.setBackground(i, null);
			    	  weekViewTable.deselectAll();
			    	  Time deselectedTimeStart = new Time(Time.parseHour(item.getText(0)), 0, 0); // find the deselected timeStart
			    	  Time deselectedTimeEnd = new Time(deselectedTimeStart.getHour()+1, 0, 0); // fix the corresponding timeEnd;
			    	  Date deselectedDate = dateInAWeekList.get(i-1); //find the deselected date;
			    	  BookedDateTime deselectedDateTime = new BookedDateTime(deselectedDate, deselectedTimeStart, deselectedTimeEnd);
			    	  selectedDateTimeList.remove(deselectedDateTime.toString());
			    	  for(int j=0; j<bookedDateTimeList.size(); j++){
			    		  if(bookedDateTimeList.get(j).isEqualTo(deselectedDateTime)){
			    			  bookedDateTimeList.remove(j);
			    			  break;
			    		  }	 
			    	  }
			      }
			      else weekViewTable.deselectAll(); // clear the redundant selection
			  }
		  }
	}
	
	public class clear extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e){
			if (selectedDateTimeList.getItemCount()!=0){
				/* clear table highlighting */
				for(int i=0; i<weekViewTable.getItemCount(); i++){
					TableItem item = weekViewTable.getItem(i);
					for(int j=0; j<weekViewTable.getColumnCount(); j++){
						if(item.getBackground(j).equals(selectedCellColor)){
							item.setBackground(j, null);
						}
					}
				}
				/* update the bookedDateTimelist to be passed to the venue booking page */
				selectedDateTimeList.removeAll();
				bookedDateTimeList.clear();
			}
			else {
				MessageBox noInputWarning = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.ICON_WARNING);
				noInputWarning.setText("Warning!");
				noInputWarning.setMessage("No timeslot chosen!");
				noInputWarning.open();
			}
		}	
	}
	
	public class BookingPage extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e){
			if(selectedDateTimeList.getItemCount()!= 0){ // at least one time slot is selected
				Shell vBookingShell = new Shell(getDisplay(), SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
				vBookingShell.setLocation(400, 100);
				Image icon = new Image(getDisplay(), "resources/eManager.png");
				vBookingShell.setText("eManager - Registration");
				vBookingShell.setImage(icon);
				VenueBooking_BookingPage vBookingPage  = new VenueBooking_BookingPage(vBookingShell, SWT.None, selectedVenue, bookedDateTimeList);
				vBookingPage.pack();
				vBookingShell.setLocation(400,200);
				vBookingShell.pack();
				vBookingShell.open();
			}
			else {
				MessageBox noInputWarning = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.ICON_WARNING);
				noInputWarning.setText("Warning!");
				noInputWarning.setMessage("No timeslot chosen!");
				noInputWarning.open();
			}
		}
	}
}


