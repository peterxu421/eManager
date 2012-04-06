
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;


public class VenueBooking_VenueList extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table venueTable;
	private Combo comboLocation;
	
	protected String[] venueLocationArray = { "Arts and Social Sciences",
			"Business", "Computing", "Dentistry", "Design and Environment",
			"Engineering", "Law", "Medicine", "Music", "Science",
			"Central Library", "CFA", "PGP", "YIH", "SRC", "UCC", "Others" };
	private ArrayList<Venue> venuesAtSameLocation;

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
		composite.setBounds(0, 0, 800, 400);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblNewLabel.setBounds(0, 0, 117, 30);
		toolkit.adapt(lblNewLabel, true, true);
		lblNewLabel.setText("Choose location");
		
		comboLocation = new Combo(composite, SWT.READ_ONLY);
		comboLocation.setBounds(123, 0, 100, 30);
		toolkit.adapt(comboLocation);
		toolkit.paintBordersFor(comboLocation);
		comboLocation.setItems(venueLocationArray);
		comboLocation.addSelectionListener(new chooseLocation());
		
		venueTable = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		venueTable.setLinesVisible(true);
		venueTable.setHeaderVisible(true);
		venueTable.setBounds(0, 40, 600, 360);
		toolkit.adapt(venueTable);
		toolkit.paintBordersFor(venueTable);
		
		TableColumn tableColumn = new TableColumn(venueTable, SWT.CENTER);
		tableColumn.setWidth(150);
		tableColumn.setText("Name");
		
		TableColumn tableColumn_1 = new TableColumn(venueTable, SWT.CENTER);
		tableColumn_1.setWidth(150);
		tableColumn_1.setText("Location");
		
		TableColumn tableColumn_2 = new TableColumn(venueTable, SWT.CENTER);
		tableColumn_2.setWidth(150);
		tableColumn_2.setText("Type");
		
		TableColumn tblclmnCapacity = new TableColumn(venueTable, SWT.CENTER);
		tblclmnCapacity.setWidth(150);
		tblclmnCapacity.setText("Capacity");
		
		Button btnCheckAvailability = new Button(composite, SWT.NONE);
		btnCheckAvailability.setBounds(624, 39, 150, 30);
		toolkit.adapt(btnCheckAvailability, true, true);
		btnCheckAvailability.setText("Check Availability");
		btnCheckAvailability.addSelectionListener(new check());
	
	}
	
	public class chooseLocation extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			int index = comboLocation.getSelectionIndex();
			String location = comboLocation.getItem(index);
			System.out.println(location);
			DatabaseReader db = new DatabaseReader();
			venuesAtSameLocation = db.getVenuesByLocation(location);
			venueTable.removeAll(); // clear the table to load new information
			importVenuesAtSameLocation();
		}
	}
	
	public void importVenuesAtSameLocation() {
		/* update the venue table */
		for (int i=0; i<venuesAtSameLocation.size(); i++){
			TableItem temp = new TableItem(venueTable, SWT.None);
			temp.setText(0, venuesAtSameLocation.get(i).getName());
			temp.setText(1, venuesAtSameLocation.get(i).getLocation());
			temp.setText(2, venuesAtSameLocation.get(i).getType());
			temp.setText(3, venuesAtSameLocation.get(i).getCapacity()+"");
		}
	}
	
	public class check extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e){
			int index = venueTable.getSelectionIndex();
			if(index >=0 && index < venueTable.getItemCount()){
				Shell venueWeekViewShell = new Shell(getDisplay(), SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
				Image icon = new Image(getDisplay(), "resources/eManager.png");
				venueWeekViewShell.setText("eManager");
				venueWeekViewShell.setImage(icon);
				
				Venue selectedVenue = venuesAtSameLocation.get(index);
				VenueBooking_WeekView venueWeekViewPage = new VenueBooking_WeekView(venueWeekViewShell, SWT.None, selectedVenue);
				venueWeekViewPage.pack();
				venueWeekViewShell.pack();
				venueWeekViewShell.open();
			}
		}
	}
}

