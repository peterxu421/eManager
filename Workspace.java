import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

class Workspace extends Composite{
	
	Composite body;
	Composite left;
	Composite right;
	Event event = null;
	
	private String[] optionList = new String[]{
		"Organizer",
		"Facilitator",
		"Participants",
		"Event Registration"
	};
	
	private String[][] tabList = new String[][]{
			{	"Pre-Event",
				"Actual-Event",
				"Meeting",
				"Budget",
				"Feedback"
			},
			{	"Allocation of Manpower"
			},
			{	"Itinerary",
				"Packing List"
			},
			{	"Event Registration"
			}
	};

	//Constructor
	public Workspace(Composite parent, int style,Event event){
		super(parent, style);
		this.event = event;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		this.setLayout(gridLayout);
		
		//header
	    GridData headerData = new GridData(700,60);
	    Composite header = new Composite(this,SWT.None);
	    header.setLayoutData(headerData);
	    
	    //optionBar
	    GridData optionBarData = new GridData(700, 30);
	    Composite optionBar = new Composite(this, SWT.None);
	    optionBar.setLayoutData(optionBarData);
	    
	    //optionBar->
	    GridLayout optionBarLayout = new GridLayout();
	    optionBarLayout.marginLeft = 100;
	    optionBarLayout.numColumns = 4;
	    optionBar.setLayout(optionBarLayout);
	    
	    //optionBar->options
	    int num = optionList.length;
	    Button[] buttons = new Button[num];
	    for(int i=0; i<num; i++)
	    {
	    	buttons[i] = new Button(optionBar, SWT.PUSH);
	    	buttons[i].setText(optionList[i]);
	    	buttons[i].addSelectionListener(new OptionSelectionAdapter());
	    }
	    
	    //body
	    GridData bodyData = new GridData(900, 450);
	    body = new Composite(this, SWT.None);
	    body.setLayoutData(bodyData);
	    
	    //body->
	    GridLayout bodyLayout = new GridLayout();
	    bodyLayout.numColumns = 2;
	    body.setLayout(bodyLayout);
	    
	    //body->left & right panel
	    GridData leftData = new GridData(100,450);
	    GridData rightData = new GridData(800,450);
	    left = new Composite(body, SWT.None);
	    right = new Composite(body, SWT.None);
	    left.setLayoutData(leftData);
	    right.setLayoutData(rightData);

	    //header->
	    FillLayout headerLayout = new FillLayout();
	    headerLayout.marginHeight = 20;
	    headerLayout.marginWidth = 20;
	    headerLayout.spacing = 50;
	    header.setLayout(headerLayout);
	    Label eventName = new Label(header, SWT.NONE);
	    eventName.setText(event.getEventName());
	    Label eventDescription = new Label(header, SWT.NONE);
	    eventDescription.setText(event.getEventDescription());
	    
	    //body->left panel
	    GridLayout leftLayout = new GridLayout();
	    leftLayout.numColumns = 1;
	    left.setLayout(leftLayout);
	    
	    num = tabList[0].length;
	    buttons = new Button[num];
	    for(int i=0; i<num; i++)
	    {
	    	buttons[i] = new Button(left, SWT.PUSH);
	    	buttons[i].setText(tabList[0][i]);
	    	buttons[i].setLayoutData(new GridData(80,20));
	    	buttons[i].addSelectionListener(new TabSelectionAdapter());
	    }
	    
	    //body -> right panel
	    FillLayout rightLayout = new FillLayout();
	    right.setLayout(rightLayout);
	    EventPlanning_PreEvent pre_event  = new EventPlanning_PreEvent(right, SWT.None, event);
	    pre_event.pack();
	  
	}
	class TabSelectionAdapter extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e){
			if(right.getChildren().length != 0)
			{
				right.getChildren()[0].dispose();
			}
			String name = ((Button)e.getSource()).getText();
			if(name.equals("Pre-Event"))
			{
				EventPlanning_PreEvent pre_event = new EventPlanning_PreEvent(right, SWT.None, event);
				pre_event.pack();
			}
			else if(name.equals("Meeting"))
			{
				EventPlanning_Meeting meeting = new EventPlanning_Meeting(right, SWT.None, event);
				meeting.pack();
			}
			else if(name.equals("Feedback"))
			{
				EventPlanning_FeedBack feedback = new EventPlanning_FeedBack(right, SWT.None);
				feedback.pack();
			}
			else if(name.equals("Budget"))
			{
				EventPlanning_Budget budget = new EventPlanning_Budget(right, SWT.None, event);
				budget.pack();
			}
			else if(name.equals("Actual-Event"))
			{
				EventPlanning_ActualEvent actual_event = new EventPlanning_ActualEvent(right, SWT.NONE);
				actual_event.pack();
			}
			else if(name.equals("Allocation of Manpower"))
			{
				eP_facilitator_AllocOfManpower allocation = new eP_facilitator_AllocOfManpower(right,SWT.None);
				allocation.pack();
			}
			else if(name.equals("Itinerary"))
			{
				eP_participants_itinerary itinerary = new eP_participants_itinerary(right, SWT.None);
				itinerary.pack();
			}
			else if(name.equals("Packing List"))
			{
				eP_participants_packingList packing = new eP_participants_packingList(right, SWT.None);
				packing.pack();
			}
			else if(name.equals("Event Registration"))
			{
				eP_eventRegistration register = new eP_eventRegistration(right, SWT.None);
				register.pack();
			}
			right.pack();
		}
	}
	class OptionSelectionAdapter extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e){
			int num = left.getChildren().length;
			for(int i=0; i<num; i++)
			{
				left.getChildren()[0].dispose();
			}
			left.pack();
			num = right.getChildren().length;
			for(int i=0; i<num; i++)
			{
				right.getChildren()[0].dispose();
			}
			right.pack();
			String name = ((Button)e.getSource()).getText();
			for(int i=0; i<optionList.length; i++)
			{
				if(name.equals(optionList[i]))
				{
					for(int j=0; j<tabList[i].length; j++)
					{
				    	Button button = new Button(left, SWT.PUSH);
				    	button.setText(tabList[i][j]);
				    	button.setLayoutData(new GridData(80,20));
				    	button.addSelectionListener(new TabSelectionAdapter());
				    	if(j==0)button.notifyListeners(SWT.Selection, null);
					}
				}
			}
			left.pack();
			right.pack();
			body.pack();
		}
	}
}
