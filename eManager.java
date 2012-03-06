import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;


public class eManager{
	public static void main(String[] args)
	{
		Display display = new Display();
		Shell rootShell = new Shell(display);
		//menu
		Menu menu = new Menu(rootShell, SWT.BAR);
		rootShell.setMenuBar(menu);
		
		//menu->
		MenuItem file = new MenuItem(menu, SWT.CASCADE);
		file.setText("File");
		MenuItem edit = new MenuItem(menu, SWT.CASCADE);
		edit.setText("Edit");
		MenuItem view = new MenuItem(menu, SWT.CASCADE);
		view.setText("View");
		MenuItem mode = new MenuItem(menu, SWT.CASCADE);
		mode.setText("Mode");
		MenuItem help = new MenuItem(menu, SWT.CASCADE);
		help.setText("Help");
		
		//menu->file
		Menu fileMenu = new Menu(rootShell, SWT.DROP_DOWN);
		file.setMenu(fileMenu);
		MenuItem open = new MenuItem(fileMenu, SWT.PUSH);
		open.setText("Open\tCtrl+O");
		MenuItem open_recent = new MenuItem(fileMenu, SWT.PUSH);
		open_recent.setText("Open Recent\t>");
		MenuItem quit = new MenuItem(fileMenu, SWT.PUSH);
		quit.setText("Quit\tCtrl+Q");
		
		//menu->edit
		Menu editMenu = new Menu(rootShell, SWT.DROP_DOWN);
		edit.setMenu(editMenu);
		MenuItem undo = new MenuItem(editMenu, SWT.PUSH);
		undo.setText("Undo\tCtrl+Z");
		MenuItem redo = new MenuItem(editMenu, SWT.PUSH);
		redo.setText("Redo\tCtrl+Shift+Z");
		MenuItem copy = new MenuItem(editMenu, SWT.PUSH);
		copy.setText("Copy\tCtrl+C");
		MenuItem cut = new MenuItem(editMenu, SWT.PUSH);
		cut.setText("Cut\tCtrl+X");
		
		//menu->view
		Menu viewMenu = new Menu(rootShell, SWT.DROP_DOWN);
		view.setMenu(viewMenu);
		MenuItem pref = new MenuItem(viewMenu, SWT.PUSH);
		pref.setText("eManager Preferences");
		MenuItem exam = new MenuItem(viewMenu, SWT.PUSH);
		exam.setText("eManager Examples");
		MenuItem rag = new MenuItem(viewMenu, SWT.PUSH);
		rag.setText("Rag&Flag");
		MenuItem osa = new MenuItem(viewMenu, SWT.PUSH);
		osa.setText("OSA Venues");
		
		//menu-> mode
		Menu modeMenu = new Menu(rootShell, SWT.DROP_DOWN);
		mode.setMenu(modeMenu);
		MenuItem manager = new MenuItem(modeMenu, SWT.PUSH);
		manager.setText("Manager Mode");
		MenuItem booking = new MenuItem(modeMenu, SWT.PUSH);
		booking.setText("Booking Mode");
		
		//menu-> help
		Menu helpMenu = new Menu(rootShell, SWT.DROP_DOWN);
		help.setMenu(helpMenu);
		MenuItem readme = new MenuItem(helpMenu, SWT.PUSH);
		readme.setText("Read Me");
		MenuItem version = new MenuItem(helpMenu, SWT.PUSH);
		version.setText("Version");
		
		//shell
		
		
		/*Test Block*/
		
		Image image = new Image(display, "resources/bg.png");
		/*End Of Test Block*/

		rootShell.setMaximized(true);
		rootShell.setText("eManagerV1.0");
		rootShell.setBackgroundImage(image);
		rootShell.open();
		
		//welcome page
		Shell welcome_shell = new Shell(display,SWT.NO_TRIM | SWT.ON_TOP);
		welcome_shell.setLocation(400, 250);
		WelcomePage welcome_page = new WelcomePage(welcome_shell, SWT.None);
		
		welcome_page.pack();
		welcome_shell.pack();
		welcome_shell.open();
		while(!rootShell.isDisposed()){
			if(!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
}
