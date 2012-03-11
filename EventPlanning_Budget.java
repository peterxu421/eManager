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
	
	private DecimalFormat df = new DecimalFormat("#.00"); // to format a double to two decimal palces 
	
	private Event event;
	private ArrayList<BudgetAllocation> budgetAllocationList;
	private ArrayList<Inflow> inflowList;
	private ArrayList<Outflow> outflowList;

	/**
	 * Create the composite.
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
		this.event = event; // access current event
		
		Composite BudgetComposite = new Composite(this, SWT.NONE);
		BudgetComposite.setBounds(0, 0, 689, 430);
		toolkit.adapt(BudgetComposite);
		toolkit.paintBordersFor(BudgetComposite);
		
		TabFolder tabFolder = new TabFolder(BudgetComposite, SWT.NONE);
		tabFolder.setBounds(0, 0, 689, 320);
		toolkit.adapt(tabFolder);
		toolkit.paintBordersFor(tabFolder);
		
		TabItem tbtmBudgetAllocation = new TabItem(tabFolder, SWT.NONE);
		tbtmBudgetAllocation.setText("Budget Allocation");
		
		Composite AllocationComposite = new Composite(tabFolder, SWT.NONE);
		tbtmBudgetAllocation.setControl(AllocationComposite);
		toolkit.paintBordersFor(AllocationComposite);
		
		AllocationTable = new Table(AllocationComposite, SWT.BORDER | SWT.FULL_SELECTION);
		AllocationTable.setLinesVisible(true);
		AllocationTable.setHeaderVisible(true);
		AllocationTable.setBounds(10, 10, 542, 240);
		toolkit.adapt(AllocationTable);
		toolkit.paintBordersFor(AllocationTable);
		
		TableColumn AllocationTableItemColumn = new TableColumn(AllocationTable, SWT.NONE);
		AllocationTableItemColumn.setWidth(211);
		AllocationTableItemColumn.setText("Item");
		
		TableColumn AllocationTablePersonInChargeColumn = new TableColumn(AllocationTable, SWT.CENTER);
		AllocationTablePersonInChargeColumn.setWidth(123);
		AllocationTablePersonInChargeColumn.setText("Person in Charge");
		
		TableColumn AllocationTableCostColumn = new TableColumn(AllocationTable, SWT.CENTER);
		AllocationTableCostColumn.setWidth(100);
		AllocationTableCostColumn.setText("Cost($)");
		
		TableColumn AllocationTableDateColumn = new TableColumn(AllocationTable, SWT.CENTER);
		AllocationTableDateColumn.setWidth(117);
		AllocationTableDateColumn.setText("Date");
		
		Button btnAllocationAdd = new Button(AllocationComposite, SWT.NONE);
		btnAllocationAdd.setBounds(575, 49, 75, 25);
		toolkit.adapt(btnAllocationAdd, true, true);
		btnAllocationAdd.setText("Add");
		btnAllocationAdd.addSelectionListener(new AddAllocation());
		
		Button btnAllocationEdit = new Button(AllocationComposite, SWT.NONE);
		btnAllocationEdit.setBounds(575, 95, 75, 25);
		toolkit.adapt(btnAllocationEdit, true, true);
		btnAllocationEdit.setText("Edit");
		btnAllocationEdit.addSelectionListener(new EditAllocation());
		
		Button btnAllocationDelete = new Button(AllocationComposite, SWT.NONE);
		btnAllocationDelete.setBounds(575, 138, 75, 25);
		toolkit.adapt(btnAllocationDelete, true, true);
		btnAllocationDelete.setText("Delete");
		btnAllocationDelete.addSelectionListener(new DeleteAllocation());
		
		Label lblYouStillHave = new Label(AllocationComposite, SWT.NONE);
		lblYouStillHave.setAlignment(SWT.RIGHT);
		lblYouStillHave.setBounds(10, 256, 87, 15);
		toolkit.adapt(lblYouStillHave, true, true);
		lblYouStillHave.setText("You still have($):");
		
		lblYouStillHave_Amount = new Label(AllocationComposite, SWT.BORDER);
		lblYouStillHave_Amount.setAlignment(SWT.CENTER);
		lblYouStillHave_Amount.setBounds(103, 255, 55, 15);
		toolkit.adapt(lblYouStillHave_Amount, true, true);
		lblYouStillHave_Amount.setText("0.0");
		
		TabItem tbtmCashFlow = new TabItem(tabFolder, SWT.NONE);
		tbtmCashFlow.setText("Cash Flow");
		
		Composite CashFlowComposite = new Composite(tabFolder, SWT.NONE);
		tbtmCashFlow.setControl(CashFlowComposite);
		toolkit.paintBordersFor(CashFlowComposite);
		
		TabFolder tabFolder_1 = new TabFolder(CashFlowComposite, SWT.NONE);
		tabFolder_1.setBounds(0, 0, 736, 287);
		toolkit.adapt(tabFolder_1);
		toolkit.paintBordersFor(tabFolder_1);
		
		TabItem tbtmInflow = new TabItem(tabFolder_1, SWT.NONE);
		tbtmInflow.setText("Inflow");
		
		Composite InflowComposite = new Composite(tabFolder_1, SWT.NONE);
		tbtmInflow.setControl(InflowComposite);
		toolkit.paintBordersFor(InflowComposite);
		
		InflowTable = new Table(InflowComposite, SWT.BORDER | SWT.FULL_SELECTION);
		InflowTable.setLinesVisible(true);
		InflowTable.setHeaderVisible(true);
		InflowTable.setBounds(10, 10, 551, 207);
		toolkit.adapt(InflowTable);
		toolkit.paintBordersFor(InflowTable);
		
		TableColumn InflowTableSponsorColumn = new TableColumn(InflowTable, SWT.CENTER);
		InflowTableSponsorColumn.setWidth(150);
		InflowTableSponsorColumn.setText("Sponsor");
		
		TableColumn InflowTableAmountColumn = new TableColumn(InflowTable, SWT.CENTER);
		InflowTableAmountColumn.setWidth(72);
		InflowTableAmountColumn.setText("Amount($)");
		
		TableColumn InflowTableDateColumn = new TableColumn(InflowTable, SWT.CENTER);
		InflowTableDateColumn.setWidth(100);
		InflowTableDateColumn.setText("Date");
		
		TableColumn InflowTableRemarksColumn = new TableColumn(InflowTable, SWT.CENTER);
		InflowTableRemarksColumn.setWidth(261);
		InflowTableRemarksColumn.setText("Remarks");
		
		Button btnInflowAdd = new Button(InflowComposite, SWT.NONE);
		btnInflowAdd.setBounds(585, 28, 75, 25);
		toolkit.adapt(btnInflowAdd, true, true);
		btnInflowAdd.setText("Add");
		btnInflowAdd.addSelectionListener(new AddInflow());
		
		Button btnInflowEdit = new Button(InflowComposite, SWT.NONE);
		btnInflowEdit.setBounds(585, 77, 75, 25);
		toolkit.adapt(btnInflowEdit, true, true);
		btnInflowEdit.setText("Edit");
		btnInflowEdit.addSelectionListener(new EditInflow());
		
		Button btnInflowDelete = new Button(InflowComposite, SWT.NONE);
		btnInflowDelete.setBounds(585, 128, 75, 25);
		toolkit.adapt(btnInflowDelete, true, true);
		btnInflowDelete.setText("Delete");
		
		Label lblTotalMoneyReceived = new Label(InflowComposite, SWT.NONE);
		lblTotalMoneyReceived.setText("Total Money Received($):");
		lblTotalMoneyReceived.setAlignment(SWT.CENTER);
		lblTotalMoneyReceived.setBounds(10, 223, 139, 15);
		toolkit.adapt(lblTotalMoneyReceived, true, true);
		
		lblReceivedAmount = new Label(InflowComposite, SWT.BORDER);
		lblReceivedAmount.setText("0.0");
		lblReceivedAmount.setAlignment(SWT.CENTER);
		lblReceivedAmount.setBounds(168, 223, 72, 15);
		toolkit.adapt(lblReceivedAmount, true, true);
		btnInflowDelete.addSelectionListener(new DeleteInflow());
		
		TabItem tbtmOutflow = new TabItem(tabFolder_1, SWT.NONE);
		tbtmOutflow.setText("Outflow");
		
		Composite OutflowComposite = new Composite(tabFolder_1, SWT.NONE);
		tbtmOutflow.setControl(OutflowComposite);
		toolkit.paintBordersFor(OutflowComposite);
		OutflowComposite.setLayout(new FormLayout());
		
		OutflowTable = new Table(OutflowComposite, SWT.BORDER | SWT.FULL_SELECTION);
		OutflowTable.setToolTipText("(dd/mm/yyyy)\r\n");
		OutflowTable.setLinesVisible(true);
		OutflowTable.setHeaderVisible(true);
		FormData fd_OutflowTable = new FormData();
		fd_OutflowTable.right = new FormAttachment(0, 586);
		fd_OutflowTable.bottom = new FormAttachment(100, -43);
		fd_OutflowTable.top = new FormAttachment(0, 10);
		fd_OutflowTable.left = new FormAttachment(0, 10);
		OutflowTable.setLayoutData(fd_OutflowTable);
		toolkit.adapt(OutflowTable);
		toolkit.paintBordersFor(OutflowTable);
		
		TableColumn OutflowTableItemColumn = new TableColumn(OutflowTable, SWT.CENTER);
		OutflowTableItemColumn.setWidth(236);
		OutflowTableItemColumn.setText("Item");
		
		TableColumn OutflowTableQuantityColumn = new TableColumn(OutflowTable, SWT.CENTER);
		OutflowTableQuantityColumn.setWidth(90);
		OutflowTableQuantityColumn.setText("Quantity");
		
		TableColumn OutflowTableTypeColumn = new TableColumn(OutflowTable, SWT.CENTER);
		OutflowTableTypeColumn.setWidth(77);
		OutflowTableTypeColumn.setText("Type");
		
		TableColumn OutflowTableDateColumn = new TableColumn(OutflowTable, SWT.CENTER);
		OutflowTableDateColumn.setWidth(96);
		OutflowTableDateColumn.setToolTipText("(dd/mm/yyyy)");
		OutflowTableDateColumn.setText("Purchase Date");
		
		TableColumn OutflowTableCostColumn = new TableColumn(OutflowTable, SWT.CENTER);
		OutflowTableCostColumn.setWidth(86);
		OutflowTableCostColumn.setText("Cost($)");
		
		Button btnOutflowAdd = new Button(OutflowComposite, SWT.NONE);
		FormData fd_btnOutflowAdd = new FormData();
		fd_btnOutflowAdd.bottom = new FormAttachment(0, 57);
		fd_btnOutflowAdd.top = new FormAttachment(0, 30);
		fd_btnOutflowAdd.left = new FormAttachment(OutflowTable, 19);
		fd_btnOutflowAdd.right = new FormAttachment(100, -67);
		btnOutflowAdd.setLayoutData(fd_btnOutflowAdd);
		toolkit.adapt(btnOutflowAdd, true, true);
		btnOutflowAdd.setText("Add");
		btnOutflowAdd.addSelectionListener(new AddOutflow());
		
		Button btnOutflowEdit = new Button(OutflowComposite, SWT.NONE);
		FormData fd_btnOutflowEdit = new FormData();
		fd_btnOutflowEdit.top = new FormAttachment(btnOutflowAdd, 12);
		fd_btnOutflowEdit.right = new FormAttachment(100, -67);
		fd_btnOutflowEdit.left = new FormAttachment(OutflowTable, 19);
		btnOutflowEdit.setLayoutData(fd_btnOutflowEdit);
		toolkit.adapt(btnOutflowEdit, true, true);
		btnOutflowEdit.setText("Edit");
		btnOutflowEdit.addSelectionListener(new EditOutflow());
		
		Button btnOutflowDelete = new Button(OutflowComposite, SWT.NONE);
		fd_btnOutflowEdit.bottom = new FormAttachment(btnOutflowDelete, -13);
		FormData fd_btnOutflowDelete = new FormData();
		fd_btnOutflowDelete.right = new FormAttachment(100, -67);
		fd_btnOutflowDelete.left = new FormAttachment(OutflowTable, 19);
		fd_btnOutflowDelete.top = new FormAttachment(0, 109);
		fd_btnOutflowDelete.bottom = new FormAttachment(100, -123);
		btnOutflowDelete.setLayoutData(fd_btnOutflowDelete);
		toolkit.adapt(btnOutflowDelete, true, true);
		btnOutflowDelete.setText("Delete");
		
		Label lblTotalMoneySpent = new Label(OutflowComposite, SWT.NONE);
		lblTotalMoneySpent.setText("Total Money Spent($):");
		FormData fd_lblTotalMoneySpent = new FormData();
		fd_lblTotalMoneySpent.right = new FormAttachment(OutflowTable, 128);
		fd_lblTotalMoneySpent.top = new FormAttachment(OutflowTable, 6);
		fd_lblTotalMoneySpent.left = new FormAttachment(OutflowTable, 0, SWT.LEFT);
		lblTotalMoneySpent.setLayoutData(fd_lblTotalMoneySpent);
		toolkit.adapt(lblTotalMoneySpent, true, true);
		
		lblSpentAmount = new Label(OutflowComposite, SWT.BORDER);
		lblSpentAmount.setText("0.0");
		lblSpentAmount.setAlignment(SWT.CENTER);
		FormData fd_lblSpentAmount = new FormData();
		fd_lblSpentAmount.top = new FormAttachment(OutflowTable, 6);
		fd_lblSpentAmount.left = new FormAttachment(lblTotalMoneySpent, 6);
		fd_lblSpentAmount.right = new FormAttachment(100, -528);
		lblSpentAmount.setLayoutData(fd_lblSpentAmount);
		toolkit.adapt(lblSpentAmount, true, true);
		btnOutflowDelete.addSelectionListener(new DeleteOutflow());
		
		
		TabItem tbtmBudgetOverview = new TabItem(tabFolder, SWT.NONE);
		tbtmBudgetOverview.setText("Budget Overview");
		
		Composite BudgetOverviewComposite = new Composite(tabFolder, SWT.NONE);
		tbtmBudgetOverview.setControl(BudgetOverviewComposite);
		toolkit.paintBordersFor(BudgetOverviewComposite);
		BudgetOverviewComposite.setLayout(new FormLayout());
		
		Composite OverviewComposite = new Composite(BudgetOverviewComposite, SWT.NONE);
		FormData fd_OverviewComposite = new FormData();
		fd_OverviewComposite.top = new FormAttachment(0, 10);
		fd_OverviewComposite.bottom = new FormAttachment(100, -10);
		fd_OverviewComposite.left = new FormAttachment(0, 10);
		OverviewComposite.setLayoutData(fd_OverviewComposite);
		toolkit.adapt(OverviewComposite);
		toolkit.paintBordersFor(OverviewComposite);
		
		Label lblTotalMoneySpent1 = new Label(BudgetOverviewComposite, SWT.NONE);
		fd_OverviewComposite.right = new FormAttachment(lblTotalMoneySpent1, -6);
		lblTotalMoneySpent1.setAlignment(SWT.CENTER);
		FormData fd_lblTotalMoneySpent1 = new FormData();
		fd_lblTotalMoneySpent1.left = new FormAttachment(0, 524);
		fd_lblTotalMoneySpent1.right = new FormAttachment(100, -10);
		lblTotalMoneySpent1.setLayoutData(fd_lblTotalMoneySpent1);
		toolkit.adapt(lblTotalMoneySpent1, true, true);
		lblTotalMoneySpent1.setText(" Total Money Spent($):");
		
		lblSpentAmount1 = new Label(BudgetOverviewComposite, SWT.BORDER);
		lblSpentAmount1.setAlignment(SWT.CENTER);
		lblSpentAmount1.setText("0.0");
		FormData fd_lblSpentAmount1 = new FormData();
		fd_lblSpentAmount1.left = new FormAttachment(OverviewComposite, 28);
		fd_lblSpentAmount1.right = new FormAttachment(100, -32);
		fd_lblSpentAmount1.top = new FormAttachment(lblTotalMoneySpent1, 6);
		lblSpentAmount1.setLayoutData(fd_lblSpentAmount1);
		toolkit.adapt(lblSpentAmount1, true, true);
		
		Label lblRemainingBudget = new Label(BudgetOverviewComposite, SWT.NONE);
		lblRemainingBudget.setAlignment(SWT.CENTER);
		lblRemainingBudget.setText("Remaining Budget($):");
		FormData fd_lblRemainingBudget = new FormData();
		fd_lblRemainingBudget.left = new FormAttachment(OverviewComposite, 6);
		fd_lblRemainingBudget.right = new FormAttachment(100, -10);
		fd_lblRemainingBudget.top = new FormAttachment(lblSpentAmount1, 36);
		lblRemainingBudget.setLayoutData(fd_lblRemainingBudget);
		toolkit.adapt(lblRemainingBudget, true, true);
		
		lblRemainingAmount = new Label(BudgetOverviewComposite, SWT.BORDER);
		lblRemainingAmount.setAlignment(SWT.CENTER);
		lblRemainingAmount.setText("0.0");
		FormData fd_lblRemainingAmount = new FormData();
		fd_lblRemainingAmount.left = new FormAttachment(OverviewComposite, 28);
		fd_lblRemainingAmount.right = new FormAttachment(100, -32);
		fd_lblRemainingAmount.top = new FormAttachment(lblRemainingBudget, 6);
		lblRemainingAmount.setLayoutData(fd_lblRemainingAmount);
		toolkit.adapt(lblRemainingAmount, true, true);
		
		Label lblTotalMoneyReceived1 = new Label(BudgetOverviewComposite, SWT.NONE);
		lblTotalMoneyReceived1.setText("Total Money Received($):");
		lblTotalMoneyReceived1.setAlignment(SWT.CENTER);
		FormData fd_lblTotalMoneyReceived1 = new FormData();
		fd_lblTotalMoneyReceived1.left = new FormAttachment(OverviewComposite, 7);
		
		Label lblNewLabel = new Label(OverviewComposite, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(163, 113, 106, 15);
		toolkit.adapt(lblNewLabel, true, true);
		lblNewLabel.setText("Budget Chart");
		fd_lblTotalMoneyReceived1.right = new FormAttachment(100, -10);
		fd_lblTotalMoneyReceived1.top = new FormAttachment(0, 43);
		lblTotalMoneyReceived1.setLayoutData(fd_lblTotalMoneyReceived1);
		toolkit.adapt(lblTotalMoneyReceived1, true, true);
		
		lblReceivedAmount1 = new Label(BudgetOverviewComposite, SWT.BORDER);
		fd_lblTotalMoneySpent1.top = new FormAttachment(lblReceivedAmount1, 39);
		lblReceivedAmount1.setText("0.0");
		lblReceivedAmount1.setAlignment(SWT.CENTER);
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(lblTotalMoneyReceived1, 10);
		fd_label.left = new FormAttachment(lblTotalMoneyReceived1, 21, SWT.LEFT);
		fd_label.right = new FormAttachment(100, -32);
		lblReceivedAmount1.setLayoutData(fd_label);
		toolkit.adapt(lblReceivedAmount1, true, true);
		
		importBudgetAllocationData();
		importBudgetInflowData();
		importBudgetOutflowData();
	}
	
	public void importBudgetAllocationData(){
		DatabaseReader db = new DatabaseReader();
		budgetAllocationList = db.getBudgetAllocation(event);
        double totalCost = 0;
		
		/* update the budget allocation table */
		for(int i=0; i<budgetAllocationList.size(); i++){
			TableItem temp = new TableItem(AllocationTable, SWT.NULL);
			temp.setText(0, budgetAllocationList.get(i).getItem());
			temp.setText(1, budgetAllocationList.get(i).getPersonInCharge());
			temp.setText(2, String.valueOf(budgetAllocationList.get(i).getCost()));
		    temp.setText(3, budgetAllocationList.get(i).getDate().toString());
		    
		    totalCost += budgetAllocationList.get(i).getCost();
		}
		double currentAmount = Double.parseDouble(lblYouStillHave_Amount.getText());
		lblYouStillHave_Amount.setText(String.valueOf(df.format(currentAmount - totalCost))); // update the notification box
		
		/* update budget overview section */
		LabelCashFlow lbl = new LabelCashFlow();
        lbl.label(); 
	}
	
	public void importBudgetInflowData(){
		DatabaseReader db = new DatabaseReader();
		inflowList = db.getInflow(event);
		double totalInflow = 0;
		
		for(int i=0; i<inflowList.size(); i++){
			TableItem temp = new TableItem(InflowTable, SWT.NULL);
			temp.setText(0, inflowList.get(i).getSponsor());
			temp.setText(1, String.valueOf(inflowList.get(i).getAmount()));
			temp.setText(2, inflowList.get(i).getDate().toString());
			temp.setText(3, inflowList.get(i).getRemarks());
			
		    totalInflow += inflowList.get(i).getAmount();
		}	
		double currentAmount = Double.parseDouble(EventPlanning_Budget.lblReceivedAmount.getText());
		EventPlanning_Budget.lblReceivedAmount.setText(String.valueOf(df.format(currentAmount + totalInflow)));  // the double value is formated to two decimal palces before being converted to a string
	
		/* update budget overview section */
		LabelCashFlow lbl = new LabelCashFlow();
        lbl.label(); 
	}
	
	public void importBudgetOutflowData(){
		DatabaseReader db = new DatabaseReader();
		outflowList = db.getOutflow(event);
	    double totalOutflow = 0;
		
		for(int i=0; i<outflowList.size(); i++){
			TableItem temp = new TableItem(OutflowTable, SWT.NULL);
			temp.setText(0, outflowList.get(i).getItem());
			temp.setText(1, String.valueOf(outflowList.get(i).getQuantity()));
			temp.setText(2, outflowList.get(i).getType());
			temp.setText(3, outflowList.get(i).getDate().toString());
			temp.setText(4, String.valueOf(outflowList.get(i).getCost()));
			
			totalOutflow += outflowList.get(i).getCost();
		}	
		double currentAmount = Double.parseDouble(EventPlanning_Budget.lblSpentAmount.getText());
		EventPlanning_Budget.lblSpentAmount.setText(String.valueOf(df.format(currentAmount + totalOutflow)));
		
		/* update budget overview section */
		LabelCashFlow lbl = new LabelCashFlow();
        lbl.label(); 
	}

	// Budget Allocation
	class AddAllocation extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if(AllocationTable.getSelectionCount()==0){
				Shell add_allocation_shell = new Shell(getDisplay());
				AddBudgetAllocationPage add_allocation_page = new AddBudgetAllocationPage(add_allocation_shell, SWT.None, event);
				add_allocation_page.pack();
				add_allocation_shell.pack();
				add_allocation_shell.open();	
			}
			else AllocationTable.deselectAll(); // clear selection for add function
		}
	}
	class DeleteAllocation extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e){
			if (AllocationTable.getItemCount() != 0){
				int index = AllocationTable.getSelectionIndex();
				if(index >=0 && index < AllocationTable.getItemCount()){
					/* update budget allocation table */
					double currentAmount = Double.parseDouble(lblYouStillHave_Amount.getText());
					double addition = Double.parseDouble(AllocationTable.getItem(index).getText(2));
					lblYouStillHave_Amount.setText(String.valueOf(df.format(currentAmount + addition)));
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
			if(AllocationTable.getSelectionCount()!=0){
				Shell edit_allocation_shell = new Shell(getDisplay());
				AddBudgetAllocationPage edit_allocation_page = new AddBudgetAllocationPage(edit_allocation_shell, SWT.None, event);
				edit_allocation_page.pack();
				edit_allocation_shell.pack();
				edit_allocation_shell.open();
			}
		}
	}
	
	//Cash Flow
	  //Inflow
	class AddInflow extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if(InflowTable.getSelectionCount()==0){
				Shell add_inflow_shell = new Shell(getDisplay());
				AddInflowPage add_inflow_page = new AddInflowPage(add_inflow_shell, SWT.None, event);
				add_inflow_page.pack();
				add_inflow_shell.pack();
				add_inflow_shell.open();	
			}
			else InflowTable.deselectAll();
		}
	}
	class DeleteInflow extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e){
			if (InflowTable.getItemCount() != 0){
				int index = InflowTable.getSelectionIndex();
				if(index >=0 && index < InflowTable.getItemCount()){
					/* update budget inflow table */
					double currentAmount = Double.parseDouble(lblReceivedAmount.getText());
					double deduction = Double.parseDouble(InflowTable.getItem(index).getText(1));
					lblReceivedAmount.setText(String.valueOf(df.format(currentAmount - deduction)));
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
			if(InflowTable.getSelectionCount()!=0){
				Shell edit_inflow_shell = new Shell(getDisplay());
				AddInflowPage edit_inflow_page = new AddInflowPage(edit_inflow_shell, SWT.None, event);
				edit_inflow_page.pack();
				edit_inflow_shell.pack();
				edit_inflow_shell.open();
			}
		}
	}
	  //Outflow
	class AddOutflow extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if(OutflowTable.getSelectionCount()==0){
				Shell add_outflow_shell = new Shell(getDisplay());
				AddOutflowPage add_outflow_page = new AddOutflowPage(add_outflow_shell, SWT.None,event);
				add_outflow_page.pack();
				add_outflow_shell.pack();
				add_outflow_shell.open();	
			}
			else OutflowTable.deselectAll();
		}
	}
	class DeleteOutflow extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e){
			if (OutflowTable.getItemCount() != 0){
				int index = OutflowTable.getSelectionIndex();
				if(index >=0 && index < OutflowTable.getItemCount()){
					/* update budget outflow table */
					double currentAmount = Double.parseDouble(lblSpentAmount.getText());
					double deduction = Double.parseDouble(OutflowTable.getItem(index).getText(4));
					lblSpentAmount.setText(String.valueOf(df.format(currentAmount - deduction)));
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
			if(OutflowTable.getSelectionCount()!=0){
				Shell edit_outflow_shell = new Shell(getDisplay());
				AddOutflowPage edit_outflow_page = new AddOutflowPage(edit_outflow_shell, SWT.None,event);
				edit_outflow_page.pack();
				edit_outflow_shell.pack();
				edit_outflow_shell.open();
			}
		}
	}
}
