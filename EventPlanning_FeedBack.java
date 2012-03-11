import java.util.ArrayList;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableItem;

public class EventPlanning_FeedBack extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Table tableFeedBack;
	private ArrayList<Feedback> feedbackList;
	private Event event;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public EventPlanning_FeedBack(Composite parent, int style, Event event) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.event = event;
		DatabaseReader db = new DatabaseReader();
		this.feedbackList = db.getFeedback(event);

		Composite compositeFeedBack = new Composite(this, SWT.NONE);
		compositeFeedBack.setBounds(0, 0, 488, 318);
		toolkit.adapt(compositeFeedBack);
		toolkit.paintBordersFor(compositeFeedBack);

		tableFeedBack = new Table(compositeFeedBack, SWT.BORDER
				| SWT.FULL_SELECTION);
		tableFeedBack.setBounds(10, 10, 376, 273);
		toolkit.adapt(tableFeedBack);
		toolkit.paintBordersFor(tableFeedBack);
		tableFeedBack.setHeaderVisible(true);
		tableFeedBack.setLinesVisible(true);

		TableColumn tblclmnIssue = new TableColumn(tableFeedBack, SWT.NONE);
		tblclmnIssue.setWidth(241);
		tblclmnIssue.setText("Issue");

		TableColumn tblclmDate = new TableColumn(tableFeedBack, SWT.NONE);
		tblclmDate.setWidth(65);
		tblclmDate.setText("Date");

		TableColumn tblclmnTime = new TableColumn(tableFeedBack, SWT.NONE);
		tblclmnTime.setWidth(66);
		tblclmnTime.setText("Time");

		Button btnFeedBackAddItem = new Button(compositeFeedBack, SWT.NONE);
		btnFeedBackAddItem.addSelectionListener(new FeedBackAddItemPage());
		btnFeedBackAddItem.setBounds(398, 31, 80, 27);
		toolkit.adapt(btnFeedBackAddItem, true, true);
		btnFeedBackAddItem.setText("Add");

		Button btnFeedBackDeleteItem = new Button(compositeFeedBack, SWT.NONE);
		btnFeedBackDeleteItem.addSelectionListener(new FeedBackDeleteItem(
				tableFeedBack));
		btnFeedBackDeleteItem.setBounds(398, 73, 80, 27);
		toolkit.adapt(btnFeedBackDeleteItem, true, true);
		btnFeedBackDeleteItem.setText("Delete ");

		Button btnFeedBackEditItem = new Button(compositeFeedBack, SWT.NONE);
		btnFeedBackEditItem.addSelectionListener(new FeedBackEditItemPage());
		btnFeedBackEditItem.setBounds(398, 116, 80, 27);
		toolkit.adapt(btnFeedBackEditItem, true, true);
		btnFeedBackEditItem.setText("Edit ");

		fillTable();

	}

	public void fillTable() {
		TableItem item;

		for (int i = 0; i < feedbackList.size(); i++) {
			item = new TableItem(tableFeedBack, SWT.NONE);
			item.setText(0, feedbackList.get(i).getFeedbackDetails());
			item.setText(1, feedbackList.get(i).getDate().toString());
			item.setText(2, feedbackList.get(i).getTime().toString());
		}
	}

	/*
	 * public static void main(String[] args) { Display display = new Display();
	 * Shell shell = new Shell(display); EventPlanning_FeedBack calc = new
	 * EventPlanning_FeedBack(shell, SWT.NONE); calc.pack(); shell.pack();
	 * shell.open(); while (!shell.isDisposed()) { if
	 * (!display.readAndDispatch()) display.sleep(); } }
	 */

	public class FeedBackAddItemPage extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Shell feedbackAddItemPage = new Shell(getDisplay());
			FeedBackAddItem feedbackAddItem = new FeedBackAddItem(
					feedbackAddItemPage, SWT.None, tableFeedBack, event);
			feedbackAddItem.pack();
			feedbackAddItemPage.pack();
			feedbackAddItemPage.open();
		}
	}

	public class FeedBackDeleteItem extends SelectionAdapter {
		Table table;

		public FeedBackDeleteItem(Table table) {
			this.table = table;
		}

		public void widgetSelected(SelectionEvent e) {
			if (table.getColumnCount() != 0) {
				int index = table.getSelectionIndex();
				if (index < 0 || index >= table.getItemCount()) {
					// Do nothing.
				} else {
					table.remove(index);
					DatabaseReader db = new DatabaseReader();
					db.deleteFeedback(feedbackList.get(index));
				}
			}
		}
	}

	public class FeedBackEditItemPage extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			int index = tableFeedBack.getSelectionIndex();
			if (index < tableFeedBack.getItemCount() && index >= 0) {
				Shell feedbackEditItemPage = new Shell(getDisplay());
				FeedBackEditItem feedbackEditItem = new FeedBackEditItem(
						feedbackEditItemPage, SWT.None, tableFeedBack, index, feedbackList.get(index));
				feedbackEditItem.pack();
				feedbackEditItemPage.pack();
				feedbackEditItemPage.open();
			}
		}
	}
}
