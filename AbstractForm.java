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
	protected DatabaseReader db;
	protected String[] stringList; // to store input texts
	protected String[] organizerArray;
	protected String[] facilitatorArray;
	protected String[] rolesArray = { "Facilitator", "Participant" };
	protected String[] facultyArray = { "Arts and Social Sciences", "Business",
			"Computing", "Dentistry", "Design and Environment", "Engineering",
			"Law", "Medicine", "Music", "Science" };
	protected String[] venueTypeArray = { "Auditorium", "Lecture Theatre",
			"Tutorial Room", "Seminar Room", "Function Room", "Practice Room",
			"Discussion Room", "Music Studio", "Open area", "Sports Room",
			"Gym", "MPSH", "others" };
	protected String[] venueLocationArray = { "Arts and Social Sciences",
			"Business", "Computing", "Dentistry", "Design and Environment",
			"Engineering", "Law", "Medicine", "Music", "Science",
			"Central Library", "CFA", "PGP", "YIH", "SRC", "UCC", "Others" };

	private HashMap<String, Object> map;

	public abstract void onLoad();

	public abstract void onSubmit();

	public abstract boolean additionalCheck();

	// Constructor
	public AbstractForm(Composite parent, int style, String[] labelList,
			int[] signature) {
		super(parent, style);

		this.labelList = labelList;
		this.signature = signature;
		this.db = new DatabaseReader();
		this.map = new HashMap<String, Object>();
		stringList = new String[signature.length]; // initiate the input text
													// string array
		
		/* Layout */
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginLeft = 50;
		gridLayout.marginRight = gridLayout.marginLeft;
		gridLayout.marginTop = 20;
		gridLayout.marginBottom = gridLayout.marginTop;
		gridLayout.marginWidth = 20;
		gridLayout.marginHeight = 20;
		gridLayout.numColumns = 2;
		gridLayout.verticalSpacing = 20;
		this.setLayout(gridLayout);

		/* Content */
		for (int i = 0; i < labelList.length; i++) {
			Label label = new Label(this, SWT.None);
			label.setText(labelList[i]);
			label.setLayoutData(new GridData(140, 20));
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
			((Text) input).setLayoutData(new GridData(140, 20));
		}
		// Deal with read only text
		else if (signature == MACRO.READONLY) {
			input = new Text(parent, SWT.BORDER | SWT.READ_ONLY);
			((Text) input).setText(SessionManager.getCurrentEvent()
					.getEventName());
			((Text) input).setLayoutData(new GridData(140, 20));
		}
		// Deal with Date
		else if (signature == MACRO.DATE) {
			input = new CalendarCombo(this, SWT.READ_ONLY);
			((CalendarCombo) input).setDate(Calendar.getInstance());
			((CalendarCombo) input).setLayoutData(new GridData(140, 30));
		}
		// Deal with Time
		else if (signature == MACRO.TIME) {
			input = new DateTime(parent, SWT.TIME);
			((DateTime) input).setTime(0, 0, 0);
			((DateTime) input).setLayoutData(new GridData(140, 30));
		}
		// Deal with CheckBox
		else if (signature == MACRO.CHECK) {
			input = new Button(parent, SWT.CHECK);
		}
		// Deal with bigger Text
		else if (signature == MACRO.TEXTBIG) {
			input = new Text(parent, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL
					| SWT.MULTI);
			((Text) input).setLayoutData(new GridData(140, 50));
		}
		// Make a drop down list for names, faculties, etc.
		// Deal with Faculties
		else if (signature == MACRO.FACULTY) {

			input = new Combo(parent, SWT.READ_ONLY);
			((Combo) input).setItems(facultyArray);
			((Combo) input).setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
					true, false, 1, 1));
		}
		// Deal with two different roles
		else if (signature == MACRO.ROLES) {

			input = new Combo(parent, SWT.READ_ONLY);
			((Combo) input).setItems(rolesArray);
			((Combo) input).setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
					true, false, 1, 1));
		}
		// Deal with venue types
		else if (signature == MACRO.VENUETYPE) {
			input = new Combo(parent, SWT.READ_ONLY);
			((Combo) input).setItems(venueTypeArray);
			((Combo) input).setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
					true, false, 1, 1));
		}
		// Deal with venue locations
		else if (signature == MACRO.VENUELOCATION) {
			input = new Combo(parent, SWT.READ_ONLY);
			((Combo) input).setItems(venueLocationArray);
			((Combo) input).setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
					true, false, 1, 1));
		}
		// Deal with organizer names
		else if (signature == MACRO.ORGANIZER) {
			// Update member name array
			int organizerSize = db.getOrganizers(
					SessionManager.getCurrentEvent()).size();
			organizerArray = new String[organizerSize];
			for (int i = 0; i < organizerSize; i++) {
				organizerArray[i] = db
						.getOrganizers(SessionManager.getCurrentEvent()).get(i)
						.getName();
			}
			input = new Combo(parent, SWT.READ_ONLY);
			((Combo) input).setItems(organizerArray);
			((Combo) input).setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
					true, false, 1, 1));
		}
		// Deal with facilitator names
		else if (signature == MACRO.FACILITATOR) {
			// Update facilitator name array
			int facilitatorSize = db.getFacilitators(
					SessionManager.getCurrentEvent()).size();
			facilitatorArray = new String[facilitatorSize];
			for (int i = 0; i < facilitatorSize; i++) {
				facilitatorArray[i] = db
						.getFacilitators(SessionManager.getCurrentEvent())
						.get(i).getName();
			}
			input = new Combo(parent, SWT.READ_ONLY);
			((Combo) input).setItems(facilitatorArray);
			((Combo) input).setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
					true, false, 1, 1));
		}
		// Deal with password
		else if (signature == MACRO.PASSWORD) {
			input = new Text(parent, SWT.BORDER | SWT.PASSWORD);
			((Text) input).setLayoutData(new GridData(140, 20));
		}
		return input;
	}

	// Get Object.
	protected Object get(int i) {
		return map.get(labelList[i]);
	}

	// Set everything to empty
	protected void setEmpty() {
		for (int i = 0; i < signature.length; i++) {
			// Deal with Text, BigText, int and double except read only text
			if (signature[i] == MACRO.TEXT || signature[i] == MACRO.INT
					|| signature[i] == MACRO.DOUBLE
					|| signature[i] == MACRO.TEXTBIG
					|| signature[i] == MACRO.PASSWORD) {
				((Text) get(i)).setText("");
			}
			// Deal with Date
			else if (signature[i] == MACRO.DATE) {
				((CalendarCombo) get(i)).setDate(Calendar.getInstance());
			}
			// Deal with Time
			else if (signature[i] == MACRO.TIME) {
				((DateTime) get(i)).setTime(0, 0, 0);
			}
			// Deal with CheckBox
			else if (signature[i] == MACRO.CHECK) {
				// If selected, change to true; else change to false.
				((Button) get(i)).setSelection(false);
			}
			// Deal with names, positions, faculties, etc(Combos).
			// Do nothing to them
			else if (signature[i] == MACRO.FACULTY
					|| signature[i] == MACRO.ORGANIZER
					|| signature[i] == MACRO.FACILITATOR
					|| signature[i] == MACRO.VENUELOCATION
					|| signature[i] == MACRO.VENUETYPE
					|| signature[i] == MACRO.ROLES) {
				((Combo) get(i)).deselectAll();
			}
		}
	}

	// If it returns true, then the parent shell will be disposed.
	// Otherwise, the parent shell will not.
	// It gives the flexibility for children.
	protected boolean additionalRequirement() {
		return true;
	}

	// Pre-condition: there is no error for the input data.
	// Cast all kinds of data type to string and return an array of string.
	protected String[] getStringList() {
		for (int i = 0; i < signature.length; i++) {
			// Deal with Text, BigText, int and double
			if (signature[i] == MACRO.TEXT || signature[i] == MACRO.INT
					|| signature[i] == MACRO.DOUBLE
					|| signature[i] == MACRO.TEXTBIG
					|| signature[i] == MACRO.PASSWORD
					|| signature[i] == MACRO.READONLY) {
				stringList[i] = ((Text) get(i)).getText();
				stringList[i] = stringList[i].replaceAll("\\s+", " "); // remove
																		// extra
																		// white
																		// space
																		// input
			}
			// Deal with Date
			else if (signature[i] == MACRO.DATE) {
				Calendar calendar = ((CalendarCombo) get(i)).getDate();
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DATE);
				Date date = new Date(year, month+1, day);
				stringList[i] = date.toString();
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
					|| signature[i] == MACRO.FACILITATOR
					|| signature[i] == MACRO.VENUELOCATION
					|| signature[i] == MACRO.VENUETYPE
					|| signature[i] == MACRO.ROLES) {
				stringList[i] = ((Combo) get(i)).getItem(((Combo) get(i))
						.getSelectionIndex());
			}
		}
		return stringList;
	}

	// Error checking.
	// If there is no error, then return -1;
	// If there exist error, then return the index of input;
	protected int check() {
		boolean isValid = true;
		int index = -1;
		for (int i = 0; i < labelList.length; i++) {
			// Deal with Text and BigText
			if (signature[i] == MACRO.TEXT || signature[i] == MACRO.TEXTBIG
					|| signature[i] == MACRO.PASSWORD) {
				Text text = (Text) get(i);
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
			// Deal with names, positions, faculties, etc(Combos).
			else if (signature[i] == MACRO.FACULTY
					|| signature[i] == MACRO.ORGANIZER
					|| signature[i] == MACRO.FACILITATOR
					|| signature[i] == MACRO.VENUELOCATION
					|| signature[i] == MACRO.VENUETYPE
					|| signature[i] == MACRO.ROLES) {
				Combo combo = (Combo) get(i);
				isValid = !combo.getText().isEmpty();
			}
			// Deal with calendar combo
			else if (signature[i] == MACRO.DATE) {
				CalendarCombo calendarCombo = (CalendarCombo) get(i);
				Calendar calendar = calendarCombo.getDate();
				if (calendar == null) {
					isValid = false;
				} else {
					isValid = true;
				}
			}
			index = i;
			// If isValid is false, return directly return false.
			if (!isValid) {
				return index;
			}
		}
		// no error
		return -1;
	}

	// Submit button handler
	// When click submit button.
	protected class SubmitHandler extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (check() == -1) {
				if (additionalCheck()) {
					onSubmit();
					if (additionalRequirement()) {
						getParent().dispose();
					}
				}
			}

			else {
				// Show messageBox if there is error in input data and specify
				// where is the error.
				MessageBox warningPage = new MessageBox(getDisplay()
						.getActiveShell(), SWT.OK | SWT.ICON_WARNING);
				warningPage.setText("Warning!");
				warningPage.setMessage("Wrong input format in "
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
