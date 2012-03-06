import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ItineraryAddItem extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text text_eP_actual_itinerary_itinerary;
	private Text text_eP_actual_itinerary_date;
	private Text text_eP_actual_itinerary_time;
	private Text text_eP_actual_itinerary_done;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ItineraryAddItem(Composite parent, int style, Table table_1) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		setLayout(null);

		Composite comp_eP_actual_itinerary_addPage = new Composite(this, SWT.NONE);
		comp_eP_actual_itinerary_addPage.setBounds(56, 10, 372, 290);
		comp_eP_actual_itinerary_addPage.setLayout(null);
		toolkit.adapt(comp_eP_actual_itinerary_addPage);
		toolkit.paintBordersFor(comp_eP_actual_itinerary_addPage);

		Label lbl_eP_actual_itinerary_itinerary = new Label(comp_eP_actual_itinerary_addPage, SWT.NONE);
		lbl_eP_actual_itinerary_itinerary.setBounds(35, 34, 70, 17);
		toolkit.adapt(lbl_eP_actual_itinerary_itinerary, true, true);
		lbl_eP_actual_itinerary_itinerary.setText("Description");

		Label lbl_eP_actual_itinerary_date = new Label(comp_eP_actual_itinerary_addPage, SWT.NONE);
		lbl_eP_actual_itinerary_date.setBounds(33, 77, 72, 17);
		toolkit.adapt(lbl_eP_actual_itinerary_date, true, true);
		lbl_eP_actual_itinerary_date.setText("Date");

		Label lbl_eP_actual_itinerary_time = new Label(comp_eP_actual_itinerary_addPage, SWT.NONE);
		lbl_eP_actual_itinerary_time.setBounds(35, 117, 27, 17);
		toolkit.adapt(lbl_eP_actual_itinerary_time, true, true);
		lbl_eP_actual_itinerary_time.setText("Time");

		Label lbl_eP_actual_itinerary_done = new Label(comp_eP_actual_itinerary_addPage, SWT.NONE);
		lbl_eP_actual_itinerary_done.setBounds(35, 162, 54, 17);
		toolkit.adapt(lbl_eP_actual_itinerary_done, true, true);
		lbl_eP_actual_itinerary_done.setText("Done");

		text_eP_actual_itinerary_itinerary = new Text(comp_eP_actual_itinerary_addPage, SWT.BORDER);
		text_eP_actual_itinerary_itinerary.setBounds(152, 31, 125, 23);
		toolkit.adapt(text_eP_actual_itinerary_itinerary, true, true);

		text_eP_actual_itinerary_date = new Text(comp_eP_actual_itinerary_addPage, SWT.BORDER);
		text_eP_actual_itinerary_date.setBounds(152, 74, 125, 23);
		toolkit.adapt(text_eP_actual_itinerary_date, true, true);

		text_eP_actual_itinerary_time = new Text(comp_eP_actual_itinerary_addPage, SWT.BORDER);
		text_eP_actual_itinerary_time.setBounds(152, 114, 125, 23);
		toolkit.adapt(text_eP_actual_itinerary_time, true, true);

		text_eP_actual_itinerary_done = new Text(comp_eP_actual_itinerary_addPage, SWT.BORDER);
		text_eP_actual_itinerary_done.setBounds(152, 159, 125, 23);
		toolkit.adapt(text_eP_actual_itinerary_done, true, true);

		Button btn_eP_actual_itinerary_add = new Button(comp_eP_actual_itinerary_addPage, SWT.NONE);
		btn_eP_actual_itinerary_add.addSelectionListener(new ItineraryAddNewItem(table_1));
		btn_eP_actual_itinerary_add.setBounds(87, 219, 66, 27);
		toolkit.adapt(btn_eP_actual_itinerary_add, true, true);
		btn_eP_actual_itinerary_add.setText("Add Item");

		Button btn_eP_actual_itinerary_cancel = new Button(comp_eP_actual_itinerary_addPage, SWT.NONE);
		btn_eP_actual_itinerary_cancel.addSelectionListener(new ItineraryCancel());
		btn_eP_actual_itinerary_cancel.setBounds(211, 219, 66, 27);
		toolkit.adapt(btn_eP_actual_itinerary_cancel, true, true);
		btn_eP_actual_itinerary_cancel.setText("Cancel");

	}

	public class ItineraryAddNewItem extends SelectionAdapter {
		Table table_1;

		public ItineraryAddNewItem(Table table_1) {
			this.table_1 = table_1;
		}

		public void widgetSelected(SelectionEvent e) {
			String[] itineraryArray = new String[4];
			if (!text_eP_actual_itinerary_itinerary.getText().isEmpty()) {
				itineraryArray[0] = text_eP_actual_itinerary_itinerary.getText();
			}
			if (!text_eP_actual_itinerary_date.getText().isEmpty()) {
				itineraryArray[1] = text_eP_actual_itinerary_date.getText();
			}
			if (!text_eP_actual_itinerary_time.getText().isEmpty()) {
				itineraryArray[2] = text_eP_actual_itinerary_time.getText();
			}
			if (!text_eP_actual_itinerary_done.getText().isEmpty()) {
				itineraryArray[3] = text_eP_actual_itinerary_done.getText();
			}
			if (!text_eP_actual_itinerary_itinerary.getText().isEmpty() && !text_eP_actual_itinerary_date.getText().isEmpty()
					&& !text_eP_actual_itinerary_time.getText().isEmpty()
					&& !text_eP_actual_itinerary_done.getText().isEmpty()) {
				TableItem item = new TableItem(table_1, SWT.NULL);
				for (int i = 0; i < 4; i++) {
					item.setText(i, itineraryArray[i]);
				}
				getParent().dispose();
			}
		}
	}
	public class ItineraryCancel extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
