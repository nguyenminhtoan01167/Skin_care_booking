import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;

public class GrowthChart {
    public static JFreeChart createChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // Thêm dữ liệu vào dataset

        return ChartFactory.createLineChart(
            "Growth Chart", "Time", "Value", dataset,
            PlotOrientation.VERTICAL, true, true, false
        );
    }
}
