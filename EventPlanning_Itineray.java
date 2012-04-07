import java.util.ArrayList;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class EventPlanning_Itineray extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table table_eP_participants_itinerary;
	private ArrayList<Itinerary> itineraryList;
	private Event event;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public EventPlanning_Itineray(Composite parent, int style, Event event) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.event = event;

		table_eP_participants_itinerary = new Table(this, SWT.BORDER
				| SWT.FULL_SELECTION);
		table_eP_participants_itinerary.setLinesVisible(true);
		table_eP_participants_itinerary.setHeaderVisible(true);
		table_eP_participants_itinerary.setBounds(10, 10, 550, 400);
		toolkit.adapt(table_eP_participants_itinerary);
		toolkit.paintBordersFor(table_eP_participants_itinerary);

		TableColumn col_eP_participants_description = new TableColumn(
				table_eP_participants_itinerary, SWT.CENTER);
		col_eP_participants_description.setWidth(300);
		col_eP_participants_description.setText("Description");

		TableColumn col_eP_participants_date = new TableColumn(
				table_eP_participants_itinerary, SWT.CENTER);
		col_eP_participants_date.setWidth(120);
		col_eP_participants_date.setText("Date");

		TableColumn col_eP_participants_time = new TableColumn(
				table_eP_participants_itinerary, SWT.CENTER);
		col_eP_participants_time.setWidth(120);
		col_eP_participants_time.setText("Time");

		importItineraryData();

	}

	public void importItineraryData() {
		DatabaseReader db = new DatabaseReader();
		itineraryList = db.getItinerary(event);

		for (int i = 0; i < itineraryList.size(); i++) {
			TableItem temp = new TableItem(table_eP_participants_itinerary,
					SWT.NULL);
			temp.setText(0, itineraryList.get(i).getItineraryDetails());
			temp.setText(1, itineraryList.get(i).getDate().toString());
			temp.setText(2, itineraryList.get(i).getTime().toString());
		}
	}

}
