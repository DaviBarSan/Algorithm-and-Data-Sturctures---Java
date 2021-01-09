package aed.tables;

import javax.security.auth.kerberos.KerberosTicket;

public class OpenAdressingHashTable<Key,Value> {
    int m;
    int size, primeIndex;
    float loadFactor;
    int deletedCounter;
    public Key deletedNotRemoved;

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
        int flag = 0;
        //avoiding infite loop if key is not in hashTable;
        while (flag < 2) {
            for (; keys[i] != null && keys[i] != deletedNotRemoved; i = (i + 1) % m) {
                if (keys[i].equals(k)) return values[i];
                //avoiding infite loop if key is not in hashTable;
                if (i % hash(k) == 0) flag++;
            }
        }
        return null;
    }

    public void put(Key k, Value v)
    {
        if (loadFactor>=0.5) resize(++primeIndex);

        int i = hash(k);
        //avançar com o ponteiro i até um valor null dentro da hashTable, fazendo linearProbing
        for (; keys[i] != null; i = (i+1) % m){
            //caso o hash coloque o i no pont
            if(keys[i].equals(k)){
                values[i] = v;
                return;
            }
        }
        //caso o ponteiro i direcionado pelo hash caia diretamente em uma bucket vazia, a condição do for irá falhar
        //os valores ja podem ser atualizados automaticamente;
        keys[i] = k;
        values[i] = v;
        size++;
        loadFactor = size/m;

    }

    public void delete(Key k)
    {
		//lazy delete
        int i = hash(k);
        for(; keys[i] != null && keys[i] != deletedNotRemoved; i = (i+1) % m){
            if (keys[i].equals(k)){
               k = deletedNotRemoved;
               deletedCounter++;
            }
        }
    }

    public Iterable<Key> keys() {
        //TODO: implement
    }
//-------------------- acessory methods ----------------------
    private void resize(int primeIndex) {
        //avoid errors
        if (primeIndex < 0 || primeIndex >= primes.length) return;

        int oldM = m;
        //update this.primeIndex using parameter value;
        this.primeIndex = primeIndex;
        //new hashTable, using new prime as reference to capacity;
        //Constructor(newSize) already update m value to next primeIndex
        OpenAdressingHashTable<Key,Value> aux = new OpenAdressingHashTable<Key,Value>(this.primeIndex);
        //placing all existing keys on new hashTable
        for (int i = 0; i < oldM; i++){
            if(keys[i] != null) aux.put(keys[i], values[i]);
        }
        this.keys = aux.keys;
        this.values = aux.values;
        this.loadFactor = size/m;

    }
    public OpenAdressingHashTable(int newPrimeIndex){
        this.m = primes[newPrimeIndex];

    }


}
