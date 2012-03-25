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

public class VenueListEditItem extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text name;
	private Text location;
	private Text type;
	private Text capacity;
	
	private Table table;
	private int index;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public VenueListEditItem(Composite parent, int style, Table table, int index) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.table = table;
		this.index = index;

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
		
		Label lblCapacity = new Label(composite, SWT.NONE);
		lblCapacity.setAlignment(SWT.RIGHT);
		lblCapacity.setBounds(80, 199, 55, 15);
		toolkit.adapt(lblCapacity, true, true);
		lblCapacity.setText("Capacity");

		name = new Text(composite, SWT.BORDER);
		name.setBounds(152, 71, 125, 23);
		toolkit.adapt(name, true, true);
		if (index >= 0 && index <table.getItemCount()){
			name.setText(table.getItem(index).getText(0));
		}

		location = new Text(composite, SWT.BORDER);
		location.setToolTipText("");
		location.setBounds(152, 114, 125, 23);
		toolkit.adapt(location, true, true);
		if (index >= 0 && index <table.getItemCount()){
			location.setText(table.getItem(index).getText(1));
		}

		type = new Text(composite, SWT.BORDER);
		type.setBounds(152, 154, 125, 23);
		toolkit.adapt(type, true, true);
		if (index >= 0 && index <table.getItemCount()){
			type.setText(table.getItem(index).getText(2));
		}
		
		capacity = new Text(composite, SWT.BORDER);
		capacity.setBounds(152, 199, 125, 21);
		toolkit.adapt(capacity, true, true);
		if (index >= 0 && index <table.getItemCount()){
			capacity.setText(table.getItem(index).getText(3));
		}
		
		Button btnEdit = new Button(composite, SWT.NONE);
		btnEdit.setBounds(108, 239, 75, 25);
		toolkit.adapt(btnEdit, true, true);
		btnEdit.setText("Edit");
		btnEdit.addSelectionListener(new edit());
		
		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setBounds(202, 239, 75, 25);
		toolkit.adapt(btnCancel, true, true);
		btnCancel.setText("Cancel");
		btnCancel.addSelectionListener(new cancel());
	}
	
	public class edit extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e){
			String[] _venueList = new String[4];
			
			if (!name.getText().isEmpty()){
				_venueList[0] = name.getText();
			}
			if (!location.getText().isEmpty()){
				_venueList[1] = location.getText();
			}
			if (!type.getText().isEmpty()){
				_venueList[2] = type.getText();
			}
			if(!capacity.getText().isEmpty()){
				_venueList[3] = capacity.getText();
			}
			
			
			if (!name.getText().isEmpty() &&
				   !location.getText().isEmpty() &&
				       !type.getText().isEmpty() &&
				       !capacity.getText().isEmpty()){
				/* update the venue table */ 
				TableItem temp = table.getItem(index);
				for (int i=0; i<4; i++){
					temp.setText(i, _venueList[i]);
				}
				
				/* update the database */
				DatabaseReader db = new DatabaseReader();
				Venue toBeEdited = db.getVenues().get(index);
				toBeEdited.setName(_venueList[0]);
				toBeEdited.setLocation(_venueList[1]);
				toBeEdited.setType(_venueList[2]);
				toBeEdited.setCapacity(Integer.parseInt(_venueList[3]));
				db.updateVenue(toBeEdited);
				
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




