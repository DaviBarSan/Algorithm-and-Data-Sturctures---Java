package aed.tables;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class OpenAddressingHashTable<Key,Value>{
    int m;
    int size, primeIndex;
    private static final int MIN_PRIMEINDEX = 1;
    float loadFactor;
    int deletedCounter;


    private Key[] keys;
    private Value[] values;


    private static final int[] primes = {
            17, 37, 79, 163, 331,
            673, 1361, 2729, 5471, 10949,
            21911, 43853, 87719, 175447, 350899,
            701819, 1403641, 2807303, 5614657,
            11229331, 22458671, 44917381, 89834777, 179669557
    };
    @SuppressWarnings("unchecked")
    public OpenAddressingHashTable() {
        //Keys e Values são arrays com index correspondentes;
        //m = lenght dos arrays Keys e Values;
        //n = número de elementos nestes arrays;

        this.size = 0;
        this.primeIndex = 1;
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

    public int size() {
        return size;
    }

    public int getCapacity() {
        return m;
    }

    public float getLoadFactor() {
        return loadFactor;
    }

    public int getDeletedNotRemoved() {
        return deletedCounter;
    }

    public boolean containsKey(Key k) {
        int i = hash(k);
        for (; keys[i] != null; i = (i + 1) % m)
            if (keys[i].equals(k) && values[i] != null) return true;
        return false;
    }

    public Value get(Key k) {
        int i = hash(k);
        //difference between == and .equals():
        // == operator do an adress comparation, setted in object memory location;
        //.equals() method compares the content. Even if the objects has different memory adress location, if the content
        //is equal, it return true
        for (; keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(k)) {
                if (values[i] == null) {
                    return null;
                }
                return values[i];
            }
        }
        return null;
    }

    public void put(Key k, Value v) {
        if (k == null)return;
        if(v == null) {
            delete(k);
            return;
        }
        int i = hash(k);
        //avançar com o ponteiro i até um valor null dentro da hashTable, fazendo linearProbing
        for (; keys[i] != null; i = (i + 1) % m) {
            //check if key already exists
            if (keys[i].equals(k)) {
                //se for inserida chave ja existente mas antes estava marcada como apagada (values[i] == null), será um reciclagem;
                if (values[i] == null) {
                    deletedCounter--;
                    break;
                }
                //updating value
                values[i] = v;
                return;
            }
        }
        //once key index was found, insert it
        keys[i] = k;
        values[i] = v;
        size++;
        this.loadFactor = (float) size / m;
        if (loadFactor >= 0.5f) {
            resize(++primeIndex);
        }
    }

    public void delete(Key k) {
        //lazy delete
        if (k == null) return;
        int i = hash(k);
        for (; keys[i] != null; i = (i + 1) % m) {
            //i index already positionated in exactely index that might be deleted
            if (keys[i].equals(k)) {
                if (values[i] == null) return;
                values[i] = null;
                deletedCounter++;
                size--;
                this.loadFactor = (float) size/m;
                break;
            }
        }
        if (primeIndex > MIN_PRIMEINDEX && loadFactor < 0.125) {
            resize(--primeIndex);
        }
        if (((float) deletedCounter / m) >= 0.2f) {
            resize(primeIndex);
        }
    }
    public Iterable<Key> keys(){
        return new Iterable<Key>() {
            @Override
            public Iterator<Key> iterator() {
                return new Iterator<Key>() {
                    int currentIndex = 0;
                    int validKeys = 0;

                    @Override
                    public boolean hasNext() {
                        return validKeys < size;
                    }

                    @Override
                    public Key next() {
                        while(hasNext() && values[currentIndex] == null) {
                            currentIndex++;
                            }
                        if (!hasNext()) return null;
                        validKeys++;
                        return keys[currentIndex++];
                    }
                };
            }
        };
    }
    //-------------------- acessory methods ----------------------
    private void resize(int primeIndex) {
        //avoid errors
        if (primeIndex < 0 || primeIndex >= primes.length) return;

        //new hashTable, using new prime as reference to capacity;
        //Constructor(newSize) already update m value to next primeIndex
        OpenAddressingHashTable<Key, Value> aux = new OpenAddressingHashTable<>(this.primeIndex);
        //placing all existing keys on new hashTable
        for (int i = 0; i < m-1; i++) {
            //reinserting only valid keys;
            // (values[i] != null) talvez funcione;
            if (keys[i] != null && values[i] != null) aux.put(keys[i], values[i]);
        }
        this.keys = aux.keys;
        this.values = aux.values;
        this.m = aux.m;
        this.deletedCounter = 0;
        this.loadFactor = (float) size / m;
    }
    @SuppressWarnings("unchecked")
    public OpenAddressingHashTable(int newPrimeIndex) {
        this.m = primes[newPrimeIndex];
        this.keys = (Key[]) new Object[m];
        this.values = (Value[]) new Object[m];
    }

    //LinearProbing pode gerar um clustering, ou agrupamento. Isto é, criar aglomerados de keys, que resulta em baixa
    //eficiencia em outros methods, devido a uma busca linear prolongada
    private int doubleHash(Key k) {
        return primes[primes.length-1] + (k.hashCode() & 0x7fffffff) % (m - 1);
        //um methodo de evitar isso é criar um segundo hashing, idependente do primeiro, que possa fazer iterações saltando
        //entre keys, mas sem causar aglomeração uma vez que usa a mesma lógica do hash. Tende a manter distribuição mais uniforme;
    }
    public void doublehashedPut(Key k, Value v) {
        if (k == null) return;
        if (v == null) {
            delete(k);
            return;
        }
        int i = hash(k);
        int y= doubleHash(k);

            //avançar com o ponteiro i até um valor null dentro da hashTable, fazendo linearProbing
        for (int j = 0; keys[i] != null; i = (i + (y*j++)) % m){
            //check if key already exists
            if (keys[i].equals(k)) {
                //se for inserida chave ja existente mas antes estava marcada como apagada (values[i] == null), será um reciclagem;
                if (values[i] == null) {
                    deletedCounter--;
                    break;
                }
                //updating value
                values[i] = v;
                return;
            }
        }
        //once key index was found, insert it
        keys[i] = k;
        values[i] = v;
        size++;
        this.loadFactor = (float) size / m;
        if (loadFactor >= 0.5f) {
            resize(++primeIndex);
        }
    }
}

/*  ------CORNER SITUATION----
    ->quando usar put, e encontrar uma tombstone, usar esta ao invés de continuar buscando uma key null; DONE
    ->quando fizer resize, a tombstone não deve inserida novamente, então deve-se diminuir size e deletedCounter; DONE
    ->se ao fazer delete de uma key o fator de carga seja < 0.125 (1/8), deve-se fazer resize para primeIndex--
      que no entanto deve ser no minimo [37] (MIN_PRIMEINDEX = 1);DONE
    ->ao fazer delete de uma key, caso deletedCounter/m >= 0.2, as tombstones devem ser efetivamente deletadas; DONE


    for (int j = 0; keys[i] != null; i = (i + y*j++) % m) {

    classe deleted
        ponteiro para as tombstones;
        method que apaga todas as tombstones;

---------------TESTER-------------------
criar clusters gigantes com o put(LINEARPROBING) e em seguida realizar get(LINEARPROBING) dos keys inseridos;
salvar o tempo levado para o último;

usar array put(DOUBLEHASHING) e em seguida realizar get(DOUBLEHASHING) dos keys inseridos;
salvar o tempo levado para o último;


LINEARPROBING PUT:





LINEARP






















 */


