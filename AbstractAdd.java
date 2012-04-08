import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

//Abstract Add page inherits from AbstractForm
public abstract class AbstractAdd extends AbstractForm {

	protected Table table; // the SWT table where the information is stored

	public AbstractAdd(Composite parent, int style, String[] stringList,
			int[] signature, Table table) {
		// Inherit AbstractForm constructor.
		super(parent, style, stringList, signature);
		// TODO Auto-generated constructor stub

		// get the table with stored input information
		this.table = table;
		parent.getShell().setText("eManager - Add");
		//Set shell's location
		parent.getShell().setLocation(400, 200);
		
		// Set buttons
		Button btnAdd = new Button(this, SWT.None);
		btnAdd.addSelectionListener(new SubmitHandler());
		btnAdd.setText("Add");
		btnAdd.setLayoutData(new GridData(80, 40));

		Button btnCancel = new Button(this, SWT.None);
		btnCancel.addSelectionListener(new CancelHandler());
		btnCancel.setText("Cancel");
		btnCancel.setLayoutData(new GridData(80, 40));
	}

	// Do nothing about the onLoad();
	public void onLoad() {

	}

	// Override the additionalCheck();
	public boolean additionalCheck() {
		// check for duplicate input
		String inputStr = "";
		boolean noDuplicateInput = true;
		stringList = getStringList();
		for (int i = 0; i < stringList.length; i++) {
			inputStr += stringList[i];
		}
		inputStr = inputStr.replaceAll("\\s", ""); // remove while spaces and
													// non-visible characters
													// such as \n
		for (int i = 0; i < table.getItemCount(); i++) {
			TableItem item = table.getItem(i);
			String tableStr = "";
			for (int j = 0; j < table.getColumnCount(); j++) {
				tableStr += item.getText(j);
			}
			tableStr = tableStr.replaceAll("\\s", ""); // remove while spaces
														// and non-visible
														// characters such as \n
			if (inputStr.equalsIgnoreCase(tableStr)) {
				MessageBox warningPage = new MessageBox(getDisplay()
						.getActiveShell(), SWT.OK | SWT.ICON_WARNING);
				warningPage.setText("Warning!");
				warningPage.setMessage("Already exsits!");
				warningPage.open();
				noDuplicateInput = false;
				break;
			}
		}
		return noDuplicateInput;
	}
}
