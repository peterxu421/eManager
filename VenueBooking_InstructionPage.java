import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.wb.swt.SWTResourceManager;


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
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
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
	}
}
