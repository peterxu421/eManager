import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public abstract class AbstractForm extends Composite {
	
	protected String[] labelList;
	protected int[] signature;
	protected int[] sizeList;
	protected DatabaseReader db;
	protected Button btnAdd;
	private HashMap<String, Object> map;
	
	public abstract void onLoad();
	public abstract void onSubmit();

	public AbstractForm(Composite parent, int style, String[] labelList, int[] signature){
		super(parent, style);
		
		this.labelList = labelList;
		this.signature = signature;
		this.db = new DatabaseReader();
		this.map = new HashMap<String, Object>();
		
		/*Layout*/
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginLeft = 20;
		gridLayout.marginRight = 20;
		gridLayout.marginTop = 20;
		gridLayout.marginBottom = 20;
		gridLayout.marginWidth = 20;
		gridLayout.marginHeight = 10;
		gridLayout.numColumns = 2;
		this.setLayout(gridLayout);

		/*Content*/
		for(int i=0; i<labelList.length; i++){
			Label label = new Label(this, SWT.None);
			label.setText(labelList[i]);
			map.put(labelList[i], createInput(this, signature[i]));
		}
		onLoad();
	}
	public AbstractForm(Composite parent, int style, String[] labelList, int[] signature, int[] sizeList){
		super(parent, style);
		//use this constructor when size of each box is specified
	}
	
	private Object createInput(Composite parent, int signature){
		Object input;
		if(signature == MACRO.TEXT){
			input = new Text(parent, SWT.NONE);
		}
		else if(signature == MACRO.DATE){
			input = new DateTime(parent, SWT.None);
		}
//		... and so on
		return input;
	}
	
	protected Object get(String label){
		return map.get(label);
	}
	private class SubmitHandler extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			onSubmit();
			getParent().dispose();
		}
	}

	private class CancelHandler extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
