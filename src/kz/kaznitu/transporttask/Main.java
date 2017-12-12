package kz.kaznitu.transporttask;


import java.util.Scanner;

public class Main{

    public static void main(String[] args) {

        System.out.println("Решение транспортной задачи");
        System.out.println("В задаче рассматривается M строк и N столбцов");
        System.out.println("Введите M и N");
        Scanner in = new Scanner(System.in) ;
        int M = in.nextInt() ; // Количество строк
        int N = in.nextInt() ; // Количество столбцов

        // A[i] и B[j] массивы строк и столбцов соответственно поставщиков и магазинов
        // DA[i] и DB[j] предназначены для хранения их копии (A[i] и B[j])
        int [] A = new int[M] ;
        int [] DA = new int[M] ;
        int [] B = new int[N] ;
        int [] DB = new int[N] ;

        // массивы IR[i] и IC[j] указывают (если их элементы равны 1),
        // что соответствующие строки и столбцы удалены
        int [] IR = new int[M] ;
        int [] IC = new int[N] ;

        // массивы TR[i] и TC[j] являются счетчиками базисных переменных
        // в строках и столбцах
        int [] TR = new int[M] ;
        int [] TC = new int[N] ;

        // массивы IU[i] и IV[j] указывают (если их элементы равны 1),
        // что элементам массивов U и V были присвоены значения
        int [] U = new int[M] ;
        int [] IU = new int[M] ;
        int [] V = new int[N] ;
        int [] IV = new int[N] ;

        // нулевое инициализация
        for (int i = 0; i < M; i++){
            U[i] = 0 ;
            IU[i] = 0 ;
            IR[i] = 0 ;
        }
        for (int j = 0; j < N; j++){
            V[j] = 0 ;
            IV[j] = 0 ;
            IC[j] = 0 ;
        }


        // массив С[i][j] содержит стоимости перевозки
        int [][] C = new int[M][N] ;

        // массив X[i][j] неизвестные
        int [][] X = new int[M][N] ;

        // массив IX[i][j] обозначает базис, если его элементы равны 1
        int [][] IX = new int[M][N] ;

        // нулевое инициализация
        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++){
                X[i][j] = 0 ;
                IX[i][j] = 0 ;
            }
        }


        // прочие массивы являются рабочими
        int [] RT = new int[M+N] ;
        int [] CT = new int[M+N] ;
        int [][] D = new int[M][N] ;
        int [][] MM = new int[M][N] ;

        // Ввести стоимости перевозки
        System.out.println("Ввести стоимости перевозки: ");
        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++){
                C[i][j] = in.nextInt() ;
            }
        }

        // Ввести значение объема запасов поставщиков
        System.out.println("Ввести значение объема запасов поставщиков: ");
        for(int i = 0; i < M; i++){
            A[i] = in.nextInt() ;
            DA[i] = A[i] ;
        }

        // Ввести значение потребности магазинов
        System.out.println("Ввести значение потребности магазинов: ");
         for(int j = 0; j < N; j++){
            B[j] = in.nextInt() ;
            DB[j] = B[j] ;
         }

         // Найти наименьшую стоимость в массиве C[RI][CJ]
        int c = 0, // количество базисных переменных
                ct = 0, cr = 0 ;
        do {
            int RI = 0; // индекс строки минимального элемента
            int CJ = 0; // индекс столбца минимального элемента
            int Y = 1000; // минимальный элемент
            for (int i = 0; i < M; i++) {
                if (IR[i] != 1) {
                    for (int j = 0; j < N; j++) {
                        if (IC[j] != 1) {
                            if (C[i][j] < Y) {
                                Y = C[i][j];
                                RI = i;
                                CJ = j;
                                System.out.println("("+RI+","+CJ+")");
                            }

                        }
                    }
                }
            } // конец цикла нахождение минимального элемента

            // Теперь, минимальный элемент по всем строкам и столбцам
            // присвоим в массив X[RI][CJ]
            // пометить базис в массиве IX
            // удалить строку или столбец и пометить их
            // определить другую сумму и подсчитать количество удалений строк
            if (DA[RI] <= DB[CJ] && cr < M - 1) {
                X[RI][CJ] = DA[RI];
                IX[RI][CJ] = 1;
                DB[CJ] = DB[CJ] - DA[RI];
                DA[RI] = 0;
                IR[RI] = 1;
                c = c + 1;
                cr = cr + 1;

            } else {
                X[RI][CJ] = DB[CJ];
                IX[RI][CJ] = 1;
                DA[RI] = DA[RI] - DB[CJ];
                DB[CJ] = 0;
                IC[CJ] = 1;
                c = c + 1;
                ct = ct + 1;
            }

            TR[RI] = TR[RI] + 1; // счетчики базисных переменных
            TC[CJ] = TC[CJ] + 1;
            System.out.println(c);
        }while(c < M+N-1) ;
        cr = cr + 1 ;

        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++){
                System.out.print(X[i][j] + "  ");
            }
            System.out.println();
        }
    }

}