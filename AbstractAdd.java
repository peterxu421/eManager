import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

//Abstract Add page inherits from AbstractForm
public abstract class AbstractAdd extends AbstractForm {

	public AbstractAdd(Composite parent, int style, String[] stringList,
			int[] signature) {
		// Inherit AbstractForm constructor.
		super(parent, style, stringList, signature);
		// TODO Auto-generated constructor stub
		// Set buttons
		btnAdd = new Button(this, SWT.None);
		btnAdd.addSelectionListener(new SubmitHandler());
		btnAdd.setText("Add");
		btnAdd.setLayoutData(new GridData(60, 30));

		Button btnCancel = new Button(this, SWT.None);
		btnCancel.addSelectionListener(new CancelHandler());
		btnCancel.setText("Cancel");
		btnCancel.setLayoutData(new GridData(60, 30));
	}

	// Do nothing about the onLoad();
	public void onLoad() {

	}
}
