/**
 * Created by Администратор on 03.05.2016.
 */
public class Spline {

    public Spline(double[] x, double[] y)  {

        for(int i = 0; i < x.length-1;){
            if (x[i] > x[i+1]){
                double svap = x[i];
                x[i] = x[i+1];
                x[i+1] = svap;
                svap = y[i];
                y[i] = y[i+1];
                y[i+1] = svap;
                i = 0;
            }
            else {
                i++;
            }
        }

        Count = x.length;
        Splines = new SplineTuple[Count];
        for (int i = 0; i < Count; ++i){
            Splines[i] = new SplineTuple();
        }
        for (int i = 0; i < Count; ++i){
            Splines[i].x = x[i];
            Splines[i].a = y[i];
        }
        Splines[0].c = Splines[Count-1].c = 0.0;

        double[] alpha = new double[Count-1];
        double[] beta = new double[Count-1];
        alpha[0]= beta[0] = 0.0;
        for (int i = 1; i < Count - 1; ++i){
            double hi = x[i] - x[i-1];
            double hi1 = x[i+1] - x[i];
            double A = hi;
            double C = 2 * (hi + hi1);
            double B = hi1;
            double F = 6 * ((y[i + 1] - y[i]) / hi1 - (y[i] - y[i - 1]) / hi);
            double z = (A * alpha[i-1] + C);
            alpha[i] = -B/z;
            beta[i] = (F-A*beta[i-1])/z;
        }

        for(int i = Count - 2; i > 0; --i){
            Splines[i].c = alpha[i]* Splines[i+1].c + beta[i];
        }

        for (int i = Count - 1; i > 0; --i){
            double hi = x[i] - x[i-1];
            Splines[i].d = (Splines[i].c - Splines[i-1].c) / hi;
            Splines[i].b = hi * (2 * Splines[i].c + Splines[i-1].c)/ 6 + (y[i] - y[i-1]) / hi;
        }
    }

    public double Interpol(double x){
        SplineTuple s;
        if (x <= Splines[0].x){
            s = Splines[0];
        }
        else if (x >= Splines[Count-1].x){
            s = Splines[Count-1];
        }
        else {
            int i = 0;
            int j = Count - 1;
            while (i + 1 < j){
                int k = i + (j - i) / 2;
                if (x <= Splines[k].x){
                    j = k;
                }
                else {
                    i = k;
                }
            }
            s = Splines[j];
        }
        double dx = x - s.x;
        return s.a + (s.b + (s.c / 2.0 + s.d * dx / 6.0) * dx) * dx;
    }
    int  Count;
    SplineTuple[] Splines;
}

class SplineTuple{
    public double a, b, c, d, x;
}