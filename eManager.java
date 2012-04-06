import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class eManager {
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

		// menu-> menuMode
		Menu modeItem = new Menu(rootShell, SWT.DROP_DOWN);
		mode.setMenu(modeItem);
		MenuItem eventManager = new MenuItem(modeItem, SWT.PUSH);
		eventManager.setText("Select Events");
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
		rootShell.setText("eManagerV0.2");
		rootShell.setBackgroundImage(image);
		rootShell.open();

		// menu->file
		open.addSelectionListener(new MenuOpenListener());
		quit.addSelectionListener(new MenuQuitListener());

		// menu->mode
		eventManager.addSelectionListener(new EventManagerListener());
		manager.addSelectionListener(new MenuManagerListener());
		applicant.addSelectionListener(new MenuApplicantListener());

		// menu-> help
		// readme.addSelectionListener(new MenuListener());
		version.addSelectionListener(new MenuVersionListener());

		// welcome page
		welcome_shell = new Shell(display, SWT.NO_TRIM | SWT.ON_TOP);
		welcome_shell.setLocation(400, 200);
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

	// opens Welcome page
	class MenuOpenListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent event) {
			welcome_shell = new Shell(display, SWT.APPLICATION_MODAL
					| SWT.DIALOG_TRIM);
			welcome_shell.setText("Welcome to eManagerV0.2");
			welcome_shell.setLocation(400, 200);
			welcome_page = new WelcomePage(welcome_shell, SWT.NONE);
			welcome_page.setSize(400, 350);
			welcome_shell.pack();
			welcome_shell.open();
			SessionManager.disposeShells(display, welcome_shell);
		}
	}

	// Quits the program
	class MenuQuitListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent event) {
			display.dispose();
		}
	}

	// opens Version page
	class MenuVersionListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent event) {
			Shell version_shell = new Shell(display, SWT.APPLICATION_MODAL
					| SWT.DIALOG_TRIM);
			version_shell.setText("File Version");
			version_shell.setLocation(400, 250);
			FileVersion version_page = new FileVersion(version_shell, SWT.None);
			version_page.pack();
			version_shell.pack();
			version_shell.open();
			SessionManager.disposeShells(display, version_shell);
		}
	}

	class EventManagerListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Shell mode_shell = new Shell(display, SWT.NO_TRIM | SWT.ON_TOP);
			mode_shell.setText("Event Manager");
			mode_shell.setLocation(400, 200);
			SessionManager.setCurrentMode(MACRO.ORGANIZER);
			SelectEventPage newEventPage = new SelectEventPage(mode_shell,
					SWT.None);
			newEventPage.setSize(500, 400);
			System.out.println("Reach here1!");
			mode_shell.pack();
			mode_shell.open();
			System.out.println("Reach here2!");
			SessionManager.disposeShells(display, mode_shell);
		}
	}

	class MenuManagerListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Shell mode_shell = new Shell(display, SWT.NO_TRIM | SWT.ON_TOP);
			mode_shell.setText("Venue Manager");
			mode_shell.setLocation(400, 200);
			SessionManager.setCurrentMode(MACRO.MANAGER);
			PromptPassword mode_page = new PromptPassword(mode_shell, SWT.None,
					MACRO.MANAGER);
			mode_page.pack();
			mode_shell.pack();
			mode_shell.open();
			SessionManager.disposeShells(display, mode_shell);
		}
	}

	class MenuApplicantListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Shell mode_shell = new Shell(display);
			mode_shell.setText("Venue Applicant");
			mode_shell.setLocation(200, 50);
			SessionManager.setCurrentMode(MACRO.APPLICANT);
			Venuespace venuspace = new Venuespace(mode_shell, SWT.None);
			venuspace.pack();
			mode_shell.pack();
			mode_shell.open();
			SessionManager.disposeShells(display, mode_shell);
		}
	}

	public static void main(String[] args) {
		new eManager();
	}

}
