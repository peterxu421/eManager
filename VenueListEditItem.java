import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class VenueListEditItem extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text text;
	private Text text_1;
	private Text text_2;

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

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(null);
		composite.setBounds(10, 10, 372, 290);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);

		Label lblName = new Label(composite, SWT.NONE);
		lblName.setText("Name");
		lblName.setBounds(35, 34, 42, 17);
		toolkit.adapt(lblName, true, true);

		Label lblLocation = new Label(composite, SWT.NONE);
		lblLocation.setText("Location");
		lblLocation.setBounds(33, 77, 72, 17);
		toolkit.adapt(lblLocation, true, true);

		Label lblType = new Label(composite, SWT.NONE);
		lblType.setText("Type");
		lblType.setBounds(35, 117, 27, 17);
		toolkit.adapt(lblType, true, true);

		text = new Text(composite, SWT.BORDER);
		text.setBounds(152, 31, 125, 23);
		toolkit.adapt(text, true, true);
		text.setText(table.getItem(index).getText(0));

		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(152, 74, 125, 23);
		toolkit.adapt(text_1, true, true);
		text_1.setText(table.getItem(index).getText(1));

		text_2 = new Text(composite, SWT.BORDER);
		text_2.setBounds(152, 114, 125, 23);
		toolkit.adapt(text_2, true, true);
		text_2.setText(table.getItem(index).getText(2));

		Button btnEditVenue = new Button(composite, SWT.NONE);
		btnEditVenue
				.addSelectionListener(new VenueListEditOldItem(table, index));
		btnEditVenue.setText("Edit Venue");
		btnEditVenue.setBounds(83, 171, 80, 27);
		toolkit.adapt(btnEditVenue, true, true);

		Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new VenueListCancel());
		button_1.setText("Cancel");
		button_1.setBounds(211, 171, 66, 27);
		toolkit.adapt(button_1, true, true);

	}

	public class VenueListEditOldItem extends SelectionAdapter {
		Table table;
		int index;

		public VenueListEditOldItem(Table table, int index) {
			this.table = table;
			this.index = index;
		}

		public void widgetSelected(SelectionEvent e) {
			String[] taskAssignArray = new String[3];
			if (!text.getText().isEmpty()) {
				taskAssignArray[0] = text.getText();
				table.getItem(index).setText(0, taskAssignArray[0]);
			}
			if (!text_1.getText().isEmpty()) {
				taskAssignArray[1] = text_1.getText();
				table.getItem(index).setText(1, taskAssignArray[1]);
			}
			if (!text_2.getText().isEmpty()) {
				taskAssignArray[2] = text_2.getText();
				table.getItem(index).setText(2, taskAssignArray[2]);
			}
			if (!text.getText().isEmpty() && !text_1.getText().isEmpty()
					&& !text_2.getText().isEmpty()) {
				getParent().dispose();
			}
		}
	}

	public class VenueListCancel extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
