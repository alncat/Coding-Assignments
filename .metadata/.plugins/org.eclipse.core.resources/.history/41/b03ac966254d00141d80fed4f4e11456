import java.util.*;

class ColumnMajorDoubleMatrix extends ADoubleMatrix {

	private IDoubleVector vector, placeVals, vectorRowSum;
	private ArrayList<IDoubleVector> rowHolder;

	public ColumnMajorDoubleMatrix(int rows, int cols, double initialVal) {// sets up the parameters
																			// of the matrix
		// this is the column
		vector = new DenseDoubleVector(rows, initialVal);
		rowHolder = new ArrayList<IDoubleVector>();
		// loop until all the columns are added
		for (int i = 0; i < cols; i++) {
			rowHolder.add(i, new DenseDoubleVector(rows, initialVal));
		}
	}


	/* (non-Javadoc)
	 * @see ADoubleMatrix#getRow(int)
	 */
	public IDoubleVector getRow(int i) throws OutOfBoundsException {

		if (i >= vector.getLength() || i < 0) { // tests the parameter
			throw new OutOfBoundsException("Input exceeds max index.");
		}
		// number of columns in the matrix
		int colsHere = rowHolder.size();

		// setup a vector of the same length for the return row
		IDoubleVector getRowHere = new DenseDoubleVector(colsHere, 0.0);
		// loop through each column at the ith entry
		for (int jthCol = 0; jthCol < colsHere; jthCol++) {
			// get the jth column
			IDoubleVector jthColVector = rowHolder.get(jthCol);
			// get the ijth element from the row/column
			double ijthElement = jthColVector.getItem(i);
			getRowHere.setItem(jthCol, ijthElement);
		}

		return getRowHere;

	}

	/* (non-Javadoc)
	 * @see ADoubleMatrix#getColumn(int)
	 */
	public IDoubleVector getColumn(int j) throws OutOfBoundsException {
		if (j >= rowHolder.size() || j < 0) {// tests the parameter
			throw new OutOfBoundsException("Input exceeds max index.");
		}
		return rowHolder.get(j);
	}

	/* (non-Javadoc)
	 * @see ADoubleMatrix#setRow(int, IDoubleVector)
	 */
	public void setRow(int i, IDoubleVector setToMe) throws OutOfBoundsException {
		if (i >= vector.getLength() || i < 0 || setToMe.getLength() != rowHolder.size()) {// tests
																							// the
																							// parameter
																							// //||
																							// setToMe.getLength()
																							// !=
																							// element.getLength()
			throw new OutOfBoundsException("Input exceeds max index.");

		}
		// number of columns in the matrix
		int colsHere = rowHolder.size();
		// loop through each column at the ith entry
		for (int jthCol = 0; jthCol < colsHere; jthCol++) {
			// get the jth column
			IDoubleVector jthColVector = rowHolder.get(jthCol);
			double newEntry = setToMe.getItem(jthCol);
			jthColVector.setItem(i, newEntry);
			rowHolder.set(jthCol, jthColVector);
		}
		return;
	}

	/* (non-Javadoc)
	 * @see ADoubleMatrix#setColumn(int, IDoubleVector)
	 */
	public void setColumn(int j, IDoubleVector setToMe) throws OutOfBoundsException {
		if (j >= rowHolder.size() || j < 0 || setToMe.getLength() != vector.getLength()) {// tests
																							// the
																							// parameter
			throw new OutOfBoundsException("Input exceeds max index.");
		}
		rowHolder.set(j, setToMe);
		return;
	}

	/* (non-Javadoc)
	 * @see ADoubleMatrix#getEntry(int, int)
	 */
	public double getEntry(int i, int j) throws OutOfBoundsException {
		if (i >= vector.getLength() || i < 0) {// tests the parameter
			throw new OutOfBoundsException("Input exceeds max index.");
		}

		if (j >= rowHolder.size() || j < 0) {// tests the parameter
			throw new OutOfBoundsException("Input exceeds max index.");
		}
		IDoubleVector jthColumn = rowHolder.get(j);

		return jthColumn.getItem(i);
	}

	/* (non-Javadoc)
	 * @see ADoubleMatrix#setEntry(int, int, double)
	 */
	public void setEntry(int i, int j, double setToMe) throws OutOfBoundsException {
		if (j >= rowHolder.size() || j < 0) {// tests the parameter
			throw new OutOfBoundsException("Input exceeds max index.");
		}

		if (i >= vector.getLength() || i < 0) {// tests the parameter
			throw new OutOfBoundsException("Input exceeds max index.");
		}
		IDoubleVector jthColumn = rowHolder.get(j);
		jthColumn.setItem(i, setToMe);
		return;
	}

	/* (non-Javadoc)
	 * @see ADoubleMatrix#addMyselfToHim(IDoubleMatrix)
	 */
	public void addMyselfToHim(IDoubleMatrix toMe) throws OutOfBoundsException {
		if (toMe.getNumRows() != vector.getLength() || toMe.getNumRows() < 0) {// tests the
																				// parameter
			throw new OutOfBoundsException("Input exceeds max index.");
		}

		if (toMe.getNumColumns() != rowHolder.size() || toMe.getNumColumns() < 0) {// tests the
																					// parmeter
			throw new OutOfBoundsException("Input exceeds max index.");
		}
		
		int numCols = toMe.getNumColumns();
		for (int i = 0; i < numCols; i++) {
			IDoubleVector one = toMe.getColumn(i);
			IDoubleVector two = rowHolder.get(i);
			two.addMyselfToHim(one);
			toMe.setColumn(i, one);
		}

		return;
	}

	/* (non-Javadoc)
	 * @see ADoubleMatrix#sumRows()
	 */
	public IDoubleVector sumRows() {
		double singleRowSum = 0.0;
		placeVals = new DenseDoubleVector(vector.getLength(), 0.0);
		vectorRowSum = new DenseDoubleVector(rowHolder.size(), 0.0);
		placeVals = rowHolder.get(0);

		for (int i = 0; i < rowHolder.size(); i++) {// for ith row
			placeVals = rowHolder.get(i);
			for (int j = 0; j < placeVals.getLength(); j++) {// for jth column
				try {
					singleRowSum = vectorRowSum.getItem(i);
					vectorRowSum.setItem(i, singleRowSum + placeVals.getItem(j));
				} catch (OutOfBoundsException E) {
				}
			}
		}
		return vectorRowSum;
	}

	/* (non-Javadoc)
	 * @see ADoubleMatrix#sumColumns()
	 */
	public IDoubleVector sumColumns() {

		IDoubleVector sumCounter = new DenseDoubleVector(vector.getLength(), 0.0);
		for (int i = 0; i < rowHolder.size(); i++) {
			IDoubleVector thisRow = rowHolder.get(i);
			try {
				thisRow.addMyselfToHim(sumCounter);
			} catch (OutOfBoundsException E) {
			}
		}
		return sumCounter;
	}



	/* (non-Javadoc)
	 * @see ADoubleMatrix#getNumRows()
	 */
	public int getNumRows() {
		return vector.getLength();
	}

	/* (non-Javadoc)
	 * @see ADoubleMatrix#getNumColumns()
	 */
	public int getNumColumns() {
		return rowHolder.size();
	}
}
