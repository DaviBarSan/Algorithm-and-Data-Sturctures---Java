package aed.tables;

import aed.collections.QueueArray;

import java.util.Iterator;

public class OpenAdressingHashTable<Key,Value> {

    int m;
    int size, primeIndex;
    float loadFactor;
    boolean deleteNotRemoved;
    int deleteCount;


    private Key[] keys;
    private Value[] values;

    private static final int MIN_PRIMEINDEX = 0;








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
        this.m = primes[MIN_PRIMEINDEX + primeIndex];
        this.loadFactor = size/primeIndex;

        this.deleteCount = 0;
        this.deleteNotRemoved = deleteNotRemoved;

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
        return deleteCount;
    }

    public boolean containsKey(Key k)
    {
        //TODO: implement
    }

    public Value get(Key k)
    {
		//TODO: implement
    }

    public void put(Key k, Value v)
    {
		//TODO: implement
    }

    public void delete(Key k)
    {
		//lazy delete
        //TODO: implement
    }

    public Iterable<Key> keys() {
        //TODO: implement
    }


}
