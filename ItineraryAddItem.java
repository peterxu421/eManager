import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.DateTime;

public class ItineraryAddItem extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private final Text description;
	private final DateTime date;
	private final DateTime time;
	private final Button done;

	private Event event;
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ItineraryAddItem(Composite parent, int style, Table table_1, Event event) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.event = event;
		setLayout(null);

		Composite comp_eP_actual_itinerary_addPage = new Composite(this, SWT.NONE);
		comp_eP_actual_itinerary_addPage.setBounds(57, 20, 372, 304);
		comp_eP_actual_itinerary_addPage.setLayout(null);
		toolkit.adapt(comp_eP_actual_itinerary_addPage);
		toolkit.paintBordersFor(comp_eP_actual_itinerary_addPage);
		
		Label lblPageName = new Label(comp_eP_actual_itinerary_addPage, SWT.NONE);
		lblPageName.setBounds(152, 10, 93, 15);
		toolkit.adapt(lblPageName, true, true);
		lblPageName.setText("Event Itinerary");

		Label lbl_eP_actual_itinerary_itinerary = new Label(comp_eP_actual_itinerary_addPage, SWT.NONE);
		lbl_eP_actual_itinerary_itinerary.setAlignment(SWT.RIGHT);
		lbl_eP_actual_itinerary_itinerary.setBounds(55, 51, 70, 17);
		toolkit.adapt(lbl_eP_actual_itinerary_itinerary, true, true);
		lbl_eP_actual_itinerary_itinerary.setText("Description");

		Label lbl_eP_actual_itinerary_date = new Label(comp_eP_actual_itinerary_addPage, SWT.NONE);
		lbl_eP_actual_itinerary_date.setAlignment(SWT.RIGHT);
		lbl_eP_actual_itinerary_date.setBounds(53, 91, 72, 17);
		toolkit.adapt(lbl_eP_actual_itinerary_date, true, true);
		lbl_eP_actual_itinerary_date.setText("Date");

		Label lbl_eP_actual_itinerary_time = new Label(comp_eP_actual_itinerary_addPage, SWT.NONE);
		lbl_eP_actual_itinerary_time.setAlignment(SWT.RIGHT);
		lbl_eP_actual_itinerary_time.setBounds(98, 133, 27, 17);
		toolkit.adapt(lbl_eP_actual_itinerary_time, true, true);
		lbl_eP_actual_itinerary_time.setText("Time");

		Label lbl_eP_actual_itinerary_done = new Label(comp_eP_actual_itinerary_addPage, SWT.NONE);
		lbl_eP_actual_itinerary_done.setAlignment(SWT.RIGHT);
		lbl_eP_actual_itinerary_done.setBounds(71, 177, 54, 17);
		toolkit.adapt(lbl_eP_actual_itinerary_done, true, true);
		lbl_eP_actual_itinerary_done.setText("Done");

		description = new Text(comp_eP_actual_itinerary_addPage, SWT.BORDER);
		description.setBounds(152, 48, 125, 23);
		toolkit.adapt(description, true, true);
		
		date = new DateTime(comp_eP_actual_itinerary_addPage, SWT.BORDER);
		date.setBounds(152, 84, 125, 24);
		toolkit.adapt(date);
		toolkit.paintBordersFor(date);
		
		time = new DateTime(comp_eP_actual_itinerary_addPage, SWT.BORDER | SWT.TIME);
		time.setBounds(152, 126, 125, 24);
		toolkit.adapt(time);
		toolkit.paintBordersFor(time);
		time.setHours(0);
		time.setMinutes(0);
		time.setSeconds(0);
		
		done = new Button(comp_eP_actual_itinerary_addPage, SWT.CHECK);
		done.setBounds(152, 178, 93, 16);
		toolkit.adapt(done, true, true);


		Button btn_eP_actual_itinerary_add = new Button(comp_eP_actual_itinerary_addPage, SWT.NONE);
		btn_eP_actual_itinerary_add.setBounds(85, 237, 66, 27);
		toolkit.adapt(btn_eP_actual_itinerary_add, true, true);
		btn_eP_actual_itinerary_add.setText("Add Item");
		btn_eP_actual_itinerary_add.addSelectionListener(new ItineraryAddNewItem(table_1));

		Button btn_eP_actual_itinerary_cancel = new Button(comp_eP_actual_itinerary_addPage, SWT.NONE);
		btn_eP_actual_itinerary_cancel.setBounds(209, 237, 66, 27);
		toolkit.adapt(btn_eP_actual_itinerary_cancel, true, true);
		btn_eP_actual_itinerary_cancel.setText("Cancel");
		btn_eP_actual_itinerary_cancel.addSelectionListener(new ItineraryCancel());
	
	}

	public class ItineraryAddNewItem extends SelectionAdapter {
		Table table_1;

		public ItineraryAddNewItem(Table table_1) {
			this.table_1 = table_1;
		}

		public void widgetSelected(SelectionEvent e) {
			String[] itineraryArray = new String[4];
			if (!description.getText().isEmpty()) {
				itineraryArray[0] = description.getText();
			}
			itineraryArray[1] = String.format("%04d",date.getYear())  + "-"
			        + String.format("%02d", date.getMonth()+1)  + "-" 
					+ String.format("%02d",date.getDay()) ;  // getMonth() + 1 since getMonth() returns 0 to 11
			itineraryArray[2] = String.format("%02d",time.getHours()) + ":"
			        + String.format("%02d",time.getMinutes())  + ":" 
					+ String.format("%02d",time.getSeconds()) ;
			if (done.getSelection()) itineraryArray[3] = "Yes";
			else itineraryArray[3] = "No";

			if (!description.getText().isEmpty()) {
				/* update the itinerary table */
				TableItem item = new TableItem(table_1, SWT.NULL);
				for (int i = 0; i < 4; i++) {
					item.setText(i, itineraryArray[i]);
				}
				/* update the database */
				DatabaseReader db = new DatabaseReader();
				Itinerary newIti = new Itinerary(itineraryArray[0], Date.parseDate(itineraryArray[1]), Time.parseTime(itineraryArray[2]), itineraryArray[3] == "Yes");
				db.insertItinerary(event, newIti);	
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
