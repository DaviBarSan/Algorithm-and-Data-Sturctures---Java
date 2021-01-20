package aed.matrix;

import java.util.Iterator;

import aed.tables.OpenAddressingHashTable;

@SuppressWarnings("unchecked")
public class Sparse2DMatrix {

    public OpenAddressingHashTable<Integer, Float> dataHashTable;
    int size;
    int numberOfColumns;
    int numberOfLines;


    public Sparse2DMatrix(int lines, int columns) {
        this.numberOfLines = lines;
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
            if (valueInMatrix == null) return;
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
        }
        ;
        return (float) value;
    }

    public Sparse2DMatrix scalar(float scalar) {
        Iterable tableIterator = dataHashTable.keys();
        Sparse2DMatrix scalarMatrix = new Sparse2DMatrix(numberOfLines, numberOfColumns);
        Boolean isDeletion = (scalar == 0f);

        tableIterator.forEach(key -> {
            if (isDeletion) {
                scalarMatrix.dataHashTable.put((Integer) key, null);
            } else {
                scalarMatrix.dataHashTable.put((Integer) key, dataHashTable.get((Integer) key) * scalar);
            }
        });
        return scalarMatrix;
    }
    public Sparse2DMatrix sum(Sparse2DMatrix matrixB) {
        if (matrixB.numberOfColumns != this.numberOfColumns || matrixB.numberOfLines != this.numberOfLines) {
            throw new IllegalArgumentException();
        }
        Sparse2DMatrix sumMatrix = new Sparse2DMatrix(numberOfLines, numberOfColumns);
        sumMatrix.dataHashTable = dataHashTable;

        Iterable bKeys = matrixB.dataHashTable.keys();
        bKeys.forEach(bKey -> {
            Object valueInA = dataHashTable.get((Integer) bKey);
            Object valueInB = matrixB.dataHashTable.get((Integer) bKey);
            if (valueInA == null && valueInB != null) { // valid value only in b
                sumMatrix.dataHashTable.put((Integer) bKey, (float) valueInB);
            } else if ((float) valueInA + (float) valueInB == 0.0f) {
                sumMatrix.dataHashTable.put((Integer)bKey, null);
            } else { // exists in A, sums values
                sumMatrix.dataHashTable.put((Integer) bKey, ((float) valueInA) + (float) valueInB);
            }
        });
        return sumMatrix;
    }

    public Sparse2DMatrix multiply(Sparse2DMatrix that) {
        // check if the matrix sizes matches;
        // use a method which reconverts ordinal indexes to matrix indexes
        // multiply for each in line A by each column B;

        return this;
    }

    public float[] getNonZeroElements() {
        float[] validValues = new float[dataHashTable.size()];
        Iterator keysIterator = dataHashTable.keys().iterator();
        for (int i = 0; i < dataHashTable.size(); i++) {
            int validKey = (int) keysIterator.next();
            validValues[i] = dataHashTable.get(validKey);
        }
        return validValues;
    }

    public float[][] getNonSparseMatrix() {
        float[][] nonSparseMatrix = new float[numberOfLines][numberOfColumns];
        // access each index in each
        int totalSlots = numberOfColumns * numberOfLines;
        /*
        for (int i = 0; i < totalSlots; i++){
            int currKey = i;
            Float currValue = dataHashTable.get(currKey);

            int m = linesMatricial(i, this);
            int n = columnsMatricial(i, this);

            if (currValue == null) {
                nonSparseMatrix[m][n] = 0.0f;

            }
            nonSparseMatrix[m][n] = currKey;
        }

         */


        return nonSparseMatrix;
    }

    // --------------------------------- accessory methods ---------------------------
    private int linesMatricial(int ordinal, Sparse2DMatrix matrix) {
        int maxColumns = matrix.numberOfColumns;
        int line = ordinal / maxColumns;
        return line;
    }

    private int columnsMatricial(int ordinal, Sparse2DMatrix matrix) {
        int maxColumns = matrix.numberOfColumns;
        int column = ordinal % maxColumns;
        return column;
    }


}
