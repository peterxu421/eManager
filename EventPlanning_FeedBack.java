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
	private String[] stringArray = { "Issue", "Date", "Time" };
	private int[] signatureArray = { MACRO.TEXT, MACRO.DATE, MACRO.TIME };
	private int index;

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
		
				tableFeedBack = new Table(this, SWT.BORDER
						| SWT.FULL_SELECTION);
				tableFeedBack.setLocation(10, 10);
				tableFeedBack.setSize(550, 400);
				toolkit.adapt(tableFeedBack);
				toolkit.paintBordersFor(tableFeedBack);
				tableFeedBack.setHeaderVisible(true);
				tableFeedBack.setLinesVisible(true);
				
						TableColumn tblclmnIssue = new TableColumn(tableFeedBack, SWT.CENTER);
						tblclmnIssue.setWidth(300);
						tblclmnIssue.setText("Issue");
						
								TableColumn tblclmDate = new TableColumn(tableFeedBack, SWT.CENTER);
								tblclmDate.setWidth(120);
								tblclmDate.setText("Date");
								
										TableColumn tblclmnTime = new TableColumn(tableFeedBack, SWT.CENTER);
										tblclmnTime.setWidth(120);
										tblclmnTime.setText("Time");
										
												Button btnFeedBackAddItem = new Button(this, SWT.NONE);
												btnFeedBackAddItem.setLocation(570, 10);
												btnFeedBackAddItem.setSize(80, 40);
												btnFeedBackAddItem.addSelectionListener(new FeedBackAddItemPage());
												toolkit.adapt(btnFeedBackAddItem, true, true);
												btnFeedBackAddItem.setText("Add");
												
														Button btnFeedBackDeleteItem = new Button(this, SWT.NONE);
														btnFeedBackDeleteItem.setLocation(570, 60);
														btnFeedBackDeleteItem.setSize(80, 40);
														btnFeedBackDeleteItem.addSelectionListener(new FeedBackDeleteItem());
														toolkit.adapt(btnFeedBackDeleteItem, true, true);
														btnFeedBackDeleteItem.setText("Delete ");
														
																Button btnFeedBackEditItem = new Button(this, SWT.NONE);
																btnFeedBackEditItem.setLocation(570, 110);
																btnFeedBackEditItem.setSize(80, 40);
																btnFeedBackEditItem.addSelectionListener(new FeedBackEditItemPage());
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
			AbstractAdd feedbackAddItem = new AbstractAdd(feedbackAddItemPage,
					SWT.None, stringArray,signatureArray, tableFeedBack) {
				public void onSubmit() {
					// insert to database
					String[] tempList=getStringList();
					Date date = new Date(tempList[1]);
					Time time = new Time(tempList[2]);
					Feedback feedback = new Feedback(tempList[0],
							date, time);
					db.insertFeedback(event, feedback);
					feedbackList.add(feedback);
					// update the table
					TableItem item = new TableItem(tableFeedBack, SWT.NULL);
					for (int i = 0; i < stringArray.length; i++) {
						item.setText(i, tempList[i]);
					}
				}
			};
			feedbackAddItem.pack();
			feedbackAddItemPage.pack();
			feedbackAddItemPage.open();
		}
	}

	public class FeedBackDeleteItem extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (tableFeedBack.getColumnCount() != 0) {
				int index = tableFeedBack.getSelectionIndex();
				if (index < 0 || index >= tableFeedBack.getItemCount()) {
					// Do nothing.
				} else {
					tableFeedBack.remove(index);
					DatabaseReader db = new DatabaseReader();
					db.deleteFeedback(feedbackList.get(index));
				}
			}
		}
	}

	public class FeedBackEditItemPage extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			index = tableFeedBack.getSelectionIndex();
			if (index < tableFeedBack.getItemCount() && index >= 0) {
				Shell feedbackEditItemPage = new Shell(getDisplay());
				AbstractEdit feedbackEditItem = new AbstractEdit(
						feedbackEditItemPage, SWT.None, stringArray,signatureArray) {
					// setText
					public void onLoad() {
						for (int i = 0; i < stringArray.length; i++) {
							setData(tableFeedBack.getItem(index)
									.getText(i),signatureArray[i],i);
						}
					}

					public void onSubmit() {
						String[] tempList=getStringList();
						Feedback feedback = feedbackList.get(index);
						feedback.setFeedbackDetails(tempList[0]);
						feedback.setDate(new Date(tempList[1]));
						feedback.setTime(new Time(tempList[2]));
						// update database
						db.updateFeedback(feedback);
						// update the table
						for (int i = 0; i < stringArray.length; i++) {
							tableFeedBack.getItem(index).setText(i,
									tempList[i]);
						}
					}
				};
				feedbackEditItem.pack();
				feedbackEditItemPage.pack();
				feedbackEditItemPage.open();
			}
		}
	}
}
