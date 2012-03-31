import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;


public class VenueBooking_InstructionPage extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private String lyrics = "Eligibility\n" +
			"The bookings are only for NUS Students Staff.\n\n" +
			"Terms and Conditions\n" +
			"<to be set by the venue manager>\n\n" +
			"Administration Guide\n" +
			"<to be set by the venue manager>\n\n" + 
			"Note: please submit your event proposal to the venue manager before booking a venue.";

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VenueBooking_InstructionPage(Composite parent, int style) {
		super(parent, SWT.NONE);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite composite = new Composite(this, SWT.BORDER);
		composite.setBounds(0, 0, 547, 406);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		Label lblNewLabel = new Label(composite, SWT.WRAP | SWT.HORIZONTAL);
		lblNewLabel.setAlignment(SWT.LEFT);
		lblNewLabel.setFont(SWTResourceManager.getFont("Courier New", 11, SWT.NORMAL));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblNewLabel.setBounds(0, 0, 521, 379);
		toolkit.adapt(lblNewLabel, true, true);
		lblNewLabel.setText(lyrics);
	}
}
