package imsa;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.BarChart;
import com.codename1.ui.*;

import java.util.ArrayList;
import java.util.List;


public class BarChartComponent {
    Font smallFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.SIZE_SMALL, Font.STYLE_PLAIN);
    Font medFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.SIZE_MEDIUM, Font.STYLE_PLAIN);
    Font largeFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.SIZE_LARGE, Font.STYLE_PLAIN);

    protected XYMultipleSeriesDataset buildBarDataset(String[] titles, List<double[]> values) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        int length = titles.length;
        for (int i = 0; i < length; i++) {
            CategorySeries series = new CategorySeries(titles[i]);
            double[] v = values.get(i);
            int seriesLength = v.length;
            for (int k = 0; k < seriesLength; k++) {
                series.add(v[k]);
            }
            dataset.addSeries(series.toXYSeries());
        }
        return dataset;
    }

    protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setAxisTitleTextSize(smallFont.getHeight()/2);
        renderer.setChartTitleTextFont(smallFont);
        renderer.setLabelsTextSize(smallFont.getHeight()/2);
        renderer.setLegendTextSize(smallFont.getHeight()/2);
        int length = colors.length;
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(colors[i]);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Sets a few of the series renderer settings.
     *
     * @param renderer the renderer to set the properties to
     * @param title the chart title
     * @param xTitle the title for the X axis
     * @param yTitle the title for the Y axis
     * @param xMin the minimum value on the X axis
     * @param xMax the maximum value on the X axis
     * @param yMin the minimum value on the Y axis
     * @param yMax the maximum value on the Y axis
     * @param axesColor the axes color
     * @param labelsColor the labels color
     */
    protected void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle,
                                    String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor,
                                    int labelsColor) {
        renderer.setChartTitle(title);
        renderer.setXTitle(xTitle);
        renderer.setYTitle(yTitle);
        renderer.setXAxisMin(xMin);
        renderer.setXAxisMax(xMax);
        renderer.setYAxisMin(yMin);
        renderer.setYAxisMax(yMax);
        renderer.setAxesColor(axesColor);
        renderer.setLabelsColor(labelsColor);
    }

    public Component createComponent()
    {
        String[] titles = new String[] { "2007", "2008" };
        List<double[]> values = new ArrayList<double[]>();
        values.add(new double[] { 5230, 7300, 9240, 10540, 7900, 9200, 12030, 11200, 9500, 10500,
                11600, 13500 });
        values.add(new double[] { 14230, 12300, 14240, 15244, 15900, 19200, 22030, 21200, 19500, 15500,
                12600, 14000 });
        int[] colors = new int[] { ColorUtil.CYAN, ColorUtil.BLUE };
        XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
        renderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
        setChartSettings(renderer, "Monthly sales in the last 2 years", "Month", "Units sold", 0.5,
                12.5, 0, 24000, ColorUtil.GRAY, ColorUtil.LTGRAY);
        renderer.setXLabels(1);
        renderer.setYLabels(10);
        renderer.addXTextLabel(1, "Jan");
        renderer.addXTextLabel(3, "Mar");
        renderer.addXTextLabel(5, "May");
        renderer.addXTextLabel(7, "Jul");
        renderer.addXTextLabel(10, "Oct");
        renderer.addXTextLabel(12, "Dec");
        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer seriesRenderer = (XYSeriesRenderer) renderer.getSeriesRendererAt(i);
            seriesRenderer.setDisplayChartValues(true);
        }

        BarChart chart = new BarChart(buildBarDataset(titles, values), renderer,
                BarChart.Type.DEFAULT);
        return new ChartComponent(chart);
    }

}
