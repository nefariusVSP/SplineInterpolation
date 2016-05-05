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

    public Scaling(double xMax, double xMin, double yMax, double yMin, int border, int width , int height ){
        Border = border;
        Width = width;
        Height = height;
        XMax = xMax;
        XMin = xMin;
        YMax= yMax;
        YMin = yMin;
        XDifference = XMax - XMin;
        YDifference = YMax - YMin;
    }
    public Scaling(double xMax, double xMin, double yMin, double yMax ,int border){
        this(xMax,  xMin, yMax, yMin, border, 800, 500);
    }
    public int ScalingX (double x){
        return ((int)((Border + (((x-XMin) * ((Width - (Border * 2)))) / XDifference ))));
    }
    public int ScalingY (double y){
        return ((int)(Border + (YMax + (((y-YMin) * ((Height - (Border * 2)))) / YDifference ))));
    }
}
