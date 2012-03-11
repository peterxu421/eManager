import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public class PreEvent_Publicity extends Composite{
	
	public PreEvent_Publicity(Composite parent, int style) {
		super(parent, style);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		this.setLayout(gridLayout);
		this.setSize(300,300);
		
		Canvas button = new Canvas(this, SWT.None);
		GridData imageGridData = new GridData(300,300);
		button.setLayoutData(imageGridData);
		
		button.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				Image image = new Image(getDisplay(), "resources/girls.jpg");
				e.gc.drawImage(image, 0,0);
				//image.dispose();
			}
		});
	}
	public static void main(String[] args){
		Display display = new Display();
		Shell shell = new Shell(display);
		
		PreEvent_Publicity page = new PreEvent_Publicity(shell, SWT.V_SCROLL);
		page.pack();
		shell.pack();
		shell.open();
		while(!shell.isDisposed()){
			if(!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
	
}
