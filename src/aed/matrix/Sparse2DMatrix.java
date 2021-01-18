package aed.matrix;
import aed.tables.OpenAddressingHashTable;

@SuppressWarnings("unchecked")
public class Sparse2DMatrix {


    int size;
    Sparse2DMatrix[][] newMatrix;
    Sparse2DMatrix matrixData;
    int numberOfColumns;
    int numberOfRows;

    //put valid indexes in an nested array to easier access by hashing
    public OpenAddressingHashTable hashTableMatrix;

    public Sparse2DMatrix(){
        this.matrixData = new Sparse2DMatrix();
        this.hashTableMatrix = new OpenAddressingHashTable();
        this.size = hashTableMatrix.size();
    }



    public Sparse2DMatrix(int lines, int columns)
    {
        this.newMatrix = new Sparse2DMatrix[lines][columns];
        this.numberOfRows = lines;
        this.numberOfColumns = columns;
        new Sparse2DMatrix();
    }

    public int getNumberNonZero() {
        return size;
    }

    public void put(int line, int column, float value)
    {
        int matrixIndex = hashingIndex(line,column, numberOfColumns);
        if (value == 0){
            hashTableMatrix.put(matrixIndex,null);
            return;
        }
        hashTableMatrix.put(matrixIndex,value);


    }

    public float get(int line, int column)
    {
        int i = hashingIndex(line, column, numberOfColumns);
        float value = (float) hashTableMatrix.get(i);
        return value;
    }

    public Sparse2DMatrix scalar(float scalar)
    {
        Iterable nextIterator = hashTableMatrix.keys();
        for( Object v : hashTableMatrix.values){
            float result = (float) v*scalar;
            v = result;

        }
        Sparse2DMatrix multipliedMatrix = new Sparse2DMatrix();
        multipliedMatrix.matrixData = this.matrixData;

        return multipliedMatrix;

    }

    public Sparse2DMatrix sum(Sparse2DMatrix that)
    {

        Sparse2DMatrix sumMatrixResult = new Sparse2DMatrix();
            Iterable matrixAIterable = matrixData.hashTableMatrix.keys();
            Iterable matrixThatIterable = that.hashTableMatrix.keys();
        for (int i = 0; i < size; i++){
            int keyA = (int) matrixAIterable.iterator().next();
            float valueA = (float) this.matrixData.hashTableMatrix.get(keyA);
            int keyThat = (int) matrixThatIterable.iterator().next();
            float valueThat = (float) that.hashTableMatrix.get(keyThat);
            float resultValue = valueA + valueThat;
            sumMatrixResult.hashTableMatrix.put(keyA, resultValue);
    }

        return sumMatrixResult;
    }

    public Sparse2DMatrix multiply(Sparse2DMatrix that)
    {
		return matrixData;
    }
	
	public float[] getNonZeroElements()
    {
        float[] nonZeroArray = new float[size];
        Iterable validKeysIterator = matrixData.hashTableMatrix.keys();

        for (int i = 0;  i < size; i++ ){
            int currentKey = (int) validKeysIterator.iterator().next();

        }
        return nonZeroArray;
    }


    public float[][] getNonSparseMatrix()
    {
        float[][] sahud = new float[5][5];
        return sahud;
    }
//  ----------------------------------------accessory methods;
    public int hashingIndex(int line, int column, int numberOfColumns){
        int i = line * numberOfColumns + column;
        return i;
    }
}
/*

catch rows and columns reference from a ordinal indexes
indexNumber = n
columnsPosition = n % totalColumns;
rowsPosition = n / totalColumns;

matrixPosition = (rowsPosition, columnsPosition);
 */