package aed.matrix;
import aed.tables.OpenAddressingHashTable;

public class Sparse2DMatrix {


    int size;
    Sparse2DMatrix[][] newMatrix;
    int numberOfColumns;
    int numberOfRows;
    //put valid indexes in an nested array to easier access by hashing

    OpenAddressingHashTable hashTableMatrix;




    public Sparse2DMatrix(int lines, int columns)
    {

        this.newMatrix = new Sparse2DMatrix[lines][columns];
        this.hashTableMatrix = new OpenAddressingHashTable();


        this.size = hashTableMatrix.size();
        this.numberOfColumns = newMatrix.length;
        this.numberOfRows = newMatrix[0].length;
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
        Sparse2DMatrix aux = new Sparse2DMatrix(this.numberOfColumns, this.numberOfColumns);

    }

    public Sparse2DMatrix sum(Sparse2DMatrix that)
    {
        //TODO: implement

    }

    public Sparse2DMatrix multiply(Sparse2DMatrix that)
    {
		//TODO: implement
    }
	
	public float[] getNonZeroElements()
    {
        float[] nonZeroArray = new float[size];

        hashTableMatrix.keys().forEach( (key) -> {
                float currentValue = (float) hashTableMatrix.get(key);

            }

        );
        return nonZeroArray;
    }


    public float[][] getNonSparseMatrix()
    {

    }
//  ----------------------------------------accessory methods;
    public int hashingIndex(int line, int column, int numberOfColumns){
        int i = line * numberOfColumns + column;
        return i;

    }
}
