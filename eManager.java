import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;


public class eManager{
	Shell rootShell;
	Shell welcome_shell;
	WelcomePage welcome_page;
	Display display;


	public eManager() {		
		display = new Display();
		rootShell = new Shell(display);
		// menu
		Menu menu = new Menu(rootShell, SWT.BAR);
		rootShell.setMenuBar(menu);

		// menu->
		MenuItem file = new MenuItem(menu, SWT.CASCADE);
		file.setText("File");
		MenuItem mode = new MenuItem(menu, SWT.CASCADE);
		mode.setText("Mode");
		MenuItem help = new MenuItem(menu, SWT.CASCADE);
		help.setText("Help");

		// menu->file
		Menu fileMenu = new Menu(rootShell, SWT.DROP_DOWN);
		file.setMenu(fileMenu);
		MenuItem open = new MenuItem(fileMenu, SWT.PUSH);
		open.setText("Open");
		MenuItem quit = new MenuItem(fileMenu, SWT.PUSH);
		quit.setText("Quit");

		// menu->  menuMode
		Menu modeItem = new Menu(rootShell, SWT.DROP_DOWN);
		mode.setMenu(modeItem);
		MenuItem eventManager = new MenuItem(modeItem, SWT.PUSH);
		eventManager.setText("Event Manager");
		MenuItem manager = new MenuItem(modeItem, SWT.PUSH);
		manager.setText("Venue Manager Mode");
		MenuItem applicant = new MenuItem(modeItem, SWT.PUSH);
		applicant.setText("Venue Applicant Mode");

		// menu-> help
		Menu helpMenu = new Menu(rootShell, SWT.DROP_DOWN);
		help.setMenu(helpMenu);
		MenuItem readme = new MenuItem(helpMenu, SWT.PUSH);
		readme.setText("Read Me");
		MenuItem version = new MenuItem(helpMenu, SWT.PUSH);
		version.setText("Version");

		// shell
		Image image = new Image(display, "resources/bg.png");
		rootShell.setMaximized(true);
		rootShell.setText("eManagerV1.0");
		rootShell.setBackgroundImage(image);
		rootShell.open();

		//menu->file
		open.addSelectionListener(new MenuOpenListener());
		quit.addSelectionListener(new MenuQuitListener());

		//menu->mode
		eventManager.addSelectionListener(new eventManagerListener());
		manager.addSelectionListener(new MenuManagerListener());
		applicant.addSelectionListener(new MenuApplicantListener());

		//menu-> help
		//readme.addSelectionListener(new MenuListener());
		version.addSelectionListener(new MenuVersionListener());

		// welcome page
		welcome_shell = new Shell(display, SWT.NO_TRIM);
		welcome_shell.setLocation(400, 250);
		welcome_page = new WelcomePage(welcome_shell, SWT.None);

		welcome_page.setSize(400, 350);
		welcome_shell.pack();
		welcome_shell.open();
		while (!rootShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	//opens Welcome page
	class MenuOpenListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent event) {
			welcome_shell = new Shell(display,SWT.NONE);
			welcome_shell.setLocation(400, 250);
			welcome_page = new WelcomePage(welcome_shell, SWT.NONE);
			welcome_page.setSize(400, 350);
			welcome_page.pack();
			welcome_shell.pack();
			welcome_shell.open();
		}
	}

	//Quits the program
	class MenuQuitListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent event) {
			display.dispose();
		}
	}


	//opens Version page
	class MenuVersionListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent event) {
			Shell version_shell = new Shell(display,SWT.None);
			version_shell.setLocation(400, 250);
			FileVersion version_page = new FileVersion(version_shell, SWT.None);
			version_page.pack();
			version_shell.pack();
			version_shell.open();
		}
	}

	class eventManagerListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if(SessionManager.getCurrentIntMode() != MACRO.MANAGER) {
				Shell mode_shell = new Shell(display, SWT.None);
				SelectEventPage newEventPage = new SelectEventPage(mode_shell, SWT.None);
				newEventPage.pack();
				mode_shell.pack();
				mode_shell.open();
			}
		}
	}
	class MenuManagerListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if(SessionManager.getCurrentIntMode() != MACRO.MANAGER) {
				Shell mode_shell = new Shell(display, SWT.None);
				PromptPassword mode_page = new PromptPassword(mode_shell, SWT.None, MACRO.MANAGER);
				mode_page.pack();
				mode_shell.pack();
				mode_shell.open();
			}
		}
	}
	class MenuApplicantListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			if(SessionManager.getCurrentIntMode() != MACRO.APPLICANT) {
				Shell mode_shell = new Shell(display, SWT.None);
				Venuespace venuspace = new Venuespace(mode_shell, SWT.None, MACRO.APPLICANT_MODE);
				venuspace.pack();
				mode_shell.pack();
				mode_shell.open();
			}
		}
	}

	public static void main(String[] args) {
		eManager eManager = new eManager();
	}

}
