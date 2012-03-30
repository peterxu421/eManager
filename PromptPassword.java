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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class PromptPassword extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text textPassWord;
	private String password = "123";
	private int changeToMode;

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

		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(10, 10, 224, 129);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);

		Label lblPassword = new Label(composite, SWT.NONE);
		lblPassword.setText("Password:");
		lblPassword.setBounds(21, 20, 59, 14);
		toolkit.adapt(lblPassword, true, true);

		textPassWord = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		textPassWord.setBounds(96, 20, 103, 19);
		toolkit.adapt(textPassWord, true, true);

		Button btnConfirm = new Button(composite, SWT.NONE);
		btnConfirm.setBounds(10, 55, 94, 28);
		toolkit.adapt(btnConfirm, true, true);
		btnConfirm.setText("Confirm");
		btnConfirm.addSelectionListener(new MenuConfirmListener());

		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setBounds(120, 55, 94, 28);
		toolkit.adapt(btnCancel, true, true);
		btnCancel.setText("Cancel");
		btnCancel.addSelectionListener(new CancelListener());

		Button btnForgotPassword = new Button(composite, SWT.NONE);
		btnForgotPassword.setBounds(10, 89, 125, 28);
		toolkit.adapt(btnForgotPassword, true, true);
		btnForgotPassword.setText("Forgot Password?");

	}

	public void CreateEventPage(boolean[] boolMode) {
		Shell shell = new Shell(getDisplay(), SWT.NO_TRIM);
		shell.setLocation(200, 100);
		Image icon = new Image(getDisplay(), "resources/eManager.png");
		shell.setText("eManager");
		shell.setImage(icon);
		Eventspace workspace = new Eventspace(shell, SWT.None, boolMode);
		workspace.pack();
		shell.pack();
		shell.open();
		getParent().getShell().getParent().dispose();
	}

	public void CreateVenuePage(boolean[] boolMode) {
		Shell shell = new Shell(getDisplay());
		shell.setLocation(200, 100);
		Image icon = new Image(getDisplay(), "resources/eManager.png");
		shell.setText("eManager");
		shell.setImage(icon);
		Venuespace venuespace = new Venuespace(shell, SWT.None, boolMode);
		venuespace.pack();
		shell.pack();
		shell.open();
	}

	private class CancelListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent event) {
			getParent().dispose();
		}
	}

	class MenuConfirmListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent event) {
			if (changeToMode == MACRO.ORGANIZER) {
				// password = event.getOrganizerPassword();
				if (textPassWord.getText().equals(password)) {
					CreateEventPage(MACRO.ORGANIZER_MODE);
				}
			}

			if (changeToMode == MACRO.FACILITATOR) {
				// password = event.getFacilitatorPassword();
				if (textPassWord.getText().equals(password)) {
					CreateEventPage(MACRO.FACILITATOR_MODE);
				}
			}

			if (changeToMode == MACRO.PARTICIPANT) {
				CreateEventPage(MACRO.PARTICIPANT_MODE);
			}

			if (changeToMode == MACRO.MANAGER) {
				CreateVenuePage(MACRO.MANAGER_MODE);
			}

			if (changeToMode == MACRO.APPLICANT) {
				CreateVenuePage(MACRO.APPLICANT_MODE);
			}
		}
	}
}
