import java.util.ArrayList;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
<<<<<<< HEAD
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
=======
>>>>>>> de2b69bfc67aaafeb9b3c8d5b15122ffabc7fcd6
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
<<<<<<< HEAD
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.wb.swt.SWTResourceManager;
=======
>>>>>>> de2b69bfc67aaafeb9b3c8d5b15122ffabc7fcd6


public class VenueViewBookingInfo extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
<<<<<<< HEAD
	private List listBookingStatus;
	private String format =  "|%1$-15s|%2$-15s|%3$-40s|%4$-10s";
=======
>>>>>>> de2b69bfc67aaafeb9b3c8d5b15122ffabc7fcd6

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
<<<<<<< HEAD
	public VenueViewBookingInfo(Composite parent, int style, final Venue venue) {
=======
	public VenueViewBookingInfo(Composite parent, int style, Venue venue) {
>>>>>>> de2b69bfc67aaafeb9b3c8d5b15122ffabc7fcd6
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite composite = new Composite(this, SWT.NONE);
<<<<<<< HEAD
		composite.setBounds(0, 0, 634, 527);
=======
		composite.setBounds(37, 26, 468, 463);
>>>>>>> de2b69bfc67aaafeb9b3c8d5b15122ffabc7fcd6
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		Label lblVenueBookingStatus = new Label(composite, SWT.NONE);
<<<<<<< HEAD
		lblVenueBookingStatus.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblVenueBookingStatus.setAlignment(SWT.CENTER);
		lblVenueBookingStatus.setBounds(10, 35, 614, 15);
		toolkit.adapt(lblVenueBookingStatus, true, true);
		lblVenueBookingStatus.setText("Booking Applications for <" + venue.getName()+ ">");
		
		
		listBookingStatus = new List(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		listBookingStatus.setItems(new String[] {});
		listBookingStatus.setFont(SWTResourceManager.getFont("Courier New", 9, SWT.NORMAL));
		listBookingStatus.setBounds(10, 60, 614, 415);
		toolkit.adapt(listBookingStatus, true, true);
		

		
		
		Button btnClearAllApplications = new Button(composite, SWT.NONE);
		btnClearAllApplications.setBounds(249, 492, 121, 25);
		toolkit.adapt(btnClearAllApplications, true, true);
		btnClearAllApplications.setText("Clear the log");
		btnClearAllApplications.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				MessageBox warningPage  = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.CANCEL | SWT.ICON_WARNING );
				warningPage.setText("Warning!");
				warningPage.setMessage("Remove all the booking application records for <" + venue.getName() + "> from the database?");
				int choice = warningPage.open(); // indicates the user's choice
				switch(choice){
				case SWT.OK:
					/* update the database*/
					//db.deleteVenueBookingInfo(venue);
					
					/* update the list */
					listBookingStatus.removeAll();
					String header[] = {"Name","Organization","Date and Time","Status"};
					listBookingStatus.add(String.format(format, (Object[])header));
					listBookingStatus.add("\n");
					listBookingStatus.add("---Not Booked---");
					break;
				case SWT.CANCEL:
					break;
				}
			}
		});
		
		fillApplicationList(venue);
	}
	
	public void fillApplicationList(Venue venue){
		/* show the booking info from database */
		DatabaseReader db = new DatabaseReader();
		ArrayList<VenueBookingInfo> bookingInfoList = db.getVenueBookingInfo(venue);
		String header[] = {"Name","Organization","Date and Time","Status"};
		listBookingStatus.add(String.format(format, (Object[])header));
		listBookingStatus.add("\n");
=======
		lblVenueBookingStatus.setAlignment(SWT.CENTER);
		lblVenueBookingStatus.setBounds(26, 21, 407, 15);
		toolkit.adapt(lblVenueBookingStatus, true, true);
		
		
		List listBookingStatus = new List(composite, SWT.BORDER | SWT.V_SCROLL);
		listBookingStatus.setBounds(32, 54, 401, 387);
		toolkit.adapt(listBookingStatus, true, true);
		
		/* show the booking info from database */
		DatabaseReader db = new DatabaseReader();
		lblVenueBookingStatus.setText("Booking Applications for <" + venue.getName()+ ">");
		ArrayList<VenueBookingInfo> bookingInfoList = db.getVenueBookingInfo(venue);
		
>>>>>>> de2b69bfc67aaafeb9b3c8d5b15122ffabc7fcd6
		if (!bookingInfoList.isEmpty()){
			for (int i=0; i<bookingInfoList.size(); i++){
				String name = bookingInfoList.get(i).getApplicant().getName();
			    String organization = bookingInfoList.get(i).getApplicant().getOrganization();
			    BookedDateTime dateTime = bookingInfoList.get(i).getDateTime();
<<<<<<< HEAD
			    int statusIndex = bookingInfoList.get(i).getStatus();
			    String status = "";
			    if(statusIndex == MACRO.APPROVED){
			    	status = "Approved";
			    }
			    else if(statusIndex == MACRO.PENDING){
			    	status = "Pending";
			    }
			    else if(statusIndex == MACRO.REJECTED){
			    	status = "Rejected";
			    }
			    String bookingInfo[] = {name, organization, 
			                         dateTime.getDate().toString() + " From " + 
		                             dateTime.getTimeStart().toString() + " to " +
				                     dateTime.getTimeEnd().toString(), status};
			    System.out.println(String.format(format, (Object[])bookingInfo));
			    listBookingStatus.add(String.format(format, (Object[])bookingInfo));
			}
		}
		
		else listBookingStatus.add("---Not Booked---");
=======
			    
			    String bookingInfo = name + "  " + organization + " " + 
			                         dateTime.getDate().toString() + "    From " + 
		                             dateTime.getTimeStart().toString() + " to " +
				                     dateTime.getTimeEnd().toString();
			    listBookingStatus.add(bookingInfo, i);
			    }
		}
		
		else listBookingStatus.add("Not Booked",0);
>>>>>>> de2b69bfc67aaafeb9b3c8d5b15122ffabc7fcd6
	}
}