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
        if (new_value == 0 && valueInMatrix != null) {
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
        return (Float) value;
    }

    public Sparse2DMatrix scalar(float scalar) {
        Iterable tableIterator = dataHashTable.keys();

        tableIterator.forEach( key ->
                dataHashTable.put((Integer) key,dataHashTable.get((Integer) key)*scalar)
        );
        return this;
    }

    public Sparse2DMatrix sum (Sparse2DMatrix matrixB) {
        if (matrixB.numberOfColumns != numberOfColumns || matrixB.numberOfRows != numberOfRows) {
            throw new IllegalArgumentException("Matrix are not of the same size");
        }

        Iterable bKeys = matrixB.dataHashTable.keys();

        bKeys.forEach( bKey -> {
            Object valueInA = dataHashTable.get((Integer) bKey);
            Object valueInB = matrixB.dataHashTable.get((Integer) bKey);
            if (valueInA == null) { // only in b
                dataHashTable.put((Integer) bKey, (Float) valueInB);
            } else { // exists in A, sums values
                dataHashTable.put((Integer) bKey, (Float) valueInB + (Float) valueInA);
            }
        });
        return this;
    }

    public Sparse2DMatrix multiply(Sparse2DMatrix that) {
        return this;
    }

    public float[] getNonZeroElements() {
        float [] validValues = new float[size];
        Iterator tableIterator = dataHashTable.keys().iterator();

        for (int i = 0; i < dataHashTable.size(); i++){
            validValues[i] = (float) tableIterator.next();
        }
       return validValues;
    }

    public float[][] getNonSparseMatrix() {
        float[][] sahud = new float[5][5];
        return sahud;
    }

}
