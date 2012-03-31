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
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

public class WelcomePage extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	/**
	 * Create the composite.
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
		lblEmanager.setBounds(122, 80, 105, 17);
		toolkit.adapt(lblEmanager, true, true);
		lblEmanager.setText("eManager");
		
		Button btnEventPlanning = new Button(this, SWT.NONE);
		btnEventPlanning.setBounds(122, 126, 188, 52);
		toolkit.adapt(btnEventPlanning, true, true);
		btnEventPlanning.setText("Event Planning");
		btnEventPlanning.addSelectionListener(new EventPlanner());
		
		Button btnVenueManagement = new Button(this, SWT.NONE);
		btnVenueManagement.setBounds(122, 194, 188, 52);
		toolkit.adapt(btnVenueManagement, true, true);
		btnVenueManagement.setText("Venue Management");
		btnVenueManagement.addSelectionListener(new venueManager());
	}
	
	class EventPlanner extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Shell add_item_shell = new Shell(getDisplay(), SWT.NO_TRIM | SWT.ON_TOP);
			add_item_shell.setLocation(400,250);
			SelectEventPage add_new_item_page = new SelectEventPage(add_item_shell,
					SWT.None);
			add_new_item_page.pack();
			add_item_shell.pack();
			add_item_shell.open();
			getShell().dispose();
		}
	}
	
	class venueManager extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			SessionManager.setCurrentMode(MACRO.MANAGER);
			Shell shell = new Shell(getDisplay());
			shell.setLocation(200,100);
			Image icon = new Image(getDisplay(), "resources/eManager.png");
			shell.setText("eManager");
			shell.setImage(icon);
			Venuespace ws2 = new Venuespace(shell, SWT.None, MACRO.ORGANIZER_MODE);
				ws2.pack();
				shell.pack();
				shell.open();
			}	
		}
}


