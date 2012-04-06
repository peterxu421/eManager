import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.swtchart.Chart;
import org.swtchart.IAxis;
import org.swtchart.IAxisSet;
import org.swtchart.IBarSeries;
import org.swtchart.ISeries.SeriesType;
import org.swtchart.Range;

public class TaskChart extends Composite {

	DatabaseReader db;
	ArrayList<Organizer> listOfPeople;
	ArrayList<Task> listOfTasks;
	ArrayList<Integer> listOfTotalIndividualTask;
	ArrayList<Integer> listOfTasksDone;
	Event event;
	double[] ySeries;

	public TaskChart(Composite parent, int style, Event event) {
		// set attributes
		super(parent, style);
		db = new DatabaseReader();
		listOfPeople = db.getOrganizers(event);
		listOfTasks = db.getTasks(event);
		if (listOfPeople.size() != 0 && listOfTasks.size() != 0) {
			listOfTotalIndividualTask = new ArrayList<Integer>();
			listOfTasksDone = new ArrayList<Integer>();
			// initialize
			for (int i = 0; i < listOfPeople.size(); i++) {
				listOfTotalIndividualTask.add(0);
				listOfTasksDone.add(0);
			}
			String[] stringOfPeople = new String[listOfPeople.size()];
			for (int i = 0; i < listOfPeople.size(); i++) {
				// populating the stringOfPeople
				stringOfPeople[i] = listOfPeople.get(i).getName();
				for (int j = 0; j < listOfTasks.size(); j++) {
					if (listOfPeople.get(i).getName()
							.equals(listOfTasks.get(j).getAssignedTo())) {
						int temp = listOfTotalIndividualTask.get(i);
						temp++;
						listOfTotalIndividualTask.set(i, temp);
						if (listOfTasks.get(j).isDone()) {
							temp = listOfTasksDone.get(i);
							temp++;
							listOfTasksDone.set(i, temp);
						}
					}
				}
			}

			// create a chart
			Chart chart = new Chart(this, SWT.LEFT);

			// set titles
			chart.getTitle().setText("Task Chart");
			chart.getAxisSet().getXAxis(0).getTitle().setText("");
			chart.getAxisSet().getYAxis(0).getTitle().setText("% of work done");
			chart.setLocation(0, 0);
			chart.setBounds(0, 0, 600, 400);

			// set xAxis
			IAxisSet axisSet = chart.getAxisSet();
			IAxis xAxis = axisSet.getXAxis(0);
			xAxis.setCategorySeries(stringOfPeople);

			// set yAxis
			ySeries = new double[listOfPeople.size()];
			for (int i = 0; i < listOfPeople.size(); i++) {
				// populating the y-series
				if (listOfTotalIndividualTask.get(i) != 0)
					ySeries[i] = (double) listOfTasksDone.get(i)
							/ (double) listOfTotalIndividualTask.get(i);
				else
					ySeries[i] = 0;
			}

			// set Horizontal
			chart.setOrientation(SWT.VERTICAL);

			// create bar series
			IBarSeries barSeries = (IBarSeries) chart.getSeriesSet()
					.createSeries(SeriesType.BAR, "bar series");
			barSeries.setYSeries(ySeries);

			// setting the bar's color
			Color color = new Color(Display.getDefault(), 238, 221, 130);
			barSeries.setBarColor(color);

			// adjust the axis range
			chart.getAxisSet().adjustRange();
			IAxis yAxis = axisSet.getYAxis(0);
			xAxis.enableCategory(true);
			yAxis.setRange(new Range(0, 1));

		} else {
			this.setLayout(new GridLayout());
			Label label = new Label(this, SWT.None);
			label.setText("Sorry, there is neither tasks nor committee members added");
		}
	}
}