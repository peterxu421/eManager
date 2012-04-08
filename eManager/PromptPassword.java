package eManager;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import eManager.database.DatabaseReader;
import eManager.eventSpace.Eventspace;
import eManager.macro.MACRO;
import eManager.macro.SessionManager;
import eManager.venueSpace.Venuespace;

public class PromptPassword extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text textPassWord;
	private String password = "";
	private int changeToMode;
	private Composite parent;

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
		shell.setText("eManager - Event Management");
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
		shell.setText("eMananger - Venue Management");
		shell.setImage(icon);
		Venuespace venuespace = new Venuespace(shell, SWT.None);
		venuespace.pack();
		shell.pack();
		shell.open();
		SessionManager.disposeShells(getDisplay(), shell);
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
				DatabaseReader db = new DatabaseReader();
				password = db.getPassword();
				if (password == null && textPassWord.getText().isEmpty()) {
					CreateVenuePage(MACRO.MANAGER_MODE);
					parent.dispose();
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
