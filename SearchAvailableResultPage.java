import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class SearchAvailableResultPage extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table table;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public SearchAvailableResultPage(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(10, 10, 407, 261);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 10, 387, 241);
		toolkit.adapt(table);
		toolkit.paintBordersFor(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(79);
		tblclmnNewColumn.setText("Location");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("Type of Venue");

		TableColumn tblclmnCapacity = new TableColumn(table, SWT.NONE);
		tblclmnCapacity.setWidth(100);
		tblclmnCapacity.setText("Capacity");

		TableColumn tblclmnEquipments = new TableColumn(table, SWT.NONE);
		tblclmnEquipments.setWidth(106);
		tblclmnEquipments.setText("Equipments");

	}
}
