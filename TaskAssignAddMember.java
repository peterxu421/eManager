import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class TaskAssignAddMember extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text textName;
	private Text textYear;
	private Text textFaculty;
	private Text textPosition;
	private Event event;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public TaskAssignAddMember(Composite parent, int style, Table table_2, Event event) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.event =event;

		Composite compositeCommitteeAdd = new Composite(this, SWT.NONE);
		compositeCommitteeAdd.setLayout(null);
		compositeCommitteeAdd.setBounds(10, 10, 397, 313);
		toolkit.adapt(compositeCommitteeAdd);
		toolkit.paintBordersFor(compositeCommitteeAdd);

		Label lblName = new Label(compositeCommitteeAdd, SWT.NONE);
		lblName.setText("Name");
		lblName.setBounds(35, 34, 40, 17);
		toolkit.adapt(lblName, true, true);

		Label lblYear = new Label(compositeCommitteeAdd, SWT.NONE);
		lblYear.setText("Year");
		lblYear.setBounds(33, 77, 72, 17);
		toolkit.adapt(lblYear, true, true);

		Label lblFaculty = new Label(compositeCommitteeAdd, SWT.NONE);
		lblFaculty.setText("Faculty");
		lblFaculty.setBounds(35, 117, 54, 17);
		toolkit.adapt(lblFaculty, true, true);

		Label lblPosition = new Label(compositeCommitteeAdd, SWT.NONE);
		lblPosition.setText("Position");
		lblPosition.setBounds(35, 161, 54, 17);
		toolkit.adapt(lblPosition, true, true);
		
		textName = new Text(compositeCommitteeAdd, SWT.BORDER);
		textName.setBounds(152, 31, 125, 23);
		toolkit.adapt(textName, true, true);

		textYear = new Text(compositeCommitteeAdd, SWT.BORDER);
		textYear.setBounds(152, 74, 125, 23);
		toolkit.adapt(textYear, true, true);

		textFaculty = new Text(compositeCommitteeAdd, SWT.BORDER);
		textFaculty.setBounds(152, 114, 125, 23);
		toolkit.adapt(textFaculty, true, true);

		textPosition = new Text(compositeCommitteeAdd, SWT.BORDER);
		textPosition.setBounds(152, 158, 125, 23);
		toolkit.adapt(textPosition, true, true);
		
		Button btnAddMember = new Button(compositeCommitteeAdd, SWT.NONE);
		btnAddMember.addSelectionListener(new TaskAssignAddNewMember(table_2));
		btnAddMember.setText("Add Member");
		btnAddMember.setBounds(91, 205, 90, 27);
		toolkit.adapt(btnAddMember, true, true);

		Button buttonCancel = new Button(compositeCommitteeAdd, SWT.NONE);
		buttonCancel.addSelectionListener(new TaskAssignMemberCancel());
		buttonCancel.setText("Cancel");
		buttonCancel.setBounds(230, 205, 66, 27);
		toolkit.adapt(buttonCancel, true, true);

	}

	public class TaskAssignAddNewMember extends SelectionAdapter {
		Table table;

		public TaskAssignAddNewMember(Table table) {
			this.table = table;
		}

		public void widgetSelected(SelectionEvent e) {
			String[] memberArray = new String[4];
			if (!textName.getText().isEmpty()) {
				memberArray[0] = textName.getText();
			}
			if (!textYear.getText().isEmpty()) {
				memberArray[1] = textYear.getText();
			}
			if (!textFaculty.getText().isEmpty()) {
				memberArray[2] = textFaculty.getText();
			}
			if (!textPosition.getText().isEmpty()) {
				memberArray[3] = textPosition.getText();
			}
			if (!textName.getText().isEmpty() && !textYear.getText().isEmpty()
					&& !textFaculty.getText().isEmpty()
					&& !textPosition.getText().isEmpty()) {
				TableItem item = new TableItem(table, SWT.NULL);
				for (int i = 0; i < 4; i++) {
					item.setText(i, memberArray[i]);
				}
				DatabaseReader db = new DatabaseReader();
				Organizer organizer = new Organizer(memberArray[0],Integer.parseInt(memberArray[1]),memberArray[2],memberArray[3]);
				db.insertOrganizer(event, organizer);
				getParent().dispose();
			}
		}
	}

	public class TaskAssignMemberCancel extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}

}
