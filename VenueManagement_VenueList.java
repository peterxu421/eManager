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
	private Table venueTable;

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
		composite.setBounds(10, 10, 500, 350);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		venueTable = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		venueTable.setLinesVisible(true);
		venueTable.setHeaderVisible(true);
		venueTable.setBounds(10, 10, 307, 314);
		toolkit.adapt(venueTable);
		toolkit.paintBordersFor(venueTable);
		
		TableColumn tableColumn = new TableColumn(venueTable, SWT.CENTER);
		tableColumn.setWidth(100);
		tableColumn.setText("Name");
		
		TableColumn tableColumn_1 = new TableColumn(venueTable, SWT.CENTER);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("Location");
		
		TableColumn tableColumn_2 = new TableColumn(venueTable, SWT.CENTER);
		tableColumn_2.setWidth(103);
		tableColumn_2.setText("Type");
		
		Button btnAdd = new Button(composite, SWT.NONE);
		btnAdd.setText("Add");
		btnAdd.setBounds(348, 10, 89, 27);
		toolkit.adapt(btnAdd, true, true);
		btnAdd.addSelectionListener(new add());
		
		Button btnDelete = new Button(composite, SWT.NONE);
		btnDelete.setText("Delete");
		btnDelete.setBounds(348, 43, 89, 27);
		toolkit.adapt(btnDelete, true, true);
		btnDelete.addSelectionListener(new delete());
		
		Button btnEdit = new Button(composite, SWT.NONE);
		btnEdit.setText("Edit");
		btnEdit.setBounds(348, 76, 89, 27);
		toolkit.adapt(btnEdit, true, true);
	    btnEdit.addSelectionListener(new edit());

	}
	
	public class add extends SelectionAdapter {

		public void widgetSelected(SelectionEvent e) {
			Shell addVenueShell = new Shell(getDisplay());
			VenueListAddItem addVenuePage = new VenueListAddItem(addVenueShell, SWT.None, venueTable);
			addVenuePage.pack();
			addVenueShell.pack();
			addVenueShell.open();
		}
	}
	
	public class delete extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (venueTable.getColumnCount() != 0) {
				int index = venueTable.getSelectionIndex();
				if (index < 0 || index >= venueTable.getItemCount()) {
					// Do nothing.
				} else {
					/* update the itinerary table */
					venueTable.remove(index);
					/* update the database */
/*					DatabaseReader db = new DatabaseReader();
					db.deleteItinerary(venueList.get(index));*/
				}
			}
		}
	}
	
	public class edit extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			int index = venueTable.getSelectionIndex();
			if(index >=0 && index < venueTable.getItemCount()){
				Shell editVenueShell = new Shell(getDisplay());
				VenueListEditItem editVenuePage = new VenueListEditItem(editVenueShell, SWT.None, venueTable, index);
				editVenuePage.pack();
				editVenueShell.pack();
				editVenueShell.open();
			}
		}
	}
}
