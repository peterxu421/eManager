import java.util.ArrayList;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionListener;
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
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;

public class AddEvent extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	ArrayList<Event> events;
	DatabaseReader databaseReader;
	List list;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AddEvent(Composite parent, int style) {
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
		FormData fd_list = new FormData();
		fd_list.left = new FormAttachment(0, 48);
		fd_list.top = new FormAttachment(label_1, 25);
		list.setLayoutData(fd_list);
		for(int i=0; i<events.size(); i++){
			list.add(events.get(i).getEventName());
		}
		toolkit.adapt(list, true, true);
		
		Button btnSelect = new Button(this, SWT.NONE);
		fd_list.right = new FormAttachment(btnSelect, -21);
		FormData fd_btnSelect = new FormData();
		btnSelect.setLayoutData(fd_btnSelect);
		toolkit.adapt(btnSelect, true, true);
		btnSelect.setText("Select Event");
		btnSelect.addSelectionListener(new SelectProjectHandler());
		
		List list_1 = new List(this, SWT.BORDER);
		fd_list.bottom = new FormAttachment(list_1, -6);
		FormData fd_list_1 = new FormData();
		fd_list_1.left = new FormAttachment(0, 48);
		fd_list_1.top = new FormAttachment(0, 229);
		fd_list_1.bottom = new FormAttachment(0, 254);
		list_1.setLayoutData(fd_list_1);
		toolkit.adapt(list_1, true, true);
		
		Button btnCancel = new Button(this, SWT.NONE);
		fd_btnSelect.bottom = new FormAttachment(btnCancel, -43);
		fd_btnSelect.left = new FormAttachment(btnCancel, 0, SWT.LEFT);
		fd_list_1.right = new FormAttachment(btnCancel, -21);
		FormData fd_btnCancel = new FormData();
		fd_btnCancel.top = new FormAttachment(list_1, -1, SWT.TOP);
		fd_btnCancel.left = new FormAttachment(0, 236);
		fd_btnCancel.right = new FormAttachment(0, 319);
		btnCancel.setLayoutData(fd_btnCancel);
		toolkit.adapt(btnCancel, true, true);
		btnCancel.setText("Cancel");
		btnCancel.addSelectionListener(new Cancel());
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.right = new FormAttachment(list, 0, SWT.RIGHT);
		fd_lblNewLabel.bottom = new FormAttachment(label, -5);
		fd_lblNewLabel.left = new FormAttachment(0, 48);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		toolkit.adapt(lblNewLabel, true, true);
		lblNewLabel.setText("Welcome to Event Planner!");
		
		Label lblSelectProject = new Label(this, SWT.NONE);
		lblSelectProject.setAlignment(SWT.CENTER);
		FormData fd_lblSelectProject = new FormData();
		fd_lblSelectProject.right = new FormAttachment(btnCreateNewProject, 0, SWT.RIGHT);
		fd_lblSelectProject.top = new FormAttachment(label_1, 2);
		fd_lblSelectProject.left = new FormAttachment(btnCreateNewProject, 0, SWT.LEFT);
		lblSelectProject.setLayoutData(fd_lblSelectProject);
		toolkit.adapt(lblSelectProject, true, true);
		lblSelectProject.setText("Select Event");
		
		Button btnDeleteEvent = new Button(this, SWT.NONE);
		btnDeleteEvent.addSelectionListener(new DeleteProjectHandler());
		FormData fd_btnDeleteEvent = new FormData();
		fd_btnDeleteEvent.top = new FormAttachment(btnSelect, 6);
		fd_btnDeleteEvent.left = new FormAttachment(btnSelect, 0, SWT.LEFT);
		btnDeleteEvent.setLayoutData(fd_btnDeleteEvent);
		toolkit.adapt(btnDeleteEvent, true, true);
		btnDeleteEvent.setText("Delete Event");
	}
	class Cancel extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
	class DeleteProjectHandler extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e){
			int index=list.getSelectionIndex();
			list.remove(index);
			databaseReader.deleteEvent(events.get(index));
		}
	}
	class SelectProjectHandler extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Shell shell = new Shell(getDisplay());
			shell.setLocation(200,100);
			int index=list.getSelectionIndex();
			if(index!=-1){
				Event existEvent=events.get(index);
				Workspace workspace = new Workspace(shell, SWT.None, existEvent);
				workspace.pack();
				shell.pack();
				shell.open();
				getParent().dispose();
			}	
		}
	}
	class AddEventHandler extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Shell  add_newEvent_shell = new Shell(getDisplay(),SWT.NO_TRIM|SWT.ON_TOP);
			add_newEvent_shell.setLocation(400, 300);
			CreatNewEventPage add_newEvent_page = new CreatNewEventPage(add_newEvent_shell, SWT.None);
			add_newEvent_page.pack();
			add_newEvent_shell.pack();
			add_newEvent_shell.open();
		}
	}
	public class CreatNewEventPage extends Composite {

		private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
		private Text text;
		private Text text_1;

		/**
		 * Create the composite.
		 * @param parent
		 * @param style
		 */
		public CreatNewEventPage(Composite parent, int style) {
			super(parent, style);
			addDisposeListener(new DisposeListener() {
				public void widgetDisposed(DisposeEvent e) {
					toolkit.dispose();
				}
			});
			toolkit.adapt(this);
			toolkit.paintBordersFor(this);
			
			Composite composite = new Composite(this, SWT.NONE);
			composite.setBounds(38, 25, 233, 159);
			toolkit.adapt(composite);
			toolkit.paintBordersFor(composite);
			
			Label lblNewLabel = new Label(composite, SWT.NONE);
			lblNewLabel.setBounds(10, 24, 72, 14);
			toolkit.adapt(lblNewLabel, true, true);
			lblNewLabel.setText("Event Name:");
			
			text = new Text(composite, SWT.BORDER);
			text.setBounds(88, 21, 93, 19);
			toolkit.adapt(text, true, true);
			
			Label lblDescription = new Label(composite, SWT.NONE);
			lblDescription.setBounds(10, 78, 72, 14);
			toolkit.adapt(lblDescription, true, true);
			lblDescription.setText("Description:");
			
			text_1 = new Text(composite, SWT.BORDER);
			text_1.setBounds(88, 78, 93, 19);
			toolkit.adapt(text_1, true, true);
			
			Button btnCreateEvent = new Button(composite, SWT.NONE);
			btnCreateEvent.setBounds(10, 121, 94, 28);
			toolkit.adapt(btnCreateEvent, true, true);
			btnCreateEvent.setText("Create Event");
			btnCreateEvent.addSelectionListener(new CreateEventHandler());
			
			Button btnCancel = new Button(composite, SWT.NONE);
			btnCancel.setBounds(133, 121, 94, 28);
			toolkit.adapt(btnCancel, true, true);
			btnCancel.setText("Cancel");
			btnCancel.addSelectionListener(new SelectionAdapter(){
				public void widgetSelected(SelectionEvent e){
					getParent().dispose();
				}
			});
		}
		class CreateEventHandler extends SelectionAdapter {
			public void widgetSelected(SelectionEvent e) {
				Shell shell = new Shell(getDisplay());
				shell.setLocation(100,150);
				Event newEvent = new Event(text.getText(), text_1.getText());
				DatabaseReader dbReader = new DatabaseReader();
				dbReader.insertEvent(newEvent);
				Workspace workspace = new Workspace(shell, SWT.None, newEvent);
				workspace.pack();
				shell.pack();
				shell.open();
				getParent().dispose();
			}
		}
	}
}
