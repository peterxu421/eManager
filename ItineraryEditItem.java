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
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;

public class ItineraryEditItem extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text description;
	private DateTime date;
	private DateTime time;
	private Button done;

	private Event event;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ItineraryEditItem(Composite parent, int style, Table table,
			int index, Event event) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.event = event;

		Composite comp_eP_actual_itinerary_editPage = new Composite(this,
				SWT.NONE);
		comp_eP_actual_itinerary_editPage.setLayout(null);
		comp_eP_actual_itinerary_editPage.setBounds(34, 10, 372, 290);
		toolkit.adapt(comp_eP_actual_itinerary_editPage);
		toolkit.paintBordersFor(comp_eP_actual_itinerary_editPage);

		Label lblPageName = new Label(comp_eP_actual_itinerary_editPage,
				SWT.NONE);
		lblPageName.setBounds(153, 20, 104, 15);
		toolkit.adapt(lblPageName, true, true);
		lblPageName.setText("Event Itinerary");

		Label lbl_eP_actual_itinerary_itinerary = new Label(
				comp_eP_actual_itinerary_editPage, SWT.NONE);
		lbl_eP_actual_itinerary_itinerary.setAlignment(SWT.RIGHT);
		lbl_eP_actual_itinerary_itinerary.setText("Description");
		lbl_eP_actual_itinerary_itinerary.setBounds(47, 51, 70, 17);
		toolkit.adapt(lbl_eP_actual_itinerary_itinerary, true, true);

		Label lbl_eP_actual_itinerary_date = new Label(
				comp_eP_actual_itinerary_editPage, SWT.NONE);
		lbl_eP_actual_itinerary_date.setAlignment(SWT.RIGHT);
		lbl_eP_actual_itinerary_date.setText("Date");
		lbl_eP_actual_itinerary_date.setBounds(45, 94, 72, 17);
		toolkit.adapt(lbl_eP_actual_itinerary_date, true, true);

		Label lbl_eP_actual_itinerary_time = new Label(
				comp_eP_actual_itinerary_editPage, SWT.NONE);
		lbl_eP_actual_itinerary_time.setAlignment(SWT.RIGHT);
		lbl_eP_actual_itinerary_time.setText("Time");
		lbl_eP_actual_itinerary_time.setBounds(90, 134, 27, 17);
		toolkit.adapt(lbl_eP_actual_itinerary_time, true, true);

		Label lbl_eP_actual_itinerary_done = new Label(
				comp_eP_actual_itinerary_editPage, SWT.NONE);
		lbl_eP_actual_itinerary_done.setAlignment(SWT.RIGHT);
		lbl_eP_actual_itinerary_done.setText("Done");
		lbl_eP_actual_itinerary_done.setBounds(63, 170, 54, 17);
		toolkit.adapt(lbl_eP_actual_itinerary_done, true, true);

		description = new Text(comp_eP_actual_itinerary_editPage, SWT.BORDER);
		description.setBounds(153, 48, 125, 23);
		toolkit.adapt(description, true, true);
		if (index >= 0 && index < table.getItemCount()) {
			description.setText(table.getItem(index).getText(0));
		}

		date = new DateTime(comp_eP_actual_itinerary_editPage, SWT.BORDER);
		date.setBounds(153, 87, 125, 24);
		toolkit.adapt(date);
		toolkit.paintBordersFor(date);
		if (index >= 0 && index < table.getItemCount()) {
			Date dt = Date.parseDate(table.getItem(index).getText(1));
			date.setYear(dt.getYear());
			date.setMonth(dt.getMonth() - 1);
			date.setDay(dt.getDay());
		}

		time = new DateTime(comp_eP_actual_itinerary_editPage, SWT.BORDER
				| SWT.TIME);
		time.setBounds(153, 127, 125, 24);
		toolkit.adapt(time);
		if (index >= 0 && index < table.getItemCount()) {
			Time tm = Time.parseTime(table.getItem(index).getText(2));
			time.setHours(tm.getHour());
			time.setMinutes(tm.getMinute());
			time.setSeconds(tm.getSecond());
		} else {
			time.setHours(0);
			time.setMinutes(0);
			time.setSeconds(0);
		}

		done = new Button(comp_eP_actual_itinerary_editPage, SWT.CHECK);
		done.setBounds(154, 171, 93, 16);
		toolkit.adapt(done, true, true);
		if (index >= 0 && index < table.getItemCount())
			done.setSelection(table.getItem(index).getText(3) == "Yes");

		table.deselect(index);

		Button btn_eP_actual_itinerary_edit = new Button(
				comp_eP_actual_itinerary_editPage, SWT.NONE);
		btn_eP_actual_itinerary_edit.setText("Edit Item");
		btn_eP_actual_itinerary_edit
				.addSelectionListener(new ItineraryEditOldItem(table, index));
		btn_eP_actual_itinerary_edit.setBounds(86, 236, 66, 27);
		toolkit.adapt(btn_eP_actual_itinerary_edit, true, true);

		Button btn_eP_actual_itinerary_cancel = new Button(
				comp_eP_actual_itinerary_editPage, SWT.NONE);
		btn_eP_actual_itinerary_cancel.setText("Cancel");
		btn_eP_actual_itinerary_cancel
				.addSelectionListener(new ItineraryCancel());
		btn_eP_actual_itinerary_cancel.setBounds(211, 236, 66, 27);
		toolkit.adapt(btn_eP_actual_itinerary_cancel, true, true);
		btn_eP_actual_itinerary_cancel
				.addSelectionListener(new ItineraryCancel());

	}

	public class ItineraryEditOldItem extends SelectionAdapter {
		Table table;
		int index;

		public ItineraryEditOldItem(Table table, int index) {
			this.table = table;
			this.index = index;
		}

		public void widgetSelected(SelectionEvent e) {
			String[] itineraryArray = new String[4];
			if (!description.getText().isEmpty()) {
				itineraryArray[0] = description.getText();
			}
			itineraryArray[1] = String.format("%04d", date.getYear()) + "-"
					+ String.format("%02d", date.getMonth() + 1) + "-"
					+ String.format("%02d", date.getDay()); // getMonth() + 1
															// since getMonth()
															// returns 0 to 11
			itineraryArray[2] = String.format("%02d", time.getHours()) + ":"
					+ String.format("%02d", time.getMinutes()) + ":"
					+ String.format("%02d", time.getSeconds());
			if (done.getSelection())
				itineraryArray[3] = "Yes";
			else
				itineraryArray[3] = "No";

			if (!description.getText().isEmpty()) {
				/* update the itinerary table */
				TableItem item = table.getItem(index);
				for (int i = 0; i < 4; i++) {
					item.setText(i, itineraryArray[i]);
				}
				/* update the database */
				DatabaseReader db = new DatabaseReader();
				Itinerary newIti = db.getItinerary(event).get(index);
				newIti.setItineraryDetails(itineraryArray[0]);
				newIti.setDate(Date.parseDate(itineraryArray[1]));
				newIti.setTime(Time.parseTime(itineraryArray[2]));
				newIti.setDone(itineraryArray[3] == "Yes");
				db.updateItinerary(newIti);
				getParent().dispose();
			}
		}
	}

	public class ItineraryCancel extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
