package eManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import eManager.eventSpace.SelectEventPage;
import eManager.venueSpace.SelectVenueModePage;

public class WelcomePage extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public WelcomePage(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		setLayout(null);

		Label lblEmanager = new Label(this, SWT.NONE);
		lblEmanager.setFont(SWTResourceManager.getFont("Calibri", 13,
				SWT.NORMAL));
		lblEmanager.setBounds(110, 80, 105, 23);
		toolkit.adapt(lblEmanager, true, true);
		lblEmanager.setText("eManager");

		Button btnEventManagement = new Button(this, SWT.NONE);
		btnEventManagement.setBounds(110, 120, 188, 60);
		toolkit.adapt(btnEventManagement, true, true);
		btnEventManagement.setText("Event Management");
		btnEventManagement.addSelectionListener(new EventPlanner());

		Button btnVenueManagement = new Button(this, SWT.NONE);
		btnVenueManagement.setBounds(110, 194, 188, 60);
		toolkit.adapt(btnVenueManagement, true, true);
		btnVenueManagement.setText("Venue Management");
		btnVenueManagement.addSelectionListener(new VenueManager());
	}

	class EventPlanner extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Shell add_item_shell = new Shell(getDisplay(),
					SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
			add_item_shell.setLocation(400, 200);
			add_item_shell.setText("eManager - Event Management");
			Image icon = new Image(getDisplay(), "resources/eManager.png");
			add_item_shell.setImage(icon);
			SelectEventPage add_new_item_page = new SelectEventPage(
					add_item_shell, SWT.None);
			add_new_item_page.setSize(500, 400);
			add_item_shell.pack();
			add_item_shell.open();
			getShell().dispose();
		}
	}

	class VenueManager extends SelectionAdapter {

		public void widgetSelected(SelectionEvent e) {
			Shell add_item_shell = new Shell(getDisplay(),
					SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
			add_item_shell.setLocation(400, 200);
			add_item_shell.setText("eManager - Venue Management");
			Image icon = new Image(getDisplay(), "resources/eManager.png");
			add_item_shell.setImage(icon);
			SelectVenueModePage add_new_item_page = new SelectVenueModePage(
					add_item_shell, SWT.None);
			add_new_item_page.setSize(400, 300);
			add_item_shell.pack();
			add_item_shell.open();
			getShell().dispose();
		}
	}
}
