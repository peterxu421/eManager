import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ListViewer;

public class eP_eventRegistration extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text textName;
	private Text textYearOfStudy;
	private Text textFaculty;
	private Text textEvent;
	private Text textFoodType;
	private Text textAllergy;
	private Text textMatriculation;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public eP_eventRegistration(Composite parent, int style) {
		super(parent, SWT.NONE);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		ScrolledComposite scrolledCompositeRegistration = new ScrolledComposite(
				this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledCompositeRegistration.setBounds(10, 10, 440, 326);
		toolkit.adapt(scrolledCompositeRegistration);
		toolkit.paintBordersFor(scrolledCompositeRegistration);
		scrolledCompositeRegistration.setExpandHorizontal(true);
		scrolledCompositeRegistration.setExpandVertical(true);

		Composite compositeRegistration = new Composite(
				scrolledCompositeRegistration, SWT.NONE);
		toolkit.adapt(compositeRegistration);
		toolkit.paintBordersFor(compositeRegistration);

		Label lblFullName = new Label(compositeRegistration, SWT.NONE);
		lblFullName.setBounds(10, 10, 165, 17);
		toolkit.adapt(lblFullName, true, true);
		lblFullName.setText("Full Name");

		textName = new Text(compositeRegistration, SWT.BORDER);
		textName.setBounds(10, 33, 165, 23);
		toolkit.adapt(textName, true, true);

		Label lblMatriculationNumber = new Label(compositeRegistration,
				SWT.NONE);
		lblMatriculationNumber.setText("Matriculation Number");
		lblMatriculationNumber.setBounds(10, 62, 165, 17);
		toolkit.adapt(lblMatriculationNumber, true, true);

		textMatriculation = new Text(compositeRegistration, SWT.BORDER);
		textMatriculation.setBounds(10, 85, 165, 23);
		toolkit.adapt(textMatriculation, true, true);

		Label lblYearOfStudy = new Label(compositeRegistration, SWT.NONE);
		lblYearOfStudy.setText("Year of Study");
		lblYearOfStudy.setBounds(10, 114, 165, 17);
		toolkit.adapt(lblYearOfStudy, true, true);

		textYearOfStudy = new Text(compositeRegistration, SWT.BORDER);
		textYearOfStudy.setBounds(10, 137, 165, 23);
		toolkit.adapt(textYearOfStudy, true, true);

		Label lblFaculty = new Label(compositeRegistration, SWT.NONE);
		lblFaculty.setText("Faculty");
		lblFaculty.setBounds(10, 166, 165, 17);
		toolkit.adapt(lblFaculty, true, true);

		textFaculty = new Text(compositeRegistration, SWT.BORDER);
		textFaculty.setBounds(10, 189, 165, 23);
		toolkit.adapt(textFaculty, true, true);

		Label lblEvent = new Label(compositeRegistration, SWT.NONE);
		lblEvent.setText("Event Interested");
		lblEvent.setBounds(10, 218, 165, 17);
		toolkit.adapt(lblEvent, true, true);

		textEvent = new Text(compositeRegistration, SWT.BORDER);
		textEvent.setBounds(10, 241, 165, 23);
		toolkit.adapt(textEvent, true, true);

		Label lblFoodType = new Label(compositeRegistration, SWT.H_SCROLL);
		lblFoodType.setText("Food Type");
		lblFoodType.setBounds(10, 270, 165, 17);
		toolkit.adapt(lblFoodType, true, true);

		textFoodType = new Text(compositeRegistration, SWT.BORDER);
		textFoodType.setBounds(10, 293, 165, 23);
		toolkit.adapt(textFoodType, true, true);

		Label lblAllergy = new Label(compositeRegistration, SWT.NONE);
		lblAllergy.setText("Allergy (If any)");
		lblAllergy.setBounds(10, 322, 165, 17);
		toolkit.adapt(lblAllergy, true, true);

		textAllergy = new Text(compositeRegistration, SWT.BORDER);
		textAllergy.setBounds(10, 345, 165, 23);
		toolkit.adapt(textAllergy, true, true);

		Button btnClear = new Button(compositeRegistration, SWT.NONE);
		btnClear.addSelectionListener(new RegistrationClear());
		btnClear.setBounds(95, 374, 80, 27);
		toolkit.adapt(btnClear, true, true);
		btnClear.setText("Clear");

		Button btnSubmit = new Button(compositeRegistration, SWT.NONE);
		btnSubmit.setText("Submit");
		btnSubmit.setBounds(10, 374, 80, 27);
		toolkit.adapt(btnSubmit, true, true);
		scrolledCompositeRegistration.setContent(compositeRegistration);
		scrolledCompositeRegistration.setMinSize(compositeRegistration
				.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	public class RegistrationClear extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			textName.setText("");
			textYearOfStudy.setText("");
			textFaculty.setText("");
			textEvent.setText("");
			textFoodType.setText("");
			textAllergy.setText("");
			textMatriculation.setText("");
		}
	}
}
