import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

public class PromptPassword extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text textPassWord;
	private String password = "";
	private int changeToMode;
	private Composite parent;
	private String[] stringPassword = { "New Password", "Confirm New Password" };
	private int[] signaturePassword = { MACRO.PASSWORD, MACRO.PASSWORD };

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public PromptPassword(Composite parent, int style, int changeToMode) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.changeToMode = changeToMode;
		this.parent = parent;

		// password = SessionManager.getCurrentEvent().getOrganizerPassword();
		// System.out.println(password);

		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(10, 10, 363, 243);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);

		Label lblPassword = new Label(composite, SWT.NONE);
		lblPassword.setFont(SWTResourceManager.getFont("Calibri", 13,
				SWT.NORMAL));
		lblPassword.setText("Password");
		lblPassword.setBounds(37, 60, 85, 26);
		toolkit.adapt(lblPassword, true, true);

		textPassWord = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		textPassWord.setFont(SWTResourceManager.getFont("Myriad Pro Light", 14,
				SWT.NORMAL));
		textPassWord.setBounds(162, 60, 111, 26);
		toolkit.adapt(textPassWord, true, true);

		Button btnConfirm = new Button(composite, SWT.NONE);
		btnConfirm.setBounds(37, 112, 100, 40);
		toolkit.adapt(btnConfirm, true, true);
		btnConfirm.setText("Confirm");
		btnConfirm.addSelectionListener(new MenuConfirmListener());

		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setBounds(162, 112, 100, 40);
		toolkit.adapt(btnCancel, true, true);
		btnCancel.setText("Cancel");
		btnCancel.addSelectionListener(new CancelListener());

	}

	public void CreateEventPage(boolean[][] boolMode) {
		Shell shell = new Shell(getDisplay());
		shell.setLocation(200, 50);
		Image icon = new Image(getDisplay(), "resources/eManager.png");
		shell.setText("Event Planning");
		shell.setImage(icon);
		Eventspace eventspace = new Eventspace(shell, SWT.None);
		eventspace.pack();
		shell.pack();
		shell.open();
		SessionManager.disposeShells(getDisplay(), shell);
	}

	public void CreateVenuePage(boolean[][] boolMode) {
		Shell shell = new Shell(getDisplay());
		shell.setLocation(200, 50);
		Image icon = new Image(getDisplay(), "resources/eManager.png");
		shell.setText("Venue Management");
		shell.setImage(icon);
		Venuespace venuespace = new Venuespace(shell, SWT.None);
		venuespace.pack();
		shell.pack();
		shell.open();
		SessionManager.disposeShells(getDisplay(), shell);
		// Check whether parent.parent is null or not.
		// if (getParent().getParent() != null) {
		// getParent().getParent().dispose();
		// } else
		// getParent().dispose();
	}

	private class CancelListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent event) {
			getParent().dispose();
		}
	}

	// Check which mode they will enter
	class MenuConfirmListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent event) {
			// Check whether their password is correct or not.
			if (changeToMode == MACRO.ORGANIZER) {
				password = SessionManager.getCurrentEvent()
						.getOrganizerPassword();
				if (password == null && textPassWord.getText().isEmpty()) {
					CreateEventPage(MACRO.ORGANIZER_MODE);
					parent.dispose();

				} else if (textPassWord.getText().equals(password)) {
					CreateEventPage(MACRO.ORGANIZER_MODE);
					parent.dispose();
				} else {
					MessageBox warningPage = new MessageBox(getDisplay()
							.getActiveShell(), SWT.OK | SWT.ICON_WARNING);
					warningPage.setText("Warning!");
					warningPage.setMessage("Wrong Organizer Password!");
					warningPage.open();
				}
			}

			if (changeToMode == MACRO.FACILITATOR) {
				password = SessionManager.getCurrentEvent()
						.getFacilitatorPassword();
				if (password == null && textPassWord.getText().isEmpty()) {
					CreateEventPage(MACRO.FACILITATOR_MODE);
					parent.dispose();
				} else if (textPassWord.getText().equals(password)) {
					CreateEventPage(MACRO.FACILITATOR_MODE);
					parent.dispose();
				} else {
					MessageBox warningPage = new MessageBox(getDisplay()
							.getActiveShell(), SWT.OK | SWT.ICON_WARNING);
					warningPage.setText("Warning!");
					warningPage.setMessage("Wrong Facilitator Password!");
					warningPage.open();
				}
			}

			if (changeToMode == MACRO.PARTICIPANT) {
				CreateEventPage(MACRO.PARTICIPANT_MODE);
			}

			if (changeToMode == MACRO.MANAGER) {
				System.out.println(password);
				if (password == null) {
					Shell shell = new Shell(getShell(), SWT.NO_TRIM
							| SWT.ON_TOP);
					shell.setLocation(getShell().getLocation());
					AbstractAdd addPasswordVenue = new AbstractAdd(shell,
							SWT.None, stringPassword, signaturePassword,
							new Table(getShell(), SWT.None)) {

						@Override
						public void onSubmit() {
							// TODO Auto-generated method stub
							String[] stringList = getStringList();
							// update database
							db.updatePassword(stringList[0]);
							Shell venueManagerShell = new Shell(getDisplay());
							venueManagerShell.setLocation(200, 50);
							Image icon = new Image(getDisplay(),
									"resources/eManager.png");
							venueManagerShell.setText("Venue Management");
							venueManagerShell.setImage(icon);
							Venuespace venuespace = new Venuespace(
									venueManagerShell, SWT.None);
							SessionManager.disposeShells(getDisplay(),
									venueManagerShell);
							venuespace.pack();
							venueManagerShell.pack();
							venueManagerShell.open();
						}

						@Override
						public boolean additionalRequirement() {
							return false;
						}

						@Override
						public boolean additionalCheck() {
							String[] stringList = getStringList();
							boolean isValid = true;
							// if the two input password does not match
							if (!stringList[0].equals(stringList[1])) {
								isValid = false;
								MessageBox warningPage = new MessageBox(
										getDisplay().getActiveShell(), SWT.OK
												| SWT.ICON_WARNING);
								warningPage.setText("Warning!");
								warningPage
										.setMessage("The confirmed new passowrd for venue manager does not match to new password!");
								warningPage.open();
							}
							return isValid;
						}
					};
					addPasswordVenue.setSize(getShell().getSize());
					shell.pack();
					shell.open();
				} else if (textPassWord.getText().equals(password)) {
					CreateVenuePage(MACRO.MANAGER_MODE);
					parent.dispose();
				} else {
					MessageBox warningPage = new MessageBox(getDisplay()
							.getActiveShell(), SWT.OK | SWT.ICON_WARNING);
					warningPage.setText("Warning!");
					warningPage.setMessage("Wrong Manager Password!");
					warningPage.open();
				}
			}

			if (changeToMode == MACRO.APPLICANT) {
				CreateVenuePage(MACRO.APPLICANT_MODE);
			}
		}
	}
}
