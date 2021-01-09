package aed.tables;

import java.util.Arrays;

public class OpenAdressingHashTable<Key,Value> {
    int m;
    int size, primeIndex;
    float loadFactor;
    int deletedCounter;
    private Key deletedNotRemoved = (Key) new Object();;

    private Key[] keys;
    private Value[] values;


    private static int[] primes = {
            17, 37, 79, 163, 331,
            673, 1361, 2729, 5471, 10949,
            21911, 43853, 87719, 175447, 350899,
            701819, 1403641, 2807303, 5614657,
            11229331, 22458671, 44917381, 89834777, 179669557
    };

    public OpenAdressingHashTable()
    {
        //Keys e Values são arrays com index correspondentes;
        //m = lenght dos arrays Keys e Values;
        //n = número de elementos nestes arrays;

        this.size = 0;
        this.primeIndex = primeIndex;
        this.m = primes[primeIndex];
        this.loadFactor = 0;
        this.deletedCounter = 0;
        this.deletedNotRemoved = deletedNotRemoved;



        this.keys = (Key[]) new Object[m];
        this.values = (Value[]) new Object[m];


    }
    private int hash(Key k) {
        //using hashCode with an function that convert negative
        //values into positive. Otherway, it would generate negative
        //indexes;
        //% operator guarantee loop over array when hashCode is bigger
        //than array lenght (m);
        return (k.hashCode() & 0x7fffffff) % m;
    }

    public int size()
    {
        return size;
    }

    public int getCapacity()
    {
        return m;
    }

    public float getLoadFactor()
    {
        return loadFactor;
    }

    public int getDeletedNotRemoved()
    {
        return deletedCounter;
    }

    public boolean containsKey(Key k)
    {
        int i = hash(k);
        for (;keys[i] != null; i = (i+1) % m)
            if (k.equals(i)) return true;
        return false;
    }

    public Value get(Key k)
    {
        int i = hash(k);
        if (keys[i] == deletedNotRemoved) return null;
        for (; keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(k)) return values[i];
        }
        return null;
    }

    public void put(Key k, Value v)
    {
        if (loadFactor>=0.5) resize(++primeIndex);
        int i = hash(k);
        //avançar com o ponteiro i até um valor null dentro da hashTable, fazendo linearProbing
        for (;keys[i] != null; i = (i+1) % m){
            //caso nessa busca encontre uma chave correspondente, fazer o update da chave falor
            if(keys[i].equals(k)){
                values[i] = v;
                return;
            }
            else if (keys[i] == deletedNotRemoved)
                break;
        }
        //caso o ponteiro i direcionado pelo hash caia diretamente em uma bucket vazia, a condição do for irá falhar
        //os valores ja podem ser atualizados automaticamente;
        keys[i] = k;
        values[i] = v;
        size++;
        this.loadFactor =(float) size/m;
    }

    public void delete(Key k)
    {
		//lazy delete
        int i = hash(k);
        for(; keys[i] != null; i = (i+1) % m){
            if (keys[i].equals(k)){
               keys[i] = deletedNotRemoved;
               deletedCounter++;
            }
        }
    }
    /*
    public Iterable<Key> keys() {

        //TODO: implement

    }
    */
//-------------------- acessory methods ----------------------
    private void resize(int primeIndex) {
        //avoid errors
        if (primeIndex < 0 || primeIndex >= primes.length) return;

        //new hashTable, using new prime as reference to capacity;
        //Constructor(newSize) already update m value to next primeIndex
        OpenAdressingHashTable<Key,Value> aux = new OpenAdressingHashTable<Key,Value>(this.primeIndex);
        //placing all existing keys on new hashTable
        for (int i = 0; i < m; i++){
            if(keys[i] == deletedNotRemoved) {
                deletedCounter--;
                size--;
            }
            else if(keys[i] != null) aux.put(keys[i], values[i]);
        }
        this.keys = aux.keys;
        this.values = aux.values;
        this.m = aux.m;
        this.loadFactor = (float) size/m;

    }
    public OpenAdressingHashTable(int newPrimeIndex){
        this.m = primes[newPrimeIndex];
        this.keys = (Key[]) new Object[m];
        this.values = (Value[]) new Object[m];



    }


}
/*  ------CORNER SITUATION----
    ->quando usar put, e encontrar uma tombstone, usar esta ao invés de continuar buscando uma key null; DONE
    ->quando fizer resize, a tombstone não deve inserida novamente, então deve-se diminuir size e deletedCounter; DONE
    ->se ao fazer delete de uma key o fator de carga seja < 0.125 (1/8), deve-se fazer resize para primeIndex--,
    ->que no entanto deve ser no minimo [37] (MIN_PRIMEINDEX = 1)

 */