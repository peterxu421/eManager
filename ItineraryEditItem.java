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


public class ItineraryEditItem extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text text_eP_actual_itinerary_itinerary;
	private Text text_eP_actual_itinerary_date;
	private Text text_eP_actual_itinerary_time;
	private Text text_eP_actual_itinerary_done;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ItineraryEditItem(Composite parent, int style, Table table, int index) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite comp_eP_actual_itinerary_editPage = new Composite(this, SWT.NONE);
		comp_eP_actual_itinerary_editPage.setLayout(null);
		comp_eP_actual_itinerary_editPage.setBounds(34, 10, 372, 290);
		toolkit.adapt(comp_eP_actual_itinerary_editPage);
		toolkit.paintBordersFor(comp_eP_actual_itinerary_editPage);
		
		Label lbl_eP_actual_itinerary_itinerary = new Label(comp_eP_actual_itinerary_editPage, SWT.NONE);
		lbl_eP_actual_itinerary_itinerary.setText("Description");
		lbl_eP_actual_itinerary_itinerary.setBounds(35, 34, 70, 17);
		toolkit.adapt(lbl_eP_actual_itinerary_itinerary, true, true);
		
		Label lbl_eP_actual_itinerary_date = new Label(comp_eP_actual_itinerary_editPage, SWT.NONE);
		lbl_eP_actual_itinerary_date.setText("Date");
		lbl_eP_actual_itinerary_date.setBounds(33, 77, 72, 17);
		toolkit.adapt(lbl_eP_actual_itinerary_date, true, true);
		
		Label lbl_eP_actual_itinerary_time = new Label(comp_eP_actual_itinerary_editPage, SWT.NONE);
		lbl_eP_actual_itinerary_time.setText("Time");
		lbl_eP_actual_itinerary_time.setBounds(35, 117, 27, 17);
		toolkit.adapt(lbl_eP_actual_itinerary_time, true, true);
		
		Label lbl_eP_actual_itinerary_done = new Label(comp_eP_actual_itinerary_editPage, SWT.NONE);
		lbl_eP_actual_itinerary_done.setText("Done");
		lbl_eP_actual_itinerary_done.setBounds(35, 162, 54, 17);
		toolkit.adapt(lbl_eP_actual_itinerary_done, true, true);
		
		text_eP_actual_itinerary_itinerary = new Text(comp_eP_actual_itinerary_editPage, SWT.BORDER);
		text_eP_actual_itinerary_itinerary.setBounds(152, 31, 125, 23);
		toolkit.adapt(text_eP_actual_itinerary_itinerary, true, true);
		text_eP_actual_itinerary_itinerary.setText(table.getItem(index).getText(0));
		
		text_eP_actual_itinerary_date = new Text(comp_eP_actual_itinerary_editPage, SWT.BORDER);
		text_eP_actual_itinerary_date.setBounds(152, 74, 125, 23);
		toolkit.adapt(text_eP_actual_itinerary_date, true, true);
		text_eP_actual_itinerary_date.setText(table.getItem(index).getText(1));
		
		text_eP_actual_itinerary_time = new Text(comp_eP_actual_itinerary_editPage, SWT.BORDER);
		text_eP_actual_itinerary_time.setBounds(152, 114, 125, 23);
		toolkit.adapt(text_eP_actual_itinerary_time, true, true);
		text_eP_actual_itinerary_time.setText(table.getItem(index).getText(2));
		
		text_eP_actual_itinerary_done = new Text(comp_eP_actual_itinerary_editPage, SWT.BORDER);
		text_eP_actual_itinerary_done.setBounds(152, 159, 125, 23);
		toolkit.adapt(text_eP_actual_itinerary_done, true, true);
		text_eP_actual_itinerary_done.setText(table.getItem(index).getText(3));
		
		Button btn_eP_actual_itinerary_edit = new Button(comp_eP_actual_itinerary_editPage, SWT.NONE);
		btn_eP_actual_itinerary_edit.addSelectionListener(new ItineraryEditOldItem(table, index));
		btn_eP_actual_itinerary_edit.setText("Edit Item");
		btn_eP_actual_itinerary_edit.setBounds(87, 219, 66, 27);
		toolkit.adapt(btn_eP_actual_itinerary_edit, true, true);
		
		Button btn_eP_actual_itinerary_cancel = new Button(comp_eP_actual_itinerary_editPage, SWT.NONE);
		btn_eP_actual_itinerary_cancel.addSelectionListener(new ItineraryCancel2());
		btn_eP_actual_itinerary_cancel.setText("Cancel");
		btn_eP_actual_itinerary_cancel.setBounds(211, 219, 66, 27);
		toolkit.adapt(btn_eP_actual_itinerary_cancel, true, true);

	}
	
	public class ItineraryEditOldItem extends SelectionAdapter{
		Table table;
		int index;

		public ItineraryEditOldItem(Table table, int index) {
			this.table = table;
			this.index=index;
		}

		public void widgetSelected(SelectionEvent e) {
			String[] itineraryArray = new String[4];
			if (!text_eP_actual_itinerary_itinerary.getText().isEmpty()) {
				itineraryArray[0] = text_eP_actual_itinerary_itinerary.getText();
				table.getItem(index).setText(0, itineraryArray[0]);
			}
			if (!text_eP_actual_itinerary_date.getText().isEmpty()) {
				itineraryArray[1] = text_eP_actual_itinerary_date.getText();
				table.getItem(index).setText(1, itineraryArray[1]);
			}
			if (!text_eP_actual_itinerary_time.getText().isEmpty()) {
				itineraryArray[2] = text_eP_actual_itinerary_time.getText();
				table.getItem(index).setText(2, itineraryArray[2]);
			}
			if (!text_eP_actual_itinerary_done.getText().isEmpty()) {
				itineraryArray[3] = text_eP_actual_itinerary_done.getText();
				table.getItem(index).setText(3, itineraryArray[3]);
			}
			if (!text_eP_actual_itinerary_itinerary.getText().isEmpty() && !text_eP_actual_itinerary_date.getText().isEmpty()
					&& !text_eP_actual_itinerary_time.getText().isEmpty()
					&& !text_eP_actual_itinerary_done.getText().isEmpty()) {
				getParent().dispose();
			}
		}
	}
	
	public class ItineraryCancel2 extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
