import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.custom.ScrolledComposite;

public class eP_eventRegistration extends AbstractForm {

	Button btnClear;

	public eP_eventRegistration(Composite parent, int style,
			String[] stringArrayRegistration, int[] signatureArrayRegistration) {
		super(parent, style, stringArrayRegistration,
				signatureArrayRegistration);
		Button btnSubmit = new Button(this, SWT.None);
		btnSubmit.addSelectionListener(new SubmitHandler());
		btnSubmit.setText("Submit");
		GridData gridData = new GridData(80, 40);
		gridData.horizontalIndent = 25;
		btnSubmit.setLayoutData(gridData);

		btnClear = new Button(this, SWT.None);
		btnClear.addSelectionListener(new RegistrationClear());
		btnClear.setText("Clear");
		btnClear.setLayoutData(gridData);

	}

	public class RegistrationClear extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			setEmpty();
		}
	}

	@Override
	// Do not want the parent shell to be disposed.
	public boolean additionalRequirement() {
		return false;
	}

	@Override
	// Do nothing to onLoad()
	public void onLoad() {
	}

	@Override
	public void onSubmit() {
		String[] tempList = getStringList();
		if (tempList[5].equals("Participant")) {
			Participant participant = new Participant();
			participant.setName(tempList[0]);
			participant.setMatricNo(tempList[1]);
			participant.setYear(Integer.parseInt(tempList[2]));
			participant.setFaculty(tempList[3]);
			participant.setFoodType(tempList[6]);
			participant.setMatricNo(tempList[7]);
			db.insertParticipant(SessionManager.getCurrentEvent(), participant);
		} else {
			Facilitator facilitator = new Facilitator();
			facilitator.setName(tempList[0]);
			facilitator.setMatricNo(tempList[1]);
			facilitator.setYear(Integer.parseInt(tempList[2]));
			facilitator.setFaculty(tempList[3]);
			facilitator.setFoodType(tempList[6]);
			facilitator.setAllergy(tempList[7]);
			db.insertFacilitator(SessionManager.getCurrentEvent(), facilitator);
		}
		btnClear.notifyListeners(SWT.Selection, null);
	}

	@Override
	// Do nothing to additionalCheck
	public boolean additionalCheck() {
		return true;
	}
}
