import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;


public class GetImageFromURL extends Shell {
	private Text url;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			GetImageFromURL shell = new GetImageFromURL(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public GetImageFromURL(Display display) {
		super(display, SWT.SHELL_TRIM);

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
		public void widgetSelected(SelectionEvent e) {

			String link = "";
			if(url.getText().contains("http://")) {
				link = url.getText().trim().substring(url.getText().indexOf("http://")+7);
				System.out.println(link);
			} else {
				link = url.getText().trim();
			}
			if(link.equals("") || !link.contains("www.") || !link.contains("/")) {
				MessageBox warningPage  = new MessageBox(getDisplay().getActiveShell(), SWT.OK | SWT.ICON_WARNING );
				warningPage.setText("Warning!");
				warningPage.setMessage("Please input a valid URL.");
				warningPage.open(); 
			} 
			else {
				System.out.println(link);
				String host = link.substring(0, link.indexOf("/"));
				System.out.println(host);

				String filePath = link.substring(link.indexOf("/"));
				System.out.println(filePath);

				String fileName = link.substring(link.lastIndexOf("/")+1);
				System.out.println(fileName);

				String path = "/Users/niwde/Documents/workspace/CS2105/";
				int port = 80;

				// Create a TCP socket and connect to the host:port.
				FileOutputStream fos;
				Socket socket;

				try {
					socket = new Socket(host, port);
					socket.setSoTimeout(10000);

					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					fos = new FileOutputStream(path + fileName);

					out.println("GET " + filePath + " HTTP/1.1" + "\r\n" + "Host: " + host + "\r\n\r\n");
					out.flush();
					System.out.println("Sent: " + "GET " + filePath + " HTTP/1.1" + "\r\n" + "Host: " + host + "\r\n\r\n");
					DataInputStream dis = new DataInputStream(socket.getInputStream());

					byte[] byteArray;
					String lengthOfArray = "";
					String checker = "";
					//get the length of the whole thing
					while((checker = dis.readLine()) != null) {
						if(checker.contains("Content-Length")) {
							lengthOfArray = checker.substring(checker.indexOf(" ") + 1, checker.length());

							byteArray = new byte[Integer.parseInt(lengthOfArray)];

							//skip two lines beacuse of the Content-type header
							checker = dis.readLine();
							checker = dis.readLine();

							//reads in everything
							dis.readFully(byteArray);

							//write the byteArray into the file
							fos.write(byteArray);
							fos.flush();
						}
					}
					// Close the I/O streams.
					fos.close();
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
					warningPage.setMessage("Time taken to download image too long or the image does not exist, please input another URL.");
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
