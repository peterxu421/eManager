import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.wb.swt.SWTResourceManager;
<<<<<<< HEAD
import org.eclipse.swt.widgets.Label;
=======
>>>>>>> de2b69bfc67aaafeb9b3c8d5b15122ffabc7fcd6


public class VenueBooking_InstructionPage extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private String lyrics = "This is the page to display regulations and rules.:-)\n" +
			"Regulations: You say it!\n" +
			"Regulations: You say it!\n" +
			"Regulations: You say it!\n" +
			"Regulations: You say it!\n" +
			"Regulations: You say it!\n" +
			"Regulations: You say it!\n" +
			"Regulations: You say it!\n" +
			"Regulations: You say it!\n" +
			"Regulations: You say it!\n" +
			"Regulations: You say it!\n" +
			"Regulations: You say it!\n" +
			"Rules: You say it!";

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VenueBooking_InstructionPage(Composite parent, int style) {
<<<<<<< HEAD
		super(parent, SWT.NONE);
=======
		super(parent, style);
>>>>>>> de2b69bfc67aaafeb9b3c8d5b15122ffabc7fcd6
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
<<<<<<< HEAD
		Composite composite = new Composite(this, SWT.BORDER);
		composite.setBounds(0, 0, 547, 406);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		Label lblNewLabel = new Label(composite, SWT.HORIZONTAL);
		lblNewLabel.setAlignment(SWT.LEFT);
		lblNewLabel.setFont(SWTResourceManager.getFont("Courier New", 11, SWT.NORMAL));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblNewLabel.setBounds(0, 0, 521, 379);
		toolkit.adapt(lblNewLabel, true, true);
		lblNewLabel.setText(lyrics);
=======
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(10, 10, 365, 393);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		CLabel lblDisplayBoard = new CLabel(composite, SWT.NONE);
		lblDisplayBoard.setForeground(SWTResourceManager.getColor(0, 0, 0));
		lblDisplayBoard.setFont(SWTResourceManager.getFont("Courier New", 11, SWT.NORMAL));
		lblDisplayBoard.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblDisplayBoard.setBounds(10, 10, 345, 373);
		toolkit.adapt(lblDisplayBoard);
		toolkit.paintBordersFor(lblDisplayBoard);
		lblDisplayBoard.setText(lyrics);
>>>>>>> de2b69bfc67aaafeb9b3c8d5b15122ffabc7fcd6
	}
}
