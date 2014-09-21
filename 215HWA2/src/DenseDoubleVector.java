
class DenseDoubleVector extends ADoubleVector {

  private int vectorLength;
  private double offset;
  private double[] buildArray;

  public DenseDoubleVector(int firstArg, double secondArg) {
    vectorLength = firstArg;
    offset = secondArg;
    buildArray = new double[firstArg];
  }

  /*
   * (non-Javadoc)
   * 
   * @see ADoubleVector#getItem(int)
   */
  public double getItem(int theIndex) throws OutOfBoundsException {
    // gets item at index
    return offset + buildArray[theIndex];
  }

  /*
   * (non-Javadoc)
   * 
   * @see ADoubleVector#addMyselfToHim(IDoubleVector)
   */
  public void addMyselfToHim(IDoubleVector addToHim) throws OutOfBoundsException {
    // adds all the indices to the IDoubleVector parameter
    for (int i = 0; i < buildArray.length; i++) {
      buildArray[i] += buildArray[i] + addToHim.getItem(i);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see ADoubleVector#addToAll(double)
   */
  public void addToAll(double addMe) {
    // adds a value to all the values in the list
    offset = offset + addMe;
  }

  /*
   * (non-Javadoc)
   * 
   * @see ADoubleVector#getRoundedItem(int)
   */
  public long getRoundedItem(int whichOne) {
    // returns value rounded up to an integer
    double value = buildArray[whichOne] + offset;
    long rounded = Math.round(value);
    return rounded;
  }

  /*
   * (non-Javadoc)
   * 
   * @see ADoubleVector#normalize()
   */
  public void normalize() {
    // divides all the elements by the length of the list
    double i = l1Norm();
    for (int j = 0; j < buildArray.length; j++) {
      buildArray[j] = (buildArray[j]) / i;
    }
    offset /= i;
  }

  /*
   * (non-Javadoc)
   * 
   * @see ADoubleVector#getLength()
   */
  public int getLength() {
    // gets the length of the list
    return buildArray.length;
  }

  /*
   * (non-Javadoc)
   * 
   * @see ADoubleVector#l1Norm()
   */
  public double l1Norm() {
    // the sum of all entries
    double sum = 0;
    for (int i = 0; i < buildArray.length; i++) {
      sum = sum + buildArray[i] + offset;
    }
    return sum;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  public String toString() {
    // vector into a string
    String str = " ";
    for (int i = 0; i < vectorLength; i++) {
      try {
        str += getItem(i) + " ";
      } catch (OutOfBoundsException e) {
        e.printStackTrace();
      }
    }
    return str;
  }

  /*
   * (non-Javadoc)
   * 
   * @see ADoubleVector#setItem(int, double)
   */
  public void setItem(int whichOne, double setToMe) throws OutOfBoundsException {
    // set the specific item
    double setResult = setToMe - offset;
    buildArray[whichOne] = setResult;
  }
}
