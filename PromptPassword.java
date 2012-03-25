import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;


public class PromptPassword extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text text;
	private Text text_1;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public PromptPassword(Composite parent, int style, int mode) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		Composite composite = new Composite(this, SWT.NONE);
		composite.setBounds(10, 10, 224, 165);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);

		Label lblUser = new Label(composite, SWT.NONE);
		lblUser.setBounds(21, 20, 59, 14);
		toolkit.adapt(lblUser, true, true);
		lblUser.setText("Username:");

		text = new Text(composite, SWT.BORDER);
		text.setBounds(96, 20, 103, 19);
		toolkit.adapt(text, true, true);

		Label lblPassword = new Label(composite, SWT.NONE);
		lblPassword.setText("Password:");
		lblPassword.setBounds(21, 52, 59, 14);
		toolkit.adapt(lblPassword, true, true);

		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(96, 52, 103, 19);
		toolkit.adapt(text_1, true, true);

		Button btnConfirm = new Button(composite, SWT.NONE);
		btnConfirm.setBounds(10, 87, 94, 28);
		toolkit.adapt(btnConfirm, true, true);
		btnConfirm.setText("Confirm");

		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setBounds(120, 87, 94, 28);
		toolkit.adapt(btnCancel, true, true);
		btnCancel.setText("Cancel");

		Button btnForgotPassword = new Button(composite, SWT.NONE);
		btnForgotPassword.setBounds(10, 121, 125, 28);
		toolkit.adapt(btnForgotPassword, true, true);
		btnForgotPassword.setText("Forgot Password?");

		if (mode==MACRO.ORGANIZER)
		{

		}
		if (mode==MACRO.FACILITATOR)
		{

		}
		if (mode==MACRO.PARTICIPANT)
		{

		}

	}

	public void CreatePage(boolean[] mode) { 
		Shell shell = new Shell(getDisplay());
		shell.setLocation(200,100);
		Image icon = new Image(getDisplay(), "resources/eManager.png");
		shell.setText("eManager");
		shell.setImage(icon);
		Venuespace ws2 = null;
		ws2 = new Venuespace(shell, SWT.None, mode);
		ws2.pack();
		shell.pack();
		shell.open();
	}
}
