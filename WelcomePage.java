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
		FormLayout formLayout = new FormLayout();
		formLayout.marginTop = 30;
		formLayout.marginBottom = 30;
		setLayout(formLayout);
		
		Label lblEmanager = new Label(this, SWT.NONE);
		FormData fd_lblEmanager = new FormData();
		fd_lblEmanager.left = new FormAttachment(0, 122);
		fd_lblEmanager.right = new FormAttachment(100, -223);
		lblEmanager.setLayoutData(fd_lblEmanager);
		toolkit.adapt(lblEmanager, true, true);
		lblEmanager.setText("eManager");
		
		Button btnEventPlanning = new Button(this, SWT.NONE);
		fd_lblEmanager.bottom = new FormAttachment(btnEventPlanning, -24);
		FormData fd_btnEventPlanning = new FormData();
		fd_btnEventPlanning.left = new FormAttachment(0, 122);
		fd_btnEventPlanning.right = new FormAttachment(100, -140);
		btnEventPlanning.setLayoutData(fd_btnEventPlanning);
		toolkit.adapt(btnEventPlanning, true, true);
		btnEventPlanning.setText("Event Planning");
		btnEventPlanning.addSelectionListener(new Event());
		
		Button btnVenueManagement = new Button(this, SWT.NONE);
		fd_btnEventPlanning.bottom = new FormAttachment(100, -92);
		FormData fd_btnVenueManagement = new FormData();
		fd_btnVenueManagement.top = new FormAttachment(btnEventPlanning, 6);
		fd_btnVenueManagement.right = new FormAttachment(btnEventPlanning, 0, SWT.RIGHT);
		fd_btnVenueManagement.left = new FormAttachment(0, 122);
		btnVenueManagement.setLayoutData(fd_btnVenueManagement);
		toolkit.adapt(btnVenueManagement, true, true);
		btnVenueManagement.setText("Venue Management");
		btnVenueManagement.addSelectionListener(new venueManager());

	}
	class Event extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Shell add_item_shell = new Shell(getDisplay(), SWT.NO_TRIM | SWT.ON_TOP);
			add_item_shell.setLocation(400,250);
			SelectEventPage add_new_item_page = new SelectEventPage(add_item_shell,
					SWT.None);
			add_new_item_page.pack();
			add_item_shell.pack();
			add_item_shell.open();
		}
	}
	class venueManager extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Shell shell = new Shell(getDisplay());
			shell.setLocation(200,100);
			Image icon = new Image(getDisplay(), "resources/eManager.png");
			shell.setText("eManager");
			shell.setImage(icon);
			Venuespace ws2 = new Venuespace(shell, SWT.None, MACRO.MANAGER);
				ws2.pack();
				shell.pack();
				shell.open();
			}	
		}
}


