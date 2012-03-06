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
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;


public class FeedBackAddItem extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text textIssue;
	private Text textDate;
	private Text textTime;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public FeedBackAddItem(Composite parent, int style, Table table) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		Composite compositeFeedBackAdd = new Composite(this, SWT.NONE);
		compositeFeedBackAdd.setLayout(null);
		compositeFeedBackAdd.setBounds(10, 10, 372, 290);
		toolkit.adapt(compositeFeedBackAdd);
		toolkit.paintBordersFor(compositeFeedBackAdd);
		
		Label lblIssue = new Label(compositeFeedBackAdd, SWT.NONE);
		lblIssue.setText("Issue");
		lblIssue.setBounds(35, 34, 42, 17);
		toolkit.adapt(lblIssue, true, true);
		
		Label lblDate = new Label(compositeFeedBackAdd, SWT.NONE);
		lblDate.setText("Date");
		lblDate.setBounds(33, 77, 72, 17);
		toolkit.adapt(lblDate, true, true);
		
		Label lblTime = new Label(compositeFeedBackAdd, SWT.NONE);
		lblTime.setText("Time");
		lblTime.setBounds(35, 117, 27, 17);
		toolkit.adapt(lblTime, true, true);
		
		textIssue = new Text(compositeFeedBackAdd, SWT.BORDER);
		textIssue.setBounds(152, 31, 125, 23);
		toolkit.adapt(textIssue, true, true);
		
		textDate = new Text(compositeFeedBackAdd, SWT.BORDER);
		textDate.setBounds(152, 74, 125, 23);
		toolkit.adapt(textDate, true, true);
		
		textTime = new Text(compositeFeedBackAdd, SWT.BORDER);
		textTime.setBounds(152, 114, 125, 23);
		toolkit.adapt(textTime, true, true);
		
		Button buttonAdd = new Button(compositeFeedBackAdd, SWT.NONE);
		buttonAdd.addSelectionListener(new FeedBackAddNewItem(table));
		buttonAdd.setText("Add Item");
		buttonAdd.setBounds(83, 171, 66, 27);
		toolkit.adapt(buttonAdd, true, true);
		
		Button buttonCancel = new Button(compositeFeedBackAdd, SWT.NONE);
		buttonCancel.addSelectionListener(new FeedBackCancel());
		buttonCancel.setText("Cancel");
		buttonCancel.setBounds(211, 171, 66, 27);
		toolkit.adapt(buttonCancel, true, true);

	}
	public class FeedBackAddNewItem extends SelectionAdapter {
		Table table;

		public FeedBackAddNewItem(Table table) {
			this.table = table;
		}

		public void widgetSelected(SelectionEvent e) {
			String[] taskAssignArray = new String[3];
			if (!textIssue.getText().isEmpty()) {
				taskAssignArray[0] = textIssue.getText();
			}
			if (!textDate.getText().isEmpty()) {
				taskAssignArray[1] = textDate.getText();
			}
			if (!textTime.getText().isEmpty()) {
				taskAssignArray[2] = textTime.getText();
			}
			if (!textIssue.getText().isEmpty() && !textDate.getText().isEmpty()
					&& !textTime.getText().isEmpty()) {
				TableItem item = new TableItem(table, SWT.NULL);
				for (int i = 0; i < 3; i++) {
					item.setText(i, taskAssignArray[i]);
				}
				getParent().dispose();
			}
		}
	}
	public class FeedBackCancel extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e) {
			getParent().dispose();
		}
	}
}
