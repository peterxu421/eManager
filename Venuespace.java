import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

class Venuespace extends Composite{
	
	Composite body;
	Composite left;
	Composite right;
	Composite header;
	
	private String[] optionMenu = new String[]{
		"Venue Management",
		"Venue Registration",
	};
	
	private String[][] tabList = new String[][]{
			{	"Venue List",
				"Booking Applications",
			},
			{	
				"Regulations and Rules",
				"Select and Book",
				"Check My Applications"
			}
	};

	//Constructor
	public Venuespace(Composite parent, int style, boolean[] boolMode){
		super(parent, style);
		//setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		this.setLayout(gridLayout);
		
		// header
	    header = new Composite(this,SWT.None);
	   // header.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	    header.setLayoutData(new GridData(1000, 100));
	    //header.setBounds(0, 0, 1000, 100);
	    //header->
	    FillLayout headerLayout = new FillLayout();
	    headerLayout.marginHeight = 20;
	    headerLayout.marginWidth = 20;
	    headerLayout.spacing = 50;
	    header.setLayout(headerLayout);

	    
	    //optionBar->
	    Composite optionBar = new Composite(this, SWT.None);
	    GridLayout optionBarLayout = new GridLayout();
	    optionBarLayout.marginLeft = 154;
	    optionBarLayout.numColumns = 5;
	    optionBar.setLayout(optionBarLayout);
	   // optionBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	    optionBar.setLayoutData(new GridData(1000, 50));
	    
	    //optionBar->options
	    int num = optionMenu.length;
	    Button[] buttons = new Button[num];
	    for(int i=0; i<num; i++)
	    {
	    	buttons[i] = new Button(optionBar, SWT.PUSH);
	    	buttons[i].setLayoutData(new GridData(130, 40));
	    	buttons[i].setText(optionMenu[i]);
	    	buttons[i].addSelectionListener(new OptionSelectionAdapter());
	    	buttons[i].setEnabled(boolMode[i]);
	    }
	    
	    //body->
	    body = new Composite(this, SWT.None);
	    //body.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	    body.setLayoutData(new GridData(1000, 460));
	    
	    
	    GridLayout bodyLayout = new GridLayout();
	    bodyLayout.numColumns = 2;
	    body.setLayout(bodyLayout);
	    left = new Composite(body, SWT.None);
	   // left.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	    right = new Composite(body, SWT.None);
	 //   right.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	    left.setLayoutData(new GridData(150,450));
	    right.setLayoutData(new GridData(800,450));
	    
	    Label eventName = new Label(header, SWT.WRAP);
	    eventName.setFont(SWTResourceManager.getFont("Courier New", 11, SWT.NORMAL));
	  //  eventName.setBounds(0, 10, 1000, 25);
	   // eventName.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	    eventName.setText("NUS Office of Studnet Affairs\n© Copyright 2001-08 National University of Singapore. All Rights Reserved.");
	    
	    Label eventDescription = new Label(header, SWT.WRAP);
	    eventDescription.setFont(SWTResourceManager.getFont("Courier New", 11, SWT.NORMAL));
	   // eventDescription.setBounds(0, 50, 1000, 40);
	   // eventDescription.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	    eventDescription.setText("We are committed to providing you with easy accessibility and responsiveness in our services.");
	    
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
	    	buttons[i].setLayoutData(new GridData(130, 40));
	    	buttons[i].addSelectionListener(new TabSelectionAdapter());
	    }
	    
	    //body -> right panel
	    VenueManagement_VenueList vList  = new VenueManagement_VenueList(right, SWT.NONE);
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
			    VenueManagement_VenueList vList  = new VenueManagement_VenueList(right, SWT.NONE);
			    vList.pack();
			}
			else if(name.equals("Booking Applications"))
			{
			    VenueManagement_BookingApplications vApplicants  = new VenueManagement_BookingApplications(right, SWT.NONE);
			    vApplicants.pack();
			}
			else if(name.equals("Regulations and Rules")){
				VenueBooking_InstructionPage vInstruc = new VenueBooking_InstructionPage (right, SWT.BORDER);
	    		vInstruc.pack();
			}
			else if(name.equals("Select and Book"))
			{
			    VenueBooking_VenueList vList  = new VenueBooking_VenueList(right, SWT.NONE);
			    vList.pack();
			}
			else if(name.equals("Check My Applications"))
			{
			    VenueBooking_CheckMyApplication vCheckMyApp  = new VenueBooking_CheckMyApplication(right, SWT.NONE);
			    vCheckMyApp.pack();
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
				    	button.setLayoutData(new GridData(130,40));
				    	button.addSelectionListener(new TabSelectionAdapter());
				    	if(j==0)button.notifyListeners(SWT.Selection, null); // to display the the first page of each component in the menu bar
 					}
				}
			}
			left.pack();
			right.pack();
			body.pack();
		}
	}
}
