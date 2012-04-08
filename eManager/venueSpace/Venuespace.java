package eManager.venueSpace;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import eManager.abstractForm.AbstractEdit;
import eManager.dataType.event.Event;
import eManager.macro.MACRO;
import eManager.macro.SessionManager;
import eManager.venueSpace.venueBooking.VenueBooking_CheckMyApplication;
import eManager.venueSpace.venueBooking.VenueBooking_InstructionPage;
import eManager.venueSpace.venueBooking.VenueBooking_VenueList;
import eManager.venueSpace.venueManagement.VenueManagement_BookingApplications;
import eManager.venueSpace.venueManagement.VenueManagement_VenueList;

public class Venuespace extends Composite {

	Composite body;
	Composite left;
	Composite right;
	Composite header;
	boolean[][] boolMode = SessionManager.getCurrentBoolMode();

	private String[] stringPassword = { "Original Password", "New Password",
			"Confirm New Password" };
	private int[] signaturePassword = { MACRO.PASSWORD, MACRO.PASSWORD,
			MACRO.PASSWORD };
	private String[] optionMenu = new String[] { "Venue Management",
			"Venue Registration", };

	private String[][] tabList = new String[][] {
			{ "Venue List", "Booking Applications", },
			{ "Regulations and Rules", "Select and Book",
					"Check My Applications" } };

	// Constructor
	public Venuespace(Composite parent, int style) {
		super(parent, style);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		this.setLayout(gridLayout);

		// header
		header = new Composite(this, SWT.None);
		header.setLayoutData(new GridData(1000, 100));

		// header->
		FillLayout headerLayout = new FillLayout();
		headerLayout.marginHeight = 20;
		headerLayout.marginWidth = 20;
		headerLayout.spacing = 50;
		header.setLayout(headerLayout);

		// optionBar->
		Composite optionBar = new Composite(this, SWT.None);
		GridLayout optionBarLayout = new GridLayout();
		optionBarLayout.marginLeft = 165;
		optionBarLayout.numColumns = 5;
		optionBar.setLayout(optionBarLayout);
		optionBar.setLayoutData(new GridData(1000, 50));

		// optionBar->options
		int num = optionMenu.length;
		Button[] options = new Button[num];
		int width = 130;
		int height = 40;
		for (int i = 0; i < num; i++) {
			options[i] = new Button(optionBar, SWT.PUSH);
			options[i].setLayoutData(new GridData(width, height));
			options[i].setText(optionMenu[i]);
			options[i].addSelectionListener(new OptionSelectionAdapter());
			options[i].setEnabled(boolMode[0][i]);
		}
		// setting button
		Button btnSetting = new Button(optionBar, SWT.PUSH);
		btnSetting.setText("Setting");
		GridData settingData = new GridData(width-50, height);
		settingData.horizontalIndent = 190;
		btnSetting.setLayoutData(settingData);
		btnSetting.addSelectionListener(new SettingAdapter());
		btnSetting.setEnabled(boolMode[3][0]);

		// body->
		body = new Composite(this, SWT.None);
		body.setLayoutData(new GridData(1000, 460));

		GridLayout bodyLayout = new GridLayout();
		bodyLayout.numColumns = 2;
		body.setLayout(bodyLayout);
		left = new Composite(body, SWT.None);
		right = new Composite(body, SWT.None);
		left.setLayoutData(new GridData(160, 450));
		right.setLayoutData(new GridData(800, 450));

		Label eventName = new Label(header, SWT.WRAP);
		eventName.setFont(SWTResourceManager.getFont("Courier New", 11,
				SWT.NORMAL));
		eventName
				.setText("NUS Office of Studnet Affairs\nCopyright National University of Singapore.\nAll Rights Reserved.");

		Label eventDescription = new Label(header, SWT.WRAP);
		eventDescription.setFont(SWTResourceManager.getFont("Courier New", 11,
				SWT.NORMAL));
		eventDescription.setText("Better service, better univeristy.");

		// body->left panel
		GridLayout leftLayout = new GridLayout();
		leftLayout.numColumns = 1;
		left.setLayout(leftLayout);

		num = tabList[0].length;
		Button[] buttons = new Button[num];
		for (int i = 0; i < num; i++) {
			buttons[i] = new Button(left, SWT.PUSH);
			buttons[i].setText(tabList[0][i]);
			buttons[i].setLayoutData(new GridData(150, 40));
			buttons[i].addSelectionListener(new TabSelectionAdapter());
			buttons[i].setEnabled(boolMode[1][i]);
		}
		// body -> right panel
		int mode = SessionManager.getCurrentIntMode();
		if (mode == MACRO.MANAGER)
			options[0].notifyListeners(SWT.Selection, null);
		else if (mode == MACRO.APPLICANT)
			options[1].notifyListeners(SWT.Selection, null);
	}

	class SettingAdapter extends SelectionAdapter {
		Event event = SessionManager.getCurrentEvent();

		public void widgetSelected(SelectionEvent e) {
			Shell settingShell = new Shell(getShell(), SWT.APPLICATION_MODAL
					| SWT.DIALOG_TRIM);
			settingShell.setLocation(400, 200);
			AbstractEdit settingPage = new AbstractEdit(settingShell, SWT.None,
					stringPassword, signaturePassword) {
				// Do nothing.
				public void onLoad() {
				}

				// Override
				public void onSubmit() {
					String[] stringList = getStringList();
					//update the database
					db.updatePassword(stringList[1]);
				}

				// Override
				public boolean additionalCheck() {
					String[] stringList = getStringList();
					boolean isValid = true;
					// if the input password does not match the system one
					if (!stringList[0].equals(db.getPassword())
							&& stringList[0] != null) {
						isValid = false;
						MessageBox warningPage = new MessageBox(getDisplay()
								.getActiveShell(), SWT.OK | SWT.ICON_WARNING);
						warningPage.setText("Warning!");
						warningPage
								.setMessage("Original venue manager password is wrong!");
						warningPage.open();
					}
					// if the two input password does not match
					else if (!stringList[1].equals(stringList[2])) {
						isValid = false;
						MessageBox warningPage = new MessageBox(getDisplay()
								.getActiveShell(), SWT.OK | SWT.ICON_WARNING);
						warningPage.setText("Warning!");
						warningPage
								.setMessage("The confirmed new passowrd for venue manager does not match to new password!");
						warningPage.open();
					}
					return isValid;
				}
			};
			settingPage.pack();
			settingShell.pack();
			settingShell.open();
		};
	}

	class TabSelectionAdapter extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (right.getChildren().length != 0) {
				right.getChildren()[0].dispose();
			}
			String name = ((Button) e.getSource()).getText();
			if (name.equals("Venue List")) {
				VenueManagement_VenueList vList = new VenueManagement_VenueList(
						right, SWT.NONE);
				vList.pack();
			} else if (name.equals("Booking Applications")) {
				VenueManagement_BookingApplications vApplicants = new VenueManagement_BookingApplications(
						right, SWT.NONE);
				vApplicants.pack();
			} else if (name.equals("Regulations and Rules")) {
				VenueBooking_InstructionPage vInstruc = new VenueBooking_InstructionPage(
						right, SWT.BORDER);
				vInstruc.pack();
			} else if (name.equals("Select and Book")) {
				VenueBooking_VenueList vList = new VenueBooking_VenueList(
						right, SWT.NONE);
				vList.pack();
			} else if (name.equals("Check My Applications")) {
				VenueBooking_CheckMyApplication vCheckMyApp = new VenueBooking_CheckMyApplication(
						right, SWT.NONE);
				vCheckMyApp.pack();
			}
			right.pack();
		}
	}

	class OptionSelectionAdapter extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			int num = left.getChildren().length;
			for (int i = 0; i < num; i++) {
				left.getChildren()[0].dispose();
			}
			left.pack();
			num = right.getChildren().length;
			for (int i = 0; i < num; i++) {
				right.getChildren()[0].dispose();
			}
			right.pack();
			String name = ((Button) e.getSource()).getText();
			for (int i = 0; i < optionMenu.length; i++) {
				if (name.equals(optionMenu[i])) {
					for (int j = 0; j < tabList[i].length; j++) {
						Button button = new Button(left, SWT.PUSH);
						button.setText(tabList[i][j]);
						button.setLayoutData(new GridData(150, 40));
						button.addSelectionListener(new TabSelectionAdapter());
						if (j == 0)
							button.notifyListeners(SWT.Selection, null); // to
																			// display
																			// the
																			// the
																			// first
																			// page
																			// of
																			// each
																			// component
																			// in
																			// the
																			// menu
																			// bar
					}
				}
			}
			left.pack();
			right.pack();
			body.pack();
		}
	}
}
