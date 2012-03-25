import java.util.ArrayList;
import java.util.Calendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;

public class VenueManagement_BookingApplications extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table applicationTable;
	private Button btnReject;
	private Button btnApprove;
	
	private DatabaseReader db = new DatabaseReader();
	private Date today = new Date();
	private ArrayList<VenueBookingApplication> bookingAppList;

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
		
		btnReject = new Button(composite, SWT.NONE);
		btnReject.setText("Reject");
		btnReject.setBounds(10, 10, 89, 27);
		toolkit.adapt(btnReject, true, true);
		btnReject.addSelectionListener(new reject());
		
		btnApprove = new Button(composite, SWT.NONE);
		btnApprove.setBounds(10, 41, 89, 27);
		toolkit.adapt(btnApprove, true, true);
		btnApprove.setText("Approve");
		btnApprove.addSelectionListener(new approve());
		
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
		tblclmnApproval.setText("Status");
		
		Calendar calendar = Calendar.getInstance(); // today
		today.setYear(calendar.get(Calendar.YEAR));
		today.setMonth(calendar.get(Calendar.MONTH)+1);
		today.setDay(calendar.get(Calendar.DATE));
		
		importApplicationData();

	}

	public void importApplicationData() {
		bookingAppList = db.getVenueBookingInfoFromToday(today);
		if(!bookingAppList.isEmpty()){ //  booked
			for(int j=0; j<bookingAppList.size(); j++){
				TableItem item = new TableItem(applicationTable, SWT.NULL);
				item.setText(0, bookingAppList.get(j).getVenue().getName() + " at " + bookingAppList.get(j).getVenue().getLocation() );
				item.setText(1, bookingAppList.get(j).getApplicant().getName());
				item.setText(2, bookingAppList.get(j).getApplicant().getMatricNo());
				item.setText(3, bookingAppList.get(j).getApplicant().getOrganization());
				item.setText(4, bookingAppList.get(j).getApplicant().getContact());
				item.setText(5, bookingAppList.get(j).getApplicant().getEmail());
				item.setText(6, bookingAppList.get(j).getDateTime().toString());
				if(bookingAppList.get(j).getStatus()== MACRO.PENDING){
					item.setText(7,"Pending");
				}
				else if (bookingAppList.get(j).getStatus()== MACRO.APPROVED){
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
				item.setBackground(null); // get rid of the conflict reminder if there is any
				item.setText(7,"Rejected");
				
				/* update the database */
				DatabaseReader db = new DatabaseReader();
				VenueBookingApplication bookingInfo = bookingAppList.get(index);
				bookingInfo.setStatus(MACRO.REJECTED);
				db.updateVenueBookingInfo(bookingInfo);
			}
		}
	}
	
	public class approve extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			ArrayList<VenueBookingApplication> conflictedAppList = new ArrayList<VenueBookingApplication>();
			ArrayList<Integer> conflictIndexList = new ArrayList<Integer>();
			int index = applicationTable.getSelectionIndex();
			
			if(index>=0 && index <= applicationTable.getItemCount()){
				VenueBookingApplication selectedBookingApp = bookingAppList.get(index);
				TableItem item = applicationTable.getItem(index);
				for(int i=0; i<bookingAppList.size(); i++){
					if (   (bookingAppList.get(i).getStatus() == MACRO.PENDING ||
							bookingAppList.get(i).getStatus() == MACRO.APPROVED) 
							&& 
							bookingAppList.get(i).getDateTime().getDate().isEqualTo(selectedBookingApp.getDateTime().getDate()) 
							&& 
							bookingAppList.get(i).getDateTime().getTimeStart().isEarlierThan(selectedBookingApp.getDateTime().getTimeEnd()) 
							&& 
						    bookingAppList.get(i).getDateTime().getTimeEnd().isLaterThan(selectedBookingApp.getDateTime().getTimeStart())
						    &&
						    i!=index // no conflict with it self
						){
						conflictedAppList.add(bookingAppList.get(i));
						conflictIndexList.add(i);
				    }
			
			    }
				if(!conflictedAppList.isEmpty()){
					MessageBox warningPage  = new MessageBox(getDisplay().getActiveShell(), SWT.YES | SWT.NO | SWT.ICON_WARNING );
					warningPage.setText("Warning!");
					warningPage.setMessage("Conflicted applications will be rejected automatically. Do you want to review them before proceeding? ");
					int choice = warningPage.open(); // indicates the user's choice
					switch(choice){
					case SWT.NO:
						/* update the database */
						for(int i=0; i<conflictedAppList.size(); i++){
							conflictedAppList.get(i).setStatus(MACRO.REJECTED);
							db.updateVenueBookingInfo(conflictedAppList.get(i));
						}
						selectedBookingApp.setStatus(MACRO.APPROVED);
						db.updateVenueBookingInfo(selectedBookingApp);
						/* update the application table */
						for(int i=0; i<conflictIndexList.size();i++){
							TableItem tempItem = applicationTable.getItem(conflictIndexList.get(i));
							tempItem.setText(7, "Rejected");
						}
						item.setText(7, "Approved");
						break;
					case SWT.YES:
						/* highlight them in the table */
						Color conflictedColor = applicationTable.getDisplay().getSystemColor(SWT.COLOR_RED);
						for(int i=0; i<conflictIndexList.size();i++){
							TableItem tempItem = applicationTable.getItem(conflictIndexList.get(i));
							tempItem.setBackground(conflictedColor);
						}
						break;
					}
				}
				else {
					/* update the database */	
					selectedBookingApp.setStatus(MACRO.APPROVED);
					db.updateVenueBookingInfo(selectedBookingApp);
					/* update the application table */
					item.setText(7,"Approved");
				}
		    }
	    }
    }
}
