import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class VenueManagement_VenueList extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VenueManagement_VenueList(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(0, 0, 799, 492);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setBounds(0, 0, 583, 334);
		toolkit.adapt(composite_1);
		toolkit.paintBordersFor(composite_1);
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 10, 307, 314);
		toolkit.adapt(table);
		toolkit.paintBordersFor(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Name");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("Location");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("Type");
		
		Button btnAddVenue = new Button(composite_1, SWT.NONE);
		btnAddVenue.addSelectionListener(new VenueListAddPage());
		btnAddVenue.setBounds(366, 10, 89, 27);
		toolkit.adapt(btnAddVenue, true, true);
		btnAddVenue.setText("Add Venue");
		
		Button btnDeleteVenue = new Button(composite_1, SWT.NONE);
		btnDeleteVenue.addSelectionListener(new VenueListDeleteItem(table));
		btnDeleteVenue.setBounds(366, 48, 89, 27);
		toolkit.adapt(btnDeleteVenue, true, true);
		btnDeleteVenue.setText("Delete Venue");
		
		Button btnEditVenue = new Button(composite_1, SWT.NONE);
		btnEditVenue.addSelectionListener(new VenueListEditPage());
		btnEditVenue.setBounds(366, 93, 89, 27);
		toolkit.adapt(btnEditVenue, true, true);
		btnEditVenue.setText("Edit Venue");

	}
	
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		VenueManagement_VenueList calc = new VenueManagement_VenueList(shell,
				SWT.NONE);
		calc.pack();
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
	public class VenueListAddPage extends SelectionAdapter {

		public void widgetSelected(SelectionEvent e) {
			Shell venueListAddItemPage = new Shell(getDisplay());
			VenueListAddItem venueListAddItem = new VenueListAddItem (venueListAddItemPage, SWT.None, table);
			venueListAddItem.pack();
			venueListAddItemPage.pack();
			venueListAddItemPage.open();
		}
	}
	public class VenueListDeleteItem extends SelectionAdapter {
		Table table;
		public VenueListDeleteItem(Table table){
			this.table=table;
		}
		public void widgetSelected(SelectionEvent e) {
			if (table.getColumnCount() != 0) {
				int index = table.getSelectionIndex();
				if (index < 0 || index >= table.getItemCount()) {
					// Do nothing.
				} else {
					table.remove(index);
				}
			}
		}
	}

	public class VenueListEditPage extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			int index = table.getSelectionIndex();
			if (index < table.getItemCount() && index >= 0) {
				Shell venueListEditMemberPage = new Shell(getDisplay());
				VenueListEditItem venueListEditMember = new VenueListEditItem(
						venueListEditMemberPage, SWT.None, table, index);
				venueListEditMember.pack();
				venueListEditMemberPage.pack();
				venueListEditMemberPage.open();
			}
		}
	}
}
