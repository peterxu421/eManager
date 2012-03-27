import java.util.Calendar;
import java.util.HashMap;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

public abstract class AbstractForm extends Composite {

	protected String[] labelList;
	protected int[] signature;
	protected int[] sizeList;
	protected String[] facultyArray = { "Arts and Social Sciences", "Business",
			"Computing", "Dentistry", "Design and Environment", "Engineering",
			"Law", "Medicine", "Music", "Science" };
	protected DatabaseReader db;
	protected String[] organizerArray;
	protected String[] facilitatorArray;
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
		// Update member name array
		int organizerSize = db.getOrganizers(SessionManager.getCurrentEvent())
				.size();
		organizerArray = new String[organizerSize];
		for (int i = 0; i < organizerSize; i++) {
			organizerArray[i] = db
					.getOrganizers(SessionManager.getCurrentEvent()).get(i)
					.getName();
		}
		// Update facilitator name array
		int facilitatorSize = db.getFacilitators(
				SessionManager.getCurrentEvent()).size();
		facilitatorArray = new String[facilitatorSize];
		for (int i = 0; i < facilitatorSize; i++) {
			facilitatorArray[i] = db
					.getFacilitators(SessionManager.getCurrentEvent()).get(i)
					.getName();
		}
		/* Layout */
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginLeft = 20;
		gridLayout.marginRight = 20;
		gridLayout.marginTop = 20;
		gridLayout.marginBottom = 20;
		gridLayout.marginWidth = 20;
		gridLayout.marginHeight = 10;
		gridLayout.numColumns = 2;
		gridLayout.verticalSpacing = 15;
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
		// Deal with Text, int and double
		if (signature == MACRO.TEXT || signature == MACRO.INT
				|| signature == MACRO.DOUBLE) {
			input = new Text(parent, SWT.BORDER);
			((Text) input).setLayoutData(new GridData(120, 20));
		}
		// Deal with Date
		else if (signature == MACRO.DATE) {
			input = new CalendarCombo(this, SWT.READ_ONLY);
			((CalendarCombo) input).setDate(Calendar.getInstance());
			((CalendarCombo) input).setLayoutData(new GridData(120, 30));
		}
		// Deal with Time
		else if (signature == MACRO.TIME) {
			input = new DateTime(parent, SWT.TIME);
			((DateTime) input).setLayoutData(new GridData(120, 30));
		}
		// Deal with CheckBox
		else if (signature == MACRO.CHECK) {
			input = new Button(parent, SWT.CHECK);
		}
		// Deal with bigger Text
		else if (signature == MACRO.TEXTBIG) {
			input = new Text(parent, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL
					| SWT.MULTI);
			((Text) input).setLayoutData(new GridData(120, 50));
		}
		// Make a drop down list for names, faculties, etc.
		// Deal with Faculties
		else if (signature == MACRO.FACULTY) {
			input = new Combo(parent, SWT.READ_ONLY);
			((Combo) input).setItems(facultyArray);
			((Combo) input).setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
					true, false, 1, 1));
		}
		// Deal with organizer names
		else if (signature == MACRO.ORGANIZER) {
			input = new Combo(parent, SWT.READ_ONLY);
			((Combo) input).setItems(organizerArray);
			((Combo) input).setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
					true, false, 1, 1));
		}
		// Deal with facilitator names
		else if (signature == MACRO.FACILITATOR) {
			input = new Combo(parent, SWT.READ_ONLY);
			((Combo) input).setItems(facilitatorArray);
			((Combo) input).setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
					true, false, 1, 1));
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
			// Deal with Text, BigText, int and double
			if (signature[i] == MACRO.TEXT || signature[i] == MACRO.INT
					|| signature[i] == MACRO.DOUBLE
					|| signature[i] == MACRO.TEXTBIG) {
				stringList[i] = ((Text) get(i)).getText();
			}
			// Deal with Date
			else if (signature[i] == MACRO.DATE) {
				stringList[i] = ((CalendarCombo) get(i)).getDateAsString();
			}
			// Deal with Time
			else if (signature[i] == MACRO.TIME) {
				DateTime tempTime = (DateTime) get(i);
				Time time = new Time(tempTime.getHours(),
						tempTime.getMinutes(), tempTime.getSeconds());
				stringList[i] = time.toString();
			}
			// Deal with CheckBox
			else if (signature[i] == MACRO.CHECK) {
				// If selected, change to true; else change to false.
				if (((Button) get(i)).getSelection())
					stringList[i] = "true";
				else
					stringList[i] = "false";
			}
			// Deal with names, positions, faculties, etc(Combos).
			else if (signature[i] == MACRO.FACULTY
					|| signature[i] == MACRO.ORGANIZER
					|| signature[i] == MACRO.FACILITATOR) {
				stringList[i] = ((Combo) get(i)).getItem(((Combo) get(i))
						.getSelectionIndex());
			}
		}
		return stringList;
	}

	// Error checking.
	// If there is no error, then return -1;
	// If there exist error, then return the index.
	protected int check() {
		boolean isValid = true;
		int index = -1;
		for (int i = 0; i < labelList.length; i++) {
			// Deal with Text and BigText
			if (signature[i] == MACRO.TEXT || signature[i] == MACRO.TEXTBIG) {
				Text text = (Text) map.get(labelList[i]);
				isValid = !text.getText().isEmpty();
			}
			// Deal with integer
			else if (signature[i] == MACRO.INT) {
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
			// Deal with double
			else if (signature[i] == MACRO.DOUBLE) {
				Text text = (Text) map.get(labelList[i]);
				String tempDouble = text.getText();
				// Catch the exception if string is not double.
				try {
					Double.parseDouble(tempDouble);
					isValid = true;
				} catch (NumberFormatException e) {
					isValid = false;
				}
			}
			index = i;
			// If isValid is false, return directly return false.
			if (!isValid) {
				return index;
			}
		}
		return -1;
	}

	// When click submit button.
	protected class SubmitHandler extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (check() == -1) {
				onSubmit();
				getParent().dispose();
			} else {
				// Show messageBox if there is error in input data and specify
				// where is the error.
				MessageBox warningPage = new MessageBox(getDisplay()
						.getActiveShell(), SWT.OK | SWT.ICON_WARNING);
				warningPage.setText("Warning!");
				warningPage.setMessage("There exists error in "
						+ labelList[check()] + ".");
				int choice = warningPage.open(); // indicates the user's choice
				switch (choice) {
				case SWT.OK:
					break;
				}
			}
		}
	}

	// When click cancel button.
	protected class CancelHandler extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
