package eManager.eventSpace;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import eManager.abstractForm.AbstractEdit;
import eManager.dataType.event.Event;
import eManager.eventSpace.facilitator.EventPlanning_AllocationOfManpower;
import eManager.eventSpace.organizer.actualEvent.EventPlanning_ActualEvent;
import eManager.eventSpace.organizer.budget.EventPlanning_Budget;
import eManager.eventSpace.organizer.feedback.EventPlanning_FeedBack;
import eManager.eventSpace.organizer.meeting.EventPlanning_Meeting;
import eManager.eventSpace.organizer.preEvent.EventPlanning_PreEvent;
import eManager.eventSpace.participant.EventPlanning_Itinerary;
import eManager.eventSpace.participant.EventPlanning_PackingList;
import eManager.eventSpace.registration.EventPlanning_EventRegistration;
import eManager.macro.MACRO;
import eManager.macro.SessionManager;

public class Eventspace extends Composite {
	Composite header;
	Composite optionBar;
	Composite body;
	Composite left;
	Composite right;
	boolean[][] boolMode = SessionManager.getCurrentBoolMode();

	Label eventName;
	Label eventDescription;
	private String[] stringArrayRegistration = { "Name", "Matriculation",
			"Year", "Faculty", "Event", "Applying for", "Food Type", "Allergy" };
	private int[] signatureArrayRegistration = { MACRO.TEXT, MACRO.TEXT,
			MACRO.YEAR, MACRO.FACULTY, MACRO.READONLY, MACRO.ROLES, MACRO.TEXT,
			MACRO.TEXT };
	private String[] stringPassword = { "Original Password", "New Password",
			"Confirm New Password" };
	private int[] signaturePassword = { MACRO.PASSWORD, MACRO.PASSWORD,
			MACRO.PASSWORD };
	private String[] stringButton = { "Submit", "Cancel", "Organizer Password",
			"Facilitator Password" };
	private String[] stringSetting = { "Event Name", "Event Description" };
	private int[] signatureSetting = { MACRO.TEXT, MACRO.TEXTBIG };

	private String[] optionList = new String[] { "Organizer", "Facilitator",
			"Participants", "Event Registration" };

	private String[][] tabList = new String[][] {
			{ "Pre-Event", "Actual-Event", "Meeting", "Budget", "Feedback" },
			{ "Manpower Allocation" }, { "Itinerary", "Packing List" },
			{ "Event Registration" } };

	public Button[] getButtons() {
		return (Button[]) left.getChildren();
	}

	// Constructor
	public Eventspace(Composite parent, int style) {
		super(parent, style);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		this.setLayout(gridLayout);

		// header
		GridData headerData = new GridData(1000, 70);
		header = new Composite(this, SWT.None);
		header.setLayoutData(headerData);

		// optionBar
		GridData optionBarData = new GridData(1000, 50);
		optionBar = new Composite(this, SWT.None);
		optionBar.setLayoutData(optionBarData);

		// optionBar->
		GridLayout optionBarLayout = new GridLayout();
		optionBarLayout.marginLeft = 150;
		optionBarLayout.numColumns = 5;
		optionBar.setLayout(optionBarLayout);

		// optionBar->options
		int num = optionList.length;
		int width = 130;
		int height = 40;
		Button[] options = new Button[num];
		for (int i = 0; i < num; i++) {
			options[i] = new Button(optionBar, SWT.PUSH);
			options[i].setLayoutData(new GridData(width, height));
			options[i].setText(optionList[i]);
			options[i].addSelectionListener(new OptionSelectionAdapter());
			options[i].setEnabled(boolMode[0][i]);
		}
		// setting button
		Button btnSetting = new Button(optionBar, SWT.PUSH);
		btnSetting.setText("Setting");
		GridData settingData = new GridData(width - 50, height);
		settingData.horizontalIndent = 150;
		btnSetting.setLayoutData(settingData);
		btnSetting.addSelectionListener(new SettingAdapter());
		btnSetting.setEnabled(boolMode[5][0]);

		// body
		GridData bodyData = new GridData(1000, 460);
		body = new Composite(this, SWT.None);
		body.setLayoutData(bodyData);

		// body->
		GridLayout bodyLayout = new GridLayout();
		bodyLayout.numColumns = 2;
		body.setLayout(bodyLayout);

		// body->left & right panel
		GridData leftData = new GridData(150, 450);
		GridData rightData = new GridData(800, 450);
		left = new Composite(body, SWT.None);
		right = new Composite(body, SWT.None);
		left.setLayoutData(leftData);
		right.setLayoutData(rightData);

		// header->
		FillLayout headerLayout = new FillLayout();
		headerLayout.marginHeight = 20;
		headerLayout.marginWidth = 20;
		headerLayout.spacing = 50;
		header.setLayout(headerLayout);
		eventName = new Label(header, SWT.NONE);
		eventName.setText(SessionManager.getCurrentEvent().getEventName());
		eventDescription = new Label(header, SWT.WRAP);
		eventDescription.setText(SessionManager.getCurrentEvent()
				.getEventDescription());

		// body->left panel
		GridLayout leftLayout = new GridLayout();
		leftLayout.numColumns = 1;
		left.setLayout(leftLayout);
		num = tabList[0].length;
		Button[] buttons = new Button[num];
		for (int i = 0; i < num; i++) {
			buttons[i] = new Button(left, SWT.PUSH);
			buttons[i].setText(tabList[0][i]);
			buttons[i].setLayoutData(new GridData(130, 40));
			buttons[i].addSelectionListener(new TabSelectionAdapter());
			buttons[i].setEnabled(boolMode[1][i]);
		}

		// body -> right panel
		int mode = SessionManager.getCurrentIntMode();
		if (mode == MACRO.ORGANIZER)
			options[0].notifyListeners(SWT.Selection, null);
		else if (mode == MACRO.FACILITATOR)
			options[1].notifyListeners(SWT.Selection, null);
		else if (mode == MACRO.PARTICIPANT)
			options[2].notifyListeners(SWT.Selection, null);
	}

	class SettingAdapter extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Shell settingShell = new Shell(getShell(), SWT.APPLICATION_MODAL
					| SWT.DIALOG_TRIM);
			settingShell.setLocation(400, 200);
			AbstractEdit settingPage = new Setting(settingShell, SWT.None,
					stringSetting, signatureSetting, stringButton);
			settingPage.pack();
			settingShell.pack();
			settingShell.open();
		}
	}

	class Setting extends AbstractEdit {
		public Setting(Shell settingShell, int type, String[] stringList,
				int[] sigantureList, String[] stringButton) {
			super(settingShell, type, stringList, sigantureList, stringButton);
			// Create the setting page
			Button btnOK = new Button(this, SWT.None);
			btnOK.addSelectionListener(new SubmitHandler());
			btnOK.setText(stringButton[0]);
			GridData gridData = new GridData(80, 40);
			gridData.horizontalIndent = 25;
			btnOK.setLayoutData(gridData);

			Button btnCancel = new Button(this, SWT.None);
			btnCancel.addSelectionListener(new CancelHandler());
			btnCancel.setText(stringButton[1]);
			btnCancel.setLayoutData(gridData);

			Button btnOrganizerPassword = new Button(this, SWT.None);
			btnOrganizerPassword
					.addSelectionListener(new OrganizerPasswordPage());
			btnOrganizerPassword.setText(stringButton[2]);
			btnOrganizerPassword.setLayoutData(new GridData(130, 40));

			Button btnFacilitatorPassword = new Button(this, SWT.None);
			btnFacilitatorPassword
					.addSelectionListener(new FacilitatorPasswordPage());
			btnFacilitatorPassword.setText(stringButton[3]);
			btnFacilitatorPassword.setLayoutData(new GridData(130, 40));
		}

		// pop up organizer password page
		class OrganizerPasswordPage extends SelectionAdapter {
			Event event;

			public void widgetSelected(SelectionEvent e) {
				Shell organizerPaswordPage = new Shell(getShell(),
						SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
				organizerPaswordPage.setLocation(getShell().getLocation());
				organizerPaswordPage.setSize(getShell().getSize());
				event = SessionManager.getCurrentEvent();
				AbstractEdit organizerPasword = new AbstractEdit(
						organizerPaswordPage, SWT.None, stringPassword,
						signaturePassword) {
					// Do nothing.
					public void onLoad() {
					}

					// Override
					public void onSubmit() {
						String[] stringList = getStringList();
						// reset
						event.setOrganizerPassword(stringList[1]);
						// update database
						db.updateEvent(event);
					}

					// Override
					public boolean additionalCheck() {
						String[] stringList = getStringList();
						boolean isValid = true;
						// if the input password does not match the system one
						if (!stringList[0].equals(event.getOrganizerPassword())) {
							isValid = false;
							MessageBox warningPage = new MessageBox(
									getDisplay().getActiveShell(), SWT.OK
											| SWT.ICON_WARNING);
							warningPage.setText("Warning!");
							warningPage
									.setMessage("Original organizer password is wrong!");
							warningPage.open();
						}
						// if the two input password does not match
						else if (!stringList[1].equals(stringList[2])) {
							isValid = false;
							MessageBox warningPage = new MessageBox(
									getDisplay().getActiveShell(), SWT.OK
											| SWT.ICON_WARNING);
							warningPage.setText("Warning!");
							warningPage
									.setMessage("The confirmed new passowrd for organizer does not match to new password!");
							warningPage.open();
						}

						return isValid;
					}
				};
				organizerPasword.pack();
				organizerPaswordPage.pack();
				organizerPaswordPage.open();
			}
		}

		// pop up facilitator password page
		class FacilitatorPasswordPage extends SelectionAdapter {
			Event event;

			public void widgetSelected(SelectionEvent e) {
				Shell facilitatorPaswordPage = new Shell(getShell(),
						SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
				facilitatorPaswordPage.setLocation(getShell().getLocation());
				facilitatorPaswordPage.setSize(getShell().getSize());
				event = SessionManager.getCurrentEvent();
				AbstractEdit facilitatorPasword = new AbstractEdit(
						facilitatorPaswordPage, SWT.None, stringPassword,
						signaturePassword) {
					// Do nothing.
					public void onLoad() {
					}

					// Override
					public void onSubmit() {
						String[] stringList = getStringList();
						// reset
						event.setFacilitatorPassword(stringList[1]);
						// update database
						db.updateEvent(event);
					}

					// Override
					public boolean additionalCheck() {
						String[] stringList = getStringList();
						boolean isValid = true;
						// if the input password does not match the system one
						if (!stringList[0].equals(event
								.getFacilitatorPassword())) {
							isValid = false;
							MessageBox warningPage = new MessageBox(
									getDisplay().getActiveShell(), SWT.OK
											| SWT.ICON_WARNING);
							warningPage.setText("Warning!");
							warningPage
									.setMessage("Original facilitator password is wrong!");
							warningPage.open();
						}
						// if the two input password does not match
						else if (!stringList[1].equals(stringList[2])) {
							isValid = false;
							MessageBox warningPage = new MessageBox(
									getDisplay().getActiveShell(), SWT.OK
											| SWT.ICON_WARNING);
							warningPage.setText("Warning!");
							warningPage
									.setMessage("The confirmed new passowrd for facilitator does not match to new password!");
							warningPage.open();
						}
						return isValid;
					}
				};
				facilitatorPasword.pack();
				facilitatorPaswordPage.pack();
				facilitatorPaswordPage.open();
			}
		}

		@Override
		public void onLoad() {
			setData(SessionManager.getCurrentEvent().getEventName(),
					signatureSetting[0], 0);
			setData(SessionManager.getCurrentEvent().getEventDescription(),
					signatureSetting[1], 1);
			Text eventName = (Text) get(0);
			eventName.addVerifyListener(new VerifyListener() {
				public void verifyText(VerifyEvent e) {
					String exp = "/\\:*?\"<>|";
					char[] check = exp.toCharArray();
					boolean isValid = true;
					for (int i = 0; i < check.length; i++) {
						if (e.text.length() != 0)
							if (e.text.charAt(0) == check[i])
								isValid = false;
					}
					if (isValid == false) {
						e.doit = false;
						MessageBox messageBox = new MessageBox(getShell(),
								SWT.OK | SWT.ICON_ERROR);
						messageBox.setText("ERROR");
						messageBox.setMessage("EventName must not contant "
								+ exp);
						messageBox.open();
					}
				}
			});
		}

		@Override
		public void onSubmit() {
			String[] stringList = getStringList();
			// reset
			Event event = SessionManager.getCurrentEvent();
			event.setEventName(stringList[0]);
			event.setEventDescription(stringList[1]);
			// update database
			db.updateEvent(event);
			// update event name and event description
			eventName.setText(stringList[0]);
			eventDescription.setText(stringList[1]);
		}

		@Override
		public boolean additionalCheck() {
			return true;
		}
	}

	class TabSelectionAdapter extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (right.getChildren().length != 0) {
				right.getChildren()[0].dispose();
			}
			String name = ((Button) e.getSource()).getText();
			if (name.equals("Pre-Event")) {
				EventPlanning_PreEvent pre_event = new EventPlanning_PreEvent(
						right, SWT.None, SessionManager.getCurrentEvent());
				pre_event.pack();
			} else if (name.equals("Meeting")) {
				EventPlanning_Meeting meeting = new EventPlanning_Meeting(
						right, SWT.None, SessionManager.getCurrentEvent());
				meeting.pack();
			} else if (name.equals("Feedback")) {
				EventPlanning_FeedBack feedback = new EventPlanning_FeedBack(
						right, SWT.None, SessionManager.getCurrentEvent());
				feedback.pack();
			} else if (name.equals("Budget")) {
				EventPlanning_Budget budget = new EventPlanning_Budget(right,
						SWT.None, SessionManager.getCurrentEvent());
				budget.pack();
			} else if (name.equals("Actual-Event")) {
				EventPlanning_ActualEvent actual_event = new EventPlanning_ActualEvent(
						right, SWT.NONE, SessionManager.getCurrentEvent());
				actual_event.pack();
			} else if (name.equals("Manpower Allocation")) {
				EventPlanning_AllocationOfManpower allocation = new EventPlanning_AllocationOfManpower(
						right, SWT.None, SessionManager.getCurrentEvent());
				allocation.pack();
			} else if (name.equals("Itinerary")) {
				EventPlanning_Itinerary itinerary = new EventPlanning_Itinerary(
						right, SWT.None, SessionManager.getCurrentEvent());
				itinerary.pack();
			} else if (name.equals("Packing List")) {
				EventPlanning_PackingList packing = new EventPlanning_PackingList(
						right, SWT.None, SessionManager.getCurrentEvent());
				packing.pack();
			} else if (name.equals("Event Registration")) {
				EventPlanning_EventRegistration register = new EventPlanning_EventRegistration(
						right, SWT.BORDER, stringArrayRegistration,
						signatureArrayRegistration);
				register.pack();
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
			for (int i = 0; i < optionList.length; i++) {
				if (name.equals(optionList[i])) {
					for (int j = 0; j < tabList[i].length; j++) {
						Button button = new Button(left, SWT.PUSH);
						button.setText(tabList[i][j]);
						button.setLayoutData(new GridData(130, 40));
						button.addSelectionListener(new TabSelectionAdapter());
						if (j == 0)
							button.notifyListeners(SWT.Selection, null);
					}
				}
			}
			left.pack();
			right.pack();
			body.pack();
		}
	}
}
