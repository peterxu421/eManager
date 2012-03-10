import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;


public class VenueManagement_BookingApplications extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table applicationTable;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VenueManagement_BookingApplications(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(10, 10, 541, 350);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		applicationTable = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		applicationTable.setLinesVisible(true);
		applicationTable.setHeaderVisible(true);
		applicationTable.setBounds(10, 10, 424, 324);
		toolkit.adapt(applicationTable);
		toolkit.paintBordersFor(applicationTable);
		
		TableColumn tblclmnVenueName = new TableColumn(applicationTable, SWT.NONE);
		tblclmnVenueName.setWidth(98);
		tblclmnVenueName.setText("Booked Venue");
		
		TableColumn tblclmnName = new TableColumn(applicationTable, SWT.CENTER);
		tblclmnName.setWidth(80);
		tblclmnName.setText("Name");
		
		TableColumn tblclmnMatricNo = new TableColumn(applicationTable, SWT.CENTER);
		tblclmnMatricNo.setWidth(100);
		tblclmnMatricNo.setText("Matric No.");
		
		TableColumn tblclmnOrganization = new TableColumn(applicationTable, SWT.CENTER);
		tblclmnOrganization.setWidth(88);
		tblclmnOrganization.setText("Organization");
		
		TableColumn tblclmnContact = new TableColumn(applicationTable, SWT.CENTER);
		tblclmnContact.setWidth(100);
		tblclmnContact.setText("Contact No.");
		
		TableColumn tblclmnNewEmail = new TableColumn(applicationTable, SWT.CENTER);
		tblclmnNewEmail.setWidth(106);
		tblclmnNewEmail.setText("Eamil");
		
		TableColumn tblclmnDateAndTime = new TableColumn(applicationTable, SWT.CENTER);
		tblclmnDateAndTime.setWidth(181);
		tblclmnDateAndTime.setText("Date and Time");
		
		Button btnAccept = new Button(composite, SWT.NONE);
		btnAccept.setText("Accept");
		btnAccept.setBounds(440, 10, 89, 27);
		toolkit.adapt(btnAccept, true, true);
		
		Button btnReject = new Button(composite, SWT.NONE);
		btnReject.setText("Reject");
		btnReject.setBounds(440, 43, 89, 27);
		toolkit.adapt(btnReject, true, true);
		
		importApplicationData();

	}
	
	public void importApplicationData() {
		DatabaseReader db = new DatabaseReader();
		ArrayList<Venue> venueList = db.getVenues();
		
		for(int i=0; i<venueList.size(); i++){
		    Venue venue = venueList.get(i);
		    ArrayList<VenueBookingInfo> bookingInfoList = db.getVenueBookingInfo(venue);
			if(!bookingInfoList.isEmpty()){ //  booked
				for(int j=0; j<bookingInfoList.size(); j++){
					TableItem item = new TableItem(applicationTable, SWT.NULL);
					item.setText(0, venue.getName() + " at " + venue.getLocation());
					item.setText(1, bookingInfoList.get(j).getApplicant().getName());
					item.setText(1, bookingInfoList.get(j).getApplicant().getMatricNo());
					item.setText(1, bookingInfoList.get(j).getApplicant().getOrganization());
					item.setText(1, bookingInfoList.get(j).getApplicant().getContact());
					item.setText(1, bookingInfoList.get(j).getApplicant().getEmail());
					item.setText(1, bookingInfoList.get(j).getDateTime().toString());
				}
			}	
		}
	}
}
