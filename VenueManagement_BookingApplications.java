import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;


public class VenueManagement_BookingApplications extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table table_1;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VenueManagement_BookingApplications(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(10, 10, 849, 350);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		table_1 = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setBounds(10, 10, 734, 324);
		toolkit.adapt(table_1);
		toolkit.paintBordersFor(table_1);
		
		TableColumn tblclmnVenueName = new TableColumn(table_1, SWT.NONE);
		tblclmnVenueName.setWidth(98);
		tblclmnVenueName.setText("Booked Venue");
		
		TableColumn tblclmnName = new TableColumn(table_1, SWT.CENTER);
		tblclmnName.setWidth(80);
		tblclmnName.setText("Name");
		
		TableColumn tblclmnMatricNo = new TableColumn(table_1, SWT.CENTER);
		tblclmnMatricNo.setWidth(100);
		tblclmnMatricNo.setText("Matric No.");
		
		TableColumn tblclmnOrganization = new TableColumn(table_1, SWT.CENTER);
		tblclmnOrganization.setWidth(88);
		tblclmnOrganization.setText("Organization");
		
		TableColumn tblclmnContact = new TableColumn(table_1, SWT.CENTER);
		tblclmnContact.setWidth(100);
		tblclmnContact.setText("Contact No.");
		
		TableColumn tblclmnNewEmail = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewEmail.setWidth(106);
		tblclmnNewEmail.setText("Eamil");
		
		TableColumn tblclmnDateAndTime = new TableColumn(table_1, SWT.CENTER);
		tblclmnDateAndTime.setWidth(181);
		tblclmnDateAndTime.setText("Date and Time");
		
		Button btnAccept = new Button(composite, SWT.NONE);
		btnAccept.setText("Accept");
		btnAccept.setBounds(750, 21, 89, 27);
		toolkit.adapt(btnAccept, true, true);
		
		Button btnReject = new Button(composite, SWT.NONE);
		btnReject.setText("Reject");
		btnReject.setBounds(750, 54, 89, 27);
		toolkit.adapt(btnReject, true, true);

	}
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		VenueManagement_BookingApplications calc = new VenueManagement_BookingApplications(shell,
				SWT.NONE);
		calc.pack();
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
	
}
