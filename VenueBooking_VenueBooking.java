import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;


public class VenueBooking_VenueBooking extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VenueBooking_VenueBooking(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(10, 10, 440, 307);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 72, 17);
		toolkit.adapt(lblNewLabel, true, true);
		lblNewLabel.setText("Full Name");
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(10, 33, 127, 23);
		toolkit.adapt(text, true, true);
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setBounds(10, 62, 127, 17);
		toolkit.adapt(lblNewLabel_1, true, true);
		lblNewLabel_1.setText("Matriculation Card");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(9, 85, 128, 23);
		toolkit.adapt(text_1, true, true);
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setBounds(10, 114, 95, 17);
		toolkit.adapt(lblNewLabel_2, true, true);
		lblNewLabel_2.setText("Organization");
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setBounds(10, 137, 127, 23);
		toolkit.adapt(text_2, true, true);
		
		Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setBounds(10, 166, 72, 17);
		toolkit.adapt(lblNewLabel_3, true, true);
		lblNewLabel_3.setText("Date Start");
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setBounds(10, 189, 127, 23);
		toolkit.adapt(text_3, true, true);
		
		Label lblNewLabel_4 = new Label(composite, SWT.NONE);
		lblNewLabel_4.setBounds(10, 218, 61, 17);
		toolkit.adapt(lblNewLabel_4, true, true);
		lblNewLabel_4.setText("Date End");
		
		text_4 = new Text(composite, SWT.BORDER);
		text_4.setBounds(9, 241, 128, 23);
		toolkit.adapt(text_4, true, true);

	}
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		VenueBooking_VenueBooking calc = new VenueBooking_VenueBooking(shell,
				SWT.NONE);
		calc.pack();
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
}
