import java.util.ArrayList;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class SelectEventPage extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private ArrayList<Event> events;
	private DatabaseReader databaseReader;
	private List list;
	private Button btnSelect;
	
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public SelectEventPage(Composite parent, int style) {
		super(parent, SWT.NONE);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		FormLayout formLayout = new FormLayout();
		formLayout.marginBottom = 30;
		formLayout.marginRight = 30;
		setLayout(formLayout);

		databaseReader = new DatabaseReader();
		events = databaseReader.getEvents();

		Button btnCreateNewProject = new Button(this, SWT.NONE);
		FormData fd_btnCreateNewProject = new FormData();
		fd_btnCreateNewProject.top = new FormAttachment(0, 40);
		fd_btnCreateNewProject.left = new FormAttachment(0, 48);
		btnCreateNewProject.setLayoutData(fd_btnCreateNewProject);
		toolkit.adapt(btnCreateNewProject, true, true);
		btnCreateNewProject.setText("Create New Event");
		Label label = new Label(this, SWT.NONE);
		fd_btnCreateNewProject.right = new FormAttachment(label, 19);
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(0, 30);
		fd_label.left = new FormAttachment(0, 152);
		label.setLayoutData(fd_label);
		toolkit.adapt(label, true, true);
		Label label_1 = new Label(this, SWT.NONE);
		FormData fd_label_1 = new FormData();
		fd_label_1.top = new FormAttachment(0, 60);
		fd_label_1.left = new FormAttachment(0, 152);
		label_1.setLayoutData(fd_label_1);
		toolkit.adapt(label_1, true, true);
		btnCreateNewProject.addSelectionListener(new AddEventHandler());

		list = new List(this, SWT.BORDER);
		list.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				btnSelect.notifyListeners(SWT.Selection, null);
			}
		});

		FormData fd_list = new FormData();
		fd_list.bottom = new FormAttachment(100);
		fd_list.left = new FormAttachment(0, 48);
		list.setLayoutData(fd_list);
		for (int i = 0; i < events.size(); i++) {
			list.add(events.get(i).getEventName());
		}
		toolkit.adapt(list, true, true);

		btnSelect = new Button(this, SWT.NONE);
		fd_list.right = new FormAttachment(100, -205);
		FormData fd_btnSelect = new FormData();
		fd_btnSelect.right = new FormAttachment(100, -101);
		fd_btnSelect.left = new FormAttachment(list, 21);
		btnSelect.setLayoutData(fd_btnSelect);
		toolkit.adapt(btnSelect, true, true);
		btnSelect.setText("Select");
		btnSelect.addSelectionListener(new SelectProjectHandler());

		Button btnCancel = new Button(this, SWT.NONE);
		FormData fd_btnCancel = new FormData();
		fd_btnCancel.right = new FormAttachment(btnSelect, 0, SWT.RIGHT);
		fd_btnCancel.left = new FormAttachment(btnSelect, 0, SWT.LEFT);
		btnCancel.setLayoutData(fd_btnCancel);
		toolkit.adapt(btnCancel, true, true);
		btnCancel.setText("Cancel");
		btnCancel.addSelectionListener(new Cancel());

		Label lblNewLabel = new Label(this, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.right = new FormAttachment(100, -205);
		fd_lblNewLabel.left = new FormAttachment(0, 48);
		fd_lblNewLabel.bottom = new FormAttachment(label, -5);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		toolkit.adapt(lblNewLabel, true, true);
		lblNewLabel.setText("Welcome to Event Planner!");

		Label lblSelectProject = new Label(this, SWT.NONE);
		fd_list.top = new FormAttachment(lblSelectProject, 8);
		FormData fd_lblSelectProject = new FormData();
		fd_lblSelectProject.right = new FormAttachment(btnCreateNewProject, 0,
				SWT.RIGHT);
		fd_lblSelectProject.top = new FormAttachment(label_1, 2);
		fd_lblSelectProject.left = new FormAttachment(btnCreateNewProject, 0,
				SWT.LEFT);
		lblSelectProject.setLayoutData(fd_lblSelectProject);
		toolkit.adapt(lblSelectProject, true, true);
		lblSelectProject.setText("Select Event");

		Button btnDeleteEvent = new Button(this, SWT.NONE);
		fd_btnCancel.top = new FormAttachment(btnDeleteEvent, 6);
		fd_btnSelect.bottom = new FormAttachment(btnDeleteEvent, -6);
		btnDeleteEvent.addSelectionListener(new DeleteProjectHandler());
		FormData fd_btnDeleteEvent = new FormData();
		fd_btnDeleteEvent.right = new FormAttachment(100, -101);
		fd_btnDeleteEvent.left = new FormAttachment(list, 21);
		fd_btnDeleteEvent.top = new FormAttachment(0, 191);
		btnDeleteEvent.setLayoutData(fd_btnDeleteEvent);
		toolkit.adapt(btnDeleteEvent, true, true);
		btnDeleteEvent.setText("Delete");
	}

	class Cancel extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}

	class DeleteProjectHandler extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			int index = list.getSelectionIndex();
			if (index >= 0 && index <= list.getItemCount()) {
				list.remove(index);
				databaseReader.deleteEvent(events.get(index));
			}
		}
	}

	class SelectProjectHandler extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Shell shell = new Shell(getDisplay());
			shell.setLocation(200, 100);
			int index = list.getSelectionIndex();
			if (index != -1) {
				Event existEvent = events.get(index);
				SessionManager.setCurrentEvent(existEvent);
				SelectModePage mode = new SelectModePage(shell, SWT.None);
				mode.pack();
				shell.pack();
				shell.open();
				getParent().dispose();
			}
		}
	}

	class AddEventHandler extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Shell add_newEvent_shell = new Shell(getDisplay(), SWT.NO_TRIM
					| SWT.ON_TOP);
			add_newEvent_shell.setLocation(400, 300);
			CreateNewEventPage add_newEvent_page = new CreateNewEventPage(
					add_newEvent_shell, SWT.None);
			add_newEvent_page.pack();
			add_newEvent_shell.pack();
			add_newEvent_shell.open();
		}
	}

	public void disposeShell() {
		getParent().dispose();
	}

	public class CreateNewEventPage extends Composite {

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
		public CreateNewEventPage(Composite parent, int style) {
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
				SessionManager.setCurrentEvent(newEvent);
				SessionManager.setCurrentMode(MACRO.ORGANIZER);
				Eventspace workspace = new Eventspace(shell, SWT.None, MACRO.ORGANIZER_MODE);
				workspace.pack();
				shell.pack();
				shell.open();
				getParent().dispose();
				getDisplay().dispose();

			}
		}
	}
}
