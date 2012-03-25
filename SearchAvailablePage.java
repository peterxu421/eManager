import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.custom.CCombo;


public class SearchAvailablePage extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text text;
	private Text text_1;
	private Text text_2;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SearchAvailablePage(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(10, 10, 237, 264);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		Label lblDate = new Label(composite, SWT.NONE);
		lblDate.setBounds(24, 27, 59, 14);
		toolkit.adapt(lblDate, true, true);
		lblDate.setText("Date:");
		
		DateTime dateTime = new DateTime(composite, SWT.BORDER);
		dateTime.setBounds(122, 22, 92, 27);
		toolkit.adapt(dateTime);
		toolkit.paintBordersFor(dateTime);
		
		Label lblTime = new Label(composite, SWT.NONE);
		lblTime.setBounds(24, 69, 81, 14);
		toolkit.adapt(lblTime, true, true);
		lblTime.setText("Time(2359):");
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(122, 64, 64, 19);
		toolkit.adapt(text, true, true);
		
		Label lblLocation = new Label(composite, SWT.NONE);
		lblLocation.setText("Location:");
		lblLocation.setBounds(24, 109, 59, 14);
		toolkit.adapt(lblLocation, true, true);
		
		CCombo combo = new CCombo(composite, SWT.BORDER);
		combo.setBounds(122, 106, 64, 14);
		toolkit.adapt(combo);
		toolkit.paintBordersFor(combo);
		
		CCombo combo_1 = new CCombo(composite, SWT.BORDER);
		combo_1.setBounds(122, 141, 64, 14);
		toolkit.adapt(combo_1);
		toolkit.paintBordersFor(combo_1);
		
		Label lblTypeOfVenue = new Label(composite, SWT.NONE);
		lblTypeOfVenue.setText("Type of Venue:");
		lblTypeOfVenue.setBounds(24, 144, 81, 14);
		toolkit.adapt(lblTypeOfVenue, true, true);
		
		Label lblCapacity = new Label(composite, SWT.NONE);
		lblCapacity.setText("Min. Capacity:");
		lblCapacity.setBounds(24, 183, 81, 14);
		toolkit.adapt(lblCapacity, true, true);
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(122, 178, 64, 19);
		toolkit.adapt(text_1, true, true);
		
		Label lblEquipments = new Label(composite, SWT.NONE);
		lblEquipments.setText("Equipments Req:");
		lblEquipments.setBounds(24, 222, 92, 14);
		toolkit.adapt(lblEquipments, true, true);
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setBounds(122, 217, 64, 19);
		toolkit.adapt(text_2, true, true);

	}
}
