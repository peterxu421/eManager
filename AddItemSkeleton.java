import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public abstract class AddItemSkeleton extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private ArrayList<String> stringList;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public AddItemSkeleton(Composite parent, int style,
			ArrayList<String> stringList) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		this.stringList = stringList;
		
		//Set the boundary
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginLeft = 20;
		gridLayout.marginRight=20;
		gridLayout.marginTop=20;
		gridLayout.marginBottom=20;
		gridLayout.marginWidth=20;
		gridLayout.marginHeight=10;
		gridLayout.numColumns = 2;
		this.setLayout(gridLayout);

		//Set the input
		Label label;
		for (int i = 0; i < stringList.size(); i++) {
			label = new Label(this, SWT.NONE);
			label.setText(stringList.get(i));
			toolkit.adapt(label, true, true);
			label.setLayoutData(new GridData(80, 20));

			ArrayList<Text> textList = new ArrayList <Text>();
			Text text=new Text(this, SWT.None);
			text.setLayoutData(new GridData(80, 20));
			textList.add(text);
			toolkit.adapt(text, true, true);
		}
		
		//Set buttons
		Button btnAdd=new Button(this, SWT.None);
		btnAdd.addSelectionListener(new AddNewItem());
		btnAdd.setText("Add");
		btnAdd.setLayoutData(new GridData(60,30));
		
		Button btnCancel=new Button(this, SWT.None);
		btnCancel.addSelectionListener(new CancelNewItem());
		btnCancel.setText("Cancel");
		btnCancel.setLayoutData(new GridData(60,30));
		
		class AddNewItem extends SelectionAdapter{
			public void widgetSelected(SelectionEvent e){
				DatabaseReader db = new DatabaseReader();
			}
		}
		
		class CancelNewItem extends SelectionAdapter{
			public void widgetSelected(SelectionEvent e){
				
			}
		}
		/*Composite compositeBtnAdd = new Composite(this, SWT.None);
		GridLayout gridLayoutBtnAdd = new GridLayout();
		compositeBtnAdd.setLayout(gridLayoutBtnAdd);
		gridLayoutBtnAdd.numColumns=1;
		gridLayoutBtnAdd.marginLeft=40;
		
		Button btnAdd=new Button(compositeBtnAdd, SWT.None);
		btnAdd.setText("Add");
		btnAdd.setLayoutData(new GridData(60,30));
		
		Composite compositeBtnCancel = new Composite(this, SWT.None);
		GridLayout gridLayoutBtnCancel = new GridLayout();
		compositeBtnCancel.setLayout(gridLayoutBtnCancel);
		
		Button btnCancel=new Button(compositeBtnCancel, SWT.None);
		btnCancel.setText("Cancel");
		btnCancel.setLayoutData(new GridData(60,30));*/
	}

	/*public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		ArrayList<String> stringList = new ArrayList<String>();
		stringList.add("a");
		stringList.add("b");
		stringList.add("c");
		AddItemSkeleton calc = new AddItemSkeleton(shell, SWT.NONE, stringList);
		calc.pack();
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}*/
}
