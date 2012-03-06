import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;


public class eP_participants_itinerary extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table table_eP_participants_itinerary;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public eP_participants_itinerary(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite comp_participants_itinerary = new Composite(this, SWT.NONE);
		comp_participants_itinerary.setBounds(10, 10, 553, 290);
		toolkit.adapt(comp_participants_itinerary);
		toolkit.paintBordersFor(comp_participants_itinerary);
		
		table_eP_participants_itinerary = new Table(comp_participants_itinerary, SWT.BORDER | SWT.FULL_SELECTION);
		table_eP_participants_itinerary.setLinesVisible(true);
		table_eP_participants_itinerary.setHeaderVisible(true);
		table_eP_participants_itinerary.setBounds(10, 10, 418, 255);
		toolkit.adapt(table_eP_participants_itinerary);
		toolkit.paintBordersFor(table_eP_participants_itinerary);
		
		TableColumn col_eP_participants_description = new TableColumn(table_eP_participants_itinerary, SWT.CENTER);
		col_eP_participants_description.setWidth(196);
		col_eP_participants_description.setText("Description");
		
		TableColumn col_eP_participants_date = new TableColumn(table_eP_participants_itinerary, SWT.NONE);
		col_eP_participants_date.setWidth(71);
		col_eP_participants_date.setText("Date");
		
		TableColumn col_eP_participants_time = new TableColumn(table_eP_participants_itinerary, SWT.NONE);
		col_eP_participants_time.setWidth(68);
		col_eP_participants_time.setText("Time");
		
		TableColumn col_eP_participants_done = new TableColumn(table_eP_participants_itinerary, SWT.NONE);
		col_eP_participants_done.setWidth(77);
		col_eP_participants_done.setText("Done");
		
		Button btn_eP_participants_print = new Button(comp_participants_itinerary, SWT.NONE);
		btn_eP_participants_print.setText("Print");
		btn_eP_participants_print.setBounds(445, 10, 80, 27);
		toolkit.adapt(btn_eP_participants_print, true, true);

	}

}
