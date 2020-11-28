package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);       // считывание с клавиатуры

    static int size;            // размерность
    static double [] b;         // вектор
    static double [][] a;       // матрица

    // главная функция
    public static void main(String[] args) throws FileNotFoundException{
        init();     // первый ввод
        menu();     // вызов меню
    }



    // инициализация вектора и матрицы
    public static void init() throws FileNotFoundException {

        System.out.println();
        System.out.println("   Инициализация данных");
        System.out.println("1. Заполнить с клавиатуры");
        System.out.println("2. Считать из файла");
        System.out.print("   Выберите: ");

        switch (scan.nextInt()) {
            case 1: {
                System.out.println();
                manual_init();
                break;
            }
            case 2: {
                System.out.println();
                auto_init();
                break;
            }
        }
    }



    // ручной ввод
    public static void manual_init() {
        System.out.println("1. Заполнить с клавиатуры");
        System.out.println();

        System.out.print("Размерность: ");
        size = scan.nextInt();

        System.out.println("Матрица А:");
        a = new double[size + 1][size + 1];
        for (int i = 1; i <= size; i++){
            for (int j = 1; j <= size; j++){
                a[i][j] = scan.nextDouble();
            }
        }

        System.out.print("Вектор b: ");
        b = new double[size + 1];
        for (int i = 1; i <= size; i++){
            b[i] = scan.nextDouble();
        }
    }

    // авто считывание
    public static void auto_init() throws FileNotFoundException {
        System.out.println("2. Считать из файла");
        System.out.println();

        Scanner init = new Scanner(new File("H:\\Program Files\\JetBrains\\ProjectIDEA\\MetodyVychislenij\\Laboratornaya 2.0\\src\\com\\company\\input.txt"));
        init.useLocale(Locale.US);

        size = init.nextInt();
        System.out.println("Размерность: " + size);

        System.out.println("Матрица А:");
        a = new double[size + 1][size + 1];
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                a[i][j] = init.nextDouble();
            }
        }
        print_matrix(a);

        System.out.println("Вектор b:");
        b = new double[size + 1];
        for (int i = 1; i <= size; i++){
            b[i] = init.nextDouble();
        }
        print_vector(b);
    }



    // печать матрицы
    public static void print_matrix(double [][] a) {
        for (int i = 1; i <= size; i++){
            System.out.print("| ");
            for (int j = 1; j <= size; j++){
                if (a[i][j] >= 0){
                    System.out.print(" ");
                    System.out.printf("%.2f", a[i][j]);
                    System.out.print(" ");
                } else{
                    System.out.printf("%.2f", a[i][j]);
                    System.out.print(" ");
                }
            }
            System.out.println(" |");
        }
    }

    // печать вектора
    public static void print_vector(double [] b) {
        System.out.print("| ");
        for (int i = 1; i <= size; i++){
            System.out.printf("%.2f", b[i]);
            System.out.print(" ");
        }
        System.out.print("|");
        System.out.println();
    }



    // меню
    public static void menu() throws FileNotFoundException {
        for (;;){
            System.out.println();

            System.out.println("   Меню:");
            System.out.println("1. Нормы матрицы A");
            System.out.println("2. Нормы вектора b");
            System.out.println("3. Разложение матрицы A (S*DS)");
            System.out.println("4. Определитель матрицы |A| (S*DS)");
            System.out.println("5. Решение СЛАУ вида Ax = b (Метод Якоби)");
            System.out.println("9. Ввести другую матрицу и вектор");
            System.out.println("0. Выход");
            System.out.print("   Выберите: ");

            switch (scan.nextInt()) {
                case 1: {
                    System.out.println();
                    norm_matrix(a);
                    break;
                }
                case 2: {
                    System.out.println();
                    norm_vector(b);
                    break;
                }
                case 3: {
                    System.out.println();
                    sds(a);
                    break;
                }
                case 4: {
                    System.out.println();
                    det(a);
                    break;
                }
                case 5: {
                    System.out.println();
                    print_jacob(a, b);
                    break;
                }
                case 9: {
                    System.out.println();
                    init();
                    break;
                }
                case 0: {
                    System.out.println();
                    System.out.println("Завершение работы");
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println();
                    System.out.println("Ошибка!");
                    break;
                }
            }
        }
    }



    // печать норм матриц
    public static void norm_matrix(double [][] a) {
        System.out.println("1. Нормы матрицы A");
        System.out.println();

        System.out.print("Первая норма: ");
        System.out.printf("%.2f", norm_matrix_first(a));
        System.out.println();

        System.out.print("Бесконечная норма: ");
        System.out.printf("%.2f", norm_matrix_beskon(a));
        System.out.println();

        System.out.print("Евклидова норма: ");
        System.out.printf("%.2f", norm_matrix_evkl(a));
        System.out.println();
    }

    // первая норма матрицы
    public static double norm_matrix_first(double [][] a) {
        double norm = 0;

        for (int j = 1; j <= size; j++){
            double temp = 0;
            for (int i = 1; i <= size; i++){
                temp += Math.abs(a[i][j]);
            }
            if (temp > norm){
                norm = temp;
            }
        }

        return norm;
    }

    // вторая норма матрицы
    public static double norm_matrix_second(double [][] a) {
        double norm = 0;

        for (int j = 1; j <= size; j++){
            double temp = 0;
            for (int i = 1; i <= size; i++){
                temp += Math.pow(a[i][j], 2);
            }
            if (temp > norm){
                norm = temp;
            }
        }

        return Math.sqrt(norm);
    }

    // бесконечная норма матрицы
    public static double norm_matrix_beskon(double [][] a) {
        double norm = 0;

        for (int i = 1; i <= size; i++){
            double temp = 0;
            for (int j = 1; j <= size; j++){
                temp += Math.abs(a[i][j]);
            }
            if (temp > norm){
                norm = temp;
            }
        }

        return norm;
    }

    // евклидова норма матрицы
    public static double norm_matrix_evkl(double [][] a) {
        double norm = 0;

        for (int i = 1; i <= size; i++){
            for (int j = 1; j <= size; j++){
                norm += Math.pow(a[i][j], 2);
            }
        }
        norm = Math.sqrt(norm);

        return norm;
    }



    // печать норм вектора
    public static void norm_vector(double [] b) {
        System.out.println("2. Нормы вектора b");
        System.out.println();

        System.out.print("Первая норма: ");
        System.out.printf("%.2f", norm_vector_first(b));
        System.out.println();

        System.out.print("Вторая норма: ");
        System.out.printf("%.2f", norm_vector_second(b));
        System.out.println();

        System.out.print("Бесконечная норма: ");
        System.out.printf("%.2f", norm_vector_beskon(b));
        System.out.println();
    }

    // первая норма вектора
    public static double norm_vector_first(double [] b) {
        double norm = 0;

        for (int i = 1; i <= size; i++){
            norm += Math.abs(b[i]);
        }

        return norm;
    }

    // вторая норма вектора
    public static double norm_vector_second(double [] b) {
        double norm = 0;

        for (int i = 1; i <= size; i++){
            norm += Math.pow(b[i], 2);
        }
        norm = Math.sqrt(norm);

        return norm;
    }

    // бесконечная норма вектора
    public static double norm_vector_beskon(double [] b) {
        double norm = b[0];

        for (int i = 1; i <= size; i++){
            if (Math.abs(b[i]) >= norm){
                norm = Math.abs(b[i]);
            }
        }

        return norm;
    }



    // печать S*DS разложения
    public static void sds(double [][] a) {
        System.out.println("3. Разложение матрицы A (S*DS)");
        System.out.println();

        if (check_mirror_matrix(a) == 1){
            boolean noun = true;
            double [][] d = new double[size + 1][size + 1];
            double [][] s = new double[size + 1][size + 1];

            if (sds(a, d, s, noun)) {
                System.out.println("Из матрицы А:");
                print_matrix(a);

                System.out.println();

                System.out.println("Получим такие матрицы S, D, S*:");

                System.out.println("S:");
                print_matrix(s);

                System.out.println("D:");
                print_matrix(d);

                System.out.println("S*:");
                print_matrix(trans_matrix(s));

                System.out.println();

                System.out.println("Перемножим их:");
                print_matrix(multiply_matrix(multiply_matrix(trans_matrix(s), d), s));
            }
            else
                System.out.println("Матрица вырожденная, введите другую");
        }
        else
            System.out.println("Матрица не симметричная, введите другую");
    }

    // реализация S*DS разложения
    public static boolean sds(double [][] a, double [][] d, double [][] s, boolean noun) {
        for (int i = 1; i <= size; i++)
            for (int j = 1; j <= size; j++)
                d[i][j] = 0;

        d[1][1] = Math.signum(a[1][1]);
        s[1][1] = Math.sqrt(Math.abs(a[1][1]));

        for (int j = 2; j <= size; j++) {
            if ((s[1][1] * d[1][1]) != 0)
                s[1][j] = a[1][j] / (s[1][1] * d[1][1]);
            else {
                noun = false;
                return noun;
            }
        }

        for (int i = 2; i <= size; i++) {
            double sum = 0;
            for (int k = 1; k <= i - 1; k++)
                sum += s[k][i] * s[k][i] * d[k][k];

            d[i][i] = Math.signum(a[i][i] - sum);
            s[i][i] = Math.sqrt(Math.abs(a[i][i] - sum));

            if ((s[i][i] * d[i][i]) != 0) {
                double l = 1 / (s[i][i] * d[i][i]);

                for (int j = i + 1; j <= size; j++) {
                    double SDSsum = 0;
                    for (int k = 1; k <= i - 1; k++) {
                        SDSsum += s[k][i] * d[k][k] * s[k][j];
                    }
                    s[i][j] = l * (a[i][j] - SDSsum);
                }
            }
            else {
                noun = false;
                return noun;
            }
        }

        return noun;
    }



    // печать определителя методом S*SD
    public static void det(double [][] a) {
        System.out.println("4. Определитель матрицы |A| на основе разложения");
        System.out.println();

        if (check_mirror_matrix(a) == 1){
            System.out.print("det A: ");
            System.out.printf("%.2f", det_sds(a));
            System.out.println();
        }
        else
            System.out.println("Матрица не симметричная, введите другую");
    }

    // реализация определителя методом S*SD
    public static double det_sds(double [][] a) {
        boolean noun = true;
        double [][] d = new double[size + 1][size + 1];
        double [][] s = new double[size + 1][size + 1];

        sds(a, d, s, noun);

        double det = 1;
        for (int i = 1; i <= size; i++)
            det *= s[i][i]*s[i][i]*d[i][i];

        return det;
    }



    // печать метода Якоби
    public static void print_jacob(double [][] a, double [] b) {
        System.out.println("5. Решение СЛАУ вида Ax = b (Метод Якоби)");
        System.out.println();

        boolean noun = true;
        double [] result = new double[size + 1];

        if (jacob(a, b, result, noun)) {
            System.out.println("Получим вектор X: ");
            print_vector(result);
        }
        else {
            System.out.println("Условие сходимости не выполняется");
        }
    }

    // реализация метода Якоби
    public static boolean jacob(double [][] a, double [] b, double [] result, boolean noun) {
        double [][] a1 = new double[size + 1][size + 1];
        double [][] a2 = new double[size + 1][size + 1];
        double [][] d = new double[size + 1][size + 1];
        for (int i = 1; i <= size; i++)
            for (int j = 1; j <= size; j++) {
                if (i > j)
                    a1[i][j] = a[i][j];
                else
                    a1[i][j] = 0;
                if (i < j)
                    a2[i][j] = a[i][j];
                else
                    a2[i][j] = 0;
                if (i == j)
                    d[i][j] = a[i][j];
                else
                    d[i][j] = 0;
            }
        double [][] d_reverse = reverse_matrix(d);

        // Приведение к итерационному виду В
        double [][] B = multiply_matrix(multiply_matrix_int(d_reverse, -1), add_matrix(a1, a2));

        double [][] f = new double[size + 1][size + 1];
        for (int i = 1; i <= size; i++)
            for (int j = 1; j <= size; j++)
                f[i][1] = b[i];
        f = (multiply_matrix(d_reverse, f));
        double [][] x1 = f;
        double [][] x = add_matrix(multiply_matrix(B, x1), f);

        // Условие сходимости
        if (norm_matrix_second(B) < 1) {
            // Условие остановки
            while (norm_matrix_second(sub_matrix(x, x1)) >= 0.001) {
                x1 = x;
                x = add_matrix(multiply_matrix(B, x), f);
            }
        }
        else {
            noun = false;
            return noun;
        }

        for (int i = 1; i <= size; i++)
            for (int j = 1; j <= size; j++)
                result[i] = x[i][1];

        return noun;
    }



    // проверка симметричности
    public static int check_mirror_matrix(double [][] a) {
        int check = 1;

        for (int i = 1; i <= size; i++)
            for (int j = 1; j <= size; j++)
                if (a[i][j] != a[j][i]) {
                    check = 0;
                    break;
                }

        return check;
    }

    // транспонирование
    public static double[][] trans_matrix(double [][] matrix) {
        double [][] _matrix = new double[size + 1][size + 1];
        for (int i = 1; i <= size; i++){
            for (int j = 1; j <= size; j++){
                _matrix[i][j] = matrix[j][i];
            }
        }

        return _matrix;
    }

    // обратная матрица
    public static double [][] reverse_matrix(double [][] a) {
        float [][] e = new float [size + 1][size + 1];

        for (int i = 1; i <= size; i++)
            for (int j = 1; j <= size; j++) {
                e[i][j] = 0f;
                if (i == j)
                    e[i][j] = 1f;
            }

        double temp;
        for (int k = 1; k <= size; k++) {
            temp = a[k][k];

            for (int j = 1; j <= size; j++) {
                a[k][j] /= temp;
                e[k][j] /= temp;
            }

            for (int i = k + 1; i <= size; i++) {
                temp = a[i][k];
                for (int j = 1; j <= size; j++) {
                    a[i][j] -= a[k][j] * temp;
                    e[i][j] -= e[k][j] * temp;
                }
            }
        }

        for (int k = size; k > 1; k--) {
            for (int i = k - 1; i >= 0; i--) {
                temp = a[i][k];
                for (int j = 1; j <= size; j++) {
                    a[i][j] -= a[k][j] * temp;
                    e[i][j] -= e[k][j] * temp;
                }
            }
        }

        for (int i = 1; i <= size; i++)
            for (int j = 1; j <= size; j++)
                a[i][j] = e[i][j];

        return a;
    }

    // умножение двух матриц
    public static double [][] multiply_matrix(double [][] a, double [][] b) {
        int m = a.length;
        int n = b[0].length;
        int o = b.length;

        double[][] multiply = new double[m][n];

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < o; k++)
                    multiply[i][j] += a[i][k] * b[k][j];

        return multiply;
    }

    // умножение матрицы на число
    public static double [][] multiply_matrix_int(double [][] a, int b) {
        double[][] multiply = new double[size + 1][size + 1];
        for (int i = 1; i <= size; i++)
            for (int j = 1; j <= size; j++)
                multiply[i][j] = a[i][j] * b;
        return multiply;
    }

    // сложение двух матриц
    public static double [][] add_matrix (double [][] a, double [][] b) {
        double[][] add = new double[size + 1][size + 1];
        for (int i = 1; i <= size; i++)
            for (int j = 1; j <= size; j++)
                add[i][j] = a[i][j] + b[i][j];
        return add;
    }

    // вычитание двух матриц
    public static double [][] sub_matrix (double [][] a, double [][] b) {
        double[][] add = new double[size + 1][size + 1];
        for (int i = 1; i <= size; i++)
            for (int j = 1; j <= size; j++)
                add[i][j] = a[i][j] - b[i][j];
        return add;
    }
}