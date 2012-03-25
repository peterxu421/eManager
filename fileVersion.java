import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;


public class fileVersion extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public fileVersion(Composite parent, int style) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(10, 10, 289, 69);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		Label lblEmanagerVersion = new Label(composite, SWT.NONE);
		lblEmanagerVersion.setBounds(85, 25, 141, 14);
		toolkit.adapt(lblEmanagerVersion, true, true);
		lblEmanagerVersion.setText("eManager Version 0.2");

	}
}
