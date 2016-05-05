import com.sun.javafx.iio.ImageStorage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Администратор on 03.05.2016.
 */
public class MyWindow extends JFrame {

    private JButton Button;
    public JTextPane LabelX;
    public JTextPane LabelY;
    private JLabel TextX;
    private JLabel TextY;

    public MyWindow(){
        super("Spline Interpolation");

        setBounds(100,100,600,200);

        Button = new JButton("Создание изображиения");
        LabelX = new JTextPane();
        LabelX.setText("0,50,100,150,200,250,300,350,400,450");
        LabelY = new JTextPane();
        LabelY.setText("50,100,90,330,400,100,50,59,354,200");
        TextX = new JLabel("X:");
        TextY = new JLabel("Y:");

        setVisible(true);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JPanel button1Panel = new JPanel(new FlowLayout());
        JPanel button2Panel = new JPanel(new FlowLayout());
        button1Panel.add(TextX);
        button1Panel.add(LabelX);
        buttonPanel.add(TextY);
        buttonPanel.add(LabelY);
        button2Panel.add(Button);
        add(button1Panel, BorderLayout.LINE_START);
        add(buttonPanel, BorderLayout.LINE_END);
        add(button2Panel, BorderLayout.NORTH);

        Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double[] x = ConvertToArray.OfString(LabelX.getText());
                double[] y = ConvertToArray.OfString(LabelY.getText());

                int xMax = (int)MaxDouble(x);
                int xMin = (int)MinDouble(x);
                int yMax = (int)MaxDouble(y);
                int yMin = (int)MinDouble(y);
                Spline spline = new Spline(x,y);
                
                Scaling scaling = new Scaling(xMax, xMin, yMax, yMin, 20);
                BufferedImage img = new BufferedImage(scaling.GetWidth(),scaling.GetHeight(), BufferedImage.TYPE_INT_RGB);

                //System.err.println(scaling.ScalingX(xMin) + " " + scaling.ScalingX(xMax) );
                //System.err.println(scaling.ScalingY(yMin) + " " + scaling.ScalingY(yMax) );

                Graphics2D graphics = img.createGraphics();
                graphics.setColor(Color.WHITE);
                graphics.drawLine(scaling.ScalingX(xMin),scaling.ScalingY(0),780,scaling.ScalingY(0));
                graphics.dispose();
                try
                {
                    ImageIO.write(img, "png", new File("image.bmp"));
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
    public double MaxDouble(double[] values){
        double max = 0;
        for (double value: values) {
            if (max < value){
                max = value;
            }
        }
        return max;
    }
    public double MinDouble(double[] values){
        double min = 0;
        for (double value: values) {
            if (min > value){
                min = value;
            }
        }
        return min;
    }
}
