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
import org.eclipse.swt.events.DisposeEvent;

public class FacilitatorAddItem extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text text_eP_actual_facilitator_name;
	private Text text_eP_actual_facilitator_year;
	private Text text_eP_actual_facilitator_faculty;
	private Text text_eP_actual_facilitator_interestedIn;
	private Text text_eP_actual_facilitator_status;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public FacilitatorAddItem(Composite parent, int style, Table table_1) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		setLayout(null);

		Composite comp_eP_actual_facilitator_AddPage = new Composite(this, SWT.NONE);
		comp_eP_actual_facilitator_AddPage.setBounds(56, 10, 372, 290);
		comp_eP_actual_facilitator_AddPage.setLayout(null);
		toolkit.adapt(comp_eP_actual_facilitator_AddPage);
		toolkit.paintBordersFor(comp_eP_actual_facilitator_AddPage);

		Label lbl_eP_actual_facilitator_name = new Label(comp_eP_actual_facilitator_AddPage, SWT.NONE);
		lbl_eP_actual_facilitator_name.setBounds(35, 34, 54, 17);
		toolkit.adapt(lbl_eP_actual_facilitator_name, true, true);
		lbl_eP_actual_facilitator_name.setText("Name");

		Label lbl_eP_actual_facilitator_year = new Label(comp_eP_actual_facilitator_AddPage, SWT.NONE);
		lbl_eP_actual_facilitator_year.setBounds(36, 68, 72, 17);
		toolkit.adapt(lbl_eP_actual_facilitator_year, true, true);
		lbl_eP_actual_facilitator_year.setText("Year");

		Label lbl_eP_actual_facilitator_faclty = new Label(comp_eP_actual_facilitator_AddPage, SWT.NONE);
		lbl_eP_actual_facilitator_faclty.setBounds(35, 106, 54, 17);
		toolkit.adapt(lbl_eP_actual_facilitator_faclty, true, true);
		lbl_eP_actual_facilitator_faclty.setText("Faculty");

		Label lbl_eP_actual_facilitator_interestedIn = new Label(comp_eP_actual_facilitator_AddPage, SWT.NONE);
		lbl_eP_actual_facilitator_interestedIn.setBounds(35, 143, 73, 17);
		toolkit.adapt(lbl_eP_actual_facilitator_interestedIn, true, true);
		lbl_eP_actual_facilitator_interestedIn.setText("Interested In");
		
		Label lbl_eP_actual_facilitator_status = new Label(comp_eP_actual_facilitator_AddPage, SWT.NONE);
		lbl_eP_actual_facilitator_status.setText("Status");
		lbl_eP_actual_facilitator_status.setBounds(35, 183, 54, 17);
		toolkit.adapt(lbl_eP_actual_facilitator_status, true, true);

		text_eP_actual_facilitator_name = new Text(comp_eP_actual_facilitator_AddPage, SWT.BORDER);
		text_eP_actual_facilitator_name.setBounds(152, 31, 125, 23);
		toolkit.adapt(text_eP_actual_facilitator_name, true, true);

		text_eP_actual_facilitator_year = new Text(comp_eP_actual_facilitator_AddPage, SWT.BORDER);
		text_eP_actual_facilitator_year.setBounds(153, 65, 125, 23);
		toolkit.adapt(text_eP_actual_facilitator_year, true, true);

		text_eP_actual_facilitator_faculty = new Text(comp_eP_actual_facilitator_AddPage, SWT.BORDER);
		text_eP_actual_facilitator_faculty.setBounds(152, 103, 125, 23);
		toolkit.adapt(text_eP_actual_facilitator_faculty, true, true);

		text_eP_actual_facilitator_interestedIn = new Text(comp_eP_actual_facilitator_AddPage, SWT.BORDER);
		text_eP_actual_facilitator_interestedIn.setBounds(152, 140, 125, 23);
		toolkit.adapt(text_eP_actual_facilitator_interestedIn, true, true);
		
		text_eP_actual_facilitator_status = new Text(comp_eP_actual_facilitator_AddPage, SWT.BORDER);
		text_eP_actual_facilitator_status.setBounds(152, 180, 125, 23);
		toolkit.adapt(text_eP_actual_facilitator_status, true, true);

		Button btn_eP_actual_facilitator_add = new Button(comp_eP_actual_facilitator_AddPage, SWT.NONE);
		btn_eP_actual_facilitator_add.addSelectionListener(new FacilitatorAddNewItem(table_1));
		btn_eP_actual_facilitator_add.setBounds(87, 219, 66, 27);
		toolkit.adapt(btn_eP_actual_facilitator_add, true, true);
		btn_eP_actual_facilitator_add.setText("Add");

		Button btn_eP_actual_facilitator_cancel = new Button(comp_eP_actual_facilitator_AddPage, SWT.NONE);
		btn_eP_actual_facilitator_cancel.addSelectionListener(new FacilitatorCancel());
		btn_eP_actual_facilitator_cancel.setBounds(211, 219, 66, 27);
		toolkit.adapt(btn_eP_actual_facilitator_cancel, true, true);
		btn_eP_actual_facilitator_cancel.setText("Cancel");

	}

	public class FacilitatorAddNewItem extends SelectionAdapter {
		Table table_1;

		public FacilitatorAddNewItem(Table table_1) {
			this.table_1 = table_1;
		}

		public void widgetSelected(SelectionEvent e) {
			String[] itineraryArray = new String[5];
			if (!text_eP_actual_facilitator_name.getText().isEmpty()) {
				itineraryArray[0] = text_eP_actual_facilitator_name.getText();
			}
			if (!text_eP_actual_facilitator_year.getText().isEmpty()) {
				itineraryArray[1] = text_eP_actual_facilitator_year.getText();
			}
			if (!text_eP_actual_facilitator_faculty.getText().isEmpty()) {
				itineraryArray[2] = text_eP_actual_facilitator_faculty.getText();
			}
			if (!text_eP_actual_facilitator_interestedIn.getText().isEmpty()) {
				itineraryArray[3] = text_eP_actual_facilitator_interestedIn.getText();
			}
			if (!text_eP_actual_facilitator_interestedIn.getText().isEmpty()) {
				itineraryArray[4] = text_eP_actual_facilitator_status.getText();
			}
			if (!text_eP_actual_facilitator_name.getText().isEmpty()
					&& !text_eP_actual_facilitator_year.getText().isEmpty()
					&& !text_eP_actual_facilitator_faculty.getText().isEmpty()
					&& !text_eP_actual_facilitator_interestedIn.getText().isEmpty()
					&& !text_eP_actual_facilitator_status.getText().isEmpty()) {
				TableItem item = new TableItem(table_1, SWT.NULL);
				for (int i = 0; i < 5; i++) {
					item.setText(i, itineraryArray[i]);
				}
				getParent().dispose();
			}
		}
	}
	public class FacilitatorCancel extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
