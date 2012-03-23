import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class VenueManagement_VenueList extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table venueTable;
    
	private ArrayList<Venue> venueList;
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
		composite.setBounds(10, 10, 600, 350);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		venueTable = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		venueTable.setLinesVisible(true);
		venueTable.setHeaderVisible(true);
		venueTable.setBounds(10, 10, 408, 314);
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
		
		TableColumn tblclmnCapacity = new TableColumn(venueTable, SWT.CENTER);
		tblclmnCapacity.setWidth(100);
		tblclmnCapacity.setText("Capacity");
		
		Button btnAdd = new Button(composite, SWT.NONE);
		btnAdd.setText("Add");
		btnAdd.setBounds(466, 10, 89, 27);
		toolkit.adapt(btnAdd, true, true);
		btnAdd.addSelectionListener(new add());
		
		Button btnDelete = new Button(composite, SWT.NONE);
		btnDelete.setText("Delete");
		btnDelete.setBounds(466, 43, 89, 27);
		toolkit.adapt(btnDelete, true, true);
		btnDelete.addSelectionListener(new delete());
		
		Button btnEdit = new Button(composite, SWT.NONE);
		btnEdit.setText("Edit");
		btnEdit.setBounds(466, 76, 89, 27);
		toolkit.adapt(btnEdit, true, true);
		
		Button btnView = new Button(composite, SWT.NONE);
		btnView.setText("View Booking Applications");
		btnView.setBounds(437, 109, 153, 27);
		toolkit.adapt(btnView, true, true);
	    btnEdit.addSelectionListener(new edit());
	    btnView.addSelectionListener(new viewBookingInfo());
	    
	    importVenueListData();

	}
	
	public void importVenueListData() {
		DatabaseReader db = new DatabaseReader();
		venueList = db.getVenues();
		/* update the venue list table */
		for (int i=0; i<venueList.size(); i++){
			TableItem temp = new TableItem(venueTable, SWT.None);
			temp.setText(0, venueList.get(i).getName());
			temp.setText(1, venueList.get(i).getLocation());
			temp.setText(2, venueList.get(i).getType());
			temp.setText(3, venueList.get(i).getCapacity()+"");
		}
		
	}
	
	public class add extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			venueTable.deselectAll();
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
					DatabaseReader db = new DatabaseReader();
					db.deleteVenue(db.getVenues().get(index));
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
	
	public class viewBookingInfo extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e){
			int index = venueTable.getSelectionIndex();
			if(index >=0 && index < venueTable.getItemCount()){
				Shell venueWeekViewShell = new Shell(getDisplay());
				VenueManagement_VenueWeekView venueWeekViewPage = new VenueManagement_VenueWeekView(venueWeekViewShell, SWT.None, index);
				venueWeekViewPage.pack();
				venueWeekViewShell.pack();
				venueWeekViewShell.open();
			}
		}
	}
}
