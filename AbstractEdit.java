import java.util.Calendar;
import java.util.GregorianCalendar;

import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;

public abstract class AbstractEdit extends AbstractForm {

	public AbstractEdit(Composite parent, int style, String[] stringList,
			int[] signature) {
		// Inherit AbstractForm constructor.
		super(parent, style, stringList, signature);
		// TODO Auto-generated constructor stub
		// Set buttons
		btnAdd = new Button(this, SWT.None);
		btnAdd.addSelectionListener(new SubmitHandler());
		btnAdd.setText("Edit");
		btnAdd.setLayoutData(new GridData(60, 30));

		Button btnCancel = new Button(this, SWT.None);
		btnCancel.addSelectionListener(new CancelHandler());
		btnCancel.setText("Cancel");
		btnCancel.setLayoutData(new GridData(60, 30));
	}

	// Set data for Text, Calendar, Time and Check Box.
	protected void setData(String string, int signature, int index) {
		if (signature == MACRO.TEXT || signature == MACRO.INT) {
			((Text) get(index)).setText(string);
		} else if (signature == MACRO.DATE) {
			Date date = new Date(string);
			Calendar cal = new GregorianCalendar();
			cal.set(date.getYear(), date.getMonth(), date.getDay());
			((CalendarCombo) get(index)).setDate(cal);
		} else if (signature == MACRO.TIME) {
			Time time = new Time(string);
			((DateTime) get(index)).setTime(time.getHour(), time.getMinute(),
					time.getSecond());
		} else if (signature == MACRO.CHECK) {
			Done done = new Done(string);
			if (done.isDone()) {
				((Button) get(index)).setSelection(true);
			} else {
				((Button) get(index)).setSelection(false);
			}
		}
	}
}
