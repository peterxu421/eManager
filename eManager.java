import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class eManager{
	Shell rootShell;
	Shell welcome_shell;
	WelcomePage welcome_page;
	Display display;

	public eManager() {
		display = new Display();
		rootShell = new Shell(display);
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
		open.setText("Open");
		MenuItem open_recent = new MenuItem(fileMenu, SWT.PUSH);
		open_recent.setText("Open Recent\t>");
		MenuItem quit = new MenuItem(fileMenu, SWT.PUSH);
		quit.setText("Quit");

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
		Image image = new Image(display, "resources/bg.png");
		rootShell.setMaximized(true);
		rootShell.setText("eManagerV1.0");
		rootShell.setBackgroundImage(image);
		rootShell.open();

		//menu->file
		open.addSelectionListener(new MenuListener());
		//open_recent.addSelectionListener(new MenuListener());
		quit.addSelectionListener(new MenuListener());
/*
		//menu->edit
		edit.addSelectionListener(new MenuListener());
		undo.addSelectionListener(new MenuListener());
		redo.addSelectionListener(new MenuListener());
		copy.addSelectionListener(new MenuListener());
		cut.addSelectionListener(new MenuListener());

		//menu->view
		pref.addSelectionListener(new MenuListener());
*/
		//menu-> help
		//help.addSelectionListener(new MenuListener());
		//readme.addSelectionListener(new MenuListener());
		version.addSelectionListener(new MenuListener());

		//welcome page
		welcome_shell = new Shell(display,SWT.NO_TRIM);
		welcome_shell.setLocation(400, 250);
		welcome_page = new WelcomePage(welcome_shell, SWT.None);

		welcome_page.pack();
		welcome_shell.pack();
		welcome_shell.open();
		while(!rootShell.isDisposed()){
			if(!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}

	class MenuListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent event) {
			if(((MenuItem) event.widget).getText().equals("Open")) {
				welcome_shell = new Shell(display,SWT.NO_TRIM);
				welcome_shell.setLocation(400, 250);
				welcome_page = new WelcomePage(welcome_shell, SWT.None);

				welcome_page.pack();
				welcome_shell.pack();
				welcome_shell.open();
			}

			if(((MenuItem) event.widget).getText().equals("Open_recent")) {
				//invoke path of recent projects
			}


			if(((MenuItem) event.widget).getText().equals("Version")) {
				Shell version_shell = new Shell(display,SWT.None);
				version_shell.setLocation(400, 250);
				fileVersion version_page = new fileVersion(version_shell, SWT.None);

				version_page.pack();
				version_shell.pack();
				version_shell.open();
			}

			if(((MenuItem) event.widget).getText().equals("Quit")) {
				display.dispose();
			}
		}
	}

	public static void main(String[] args)
	{	
		eManager eManager = new eManager();
	}



}
