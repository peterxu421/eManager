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


public class VenueManagement_VenueApplicants extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table table_1;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VenueManagement_VenueApplicants(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(10, 10, 659, 377);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		table_1 = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setBounds(10, 10, 487, 324);
		toolkit.adapt(table_1);
		toolkit.paintBordersFor(table_1);
		
		TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
		tableColumn.setWidth(74);
		tableColumn.setText("Name");
		
		TableColumn tblclmnPosition = new TableColumn(table_1, SWT.NONE);
		tblclmnPosition.setWidth(65);
		tblclmnPosition.setText("Position");
		
		TableColumn tblclmnOrganization = new TableColumn(table_1, SWT.NONE);
		tblclmnOrganization.setWidth(100);
		tblclmnOrganization.setText("Organization");
		
		TableColumn tblclmnNewColumn = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn.setWidth(70);
		tblclmnNewColumn.setText("Location");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("Event Name");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_2.setWidth(66);
		tblclmnNewColumn_2.setText("Status");
		
		Button btnAccept = new Button(composite, SWT.NONE);
		btnAccept.setText("Accept");
		btnAccept.setBounds(503, 24, 89, 27);
		toolkit.adapt(btnAccept, true, true);
		
		Button btnReject = new Button(composite, SWT.NONE);
		btnReject.setText("Reject");
		btnReject.setBounds(503, 57, 89, 27);
		toolkit.adapt(btnReject, true, true);

	}
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		VenueManagement_VenueApplicants calc = new VenueManagement_VenueApplicants(shell,
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
