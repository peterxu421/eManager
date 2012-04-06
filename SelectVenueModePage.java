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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;

public class SelectVenueModePage extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	Composite parent;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
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
		lblPleaseSelectMode.setBounds(96, 21, 127, 28);
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
			Shell pass_shell = new Shell(getShell(), SWT.NO_TRIM | SWT.ON_TOP);
			pass_shell.setLocation(getShell().getLocation());
			SessionManager.setCurrentMode(MACRO.MANAGER);
			PromptPassword pass_page = new PromptPassword(pass_shell, SWT.None,
					MACRO.MANAGER);
			pass_page.pack();
			pass_shell.pack();
			pass_shell.open();
		}
	}

	class ApplicantListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Shell shell = new Shell(getShell(), SWT.NONE);
			shell.setLocation(200, 50);
			Image icon = new Image(getDisplay(), "resources/eManager.png");
			shell.setText("Venue Manager");
			shell.setImage(icon);
			SessionManager.setCurrentMode(MACRO.APPLICANT);
			Venuespace eventSpace = new Venuespace(shell, SWT.None);
			eventSpace.pack();
			shell.pack();
			shell.open();
		}
	}
}
