import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public abstract class AbstractEdit extends AbstractForm {

	public AbstractEdit(Composite parent, int style, String[] stringList) {
		super(parent, style, stringList);
		// TODO Auto-generated constructor stub
		// Set buttons
		btnAdd = new Button(this, SWT.None);
		btnAdd.addSelectionListener(new SubmitHandler());
		btnAdd.setText("Edit");
		btnAdd.setLayoutData(new GridData(60, 30));

		Button btnCancel = new Button(this, SWT.None);
		btnCancel.addSelectionListener(new CancelHandler());
		btnCancel.setText("Cancel");
		btnCancel.setLayoutData(new GridData(60, 30));
	}
}
