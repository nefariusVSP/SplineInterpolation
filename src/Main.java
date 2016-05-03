public class Main {

        public static void main(String[] args)  {
                MyWindow window = new MyWindow();

                double[] x = ConvertToArray.OfString(window.LabelX.getText());
                double[] y = ConvertToArray.OfString(window.LabelY.getText());

                Spline spline = new Spline(x,y);
        }
}
