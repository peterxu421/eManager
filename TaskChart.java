import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.swtchart.Chart;
import org.swtchart.IAxis;
import org.swtchart.IAxisSet;
import org.swtchart.IBarSeries;
import org.swtchart.ISeries;
import org.swtchart.ISeries.SeriesType;
import org.swtchart.ISeriesLabel;
import org.swtchart.ISeriesSet;

public class TaskChart extends Composite {

	DatabaseReader db;
	ArrayList<Organizer> listOfPeople;
	ArrayList<Task> listOfTasks;
	ArrayList<Integer> listOfTotalIndividualTask;
	ArrayList<Integer> listOfTasksDone;
	Event event;
	private static final double[] ySeries = { 20, 50, 80, 100 };

	public TaskChart(Composite parent, int style, Event event) {
		// set attributes
		super(parent, style);

		db = new DatabaseReader();
		listOfPeople = db.getOrganizers(event);
		listOfTasks = db.getTasks(event);
		listOfTotalIndividualTask = new ArrayList<Integer>();
		listOfTasksDone = new ArrayList<Integer>();

		int noOfTasks = listOfTasks.size();
		String[] stringOfPeople = new String[listOfPeople.size()];

		for (int i = 0; i < listOfPeople.size(); i++) {
			stringOfPeople[i] = listOfPeople.get(i).getName(); // populating the
																// stringOfPeople
			for (int j = 0; j < listOfTasks.size(); j++) {
				if (listOfPeople.get(i).getName()
						.equals(listOfTasks.get(j).getAssignedTo())) {
					int temp = listOfTotalIndividualTask.get(i);
					temp++;
					listOfTotalIndividualTask.set(i, temp);
				}
				if (listOfTasks.get(j).isDone()) {
					int temp = listOfTasksDone.get(i);
					temp++;
					listOfTasksDone.set(i, temp);
				}
			}
		}

		// create a chart
		Chart chart = new Chart(parent, SWT.NONE);

		// set titles
		chart.getTitle().setText("Task Chart");
		chart.getAxisSet().getXAxis(0).getTitle().setText("");
		chart.getAxisSet().getYAxis(0).getTitle().setText("% of work done");
		// set xAxis
		IAxisSet axisSet = chart.getAxisSet();
		IAxis xAxis = axisSet.getXAxis(0);
		xAxis.setCategorySeries(stringOfPeople);
		xAxis.enableCategory(true);

		// set Horizontal
		chart.setOrientation(SWT.VERTICAL);
		// create bar series
		IBarSeries barSeries = (IBarSeries) chart.getSeriesSet().createSeries(
				SeriesType.BAR, "bar series");
		barSeries.setYSeries(ySeries);

		// setting the bar's color
		Color color = new Color(Display.getDefault(), 238, 221, 130);
		barSeries.setBarColor(color);

		// adjust the axis range
		chart.getAxisSet().adjustRange();

	}
}