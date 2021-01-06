package aed.sorting;

import java.util.Arrays;
import java.util.Random;

public class QuickSort extends Sort {


    public static int partition(Comparable[] a, int low, int high) {
        Random randomIndex = new Random();


        int i = low;
        int j = high + 1; //(+1) recua o j a cada chamada recursiva, quando a primeira ''metade'' ja foi sorted

        int randomPivot = randomIndex.nextInt(high - low + 1) + low;
        exchange(a, low, randomPivot);
        Comparable v = a[low];
        // i e j são ponteiros do scan left e right respectivamente.

        while (true) {
			/*
			o pointeiro i deve avançar enquanto não encontrar um elemento maior
			que o pivot selecionado
			se o i avançar até o high sem encontrar elementos maiores
			que o pivot, não há troca;
			*/
            while (less(a[++i], v))
                if (i == high) break;
				/*
				o ponteiro j deve recuar enquanto não encontrar um elemento menor que
				o pivot selecinado
				*/
            while (less(v, a[--j]))
                if (j == low) break;
					/*
					se o j recuar até o low sem encontrar elementos menores
					que o pivot, não há troca;
					*/
            //quando estes ponteiros estiverem definidos em suas respectiva posições,
            // sairão do while-loop e então trocar de posição
            if (i >= j)
                break; //não realizar a troca quando o j ja estiver na posição oficial do pivot (sai do while(true) loop)
            exchange(a, i, j);
        }
        // ao fim das trocas, entre os ponteiros, o pivot e j devem trocar
        // de posição, uma vez que j é a posição definitiva do pivot no array ordenado
        exchange(a, low, j);
        return j;
    }

    public static void sort(Comparable[] a) {
        sortInner(a, 0, a.length - 1);
    }

    private static void sortInner(Comparable[] a, int low, int high) //RECURSÃO
    {
        if (high <= low) return;
        int j = partition(a, low, high);
        sortInner(a, low, j - 1);
        sortInner(a, j + 1, high);

    }


    public static void medianSort(Comparable[] a) {
        innerMedianSort(a, 0, a.length - 1);

        //fazer medianSort, colocar condicional boolena do cutOff, if true fazer insertionSort no começo da recursão

    }

    private static void innerMedianSort(Comparable[] a, int low, int high) {

        if (cutOff(a, low, high, 10)) {
            insertionSort(a, low, high);
            return;
        }


        if (high <= low + 1)
            return;  // low+1 e não low, uma vez que o elemento low já será corretamente posicionado por
        // sortingSentinels assumindo-se que j = low, e low < pivot, logo low não precisa ser contabilizado
        // na comparação visto que é menor que low+1;

        int j = medianPartition(a, low, high);

        innerMedianSort(a, low, j - 1);
        innerMedianSort(a, j, high); // j+1  e não j, uma vez que em determinadas situações j+1, assumindo low = i+1 resulta
        // resultava no negligicamento de um elemento entre j antigo e novo pivot, que ocasionalmente
        // não seria corrigido em sortingSentinels, caso não fosse oportunamente selecionado.


    }

    private static int medianPartition(Comparable[] a, int low, int high) {

        sortingSentinels(a, low, high);

        int i = low + 1;
        int j = high + 1;
        Comparable v = a[low + 1];

        while (true) {
            while (less(a[++i], v)) ;
            while (less(v, a[--j])) ;

            if (i >= j) break;
            exchange(a, i, j);
        }
        exchange(a, j, low + 1);

        return j;
    }


    public static Comparable quickSelect(Comparable[] a, int n) {
        if (a.length == 1) return a[0];
        int j = innerQuickSelect(a, 0, a.length - 1, n);
        return a[j];
    }

    private static int innerQuickSelect(Comparable[] a, int low, int high, int n) {

        if (high <= low) return low;

        int j = partition(a, low, high);
        //DUVIDA: Como retornar apenas o ultimo valor de j obtido ao longo das chamadas recursivas? Ou seja j final == n
        if (n == j) return j;
        if (n < j) {
            return innerQuickSelect(a, low, j - 1, n);
        } else
            return innerQuickSelect(a, j + 1, high, n);

    }


    // Métodos acessórios--------------------------------------------------------------------------------------;

    private static Comparable[] randomArray() {
        Random r = new Random();
        Comparable[] intArray = new Comparable[10];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = r.nextInt(25);
        }
        return intArray;
    }

    private static void insertionSort(Comparable[] a, int low, int high) {
        if (high <= low) return;

        for (int i = low + 1; i < high + 1; i++) { //aumentar elemntos no subarray em ordenação, um a cada iteração
            for (int j = i; j > low; j--)    //uma vez que foi adicionado ao subarray, o ponteiro j inicia neste
                if (less(a[j], a[j - 1]))  //caso o elem adicionado ao subarray seja menor que a proxima iteração de j
                    exchange(a, j, j - 1);    //trocar j com o elemento anterior (j-1)
                else break; // uma vez que todos os antecessores a j ja estão ordenados, caso não seja menor que o
            // primeiro antecessor, já é maior que todos os outros. break na iteração;
        }
    }


    private static void sortingSentinels(Comparable[] a, int low, int high) {
        /*Comparable oldFirst = (int) a[0]; Comparable oldMid = (int) a[((a.length-1)/2)]; Comparable oldLast = (int) a[a.length-1];
         *Comparable[] sentinelsArray = {oldFirst, oldMid, oldLast};
         *insertionSort(sentinelsArray);
         */

        int lowerIndex = low;
        int midIndex = low + (high - low) / 2;
        int higherIndex = high;


        if (less(a[midIndex], a[lowerIndex]))         // sorting first element
            exchange(a, midIndex, lowerIndex);
        if (less(a[higherIndex], a[lowerIndex]))
            exchange(a, higherIndex, lowerIndex);                //last x lowest;
        //mid x lowest;
        if (less(a[higherIndex], a[midIndex])) {
            exchange(a, higherIndex, midIndex);     //sorting mid, and last
        }


        //mid x last;


        exchange(a, midIndex, lowerIndex + 1);
        //troca mid pra a[low+1] posição que sera pivot
    }


    private static boolean cutOff(Comparable[] a, int low, int high, int minArraySize) {
        int arraySize = high - low;
        if (arraySize < minArraySize) {
            return true;
        } else return false;
    }


    public static void main(String[] args) {
        //situações iniciais do array
        //-->> Array ordenado, array ordenado inversamente,
        //array parcialmente (1/8 == random), array aleatório.



        Random r = new Random();
        Comparable[] RandomArray = new Comparable[100000];

        for (int i = 0; i < RandomArray.length; i++) {
            RandomArray[i] = r.nextInt(100000);
        }
        long[] timespamArrayQuickSort = new long[75];
        long[] timespamArrayMedianSort = new long[75];

        long sumQuickTime = 0;
        long meanQuickTime = 0;

        long sumMedianTime = 0;
        long meanMedianTime = 0;

        for (int i = 0; i < 75; i++) {

            long startTimeQuickSort = System.currentTimeMillis();
            sort(RandomArray);
            long endTimeQuickSort = System.currentTimeMillis();

            long startTimeMedianSort = System.currentTimeMillis();
            medianSort(RandomArray);
            long endTimeMedianSort = System.currentTimeMillis();

            long timespanQuickSort = endTimeQuickSort - startTimeQuickSort;
            timespamArrayQuickSort[i] = timespanQuickSort;

            long timespanMedianSort = endTimeMedianSort - startTimeMedianSort;
            timespamArrayMedianSort[i] = timespanMedianSort;


            sumQuickTime += timespanQuickSort;
            sumMedianTime += timespanMedianSort;

        }

        meanQuickTime = sumQuickTime / timespamArrayQuickSort.length;
        meanMedianTime = sumMedianTime / timespamArrayMedianSort.length;


        System.out.println("For random array with 100.000 elements, QuickSort average time of 75 tests: " + meanQuickTime + "ms");
        System.out.println("For random array with 100.000 elements, MedianSort average time of 75 tests: " + meanMedianTime + "ms");


        //all elements are random with bound << array capacity (muitos elementos repetidos)-----------------------------

        Comparable[] RandomArrayRepeatedElements = new Comparable[100000];
        for (int i = 0; i < RandomArrayRepeatedElements.length; i++) {
            RandomArrayRepeatedElements[i] = r.nextInt(5);
        }

        long[] timespamArrayQuickSortRepeatedElements = new long[75];
        long[] timespamArrayMedianSortRepeatedElements = new long[75];


        long sumQuickTimeRepeatedElements = 0;
        long meanQuickTimeRepeatedElements = 0;

        long sumMedianTimeRepeatedElements = 0;
        long meanMedianTimeRepeatedElements = 0;


        for (int i = 0; i < 75; i++) {

            long startTimeQuickSort = System.currentTimeMillis();
            sort(RandomArrayRepeatedElements);
            long endTimeQuickSort = System.currentTimeMillis();

            long startTimeMedianSort = System.currentTimeMillis();
            medianSort(RandomArrayRepeatedElements);
            long endTimeMedianSort = System.currentTimeMillis();

            long timespanQuickSort = endTimeQuickSort - startTimeQuickSort;
            timespamArrayQuickSortRepeatedElements[i] = timespanQuickSort;

            long timespanMedianSort = endTimeMedianSort - startTimeMedianSort;
            timespamArrayMedianSortRepeatedElements[i] = timespanMedianSort;


            sumQuickTimeRepeatedElements += timespanQuickSort;
            sumMedianTimeRepeatedElements += timespanMedianSort;

        }
        meanQuickTimeRepeatedElements = sumQuickTimeRepeatedElements / timespamArrayQuickSortRepeatedElements.length;
        meanMedianTimeRepeatedElements = sumMedianTimeRepeatedElements / timespamArrayMedianSortRepeatedElements.length;

        System.out.println("For random array with 100.000 elements, many repeated, QuickSort average time of 75 tests: " + meanQuickTimeRepeatedElements + "ms");
        System.out.println("For random array with 100.000 elements, many reapeated,  MedianSort average time of 75 tests: " + meanMedianTimeRepeatedElements + "ms");


        //already sorted array------------------------------------------------------------------------------------------
        Comparable[] AlreadySorted = new Comparable[100000];

        long[] timespamArrayQuickSortAlreadySorted = new long[75];
        long[] timespamArrayMedianSortAlreadySorted = new long[75];

        long sumQuickTimeSorted = 0;
        long meanQuickTimeSorted = 0;

        long sumMedianTimeSorted = 0;
        long meanMedianTimeSorted = 0;


        for (int i = 0; i < AlreadySorted.length; i++) {
            AlreadySorted[i] = i;
        }

        for (int i = 0; i < 75; i++) {

            long startTimeQuickSort = System.currentTimeMillis();
            sort(AlreadySorted);
            long endTimeQuickSort = System.currentTimeMillis();

            long startTimeMedianSort = System.currentTimeMillis();
            medianSort(AlreadySorted);
            long endTimeMedianSort = System.currentTimeMillis();

            long timespanQuickSort = endTimeQuickSort - startTimeQuickSort;
            timespamArrayQuickSortAlreadySorted[i] = timespanQuickSort;

            long timespanMedianSort = endTimeMedianSort - startTimeMedianSort;
            timespamArrayMedianSortAlreadySorted[i] = timespanMedianSort;


            sumQuickTimeSorted += timespanQuickSort;
            sumMedianTimeSorted += timespanMedianSort;

        }
        meanQuickTimeSorted = sumQuickTimeRepeatedElements / timespamArrayQuickSortRepeatedElements.length;
        meanMedianTimeSorted = sumMedianTimeRepeatedElements / timespamArrayMedianSortRepeatedElements.length;

        System.out.println("For already sorted array with 100.000 elements, QuickSort average time of 75 tests: " + meanQuickTimeSorted + "ms");
        System.out.println("For already sorted array with 100.000 elements, MedianSort average time of 75 tests: " + meanMedianTimeSorted + "ms");

        //partialy sorted array (1/8 == random elements)
        Comparable[] PartialySorted = new Comparable[100000];

        long[] timespamArrayQuickSortPartialySorted = new long[75];
        long[] timespamArrayMedianSortPartialySorted = new long[75];

        long sumQuickTimePartialy = 0;
        long meanQuickTimePartialy = 0;

        long sumMedianTimePartialy = 0;
        long meanMedianTimePartialy = 0;


        for (int i = 0; i < PartialySorted.length; i++)
            PartialySorted[i] = i;
        for (int m = 0; m < PartialySorted.length/8; m++)
            PartialySorted[m] = r.nextInt(PartialySorted.length/8);

        for (int i = 0; i < 75; i++) {

            long startTimeQuickSort = System.currentTimeMillis();
            sort(PartialySorted);
            long endTimeQuickSort = System.currentTimeMillis();

            long startTimeMedianSort = System.currentTimeMillis();
            medianSort(PartialySorted);
            long endTimeMedianSort = System.currentTimeMillis();

            long timespanQuickSort = endTimeQuickSort - startTimeQuickSort;
            timespamArrayQuickSortPartialySorted[i] = timespanQuickSort;

            long timespanMedianSort = endTimeMedianSort - startTimeMedianSort;
            timespamArrayMedianSortPartialySorted[i] = timespanMedianSort;


            sumQuickTimePartialy += timespanQuickSort;
            sumMedianTimePartialy += timespanMedianSort;
        }
            meanQuickTimePartialy = sumQuickTimePartialy / timespamArrayQuickSortPartialySorted.length;
            meanMedianTimePartialy = sumMedianTimePartialy / timespamArrayMedianSortPartialySorted.length;

            System.out.println("For partialy sorted array with 100.000 elements, QuickSort average time of 75 tests: " + meanQuickTimePartialy + "ms");
            System.out.println("For partialy sorted array with 100.000 elements, MedianSort average time of 75 tests: " + meanMedianTimePartialy + "ms");


        }
    //reversed sorted array---------------------------------------------------------------------------------------------
    /*
    Comparable[] ReversedSorted= new Comparable[100000];

    long[] timespamArrayQuickSortReversedSorted = new long[75];
    long[] timespamArrayMedianSortReversedSorted = new long[75];

    long sumQuickTimeReversed = 0;
    long meanQuickTimeReversed = 0;

    long sumMedianTimeReversed = 0;
    long meanMedianTimeReversed = 0;

    int totalLenght = ReversedSorted.length-1;

    for (int i = 0; i <= totalLenght ; i- totalLe)
    {
        ReversedSorted[ReversedSorted.length - i] = i;
    }


                ----------------------TESTING CUTOFF---------------------

    CUTOFF = 10
For random array with 100.000 elements, QuickSort average time of 75 tests: 16ms
For random array with 100.000 elements, MedianSort average time of 75 tests: 6ms
For random array with 100.000 elements, many repeated, QuickSort average time of 75 tests: 12ms
For random array with 100.000 elements, many reapeated,  MedianSort average time of 75 tests: 7ms
For already sorted array with 100.000 elements, QuickSort average time of 75 tests: 12ms
For already sorted array with 100.000 elements, MedianSort average time of 75 tests: 7ms
For partialy sorted array with 100.000 elements, QuickSort average time of 75 tests: 14ms
For partialy sorted array with 100.000 elements, MedianSort average time of 75 tests: 3ms

    CUTOFF = 25
For random array with 100.000 elements, QuickSort average time of 75 tests: 16ms
For random array with 100.000 elements, MedianSort average time of 75 tests: 7ms
For random array with 100.000 elements, many repeated, QuickSort average time of 75 tests: 12ms
For random array with 100.000 elements, many reapeated,  MedianSort average time of 75 tests: 7ms
For already sorted array with 100.000 elements, QuickSort average time of 75 tests: 12ms
For already sorted array with 100.000 elements, MedianSort average time of 75 tests: 7ms
For partialy sorted array with 100.000 elements, QuickSort average time of 75 tests: 12ms
For partialy sorted array with 100.000 elements, MedianSort average time of 75 tests: 4ms



    WITHOUT CUTOFF
For random array with 100.000 elements, QuickSort average time of 75 tests: 16ms
For random array with 100.000 elements, MedianSort average time of 75 tests: 9ms
For random array with 100.000 elements, many repeated, QuickSort average time of 75 tests: 13ms
For random array with 100.000 elements, many reapeated,  MedianSort average time of 75 tests: 9ms
For already sorted array with 100.000 elements, QuickSort average time of 75 tests: 13ms
For already sorted array with 100.000 elements, MedianSort average time of 75 tests: 9ms
For partialy sorted array with 100.000 elements, QuickSort average time of 75 tests: 12ms
For partialy sorted array with 100.000 elements, MedianSort average time of 75 tests: 4ms


    */


}
