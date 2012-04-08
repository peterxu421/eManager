package eManager.venueSpace;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;

import eManager.PromptPassword;
import eManager.abstractForm.AbstractAdd;
import eManager.database.DatabaseReader;
import eManager.macro.MACRO;
import eManager.macro.SessionManager;

public class SelectVenueModePage extends Composite {

	private String[] stringPassword = { "New Password", "Confirm New Password" };
	private int[] signaturePassword = { MACRO.PASSWORD, MACRO.PASSWORD };
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	Composite parent;

	public SelectVenueModePage(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.parent = parent;

		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(40, 10, 308, 225);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);

		Label lblPleaseSelectMode = new Label(composite, SWT.NONE);
		lblPleaseSelectMode.setFont(SWTResourceManager.getFont("Calibri", 13,
				SWT.NORMAL));
		lblPleaseSelectMode.setBounds(96, 21, 184, 28);
		toolkit.adapt(lblPleaseSelectMode, true, true);
		lblPleaseSelectMode.setText("Please Select Mode");

		Button btnOrganizer = new Button(composite, SWT.NONE);
		btnOrganizer.setBounds(50, 70, 100, 100);
		toolkit.adapt(btnOrganizer, true, true);
		btnOrganizer.setText("Manager");
		btnOrganizer.addSelectionListener(new ManagerListener());

		Button btnFacilitator = new Button(composite, SWT.NONE);
		btnFacilitator.setBounds(180, 70, 100, 100);
		toolkit.adapt(btnFacilitator, true, true);
		btnFacilitator.setText("Applicant");
		btnFacilitator.addSelectionListener(new ApplicantListener());
	}

	class ManagerListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Shell pass_shell = new Shell(getDisplay(), SWT.NO_TRIM | SWT.ON_TOP);
			pass_shell.setLocation(getShell().getLocation());
			SessionManager.setCurrentMode(MACRO.MANAGER);
			DatabaseReader db = new DatabaseReader();
			// If they are the first time to use the software
			if (db.getPassword() == null) {
				Shell shell = new Shell(getShell(), SWT.NO_TRIM | SWT.ON_TOP);
				shell.setLocation(getShell().getLocation());
				AbstractAdd addPasswordVenue = new AbstractAdd(shell, SWT.None,
						stringPassword, signaturePassword, new Table(
								getShell(), SWT.None)) {

					@Override
					public void onSubmit() {
						// TODO Auto-generated method stub
						String[] stringList = getStringList();
						// update database
						db.insertPassword(stringList[0]);
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
			}
			// If they have already used the software at least for once
			else {
				PromptPassword pass_page = new PromptPassword(pass_shell,
						SWT.None, MACRO.MANAGER);
				pass_page.pack();
				pass_shell.pack();
				pass_shell.open();
			}
		}
	}

	class ApplicantListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Shell shell = new Shell(getDisplay());
			shell.setLocation(200, 50);
			Image icon = new Image(getDisplay(), "resources/eManager.png");
			shell.setText("eManager - Venue Registration");
			shell.setImage(icon);
			SessionManager.setCurrentMode(MACRO.APPLICANT);
			SessionManager.disposeShells(getDisplay(), shell);
			Venuespace eventSpace = new Venuespace(shell, SWT.None);
			eventSpace.pack();
			shell.pack();
			shell.open();
		}
	}
}
