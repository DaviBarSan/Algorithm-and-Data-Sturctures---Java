package aed.sorting;

import java.util.Arrays;
import java.util.Random;

public class QuickSort extends Sort {



    public static int partition (Comparable[] a, int low, int high) {
        int i = low;
        int j = high+1; //(+1) recua o j a cada chamada recursiva, quando a primeira ''metade'' ja foi sorted

        int randomPivot = randomPivotIndex(low, high);
        exchange(a, low, randomPivot);

        Comparable v = a[low];
        // i e j são ponteiros do scan left e right respectivamente.

        while(true) {
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
            while(less(v, a[--j]))
                if (j == low) break;
					/*
					se o j recuar até o low sem encontrar elementos menores
					que o pivot, não há troca;
					*/
            //quando estes ponteiros estiverem definidos em suas respectiva posições,
            // sairão do while-loop e então trocar de posição


            if(i>=j) break; //não realizar a troca quando o j ja estiver na posição oficial do pivot (sai do while(true) loop)
            exchange(a, i, j);
        }
        // ao fim das trocas, entre os ponteiros, o pivot e j devem trocar
        // de posição, uma vez que j é a posição definitiva do pivot no array ordenado
        exchange(a, low, j);
        return j;
    }
    private static int randomPivotIndex(int lowerBound, int highBound) {


        Random randomIndex = new Random();
        return randomIndex.nextInt(highBound - lowerBound +1) + lowerBound;

    }


    public static void sort(Comparable[] a)
    {

        sortInner(a, 0, a.length-1);

    }

    private static void sortInner(Comparable[] a, int low, int high) //RECURSÃO
    {
        if (high <= low) return;
        int j = partition(a, low, high);
        sortInner(a, low, j - 1);
        sortInner(a, j + 1, high);

    }


    public static void medianSort(Comparable[] a)
    {
        innerMedianSort(a, 0, a.length-1);


        //fazer medianSort, colocar condicional boolena do cutOff, if true fazer insertionSort no começo da recursão

    }

    private static void innerMedianSort(Comparable[] a, int low, int high) {



        if(cutOff(a, low, high)) {
            insertionSort(a, low, high);
            return;}
        if (high <= low+1) return;  // low+1 e não low, uma vez que o elemento low já será corretamente posicionado por
        // sortingSentinels assumindo-se que j = low, e low < pivot, logo low não precisa ser contabilizado
        // na comparação visto que é menor que low+1;

        int j = medianPartition(a, low, high);

        innerMedianSort(a, low, j-1);
        innerMedianSort(a, j, high); // j+1  e não j, uma vez que em determinadas situações j+1, assumindo low = i+1 resulta
        // resultava no negligicamento de um elemento entre j antigo e novo pivot, que ocasionalmente
        // não seria corrigido em sortingSentinels, caso não fosse oportunamente selecionado.



    }
    private static int medianPartition(Comparable[] a, int low, int high) {

        sortingSentinels(a, low, high);

        int i = low+1;
        int j = high+1;
        Comparable v = a[low+1];

        while (true) {
            while (less(a[++i], v));
            while (less(v, a[--j]));

            if (i >= j) break;
            exchange(a, i, j);
        }
        exchange(a, j, low+1);

        return j;
    }



    public static Comparable quickSelect(Comparable[] a, int n)
    {
        int j = innerQuickSelect(a, 0, a.length-1, n);

        return a[j];


    }

    private static int innerQuickSelect(Comparable[] a, int low, int high, int n) {


        int j = partition(a, low, high);
        //DUVIDA: Como retornar apenas o ultimo valor de j obtido ao longo das chamadas recursivas? Ou seja j final == n
        if (n==j) return j;
        if (n<j) {
            return innerQuickSelect(a, low, j-1, n);
        }else
            return innerQuickSelect(a, j+1, high, n);

    }


    // Métodos acessórios--------------------------------------------------------------------------------------;

    private static Comparable[] randomArray() {
        Random r = new Random();
        Comparable[]  intArray = new Comparable[10];
        for (int i = 0; i<intArray.length; i++){
            intArray[i] = r.nextInt(10);
        }
        return intArray;
    }

    private static void insertionSort(Comparable[] a, int low, int high){
        if (high <= low) return;

        for (int i = low+1; i < high+1; i++) { //aumentar elemntos no subarray em ordenação, um a cada iteração
            for (int j = i; j > low; j--)	//uma vez que foi adicionado ao subarray, o ponteiro j inicia neste
                if (less(a[j], a[j-1]))  //caso o elem adicionado ao subarray seja menor que a proxima iteração de j
                    exchange(a, j, j-1);	//trocar j com o elemento anterior (j-1)
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
        int midIndex = low+ (high-low) / 2;
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


        exchange(a, midIndex, lowerIndex+1);
        //troca mid pra a[low+1] posição que sera pivot
    }



    private static boolean cutOff(Comparable[] a, int low, int high) {
        int arraySize = high - low;
        int minArraySize = 1;
        if (arraySize < minArraySize){
            return true;}
        else return false;
    }


    public static void main(String[] args)
    {

        Comparable[] rdmArray = randomArray();

        Comparable[] arrayTest = new Comparable[10];
        arrayTest = new Comparable[]{4, 1, 8, 1, 5, 9, 1, 4, 2, 5};

        System.out.println(Arrays.toString(rdmArray));


        medianSort(rdmArray);
        //medianSort(rdmArray);
        System.out.println(Arrays.toString(rdmArray));


        //throw new UnsupportedOperationException();

    }

}
