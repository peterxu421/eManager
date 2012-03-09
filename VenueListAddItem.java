import java.util.ArrayList;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class VenueListAddItem extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text name;
	private Text location;
	private Text type;
	
	private Table table;
	//private ArrayList<Venue> venueList; //  from database 

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public VenueListAddItem(Composite parent, int style, Table table /*ArrayList<Venue> venueList*/ ) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.table = table;
		//this.venueList = venueList;

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(null);
		composite.setBounds(10, 10, 372, 290);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		Label lblEditVenue = new Label(composite, SWT.NONE);
		lblEditVenue.setBounds(152, 27, 75, 15);
		toolkit.adapt(lblEditVenue, true, true);
		lblEditVenue.setText("Edit Venue");

		Label lblName = new Label(composite, SWT.NONE);
		lblName.setAlignment(SWT.RIGHT);
		lblName.setText("Name");
		lblName.setBounds(93, 74, 42, 17);
		toolkit.adapt(lblName, true, true);

		Label lblLocation = new Label(composite, SWT.NONE);
		lblLocation.setAlignment(SWT.RIGHT);
		lblLocation.setText("Location");
		lblLocation.setBounds(63, 117, 72, 17);
		toolkit.adapt(lblLocation, true, true);

		Label lblType = new Label(composite, SWT.NONE);
		lblType.setAlignment(SWT.RIGHT);
		lblType.setText("Type");
		lblType.setBounds(108, 157, 27, 17);
		toolkit.adapt(lblType, true, true);

		name = new Text(composite, SWT.BORDER);
		name.setBounds(152, 71, 125, 23);
		toolkit.adapt(name, true, true);

		location = new Text(composite, SWT.BORDER);
		location.setToolTipText("");
		location.setBounds(152, 114, 125, 23);
		toolkit.adapt(location, true, true);

		type = new Text(composite, SWT.BORDER);
		type.setBounds(152, 154, 125, 23);
		toolkit.adapt(type, true, true);
		
		Button btnEdit = new Button(composite, SWT.NONE);
		btnEdit.setBounds(103, 209, 75, 25);
		toolkit.adapt(btnEdit, true, true);
		btnEdit.setText("Add");
		btnEdit.addSelectionListener(new Add());
		
		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setBounds(202, 209, 75, 25);
		toolkit.adapt(btnCancel, true, true);
		btnCancel.setText("Cancel");
		btnCancel.addSelectionListener(new cancel());
	}
	
	public class Add extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e){
			String[] _venueList = new String[3];
			
			if (!name.getText().isEmpty()){
				_venueList[0] = name.getText();
			}
			if (!location.getText().isEmpty()){
				_venueList[1] = location.getText();
			}
			if (!type.getText().isEmpty()){
				_venueList[2] = type.getText();
			}
			
			if (!name.getText().isEmpty() &&
				   !location.getText().isEmpty() &&
				       !type.getText().isEmpty()){
				/* update the venue table */ 
				TableItem temp = new TableItem(table, SWT.None);
				for (int i=0; i<3; i++){
					temp.setText(i, _venueList[i]);
				}
				
				/* update the database */
				DatabaseReader db = new DatabaseReader();
				Venue newVenue = new Venue(_venueList[0], _venueList[1], _venueList[2]);
				db.insertVenue(newVenue);
				
				getParent().dispose();
			}
		}
	}
	
	public class cancel extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}




