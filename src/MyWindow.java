import ScalaClases.StringTo;
import com.sun.javafx.iio.ImageStorage;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import sun.plugin.javascript.navig.Array;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
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
        LabelX.setText("100,200,300,400,500,600,700,800,900");
        LabelY = new JTextPane();
        LabelY.setText("100,90,330,400,100,50,59,354,200");
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

                int widthImg = 800;
                int heightImg = 500;
                int border = 20;

                double[] x = StringTo.ArrayDouble(LabelX.getText());
                double[] y = StringTo.ArrayDouble(LabelY.getText());

                Spline spline = new Spline(x,y);
                double xMax = MaxDouble(x);
                double xMin = MinDouble(x);
                double yMax = MaxDouble(spline, xMax, xMin, widthImg - border *2);
                double yMin = MinDouble(spline, xMax, xMin, widthImg - border *2);
                
                Scaling scaling = new Scaling(xMax, xMin, yMax, yMin, border, widthImg, heightImg);
                BufferedImage img = new BufferedImage(scaling.GetWidth(),scaling.GetHeight(), BufferedImage.TYPE_INT_RGB);

                System.err.println((xMin) + " " + (xMax) );
                System.err.println((yMin) + " " + (yMax) );

                Graphics2D graphics = img.createGraphics();
                graphics.setColor(Color.WHITE);
                //Рисование осей
                graphics.drawLine(scaling.ScalingX(scaling.GetXMin()),scaling.ScalingY(0),scaling.ScalingX(scaling.GetXMax()),scaling.ScalingY(0));
                graphics.drawLine(scaling.ScalingX(0),scaling.ScalingY(scaling.GetYMin()),scaling.ScalingX(0),scaling.ScalingY(scaling.GetYMax()));
                //Рисование сплайна
                for (double i = 0; i + xMin < xMax ; ) {
                    graphics.drawLine(
                            scaling.ScalingX(xMin + i),
                            scaling.ScalingY(spline.Interpol(xMin + i)),
                            scaling.ScalingX(xMin + i + ((xMax - xMin) / scaling.GetWidthImg())),
                            scaling.ScalingY(spline.Interpol(xMin+ i+((xMax - xMin) / scaling.GetWidthImg())))

                    );
                    System.err.printf("i = %f, x1 = %d, y1 = %d, x2 = %d, y2 = %d \n", i, scaling.ScalingX(xMin + i),scaling.ScalingY(spline.Interpol(xMin + i)),scaling.ScalingX(xMin + i + ((xMax - xMin) / scaling.GetWidthImg())),scaling.ScalingY(spline.Interpol(xMin +i+((xMax - xMin) / scaling.GetWidthImg()))));
                    i+= (xMax - xMin) / scaling.GetWidthImg();

                }
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
        double max = values[0];
        for (double value: values) {
            if (max < (value)){
                max = (value);
            }
        }
        return max;
    }
    public double MinDouble(double[] values){
        double min = values[0];
        for (double value: values) {
            if (min > (value)){
                min = (value);
            }
        }
        return min;
    }
    
    public double MaxDouble(Spline spline, double xMax, double xMin ,double scaling){
        double max = Double.MIN_VALUE;
        for (double i = xMin; i <= xMax; i += (xMax - xMin) / scaling) {
            if (max < spline.Interpol(i)){
                max = spline.Interpol(i);
            }
        }
        return max;
    }
    public double MinDouble( Spline spline, double xMax, double xMin, double scaling){
        double min = Double.MAX_VALUE;
        for (double i = xMin; i <= xMax; i += (xMax - xMin) / scaling) {
            if (min > spline.Interpol(i)){
                min = spline.Interpol(i);
            }
        }
        return min;
    }
}
