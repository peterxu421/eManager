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
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class TaskAssignEditMember extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text textName;
	private Text textYear;
	private Text textFaculty;
	private Text textPosition;
	private Organizer member;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public TaskAssignEditMember(Composite parent, int style, Table table,
			int index, Organizer member) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.member = member;
		
		Composite compositeCommitteeEdit = new Composite(this, SWT.NONE);
		compositeCommitteeEdit.setLayout(null);
		compositeCommitteeEdit.setBounds(10, 10, 397, 313);
		toolkit.adapt(compositeCommitteeEdit);
		toolkit.paintBordersFor(compositeCommitteeEdit);

		Label lblName = new Label(compositeCommitteeEdit, SWT.NONE);
		lblName.setText("Name");
		lblName.setBounds(35, 34, 40, 17);
		toolkit.adapt(lblName, true, true);

		Label lblYear = new Label(compositeCommitteeEdit, SWT.NONE);
		lblYear.setText("Year");
		lblYear.setBounds(33, 77, 72, 17);
		toolkit.adapt(lblYear, true, true);

		Label lblFaculty = new Label(compositeCommitteeEdit, SWT.NONE);
		lblFaculty.setText("Faculty");
		lblFaculty.setBounds(35, 117, 54, 17);
		toolkit.adapt(lblFaculty, true, true);

		Label lblPosition = new Label(compositeCommitteeEdit, SWT.NONE);
		lblPosition.setText("Position");
		lblPosition.setBounds(35, 161, 54, 17);
		toolkit.adapt(lblPosition, true, true);

		textName = new Text(compositeCommitteeEdit, SWT.BORDER);
		textName.setBounds(152, 31, 125, 23);
		toolkit.adapt(textName, true, true);
		textName.setText(table.getItem(index).getText(0));

		textYear = new Text(compositeCommitteeEdit, SWT.BORDER);
		textYear.setBounds(152, 74, 125, 23);
		toolkit.adapt(textYear, true, true);
		textYear.setText(table.getItem(index).getText(0));

		textFaculty = new Text(compositeCommitteeEdit, SWT.BORDER);
		textFaculty.setBounds(152, 114, 125, 23);
		toolkit.adapt(textFaculty, true, true);
		textFaculty.setText(table.getItem(index).getText(0));

		textPosition = new Text(compositeCommitteeEdit, SWT.BORDER);
		textPosition.setBounds(152, 158, 125, 23);
		toolkit.adapt(textPosition, true, true);
		textPosition.setText(table.getItem(index).getText(0));

		Button btnEditMember = new Button(compositeCommitteeEdit, SWT.NONE);
		btnEditMember.addSelectionListener(new TaskAssignEditOldMember(table,
				index));
		btnEditMember.setText("Edit Member");
		btnEditMember.setBounds(91, 205, 90, 27);
		toolkit.adapt(btnEditMember, true, true);

		Button btnCancel = new Button(compositeCommitteeEdit, SWT.NONE);
		btnCancel.addSelectionListener(new TaskAssignCancel2());
		btnCancel.setText("Cancel");
		btnCancel.setBounds(230, 205, 66, 27);
		toolkit.adapt(btnCancel, true, true);
		
	}

	public class TaskAssignEditOldMember extends SelectionAdapter {
		Table table;
		int index;

		public TaskAssignEditOldMember(Table table, int index) {
			this.table = table;
			this.index = index;
		}

		public void widgetSelected(SelectionEvent e) {
			String[] memberArray = new String[5];
			if (!textName.getText().isEmpty()) {
				memberArray[0] = textName.getText();
				table.getItem(index).setText(0, memberArray[0]);
			}
			if (!textYear.getText().isEmpty()) {
				memberArray[1] = textYear.getText();
				table.getItem(index).setText(1, memberArray[1]);
			}
			if (!textFaculty.getText().isEmpty()) {
				memberArray[2] = textFaculty.getText();
				table.getItem(index).setText(2, memberArray[2]);
			}
			if (!textPosition.getText().isEmpty()) {
				memberArray[3] = textPosition.getText();
				table.getItem(index).setText(4, memberArray[4]);
			}
			if (!textName.getText().isEmpty() && !textYear.getText().isEmpty()
					&& !textFaculty.getText().isEmpty()
					&& !textPosition.getText().isEmpty()) {
				member.setName(memberArray[0]);
				member.setYear(Integer.parseInt(memberArray[1]));
				member.setFaculty(memberArray[2]);
				member.setPosition(memberArray[3]);
				DatabaseReader db = new DatabaseReader();
				db.updateOrganizer(member);
				getParent().dispose();
			}
		}
	}

	public class TaskAssignCancel2 extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
