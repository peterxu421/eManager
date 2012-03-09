import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.StyledText;


public class VenueBooking_VenueBooking extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text name;
	private Text matricNo;
	private Text organization;
	private List listDateTime;
	private DateTime date;
	private DateTime timeStart;
	private DateTime timeEnd;
	private Text contact;
	private Text email;
	
	private Venue selected;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VenueBooking_VenueBooking(Composite parent, int style, Venue selected) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.selected = selected; 
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(10, 10, 450, 436);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		Label lblName = new Label(composite, SWT.NONE);
		lblName.setBounds(10, 10, 72, 17);
		toolkit.adapt(lblName, true, true);
		lblName.setText("Full Name");
		
		name = new Text(composite, SWT.BORDER);
		name.setBounds(10, 33, 127, 23);
		toolkit.adapt(name, true, true);
		
		Label lblMatricNo = new Label(composite, SWT.NONE);
		lblMatricNo.setBounds(10, 62, 127, 17);
		toolkit.adapt(lblMatricNo, true, true);
		lblMatricNo.setText("Matriculation Card");
		
		matricNo = new Text(composite, SWT.BORDER);
		matricNo.setBounds(10, 85, 128, 23);
		toolkit.adapt(matricNo, true, true);
		
		Label lblOrganization = new Label(composite, SWT.NONE);
		lblOrganization.setBounds(10, 114, 95, 17);
		toolkit.adapt(lblOrganization, true, true);
		lblOrganization.setText("Organization");
		
		organization = new Text(composite, SWT.BORDER);
		organization.setBounds(10, 137, 127, 23);
		toolkit.adapt(organization, true, true);
		
		Label lblContactNumber = new Label(composite, SWT.NONE);
		lblContactNumber.setBounds(10, 166, 95, 15);
		toolkit.adapt(lblContactNumber, true, true);
		lblContactNumber.setText("Contact Number");
		
		contact = new Text(composite, SWT.BORDER);
		contact.setBounds(10, 187, 127, 21);
		toolkit.adapt(contact, true, true);
		
		Label lblEmail = new Label(composite, SWT.NONE);
		lblEmail.setBounds(10, 214, 55, 15);
		toolkit.adapt(lblEmail, true, true);
		lblEmail.setText("Email");
		
		email = new Text(composite, SWT.BORDER);
		email.setBounds(10, 235, 127, 21);
		toolkit.adapt(email, true, true);
		
		Label lblDateTime = new Label(composite, SWT.NONE);
		lblDateTime.setBounds(10, 262, 95, 17);
		toolkit.adapt(lblDateTime, true, true);
		lblDateTime.setText("Date and Time");
		
		date = new DateTime(composite, SWT.BORDER);
		date.setBounds(10, 285, 80, 24);
		toolkit.adapt(date);
		toolkit.paintBordersFor(date);
		
		timeStart = new DateTime(composite, SWT.BORDER | SWT.TIME);
		timeStart.setBounds(107, 285, 86, 24);
		toolkit.adapt(timeStart);
		toolkit.paintBordersFor(timeStart);
		timeStart.setHours(0);
		timeStart.setMinutes(0);
		timeStart.setSeconds(0);
		
		Label lblTo = new Label(composite, SWT.CENTER);
		lblTo.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblTo.setBounds(197, 285, 19, 24);
		toolkit.adapt(lblTo, true, true);
		lblTo.setText("to");
		
		timeEnd = new DateTime(composite, SWT.BORDER | SWT.TIME);
		timeEnd.setBounds(222, 285, 86, 24);
		toolkit.adapt(timeEnd);
		toolkit.paintBordersFor(timeEnd);
		timeEnd.setHours(0);
		timeEnd.setMinutes(0);
		timeEnd.setSeconds(0);
		
		Button btnAddDateTime = new Button(composite, SWT.NONE);
		btnAddDateTime.setBounds(314, 285, 34, 25);
		toolkit.adapt(btnAddDateTime, true, true);
		btnAddDateTime.setText("Add");
		btnAddDateTime.addSelectionListener(new addDateTime());
		
		listDateTime = new List(composite, SWT.BORDER | SWT.V_SCROLL);
		listDateTime.setBounds(10, 315, 232, 68);
		toolkit.adapt(listDateTime, true, true);
		
		Button btnDeleteDateTime = new Button(composite, SWT.NONE);
		btnDeleteDateTime.setBounds(248, 358, 45, 25);
		toolkit.adapt(btnDeleteDateTime, true, true);
		btnDeleteDateTime.setText("Delete");
		btnDeleteDateTime.addSelectionListener(new deleteDateTime());
		
		Button btnSubmit = new Button(composite, SWT.NONE);
		btnSubmit.setBounds(10, 389, 75, 25);
		toolkit.adapt(btnSubmit, true, true);
		btnSubmit.setText("Submit");
		btnSubmit.addSelectionListener(new submit());
		
		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setBounds(118, 389, 75, 25);
		toolkit.adapt(btnCancel, true, true);
		btnCancel.setText("Reset");
		btnCancel.addSelectionListener(new reset());
	}
	
	/* Button selection adapters*/
	public class addDateTime extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e){
			String dateStr;
			String timeStartStr;
			String timeEndStr;
			
			dateStr = String.format("%04d",date.getYear())  + "-"
			        + String.format("%02d", date.getMonth()+1)  + "-" 
					+ String.format("%02d",date.getDay()) ;  // getMonth() + 1 since getMonth() returns 0 to 11
			timeStartStr = String.format("%02d",timeStart.getHours()) + ":"
			        + String.format("%02d",timeStart.getMinutes())  + ":" 
					+ String.format("%02d",timeStart.getSeconds()) ;
			timeEndStr = String.format("%02d",timeEnd.getHours()) + ":"
			        + String.format("%02d",timeEnd.getMinutes())  + ":" 
					+ String.format("%02d",timeEnd.getSeconds()) ;
			
			listDateTime.add(dateStr + "  From " + timeStartStr + " to " + timeEndStr);	
		}
	}
	public class deleteDateTime extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e){
			int index = listDateTime.getSelectionIndex();
			if(index >0 && index <= listDateTime.getItemCount()){
				listDateTime.remove(index);
			}	
		}	
	}
	public class submit extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e){
			String _name = "";
			String _matricNo = "";
			String _organization = "";
			String _contact = "";
			String _email = "";
			ArrayList<String> _listDateTime = null;
			
			if(!name.getText().isEmpty()){
				_name = name.getText();
			}
			if(!matricNo.getText().isEmpty()){
				_matricNo = matricNo.getText();
			}
			if(!organization.getText().isEmpty()){
				_organization = organization.getText();
			}
			if(!contact.getText().isEmpty()){
				_contact = contact.getText();
			}
			if(!email.getText().isEmpty()){
				_email = email.getText();
			}
			if(listDateTime.getItemCount()!=0){
				for (int i=0; i<listDateTime.getItemCount(); i++)
					_listDateTime.set(i, listDateTime.getItem(i));
			}
			
			/* update the table of applications under venue management */
			/* ---can be updated via database later on--- */
			/* ---or can be updated by a reference to the applicationtable---*/

			getParent().dispose();
		}
	}
	public class reset extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e) {
			name.setText("");
			matricNo.setText("");
			organization.setText("");
			contact.setText("");
			email.setText("");
			listDateTime.removeAll();
		}
	}
}
