package aed.matrix;

import java.util.Iterator;

import aed.tables.OpenAddressingHashTable;

@SuppressWarnings("unchecked")
public class Sparse2DMatrix {

    int size;
    int numberOfColumns;
    int numberOfRows;
    public OpenAddressingHashTable<Integer, Float> dataHashTable;


    public Sparse2DMatrix(int lines, int columns) {
        this.numberOfRows = lines;
        this.numberOfColumns = columns;
        this.dataHashTable = new OpenAddressingHashTable<Integer, Float>();
        this.size = dataHashTable.size();
    }

    // ----------------------------------------accessory methods;
    private int hashingFuction(int line, int column, int numberOfColumns) {
        int i = line * numberOfColumns + column;
        return i;
    }

    public int getNumberNonZero() {
        return dataHashTable.size();
    }

    public void put(int line, int column, float new_value) {
        int matrixIndex = hashingFuction(line, column, numberOfColumns);
        Object valueInMatrix = dataHashTable.get(matrixIndex);
        if (new_value == 0) {
            if(valueInMatrix == null) return;
            dataHashTable.put(matrixIndex, null);
            this.size = dataHashTable.size();
            return;
        }
        dataHashTable.put(matrixIndex, new_value);
        this.size = dataHashTable.size();
    }

    public float get(int line, int column) {
        int hash = hashingFuction(line, column, numberOfColumns);
        Object value = dataHashTable.get(hash);
        if (value == null) {
            return 0.0f;
        };
        return (float) value;
    }

    public Sparse2DMatrix scalar(float scalar) {
        Iterable tableIterator = dataHashTable.keys();
        tableIterator.forEach( key ->{
            if (scalar == 0f){
                dataHashTable.put((Integer) key,null);
            }else {
                dataHashTable.put((Integer) key, dataHashTable.get((Integer) key) * scalar);
            }
        });
        return this;
    }

    public Sparse2DMatrix sum (Sparse2DMatrix matrixB) {


        if (matrixB.numberOfColumns != this.numberOfColumns || matrixB.numberOfRows != this.numberOfRows) {
            throw new IllegalArgumentException(); // Duvida do formato;
        }

        Iterable bKeys = matrixB.dataHashTable.keys();
        bKeys.forEach( bKey -> {
            Object valueInA = dataHashTable.get((Integer) bKey);
            Object valueInB = matrixB.dataHashTable.get((Integer) bKey);
            if (valueInA == null) { // valid value only in b
                dataHashTable.put((Integer) bKey, (float) valueInB);
            } else { // exists in A, sums values
                dataHashTable.put((Integer) bKey,((float) valueInA) + (float) valueInB);
            }
        });
        return this;
    }

    public Sparse2DMatrix multiply(Sparse2DMatrix that) {
        // check if the matrix sizes matches;
        // use a method which reconverts ordinal indexes to matrix indexes
        // multiply for each in line A by each column B;

        return this;
    }

    public float[] getNonZeroElements() {
        float [] validValues = new float[dataHashTable.size()];
        Iterator keysIterator = dataHashTable.keys().iterator();
        for (int i = 0; i < dataHashTable.size(); i++) {
            int validKey = (int) keysIterator.next();
            validValues[i] = dataHashTable.get(validKey);
        }
       return validValues;
    }

    public float[][] getNonSparseMatrix() {
        float[][] sahud = new float[5][5];
        // access each index in each
        return sahud;
    }
    // --------------------------------- accessory methods ---------------------------
    private int[] fromOrdinalToMatricial(int ordinal, Sparse2DMatrix matrix) {
        int[] matricial = new int[2];

        int maxColumns = matrix.numberOfColumns;
        int line = ordinal / maxColumns;
        int column = ordinal % maxColumns;
        matricial[0] = line;
        matricial[1] = column;
        return matricial;
    }



}
