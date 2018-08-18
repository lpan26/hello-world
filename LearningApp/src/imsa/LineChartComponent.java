package imsa;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.models.XYSeries;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.CubicLineChart;
import com.codename1.charts.views.PointStyle;
import com.codename1.ui.Component;
import com.codename1.ui.Font;

import java.util.ArrayList;
import java.util.List;


public class LineChartComponent {
    Font smallFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.SIZE_SMALL, Font.STYLE_PLAIN);
    Font medFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.SIZE_MEDIUM, Font.STYLE_PLAIN);
    Font largeFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.SIZE_LARGE, Font.STYLE_PLAIN);


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

    /**
     * Builds an XY multiple series renderer.
     *
     * @param colors the series rendering colors
     * @param styles the series point styles
     * @return the XY multiple series renderers
     */
    protected XYMultipleSeriesRenderer buildRenderer(int[] colors, PointStyle[] styles) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        setRenderer(renderer, colors, styles);
        return renderer;
    }

    /**
     * Builds an XY multiple dataset using the provided values.
     *
     * @param titles the series titles
     * @param xValues the values for the X axis
     * @param yValues the values for the Y axis
     * @return the XY multiple dataset
     */
    protected XYMultipleSeriesDataset buildDataset(String[] titles, List<double[]> xValues,
                                                   List<double[]> yValues) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        addXYSeries(dataset, titles, xValues, yValues, 0);
        return dataset;
    }

    public void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles, List<double[]> xValues,
                            List<double[]> yValues, int scale) {
        int length = titles.length;
        for (int i = 0; i < length; i++) {
            XYSeries series = new XYSeries(titles[i], scale);
            double[] xV = xValues.get(i);
            double[] yV = yValues.get(i);
            int seriesLength = xV.length;
            for (int k = 0; k < seriesLength; k++) {
                series.add(xV[k], yV[k]);
            }
            dataset.addSeries(series);
        }
    }

    protected void setRenderer(XYMultipleSeriesRenderer renderer, int[] colors, PointStyle[] styles) {
        renderer.setAxisTitleTextSize(smallFont.getHeight()/2);
        renderer.setChartTitleTextSize(smallFont.getHeight());
        renderer.setLabelsTextSize(smallFont.getHeight()/2);
        renderer.setLegendTextSize(smallFont.getHeight()/2);
        renderer.setPointSize(5f);
        renderer.setMargins(new int[] { medFont.getHeight(), medFont.getHeight(), 15, medFont.getHeight() });
        int length = colors.length;
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(colors[i]);
            r.setPointStyle(styles[i]);
            renderer.addSeriesRenderer(r);
        }
    }

    public Component createComponent()
    {
        String[] titles = new String[] { "Air temperature" };
        List<double[]> x = new ArrayList<double[]>();
        x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
        List<double[]> values = new ArrayList<double[]>();
        values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4, 26.1, 23.6, 20.3, 17.2,
                13.9 });
        int[] colors = new int[] { ColorUtil.BLUE, ColorUtil.YELLOW };
        PointStyle[] styles = new PointStyle[] { PointStyle.POINT, PointStyle.POINT };
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer(2);
        setRenderer(renderer, colors, styles);
        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer r = (XYSeriesRenderer) renderer.getSeriesRendererAt(i);
            r.setLineWidth(3f);
        }
        setChartSettings(renderer, "Average temperature", "Month", "Temperature", 0.5, 12.5, 0, 32,
                ColorUtil.LTGRAY, ColorUtil.LTGRAY);
        renderer.setXLabels(12);
        renderer.setYLabels(10);
        renderer.setShowGrid(true);
        renderer.setXLabelsAlign(Component.RIGHT);
        renderer.setYLabelsAlign(Component.RIGHT);
        renderer.setZoomButtonsVisible(true);
        renderer.setPanLimits(new double[] { -10, 20, -10, 40 });
        renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });
        renderer.setZoomRate(1.05f);
        renderer.setLabelsColor(ColorUtil.WHITE);
        renderer.setXLabelsColor(ColorUtil.GREEN);
        renderer.setYLabelsColor(0, colors[0]);
        renderer.setYLabelsColor(1, colors[1]);

        renderer.setYTitle("Hours", 1);
        renderer.setYAxisAlign(Component.RIGHT, 1);
        renderer.setYLabelsAlign(Component.LEFT, 1);
        renderer.setGridColor(colors[0], 0);
        renderer.setGridColor(colors[1], 1);

        XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
        x.clear();
        x.add(new double[] { -1, 0, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
        values.clear();
        values.add(new double[] { 4.3, 4.9, 5.9, 8.8, 10.8, 11.9, 13.6, 12.8, 11.4, 9.5, 7.5, 5.5 });
        addXYSeries(dataset, new String[] { "Sunshine hours" }, x, values, 1);

        CubicLineChart chart = new CubicLineChart(dataset, renderer, 0.3f);

        return new ChartComponent(chart);
    }

}
