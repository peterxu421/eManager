import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

abstract class AbstractForm extends Composite {

	protected final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	protected String[] stringList;
	protected Text[] textList;
	protected DatabaseReader db;
	protected Button btnAdd;

	public abstract void onLoad();

	public abstract void onSubmit();

	public AbstractForm(Composite parent, int style, String[] stringList) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		this.stringList = stringList;
		this.db = new DatabaseReader();

		// Set the boundary
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginLeft = 20;
		gridLayout.marginRight = 20;
		gridLayout.marginTop = 20;
		gridLayout.marginBottom = 20;
		gridLayout.marginWidth = 20;
		gridLayout.marginHeight = 10;
		gridLayout.numColumns = 2;

		this.setLayout(gridLayout);

		// Set the input
		textList = new Text[stringList.length];
		Label label;
		for (int i = 0; i < stringList.length; i++) {
			label = new Label(this, SWT.NONE);
			setLabelText(label, stringList[i]);
			label.setLayoutData(new GridData(130, 20));

			Text text = new Text(this, SWT.BORDER);
			text.setLayoutData(new GridData(100, 20));
			textList[i] = text;
		}
		onLoad();

	}

	public void setLabelText(Label label, String string) {
		if (string.contains("Date")) {
			label.setText("Date (yyyy-mm-dd)");
		} else if (string.equals("Time")) {
			label.setText("Time (hh:mm:ss)");
		} else if (string.equals("Done")) {
			label.setText("Done (Done/Undone)");
		} else
			label.setText(string);
	}

	class SubmitNewItem extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			onSubmit();
			getParent().dispose();
		}
	}

	class CancelNewItem extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
