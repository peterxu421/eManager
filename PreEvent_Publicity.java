<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> de2b69bfc67aaafeb9b3c8d5b15122ffabc7fcd6

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;


public class PreEvent_Publicity extends Composite{
	protected Composite left;
	protected Composite right;
	public PreEvent_Publicity(Composite parent, int style) {
		super(parent, style);
		//Background
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		this.setLayout(gridLayout);
		
		left = new Composite(this, SWT.None);
		GridData leftData = new GridData(750,500);
		left.setLayoutData(leftData);
		
		right = new Composite(this, SWT.None);
		GridData rightData = new GridData(150,500);
		right.setLayoutData(rightData);
		
		//left->
		GridLayout leftLayout = new GridLayout();
		leftLayout.numColumns = 3;
		left.setLayout(leftLayout);
		for(int i=0; i<3; i++){
			Canvas canvas = new Canvas(left, SWT.None);
			GridData imageGridData = new GridData(250,250);
			canvas.setLayoutData(imageGridData);
			canvas.addPaintListener(new PaintListener() {
				public void paintControl(PaintEvent e) {
					Image image = new Image(getDisplay(), new ImageData("resources/girls.jpg").scaledTo(250, 250)); 
					e.gc.drawImage(image, 0,0);
					image.dispose();
				}
			});
		}
		
		//right->
		GridLayout rightLayout = new GridLayout();
		rightLayout.numColumns = 1;
		right.setLayout(rightLayout);
		
		Button importPic = new Button(right, SWT.PUSH);
		GridData buttonData = new GridData(130,50);
		importPic.setLayoutData(buttonData);
		importPic.setText("Import");
		importPic.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				Shell open = new Shell(getDisplay());
				FileDialog browser = new FileDialog(open, SWT.OPEN);
				String path = browser.open();
				if(path != null){
					addImage(path);
				}
			}
		});
		
	}
	public void addImage(final String path){
		Canvas canvas = new Canvas(left, SWT.None);
		GridData imageGridData = new GridData(250,250);
		canvas.setLayoutData(imageGridData);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				Image image = new Image(getDisplay(), new ImageData(path).scaledTo(250, 250)); 
				e.gc.drawImage(image, 0,0);
				image.dispose();
			}
		});
		left.layout();
	}
	
	public static void main(String[] args){
		Display display = new Display();
		Shell shell = new Shell(display);
		PreEvent_Publicity page = new PreEvent_Publicity(shell, SWT.None);
		page.pack();
		shell.pack();
		shell.open();
		while(!shell.isDisposed()){
			if(!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
	
<<<<<<< HEAD
=======
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;


public class PreEvent_Publicity extends Composite{
	public PreEvent_Publicity(Composite parent, int style) {
		super(parent, style);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;
		this.setLayout(gridLayout);
		
		
	}

	public static void main(String[] args) {
		
	}

>>>>>>> cdee70e3443876392d67c564a5d0bb7ea3b5d988
=======
>>>>>>> de2b69bfc67aaafeb9b3c8d5b15122ffabc7fcd6
}
