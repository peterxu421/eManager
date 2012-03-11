import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;


public class eP_actual_participants_add extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text text_eP_actual_participants_name;
	private Text text_eP_actual_participants_age;
	private Text text_eP_actual_participants_faculty;
	private Text text_eP_actual_participants_foodType;
	private Event event;
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public eP_actual_participants_add(Composite parent, int style, Table table_1, Event event) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		setLayout(null);
		this.event = event;

		Composite comp_eP_actual_participants_add = new Composite(this, SWT.NONE);
		comp_eP_actual_participants_add.setBounds(56, 10, 372, 290);
		comp_eP_actual_participants_add.setLayout(null);
		toolkit.adapt(comp_eP_actual_participants_add);
		toolkit.paintBordersFor(comp_eP_actual_participants_add);

		Label lbl_eP_actual_participants_task = new Label(comp_eP_actual_participants_add, SWT.NONE);
		lbl_eP_actual_participants_task.setAlignment(SWT.RIGHT);
		lbl_eP_actual_participants_task.setBounds(54, 34, 54, 17);
		toolkit.adapt(lbl_eP_actual_participants_task, true, true);
		lbl_eP_actual_participants_task.setText("Name");

		Label lbl_eP_actual_participants_age = new Label(comp_eP_actual_participants_add, SWT.NONE);
		lbl_eP_actual_participants_age.setAlignment(SWT.RIGHT);
		lbl_eP_actual_participants_age.setBounds(36, 68, 72, 17);
		toolkit.adapt(lbl_eP_actual_participants_age, true, true);
		lbl_eP_actual_participants_age.setText("Year");

		Label lbl_eP_actual_participants_faculty = new Label(comp_eP_actual_participants_add, SWT.NONE);
		lbl_eP_actual_participants_faculty.setAlignment(SWT.RIGHT);
		lbl_eP_actual_participants_faculty.setBounds(35, 106, 73, 17);
		toolkit.adapt(lbl_eP_actual_participants_faculty, true, true);
		lbl_eP_actual_participants_faculty.setText("Faculty");

		Label lbl_eP_actual_participants_foodType = new Label(comp_eP_actual_participants_add, SWT.NONE);
		lbl_eP_actual_participants_foodType.setAlignment(SWT.RIGHT);
		lbl_eP_actual_participants_foodType.setBounds(35, 143, 73, 17);
		toolkit.adapt(lbl_eP_actual_participants_foodType, true, true);
		lbl_eP_actual_participants_foodType.setText("Food Type");

		text_eP_actual_participants_name = new Text(comp_eP_actual_participants_add, SWT.BORDER);
		text_eP_actual_participants_name.setBounds(152, 31, 125, 23);
		toolkit.adapt(text_eP_actual_participants_name, true, true);

		text_eP_actual_participants_age = new Text(comp_eP_actual_participants_add, SWT.BORDER);
		text_eP_actual_participants_age.setBounds(153, 65, 125, 23);
		toolkit.adapt(text_eP_actual_participants_age, true, true);

		text_eP_actual_participants_faculty = new Text(comp_eP_actual_participants_add, SWT.BORDER);
		text_eP_actual_participants_faculty.setBounds(152, 103, 125, 23);
		toolkit.adapt(text_eP_actual_participants_faculty, true, true);

		text_eP_actual_participants_foodType = new Text(comp_eP_actual_participants_add, SWT.BORDER);
		text_eP_actual_participants_foodType.setBounds(152, 140, 125, 23);
		toolkit.adapt(text_eP_actual_participants_foodType, true, true);

		Button btn_eP_actual_participants_add = new Button(comp_eP_actual_participants_add, SWT.NONE);
		btn_eP_actual_participants_add.addSelectionListener(new ParticipantsAddNewItem(table_1));
		btn_eP_actual_participants_add.setBounds(87, 199, 66, 27);
		toolkit.adapt(btn_eP_actual_participants_add, true, true);
		btn_eP_actual_participants_add.setText("Add");

		Button btn_eP_actual_participants_cancel = new Button(comp_eP_actual_participants_add, SWT.NONE);
		btn_eP_actual_participants_cancel.addSelectionListener(new ParticipantsCancel());
		btn_eP_actual_participants_cancel.setBounds(211, 199, 66, 27);
		toolkit.adapt(btn_eP_actual_participants_cancel, true, true);
		btn_eP_actual_participants_cancel.setText("Cancel");

	}

	public class ParticipantsAddNewItem extends SelectionAdapter {
		Table table_1;

		public ParticipantsAddNewItem(Table table_1) {
			this.table_1 = table_1;
		}

		public void widgetSelected(SelectionEvent e) {
			String[] participantArray = new String[5];
			if (!text_eP_actual_participants_name.getText().isEmpty()) {
				participantArray[0] = text_eP_actual_participants_name.getText();
			}
			if (!text_eP_actual_participants_age.getText().isEmpty()) {
				participantArray[1] = text_eP_actual_participants_age.getText();
			}
			if (!text_eP_actual_participants_faculty.getText().isEmpty()) {
				participantArray[2] = text_eP_actual_participants_faculty.getText();
			}
			if (!text_eP_actual_participants_foodType.getText().isEmpty()) {
				participantArray[3] = text_eP_actual_participants_foodType.getText();
			}

			if (!text_eP_actual_participants_name.getText().isEmpty() && !text_eP_actual_participants_age.getText().isEmpty()
					&& !text_eP_actual_participants_faculty.getText().isEmpty()
					&& !text_eP_actual_participants_foodType.getText().isEmpty()) {
				TableItem item = new TableItem(table_1, SWT.NULL);
				DatabaseReader db = new DatabaseReader();
				Participant participant = new Participant(participantArray[0],Integer.parseInt(participantArray[1]),participantArray[2],participantArray[3]);
				db.insertParticipant(event, participant);
				for (int i = 0; i < 4; i++) {
					item.setText(i, participantArray[i]);
				}
				getParent().dispose();
			}
		}
	}
	public class ParticipantsCancel extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
