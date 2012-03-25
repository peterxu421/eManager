import java.util.ArrayList;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;


public class VenueBooking_CheckMyApplication extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Composite composite;
	private DatabaseReader db = new DatabaseReader();
	private Text matricNoInput;
	
	private ArrayList<VenueBookingApplication> bookingApplicationList;
	private Table applicationTable;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VenueBooking_CheckMyApplication(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		composite = new Composite(this, SWT.NONE);
		composite.setBounds(0, 0, 661, 387);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		

		
		Label lblEnterYourMatriculation = new Label(composite, SWT.NONE);
		lblEnterYourMatriculation.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblEnterYourMatriculation.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblEnterYourMatriculation.setBounds(0, 0, 189, 25);
		toolkit.adapt(lblEnterYourMatriculation, true, true);
		lblEnterYourMatriculation.setText("Enter your Matriculation No");
		
		matricNoInput = new Text(composite, SWT.BORDER);
		matricNoInput.setBounds(194, 0, 134, 21);
		toolkit.adapt(matricNoInput, true, true);
		
		Button btnCheck = new Button(composite, SWT.NONE);
		btnCheck.setBounds(357, -1, 75, 25);
		toolkit.adapt(btnCheck, true, true);
		btnCheck.setText("Check");
		
		applicationTable = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL);
		applicationTable.setBounds(0, 39, 586, 290);
		toolkit.adapt(applicationTable);
		toolkit.paintBordersFor(applicationTable);
		applicationTable.setHeaderVisible(true);
		applicationTable.setLinesVisible(true);
		
		TableColumn tblclmnName = new TableColumn(applicationTable, SWT.CENTER);
		tblclmnName.setWidth(100);
		tblclmnName.setText("Name");
		
		TableColumn tblclmnOrganization = new TableColumn(applicationTable, SWT.CENTER);
		tblclmnOrganization.setWidth(100);
		tblclmnOrganization.setText("Organization");
		
		TableColumn tblclmnDateAndTime = new TableColumn(applicationTable, SWT.CENTER);
		tblclmnDateAndTime.setWidth(281);
		tblclmnDateAndTime.setText("Date and Time");
		
		TableColumn tblclmnStatus = new TableColumn(applicationTable, SWT.CENTER);
		tblclmnStatus.setWidth(100);
		tblclmnStatus.setText("Status");
		btnCheck.addSelectionListener(new check());
		
		Button btnWithdrawAll = new Button(composite, SWT.NONE);
		btnWithdrawAll.setBounds(139, 349, 121, 25);
		toolkit.adapt(btnWithdrawAll, true, true);
		btnWithdrawAll.setText("Withdraw all");
		btnWithdrawAll.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				MessageBox warningPage  = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.CANCEL | SWT.ICON_WARNING );
				warningPage.setText("Warning!");
				warningPage.setMessage("Are you sure you want to withdraw from all your applications?");
				int choice = warningPage.open(); // indicates the user's choice
				switch(choice){
				case SWT.OK:
					/* update the database */
					for(int i=0; i<bookingApplicationList.size(); i++){
						db.deleteVenueBookingInfo(bookingApplicationList.get(i));
					}
					/* update the table */
					applicationTable.removeAll();
					break;
				case SWT.CANCEL:
					break;
				}
			}
		});
		
		Button btnWithdraw = new Button(composite, SWT.NONE);
		btnWithdraw.setBounds(0, 349, 121, 25);
		toolkit.adapt(btnWithdraw, true, true);
		btnWithdraw.setText("Withdraw");
		btnWithdraw.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				int index = applicationTable.getSelectionIndex();
				if(index >= 0 && index < applicationTable.getItemCount()){
					MessageBox warningPage  = new MessageBox(getDisplay().getActiveShell(), SWT.YES | SWT.CANCEL | SWT.ICON_WARNING );
					warningPage.setText("Warning!");
					warningPage.setMessage("Are you sure you want to withdraw this application?");
					int choice = warningPage.open(); // indicates the user's choice
					switch(choice){
					case SWT.YES:
						/* update the database */		
						db.deleteVenueBookingInfo(bookingApplicationList.get(index));
						/* update the application table */
						applicationTable.remove(index);
						break;
					case SWT.CANCEL:
						break;
					}
				}
				else {
					MessageBox noSelectionWarning = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.ICON_WARNING);
					noSelectionWarning.setText("Warning!");
					noSelectionWarning.setMessage("Please select an application entry to withdraw!");
					noSelectionWarning.open();
				}
			}
	
				});


	}
	
	public class check extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			String matricNo = matricNoInput.getText();
			VenueApplicant venueApplicant = db.getVenueApplicantByMatricNo(matricNo);
			if(matricNo.isEmpty()){
				MessageBox noInputWarning = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.ICON_WARNING);
				noInputWarning.setText("Warning!");
				noInputWarning.setMessage("Please enter your matriculation number!");
				noInputWarning.open();
			}
			else if(venueApplicant == null){
				MessageBox noMatchWarning = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.ICON_WARNING);
				noMatchWarning.setText("Warning!");
				noMatchWarning.setMessage("No booking applications found!");
				noMatchWarning.open();
			}
			else{
				fillApplicationList(venueApplicant);
			}
		}
	}
	
	public void fillApplicationList(VenueApplicant venueApplicant){
		/* show the booking info from database */
	    bookingApplicationList = db.getVenueBookingInfo(venueApplicant);
		for (int i=0; i<bookingApplicationList.size(); i++){
			String name = bookingApplicationList.get(i).getApplicant().getName();
		    String organization = bookingApplicationList.get(i).getApplicant().getOrganization();
		    BookedDateTime dateTime = bookingApplicationList.get(i).getDateTime();
		    int statusIndex = bookingApplicationList.get(i).getStatus();
		    String status = "";
		    if(statusIndex == MACRO.APPROVED){
		    	status = "Approved";
		    }
		    else if(statusIndex == MACRO.PENDING){
		    	status = "Pending";
		    }
		    else if(statusIndex == MACRO.REJECTED){
		    	status = "Rejected";
		    }
		    
		    TableItem item = new TableItem(applicationTable, SWT.NULL);
		    item.setText(0, name);
		    item.setText(1, organization);
		    item.setText(2, dateTime.toString());
		    item.setText(3, status);
		}
	}
}