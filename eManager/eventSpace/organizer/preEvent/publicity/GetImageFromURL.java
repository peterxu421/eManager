package eManager.eventSpace.organizer.preEvent.publicity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import eManager.macro.SessionManager;

public class GetImageFromURL extends Shell {
	private Text urlText;
	private GalleryItem group;

	public GetImageFromURL(Shell shell, int style, GalleryItem group) {
		super(shell, style);		
		this.group = group;
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(285, 140);
		
		Label lblImageUrl = new Label(this, SWT.NONE);
		lblImageUrl.setBounds(21, 29, 70, 14);
		lblImageUrl.setText("Image URL:");

		urlText = new Text(this, SWT.BORDER);
		urlText.setBounds(97, 26, 147, 19);

		Button btnGetImage = new Button(this, SWT.NONE);
		btnGetImage.setBounds(21, 63, 94, 28);
		btnGetImage.setText("Get Image");

		Button btnCancel = new Button(this, SWT.NONE);
		btnCancel.setBounds(150, 63, 94, 28);
		btnCancel.setText("Cancel");
		
		btnGetImage.addSelectionListener(new getImage());
		btnCancel.addSelectionListener(new cancel());

	}
	class cancel extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			getShell().dispose();
		}
	}

	class getImage extends SelectionAdapter{
		public void widgetSelected(SelectionEvent event){
			FileOutputStream fos = null;
			String path = "Pictures" + File.separator + SessionManager.getCurrentEvent().getEventName();
			try {
				URL url = new URL(urlText.getText());
				InputStream fin = url.openStream();
				
				String filepath = url.getPath();
				String filename = filepath.substring(filepath.lastIndexOf("/")+1);
				
				if(new File(path + File.separator + filename).exists()){
					MessageBox warningPage  = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.ICON_WARNING );
					warningPage.setText("Warning!");
					warningPage.setMessage("Naming Conclict! Import failed!");
					warningPage.open(); 
					getShell().dispose();
				}
				else if(!filepath.contains(".png") && !filepath.contains(".jpg")){
					MessageBox warningPage  = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.ICON_WARNING );
					warningPage.setText("Warning!");
					warningPage.setMessage("Extension doesn't support!");
					warningPage.open(); 
					getShell().dispose();
				}
				else{
						fos = new FileOutputStream(path + File.separator + filename, true);
						byte[] b = new byte[2048];
						int length;
						while((length = fin.read(b))!=-1){
							fos.write(b, 0, length);
						}
						fin.close();
						fos.close();
						
						Image image = new Image(getDisplay(), path + File.separator + filename);
						GalleryItem item = new GalleryItem(group, SWT.None);
						item.setImage(image);
						
						MessageBox successPage  = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.ICON_WARNING );
						successPage.setText("Thanks for waiting...");
						successPage.setMessage("Image downloaded!");
						successPage.open();
				}	
			} catch (MalformedURLException e) {
				MessageBox warningPage  = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.ICON_WARNING );
				warningPage.setText("Warning!");
				warningPage.setMessage("Please input a valid URL.");
				warningPage.open(); 
			} catch (IOException e) {
				MessageBox warningPage  = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.ICON_WARNING );
				warningPage.setText("Warning!");
				warningPage.setMessage("Fail to import!");
				warningPage.open(); 
			}
			finally{
				getParent().redraw();
				getDisplay().getActiveShell().dispose();
			}
		}
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
