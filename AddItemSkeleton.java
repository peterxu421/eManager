import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;


public class AddItemSkeleton extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private ArrayList<String> stringList;
	private Table table;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AddItemSkeleton(Composite parent, int style, ArrayList<String> stringList, Table table) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		this.stringList=stringList;
		this.table=table;
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		this.setLayout(gridLayout);
    	
    	Label label;
		for(int i=0; i<stringList.size(); i++){
			label = new Label(parent, SWT.NONE);
			label.setText(stringList.get(i));
			toolkit.adapt(label, true, true);
			label.setLayoutData(new GridData(80,20));
		
			Text text = new Text(parent, SWT.BORDER);
			text.setLayoutData(new GridData(80,20));
			toolkit.adapt(text, true, true);
		}
	}
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		ArrayList<String> string = new ArrayList<String>();
		string.set(0, "a");
		string.set(1, "b");
		string.set(2, "c");
		AddItemSkeleton calc = new AddItemSkeleton(shell,
				SWT.NONE, string, new Table(shell, SWT.None));
		calc.pack();
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
}


