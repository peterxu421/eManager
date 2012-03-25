import java.util.ArrayList;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;


public class VenueViewBookingInfo extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VenueViewBookingInfo(Composite parent, int style, Venue venue) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(37, 26, 468, 463);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		Label lblVenueBookingStatus = new Label(composite, SWT.NONE);
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
		
		if (!bookingInfoList.isEmpty()){
			for (int i=0; i<bookingInfoList.size(); i++){
				String name = bookingInfoList.get(i).getApplicant().getName();
			    String organization = bookingInfoList.get(i).getApplicant().getOrganization();
			    BookedDateTime dateTime = bookingInfoList.get(i).getDateTime();
			    
			    String bookingInfo = name + "  " + organization + " " + 
			                         dateTime.getDate().toString() + "    From " + 
		                             dateTime.getTimeStart().toString() + " to " +
				                     dateTime.getTimeEnd().toString();
			    listBookingStatus.add(bookingInfo, i);
			    }
		}
		
		else listBookingStatus.add("Not Booked",0);
	}
}