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
	private int index; 

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VenueViewBookingInfo(Composite parent, int style, int index) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.index = index;
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(37, 26, 462, 410);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		Label lblVenueBookingStatus = new Label(composite, SWT.NONE);
		lblVenueBookingStatus.setBounds(175, 20, 128, 15);
		toolkit.adapt(lblVenueBookingStatus, true, true);
		lblVenueBookingStatus.setText("Venue Booking Status");
		
		List listBookingStatus = new List(composite, SWT.BORDER);
		listBookingStatus.setBounds(32, 54, 401, 346);
		toolkit.adapt(listBookingStatus, true, true);
		
		/* show the booking info from database */
		DatabaseReader db = new DatabaseReader();
		Venue selected = db.getVenues().get(index);
		ArrayList<VenueBookingInfo> bookingInfoList = db.getVenueBookingInfo(selected);
		
		if (!bookingInfoList.isEmpty()){
			for (int i=0; i<bookingInfoList.size(); i++){
				String name = bookingInfoList.get(i).getApplicant().getName();
			    String organization = bookingInfoList.get(i).getApplicant().getOrganization();
			    BookingDateTime dateTime = bookingInfoList.get(i).getDateTime();
			    
			    String bookingInfo = name + "  " + organization + " " + dateTime.toString();
			    listBookingStatus.add(bookingInfo, i);
			    }
		}
		
		else listBookingStatus.setItem(0, "Not booked");
	}
}
