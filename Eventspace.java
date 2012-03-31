import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

class Eventspace extends Composite {

	Composite body;
	Composite left;
	Composite right;
	private String[] stringPassword = { "Old Password", "New Password",
			"Confirm New Password" };
	private int[] signaturePassword = { MACRO.PASSWORD, MACRO.PASSWORD,
			MACRO.PASSWORD };
	private String[] stringButton = { "Submit", "Cancel", "Organizer Password",
			"Facilitator Password" };
	private String[] stringSetting = { "Event Name", "Event Description" };
	private int[] signatureSetting = { MACRO.TEXT, MACRO.TEXTBIG };

	private String[] optionList = new String[] { "Organizer", "Facilitator",
			"Participants", "Registration" };

	private boolean[] booleanList = new boolean[] { true, true, true, true };

	private String[][] tabList = new String[][] {
			{ "Pre-Event", "Actual-Event", "Meeting", "Budget", "Feedback" },
			{ "Manpower Allocation" }, { "Itinerary", "Packing List" },
			{ "Event Registration" } };

	// Constructor
	public Eventspace(Composite parent, int style, boolean[] boolMode) {
		super(parent, style);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		this.setLayout(gridLayout);

		// header
		GridData headerData = new GridData(1000, 60);
		Composite header = new Composite(this, SWT.None);
		header.setLayoutData(headerData);

		// optionBar
		GridData optionBarData = new GridData(1000, 50);
		Composite optionBar = new Composite(this, SWT.None);
		optionBar.setLayoutData(optionBarData);

		// optionBar->
		GridLayout optionBarLayout = new GridLayout();
		optionBarLayout.marginLeft = 150;
		optionBarLayout.numColumns = 5;
		optionBar.setLayout(optionBarLayout);

		// optionBar->options
		int num = optionList.length;
		int width = 100;
		int height = 40;
		Button[] buttons = new Button[num];
		for (int i = 0; i < num; i++) {
			System.out.println(num + " " + i);
			System.out.println(num + " " + i);
			buttons[i] = new Button(optionBar, SWT.PUSH);
			buttons[i].setLayoutData(new GridData(width, height));
			buttons[i].setText(optionList[i]);
			buttons[i].addSelectionListener(new OptionSelectionAdapter());
			System.out.println("bool" + boolMode[0]);
			// buttons[i].setEnabled(boolMode[i]);
		}
		// setting button
		Button btnSetting = new Button(optionBar, SWT.PUSH);
		btnSetting.setText("Setting");
		GridData settingData = new GridData(width - 20, height);
		settingData.horizontalIndent = 150;
		btnSetting.setLayoutData(settingData);
		btnSetting.addSelectionListener(new SettingPage());

		// body
		GridData bodyData = new GridData(900, 450);
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
		Label eventName = new Label(header, SWT.NONE);
		eventName.setText(SessionManager.getCurrentEvent().getEventName());
		Label eventDescription = new Label(header, SWT.NONE);
		eventDescription.setText(SessionManager.getCurrentEvent()
				.getEventDescription());

		// body->left panel
		GridLayout leftLayout = new GridLayout();
		leftLayout.numColumns = 1;
		left.setLayout(leftLayout);

		num = tabList[0].length;
		buttons = new Button[num];
		for (int i = 0; i < num; i++) {
			buttons[i] = new Button(left, SWT.PUSH);
			buttons[i].setText(tabList[0][i]);
			buttons[i].setLayoutData(new GridData(130, 20));
			buttons[i].addSelectionListener(new TabSelectionAdapter());
		}

		// body -> right panel
		EventPlanning_PreEvent pre_event = new EventPlanning_PreEvent(right,
				SWT.None, SessionManager.getCurrentEvent());
		pre_event.pack();

	}

	class SettingPage extends SelectionAdapter {
		public void widgetselected(SelectionEvent e) {
			Shell settingShell = new Shell(getShell(), SWT.ON_TOP | SWT.NO_TRIM);
			AbstractEdit settingPage = new Setting(settingShell, SWT.None,
					stringSetting, signatureSetting, new Table(getShell(),
							SWT.None), stringButton);
			settingPage.pack();
			settingShell.pack();
			settingShell.open();
		}
	}

	class Setting extends AbstractEdit {
		public Setting(Shell settingShell, int type, String[] stringList,
				int[] sigantureList, Table table, String[] stringButton) {
			super(settingShell, type, stringList, sigantureList, table,
					stringButton);
			// Create the setting page
			Button btnOK = new Button(this, SWT.None);
			btnOK.addSelectionListener(new SubmitHandler());
			btnOK.setText(stringButton[0]);
			btnOK.setLayoutData(new GridData(120, 30));

			Button btnCancel = new Button(this, SWT.None);
			btnCancel.addSelectionListener(new CancelHandler());
			btnCancel.setText(stringButton[1]);
			btnCancel.setLayoutData(new GridData(120, 30));

			Button btnOrganizerPassword = new Button(this, SWT.None);
			btnOrganizerPassword
					.addSelectionListener(new OrganizerPasswordPage());
			btnOrganizerPassword.setText(stringButton[2]);
			btnOrganizerPassword.setLayoutData(new GridData(120, 30));

			Button btnFacilitatorPassword = new Button(this, SWT.None);
			btnFacilitatorPassword
					.addSelectionListener(new FacilitatorPasswordPage());
			btnFacilitatorPassword.setText(stringButton[3]);
			btnFacilitatorPassword.setLayoutData(new GridData(120, 30));
		}

		// pop up organizer password page
		class OrganizerPasswordPage extends SelectionAdapter {
			Event event;

			public void widgetSelected(SelectionEvent e) {
				Shell organizerPaswordPage = new Shell(getShell());
				event = SessionManager.getCurrentEvent();
				AbstractEdit organizerPasword = new AbstractEdit(
						organizerPaswordPage, SWT.None, stringPassword,
						signaturePassword, new Table(getShell(), SWT.None)) {
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
						// if the input password does not match the system one
						if (!stringList[0].equals(event.getOrganizerPassword())) {
							return false;
						}
						// if the two input password does not match
						else if (!stringList[1].equals(stringList[2])) {
							return false;
						} else
							return true;
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
				Shell facilitatorPaswordPage = new Shell(getShell());
				event = SessionManager.getCurrentEvent();
				AbstractEdit facilitatorPasword = new AbstractEdit(
						facilitatorPaswordPage, SWT.None, stringPassword,
						signaturePassword, new Table(getShell(), SWT.None)) {
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
						// if the input password does not match the system one
						if (!stringList[0].equals(event
								.getFacilitatorPassword())) {
							return false;
						}
						// if the two input password does not match
						else if (!stringList[1].equals(stringList[2])) {
							return false;
						} else
							return true;
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
				eP_facilitator_AllocOfManpower allocation = new eP_facilitator_AllocOfManpower(
						right, SWT.None, SessionManager.getCurrentEvent());
				allocation.pack();
			} else if (name.equals("Itinerary")) {
				eP_participants_itinerary itinerary = new eP_participants_itinerary(
						right, SWT.None, SessionManager.getCurrentEvent());
				itinerary.pack();
			} else if (name.equals("Packing List")) {
				eP_participants_packingList packing = new eP_participants_packingList(
						right, SWT.None, SessionManager.getCurrentEvent());
				packing.pack();
			} else if (name.equals("Event Registration")) {
				eP_eventRegistration register = new eP_eventRegistration(right,
						SWT.None, SessionManager.getCurrentEvent());
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
						button.setLayoutData(new GridData(130, 20));
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
