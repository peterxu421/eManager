
import org.eclipse.nebula.widgets.gallery.DefaultGalleryGroupRenderer;
import org.eclipse.nebula.widgets.gallery.DefaultGalleryItemRenderer;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;


public class PreEvent_Publicity extends Composite{
	protected Composite left;
	protected Composite right;
	Gallery gallery;
	private GalleryItem group;
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
		leftLayout.numColumns = 1;
		left.setLayout(leftLayout);

		gallery = new Gallery(left, SWT.V_SCROLL | SWT.MULTI);
		GridData galleryData = new GridData(750,500);
		gallery.setLayoutData(galleryData);
		
		DefaultGalleryGroupRenderer gr = new DefaultGalleryGroupRenderer();
		gr.setMinMargin(2);
		gr.setItemHeight(200);
		gr.setItemWidth(200);
		gr.setAutoMargin(false);
		gallery.setGroupRenderer(gr);
		
		DefaultGalleryItemRenderer ir = new DefaultGalleryItemRenderer();
		gallery.setItemRenderer(ir);
		group = new GalleryItem(gallery, SWT.NONE);
		group.setText("Images");
		group.setExpanded(true);
		
		//right->
		GridLayout rightLayout = new GridLayout();
		rightLayout.numColumns = 1;
		right.setLayout(rightLayout);
		
		Button importPic = new Button(right, SWT.PUSH);
		GridData importPicData = new GridData(130,50);
		importPic.setLayoutData(importPicData);
		importPic.setText("Import");
		importPic.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				Shell open = new Shell(getDisplay());
				FileDialog browser = new FileDialog(open, SWT.OPEN | SWT.MULTI);
				browser.setFilterExtensions(new String[]{"*.jpg", "*.png", "*.bmp", "*.*"});
				browser.open();
				String[] filenames = browser.getFileNames();
				String path = browser.getFilterPath();
				for(int i=0; i<filenames.length; i++){
					addImage(path +"\\" + filenames[i]);
				}
				gallery.redraw();
			}
		});
		
		Button edit = new Button(right, SWT.PUSH);
		GridData editData = new GridData(130,50);
		edit.setText("Edit");
		edit.setLayoutData(editData);
		edit.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				Shell shell = new Shell(getShell(), SWT.None);
				if(gallery.getSelection().length!=0){
					Image image = gallery.getSelection()[0].getImage();
					PhotoEditorPage page = new PhotoEditorPage(shell, SWT.NONE, image);
					page.pack();
					shell.pack();
					shell.open();
				}
			}
		});
		
		Button delete = new Button(right, SWT.PUSH);
		GridData deleteData = new GridData(130, 50);
		delete.setText("Delete");
		delete.setLayoutData(deleteData);
		delete.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				if(gallery.getSelectionCount()!=0){
					int length = gallery.getSelection().length;
					GalleryItem[] images = gallery.getSelection();
					for(int i=0; i<length; i++){
						group.remove(images[i]);
					}
				}
			}
		});
	}
	public void addImage(final String path){
		Image image = new Image(getDisplay(), new ImageData(path));
		GalleryItem item = new GalleryItem(group, SWT.None);
		item.setText(path);
		if(image!=null){
			item.setImage(image);
		}
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
