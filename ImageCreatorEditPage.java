import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class ImageCreatorEditPage extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text text_eP_actual_facilitator_name;
	private Text text_eP_actual_facilitator_year;
	private Text text_eP_actual_facilitator_faculty;
	private GC gc;
	private String[] array_1;
	private Label imageLabel;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ImageCreatorEditPage(Composite parent, int style, GC gc,
			String[] array_1, Label imageLabel) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		setLayout(null);
		this.gc = gc;
		this.array_1 = array_1;
		this.imageLabel = imageLabel;
		
		Composite comp_eP_actual_facilitator_AddPage = new Composite(this,
				SWT.NONE);
		comp_eP_actual_facilitator_AddPage.setBounds(56, 10, 372, 192);
		comp_eP_actual_facilitator_AddPage.setLayout(null);
		toolkit.adapt(comp_eP_actual_facilitator_AddPage);
		toolkit.paintBordersFor(comp_eP_actual_facilitator_AddPage);

		Label lbl_eP_actual_facilitator_name = new Label(
				comp_eP_actual_facilitator_AddPage, SWT.NONE);
		lbl_eP_actual_facilitator_name.setBounds(35, 34, 92, 17);
		toolkit.adapt(lbl_eP_actual_facilitator_name, true, true);
		lbl_eP_actual_facilitator_name.setText("Event Name");

		Label lbl_eP_actual_facilitator_year = new Label(
				comp_eP_actual_facilitator_AddPage, SWT.NONE);
		lbl_eP_actual_facilitator_year.setBounds(36, 68, 72, 17);
		toolkit.adapt(lbl_eP_actual_facilitator_year, true, true);
		lbl_eP_actual_facilitator_year.setText("Date & Time");

		Label lbl_eP_actual_facilitator_faclty = new Label(
				comp_eP_actual_facilitator_AddPage, SWT.NONE);
		lbl_eP_actual_facilitator_faclty.setBounds(35, 106, 54, 17);
		toolkit.adapt(lbl_eP_actual_facilitator_faclty, true, true);
		lbl_eP_actual_facilitator_faclty.setText("Location");

		text_eP_actual_facilitator_name = new Text(
				comp_eP_actual_facilitator_AddPage, SWT.BORDER);
		text_eP_actual_facilitator_name.setBounds(152, 31, 125, 23);
		toolkit.adapt(text_eP_actual_facilitator_name, true, true);
		text_eP_actual_facilitator_name.setText(array_1[0]);

		text_eP_actual_facilitator_year = new Text(
				comp_eP_actual_facilitator_AddPage, SWT.BORDER);
		text_eP_actual_facilitator_year.setBounds(153, 65, 125, 23);
		toolkit.adapt(text_eP_actual_facilitator_year, true, true);
		text_eP_actual_facilitator_year.setText(array_1[1]);

		text_eP_actual_facilitator_faculty = new Text(
				comp_eP_actual_facilitator_AddPage, SWT.BORDER);
		text_eP_actual_facilitator_faculty.setBounds(152, 103, 125, 23);
		toolkit.adapt(text_eP_actual_facilitator_faculty, true, true);
		text_eP_actual_facilitator_faculty.setText(array_1[2]);
		
		Button btn_eP_actual_facilitator_add = new Button(
				comp_eP_actual_facilitator_AddPage, SWT.NONE);
		btn_eP_actual_facilitator_add
				.addSelectionListener(new ImageCreatorEdit());
		btn_eP_actual_facilitator_add.setBounds(61, 144, 66, 27);
		toolkit.adapt(btn_eP_actual_facilitator_add, true, true);
		btn_eP_actual_facilitator_add.setText("Add");

		Button btn_eP_actual_facilitator_cancel = new Button(
				comp_eP_actual_facilitator_AddPage, SWT.NONE);
		btn_eP_actual_facilitator_cancel
				.addSelectionListener(new FacilitatorCancel());
		btn_eP_actual_facilitator_cancel.setBounds(185, 144, 66, 27);
		toolkit.adapt(btn_eP_actual_facilitator_cancel, true, true);
		btn_eP_actual_facilitator_cancel.setText("Cancel");

	}

	public class ImageCreatorEdit extends SelectionAdapter {

		public void widgetSelected(SelectionEvent e) {
			String[] itineraryArray = new String[3];
			if (!text_eP_actual_facilitator_name.getText().isEmpty()) {
				itineraryArray[0] = text_eP_actual_facilitator_name.getText();
			}
			if (!text_eP_actual_facilitator_year.getText().isEmpty()) {
				itineraryArray[1] = text_eP_actual_facilitator_year.getText();
			}
			if (!text_eP_actual_facilitator_faculty.getText().isEmpty()) {
				itineraryArray[2] = text_eP_actual_facilitator_faculty
						.getText();
			}
			if (!text_eP_actual_facilitator_name.getText().isEmpty()
					&& !text_eP_actual_facilitator_year.getText().isEmpty()
					&& !text_eP_actual_facilitator_faculty.getText().isEmpty()) {

				for (int i = 0; i < 3; i++) {
					array_1[i] = itineraryArray[i];
				}

				// set draws text
				System.out.println("test" + itineraryArray[0]);
				gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
				gc.drawText(itineraryArray[0], 40, 10, true);
				gc.drawText(itineraryArray[1], 40, 150, true);
				gc.drawText(itineraryArray[2], 40, 200, true);
				imageLabel.redraw();

				getParent().dispose();
			}
		}
	}

	public class FacilitatorCancel extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
