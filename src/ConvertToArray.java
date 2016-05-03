/**
 * Created by Администратор on 03.05.2016.
 */
public class ConvertToArray {
    static public double[] OfString(String string){
        //ArrayList<Double> list = new ArrayList<Double>();
        int n = 1;
        for (int i = 0 ; i < string.length(); ++i){
            if (string.charAt(i) == ','){
                ++n;
            }
        }
        double[] list =  new double[n];
        double znach = 0;
        boolean b = false;
        for (int i = 0, j = 1, l = 0, ii = 0; i < string.length(); ++i){
            if (string.charAt(i) == ',' || string.charAt(i) == ' ' ){
                j = 1;
                l = 0;
                list[ii]=(znach);
                ii++;
                znach = 0;
                b = false;
            }
            else if (string.charAt(i) == '.'){
                l = 10;
            }
            else {
                if (l == 0) {
                    if (b){
                        znach = znach* 10 + Double.parseDouble(string.charAt(i) + "") * j;
                        j*=10;
                    }
                    else {
                        znach = Double.parseDouble(string.charAt(i) + "");
                        b = true;
                    }
                }
                else {
                    znach += Double.parseDouble(string.charAt(i) + "") / l;
                    l *= 10;
                }
            }
        }
        list[n-1]=(znach);
        return  list;
    }
}
