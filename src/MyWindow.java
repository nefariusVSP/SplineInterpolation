import javax.swing.*;
import java.awt.*;

/**
 * Created by Администратор on 03.05.2016.
 */
public class MyWindow extends JFrame {

    private JButton Button;
    private JTextPane LabelX;
    private JTextPane LabelY;
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
