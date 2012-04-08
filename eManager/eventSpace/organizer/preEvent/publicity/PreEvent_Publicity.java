package eManager.eventSpace.organizer.preEvent.publicity;
import java.io.File;
import org.apache.derby.iapi.services.io.FileUtil;
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
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import eManager.macro.SessionManager;

public class PreEvent_Publicity extends Composite {
	protected Composite left;
	protected Composite right;
	protected Gallery gallery;
	private GalleryItem group;
	private String eventName; 
	private String pictureDirectory;
	public PreEvent_Publicity(Composite parent, int style) {
		super(parent, style);
		
		eventName = SessionManager.getCurrentEvent().getEventName();
		pictureDirectory = "Pictures" + File.separator + eventName;
		// Background
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		this.setLayout(gridLayout);

		left = new Composite(this, SWT.None);
		GridData leftData = new GridData(500, 500);
		left.setLayoutData(leftData);

		right = new Composite(this, SWT.None);
		GridData rightData = new GridData(150, 500);
		right.setLayoutData(rightData);

		// left->
		GridLayout leftLayout = new GridLayout();
		leftLayout.numColumns = 1;
		left.setLayout(leftLayout);

		gallery = new Gallery(left, SWT.V_SCROLL | SWT.MULTI);
		GridData galleryData = new GridData(500, 500);
		gallery.setLayoutData(galleryData);

		DefaultGalleryGroupRenderer gr = new DefaultGalleryGroupRenderer();
		gr.setMinMargin(2);
		gr.setItemHeight(150);
		gr.setItemWidth(150);
		gr.setAutoMargin(false);
		gallery.setGroupRenderer(gr);

		DefaultGalleryItemRenderer ir = new DefaultGalleryItemRenderer();
		gallery.setItemRenderer(ir);
		group = new GalleryItem(gallery, SWT.NONE);
		group.setText("Images");
		group.setExpanded(true);

		fillImages();

		// right->
		GridLayout rightLayout = new GridLayout();
		rightLayout.numColumns = 1;
		right.setLayout(rightLayout);

		Button importPic = new Button(right, SWT.PUSH);
		GridData importPicData = new GridData(130, 50);
		importPic.setLayoutData(importPicData);
		importPic.setText("Import");
		importPic.addSelectionListener(new ImportAdapter());
		
		Button fromURL = new Button(right, SWT.PUSH);
		GridData fromURLData = new GridData(130, 50);
		fromURL.setText("From URL");
		fromURL.setLayoutData(fromURLData);
		fromURL.addSelectionListener(new FromURLAdatper());

		Button edit = new Button(right, SWT.PUSH);
		GridData editData = new GridData(130, 50);
		edit.setText("Edit");
		edit.setLayoutData(editData);
		edit.addSelectionListener(new EditAdapter());

		Button delete = new Button(right, SWT.PUSH);
		GridData deleteData = new GridData(130, 50);
		delete.setText("Delete");
		delete.setLayoutData(deleteData);
		delete.addSelectionListener(new DeleteAdapter());

	}

	private void addImage(final String path, final String filename) {
		String source = path + File.separator + filename;
		String target = pictureDirectory + File.separator + filename;
		File sourceFile = new File(source);
		File targetFile = new File(target);
		FileUtil.copyFile(sourceFile, targetFile);
		Image image = new Image(getDisplay(), new ImageData(target));
		if (image != null) {
			GalleryItem item = new GalleryItem(group, SWT.None);
			item.setImage(image);
			item.setText(filename);
		}
	}
	
	/*Directory is created here*/
	private void fillImages() {
		File imageDirectory = new File(pictureDirectory);
		if (!imageDirectory.exists())
			imageDirectory.mkdir();

		String[] images = imageDirectory.list();
		String[] imagesLowerCase = new String[images.length];
		for (int i = 0; i < images.length; i++) {
			imagesLowerCase[i] = images[i].toLowerCase();
			if(imagesLowerCase[i].contains(".jpg") || imagesLowerCase[i].contains(".png") || imagesLowerCase[i].contains(".bmp")){
				Image image = new Image(getDisplay(), new ImageData(pictureDirectory + File.separator + images[i]));
				if (image != null) {
					GalleryItem item = new GalleryItem(group, SWT.None);
					item.setImage(image);
					item.setText(images[i]);
				}
			}
		}
	}

	private class ImportAdapter extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Shell open = new Shell(getDisplay());
			open.setLocation(300, 150);
			open.setText("Open Image");
			Image icon = new Image(getDisplay(), "resources/eManager.png");
			open.setImage(icon);
			FileDialog browser = new FileDialog(open, SWT.OPEN | SWT.MULTI);
			browser.setFilterExtensions(new String[] { "*.jpg", "*.png",
					"*.bmp"});
			browser.open();

			String[] filenames = browser.getFileNames();
			String path = browser.getFilterPath();

			for (int i = 0; i < filenames.length; i++) {
				addImage(path, filenames[i]);
			}
			gallery.redraw();
		}
	}
	private class EditAdapter extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Shell shell = new Shell(getDisplay());
			shell.setText("Edit Image");
			Image icon = new Image(getDisplay(), "resources/eManager.png");
			shell.setImage(icon);
			shell.setLocation(300, 100);
			if (gallery.getSelection().length != 0) {
				String imagePath = pictureDirectory + File.separator + gallery.getSelection()[0].getText();
				PhotoEditorPage page = new PhotoEditorPage(shell, SWT.NONE,	imagePath);
				page.pack();
				shell.pack();
				shell.open();
			}
		}
	}
	private class DeleteAdapter extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if (gallery.getSelectionCount() != 0) {
				int length = gallery.getSelection().length;
				GalleryItem[] images = gallery.getSelection();
				for (int i = 0; i < length; i++) {
					group.remove(images[i]);
					File file = new File(pictureDirectory + File.separator
							+ images[i].getText());
					System.out.println(file);
					file.delete();
				}
			}
		}
	}
	private class FromURLAdatper extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e){
			Shell shell = new GetImageFromURL(getShell(), SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM, group);
			Image icon = new Image(getDisplay(), "resources/eManager.png");
			shell.setText("eManager - Image Grabber");
			shell.setImage(icon);
			shell.setLocation(400, 200);
			shell.open();
		}
	}
}
