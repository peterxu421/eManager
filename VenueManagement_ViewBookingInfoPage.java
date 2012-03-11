import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;


public class VenueManagement_ViewBookingInfoPage extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VenueManagement_ViewBookingInfoPage(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(37, 26, 340, 410);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		Label lblVenueBookingStatus = new Label(composite, SWT.NONE);
		lblVenueBookingStatus.setBounds(110, 20, 128, 15);
		toolkit.adapt(lblVenueBookingStatus, true, true);
		lblVenueBookingStatus.setText("Venue Booking Status");
		
		List listBookingStatus = new List(composite, SWT.BORDER);
		listBookingStatus.setBounds(32, 54, 275, 346);
		toolkit.adapt(listBookingStatus, true, true);

	}
}
