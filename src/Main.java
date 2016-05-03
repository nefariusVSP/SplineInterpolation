public class Main {

        public static void main(String[] args) {
                MyWindow window = new MyWindow();
                window.setVisible(true);
                double[] d = ConvertToArray.OfString(window.LabelX.getText());
                double[] d1 = ConvertToArray.OfString(window.LabelY.getText());

        }
}
