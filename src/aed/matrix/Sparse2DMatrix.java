package aed.matrix;

import java.util.Iterator;

import aed.tables.OpenAddressingHashTable;

@SuppressWarnings("unchecked")
public class Sparse2DMatrix {

    float size;
    float numberOfColumns;
    float numberOfRows;
    public OpenAddressingHashTable<Float, Float> dataHashTable;

    public Sparse2DMatrix(float lines, float columns) {
        this.numberOfRows = lines;
        this.numberOfColumns = columns;
        this.dataHashTable = new OpenAddressingHashTable<Float, Float>();
        this.size = dataHashTable.size();
    }

    // ----------------------------------------accessory methods;
    private float hashingFuction(float line, float column, float numberOfColumns) {
        float i = line * numberOfColumns + column;
        return i;
    }

    public float getNumberNonZero() {
        return dataHashTable.size();
    }

    public void put(float line, float column, float new_value) {
        float matrixIndex = hashingFuction(line, column, numberOfColumns);
        Object valueInMatrix = dataHashTable.get(matrixIndex);
        if (new_value == 0) {
            if (valueInMatrix == null)
                return;
            dataHashTable.put(matrixIndex, null);
            this.size = dataHashTable.size();
            return;
        }
        dataHashTable.put(matrixIndex, new_value);
        this.size = dataHashTable.size();
    }

    public float get(float line, float column) {
        float hash = hashingFuction(line, column, numberOfColumns);
        Object value = dataHashTable.get(hash);
        if (value == null) {
            return 0.0f;
        }
        ;
        return (float) value;
    }

    public Sparse2DMatrix scalar(float scalar) {
        Iterable tableIterator = dataHashTable.keys();
        tableIterator.forEach(key -> {
            if (scalar == 0f) {
                dataHashTable.put((Float) key, null);
            } else {
                dataHashTable.put((Float) key, dataHashTable.get((Float) key) * scalar);
            }
        });
        return this;
    }

    public Sparse2DMatrix sum(Sparse2DMatrix matrixB) {

        if (matrixB.numberOfColumns != this.numberOfColumns || matrixB.numberOfRows != this.numberOfRows) {
            throw new IllegalArgumentException(); // Duvida do formato;
        }

        Iterable bKeys = matrixB.dataHashTable.keys();
        bKeys.forEach(bKey -> {
            Object valueInA = dataHashTable.get((Float) bKey);
            Object valueInB = matrixB.dataHashTable.get((Float) bKey);
            if (valueInA == null) { // valid value only in b
                dataHashTable.put((Float) bKey, (float) valueInB);
            } else { // exists in A, sums values
                dataHashTable.put((Float) bKey, ((float) valueInA) + (float) valueInB);
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
        float[] validValues = new float[dataHashTable.size()];
        Iterator keysIterator = dataHashTable.keys().iterator();
        for (int i = 0; i < dataHashTable.size(); i++) {
            float validKey = (float) keysIterator.next();
            validValues[i] = dataHashTable.get(validKey);
        }
        return validValues;
    }

    public float[][] getNonSparseMatrix() {
        float[][] sahud = new float[5][5];
        // access each index in each
        return sahud;
    }

    // --------------------------------- accessory methods
    // ---------------------------
    private float[] fromOrdinalToMatricial(float ordinal, Sparse2DMatrix matrix) {
        float[] matricial = new float[2];

        float maxColumns = matrix.numberOfColumns;
        float line = ordinal / maxColumns;
        float column = ordinal % maxColumns;
        matricial[0] = line;
        matricial[1] = column;
        return matricial;
    }

}
