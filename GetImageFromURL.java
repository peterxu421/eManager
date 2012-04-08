import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

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

public class GetImageFromURL extends Shell {
	private Text url;
	private GalleryItem group;

	public GetImageFromURL(Shell shell, int style, GalleryItem group) {
		super(shell, style);

		Label lblImageUrl = new Label(this, SWT.NONE);
		lblImageUrl.setBounds(21, 29, 70, 14);
		lblImageUrl.setText("Image URL:");

		url = new Text(this, SWT.BORDER);
		url.setBounds(97, 26, 147, 19);

		Button btnGetImage = new Button(this, SWT.NONE);
		btnGetImage.setBounds(21, 63, 94, 28);
		btnGetImage.setText("Get Image");

		Button btnCancel = new Button(this, SWT.NONE);
		btnCancel.setBounds(150, 63, 94, 28);
		btnCancel.setText("Cancel");
		createContents();

		btnGetImage.addSelectionListener(new getImage());
		btnCancel.addSelectionListener(new cancel());
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(285, 140);

	}
	class cancel extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			getShell().dispose();
		}
	}

	class getImage extends SelectionAdapter {
		@SuppressWarnings("deprecation")
		public void widgetSelected(SelectionEvent e) {

			String link = "";
			if(url.getText().contains("http://")) {
				link = url.getText().trim().substring(url.getText().indexOf("http://")+7);
				System.out.println(link);
			} else {
				link = url.getText().trim();
			}
			if(link.equals("") || !link.contains("/")) {
				MessageBox warningPage  = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.ICON_WARNING );
				warningPage.setText("Warning!");
				warningPage.setMessage("Please input a valid URL.");
				warningPage.open(); 
			} 
			else {
				String host = link.substring(0, link.indexOf("/"));
				String filePath = link.substring(link.indexOf("/"));
				String fileName = link.substring(link.lastIndexOf("/")+1);
				String path = "Pictures";
				int port = 80;

				// Create a TCP socket and connect to the host:port.
				FileOutputStream fos;
				Socket socket;

				try {
					socket = new Socket(host, port);
					socket.setSoTimeout(100000);

					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					fos = new FileOutputStream(path + File.separator + fileName, true);

					out.println("GET " + filePath + " HTTP/1.1" + "\r\n" + "Host: " + host + "\r\n\r\n");
					out.flush();
					System.out.println("Sent: " + "GET " + filePath + " HTTP/1.1" + "\r\n" + "Host: " + host + "\r\n\r\n");
					DataInputStream dis = new DataInputStream(socket.getInputStream());

					byte[] byteArray;
					String lengthOfArray = "";
					String checker = "";
					int arrayLength = 0;
					//get the length of the whole thing
					while((checker = dis.readLine()) != null) {
						if(checker.contains("Not Found")) {
							MessageBox warningPage  = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.ICON_WARNING );
							warningPage.setText("Warning!");
							warningPage.setMessage("File not found, please enter URL again.");
							warningPage.open(); 
							break;
						}
						if(checker.contains("Content-Length")) {
							lengthOfArray = checker.substring(checker.indexOf(" ") + 1, checker.length());
							System.out.println(lengthOfArray);
							arrayLength = Integer.parseInt(lengthOfArray);

							//skip two lines beacuse of the Content-type header
							checker = dis.readLine();
						}
						if(checker.contains("Content-Type: ")) {
							dis.readLine();
							byteArray = new byte[arrayLength];
							//reads in everything
							dis.readFully(byteArray);
							//write the byteArray into the file
							fos.write(byteArray);
							fos.flush();
						}
						System.out.println("fsdfsdfsdfsdf " + checker);
					}
					MessageBox warningPage  = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.ICON_WARNING );
					warningPage.setText("Thanks for waiting...");
					warningPage.setMessage("Image downloaded!");
					warningPage.open(); 
					// Close the I/O streams.
					fos.close();
					Image image = new Image(getDisplay(),path + File.separator + fileName);
					GalleryItem item = new GalleryItem(group, SWT.None);
					item.setImage(image);
				} catch (UnknownHostException e1) {
					MessageBox warningPage  = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.ICON_WARNING );
					warningPage.setText("Warning!");
					warningPage.setMessage("Invalid address, please input a valid URL.");
					warningPage.open(); 
				} catch (FileNotFoundException e1) {
					MessageBox warningPage  = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.ICON_WARNING );
					warningPage.setText("Warning!");
					warningPage.setMessage("Please input a valid URL.");
					warningPage.open(); 
				} catch (SocketTimeoutException e1) {
					MessageBox warningPage  = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.ICON_WARNING );
					warningPage.setText("Warning!");
					warningPage.setMessage("File is either too big or the connection is too slow, please input another image link.");
					warningPage.open(); 
				} catch (IOException e1) {
					MessageBox warningPage  = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.ICON_WARNING );
					warningPage.setText("Warning!");
					warningPage.setMessage("Please input a valid URL.");
					warningPage.open(); 
				}
			}
		}
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
