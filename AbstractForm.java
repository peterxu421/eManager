import java.util.Calendar;
import java.util.HashMap;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
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

	// Constructor
	public AbstractForm(Composite parent, int style, String[] labelList,
			int[] signature) {
		super(parent, style);

		this.labelList = labelList;
		this.signature = signature;
		this.db = new DatabaseReader();
		this.map = new HashMap<String, Object>();

		/* Layout */
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginLeft = 20;
		gridLayout.marginRight = 20;
		gridLayout.marginTop = 20;
		gridLayout.marginBottom = 20;
		gridLayout.marginWidth = 20;
		gridLayout.marginHeight = 10;
		gridLayout.numColumns = 2;
		this.setLayout(gridLayout);

		/* Content */
		for (int i = 0; i < labelList.length; i++) {
			Label label = new Label(this, SWT.None);
			label.setText(labelList[i]);
			map.put(labelList[i], createInput(this, signature[i]));
		}
		/*
		 * Buttons
		 */
		onLoad();
	}

	// Another type of constructor.
	public AbstractForm(Composite parent, int style, String[] labelList,
			int[] signature, int[] sizeList) {
		super(parent, style);
		// use this constructor when size of each box is specified
	}

	// Set the input format given the key: labelList.
	private Object createInput(Composite parent, int signature) {
		Object input = null;
		if (signature == MACRO.TEXT || signature == MACRO.INT) {
			input = new Text(parent, SWT.NONE);
		} else if (signature == MACRO.DATE) {
			input = new CalendarCombo(this, SWT.None);
			((CalendarCombo) input).setDate(Calendar.getInstance());
		} else if (signature == MACRO.TIME) {
			input = new DateTime(parent, SWT.TIME);
		} else if (signature == MACRO.CHECK) {
			input = new Button(parent, SWT.CHECK);
		}
		return input;
	}

	// Get Object.
	protected Object get(int i) {
		return map.get(labelList[i]);
	}

	// Pre-condition: there is no error for the input data.
	// Cast all kinds of data type to string and return an array of string.
	protected String[] getStringList() {
		String[] stringList = new String[signature.length];
		for (int i = 0; i < signature.length; i++) {
			if (signature[i] == MACRO.TEXT || signature[i] == MACRO.INT) {
				stringList[i] = ((Text) get(i)).getText();
			} else if (signature[i] == MACRO.DATE) {
				stringList[i] = ((CalendarCombo) get(i)).getDateAsString();
			} else if (signature[i] == MACRO.TIME) {
				DateTime tempTime = (DateTime) get(i);
				Time time = new Time(tempTime.getHours(),
						tempTime.getMinutes(), tempTime.getSeconds());
				stringList[i] = time.toString();
			} else if (signature[i] == MACRO.CHECK) {
				if (((Button) get(i)).getSelection())
					stringList[i] = "Done";
				else
					stringList[i] = "UnDone";
			}
		}
		return stringList;
	}

	// Error checking.
	protected boolean check() {
		boolean isValid = true;
		for (int i = 0; i < labelList.length; i++) {
			if (signature[i] == MACRO.TEXT) {
				Text text = (Text) map.get(labelList[i]);
				isValid = !text.getText().isEmpty();
			} else if (signature[i] == MACRO.INT) {
				Text text = (Text) map.get(labelList[i]);
				String tempInt = text.getText();
				// Catch the exception if string is not integer.
				try {
					Integer.parseInt(tempInt);
					isValid = true;
				} catch (NumberFormatException e) {
					isValid = false;
				}
			}
		}
		return isValid;
	}

	// When click submit.
	protected class SubmitHandler extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (check()) {
				onSubmit();
				getParent().dispose();
			} else {
				// Do something i.e.
				// Dialog dialog = new Dialog()
				// custom title, error icon
				/*
				 * JOptionPane.showMessageDialog(frame,
				 * "Eggs are not supposed to be green.", "Inane error",
				 * JOptionPane.ERROR_MESSAGE);
				 */
			}

		}
	}

	// When click cancel.
	protected class CancelHandler extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
