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

public class eP_participants_packingList extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table table_eP_participants_packingList;
	private ArrayList<PackingItem> packingList;
	private Event event;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public eP_participants_packingList(Composite parent, int style, Event event) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.event = event;

		table_eP_participants_packingList = new Table(this, SWT.BORDER
				| SWT.FULL_SELECTION);
		table_eP_participants_packingList.setLinesVisible(true);
		table_eP_participants_packingList.setHeaderVisible(true);
		table_eP_participants_packingList.setBounds(10, 10, 550, 400);
		toolkit.adapt(table_eP_participants_packingList);
		toolkit.paintBordersFor(table_eP_participants_packingList);

		TableColumn col_eP_participants_description = new TableColumn(
				table_eP_participants_packingList, SWT.CENTER);
		col_eP_participants_description.setWidth(130);
		col_eP_participants_description.setText("Category");

		TableColumn col_eP_participants_date = new TableColumn(
				table_eP_participants_packingList, SWT.CENTER);
		col_eP_participants_date.setWidth(120);
		col_eP_participants_date.setText("Item");

		TableColumn col_eP_participants_time = new TableColumn(
				table_eP_participants_packingList, SWT.CENTER);
		col_eP_participants_time.setWidth(80);
		col_eP_participants_time.setText("Quantity");

		TableColumn col_eP_participants_done = new TableColumn(
				table_eP_participants_packingList, SWT.CENTER);
		col_eP_participants_done.setWidth(220);
		col_eP_participants_done.setText("Remark");

		fillTable();
	}

	public void fillTable() {
		DatabaseReader dbReader = new DatabaseReader();
		packingList = dbReader.getPackingItems(event);
		// memberList = dbReader.getMember(event);
		TableItem item;

		for (int i = 0; i < packingList.size(); i++) {
			item = new TableItem(table_eP_participants_packingList, SWT.NONE);
			item.setText(0, packingList.get(i).getCategory());
			item.setText(1, packingList.get(i).getName());
			item.setText(2, Integer.toString(packingList.get(i).getQuantity()));
			item.setText(3, packingList.get(i).getRemarks());
		}
	}
}
