import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;


public class eP_participants_packingList extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table table_eP_participants_packingList;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public eP_participants_packingList(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite comp_eP_participants_packingList = new Composite(this, SWT.NONE);
		comp_eP_participants_packingList.setBounds(10, 10, 553, 290);
		toolkit.adapt(comp_eP_participants_packingList);
		toolkit.paintBordersFor(comp_eP_participants_packingList);
		
		table_eP_participants_packingList = new Table(comp_eP_participants_packingList, SWT.BORDER | SWT.FULL_SELECTION);
		table_eP_participants_packingList.setLinesVisible(true);
		table_eP_participants_packingList.setHeaderVisible(true);
		table_eP_participants_packingList.setBounds(10, 10, 418, 255);
		toolkit.adapt(table_eP_participants_packingList);
		toolkit.paintBordersFor(table_eP_participants_packingList);
		
		TableColumn col_eP_participants_description = new TableColumn(table_eP_participants_packingList, SWT.CENTER);
		col_eP_participants_description.setWidth(130);
		col_eP_participants_description.setText("Category");
		
		TableColumn col_eP_participants_date = new TableColumn(table_eP_participants_packingList, SWT.NONE);
		col_eP_participants_date.setWidth(96);
		col_eP_participants_date.setText("Item");
		
		TableColumn col_eP_participants_time = new TableColumn(table_eP_participants_packingList, SWT.NONE);
		col_eP_participants_time.setWidth(68);
		col_eP_participants_time.setText("Quantity");
		
		TableColumn col_eP_participants_done = new TableColumn(table_eP_participants_packingList, SWT.NONE);
		col_eP_participants_done.setWidth(77);
		col_eP_participants_done.setText("Remark");
		
		Button btn_eP_participants_print = new Button(comp_eP_participants_packingList, SWT.NONE);
		btn_eP_participants_print.setText("Print");
		btn_eP_participants_print.setBounds(445, 10, 80, 27);
		toolkit.adapt(btn_eP_participants_print, true, true);

	}

}
