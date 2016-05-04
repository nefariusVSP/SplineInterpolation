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
    private double XDifference;
    private double YDifference;
    private double XMax;
    private double YMax;
    private double XMin;
    private double YMin;

    public Scaling(double xMax, double xMin, double yMin, double yMax, int width , int height ){
        Width = width;
        Height = height;
        XMax = xMax;
        XMin = xMin;
        YMax= yMax;
        YMin = yMin;
        XDifference = XMax - XMin;
        YDifference = YMax - YMin;
    }
    public Scaling(double xMax, double xMin, double yMin, double yMax){
        new Scaling(xMax,  xMin,  yMin,  yMax, 800, 500);
    }
    public double ScalingX (double x){
        return ( ((x-XMax) * Width) / XDifference );
    }
     public double ScalingY (double y){
         return (YMax - ( ((y-YMax) * Height) / YDifference ));
    }
}
