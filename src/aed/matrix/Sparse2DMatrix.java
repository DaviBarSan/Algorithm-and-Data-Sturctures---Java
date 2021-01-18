package aed.matrix;

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
        return size;
    }

    public void put(int line, int column, float value) {
        int matrixIndex = hashingFuction(line, column, numberOfColumns);
        if (value == 0) {
            dataHashTable.put(matrixIndex, null);
            return;
        }

        dataHashTable.put(matrixIndex, value);
    }

    public float get(int line, int column) {
        int hash = hashingFuction(line, column, numberOfColumns);
        float value = (float) dataHashTable.get(hash);
        return value;
    }

    public Sparse2DMatrix scalar(float scalar) {
        Iterable tableIterator = dataHashTable.keys();
        for (float v : dataHashTable.values) {
            dataHashTable.put();
        }
        return this;
    }

    public Sparse2DMatrix multiply(Sparse2DMatrix that) {
        return this;
    }

    public int getNonZeroElements() {
       return 3;
    }

    public float[][] getNonSparseMatrix() {
        float[][] sahud = new float[5][5];
        return sahud;
    }

}
