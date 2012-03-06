import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;


public class FeedBackEditItem extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text textIssue;
	private Text textDate;
	private Text textTime;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public FeedBackEditItem(Composite parent, int style, Table table, int index) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite compositeFeedBackEdit = new Composite(this, SWT.NONE);
		compositeFeedBackEdit.setLayout(null);
		compositeFeedBackEdit.setBounds(10, 10, 372, 290);
		toolkit.adapt(compositeFeedBackEdit);
		toolkit.paintBordersFor(compositeFeedBackEdit);
		
		Label labelIssue = new Label(compositeFeedBackEdit, SWT.NONE);
		labelIssue.setText("Issue");
		labelIssue.setBounds(35, 34, 42, 17);
		toolkit.adapt(labelIssue, true, true);
		
		Label labelDate = new Label(compositeFeedBackEdit, SWT.NONE);
		labelDate.setText("Date");
		labelDate.setBounds(33, 77, 72, 17);
		toolkit.adapt(labelDate, true, true);
		
		Label labelTime = new Label(compositeFeedBackEdit, SWT.NONE);
		labelTime.setText("Time");
		labelTime.setBounds(35, 117, 27, 17);
		toolkit.adapt(labelTime, true, true);
		
		textIssue = new Text(compositeFeedBackEdit, SWT.BORDER);
		textIssue.setBounds(152, 31, 125, 23);
		toolkit.adapt(textIssue, true, true);
		textIssue.setText(table.getItem(index).getText(0));
		
		textDate = new Text(compositeFeedBackEdit, SWT.BORDER);
		textDate.setBounds(152, 74, 125, 23);
		toolkit.adapt(textDate, true, true);
		textDate.setText(table.getItem(index).getText(1));
		
		textTime = new Text(compositeFeedBackEdit, SWT.BORDER);
		textTime.setBounds(152, 114, 125, 23);
		toolkit.adapt(textTime, true, true);
		textTime.setText(table.getItem(index).getText(2));
		
		Button buttonEdit = new Button(compositeFeedBackEdit, SWT.NONE);
		buttonEdit.addSelectionListener(new FeedBackEditOldItem(table,index));
		buttonEdit.setText("Edit Item");
		buttonEdit.setBounds(83, 171, 66, 27);
		toolkit.adapt(buttonEdit, true, true);
		
		Button buttonCancel = new Button(compositeFeedBackEdit, SWT.NONE);
		buttonCancel.addSelectionListener(new FeedBackCancel2());
		buttonCancel.setText("Cancel");
		buttonCancel.setBounds(211, 171, 66, 27);
		toolkit.adapt(buttonCancel, true, true);

	}
	public class FeedBackEditOldItem extends SelectionAdapter{
		Table table;
		int index;

		public FeedBackEditOldItem(Table table, int index) {
			this.table = table;
			this.index=index;
		}

		public void widgetSelected(SelectionEvent e) {
			String[] taskAssignArray = new String[3];
			if (!textIssue.getText().isEmpty()) {
				taskAssignArray[0] = textIssue.getText();
				table.getItem(index).setText(0, taskAssignArray[0]);
			}
			if (!textDate.getText().isEmpty()) {
				taskAssignArray[1] = textDate.getText();
				table.getItem(index).setText(1, taskAssignArray[1]);
			}
			if (!textTime.getText().isEmpty()) {
				taskAssignArray[2] = textTime.getText();
				table.getItem(index).setText(2, taskAssignArray[2]);
			}
			if (!textIssue.getText().isEmpty() && !textDate.getText().isEmpty()
					&& !textTime.getText().isEmpty()) {
				getParent().dispose();
			}
		}
	}
	
	public class FeedBackCancel2 extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
