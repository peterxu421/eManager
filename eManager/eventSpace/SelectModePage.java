package eManager.eventSpace;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

import eManager.PromptPassword;
import eManager.macro.MACRO;
import eManager.macro.SessionManager;

public class SelectModePage extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	Composite parent;

	public SelectModePage(Composite parent, int style) {
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
		composite.setBounds(10, 10, 346, 225);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);

		Label lblPleaseSelectMode = new Label(composite, SWT.NONE);
		lblPleaseSelectMode.setBounds(116, 21, 114, 14);
		toolkit.adapt(lblPleaseSelectMode, true, true);
		lblPleaseSelectMode.setText("Please Select Mode:");

		Button btnOrganizer = new Button(composite, SWT.NONE);
		btnOrganizer.setBounds(10, 68, 94, 98);
		toolkit.adapt(btnOrganizer, true, true);
		btnOrganizer.setText("Organizer");
		btnOrganizer.addSelectionListener(new OrganizerListener());

		Button btnFacilitator = new Button(composite, SWT.NONE);
		btnFacilitator.setBounds(124, 68, 94, 98);
		toolkit.adapt(btnFacilitator, true, true);
		btnFacilitator.setText("Facilitator");
		btnFacilitator.addSelectionListener(new FacilitatorListener());

		Button btnParticipant = new Button(composite, SWT.NONE);
		btnParticipant.setBounds(242, 68, 94, 98);
		toolkit.adapt(btnParticipant, true, true);
		btnParticipant.setText("Participant");
		btnParticipant.addSelectionListener(new ParticipantListener());
	}

	class OrganizerListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Image icon = new Image(getDisplay(), "resources/eManager.png");
			Shell pass_shell = new Shell(getShell(), SWT.APPLICATION_MODAL
					| SWT.DIALOG_TRIM);
			pass_shell.setImage(icon);
			pass_shell.setText("eManager - Enter Organizer Password");
			pass_shell.setLocation(getShell().getLocation());
			SessionManager.setCurrentMode(MACRO.ORGANIZER);
			PromptPassword pass_page = new PromptPassword(pass_shell, SWT.None,
					MACRO.ORGANIZER);
			pass_page.pack();
			pass_shell.pack();
			pass_shell.open();

		}
	}

	class FacilitatorListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Image icon = new Image(getDisplay(), "resources/eManager.png");
			Shell pass_shell = new Shell(getShell(), SWT.APPLICATION_MODAL
					| SWT.DIALOG_TRIM);
			pass_shell.setImage(icon);
			pass_shell.setText("eManager - Enter Facilitator Password");
			pass_shell.setLocation(getShell().getLocation());
			SessionManager.setCurrentMode(MACRO.FACILITATOR);
			PromptPassword pass_page = new PromptPassword(pass_shell, SWT.None,
					MACRO.FACILITATOR);
			pass_page.pack();
			pass_shell.pack();
			pass_shell.open();
		}
	}

	class ParticipantListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Shell shell = new Shell(getDisplay());
			shell.setLocation(200, 100);
			Image icon = new Image(getDisplay(), "resources/eManager.png");
			shell.setText("eManager - Participant");
			shell.setImage(icon);
			SessionManager.setCurrentMode(MACRO.PARTICIPANT);
			Eventspace eventSpace = new Eventspace(shell, SWT.None);
			SessionManager.disposeShells(getDisplay(), shell);
			eventSpace.pack();
			shell.pack();
			shell.open();
		}
	}
}
