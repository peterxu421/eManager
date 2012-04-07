import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

public class eManager {
	private String[] stringPassword = { "New Password", "Confirm New Password" };
	private int[] signaturePassword = { MACRO.PASSWORD, MACRO.PASSWORD };
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
		Image icon = new Image(display, "resources/eManager.png");
		rootShell.setImage(icon);
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
			welcome_shell = new Shell(display, SWT.NO_TRIM | SWT.ON_TOP);
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
			version_shell.setText("eManager - File Version");
			version_shell.setLocation(400, 250);
			Image icon = new Image(display, "resources/eManager.png");
			version_shell.setImage(icon);
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
			mode_shell.setText("eManager - Event Management");
			mode_shell.setLocation(400, 200);
			SessionManager.setCurrentMode(MACRO.ORGANIZER);
			SelectEventPage newEventPage = new SelectEventPage(mode_shell,
					SWT.None);
			newEventPage.setSize(500, 400);
			mode_shell.pack();
			mode_shell.open();
			SessionManager.disposeShells(display, mode_shell);
		}
	}

	// Take care of the special case:
	// When they use the software at first time
	class MenuManagerListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {

			SessionManager.setCurrentMode(MACRO.MANAGER);
			DatabaseReader db = new DatabaseReader();
			// If it is the first time to use the software
			if (db.getPassword() == null) {
				Shell shell = new Shell(display, SWT.NO_TRIM | SWT.ON_TOP);
				shell.setLocation(200, 100);
				AbstractAdd addPasswordVenue = new AbstractAdd(shell, SWT.None,
						stringPassword, signaturePassword, new Table(shell,
								SWT.None)) {

					@Override
					public void onSubmit() {
						String[] stringList = getStringList();
						// update database
						db.insertPassword(stringList[0]);
						// Create the venue shell
						Shell venueManagerShell = new Shell(getDisplay());
						venueManagerShell.setLocation(200, 50);
						Image icon = new Image(getDisplay(),
								"resources/eManager.png");
						venueManagerShell.setText("eManager - Venue Management");
						venueManagerShell.setImage(icon);
						Venuespace venuespace = new Venuespace(
								venueManagerShell, SWT.None);
						SessionManager.disposeShells(getDisplay(),
								venueManagerShell);
						venuespace.pack();
						venueManagerShell.pack();
						venueManagerShell.open();
					}

					@Override
					public boolean additionalRequirement() {
						return false;
					}

					@Override
					public boolean additionalCheck() {
						String[] stringList = getStringList();
						boolean isValid = true;
						// if the two input password does not match
						if (!stringList[0].equals(stringList[1])) {
							isValid = false;
							MessageBox warningPage = new MessageBox(
									getDisplay().getActiveShell(), SWT.OK
											| SWT.ICON_WARNING);
							warningPage.setText("Warning!");
							warningPage
									.setMessage("The confirmed new passowrd for venue manager does not match to new password!");
							warningPage.open();
						}
						return isValid;
					}
				};
				SessionManager.disposeShells(display, shell);
				addPasswordVenue.setSize(450, 250);
				shell.pack();
				shell.open();
			}
			// If the password is not null. Meaning they have used the software
			// at least once.
			else {
				Shell mode_shell = new Shell(display, SWT.NO_TRIM | SWT.ON_TOP);
				mode_shell.setText("eManager - Venue Management");
				mode_shell.setLocation(400, 200);
				PromptPassword mode_page = new PromptPassword(mode_shell,
						SWT.None, MACRO.MANAGER);
				mode_page.pack();
				mode_shell.pack();
				mode_shell.open();
				SessionManager.disposeShells(display, mode_shell);
			}
		}
	}

	class MenuApplicantListener extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			Shell mode_shell = new Shell(display);
			mode_shell.setText("eManager - Venue Applicant");
			mode_shell.setLocation(200, 50);
			Image icon = new Image(display, "resources/eManager.png");
			mode_shell.setImage(icon);
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
