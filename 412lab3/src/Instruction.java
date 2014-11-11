public class Instruction {
	private String[] Opcode = {"Empty"};
	// [0] = virtual register, [1] = physical register, [2] = next used, [3] = source register
	private String[] Op1 = {"Empty", "Empty", "Empty", "Empty"};
	// [0] = virtual register, [1] = physical register, [2] = next used, [3] = source register
	private String[] Op2 = {"Empty", "Empty", "Empty", "Empty"};
	// [0] = virtual register, [1] = physical register, [2] = next used, [3] = source register
	private String[] Op3 = {"Empty", "Empty", "Empty", "Empty"};
	
	private int lineNumber;
	
	public Instruction(int lineNumberInput) {
		lineNumber = lineNumberInput;
	}
	
	public int getLineNumber(){
		return lineNumber;
	}

	public void setTheOpcode(String value) {
		Opcode[0] = value;
	}

	public String getTheOpcode() {
		return Opcode[0];
	}

	/**
	 * The four methods below perform the set operation for operation 1.
	 * 
	 * @param OperationCode
	 */

	public void setVROp1(String value) {
		Op1[0] = value;
	}

	public void setPROp1(String value) {
		Op1[1] = value;
	}

	public void setNUOp1(String value) {
		Op1[2] = value;
	}

	public void setSROp1(String value) {
		Op1[3] = value;
	}

	/**
	 * The four methods below perform the set operations for operation 2.
	 * 
	 * @param value
	 */
	public void setVROp2(String value) {
		Op2[0] = value;
	}

	public void setPROp2(String value) {
		Op2[1] = value;
	}

	public void setNUOp2(String value) {
		Op2[2] = value;
	}

	public void setSROp2(String value) {
		Op2[3] = value;
	}

	/**
	 * The four methods below perform the set operations for operation 3.
	 * 
	 * @param value
	 */
	public void setVROp3(String value) {
		Op3[0] = value;
	}

	public void setPROp3(String value) {
		Op3[1] = value;
	}

	public void setNUOp3(String value) {
		Op3[2] = value;
	}

	public void setSROp3(String value) {
		Op3[3] = value;
	}

	/**
	 * The four methods below perform the get operations for operation 1.
	 * 
	 * @return
	 */
	public String getVROp1() {
		return Op1[0];
	}

	public String getPROp1() {
		return Op1[1];
	}

	public String getNUOp1() {
		return Op1[2];
	}

	public String getSROp1() {
		return Op1[3];
	}

	/**
	 * The four methods below perform the get operations for operation 2.
	 * 
	 * @return
	 */
	public String getVROp2() {
		return Op2[0];
	}

	public String getPROp2() {
		return Op2[1];
	}

	public String getNUOp2() {
		return Op2[2];
	}

	public String getSROp2() {
		return Op2[3];
	}

	/**
	 * The four methods below perform the get operations for operation 3.
	 * 
	 * @return
	 */
	public String getVROp3() {
		return Op3[0];
	}

	public String getPROp3() {
		return Op3[1];
	}

	public String getNUOp3() {
		return Op3[2];
	}

	public String getSROp3() {
		return Op3[3];
	}
}
