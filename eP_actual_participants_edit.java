import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;


public class eP_actual_participants_edit extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text text_eP_actual_participants_name;
	private Text text_eP_actual_participants_age;
	private Text text_eP_actual_participants_faculty;
	private Text text_eP_actual_participants_foodType;
	private Participant participant;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public eP_actual_participants_edit(Composite parent, int style, Table table, int index, Participant participant) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.participant=participant;
		
		Composite comp_eP_actual_participants_edit = new Composite(this, SWT.NONE);
		comp_eP_actual_participants_edit.setLayout(null);
		comp_eP_actual_participants_edit.setBounds(34, 10, 372, 290);
		toolkit.adapt(comp_eP_actual_participants_edit);
		toolkit.paintBordersFor(comp_eP_actual_participants_edit);
		
		Label lbl_eP_actual_participants_name = new Label(comp_eP_actual_participants_edit, SWT.NONE);
		lbl_eP_actual_participants_name.setAlignment(SWT.RIGHT);
		lbl_eP_actual_participants_name.setText("Name");
		lbl_eP_actual_participants_name.setBounds(51, 34, 54, 17);
		toolkit.adapt(lbl_eP_actual_participants_name, true, true);
		
		Label lbl_eP_actual_participants_faculty = new Label(comp_eP_actual_participants_edit, SWT.NONE);
		lbl_eP_actual_participants_faculty.setAlignment(SWT.RIGHT);
		lbl_eP_actual_participants_faculty.setText("Year");
		lbl_eP_actual_participants_faculty.setBounds(33, 72, 72, 17);
		toolkit.adapt(lbl_eP_actual_participants_faculty, true, true);
		
		Label lbl_eP_actual_participants_date = new Label(comp_eP_actual_participants_edit, SWT.NONE);
		lbl_eP_actual_participants_date.setAlignment(SWT.RIGHT);
		lbl_eP_actual_participants_date.setText("Faculty");
		lbl_eP_actual_participants_date.setBounds(51, 111, 54, 17);
		toolkit.adapt(lbl_eP_actual_participants_date, true, true);
		
		Label lbl_eP_actual_participants_foodType = new Label(comp_eP_actual_participants_edit, SWT.NONE);
		lbl_eP_actual_participants_foodType.setAlignment(SWT.RIGHT);
		lbl_eP_actual_participants_foodType.setText("Food Type");
		lbl_eP_actual_participants_foodType.setBounds(35, 150, 70, 17);
		toolkit.adapt(lbl_eP_actual_participants_foodType, true, true);
		
		text_eP_actual_participants_name = new Text(comp_eP_actual_participants_edit, SWT.BORDER);
		text_eP_actual_participants_name.setBounds(152, 31, 125, 23);
		toolkit.adapt(text_eP_actual_participants_name, true, true);
		text_eP_actual_participants_name.setText(table.getItem(index).getText(0));
		
		text_eP_actual_participants_age = new Text(comp_eP_actual_participants_edit, SWT.BORDER);
		text_eP_actual_participants_age.setBounds(152, 69, 125, 23);
		toolkit.adapt(text_eP_actual_participants_age, true, true);
		text_eP_actual_participants_age.setText(table.getItem(index).getText(1));
		
		text_eP_actual_participants_faculty = new Text(comp_eP_actual_participants_edit, SWT.BORDER);
		text_eP_actual_participants_faculty.setBounds(152, 108, 125, 23);
		toolkit.adapt(text_eP_actual_participants_faculty, true, true);
		text_eP_actual_participants_faculty.setText(table.getItem(index).getText(2));
		
		text_eP_actual_participants_foodType = new Text(comp_eP_actual_participants_edit, SWT.BORDER);
		text_eP_actual_participants_foodType.setBounds(152, 147, 125, 23);
		toolkit.adapt(text_eP_actual_participants_foodType, true, true);
		text_eP_actual_participants_foodType.setText(table.getItem(index).getText(3));
		
		Button btn_eP_actual_participants_edit = new Button(comp_eP_actual_participants_edit, SWT.NONE);
		btn_eP_actual_participants_edit.addSelectionListener(new ParticipantsEditOldItem(table, index));
		btn_eP_actual_participants_edit.setText("Edit");
		btn_eP_actual_participants_edit.setBounds(87, 203, 66, 27);
		toolkit.adapt(btn_eP_actual_participants_edit, true, true);
		
		Button btn_eP_actual_participants_cancel = new Button(comp_eP_actual_participants_edit, SWT.NONE);
		btn_eP_actual_participants_cancel.addSelectionListener(new ParticipantsCancel2());
		btn_eP_actual_participants_cancel.setText("Cancel");
		btn_eP_actual_participants_cancel.setBounds(211, 203, 66, 27);
		toolkit.adapt(btn_eP_actual_participants_cancel, true, true);

	}
	
	public class ParticipantsEditOldItem extends SelectionAdapter{
		Table table;
		int index;

		public ParticipantsEditOldItem(Table table, int index) {
			this.table = table;
			this.index=index;
		}

		public void widgetSelected(SelectionEvent e) {
			String[] participantArray = new String[5];
			if (!text_eP_actual_participants_name.getText().isEmpty()) {
				participantArray[0] = text_eP_actual_participants_name.getText();
				table.getItem(index).setText(0, participantArray[0]);
			}
			if (!text_eP_actual_participants_age.getText().isEmpty()) {
				participantArray[1] = text_eP_actual_participants_age.getText();
				table.getItem(index).setText(1, participantArray[1]);
			}
			if (!text_eP_actual_participants_faculty.getText().isEmpty()) {
				participantArray[2] = text_eP_actual_participants_faculty.getText();
				table.getItem(index).setText(2, participantArray[2]);
			}
			if (!text_eP_actual_participants_foodType.getText().isEmpty()) {
				participantArray[3] = text_eP_actual_participants_foodType.getText();
				table.getItem(index).setText(3, participantArray[3]);
			}
			if (!text_eP_actual_participants_name.getText().isEmpty() && !text_eP_actual_participants_age.getText().isEmpty()
					&& !text_eP_actual_participants_faculty.getText().isEmpty()
					&& !text_eP_actual_participants_foodType.getText().isEmpty()) {
				participant.setName(participantArray[0]);
				participant.setYear(Integer.parseInt(participantArray[1]));
				participant.setFaculty(participantArray[2]);
				participant.setFoodType(participantArray[3]);
				DatabaseReader db = new DatabaseReader();
				db.updateParticipant(participant);
				getParent().dispose();
			}
		}
	}
	
	public class ParticipantsCancel2 extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
