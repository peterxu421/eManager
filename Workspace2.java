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

class Workspace2 extends Composite{
	
	Composite body;
	Composite left;
	Composite right;
	
	private String[] optionMenu = new String[]{
		"Venue Management",
		"Venue Registration",
	};
	
	private String[][] tabList = new String[][]{
			{	"Venue List",
				"Applicants",
			},
			{	"View Venues",
				"Book a Venue"
			}
	};

	//Constructor
	public Workspace2(Composite parent, int style){
		super(parent, style);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		this.setLayout(gridLayout);
	    Composite header = new Composite(this,SWT.None);
	    header.setLayoutData(new GridData(770,60));
	    Composite optionBar = new Composite(this, SWT.None);
	    optionBar.setLayoutData(new GridData(771, 30));
	    
	    //optionBar->
	    GridLayout optionBarLayout = new GridLayout();
	    optionBarLayout.marginLeft = 110;
	    optionBarLayout.numColumns = 4;
	    optionBar.setLayout(optionBarLayout);
	    
	    //optionBar->options
	    int num = optionMenu.length;
	    Button[] buttons = new Button[num];
	    for(int i=0; i<num; i++)
	    {
	    	buttons[i] = new Button(optionBar, SWT.PUSH);
	    	buttons[i].setText(optionMenu[i]);
	    	buttons[i].addSelectionListener(new OptionSelectionAdapter());
	    }
	    body = new Composite(this, SWT.None);
	    body.setLayoutData(new GridData(769, 463));
	    
	    //body->
	    GridLayout bodyLayout = new GridLayout();
	    bodyLayout.numColumns = 2;
	    body.setLayout(bodyLayout);
	    left = new Composite(body, SWT.None);
	    right = new Composite(body, SWT.None);
	    left.setLayoutData(new GridData(100,450));
	    right.setLayoutData(new GridData(660,450));

	    //header->
	    FillLayout headerLayout = new FillLayout();
	    headerLayout.marginHeight = 20;
	    headerLayout.marginWidth = 20;
	    headerLayout.spacing = 50;
	    header.setLayout(headerLayout);
	    Label eventName = new Label(header, SWT.NONE);
	    eventName.setText("(Venue owner)");
	    Label eventDescription = new Label(header, SWT.NONE);
	    eventDescription.setText("(Venue owner's message)");
	    
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
	    VenueManagement_VenueList vList  = new VenueManagement_VenueList(right, SWT.NULL);
	    vList.pack();
	}
	class TabSelectionAdapter extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e){
			if(right.getChildren().length != 0)
			{
				right.getChildren()[0].dispose();
			}
			String name = ((Button)e.getSource()).getText();
			if(name.equals("Venue List"))
			{
			    VenueManagement_VenueList vList  = new VenueManagement_VenueList(right, SWT.None);
			    vList.pack();
			}
			else if(name.equals("Applicants"))
			{
			    VenueManagement_VenueApplicants vApplicants  = new VenueManagement_VenueApplicants(right, SWT.None);
			    vApplicants.pack();
			}
			else if(name.equals("View Venues"))
			{
			    VenueBooking_VenueList vList  = new VenueBooking_VenueList(right, SWT.None);
			    vList.pack();
			}
			else if(name.equals("Book a Venue"))
			{
			    VenueBooking_VenueBooking vBook  = new VenueBooking_VenueBooking(right, SWT.None);
			    vBook.pack();
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
			for(int i=0; i<optionMenu.length; i++)
			{
				if(name.equals(optionMenu[i]))
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