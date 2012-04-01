import java.util.Calendar;
import java.util.GregorianCalendar;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;

// Abstract Edit page inherits from AbstractForm
public abstract class AbstractEdit extends AbstractForm {
	Button[] buttonList;
	public AbstractEdit(Composite parent, int style, String[] stringList,
			int[] signature) {
		// Inherit AbstractForm constructor.
		super(parent, style, stringList, signature);
		// TODO Auto-generated constructor stub
		// Set buttons
		Button btnAdd = new Button(this, SWT.None);
		btnAdd.addSelectionListener(new SubmitHandler());
		btnAdd.setText("Edit");
		btnAdd.setLayoutData(new GridData(80, 30));

		Button btnCancel = new Button(this, SWT.None);
		btnCancel.addSelectionListener(new CancelHandler());
		btnCancel.setText("Cancel");
		btnCancel.setLayoutData(new GridData(80, 30));
	}
	public AbstractEdit(Composite parent, int style, String[] stringList,
			int[] signature,String[] stringButton){
		super(parent, style, stringList, signature);
	}
	//Do nothing about the additionalCheck()
	public boolean additionalCheck(){
		return true;
	}

	// Set data for Text, Calendar, Time and Check Box.
	protected void setData(String string, int signature, int index) {
		// Deal with Text, BigText, integer, double
		if (signature == MACRO.TEXT || signature == MACRO.INT
				|| signature == MACRO.TEXTBIG || signature == MACRO.DOUBLE) {
			((Text) get(index)).setText(string);
		}
		// Deal with Date
		else if (signature == MACRO.DATE) {
			Date date = new Date(string);
			Calendar cal = new GregorianCalendar();
			cal.set(date.getYear(), date.getMonth() - 1, date.getDay());
			((CalendarCombo) get(index)).setDate(cal);
		}
		// Deal with Time
		else if (signature == MACRO.TIME) {
			Time time = new Time(string);
			((DateTime) get(index)).setTime(time.getHour(), time.getMinute(),
					time.getSecond());
		}
		// Deal with CheckBox
		else if (signature == MACRO.CHECK) {
			boolean isDone = Boolean.parseBoolean(string);
			if (isDone) {
				((Button) get(index)).setSelection(true);
			} else {
				((Button) get(index)).setSelection(false);
			}
		}
		// Deal with Faculty
		else if (signature == MACRO.FACULTY||signature==MACRO.ORGANIZER||signature==MACRO.FACILITATOR) {
			((Combo) get(index)).setText(string);
		}
	}
}
