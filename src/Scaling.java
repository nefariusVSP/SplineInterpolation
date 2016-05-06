import java.security.PublicKey;

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
    private int WidthImg;
    public int GetWidthImg(){
        return WidthImg;
    }
    private int HeightImg;
    public int GetHeightImg(){
        return HeightImg;
    }
    private int Border;
    private double XDifference;
    private double YDifference;
    private double XMax;
    private double YMax;
    private double XMin;
    private double YMin;
    public int GetXMax(){return (int)XMax;}
    public int GetXMin(){return (int)XMin;}
    public int GetYMax(){return (int)YMax;}
    public int GetYMin(){return (int)YMin;}

    public Scaling(double xMax, double xMin, double yMax, double yMin, int border, int width , int height ){

        Border = border;
        Width = width;
        Height = height;
        WidthImg = (width - (border * 2));
        HeightImg = (height - (border * 2));
        if (xMax < 0) xMax = 0;
        if (xMin > 0) xMin = 0;
        if (yMax < 0) yMax = 0;
        if (yMin > 0) yMin = 0;
        XMax = xMax;
        XMin = xMin;
        YMax= yMax;
        YMin = yMin;
        XDifference = XMax - XMin;
        YDifference = YMax - YMin;
    }

    public int ScalingX (double x){
        return ((int)((Border + (((x-XMin) * (WidthImg))) / XDifference )));
    }
    public int ScalingY (double y){
        return ((int)(Border + (HeightImg - (((y-YMin) * (HeightImg))) / YDifference )));
    }
}
