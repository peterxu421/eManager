import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;

public class VenueManagement_BookingApplications extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table applicationTable;
	
	private ArrayList<VenueBookingInfo> bookingInfoList;

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
		composite.setBounds(473, 0, 108, 316);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		Button btnReject = new Button(composite, SWT.NONE);
		btnReject.setText("Reject");
		btnReject.setBounds(10, 10, 89, 27);
		toolkit.adapt(btnReject, true, true);
		btnReject.addSelectionListener(new reject());
		
		Button btnAccept = new Button(composite, SWT.NONE);
		btnAccept.setBounds(10, 41, 89, 27);
		toolkit.adapt(btnAccept, true, true);
		btnAccept.setText("Accept");
		btnAccept.addSelectionListener(new accept());
		
		applicationTable = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		applicationTable.setLocation(0, 0);
		applicationTable.setSize(468, 324);
		applicationTable.setLinesVisible(true);
		applicationTable.setHeaderVisible(true);
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
		
		TableColumn tblclmnApproval = new TableColumn(applicationTable, SWT.CENTER);
		tblclmnApproval.setWidth(100);
		tblclmnApproval.setText("Approval");
		btnAccept.addSelectionListener(new accept());
		
		
		importApplicationData();

	}

	public void importApplicationData() {
		DatabaseReader db = new DatabaseReader();
		
		bookingInfoList = db.getVenueBookingInfo();
		if(!bookingInfoList.isEmpty()){ //  booked
			for(int j=0; j<bookingInfoList.size(); j++){
				TableItem item = new TableItem(applicationTable, SWT.NULL);
				item.setText(0, bookingInfoList.get(j).getVenue().getName() + " at " + bookingInfoList.get(j).getVenue().getLocation() );
				item.setText(1, bookingInfoList.get(j).getApplicant().getName());
				item.setText(2, bookingInfoList.get(j).getApplicant().getMatricNo());
				item.setText(3, bookingInfoList.get(j).getApplicant().getOrganization());
				item.setText(4, bookingInfoList.get(j).getApplicant().getContact());
				item.setText(5, bookingInfoList.get(j).getApplicant().getEmail());
				item.setText(6, bookingInfoList.get(j).getDateTime().toString());
				if(bookingInfoList.get(j).getStatus()== MACRO.PENDING){
					item.setText(7,"Pending");
				}
				else if (bookingInfoList.get(j).getStatus()== MACRO.APPROVED){
					item.setText(7,"Approved");
				}
				else item.setText(7, "Rejected");
			}
		}
	}
	
	public class reject extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			int index = applicationTable.getSelectionIndex();
			if(index>=0 && index <= applicationTable.getItemCount()){
				/* update the application table */
				TableItem item = applicationTable.getItem(index);
				item.setText(7,"Rejected");
				
				/* update the database */
				DatabaseReader db = new DatabaseReader();
				VenueBookingInfo bookingInfo = bookingInfoList.get(index);
				bookingInfo.setStatus(MACRO.REJECTED);
				db.updateVenueBookingInfo(bookingInfo);
			}
		}
	}
	
	public class accept extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			int index = applicationTable.getSelectionIndex();
			if(index>=0 && index <= applicationTable.getItemCount()){
				/* update the application table */
				TableItem item = applicationTable.getItem(index);
				item.setText(7,"Accepted");
				
				/* update the database */
				DatabaseReader db = new DatabaseReader();
				VenueBookingInfo bookingInfo = bookingInfoList.get(index);
				bookingInfo.setStatus(MACRO.APPROVED);
				db.updateVenueBookingInfo(bookingInfo);

			}
		}
	}
}
