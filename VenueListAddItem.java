import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class VenueListAddItem extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text text;
	private Text text_1;
	private Text text_2;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VenueListAddItem(Composite parent, int style, Table table) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(null);
		composite.setBounds(10, 10, 397, 313);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("Name");
		label.setBounds(35, 34, 40, 17);
		toolkit.adapt(label, true, true);
		
		Label lblLocation = new Label(composite, SWT.NONE);
		lblLocation.setText("Location");
		lblLocation.setBounds(33, 77, 72, 17);
		toolkit.adapt(lblLocation, true, true);
		
		Label lblType = new Label(composite, SWT.NONE);
		lblType.setText("Type");
		lblType.setBounds(35, 117, 54, 17);
		toolkit.adapt(lblType, true, true);
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(152, 31, 125, 23);
		toolkit.adapt(text, true, true);
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(152, 74, 125, 23);
		toolkit.adapt(text_1, true, true);
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setBounds(152, 114, 125, 23);
		toolkit.adapt(text_2, true, true);
		
		Button btnAddVenue = new Button(composite, SWT.NONE);
		btnAddVenue.addSelectionListener(new VenueListAddNewMember(table));
		btnAddVenue.setText("Add Venue");
		btnAddVenue.setBounds(70, 189, 90, 27);
		toolkit.adapt(btnAddVenue, true, true);
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new VenueListMemberCancel());
		button_1.setText("Cancel");
		button_1.setBounds(211, 189, 66, 27);
		toolkit.adapt(button_1, true, true);

	}

	public class VenueListAddNewMember extends SelectionAdapter {
		Table table;

		public VenueListAddNewMember(Table table) {
			this.table = table;
		}

		public void widgetSelected(SelectionEvent e) {
			int size=3;
			String[] taskAssignArray = new String[size];
			if (!text.getText().isEmpty()) {
				taskAssignArray[0] = text.getText();
			}
			if (!text_1.getText().isEmpty()) {
				taskAssignArray[1] = text_1.getText();
			}
			if (!text_2.getText().isEmpty()) {
				taskAssignArray[2] = text_2.getText();
			}
			
			if (!text.getText().isEmpty() && !text_1.getText().isEmpty()
					&& !text_2.getText().isEmpty()) {
				TableItem item = new TableItem(table, SWT.NULL);
				for (int i = 0; i < size; i++) {
					item.setText(i, taskAssignArray[i]);
				}
				getParent().dispose();
			}
		}
	}

	public class VenueListMemberCancel extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}

}
