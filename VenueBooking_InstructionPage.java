import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Event;

public class VenueBooking_InstructionPage extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private String[] lyrics = {
			"Eligibility",
			"The bookings are only for NUS Students and Staff.",
			"     ",
			"Terms and Conditions",
			"<to be set by the venue manager>",
			"     ",
			"Administration Guide",
			"<to be set by the venue manager>",
			"     ",
			"Note: please submit your event proposal to the venue manager before booking a venue." };

	/**
	 * Create the composite.
	 * 
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

		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(0, 0, 800, 400);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);

		final List list = new List(composite, SWT.BORDER | SWT.V_SCROLL);
		list.setFont(SWTResourceManager.getFont("Courier New", 11, SWT.NORMAL));
		list.setBounds(0, 0, 800, 400);
		toolkit.adapt(list, true, true);
		for (int i = 0; i < lyrics.length; i++) {
			list.add(lyrics[i]);
		}

		list.addListener(SWT.MouseDown, new Listener() {
			public void handleEvent(Event e) {
				list.deselectAll();
			}
		}); // disable table item selection
	}
}
