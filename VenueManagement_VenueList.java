
import java.util.ArrayList;
import java.util.Calendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;


public class VenueManagement_VenueList extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table venueTable;
	private String[] venueInfoArray = { "Name", "Location", "Type", "Capacity" };
    private int[] venueInfoSignatureArray = { MACRO.TEXT, MACRO.VENUELOCATION, MACRO.VENUETYPE, MACRO.INT};
    
    private Date today = new Date();
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
		composite.setBounds(0, 0, 800, 350);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		venueTable = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		venueTable.setLinesVisible(true);
		venueTable.setHeaderVisible(true);
		venueTable.setBounds(0, 0, 600, 350);
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
		
		Button btnAdd = new Button(composite, SWT.NONE);
		btnAdd.setText("Add");
		btnAdd.setBounds(655, 0, 90, 30);
		toolkit.adapt(btnAdd, true, true);
		btnAdd.addSelectionListener(new add());
		
		Button btnDelete = new Button(composite, SWT.NONE);
		btnDelete.setText("Delete");
		btnDelete.setBounds(655, 40, 90, 30);
		toolkit.adapt(btnDelete, true, true);
		btnDelete.addSelectionListener(new delete());
		
		Button btnEdit = new Button(composite, SWT.NONE);
		btnEdit.setText("Edit");
		btnEdit.setBounds(655, 80, 90, 30);
		toolkit.adapt(btnEdit, true, true);
		btnEdit.addSelectionListener(new edit());
		
		Button btnView = new Button(composite, SWT.NONE);
		btnView.setText("View Booking Applications");
		btnView.setBounds(625, 120, 150, 30);
		toolkit.adapt(btnView, true, true);
	    btnView.addSelectionListener(new viewBookingInfo());
	    
		Calendar calendar = Calendar.getInstance(); // today
		today.setYear(calendar.get(Calendar.YEAR));
		today.setMonth(calendar.get(Calendar.MONTH)+1);
		today.setDay(calendar.get(Calendar.DATE));
	    
	    importVenueListData();

	}
	
	public void importVenueListData() {
		DatabaseReader db = new DatabaseReader();
		ArrayList<Venue> venueList = db.getVenues();
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
			Image icon = new Image(getDisplay(), "resources/eManager.png");
			addVenueShell.setText("eManager - Add a venue");
			addVenueShell.setImage(icon);
			AbstractAdd addVenuePage = new AbstractAdd(addVenueShell, SWT.None, venueInfoArray, venueInfoSignatureArray, venueTable){
				// submit info
				public void onSubmit() {
					// update database
					String[] venueInfoList = getStringList();
					Venue venue = new Venue(venueInfoList[0], venueInfoList[1], venueInfoList[2], Integer.parseInt(venueInfoList[3]));
					db.insertVenue(venue);
					// update the venue list table
					TableItem temp = new TableItem(venueTable, SWT.None);
					for (int i=0; i<venueInfoArray.length; i++){
						temp.setText(i, venueInfoList[i]);
					}
				}
			};
			addVenuePage.pack();
			addVenueShell.pack();
			addVenueShell.open();
		}
	}
	
	public class delete extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (venueTable.getItemCount() != 0) {
				int index = venueTable.getSelectionIndex();
				if (index >= 0 && index < venueTable.getItemCount()) {
					DatabaseReader db = new DatabaseReader();
					ArrayList<Venue> venueList = db.getVenues();
					Venue venueToBeDeleted = venueList.get(index);
					ArrayList<VenueBookingApplication> vBookingAppList = db.getVenueBookingInfo(venueToBeDeleted);
					if(!vBookingAppList.isEmpty()){
						boolean deletable = true;
						for (int i=0; i<vBookingAppList.size(); i++){
							if(vBookingAppList.get(i).getDateTime().getDate().isNotEarlierThan(today) &&
									vBookingAppList.get(i).getStatus() != MACRO.REJECTED){
								deletable = false;
								break;
							}
						}
						if(deletable == false){
							MessageBox warningPage  = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.ICON_WARNING );
							warningPage.setText("Warning!");
							warningPage.setMessage("There are still valid or unprocessed applications for this venue!");
							warningPage.open(); 
						}
						else {
							MessageBox warningPage  = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.ICON_WARNING );
							warningPage.setText("Warning!");
							warningPage.setMessage("Please clear its applicatin log before removing a venue!");
							warningPage.open(); 
						}
					}
					
					else{
						/* update the venue list table */
						venueTable.remove(index);
						/* update the database */
						db.deleteVenue(db.getVenues().get(index));
						
					}
				}
			}
		}
	}
	
	public class edit extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			final int index = venueTable.getSelectionIndex();
			if(index >=0 && index < venueTable.getItemCount()){
				Shell editVenueShell = new Shell(getDisplay());
				Image icon = new Image(getDisplay(), "resources/eManager.png");
				editVenueShell.setText("eManager - Edit a venue");
				editVenueShell.setImage(icon);
				AbstractEdit editVenuePage = new AbstractEdit(editVenueShell, SWT.None, 
						venueInfoArray, venueInfoSignatureArray){
					// get data from table selection
					public void onLoad(){
						for (int i=0; i<venueInfoArray.length; i++){
							setData(venueTable.getItem(index).getText(i), 
									venueInfoSignatureArray[i], i);
						}
					}
					// submit modified info
					public void onSubmit(){
						String[] venueInfoList = getStringList();
						// find the venue to be edited
						Venue venue = db.getVenues().get(index);
						venue.setName(venueInfoList[0]);
						venue.setLocation(venueInfoList[1]);
						venue.setType(venueInfoList[2]);
						venue.setCapacity(Integer.parseInt(venueInfoList[3]));
						// update the database
						db.updateVenue(venue);
						// update the venue list table
						TableItem temp = new TableItem(venueTable, SWT.None);
						for (int i=0; i<venueInfoArray.length; i++){
							temp.setText(i, venueInfoList[i]);
						}	
					}
				};
				
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
				Image icon = new Image(getDisplay(), "resources/eManager.png");
				venueWeekViewShell.setText("eManager");
				venueWeekViewShell.setImage(icon);
				VenueManagement_VenueWeekView venueWeekViewPage = new VenueManagement_VenueWeekView(venueWeekViewShell, SWT.None, index);
				venueWeekViewPage.pack();
				venueWeekViewShell.pack();
				venueWeekViewShell.open();
			}
		}
	}
}
