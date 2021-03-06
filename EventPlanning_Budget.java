import java.util.ArrayList;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.ibm.icu.text.DecimalFormat;
import org.eclipse.wb.swt.SWTResourceManager;

public class EventPlanning_Budget extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	public static Table OutflowTable;
	public static Table InflowTable;
	public static Table AllocationTable;
	public static Label lblReceivedAmount;
	public static Label lblReceivedAmount1;
	public static Label lblSpentAmount;
	public static Label lblSpentAmount1;
	public static Label lblRemainingAmount;
	public static Label lblYouStillHave_Amount;
	// To format a double to two decimal places
	private DecimalFormat df = new DecimalFormat("#.00");

	private Event event;
	private ArrayList<BudgetAllocation> budgetAllocationList;
	private ArrayList<Inflow> inflowList;
	private ArrayList<Outflow> outflowList;
	private String[] stringArrayBudget = { "Item", "Person in Charge",
			"Cost($)", "Date" };
	private int[] signatureArrayBudget = { MACRO.TEXT, MACRO.ORGANIZER,
			MACRO.DOUBLE, MACRO.DATE };
	private String[] stringArrayInflow = { "Sponsor", "Amount($)", "Date",
			"Remarks" };
	private int[] signatureArrayInflow = { MACRO.TEXT, MACRO.DOUBLE,
			MACRO.DATE, MACRO.TEXTBIG };
	private String[] stringArrayOutflow = { "Item", "Quantity", "Type",
			"Purchase Date", "Cost" };
	private int[] signatureArrayOutflow = { MACRO.TEXT, MACRO.INT, MACRO.TEXT,
			MACRO.DATE, MACRO.DOUBLE };
	private int index;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public EventPlanning_Budget(Composite parent, int style, Event event) {
		super(parent, style);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.event = event;

		TabFolder tabFolder = new TabFolder(this, SWT.NONE);
		tabFolder.setSize(673, 500);
		toolkit.adapt(tabFolder);
		toolkit.paintBordersFor(tabFolder);

		TabItem tbtmBudgetAllocation = new TabItem(tabFolder, SWT.NONE);
		tbtmBudgetAllocation.setText("Budget Allocation");

		Composite AllocationComposite = new Composite(tabFolder, SWT.NONE);
		tbtmBudgetAllocation.setControl(AllocationComposite);
		toolkit.paintBordersFor(AllocationComposite);

		AllocationTable = new Table(AllocationComposite, SWT.BORDER
				| SWT.FULL_SELECTION);
		AllocationTable.setLinesVisible(true);
		AllocationTable.setHeaderVisible(true);
		AllocationTable.setBounds(10, 10, 550, 350);
		toolkit.adapt(AllocationTable);
		toolkit.paintBordersFor(AllocationTable);

		TableColumn AllocationTableItemColumn = new TableColumn(
				AllocationTable, SWT.CENTER);
		AllocationTableItemColumn.setWidth(210);
		AllocationTableItemColumn.setText("Item");

		TableColumn AllocationTablePersonInChargeColumn = new TableColumn(
				AllocationTable, SWT.CENTER);
		AllocationTablePersonInChargeColumn.setWidth(120);
		AllocationTablePersonInChargeColumn.setText("Person in Charge");

		TableColumn AllocationTableCostColumn = new TableColumn(
				AllocationTable, SWT.CENTER);
		AllocationTableCostColumn.setWidth(100);
		AllocationTableCostColumn.setText("Cost($)");

		TableColumn AllocationTableDateColumn = new TableColumn(
				AllocationTable, SWT.CENTER);
		AllocationTableDateColumn.setWidth(120);
		AllocationTableDateColumn.setText("Date");

		Button btnAllocationAdd = new Button(AllocationComposite, SWT.NONE);
		btnAllocationAdd.setBounds(570, 10, 80, 40);
		toolkit.adapt(btnAllocationAdd, true, true);
		btnAllocationAdd.setText("Add");
		btnAllocationAdd.addSelectionListener(new AddAllocation());

		Button btnAllocationDelete = new Button(AllocationComposite, SWT.NONE);
		btnAllocationDelete.setBounds(570, 60, 80, 40);
		toolkit.adapt(btnAllocationDelete, true, true);
		btnAllocationDelete.setText("Delete");
		btnAllocationDelete.addSelectionListener(new DeleteAllocation());
		
		Button btnAllocationEdit = new Button(AllocationComposite, SWT.NONE);
		btnAllocationEdit.setBounds(570, 110, 80, 40);
		toolkit.adapt(btnAllocationEdit, true, true);
		btnAllocationEdit.setText("Edit");
		btnAllocationEdit.addSelectionListener(new EditAllocation());

		Label lblYouStillHave = new Label(AllocationComposite, SWT.NONE);
		lblYouStillHave.setFont(SWTResourceManager.getFont("΢���ź�", 13, SWT.NORMAL));
		lblYouStillHave.setAlignment(SWT.CENTER);
		lblYouStillHave.setBounds(10, 380, 135, 30);
		toolkit.adapt(lblYouStillHave, true, true);
		lblYouStillHave.setText("Money Left ($)");

		lblYouStillHave_Amount = new Label(AllocationComposite, SWT.BORDER);
		lblYouStillHave_Amount.setFont(SWTResourceManager.getFont("΢���ź�", 13, SWT.NORMAL));
		lblYouStillHave_Amount.setAlignment(SWT.CENTER);
		lblYouStillHave_Amount.setBounds(168, 380, 135, 30);
		toolkit.adapt(lblYouStillHave_Amount, true, true);
		lblYouStillHave_Amount.setText("0.0");

		TabItem tbtmCashFlow = new TabItem(tabFolder, SWT.NONE);
		tbtmCashFlow.setText("Cash Flow");

		Composite CashFlowComposite = new Composite(tabFolder, SWT.NONE);
		tbtmCashFlow.setControl(CashFlowComposite);
		toolkit.paintBordersFor(CashFlowComposite);

		TabFolder tabFolder_1 = new TabFolder(CashFlowComposite, SWT.NONE);
		tabFolder_1.setBounds(0, 0, 673, 470);
		toolkit.adapt(tabFolder_1);
		toolkit.paintBordersFor(tabFolder_1);

		TabItem tbtmInflow = new TabItem(tabFolder_1, SWT.NONE);
		tbtmInflow.setText("Inflow");

		Composite InflowComposite = new Composite(tabFolder_1, SWT.NONE);
		tbtmInflow.setControl(InflowComposite);
		toolkit.paintBordersFor(InflowComposite);

		InflowTable = new Table(InflowComposite, SWT.BORDER
				| SWT.FULL_SELECTION);
		InflowTable.setLinesVisible(true);
		InflowTable.setHeaderVisible(true);
		InflowTable.setBounds(10, 10, 550, 350);
		toolkit.adapt(InflowTable);
		toolkit.paintBordersFor(InflowTable);

		TableColumn InflowTableSponsorColumn = new TableColumn(InflowTable,
				SWT.CENTER);
		InflowTableSponsorColumn.setWidth(120);
		InflowTableSponsorColumn.setText("Sponsor");

		TableColumn InflowTableAmountColumn = new TableColumn(InflowTable,
				SWT.CENTER);
		InflowTableAmountColumn.setWidth(120);
		InflowTableAmountColumn.setText("Amount($)");

		TableColumn InflowTableDateColumn = new TableColumn(InflowTable,
				SWT.CENTER);
		InflowTableDateColumn.setWidth(100);
		InflowTableDateColumn.setText("Date");

		TableColumn InflowTableRemarksColumn = new TableColumn(InflowTable,
				SWT.CENTER);
		InflowTableRemarksColumn.setWidth(250);
		InflowTableRemarksColumn.setText("Remarks");

		Button btnInflowAdd = new Button(InflowComposite, SWT.NONE);
		btnInflowAdd.setBounds(570, 10, 80, 40);
		toolkit.adapt(btnInflowAdd, true, true);
		btnInflowAdd.setText("Add");
		btnInflowAdd.addSelectionListener(new AddInflow());

		Button btnInflowDelete = new Button(InflowComposite, SWT.NONE);
		btnInflowDelete.setBounds(570, 60, 80, 40);
		toolkit.adapt(btnInflowDelete, true, true);
		btnInflowDelete.setText("Delete");

		Button btnInflowEdit = new Button(InflowComposite, SWT.NONE);
		btnInflowEdit.setBounds(570, 110, 80, 40);
		toolkit.adapt(btnInflowEdit, true, true);
		btnInflowEdit.setText("Edit");
		btnInflowEdit.addSelectionListener(new EditInflow());

		Label lblTotalMoneyReceived = new Label(InflowComposite, SWT.NONE);
		lblTotalMoneyReceived.setFont(SWTResourceManager.getFont("΢���ź�", 13, SWT.NORMAL));
		lblTotalMoneyReceived.setText("Money Received($):");
		lblTotalMoneyReceived.setAlignment(SWT.CENTER);
		lblTotalMoneyReceived.setBounds(10, 380, 180, 30);
		toolkit.adapt(lblTotalMoneyReceived, true, true);

		lblReceivedAmount = new Label(InflowComposite, SWT.BORDER);
		lblReceivedAmount.setFont(SWTResourceManager.getFont("΢���ź�", 13, SWT.NORMAL));
		lblReceivedAmount.setText("0.0");
		lblReceivedAmount.setAlignment(SWT.CENTER);
		lblReceivedAmount.setBounds(210, 380, 120, 30);
		toolkit.adapt(lblReceivedAmount, true, true);
		btnInflowDelete.addSelectionListener(new DeleteInflow());

		TabItem tbtmOutflow = new TabItem(tabFolder_1, SWT.NONE);
		tbtmOutflow.setText("Outflow");

		Composite OutflowComposite = new Composite(tabFolder_1, SWT.NONE);
		tbtmOutflow.setControl(OutflowComposite);
		toolkit.paintBordersFor(OutflowComposite);
		OutflowComposite.setLayout(null);

		OutflowTable = new Table(OutflowComposite, SWT.BORDER
				| SWT.FULL_SELECTION);
		OutflowTable.setBounds(10, 10, 630, 350);
		OutflowTable.setToolTipText("(dd/mm/yyyy)\r\n");
		OutflowTable.setLinesVisible(true);
		OutflowTable.setHeaderVisible(true);
		toolkit.adapt(OutflowTable);
		toolkit.paintBordersFor(OutflowTable);

		TableColumn OutflowTableItemColumn = new TableColumn(OutflowTable,
				SWT.CENTER);
		OutflowTableItemColumn.setWidth(220);
		OutflowTableItemColumn.setText("Item");

		TableColumn OutflowTableQuantityColumn = new TableColumn(OutflowTable,
				SWT.CENTER);
		OutflowTableQuantityColumn.setWidth(100);
		OutflowTableQuantityColumn.setText("Quantity");

		TableColumn OutflowTableTypeColumn = new TableColumn(OutflowTable,
				SWT.CENTER);
		OutflowTableTypeColumn.setWidth(100);
		OutflowTableTypeColumn.setText("Type");

		TableColumn OutflowTableDateColumn = new TableColumn(OutflowTable,
				SWT.CENTER);
		OutflowTableDateColumn.setWidth(120);
		OutflowTableDateColumn.setToolTipText("(dd/mm/yyyy)");
		OutflowTableDateColumn.setText("Purchase Date");

		TableColumn OutflowTableCostColumn = new TableColumn(OutflowTable,
				SWT.CENTER);
		OutflowTableCostColumn.setWidth(100);
		OutflowTableCostColumn.setText("Cost($)");

		Button btnOutflowAdd = new Button(OutflowComposite, SWT.NONE);
		btnOutflowAdd.setBounds(605, 30, 0, 27);
		toolkit.adapt(btnOutflowAdd, true, true);
		btnOutflowAdd.setText("Add");
		btnOutflowAdd.addSelectionListener(new AddOutflow());

		Button btnOutflowEdit = new Button(OutflowComposite, SWT.NONE);
		btnOutflowEdit.setBounds(605, 69, 0, 27);
		toolkit.adapt(btnOutflowEdit, true, true);
		btnOutflowEdit.setText("Edit");
		btnOutflowEdit.addSelectionListener(new EditOutflow());

		Button btnOutflowDelete = new Button(OutflowComposite, SWT.NONE);
		btnOutflowDelete.setBounds(605, 109, 0, 238);
		toolkit.adapt(btnOutflowDelete, true, true);
		btnOutflowDelete.setText("Delete");

		Label lblTotalMoneySpent = new Label(OutflowComposite, SWT.NONE);
		lblTotalMoneySpent.setFont(SWTResourceManager.getFont("΢���ź�", 13, SWT.NORMAL));
		lblTotalMoneySpent.setBounds(10, 380, 180, 30);
		lblTotalMoneySpent.setAlignment(SWT.CENTER);
		lblTotalMoneySpent.setText("Money Spent($)");
		toolkit.adapt(lblTotalMoneySpent, true, true);

		lblSpentAmount = new Label(OutflowComposite, SWT.BORDER);
		lblSpentAmount.setFont(SWTResourceManager.getFont("΢���ź�", 13, SWT.NORMAL));
		lblSpentAmount.setBounds(210, 380, 120, 30);
		lblSpentAmount.setText("0.0");
		lblSpentAmount.setAlignment(SWT.CENTER);
		toolkit.adapt(lblSpentAmount, true, true);
		btnOutflowDelete.addSelectionListener(new DeleteOutflow());

		TabItem tbtmBudgetOverview = new TabItem(tabFolder, SWT.NONE);
		tbtmBudgetOverview.setText("Budget Overview");

		Composite BudgetOverviewComposite = new Composite(tabFolder, SWT.NONE);
		tbtmBudgetOverview.setControl(BudgetOverviewComposite);
		toolkit.paintBordersFor(BudgetOverviewComposite);
		BudgetOverviewComposite.setLayout(null);

		Composite OverviewComposite = new Composite(BudgetOverviewComposite,
				SWT.NONE);
		OverviewComposite.setBounds(10, 10, 510, 450);
		toolkit.adapt(OverviewComposite);
		toolkit.paintBordersFor(OverviewComposite);

		Label lblTotalMoneySpent1 = new Label(BudgetOverviewComposite, SWT.NONE);
		lblTotalMoneySpent1.setFont(SWTResourceManager.getFont("΢���ź�", 13, SWT.NORMAL));
		lblTotalMoneySpent1.setBounds(525, 160, 130, 50);
		lblTotalMoneySpent1.setAlignment(SWT.CENTER);
		toolkit.adapt(lblTotalMoneySpent1, true, true);
		lblTotalMoneySpent1.setText(" Total Money Spent($)");

		lblSpentAmount1 = new Label(BudgetOverviewComposite, SWT.BORDER);
		lblSpentAmount1.setFont(SWTResourceManager.getFont("΢���ź�", 13, SWT.NORMAL));
		lblSpentAmount1.setBounds(525, 215, 130, 30);
		lblSpentAmount1.setAlignment(SWT.CENTER);
		lblSpentAmount1.setText("0.0");
		toolkit.adapt(lblSpentAmount1, true, true);

		Label lblRemainingBudget = new Label(BudgetOverviewComposite, SWT.NONE);
		lblRemainingBudget.setFont(SWTResourceManager.getFont("΢���ź�", 13, SWT.NORMAL));
		lblRemainingBudget.setBounds(526, 280, 130, 50);
		lblRemainingBudget.setAlignment(SWT.CENTER);
		lblRemainingBudget.setText("Remaining Budget($)");
		toolkit.adapt(lblRemainingBudget, true, true);

		lblRemainingAmount = new Label(BudgetOverviewComposite, SWT.BORDER);
		lblRemainingAmount.setFont(SWTResourceManager.getFont("΢���ź�", 13, SWT.NORMAL));
		lblRemainingAmount.setBounds(525, 335, 130, 30);
		lblRemainingAmount.setAlignment(SWT.CENTER);
		lblRemainingAmount.setText("0.0");
		toolkit.adapt(lblRemainingAmount, true, true);

		Label lblTotalMoneyReceived1 = new Label(BudgetOverviewComposite,
				SWT.NONE);
		lblTotalMoneyReceived1.setFont(SWTResourceManager.getFont("΢���ź�", 13, SWT.NORMAL));
		lblTotalMoneyReceived1.setBounds(525, 40, 130, 50);
		lblTotalMoneyReceived1.setText("Total Money Received($)");
		lblTotalMoneyReceived1.setAlignment(SWT.CENTER);

		Label lblNewLabel = new Label(OverviewComposite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("΢���ź�", 13, SWT.NORMAL));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(163, 113, 179, 60);
		toolkit.adapt(lblNewLabel, true, true);
		lblNewLabel.setText("Budget Chart");
		toolkit.adapt(lblTotalMoneyReceived1, true, true);

		lblReceivedAmount1 = new Label(BudgetOverviewComposite, SWT.BORDER);
		lblReceivedAmount1.setFont(SWTResourceManager.getFont("΢���ź�", 13, SWT.NORMAL));
		lblReceivedAmount1.setBounds(525, 95, 130, 30);
		lblReceivedAmount1.setText("0.0");
		lblReceivedAmount1.setAlignment(SWT.CENTER);
		toolkit.adapt(lblReceivedAmount1, true, true);

		importBudgetAllocationData();
		importBudgetInflowData();
		importBudgetOutflowData();
	}

	public void importBudgetAllocationData() {
		DatabaseReader db = new DatabaseReader();
		budgetAllocationList = db.getBudgetAllocation(event);
		double totalCost = 0;

		/* update the budget allocation table */
		for (int i = 0; i < budgetAllocationList.size(); i++) {
			TableItem temp = new TableItem(AllocationTable, SWT.NULL);
			temp.setText(0, budgetAllocationList.get(i).getItem());
			temp.setText(1, budgetAllocationList.get(i).getPersonInCharge());
			temp.setText(2,
					String.valueOf(budgetAllocationList.get(i).getCost()));
			temp.setText(3, budgetAllocationList.get(i).getDate().toString());

			totalCost += budgetAllocationList.get(i).getCost();
		}

		double currentAmount = Double
				.parseDouble(EventPlanning_Budget.lblYouStillHave_Amount
						.getText());
		EventPlanning_Budget.lblYouStillHave_Amount.setText(String.valueOf(df
				.format(currentAmount - totalCost))); // update the notification
														// box

		/* update budget overview section */
		LabelCashFlow lbl = new LabelCashFlow();
		lbl.label();
	}

	public void importBudgetInflowData() {
		DatabaseReader db = new DatabaseReader();
		inflowList = db.getInflow(event);
		double totalInflow = 0;

		for (int i = 0; i < inflowList.size(); i++) {
			TableItem temp = new TableItem(InflowTable, SWT.NULL);
			temp.setText(0, inflowList.get(i).getSponsor());
			temp.setText(1, String.valueOf(inflowList.get(i).getAmount()));
			temp.setText(2, inflowList.get(i).getDate().toString());
			temp.setText(3, inflowList.get(i).getRemarks());

			totalInflow += inflowList.get(i).getAmount();
		}
		double currentAmount = Double
				.parseDouble(EventPlanning_Budget.lblReceivedAmount.getText());
		EventPlanning_Budget.lblReceivedAmount.setText(String.valueOf(df
				.format(currentAmount + totalInflow))); // the double value is
														// formated to two
														// decimal palces before
														// being converted to a
														// string

		/* update budget overview section */
		LabelCashFlow lbl = new LabelCashFlow();
		lbl.label();
	}

	public void importBudgetOutflowData() {
		DatabaseReader db = new DatabaseReader();
		outflowList = db.getOutflow(event);
		double totalOutflow = 0;

		for (int i = 0; i < outflowList.size(); i++) {
			TableItem temp = new TableItem(OutflowTable, SWT.NULL);
			temp.setText(0, outflowList.get(i).getItem());
			temp.setText(1, String.valueOf(outflowList.get(i).getQuantity()));
			temp.setText(2, outflowList.get(i).getType());
			temp.setText(3, outflowList.get(i).getDate().toString());
			temp.setText(4, String.valueOf(outflowList.get(i).getCost()));

			totalOutflow += outflowList.get(i).getCost()
					* outflowList.get(i).getQuantity();
		}
		double currentAmount = Double
				.parseDouble(EventPlanning_Budget.lblSpentAmount.getText());
		EventPlanning_Budget.lblSpentAmount.setText(String.valueOf(df
				.format(currentAmount + totalOutflow)));

		/* update budget overview section */
		LabelCashFlow lbl = new LabelCashFlow();
		lbl.label();
	}

	// Budget Allocation
	class AddAllocation extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (AllocationTable.getSelectionCount() == 0) {
				Shell add_allocation_shell = new Shell(getDisplay());
				AbstractAdd add_allocation_page = new AbstractAdd(
						add_allocation_shell, SWT.None, stringArrayBudget,
						signatureArrayBudget, AllocationTable) {
					public void onSubmit() {
						// insert to database
						String[] tempList = getStringList();
						Date date = new Date(tempList[3]);
						BudgetAllocation budgetAllocation = new BudgetAllocation(
								tempList[0], tempList[1],
								Double.parseDouble(tempList[2]), date);
						db.insertBudgetAllocation(event, budgetAllocation);
						budgetAllocationList.add(budgetAllocation);
						// update the table
						TableItem item = new TableItem(AllocationTable,
								SWT.NULL);
						for (int i = 0; i < stringArrayBudget.length; i++) {
							item.setText(i, tempList[i]);
						}

						/* update budget allocation table */
						double addition = Double.parseDouble(tempList[2]);
						double currentAmount = Double
								.parseDouble(lblYouStillHave_Amount.getText());
						lblYouStillHave_Amount.setText(String.valueOf(df
								.format(currentAmount - addition)));

						/* update budget overview section */
						LabelCashFlow lbl = new LabelCashFlow();
						lbl.label();
					}
				};

				add_allocation_page.pack();
				add_allocation_shell.pack();
				add_allocation_shell.open();
			} else
				AllocationTable.deselectAll(); // clear selection for add
												// function
		}
	}

	class DeleteAllocation extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (AllocationTable.getItemCount() != 0) {
				int index = AllocationTable.getSelectionIndex();
				if (index >= 0 && index < AllocationTable.getItemCount()) {
					/* update budget allocation table */
					double currentAmount = Double
							.parseDouble(lblYouStillHave_Amount.getText());
					double addition = Double.parseDouble(AllocationTable
							.getItem(index).getText(2));
					lblYouStillHave_Amount.setText(String.valueOf(df
							.format(currentAmount + addition)));
					AllocationTable.remove(index);

					/* update database */
					DatabaseReader db = new DatabaseReader();
					db.deleteBudgetAllocation(budgetAllocationList.get(index));

					/* update budget overview section */
					LabelCashFlow lbl = new LabelCashFlow();
					lbl.label();
				}
			}
		}
	}

	class EditAllocation extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			index = AllocationTable.getSelectionIndex();
			if (AllocationTable.getSelectionCount() != 0) {
				Shell edit_allocation_shell = new Shell(getDisplay());
				AbstractEdit edit_allocation_page = new AbstractEdit(
						edit_allocation_shell, SWT.None, stringArrayBudget,
						signatureArrayBudget) {
					// setText
					public void onLoad() {
						for (int i = 0; i < stringArrayBudget.length; i++) {
							setData(AllocationTable.getItem(index).getText(i),
									signatureArrayBudget[i], i);
						}
					}

					public void onSubmit() {
						String[] tempList = getStringList();

						BudgetAllocation budgetAllocation = budgetAllocationList
								.get(index);
						budgetAllocation.setItem(tempList[0]);
						budgetAllocation.setPersonInCharge(tempList[1]);
						budgetAllocation.setCost(Double
								.parseDouble(tempList[2]));
						budgetAllocation.setDate(new Date(tempList[3]));

						/* update budget allocation table */
						double previous = Double.parseDouble(AllocationTable
								.getItem(index).getText(2));
						double addition = Double.parseDouble(tempList[2]);
						double currentAmount = Double
								.parseDouble(lblYouStillHave_Amount.getText());
						lblYouStillHave_Amount.setText(String.valueOf(df
								.format(currentAmount - addition + previous)));

						// update database
						db.updateBudgetAllocation(budgetAllocation);
						// update the table
						for (int i = 0; i < stringArrayBudget.length; i++) {
							AllocationTable.getItem(index).setText(i,
									tempList[i]);
						}
						/* update budget overview section */
						LabelCashFlow lbl = new LabelCashFlow();
						lbl.label();

					}
				};

				edit_allocation_page.pack();
				edit_allocation_shell.pack();
				edit_allocation_shell.open();
			}
		}
	}

	// Cash Flow
	// Inflow
	class AddInflow extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (InflowTable.getSelectionCount() == 0) {
				Shell add_inflow_shell = new Shell(getDisplay());
				AbstractAdd add_inflow_page = new AbstractAdd(add_inflow_shell,
						SWT.None, stringArrayInflow, signatureArrayInflow,
						InflowTable) {
					public void onSubmit() {
						// insert to database
						String[] tempList = getStringList();
						Date date = new Date(tempList[2]);
						Inflow inflow = new Inflow(tempList[0],
								Double.parseDouble(tempList[1]), date,
								tempList[3]);
						db.insertInflow(event, inflow);
						inflowList.add(inflow);
						// update the table
						TableItem item = new TableItem(InflowTable, SWT.NULL);
						for (int i = 0; i < stringArrayInflow.length; i++) {
							item.setText(i, tempList[i]);
						}
						/* update budget inflow table */
						double currentAmount = Double
								.parseDouble(lblReceivedAmount.getText());
						double addition = Double.parseDouble(tempList[1]);
						lblReceivedAmount.setText(String.valueOf(df
								.format(currentAmount + addition)));
						/* update budget overview section */
						LabelCashFlow lbl = new LabelCashFlow();
						lbl.label();
					}
				};

				add_inflow_page.pack();
				add_inflow_shell.pack();
				add_inflow_shell.open();
			} else
				InflowTable.deselectAll();
		}
	}

	class DeleteInflow extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (InflowTable.getItemCount() != 0) {
				int index = InflowTable.getSelectionIndex();
				if (index >= 0 && index < InflowTable.getItemCount()) {
					/* update budget inflow table */
					double currentAmount = Double.parseDouble(lblReceivedAmount
							.getText());
					double deduction = Double.parseDouble(InflowTable.getItem(
							index).getText(1));
					lblReceivedAmount.setText(String.valueOf(df
							.format(currentAmount - deduction)));
					InflowTable.remove(index);

					/* update database */
					DatabaseReader db = new DatabaseReader();
					db.deleteInflow(inflowList.get(index));

					/* update budget overview section */
					LabelCashFlow lbl = new LabelCashFlow();
					lbl.label();
				}
			}
		}
	}

	class EditInflow extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			index = InflowTable.getSelectionIndex();
			if (InflowTable.getSelectionCount() != 0) {
				Shell edit_inflow_shell = new Shell(getDisplay());
				AbstractEdit edit_inflow_page = new AbstractEdit(
						edit_inflow_shell, SWT.None, stringArrayInflow,
						signatureArrayInflow) {
					// setText
					public void onLoad() {
						for (int i = 0; i < stringArrayInflow.length; i++) {
							setData(InflowTable.getItem(index).getText(i),
									signatureArrayInflow[i], i);
						}
					}

					public void onSubmit() {
						String[] tempList = getStringList();
						Inflow inflow = inflowList.get(index);
						inflow.setSponsor(tempList[0]);
						inflow.setAmount(Double.parseDouble(tempList[1]));
						inflow.setDate(new Date(tempList[2]));
						inflow.setRemarks(tempList[3]);
						// update database
						db.updateInflow(inflow);

						/* update budget inflow table */
						double currentAmount = Double
								.parseDouble(lblReceivedAmount.getText());
						double deduction = Double.parseDouble(InflowTable
								.getItem(index).getText(1));
						double addition = Double.parseDouble(tempList[1]);
						lblReceivedAmount.setText(String.valueOf(df
								.format(currentAmount - deduction + addition)));

						// update the table
						for (int i = 0; i < stringArrayInflow.length; i++) {
							InflowTable.getItem(index).setText(i, tempList[i]);
						}
						/* update budget overview section */
						LabelCashFlow lbl = new LabelCashFlow();
						lbl.label();
					}
				};

				edit_inflow_page.pack();
				edit_inflow_shell.pack();
				edit_inflow_shell.open();
			}
		}
	}

	// Outflow
	class AddOutflow extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (OutflowTable.getSelectionCount() == 0) {
				Shell add_outflow_shell = new Shell(getDisplay());
				AbstractAdd add_outflow_page = new AbstractAdd(
						add_outflow_shell, SWT.None, stringArrayOutflow,
						signatureArrayOutflow, OutflowTable) {
					public void onSubmit() {
						// insert to database
						String[] tempList = getStringList();
						Date date = new Date(tempList[3]);
						double amount = Double.parseDouble(tempList[4]);
						int quan = Integer.parseInt(tempList[1]);
						Outflow outflow = new Outflow(tempList[0], quan,
								tempList[2], date, amount);
						db.insertOutflow(event, outflow);
						outflowList.add(outflow);
						// update the table
						TableItem item = new TableItem(OutflowTable, SWT.NULL);
						for (int i = 0; i < stringArrayOutflow.length; i++) {
							item.setText(i, tempList[i]);
						}

						/* update budget outflow table */
						double currentAmount = Double
								.parseDouble(lblSpentAmount.getText());
						double addition = amount * quan;
						lblSpentAmount.setText(String.valueOf(df
								.format(currentAmount + addition)));

						/* update budget overview section */
						LabelCashFlow lbl = new LabelCashFlow();
						lbl.label();
					}
				};

				add_outflow_page.pack();
				add_outflow_shell.pack();
				add_outflow_shell.open();
			} else
				OutflowTable.deselectAll();
		}
	}

	class DeleteOutflow extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (OutflowTable.getItemCount() != 0) {
				int index = OutflowTable.getSelectionIndex();
				if (index >= 0 && index < OutflowTable.getItemCount()) {
					/* update budget outflow table */
					double amount = Double.parseDouble(OutflowTable.getItem(
							index).getText(4));
					int quan = Integer.parseInt(OutflowTable.getItem(index)
							.getText(1));
					double currentAmount = Double.parseDouble(lblSpentAmount
							.getText());
					double deduction = amount * quan;
					lblSpentAmount.setText(String.valueOf(df
							.format(currentAmount - deduction)));
					OutflowTable.remove(index);

					/* update database */
					DatabaseReader db = new DatabaseReader();
					db.deleteOutflow(outflowList.get(index));

					/* update budget overview section */
					LabelCashFlow lbl = new LabelCashFlow();
					lbl.label();
				}
			}
		}
	}

	class EditOutflow extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (OutflowTable.getSelectionCount() != 0) {
				Shell edit_outflow_shell = new Shell(getDisplay());
				AbstractEdit edit_outflow_page = new AbstractEdit(
						edit_outflow_shell, SWT.None, stringArrayOutflow,
						signatureArrayOutflow) {
					// setText
					public void onLoad() {
						for (int i = 0; i < stringArrayOutflow.length; i++) {
							setData(InflowTable.getItem(index).getText(i),
									signatureArrayOutflow[i], i);
						}
					}

					public void onSubmit() {
						String[] tempList = getStringList();
						Outflow outflow = outflowList.get(index);
						int quan = Integer.parseInt(tempList[1]);
						double amount = Double.parseDouble(tempList[4]);
						outflow.setItem(tempList[0]);
						outflow.setQuantity(quan);
						outflow.setType(tempList[2]);
						outflow.setDate(new Date(tempList[3]));
						outflow.setCost(amount);
						// update database
						db.updateOutflow(outflow);

						/* update budget outflow table */
						int quanPrev = Integer.parseInt(OutflowTable.getItem(
								index).getText(1));
						double amountPrev = Double.parseDouble(OutflowTable
								.getItem(index).getText(4));
						double currentAmount = Double
								.parseDouble(lblSpentAmount.getText());
						double deduction = amountPrev * quanPrev;
						double addition = quan * amount;
						lblSpentAmount.setText(String.valueOf(df
								.format(currentAmount - deduction + addition)));

						// update the table
						for (int i = 0; i < stringArrayOutflow.length; i++) {
							InflowTable.getItem(index).setText(i, tempList[i]);
						}
						/* update budget overview section */
						LabelCashFlow lbl = new LabelCashFlow();
						lbl.label();
					}
				};

				edit_outflow_page.pack();
				edit_outflow_shell.pack();
				edit_outflow_shell.open();
			}
		}
	}
}
