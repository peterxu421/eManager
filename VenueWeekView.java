import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackListener;
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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.GregorianCalendar;


public class VenueWeekView extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table weekViewTable;
	private TableColumn tblclmnTimeSlot;
	private TableColumn[] tblclmn = new TableColumn[7];
	private DateTime preferredEventDate;
	private Color cellColor;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VenueWeekView(Composite parent, int style, int venueIndex) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(10, 0, 749, 508);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		weekViewTable = new Table(composite, SWT.BORDER);
		weekViewTable.setBounds(10, 53, 630, 410);
		toolkit.adapt(weekViewTable);
		toolkit.paintBordersFor(weekViewTable);
		weekViewTable.setHeaderVisible(true);
		weekViewTable.setLinesVisible(true);
		
		tblclmnTimeSlot = new TableColumn(weekViewTable, SWT.CENTER);
		tblclmnTimeSlot.setWidth(75);

		for(int i=0; i<7; i++){
			tblclmn[i] = new TableColumn(weekViewTable, SWT.CENTER);
			tblclmn[i].setWidth(75);	
		}
		
//		weekViewTable.addListener(SWT.EraseItem, new Listener()
//		{
//			public void handleEvent(Event event)
//			{
//				event.detail &= ~SWT.SELECTED;
//			}			
//		}); // Disable the default table selection
//		
		
		weekViewTable.addListener(SWT.MouseDoubleClick, new chooseTime());

//		weekViewTable.addMouseMoveListener(new mouseHighlight());

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
		btnCheck.addSelectionListener(new checkBookingInfo(venueIndex));
	}
	
	public class chooseTime implements Listener {
		  public void handleEvent(Event event) {
			  Point pt = new Point(event.x, event.y);
			  TableItem item = weekViewTable.getItem(pt);
			  if (item == null)    return;
			  for (int i = 0; i < weekViewTable.getColumnCount(); i++) {
				  Rectangle rect = item.getBounds(i);
			      if (rect.contains(pt)) {
			    	  int rowIndex = weekViewTable.indexOf(item);
			    	  weekViewTable.getItem(rowIndex).setBackground(i, cellColor);
			    	  //weekViewTable.deselectAll();
			          System.out.println(weekViewTable.getItem(rowIndex).getText(i));
			      }
			  }
		  }
	}
	
	public class checkBookingInfo extends SelectionAdapter {
		int index;
		public checkBookingInfo (int venueIndex){
			index = venueIndex;
		}
		public void widgetSelected(SelectionEvent e) {
			/* Clear the week view time table before start a new bookingInfo check */
			weekViewTable.removeAll();
			/*Fill up time slots in the table */
			tblclmnTimeSlot.setText("Time Slot/Date");
			for (int i = 6; i<23; i++){
				TableItem item = new TableItem(weekViewTable, SWT.NULL);
				item.setText(0, String.format("%02d", i) + ":00");
			}
			/* Get the preferred event date */
			Calendar calendar = new GregorianCalendar();
			calendar.clear();
			calendar.set(preferredEventDate.getYear(), preferredEventDate.getMonth(), preferredEventDate.getDay());
			/* Arrange the week view table */
			int day_of_week;
			ArrayList<Date> dateList = new ArrayList<Date>();
			for(int i=0; i<7; i++){
				dateList.add(new Date(0,0,0)); // initialize the arraylist
			}
			for(int i=3; i<7; i++){ // right hand side half
				day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
				tblclmnTextFill(day_of_week, calendar, tblclmn[i]);
				Date date = new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH)); // MONTH+1 because MONTH starts from 0 in default
				dateList.set(i, date);
				calendar.add(Calendar.DAY_OF_MONTH, 1);  // roll the date forward
			} 
			calendar.add(Calendar.DAY_OF_MONTH, -4); // roll back to the selected date
			for(int i=3; i>=0; i--){
				day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
				tblclmnTextFill(day_of_week, calendar, tblclmn[i]);
				Date date = new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH)); // MONTH+1 because MONTH starts from 0 in default
				dateList.set(i, date);
				calendar.add(Calendar.DAY_OF_MONTH, -1); // roll the date backward
			}
			/* Get bookingInfo of the venue from database */
			DatabaseReader db = new DatabaseReader();
			Venue selected = db.getVenues().get(index);
			ArrayList<VenueBookingInfo> bookingInfoList = db.getVenueBookingInfo(selected);
			if (!bookingInfoList.isEmpty()){
				for (int i=0; i<bookingInfoList.size(); i++){
				    BookingDateTime dateTime = bookingInfoList.get(i).getDateTime();
				    for (int j=0; j<dateList.size(); j++){
				    	if(dateList.get(j).isEqualTo(dateTime.getDate())){ // find the booked date
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
/*	public class mouseHighlight extends MouseMotionAdapter{
		public void mouseMoved(MouseEvent event) {
			  Point pt = new Point(event.x, event.y);
			  TableItem item = weekViewTable.getItem(pt);
			  if (item == null)    return;
			  for (int i = 0; i < weekViewTable.getColumnCount(); i++) {
				  Rectangle rect = item.getBounds(i);
			      if (rect.contains(pt)) {
			    	  int rowIndex = weekViewTable.indexOf(item);
			    	  weekViewTable.getItem(rowIndex).setBackground(i, cellColor);
			    	  weekViewTable.deselectAll();
			          System.out.println(weekViewTable.getItem(rowIndex).getText(i));
			      }
			  }	
		}
	}*/
}


