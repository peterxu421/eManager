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
	// list of applications with valid dates (after today)
	private ArrayList<VenueBookingApplication> notApprovedBookingAppList = new ArrayList<VenueBookingApplication>();

	// list of applications either rejected or pending

	/**
	 * Create the composite.
	 * 
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
		composite.setBounds(0, 0, 800, 400);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);

		btnReject = new Button(composite, SWT.NONE);
		btnReject.setText("Reject");
		btnReject.setBounds(680, 0, 90, 35);
		toolkit.adapt(btnReject, true, true);
		btnReject.addSelectionListener(new reject());

		btnApprove = new Button(composite, SWT.NONE);
		btnApprove.setBounds(680, 50, 90, 35);
		toolkit.adapt(btnApprove, true, true);
		btnApprove.setText("Approve");
		btnApprove.addSelectionListener(new approve());

		applicationTable = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		applicationTable.setLinesVisible(true);
		applicationTable.setHeaderVisible(true);
		applicationTable.setBounds(0, 0, 650, 400);
		toolkit.adapt(applicationTable);
		toolkit.paintBordersFor(applicationTable);

		TableColumn tblclmnVenueName = new TableColumn(applicationTable,
				SWT.NONE);
		tblclmnVenueName.setWidth(98);
		tblclmnVenueName.setText("Booked Venue");

		TableColumn tblclmnName = new TableColumn(applicationTable, SWT.CENTER);
		tblclmnName.setWidth(80);
		tblclmnName.setText("Name");

		TableColumn tblclmnMatricNo = new TableColumn(applicationTable,
				SWT.CENTER);
		tblclmnMatricNo.setWidth(100);
		tblclmnMatricNo.setText("Matric No.");

		TableColumn tblclmnOrganization = new TableColumn(applicationTable,
				SWT.CENTER);
		tblclmnOrganization.setWidth(88);
		tblclmnOrganization.setText("Organization");

		TableColumn tblclmnContact = new TableColumn(applicationTable,
				SWT.CENTER);
		tblclmnContact.setWidth(100);
		tblclmnContact.setText("Contact No.");

		TableColumn tblclmnNewEmail = new TableColumn(applicationTable,
				SWT.CENTER);
		tblclmnNewEmail.setWidth(106);
		tblclmnNewEmail.setText("Eamil");

		TableColumn tblclmnDateAndTime = new TableColumn(applicationTable,
				SWT.CENTER);
		tblclmnDateAndTime.setWidth(181);
		tblclmnDateAndTime.setText("Date and Time");

		TableColumn tblclmnApproval = new TableColumn(applicationTable,
				SWT.CENTER);
		tblclmnApproval.setWidth(100);
		tblclmnApproval.setText("Status");

		Calendar calendar = Calendar.getInstance(); // today
		today.setYear(calendar.get(Calendar.YEAR));
		today.setMonth(calendar.get(Calendar.MONTH) + 1);
		today.setDay(calendar.get(Calendar.DATE));

		importApplicationData();

	}

	public void importApplicationData() {
		bookingAppList = db.getVenueBookingInfoFromToday(today);
		// show only the pending and rejected applications
		for (int i = 0; i < bookingAppList.size(); i++) {
			if (bookingAppList.get(i).getStatus() != MACRO.APPROVED) {
				notApprovedBookingAppList.add(bookingAppList.get(i));
			}
		}
		if (!notApprovedBookingAppList.isEmpty()) {
			for (int j = 0; j < notApprovedBookingAppList.size(); j++) {
				TableItem item = new TableItem(applicationTable, SWT.NULL);
				item.setText(0, notApprovedBookingAppList.get(j).getVenue()
						.getName()
						+ " at "
						+ notApprovedBookingAppList.get(j).getVenue()
								.getLocation());
				item.setText(1, notApprovedBookingAppList.get(j).getApplicant()
						.getName());
				item.setText(2, notApprovedBookingAppList.get(j).getApplicant()
						.getMatricNo());
				item.setText(3, notApprovedBookingAppList.get(j).getApplicant()
						.getOrganization());
				item.setText(4, notApprovedBookingAppList.get(j).getApplicant()
						.getContact());
				item.setText(5, notApprovedBookingAppList.get(j).getApplicant()
						.getEmail());
				item.setText(6, notApprovedBookingAppList.get(j).getDateTime()
						.toString());
				if (notApprovedBookingAppList.get(j).getStatus() == MACRO.PENDING) {
					item.setText(7, "Pending");
				}
				if (notApprovedBookingAppList.get(j).getStatus() == MACRO.REJECTED) {
					item.setText(7, "Rejected");
				}
			}
		}
	}

	public class reject extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			int index = applicationTable.getSelectionIndex();
			if (index >= 0 && index <= applicationTable.getItemCount()) {
				/* update the application table */
				TableItem item = applicationTable.getItem(index);
				item.setBackground(null); // get rid of the conflict reminder if
											// there is any
				item.setText(7, "Rejected");

				/* update the database */
				DatabaseReader db = new DatabaseReader();
				VenueBookingApplication bookingInfo = notApprovedBookingAppList
						.get(index);
				bookingInfo.setStatus(MACRO.REJECTED);
				db.updateVenueBookingInfo(bookingInfo);
			}
		}
	}

	public class approve extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			ArrayList<VenueBookingApplication> conflictedAppList = new ArrayList<VenueBookingApplication>();
			ArrayList<Integer> conflictIndexList = new ArrayList<Integer>();
			boolean conflictWithApprovedApp = false; // a boolean index
			int index = applicationTable.getSelectionIndex();

			if (index >= 0 && index <= applicationTable.getItemCount()) {
				VenueBookingApplication selectedBookingApp = notApprovedBookingAppList
						.get(index);
				TableItem item = applicationTable.getItem(index);
				for (int i = 0; i < bookingAppList.size(); i++) {
					if (bookingAppList.get(i).getVenue()
							.isSameAs(selectedBookingApp.getVenue()) // same
																		// venue
							&& bookingAppList
									.get(i)
									.getDateTime()
									.getDate()
									.isEqualTo(
											selectedBookingApp.getDateTime()
													.getDate())
							&& bookingAppList
									.get(i)
									.getDateTime()
									.getTimeStart()
									.isEarlierThan(
											selectedBookingApp.getDateTime()
													.getTimeEnd())
							&& bookingAppList
									.get(i)
									.getDateTime()
									.getTimeEnd()
									.isLaterThan(
											selectedBookingApp.getDateTime()
													.getTimeStart()) // conflicted
																		// time
							&& i != index
									+ (bookingAppList.size() - notApprovedBookingAppList
											.size())
					// locate the selected application in the list of
					// applications with valid dates
					// no conflict with itself
					) {
						if (bookingAppList.get(i).getStatus() == MACRO.APPROVED) {
							conflictWithApprovedApp = true;
						}
						if (bookingAppList.get(i).getStatus() == MACRO.PENDING) {
							conflictedAppList.add(bookingAppList.get(i));
							int conflictIndex = i
									- (bookingAppList.size() - notApprovedBookingAppList
											.size());
							// locate the conflict in the table
							conflictIndexList.add(conflictIndex);
						}
					}
				}

				// warning page for conflict with approved applications
				if (conflictWithApprovedApp == true) {
					MessageBox warningPage = new MessageBox(getDisplay()
							.getActiveShell(), SWT.OK | SWT.ICON_WARNING);
					warningPage.setText("Warning!");
					warningPage
							.setMessage("Conflict with approved applications! ");
					warningPage.open();
				}
				// warning page for conflict with pending applications
				else if (!conflictedAppList.isEmpty()) {
					MessageBox warningPage = new MessageBox(getDisplay()
							.getActiveShell(), SWT.YES | SWT.NO
							| SWT.ICON_WARNING);
					warningPage.setText("Warning!");
					warningPage
							.setMessage("Conflicted pending application(s) will be rejected automatically. Do you want to review it(them) before proceeding? ");
					int choice = warningPage.open(); // indicates the user's
														// choice
					switch (choice) {
					case SWT.NO:
						/* update the database */
						for (int i = 0; i < conflictedAppList.size(); i++) {
							conflictedAppList.get(i).setStatus(MACRO.REJECTED);
							db.updateVenueBookingInfo(conflictedAppList.get(i));
						}
						selectedBookingApp.setStatus(MACRO.APPROVED);
						db.updateVenueBookingInfo(selectedBookingApp);
						/* update the application table */
						for (int i = 0; i < conflictIndexList.size(); i++) {
							TableItem tempItem = applicationTable
									.getItem(conflictIndexList.get(i));
							tempItem.setText(7, "Rejected");
						}
						item.setText(7, "Approved");
						break;
					case SWT.YES:
						/* highlight conflicts in the table */
						Color conflictedColor = applicationTable.getDisplay()
								.getSystemColor(SWT.COLOR_RED);
						for (int i = 0; i < conflictIndexList.size(); i++) {
							TableItem tempItem = applicationTable
									.getItem(conflictIndexList.get(i));
							tempItem.setBackground(conflictedColor);
						}
						break;
					}
				}
				// no conflict
				else {
					/* update the database */
					selectedBookingApp.setStatus(MACRO.APPROVED);
					db.updateVenueBookingInfo(selectedBookingApp);
					/* update the application table */
					item.setText(7, "Approved");
				}
			}
		}
	}
}
