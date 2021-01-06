package aed.tables;

import aed.collections.QueueArray;

import java.util.Iterator;

public class OpenAdressingHashTable<Key,Value> {




    private static int[] primes = {
            17, 37, 79, 163, 331,
            673, 1361, 2729, 5471, 10949,
            21911, 43853, 87719, 175447, 350899,
            701819, 1403641, 2807303, 5614657,
            11229331, 22458671, 44917381, 89834777, 179669557
    };

    public OpenAdressingHashTable()
    {
        //TODO: implement
    }

    public int size()
    {
        //TODO: implement
    }

    public int getCapacity()
    {
        //TODO: implement
    }

    public float getLoadFactor()
    {
        //TODO: implement
    }

    public int getDeletedNotRemoved()
    {
        //TODO: implement
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
