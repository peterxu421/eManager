
import java.util.ArrayList;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;


public class VenueViewBookingInfo extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private List listBookingStatus;
	private String format =  "|%1$-15s|%2$-15s|%3$-40s|%4$-10s";
	private DatabaseReader db = new DatabaseReader();
	private ArrayList<VenueBookingApplication> bookingApplicationList;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VenueViewBookingInfo(Composite parent, int style, final Venue venue, final Table weekViewTable) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(0, 0, 634, 527);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		Label lblVenueBookingStatus = new Label(composite, SWT.NONE);
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
				if(!bookingApplicationList.isEmpty()){
					MessageBox warningPage  = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.CANCEL | SWT.ICON_WARNING );
					warningPage.setText("Warning!");
					warningPage.setMessage("Remove all the booking application records for <" + venue.getName() + "> from the database?");
					int choice = warningPage.open(); // indicates the user's choice
					switch(choice){
					case SWT.OK:
						/* update the database*/
						db.deleteVenueBookingInfo(venue);
						
						/* update the list */
						listBookingStatus.removeAll();
						String header[] = {"Name","Organization","Date and Time","Status"};
						listBookingStatus.add(String.format(format, (Object[])header));
						listBookingStatus.add("\n");
						listBookingStatus.add("---Not Booked---");
						/* update the weekViewTable */
						weekViewTable.removeAll();
						for (int i = 6; i<23; i++){
							TableItem item = new TableItem(weekViewTable, SWT.NULL);
							item.setText(0, String.format("%02d", i) + ":00" + ":00");
						} 
						break;
					case SWT.CANCEL:
						break;
					}
				}
				else {
					MessageBox noInfoWarning = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.ICON_WARNING);
					noInfoWarning.setText("Warning!");
					noInfoWarning.setMessage("This venue has no booking applictions!");
					noInfoWarning.open();
				}

			}
		});
		
		fillApplicationList(venue);
	}
	
	public void fillApplicationList(Venue venue){
		/* show the booking info from database */
		bookingApplicationList = db.getVenueBookingInfo(venue);
		String header[] = {"Name","Organization","Date and Time","Status"};
		listBookingStatus.add(String.format(format, (Object[])header));
		listBookingStatus.add("\n");
		if (!bookingApplicationList.isEmpty()){
			for (int i=0; i<bookingApplicationList.size(); i++){
				String name = bookingApplicationList.get(i).getApplicant().getName();
			    String organization = bookingApplicationList.get(i).getApplicant().getOrganization();
			    BookedDateTime dateTime = bookingApplicationList.get(i).getDateTime();
			    int statusIndex = bookingApplicationList.get(i).getStatus();
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
			    listBookingStatus.add(String.format(format, (Object[])bookingInfo));
			}
		}
		
		else listBookingStatus.add("---Not Booked---");
	}
}

