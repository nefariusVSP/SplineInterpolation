/**
 * https://ru.wikipedia.org/wiki/%D0%9A%D1%83%D0%B1%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B9_%D1%81%D0%BF%D0%BB%D0%B0%D0%B9%D0%BD
 */
public class Spline {

    public Spline(double[] x, double[] y)  {
        //Сортировка (пузырьковая)
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
        //Создание массива сплайнов
        Splines = new SplineTuple[Count];
        // Инициализация массива сплайнов
        for (int i = 0; i < Count; ++i){
            Splines[i] = new SplineTuple();
        }
        for (int i = 0; i < Count; ++i){
            Splines[i].x = x[i];
            Splines[i].y = y[i];
        }
        //Хоть если и не присваивать последнему элементу массива 0, то всё будет работать, но на Википедии сказано
        //Естественным кубическим сплайном называется кубический сплайн, удовлетворяющий также граничным условиям вида:S``(y) = S``(b) = 0
        /* так же как и эти три правила.
            На каждом отрезке [x{i-1},x{i)] является многочленом степени не выше третьей;
            имеет непрерывные первую и вторую производные на всём отрезке [a,b];
            в точках x{i} выполняется равенство S(x{i}) = f(x{i}), т. е. сплайн S(x) интерполирует функцию f в точках x{i}.
        */
        Splines[0].c = Splines[Count-1].c = 0.0;
        // Решение системы линейных алгебраических уравнений относительно коэффициентов сплайнов c[i] методом прогонки для трехдиагональных матриц
        // Вычисление прогоночных коэффициентов - прямой ход метода прогонки
        // alpha u beta это Трёхдиагональная матрица, для решения
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
        // Нахождение решения - обратный ход метода прогонки
        for(int i = Count - 2; i > 0; --i){
            Splines[i].c = alpha[i]* Splines[i+1].c + beta[i];
        }
        // По известным коэффициентам c[i] находим значения b[i] и d[i]
        for (int i = Count - 1; i > 0; --i){
            double hi = x[i] - x[i-1];
            Splines[i].d = (Splines[i].c - Splines[i-1].c) / hi;
            Splines[i].b = hi * (2 * Splines[i].c + Splines[i-1].c)/ 6 + (y[i] - y[i-1]) / hi;
        }
    }

    // Вычисление значения интерполированной функции в произвольной точке
    public double Interpol(double x){
        SplineTuple s;
        if (x <= Splines[0].x){ // Если x меньше точки сетки x[0] - пользуемся первым эл-тов массива
            s = Splines[0];
        }
        else if (x >= Splines[Count-1].x){// Если x больше точки сетки x[n - 1] - пользуемся последним эл-том массива
            s = Splines[Count-1];
        }
        else {// Иначе x лежит между граничными точками сетки - производим бинарный поиск нужного эл-та массива
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
        // Вычисляем значение сплайна в заданной точке по схеме Горнера
        return s.y + (s.b + (s.c / 2.0 + s.d * dx / 6.0) * dx) * dx;
    }

    int  Count;

    SplineTuple[] Splines;
}

// Структура, описывающая сплайн на каждом сегменте сетки
class SplineTuple{

    //y = x(i)
    //b = это коэффицент первой степени
    //c = это коэффицент второй спетени.
    public double y, b, c, d, x;
}