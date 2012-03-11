import org.eclipse.swt.*;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.forms.widgets.FormToolkit;


public class FacilitatorEditItem extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text text_eP_actual_facilitator_name;
	private Text text_eP_actual_facilitator_year;
	private Text text_eP_actual_facilitator_faculty;
	private Text text_eP_actual_facilitator_interestedIn;
	private Facilitator facilitator;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public FacilitatorEditItem(Composite parent, int style, Table table, int index, Facilitator facilitator) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.facilitator = facilitator;
		
		Composite comp_eP_actual_facilitator_EditPage = new Composite(this, SWT.NONE);
		comp_eP_actual_facilitator_EditPage.setLayout(null);
		comp_eP_actual_facilitator_EditPage.setBounds(34, 10, 372, 290);
		toolkit.adapt(comp_eP_actual_facilitator_EditPage);
		toolkit.paintBordersFor(comp_eP_actual_facilitator_EditPage);
		
		Label lbl_eP_actual_facilitator_name = new Label(comp_eP_actual_facilitator_EditPage, SWT.NONE);
		lbl_eP_actual_facilitator_name.setText("Name");
		lbl_eP_actual_facilitator_name.setBounds(35, 34, 54, 17);
		toolkit.adapt(lbl_eP_actual_facilitator_name, true, true);
		
		Label lbl_eP_actual_facilitator_year = new Label(comp_eP_actual_facilitator_EditPage, SWT.NONE);
		lbl_eP_actual_facilitator_year.setText("Year");
		lbl_eP_actual_facilitator_year.setBounds(33, 72, 72, 17);
		toolkit.adapt(lbl_eP_actual_facilitator_year, true, true);
		
		Label lbl_eP_actual_facilitator_faculty = new Label(comp_eP_actual_facilitator_EditPage, SWT.NONE);
		lbl_eP_actual_facilitator_faculty.setText("Faculty");
		lbl_eP_actual_facilitator_faculty.setBounds(35, 111, 54, 17);
		toolkit.adapt(lbl_eP_actual_facilitator_faculty, true, true);
		
		Label lbl_eP_actual_facilitator_interestedIn = new Label(comp_eP_actual_facilitator_EditPage, SWT.NONE);
		lbl_eP_actual_facilitator_interestedIn.setText("Interested In");
		lbl_eP_actual_facilitator_interestedIn.setBounds(35, 150, 70, 17);
		toolkit.adapt(lbl_eP_actual_facilitator_interestedIn, true, true);
		
		text_eP_actual_facilitator_name = new Text(comp_eP_actual_facilitator_EditPage, SWT.BORDER);
		text_eP_actual_facilitator_name.setBounds(152, 31, 125, 23);
		toolkit.adapt(text_eP_actual_facilitator_name, true, true);
		text_eP_actual_facilitator_name.setText(table.getItem(index).getText(0));
		
		text_eP_actual_facilitator_year = new Text(comp_eP_actual_facilitator_EditPage, SWT.BORDER);
		text_eP_actual_facilitator_year.setBounds(152, 69, 125, 23);
		toolkit.adapt(text_eP_actual_facilitator_year, true, true);
		text_eP_actual_facilitator_year.setText(table.getItem(index).getText(1));
		
		text_eP_actual_facilitator_faculty = new Text(comp_eP_actual_facilitator_EditPage, SWT.BORDER);
		text_eP_actual_facilitator_faculty.setBounds(152, 108, 125, 23);
		toolkit.adapt(text_eP_actual_facilitator_faculty, true, true);
		text_eP_actual_facilitator_faculty.setText(table.getItem(index).getText(2));
		
		text_eP_actual_facilitator_interestedIn = new Text(comp_eP_actual_facilitator_EditPage, SWT.BORDER);
		text_eP_actual_facilitator_interestedIn.setBounds(152, 147, 125, 23);
		toolkit.adapt(text_eP_actual_facilitator_interestedIn, true, true);
		text_eP_actual_facilitator_interestedIn.setText(table.getItem(index).getText(3));
		
		Button btn_eP_actual_facilitator_edit = new Button(comp_eP_actual_facilitator_EditPage, SWT.NONE);
		btn_eP_actual_facilitator_edit.addSelectionListener(new FacilitatorEditOldItem(table, index));
		btn_eP_actual_facilitator_edit.setText("Edit");
		btn_eP_actual_facilitator_edit.setBounds(87, 207, 66, 27);
		toolkit.adapt(btn_eP_actual_facilitator_edit, true, true);
		
		Button btn_eP_actual_facilitator_cancel = new Button(comp_eP_actual_facilitator_EditPage, SWT.NONE);
		btn_eP_actual_facilitator_cancel.addSelectionListener(new FacilitatorCancel2());
		btn_eP_actual_facilitator_cancel.setText("Cancel");
		btn_eP_actual_facilitator_cancel.setBounds(211, 207, 66, 27);
		toolkit.adapt(btn_eP_actual_facilitator_cancel, true, true);

	}
	
	public class FacilitatorEditOldItem extends SelectionAdapter{
		Table table;
		int index;

		public FacilitatorEditOldItem(Table table, int index) {
			this.table = table;
			this.index=index;
		}

		public void widgetSelected(SelectionEvent e) {
			String[] facilitatorArray = new String[5];
			if (!text_eP_actual_facilitator_name.getText().isEmpty()) {
				facilitatorArray[0] = text_eP_actual_facilitator_name.getText();
				table.getItem(index).setText(0, facilitatorArray[0]);
			}
			if (!text_eP_actual_facilitator_year.getText().isEmpty()) {
				facilitatorArray[1] = text_eP_actual_facilitator_year.getText();
				table.getItem(index).setText(1, facilitatorArray[1]);
			}
			if (!text_eP_actual_facilitator_faculty.getText().isEmpty()) {
				facilitatorArray[2] = text_eP_actual_facilitator_faculty.getText();
				table.getItem(index).setText(2, facilitatorArray[2]);
			}
			if (!text_eP_actual_facilitator_interestedIn.getText().isEmpty()) {
				facilitatorArray[3] = text_eP_actual_facilitator_interestedIn.getText();
				table.getItem(index).setText(3, facilitatorArray[3]);
			}
			if (!text_eP_actual_facilitator_name.getText().isEmpty()
					&& !text_eP_actual_facilitator_year.getText().isEmpty()
					&& !text_eP_actual_facilitator_faculty.getText().isEmpty()
					&& !text_eP_actual_facilitator_interestedIn.getText().isEmpty()) {
				facilitator.setName(facilitatorArray[0]);
				facilitator.setYear(Integer.parseInt(facilitatorArray[1]));
				facilitator.setFaculty(facilitatorArray[2]);
				facilitator.setPosition(facilitatorArray[3]);
				DatabaseReader db = new DatabaseReader();
				db.updateFacilitator(facilitator);
				getParent().dispose();
			}
		}
	}
	
	public class FacilitatorCancel2 extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
