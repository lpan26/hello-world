package imsa;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;

import java.awt.*;


public class ChartForm extends Form {
    Component comp = null;
    boolean isBar = true;
    public ChartForm() {
        super("IMSA Learning App");
        this.setLayout(new BorderLayout());
        BarChartComponent bar_component = new BarChartComponent();
        comp = bar_component.createComponent();
        addComponent(BorderLayout.CENTER, comp);
        Button button = new Button("Change to Line");
        button.addActionListener(evt ->
        {
            if(isBar == true)
            {
                LineChartComponent line = new LineChartComponent();
                comp = line.createComponent();
                button.setText("Change to Bar");
            }
            else
            {
                BarChartComponent bar = new BarChartComponent();
                comp = bar.createComponent();
                button.setText("Change to Line");
            }
            isBar = !isBar;
            addComponent(BorderLayout.CENTER, comp);
            show();
        });
        addComponent(BorderLayout.NORTH,button);
        show();
    }

}
