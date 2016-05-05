/**
 * Created by Администратор on 04.05.2016.
 */
public class Scaling {
    private int Width;
    public int GetWidth(){
        return Width;
    }
    private int Height;
    public int GetHeight(){
        return Height;
    }
    private int Border;
    private double XDifference;
    private double YDifference;
    private double XMax;
    private double YMax;
    private double XMin;
    private double YMin;

    public Scaling(double xMax, double xMin, double yMin, double yMax, int border, int width , int height ){
        Border = border;
        Width = width - border * 2;
        Height = height - border * 2;
        XMax = xMax;
        XMin = xMin;
        YMax= yMax;
        YMin = yMin;
        XDifference = XMax - XMin;
        YDifference = YMax - YMin;
    }
    public Scaling(double xMax, double xMin, double yMin, double yMax ,int border){
        new Scaling(xMax,  xMin,  yMin,  yMax, border, 800, 500);
    }
    public double ScalingX (double x){
        return Border + ( ((x-XMax) * Width) / XDifference );
    }
     public double ScalingY (double y){
         return Border + (YMax - ( ((y-YMax) * Height) / YDifference ));
    }
}
