import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public abstract class AbstractAdd extends AbstractForm {

	public AbstractAdd(Composite parent, int style, String[] stringList) {
		super(parent, style, stringList);
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
	//do nothing about the onLoad();
	public void onLoad(){
		
	}
}
