package eManager.venueSpace.venueBooking;
import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

import eManager.dataType.common.Date;
import eManager.dataType.common.Time;
import eManager.dataType.venue.BookedDateTime;
import eManager.dataType.venue.Venue;
import eManager.dataType.venue.VenueApplicant;
import eManager.dataType.venue.VenueBookingApplication;
import eManager.database.DatabaseReader;

public class VenueBooking_VenueBooking extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text name;
	private Text matricNo;
	private Text organization;
	private List listDateTime;
	private Text contact;
	private Text email;

	private Venue selected;
	private ArrayList<BookedDateTime> bookedDateTimeIntervalList = new ArrayList<BookedDateTime>();

	public VenueBooking_VenueBooking(Composite parent, int style,
			Venue selected, ArrayList<BookedDateTime> bookedDateTimeList) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.selected = selected;

		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(10, 10, 450, 436);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);

		Label lblName = new Label(composite, SWT.NONE);
		lblName.setBounds(10, 10, 72, 17);
		toolkit.adapt(lblName, true, true);
		lblName.setText("Full Name");

		name = new Text(composite, SWT.BORDER);
		name.setBounds(10, 33, 127, 23);
		toolkit.adapt(name, true, true);

		Label lblMatricNo = new Label(composite, SWT.NONE);
		lblMatricNo.setBounds(10, 62, 127, 17);
		toolkit.adapt(lblMatricNo, true, true);
		lblMatricNo.setText("Matriculation Number");

		matricNo = new Text(composite, SWT.BORDER);
		matricNo.setBounds(10, 85, 128, 23);
		toolkit.adapt(matricNo, true, true);

		Label lblOrganization = new Label(composite, SWT.NONE);
		lblOrganization.setBounds(10, 114, 95, 17);
		toolkit.adapt(lblOrganization, true, true);
		lblOrganization.setText("Organization");

		organization = new Text(composite, SWT.BORDER);
		organization.setBounds(10, 137, 127, 23);
		toolkit.adapt(organization, true, true);

		Label lblContactNumber = new Label(composite, SWT.NONE);
		lblContactNumber.setBounds(10, 166, 95, 15);
		toolkit.adapt(lblContactNumber, true, true);
		lblContactNumber.setText("Contact Number");

		contact = new Text(composite, SWT.BORDER);
		contact.setBounds(10, 187, 127, 21);
		toolkit.adapt(contact, true, true);

		Label lblEmail = new Label(composite, SWT.NONE);
		lblEmail.setBounds(10, 214, 55, 15);
		toolkit.adapt(lblEmail, true, true);
		lblEmail.setText("Email");

		email = new Text(composite, SWT.BORDER);
		email.setBounds(10, 235, 127, 21);
		toolkit.adapt(email, true, true);

		Label lblDateTime = new Label(composite, SWT.NONE);
		lblDateTime.setBounds(10, 262, 127, 17);
		toolkit.adapt(lblDateTime, true, true);
		lblDateTime.setText("Date and Time ");

		listDateTime = new List(composite, SWT.BORDER | SWT.V_SCROLL);
		listDateTime.setBounds(10, 285, 232, 98);
		toolkit.adapt(listDateTime, true, true);
		Collections.sort(bookedDateTimeList); // sort the list in the order of
												// real date and time
		for (int i = 0; i < bookedDateTimeList.size(); i++) {
			Date date = bookedDateTimeList.get(i).getDate();
			Time timeStart = bookedDateTimeList.get(i).getTimeStart(); // find
																		// the
																		// start
																		// of
																		// the
																		// time
																		// interval
			while (i < bookedDateTimeList.size() - 1
					&& bookedDateTimeList.get(i).getDate()
							.isEqualTo(bookedDateTimeList.get(i + 1).getDate())) {
				if (bookedDateTimeList
						.get(i)
						.getTimeEnd()
						.isEqualTo(bookedDateTimeList.get(i + 1).getTimeStart())) {
					i++; // move forward in the list of consecutive time slots
				} else
					break;
			}
			Time timeEnd = bookedDateTimeList.get(i).getTimeEnd(); // find the
																	// end of
																	// the time
																	// interval
			BookedDateTime timeInterval = new BookedDateTime(date, timeStart,
					timeEnd); // a whole time interval which puts together
								// consecutive time slots
			bookedDateTimeIntervalList.add(timeInterval);
			listDateTime.add(timeInterval.getDate().toString() + "    From "
					+ timeInterval.getTimeStart().toString() + " to "
					+ timeInterval.getTimeEnd().toString());
		}

		Button btnSubmit = new Button(composite, SWT.NONE);
		btnSubmit.setBounds(10, 389, 75, 25);
		toolkit.adapt(btnSubmit, true, true);
		btnSubmit.setText("Submit");
		btnSubmit.addSelectionListener(new submit());

		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setBounds(118, 389, 75, 25);
		toolkit.adapt(btnCancel, true, true);
		btnCancel.setText("Reset");
		btnCancel.addSelectionListener(new reset());
	}

	public class submit extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			String _name = "";
			String _matricNo = "";
			String _organization = "";
			String _contact = "";
			String _email = "";

			if (!name.getText().isEmpty()) {
				_name = name.getText();
			}
			if (!matricNo.getText().isEmpty()) {
				_matricNo = matricNo.getText();
			}
			if (!organization.getText().isEmpty()) {
				_organization = organization.getText();
			}
			if (!contact.getText().isEmpty()) {
				_contact = contact.getText();
			}
			if (!email.getText().isEmpty()) {
				_email = email.getText();
			}

			/* Update the database with the booking application */
			if (!name.getText().isEmpty() && !matricNo.getText().isEmpty()
					&& !organization.getText().isEmpty()
					&& !contact.getText().isEmpty()
					&& !email.getText().isEmpty()) {
				DatabaseReader db = new DatabaseReader();
				for (int i = 0; i < bookedDateTimeIntervalList.size(); i++) {
					VenueApplicant newApplicant = new VenueApplicant(_name,
							_matricNo, _contact, _email, _organization);
					VenueBookingApplication newBookingApp = new VenueBookingApplication(
							selected, newApplicant,
							bookedDateTimeIntervalList.get(i));
					db.insertVenueBookingInfo(newBookingApp);
				}
				getParent().dispose();
			}
		}
	}

	public class reset extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			name.setText("");
			matricNo.setText("");
			organization.setText("");
			contact.setText("");
			email.setText("");
		}
	}
}
