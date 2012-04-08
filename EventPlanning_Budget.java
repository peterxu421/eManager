import java.util.ArrayList;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import com.ibm.icu.text.DecimalFormat;
import org.eclipse.wb.swt.SWTResourceManager;
import org.swtchart.Chart;
import org.swtchart.IAxis;
import org.swtchart.IAxisSet;
import org.swtchart.IBarSeries;
import org.swtchart.ISeries.SeriesType;

public class EventPlanning_Budget extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	private Table OutflowTable;
	private Table InflowTable;
	private Table AllocationTable;
	private Label lblReceivedAmount;
	private Label lblSpentAmount;
	private Label lblYouStillHave_Amount;
	private Label lblSpentAmount_Overview;
	private Label lblRemainingAmount_Overview;
	private Label lblReceivedAmount_Overview;
	// To format a double to two decimal places
	private DecimalFormat df = new DecimalFormat("#.00");

	private Event event;
	private ArrayList<BudgetPlanning> budgetAllocationList;
	private ArrayList<BudgetInflow> inflowList;
	private ArrayList<BudgetOutflow> outflowList;
	private String[] stringArrayBudget = { "Item", "Person in Charge", "Cost($)", "Date" };
	private int[] signatureArrayBudget = { MACRO.TEXT, MACRO.ORGANIZER,	MACRO.DOUBLE, MACRO.DATE };
	private String[] stringArrayInflow = { "Sponsor", "Amount($)", "Date", "Remarks" };
	private int[] signatureArrayInflow = { MACRO.TEXT, MACRO.DOUBLE, MACRO.DATE, MACRO.TEXTBIG };
	private String[] stringArrayOutflow = { "Item", "Quantity", "Type", "Purchase Date", "Cost($)/Item" };
	private int[] signatureArrayOutflow = { MACRO.TEXT, MACRO.INT, MACRO.TEXT, MACRO.DATE, MACRO.DOUBLE };
	private int index;
	private Chart chart;

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

		TabItem tbtmBudgetPlanning = new TabItem(tabFolder, SWT.NONE);
		tbtmBudgetPlanning.setText("Budget Planning");

		Composite AllocationComposite = new Composite(tabFolder, SWT.NONE);
		tbtmBudgetPlanning.setControl(AllocationComposite);
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
		lblYouStillHave.setFont(SWTResourceManager.getFont("Calibri", 13,
				SWT.NORMAL));
		lblYouStillHave.setAlignment(SWT.CENTER);
		lblYouStillHave.setBounds(10, 380, 135, 30);
		toolkit.adapt(lblYouStillHave, true, true);
		lblYouStillHave.setText("Money Left ($)");

		lblYouStillHave_Amount = new Label(AllocationComposite, SWT.BORDER);
		lblYouStillHave_Amount.setFont(SWTResourceManager.getFont("Calibri",
				13, SWT.NORMAL));
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
		tbtmInflow.setText("BudgetInflow");

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
		lblTotalMoneyReceived.setFont(SWTResourceManager.getFont("Calibri", 13,
				SWT.NORMAL));
		lblTotalMoneyReceived.setText("Money Received($):");
		lblTotalMoneyReceived.setAlignment(SWT.CENTER);
		lblTotalMoneyReceived.setBounds(10, 380, 180, 30);
		toolkit.adapt(lblTotalMoneyReceived, true, true);

		lblReceivedAmount = new Label(InflowComposite, SWT.BORDER);
		lblReceivedAmount.setFont(SWTResourceManager.getFont("Calibri", 13,
				SWT.NORMAL));
		lblReceivedAmount.setText("0.0");
		lblReceivedAmount.setAlignment(SWT.CENTER);
		lblReceivedAmount.setBounds(210, 380, 120, 30);
		toolkit.adapt(lblReceivedAmount, true, true);
		btnInflowDelete.addSelectionListener(new DeleteInflow());

		TabItem tbtmOutflow = new TabItem(tabFolder_1, SWT.NONE);
		tbtmOutflow.setText("BudgetOutflow");

		Composite OutflowComposite = new Composite(tabFolder_1, SWT.NONE);
		tbtmOutflow.setControl(OutflowComposite);
		toolkit.paintBordersFor(OutflowComposite);
		OutflowComposite.setLayout(null);

		OutflowTable = new Table(OutflowComposite, SWT.BORDER
				| SWT.FULL_SELECTION);
		OutflowTable.setBounds(10, 10, 550, 350);
		OutflowTable.setToolTipText("(dd/mm/yyyy)\r\n");
		OutflowTable.setLinesVisible(true);
		OutflowTable.setHeaderVisible(true);
		toolkit.adapt(OutflowTable);
		toolkit.paintBordersFor(OutflowTable);

		TableColumn OutflowTableItemColumn = new TableColumn(OutflowTable,
				SWT.CENTER);
		OutflowTableItemColumn.setWidth(150);
		OutflowTableItemColumn.setText("Item");

		TableColumn OutflowTableQuantityColumn = new TableColumn(OutflowTable,
				SWT.CENTER);
		OutflowTableQuantityColumn.setWidth(80);
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
		OutflowTableCostColumn.setText("Cost($)/Item");

		Button btnOutflowAdd = new Button(OutflowComposite, SWT.NONE);
		btnOutflowAdd.setBounds(570, 10, 80, 40);
		toolkit.adapt(btnOutflowAdd, true, true);
		btnOutflowAdd.setText("Add");
		btnOutflowAdd.addSelectionListener(new AddOutflow());

		Button btnOutflowDelete = new Button(OutflowComposite, SWT.NONE);
		btnOutflowDelete.setBounds(570, 60, 80, 40);
		toolkit.adapt(btnOutflowDelete, true, true);
		btnOutflowDelete.setText("Delete");

		Button btnOutflowEdit = new Button(OutflowComposite, SWT.NONE);
		btnOutflowEdit.setBounds(570, 110, 80, 40);
		toolkit.adapt(btnOutflowEdit, true, true);
		btnOutflowEdit.setText("Edit");
		btnOutflowEdit.addSelectionListener(new EditOutflow());

		Label lblTotalMoneySpent = new Label(OutflowComposite, SWT.NONE);
		lblTotalMoneySpent.setFont(SWTResourceManager.getFont("Calibri", 13,
				SWT.NORMAL));
		lblTotalMoneySpent.setBounds(10, 380, 180, 30);
		lblTotalMoneySpent.setAlignment(SWT.CENTER);
		lblTotalMoneySpent.setText("Money Spent($)");
		toolkit.adapt(lblTotalMoneySpent, true, true);

		lblSpentAmount = new Label(OutflowComposite, SWT.BORDER);
		lblSpentAmount.setFont(SWTResourceManager.getFont("Calibri", 13,
				SWT.NORMAL));
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
		OverviewComposite.addPaintListener(new PaintListener(){
			public void paintControl(PaintEvent e) {
				drawChart();
			}
		});
		// GridLayout overviewLayout = new GridLayout();
		// OverviewComposite.setLayout(overviewLayout);

		Label lblTotalMoneySpent_Overview = new Label(BudgetOverviewComposite,
				SWT.NONE);
		lblTotalMoneySpent_Overview.setFont(SWTResourceManager.getFont(
				"Calibri", 13, SWT.NORMAL));
		lblTotalMoneySpent_Overview.setBounds(525, 160, 130, 50);
		lblTotalMoneySpent_Overview.setAlignment(SWT.CENTER);
		toolkit.adapt(lblTotalMoneySpent_Overview, true, true);
		lblTotalMoneySpent_Overview.setText(" Total Money Spent($)");

		lblSpentAmount_Overview = new Label(BudgetOverviewComposite, SWT.BORDER);
		lblSpentAmount_Overview.setFont(SWTResourceManager.getFont("Calibri",
				13, SWT.NORMAL));
		lblSpentAmount_Overview.setBounds(525, 215, 130, 30);
		lblSpentAmount_Overview.setAlignment(SWT.CENTER);
		lblSpentAmount_Overview.setText("0.0");
		toolkit.adapt(lblSpentAmount_Overview, true, true);

		Label lblRemainingBudget_Overview = new Label(BudgetOverviewComposite,
				SWT.NONE);
		lblRemainingBudget_Overview.setFont(SWTResourceManager.getFont(
				"Calibri", 13, SWT.NORMAL));
		lblRemainingBudget_Overview.setBounds(526, 280, 130, 50);
		lblRemainingBudget_Overview.setAlignment(SWT.CENTER);
		lblRemainingBudget_Overview.setText("Remaining Budget($)");
		toolkit.adapt(lblRemainingBudget_Overview, true, true);

		lblRemainingAmount_Overview = new Label(BudgetOverviewComposite,
				SWT.BORDER);
		lblRemainingAmount_Overview.setFont(SWTResourceManager.getFont(
				"Calibri", 13, SWT.NORMAL));
		lblRemainingAmount_Overview.setBounds(525, 335, 130, 30);
		lblRemainingAmount_Overview.setAlignment(SWT.CENTER);
		lblRemainingAmount_Overview.setText("0.0");
		toolkit.adapt(lblRemainingAmount_Overview, true, true);

		Label lblTotalMoneyReceived_Overview = new Label(
				BudgetOverviewComposite, SWT.NONE);
		lblTotalMoneyReceived_Overview.setFont(SWTResourceManager.getFont(
				"Calibri", 13, SWT.NORMAL));
		lblTotalMoneyReceived_Overview.setBounds(525, 40, 130, 50);
		lblTotalMoneyReceived_Overview.setText("Total Money Received($)");
		lblTotalMoneyReceived_Overview.setAlignment(SWT.CENTER);
		toolkit.adapt(lblTotalMoneyReceived_Overview, true, true);

		lblReceivedAmount_Overview = new Label(BudgetOverviewComposite,
				SWT.BORDER);
		lblReceivedAmount_Overview.setFont(SWTResourceManager.getFont(
				"Calibri", 13, SWT.NORMAL));
		lblReceivedAmount_Overview.setBounds(525, 95, 130, 30);
		lblReceivedAmount_Overview.setText("0.0");
		lblReceivedAmount_Overview.setAlignment(SWT.CENTER);
		toolkit.adapt(lblReceivedAmount_Overview, true, true);

		importBudgetAllocationData();
		importBudgetInflowData();
		importBudgetOutflowData();

		chart = new Chart(OverviewComposite, SWT.None);
		chart.setBounds(0, 0, 490, 400);
		chart.getTitle().setText("Budget Chart");
		chart.getAxisSet().getYAxes()[0].getTitle().setText("Amount");

		String[] categories = new String[] { "Spent", "Remaining", "Received" };
		IAxisSet axisSet = chart.getAxisSet();
		IAxis xAxis = axisSet.getXAxis(0);
		xAxis.setCategorySeries(categories);
		xAxis.enableCategory(true);

		drawChart();

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

		double currentAmount = Double.parseDouble(lblYouStillHave_Amount
				.getText());
		lblYouStillHave_Amount.setText(String.valueOf(df.format(currentAmount
				- totalCost))); // update the notification box

		/* update budget overview section */
		label();

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
		double currentAmount = Double.parseDouble(lblReceivedAmount.getText());
		lblReceivedAmount.setText(String.valueOf(df.format(currentAmount
				+ totalInflow)));   // the double value is formated to two
									// decimal palces before being converted to a string
		/* update budget overview section */
		label();
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
		double currentAmount = Double.parseDouble(lblSpentAmount.getText());
		lblSpentAmount.setText(String.valueOf(df.format(currentAmount
				+ totalOutflow)));

		/* update budget overview section */
		label();
	}

	// Use to refresh
	public void label() {
		double currentBudget = Double.parseDouble(lblRemainingAmount_Overview
				.getText());
		double moneyReceived = Double.parseDouble(lblReceivedAmount.getText());
		double moneySpent = Double.parseDouble(lblSpentAmount.getText());
		double newBudget = moneyReceived - moneySpent;
		double change = newBudget - currentBudget;
		double stillHave = Double.parseDouble(lblYouStillHave_Amount.getText());

		stillHave += change;
		lblYouStillHave_Amount.setText(String.valueOf(df.format(stillHave)));
		lblRemainingAmount_Overview
				.setText(String.valueOf(df.format(newBudget)));

		lblReceivedAmount_Overview.setText(lblReceivedAmount.getText());
		lblSpentAmount_Overview.setText(lblSpentAmount.getText());
	}
	
	private void drawChart(){
		double[] amounts = new double[] {
				Double.parseDouble(lblSpentAmount_Overview.getText()),
				Double.parseDouble(lblRemainingAmount_Overview.getText()),
				Double.parseDouble(lblReceivedAmount_Overview.getText()) };
		IBarSeries barSeries = (IBarSeries) chart.getSeriesSet().createSeries(
				SeriesType.BAR, "Amount");
		barSeries.setYSeries(amounts);

		Color color = new Color(Display.getDefault(), 238, 221, 130);
		barSeries.setBarColor(color);
		chart.getAxisSet().adjustRange();
		chart.redraw();
	}

	// Budget Allocation
	class AddAllocation extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (AllocationTable.getSelectionCount() == 0) {
				Shell add_allocation_shell = new Shell(getDisplay(),
						SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
				AbstractAdd add_allocation_page = new AbstractAdd(
						add_allocation_shell, SWT.None, stringArrayBudget,
						signatureArrayBudget, AllocationTable) {
					public void onSubmit() {
						// insert to database
						String[] tempList = getStringList();
						Date date = new Date(tempList[3]);
						BudgetPlanning budgetPlanning = new BudgetPlanning(
								tempList[0], tempList[1],
								Double.parseDouble(tempList[2]), date);
						db.insertBudgetAllocation(event, budgetPlanning);
						budgetAllocationList.add(budgetPlanning);
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
						label();
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
					label();
				}
			}
		}
	}

	class EditAllocation extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			index = AllocationTable.getSelectionIndex();
			if (AllocationTable.getSelectionCount() != 0) {
				Shell edit_allocation_shell = new Shell(getDisplay(),
						SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
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

						BudgetPlanning budgetPlanning = budgetAllocationList.get(index);
						budgetPlanning.setItem(tempList[0]);
						budgetPlanning.setPersonInCharge(tempList[1]);
						budgetPlanning.setCost(Double
								.parseDouble(tempList[2]));
						budgetPlanning.setDate(new Date(tempList[3]));

						/* update budget allocation table */
						double previous = Double.parseDouble(AllocationTable
								.getItem(index).getText(2));
						double addition = Double.parseDouble(tempList[2]);
						double currentAmount = Double
								.parseDouble(lblYouStillHave_Amount.getText());
						lblYouStillHave_Amount.setText(String.valueOf(df
								.format(currentAmount - addition + previous)));

						// update database
						db.updateBudgetAllocation(budgetPlanning);
						// update the table
						for (int i = 0; i < stringArrayBudget.length; i++) {
							AllocationTable.getItem(index).setText(i,
									tempList[i]);
						}
						/* update budget overview section */
						label();

					}
				};

				edit_allocation_page.pack();
				edit_allocation_shell.pack();
				edit_allocation_shell.open();
			}
		}
	}

	// Cash Flow
	// BudgetInflow
	class AddInflow extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (InflowTable.getSelectionCount() == 0) {
				Shell add_inflow_shell = new Shell(getDisplay(),
						SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
				AbstractAdd add_inflow_page = new AbstractAdd(add_inflow_shell,
						SWT.None, stringArrayInflow, signatureArrayInflow,
						InflowTable) {
					public void onSubmit() {
						// insert to database
						String[] tempList = getStringList();
						Date date = new Date(tempList[2]);
						BudgetInflow budgetInflow = new BudgetInflow(tempList[0],
								Double.parseDouble(tempList[1]), date,
								tempList[3]);
						db.insertInflow(event, budgetInflow);
						inflowList.add(budgetInflow);
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
						label();
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
					label();
				}
			}
		}
	}

	class EditInflow extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			index = InflowTable.getSelectionIndex();
			if (InflowTable.getSelectionCount() != 0) {
				Shell edit_inflow_shell = new Shell(getDisplay(),
						SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
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
						BudgetInflow budgetInflow = inflowList.get(index);
						budgetInflow.setSponsor(tempList[0]);
						budgetInflow.setAmount(Double.parseDouble(tempList[1]));
						budgetInflow.setDate(new Date(tempList[2]));
						budgetInflow.setRemarks(tempList[3]);
						// update database
						db.updateInflow(budgetInflow);

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
						label();
					}
				};

				edit_inflow_page.pack();
				edit_inflow_shell.pack();
				edit_inflow_shell.open();
			}
		}
	}

	// BudgetOutflow
	class AddOutflow extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (OutflowTable.getSelectionCount() == 0) {
				Shell add_outflow_shell = new Shell(getDisplay(),
						SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
				AbstractAdd add_outflow_page = new AbstractAdd(
						add_outflow_shell, SWT.None, stringArrayOutflow,
						signatureArrayOutflow, OutflowTable) {
					public void onSubmit() {
						// insert to database
						String[] tempList = getStringList();
						Date date = new Date(tempList[3]);
						double amount = Double.parseDouble(tempList[4]);
						int quan = Integer.parseInt(tempList[1]);
						BudgetOutflow budgetOutflow = new BudgetOutflow(tempList[0], quan,
								tempList[2], date, amount);
						db.insertOutflow(event, budgetOutflow);
						outflowList.add(budgetOutflow);
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
						label();
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
				index = OutflowTable.getSelectionIndex();
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
					label();
				}
			}
		}
	}

	class EditOutflow extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (OutflowTable.getSelectionCount() != 0) {
				index = OutflowTable.getSelectionIndex();
				Shell edit_outflow_shell = new Shell(getDisplay(),
						SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
				AbstractEdit edit_outflow_page = new AbstractEdit(
						edit_outflow_shell, SWT.None, stringArrayOutflow,
						signatureArrayOutflow) {
					// setText
					public void onLoad() {
						for (int i = 0; i < stringArrayOutflow.length; i++) {
							setData(OutflowTable.getItem(index).getText(i),
									signatureArrayOutflow[i], i);
						}
					}

					public void onSubmit() {
						String[] tempList = getStringList();
						BudgetOutflow budgetOutflow = outflowList.get(index);
						int quan = Integer.parseInt(tempList[1]);
						double amount = Double.parseDouble(tempList[4]);
						budgetOutflow.setItem(tempList[0]);
						budgetOutflow.setQuantity(quan);
						budgetOutflow.setType(tempList[2]);
						budgetOutflow.setDate(new Date(tempList[3]));
						budgetOutflow.setCost(amount);
						// update database
						db.updateOutflow(budgetOutflow);

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
							OutflowTable.getItem(index).setText(i, tempList[i]);
						}
						/* update budget overview section */
						label();
					}
				};

				edit_outflow_page.pack();
				edit_outflow_shell.pack();
				edit_outflow_shell.open();
			}
		}
	}
}
