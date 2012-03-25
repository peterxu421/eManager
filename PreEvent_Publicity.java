
import org.eclipse.nebula.widgets.gallery.DefaultGalleryGroupRenderer;
import org.eclipse.nebula.widgets.gallery.DefaultGalleryItemRenderer;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
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
		Image image = new Image(getDisplay(), "resources/me.jpg");
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
//		for(int i=0; i<3; i++){
//			Canvas canvas = new Canvas(left, SWT.None);
//			GridData imageGridData = new GridData(250,250);
//			canvas.setLayoutData(imageGridData);
//			canvas.addPaintListener(new PaintListener() {
//				public void paintControl(PaintEvent e) {
//					Image image = new Image(getDisplay(), new ImageData("resources/me.jpg").scaledTo(250, 250)); 
//					e.gc.drawImage(image, 0,0);
//					image.dispose();
//				}
//			});
//		}
		Gallery gallery = new Gallery(left, SWT.V_SCROLL | SWT.MULTI);
		GridData galleryData = new GridData();
		gallery.setLayoutData(galleryData);
		
		DefaultGalleryGroupRenderer gr = new DefaultGalleryGroupRenderer();
		gr.setMinMargin(2);
		gr.setItemHeight(56);
		gr.setItemWidth(72);
		gr.setAutoMargin(true);
		gallery.setGroupRenderer(gr);
		
		DefaultGalleryItemRenderer ir = new DefaultGalleryItemRenderer();
		gallery.setItemRenderer(ir);
		
		for (int g = 0; g < 2; g++) {
			GalleryItem group = new GalleryItem(gallery, SWT.NONE);
			group.setText("Group " + g); //$NON-NLS-1$
			group.setExpanded(true);

			for (int i = 0; i < 50; i++) {
				GalleryItem item = new GalleryItem(group, SWT.NONE);
				if (image != null) {
					item.setImage(image);
				}
				item.setText("Item " + i); //$NON-NLS-1$
			}
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
	
}
