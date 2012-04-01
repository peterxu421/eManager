import java.util.ArrayList;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;

public class SelectEventPage extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private ArrayList<Event> events;
	private DatabaseReader databaseReader;
	private List list;
	private Button btnSelect;
	private String[] stringArrayNames = { "Event Name", "Description",
			"Password for Manager", "Password for Facilitator" };
	private int[] signatureNames = { MACRO.TEXT, MACRO.TEXTBIG, MACRO.PASSWORD,
			MACRO.PASSWORD };
	Composite parent;

	public SelectEventPage(Composite parent, int style) {
		super(parent, SWT.NONE);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.parent = parent;

		databaseReader = new DatabaseReader();
		events = databaseReader.getEvents();
		setLayout(null);

		Button btnCreateNewProject = new Button(this, SWT.NONE);
		btnCreateNewProject.setBounds(48, 30, 130, 40);
		toolkit.adapt(btnCreateNewProject, true, true);
		btnCreateNewProject.setText("Create New Event");
		Label label = new Label(this, SWT.NONE);
		label.setBounds(152, 30, 0, 17);
		toolkit.adapt(label, true, true);
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setBounds(152, 60, 0, 17);
		toolkit.adapt(label_1, true, true);
		btnCreateNewProject.addSelectionListener(new AddEventHandler());

		list = new List(this, SWT.BORDER | SWT.V_SCROLL);
		list.setBounds(48, 104, 283, 249);
		list.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				btnSelect.notifyListeners(SWT.Selection, null);
			}
		});
		for (int i = 0; i < events.size(); i++) {
			list.add(events.get(i).getEventName());
		}
		toolkit.adapt(list, true, true);

		btnSelect = new Button(this, SWT.NONE);
		btnSelect.setBounds(338, 162, 109, 40);
		toolkit.adapt(btnSelect, true, true);
		btnSelect.setText("Select");
		btnSelect.addSelectionListener(new SelectProjectHandler());

		Button btnCancel = new Button(this, SWT.NONE);
		btnCancel.setBounds(338, 254, 109, 40);
		toolkit.adapt(btnCancel, true, true);
		btnCancel.setText("Cancel");
		btnCancel.addSelectionListener(new Cancel());

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(48, 8, 167, 17);
		toolkit.adapt(lblNewLabel, true, true);
		lblNewLabel.setText("Welcome to Event Planner!");

		Label lblSelectProject = new Label(this, SWT.NONE);
		lblSelectProject.setBounds(48, 79, 109, 40);
		toolkit.adapt(lblSelectProject, true, true);
		lblSelectProject.setText("Select Event");

		Button btnDeleteEvent = new Button(this, SWT.NONE);
		btnDeleteEvent.setBounds(338, 208, 109, 40);
		btnDeleteEvent.addSelectionListener(new DeleteProjectHandler());
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
			Shell shell = new Shell(getDisplay(),  SWT.NO_TRIM
					| SWT.ON_TOP);
			shell.setLocation(500,250);
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
			Shell add_newEvent_shell = new Shell(getShell(), SWT.NO_TRIM
					| SWT.ON_TOP);
			add_newEvent_shell.setLocation(getShell().getLocation());
			AbstractAdd add_newEvent_page = new AbstractAdd(add_newEvent_shell,
					SWT.None, stringArrayNames, signatureNames, new Table(
							getShell(), SWT.None)) {
				public void onSubmit() {
					Shell shellEvent = new Shell(getShell(),SWT.NO_TRIM
							| SWT.ON_TOP);
					shellEvent.setLocation(300, 200);

					String[] tempList = getStringList();
					Event newEvent = new Event(tempList[0], tempList[1],
							tempList[2], tempList[3]);
					db.insertEvent(newEvent);
					SessionManager.setCurrentEvent(newEvent);
					SessionManager.setCurrentMode(MACRO.ORGANIZER);
					Eventspace eventspace = new Eventspace(shellEvent, SWT.None,
							MACRO.ORGANIZER_MODE);
					System.out.println(tempList[3]);
					eventspace.pack();
					shellEvent.pack();
					shellEvent.open();
					parent.dispose();
				}
			};
			add_newEvent_page.setSize(getShell().getSize());
			add_newEvent_shell.pack();
			add_newEvent_shell.open();
		}
	}

	public void disposeShell() {
		getParent().dispose();
	}
}
