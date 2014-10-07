import java.util.*;

/*
 * RowMajorDoubleMatrix is implemented as an ISparseArray of rows, where each row is itself an
 * IDoubleVector object.
 */

class RowMajorDoubleMatrix extends ADoubleMatrix {

	private IDoubleVector vector, allSumCol, holdCol, holdRow;
	private ArrayList<IDoubleVector> columnHolder;


	public RowMajorDoubleMatrix(int rows, int cols, double initialVal) {// build the matrix with the correct parameters
		vector = new DenseDoubleVector(cols, initialVal);
		columnHolder = new ArrayList<IDoubleVector>();

		for (int i = 0; i < rows; i++) {
			columnHolder.add(i, new DenseDoubleVector(cols, initialVal));
		}
	}



	/* (non-Javadoc)
	 * @see ADoubleMatrix#getRow(int)
	 */
	public IDoubleVector getRow(int i) throws OutOfBoundsException {
		if (i >= columnHolder.size() || i < 0) {// test the bounds for the input
			throw new OutOfBoundsException("Input exceeds max index.");
		}
		return columnHolder.get(i);
	}

	
	/* (non-Javadoc)
	 * @see ADoubleMatrix#getColumn(int)
	 */
	public IDoubleVector getColumn(int j) throws OutOfBoundsException {
		if (j >= vector.getLength() || j < 0) {// test the bounds of the parameter
			throw new OutOfBoundsException("Input exceeds max index.");
		}
		int numRows = columnHolder.size();
		IDoubleVector vecColumn = new DenseDoubleVector(numRows, 0.0);
		for (int rowI = 0; rowI < numRows; rowI++) {
			IDoubleVector rowIth = columnHolder.get(rowI);
			double entryIJ = rowIth.getItem(j);
			vecColumn.setItem(rowI, entryIJ);
		}
		return vecColumn;
	}

	
	/* (non-Javadoc)
	 * @see ADoubleMatrix#setRow(int, IDoubleVector)
	 */
	public void setRow(int i, IDoubleVector setToMe) throws OutOfBoundsException {
		if (i >= columnHolder.size() || i < 0 || setToMe.getLength() != vector.getLength()) {// test the bounds of the parameter|| setToMe.getLength() != columnHolder.size()
			throw new OutOfBoundsException("Input exceeds max index.");
		}
		columnHolder.set(i, setToMe);
		return;
	}

	
	/* (non-Javadoc)
	 * @see ADoubleMatrix#setColumn(int, IDoubleVector)
	 */
	public void setColumn(int j, IDoubleVector setToMe) throws OutOfBoundsException {
		if (j >= vector.getLength() || j < 0 || setToMe.getLength() != columnHolder.size()) {// test the bounds of the parameters
			throw new OutOfBoundsException("Input exceeds max index.");
		}
		int numRows = columnHolder.size();
		int numCols = vector.getLength();
		for (int rowI = 0; rowI < numRows; rowI++) {
			IDoubleVector vecRow = columnHolder.get(rowI);
			double newVal = setToMe.getItem(rowI);
			vecRow.setItem(j, newVal);
			columnHolder.set(rowI, vecRow);
		}

		return;
	}

	/* (non-Javadoc)
	 * @see ADoubleMatrix#getEntry(int, int)
	 */
	public double getEntry(int i, int j) throws OutOfBoundsException {
		if (i >= columnHolder.size() || i < 0) {// test the bounds of the matrix
			throw new OutOfBoundsException("Input exceeds max index.");
		}
		if (j >= vector.getLength() || j < 0) {// tests the bounds of the matrix
			throw new OutOfBoundsException("Input exceeds max index.");
		}
		IDoubleVector entryVector = columnHolder.get(i);
		return entryVector.getItem(j);
	}

	/* (non-Javadoc)
	 * @see ADoubleMatrix#setEntry(int, int, double)
	 */
	public void setEntry(int i, int j, double setToMe) throws OutOfBoundsException {
		if (i >= columnHolder.size() || i < 0) {// tests the bounds of the parameter
			throw new OutOfBoundsException("Input exceeds max index.");
		}
		if (j >= vector.getLength() || j < 0) {// tests the bounds of the parameter
			throw new OutOfBoundsException("Input exceeds max index.");
		}
		// get the ith row
		IDoubleVector rowIth = columnHolder.get(i);
		// set the ij entry
		rowIth.setItem(j, setToMe);
		// place it back
		columnHolder.set(i, rowIth);
		return;
	}

	/* (non-Javadoc)
	 * @see ADoubleMatrix#addMyselfToHim(IDoubleMatrix)
	 */
	public void addMyselfToHim(IDoubleMatrix toMe) throws OutOfBoundsException {
		if (toMe.getNumColumns() != vector.getLength() || toMe.getNumColumns() < 0) {// tests the bounds of the parameter
			throw new OutOfBoundsException("Input exceeds max index.");
		}
		if (toMe.getNumRows() != columnHolder.size() || toMe.getNumRows() < 0) {// tests the bounds of the parameter
			throw new OutOfBoundsException("Input exceeds max index.");
		}
		for (int i = 0; i < toMe.getNumRows(); i++) {// for ith vector
			holdRow = new DenseDoubleVector(vector.getLength(), 0);// vector non -input matrix
			holdCol = new DenseDoubleVector(vector.getLength(), 0);// vector in toMe
			holdRow = columnHolder.get(i);
			holdCol = toMe.getRow(i);
			holdRow.addMyselfToHim(holdCol);
			toMe.setRow(i, holdCol);
		}
		return;
	}

	/* (non-Javadoc)
	 * @see ADoubleMatrix#sumRows()
	 */
	public IDoubleVector sumRows() {
		IDoubleVector sumCounter = new DenseDoubleVector(vector.getLength(), 0.0);
		for (int i = 0; i < columnHolder.size(); i++) {
			IDoubleVector thisRow = columnHolder.get(i);
			try {
				thisRow.addMyselfToHim(sumCounter);
			} catch (OutOfBoundsException E) {
			}
		}
		return sumCounter;
	}

	/* (non-Javadoc)
	 * @see ADoubleMatrix#sumColumns()
	 */
	public IDoubleVector sumColumns() {
		int lenCol = vector.getLength();
		holdCol = new DenseDoubleVector(lenCol, 0.0);
		allSumCol = new DenseDoubleVector(lenCol, 0.0);
		holdRow = new DenseDoubleVector(lenCol, 0.0);
		double singleColSum = 0.0;
		// loop the the rows
		for (int i = 0; i < columnHolder.size(); i++) {// for ith vector
			holdCol = columnHolder.get(i);
			// loop the cols of ith row
			for (int j = 0; j < holdCol.getLength(); j++) {// for jth element in vector
				try {
					singleColSum = allSumCol.getItem(i);
					allSumCol.setItem(i, holdCol.getItem(j) + singleColSum);// add the result into
																			// the vector
				} catch (OutOfBoundsException E) {
				}

			}
		}
		return allSumCol;
	}

	/* (non-Javadoc)
	 * @see ADoubleMatrix#getNumRows()
	 */
	public int getNumRows() {
		return columnHolder.size();
	}

	/* (non-Javadoc)
	 * @see ADoubleMatrix#getNumColumns()
	 */
	public int getNumColumns() {
		return vector.getLength();
	}
}
