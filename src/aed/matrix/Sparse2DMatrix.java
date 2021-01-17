package aed.matrix;
import aed.tables.OpenAddressingHashTable;

public class Sparse2DMatrix {


    int size;
    Sparse2DMatrix[][] newMatrix;
    int numberOfColumns;
    int numberOfRows;
    //put valid indexes in an nested array to easier access by hashing
    int[] validIndexes;



    public Sparse2DMatrix(int lines, int columns)
    {

        this.newMatrix = new Sparse2DMatrix[lines][columns];
        this.size = 0;
        this.numberOfColumns = newMatrix.length;
        this.numberOfRows = newMatrix[0].length;

    }

    public int getNumberNonZero() {
        return size;
    }

    public void put(int line, int column, float value)
    {
        hashingIndex(line,column, newMatrix.length);
    }

    public float get(int line, int column)
    {
        //TODO: implement
    }

    public Sparse2DMatrix scalar(float scalar)
    {
        //TODO: implement
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
        int[] nonZeroArray = new int[size];



    }

    public float[][] getNonSparseMatrix()
    {
		//TODO: implement
    }
//  ----------------------------------------acessory methods;
    public int hashingIndex(int line, int column, int columnMax){
        int i = line * columnMax + column;
    }
}
