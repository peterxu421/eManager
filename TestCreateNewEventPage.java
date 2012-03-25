import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class TestCreateNewEventPage extends Composite {

		private final FormToolkit toolkit = new FormToolkit(
				Display.getCurrent());
		private Text textEventName;
		private Text textDescription;
		
		private Text textUserManager;
		private Text textPasswordManager;
		private Text textPasswordHintManager;
		
		private Text textUsernameFacilitator;
		private Text textPasswordFacilitator;
		private Text textPasswordHintFacilitator;

		/**
		 * Create the composite.
		 * 
		 * @param parent
		 * @param style
		 */
		public TestCreateNewEventPage(Composite parent, int style) {
			super(parent, style);
			addDisposeListener(new DisposeListener() {
				public void widgetDisposed(DisposeEvent e) {
					toolkit.dispose();
				}
			});
			toolkit.adapt(this);
			toolkit.paintBordersFor(this);

			Composite composite = new Composite(this, SWT.NONE);
			composite.setBounds(10, 10, 241, 376);
			toolkit.adapt(composite);
			toolkit.paintBordersFor(composite);

			Label lblEventName = new Label(composite, SWT.NONE);
			lblEventName.setBounds(10, 24, 122, 14);
			toolkit.adapt(lblEventName, true, true);
			lblEventName.setText("Event Name:");

			textEventName = new Text(composite, SWT.BORDER);
			textEventName.setBounds(133, 16, 93, 19);
			toolkit.adapt(textEventName, true, true);

			Label lblDescription = new Label(composite, SWT.NONE);
			lblDescription.setBounds(10, 62, 122, 14);
			toolkit.adapt(lblDescription, true, true);
			lblDescription.setText("Description:");

			textDescription = new Text(composite, SWT.BORDER);
			textDescription.setBounds(133, 57, 93, 19);
			toolkit.adapt(textDescription, true, true);

			Button btnCreateEvent = new Button(composite, SWT.NONE);
			btnCreateEvent.setBounds(10, 338, 94, 28);
			toolkit.adapt(btnCreateEvent, true, true);
			btnCreateEvent.setText("Create Event");
			btnCreateEvent.addSelectionListener(new CreateEventHandler());

			Button btnCancel = new Button(composite, SWT.NONE);
			btnCancel.setBounds(133, 338, 94, 28);
			toolkit.adapt(btnCancel, true, true);
			btnCancel.setText("Cancel");
			
			Label lblUserManager = new Label(composite, SWT.NONE);
			lblUserManager.setText("Username(Manager):");
			lblUserManager.setBounds(10, 112, 122, 14);
			toolkit.adapt(lblUserManager, true, true);
			
			textUserManager = new Text(composite, SWT.BORDER);
			textUserManager.setBounds(133, 107, 93, 19);
			toolkit.adapt(textUserManager, true, true);
			
			Label lblPasswordManager = new Label(composite, SWT.NONE);
			lblPasswordManager.setText("Password(Manager):");
			lblPasswordManager.setBounds(10, 148, 122, 14);
			toolkit.adapt(lblPasswordManager, true, true);
			
			textPasswordManager = new Text(composite, SWT.BORDER);
			textPasswordManager.setBounds(133, 143, 93, 19);
			toolkit.adapt(textPasswordManager, true, true);
			
			Label lblUsernameFacilitator = new Label(composite, SWT.NONE);
			lblUsernameFacilitator.setText("Username(Facilitator):");
			lblUsernameFacilitator.setBounds(10, 233, 122, 14);
			toolkit.adapt(lblUsernameFacilitator, true, true);
			
			textUsernameFacilitator = new Text(composite, SWT.BORDER);
			textUsernameFacilitator.setBounds(133, 228, 93, 19);
			toolkit.adapt(textUsernameFacilitator, true, true);
			
			Label lblPasswordFacilitator = new Label(composite, SWT.NONE);
			lblPasswordFacilitator.setText("Password(Facilitator):");
			lblPasswordFacilitator.setBounds(10, 269, 122, 14);
			toolkit.adapt(lblPasswordFacilitator, true, true);
			
			textPasswordFacilitator = new Text(composite, SWT.BORDER);
			textPasswordFacilitator.setBounds(133, 264, 93, 19);
			toolkit.adapt(textPasswordFacilitator, true, true);
			
			Label label_2 = new Label(composite, SWT.NONE);
			label_2.setBounds(10, 87, 224, 14);
			toolkit.adapt(label_2, true, true);
			label_2.setText("----------------------------------");
			
			Label label = new Label(composite, SWT.NONE);
			label.setText("----------------------------------");
			label.setBounds(10, 208, 224, 14);
			toolkit.adapt(label, true, true);
			
			Label lblPassHintManager = new Label(composite, SWT.NONE);
			lblPassHintManager.setText("Password Hint:");
			lblPassHintManager.setBounds(10, 183, 122, 14);
			toolkit.adapt(lblPassHintManager, true, true);
			
			textPasswordHintManager = new Text(composite, SWT.BORDER);
			textPasswordHintManager.setBounds(133, 178, 93, 19);
			toolkit.adapt(textPasswordHintManager, true, true);
			
			Label lblPasswordHintFacilitator = new Label(composite, SWT.NONE);
			lblPasswordHintFacilitator.setText("Password Hint:");
			lblPasswordHintFacilitator.setBounds(10, 305, 122, 14);
			toolkit.adapt(lblPasswordHintFacilitator, true, true);
			
			textPasswordHintFacilitator = new Text(composite, SWT.BORDER);
			textPasswordHintFacilitator.setBounds(133, 300, 93, 19);
			toolkit.adapt(textPasswordHintFacilitator, true, true);
			btnCancel.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					getParent().dispose();
				}
			});
		}

		class CreateEventHandler extends SelectionAdapter {
			public void widgetSelected(SelectionEvent e) {
				Shell shell = new Shell(getDisplay());
				shell.setLocation(100, 150);
				Event newEvent = new Event(textEventName.getText(), textDescription.getText());
				DatabaseReader dbReader = new DatabaseReader();
				dbReader.insertEvent(newEvent);
				Eventspace workspace = new Eventspace(shell, SWT.None, newEvent);
				workspace.pack();
				shell.pack();
				shell.open();
				getParent().dispose();
				getDisplay().dispose();

			}
		}
	}