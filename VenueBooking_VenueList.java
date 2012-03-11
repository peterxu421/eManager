import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;


public class VenueBooking_VenueList extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table venueTable;
	private ArrayList<Venue> venueList; // from database

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VenueBooking_VenueList(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(10, 10, 453, 330);
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
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("Type");
		
		Button btnBook = new Button(composite, SWT.NONE);
		btnBook.setBounds(350, 10, 75, 25);
		toolkit.adapt(btnBook, true, true);
		btnBook.setText("Book");
		
		Button btnCheckAvailability = new Button(composite, SWT.NONE);
		btnCheckAvailability.setBounds(336, 54, 106, 25);
		toolkit.adapt(btnCheckAvailability, true, true);
		btnCheckAvailability.setText("Check Availability");
		btnBook.addSelectionListener(new bookVenue());
		btnCheckAvailability.addSelectionListener(new check());
		
		importVenueListData();
	}
	
	public void importVenueListData() {
		DatabaseReader db = new DatabaseReader();
		venueList = db.getVenues();
		/* update the venue table */
		for (int i=0; i<venueList.size(); i++){
			TableItem temp = new TableItem(venueTable, SWT.None);
			temp.setText(0, venueList.get(i).getName());
			temp.setText(1, venueList.get(i).getLocation());
			temp.setText(2, venueList.get(i).getType());
		}
	}
	
	public class bookVenue extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e){
			int index = venueTable.getSelectionIndex();
			if(index >=0 && index < venueTable.getItemCount()){
				Venue selected = venueList.get(index);
				Shell vBookingShell = new Shell(getDisplay());
				VenueBooking_VenueBooking vBookingPage  = new VenueBooking_VenueBooking(vBookingShell, SWT.None, selected);
				vBookingPage.pack();
				vBookingShell.pack();
				vBookingShell.open();
			}
		}
	}
	
	public class check extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e){
			int index = venueTable.getSelectionIndex();
			if(index >=0 && index < venueTable.getItemCount()){
				Shell viewBookingInfoShell = new Shell(getDisplay());
				VenueViewBookingInfo viewBookingInfoPage = new VenueViewBookingInfo(viewBookingInfoShell, SWT.None, index);
				viewBookingInfoPage.pack();
				viewBookingInfoShell.pack();
				viewBookingInfoShell.open();
			}
		}
	}
}

