import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.regex.Pattern;

import javax.swing.text.html.HTMLDocument.Iterator;


public class RegisterAllocator {

	// Regular expression for the lines
	private static final String REGEX = "[=>, \t]";

	// Holds the instructions of program
	private static LinkedList<Instruction> allocation = new LinkedList<Instruction>();

	// Stack that first takes in the ILOC
	private static Stack<Instruction> topToBottom = new Stack<Instruction>();

	// Source Registers mapped to Virtual Registers
	private static HashMap<String, String> SRtoVR = new HashMap<String, String>();

	// Virtual Register number that will be incremented as number of virtual registers increase
	private static int virutalRegisterNumber = 0;

	// Holds the lines in which the virtual registers are live
	private static HashMap<String, ArrayList<Integer>> virtualRegisterLineNumbers =
			new HashMap<String, ArrayList<Integer>>();

	// Holds the physical registers that are available
	private static Stack<String> availablePhysicalRegisters = new Stack<String>();

	// Holds the physical registers that are in use
	private static Stack<String> usingPhysicalRegisters = new Stack<String>();

	public static void main(String[] args) {
		//String[] inputLine = {"8", "/Users/Ace/Downloads/HolderJar/report/report1.i"};
		//String[] inputLine = {"8", "/Users/Ace/Downloads/HolderJar/report/report2.i"};
		//String[] inputLine = {"8", "/Users/Ace/Downloads/HolderJar/report/report3.i"};
		//String[] inputLine = {"8", "/Users/Ace/Downloads/HolderJar/report/report4.i"};
		//String[] inputLine = {"8", "/Users/Ace/Downloads/HolderJar/report/report5.i"};
		//String[] inputLine = {"8", "/Users/Ace/Downloads/HolderJar/report/report6.i"};
		//String[] inputLine = {"8", "/Users/Ace/Downloads/HolderJar/block1.i"};
		//String[] inputLine = {"8", "/Users/Ace/Downloads/HolderJar/block2.i"};
		//String[] inputLine = {"8", "/Users/Ace/Downloads/HolderJar/block3.i"};
		//String[] inputLine = {"8", "/Users/Ace/Downloads/HolderJar/block4.i"};
		//String[] inputLine = {"8", "/Users/Ace/Downloads/HolderJar/block5.i"};
		//String[] inputLine = {"8", "/Users/Ace/Downloads/HolderJar/block6.i"};
		//String[] inputLine = {"8", "/Users/Ace/Downloads/HolderJar/block7.i"};
		String[] inputLine = {"8", "/Users/Ace/Downloads/HolderJar/block8.i"};
		
		// inputLine = args;
		Instruction temp;
		// Check if the file exists
		File f = new File(inputLine[1]);
		if (!f.exists() || f.isDirectory()) {
			System.out.println("Failure to open '" + inputLine[1] + "' as the input file.");
			System.exit(0);
		}

		// Check to see if the parameter -h is present
		if (hFlag(inputLine)) {
			System.exit(0);
		}

		// Look for the number of registers or throw an error if not there
		if (!generateXRegisters(inputLine[0])) {
			System.out.println("Cannot allocate with fewer than 2 registers.");
			System.exit(0);
		}

		// Read each line of the file and parse it
		openAndRead(inputLine[1]);

		//
		while (!topToBottom.isEmpty()) {
			// System.out.println(topToBottom.pop().getLineNumber());
			temp = assignVirtualRegister(topToBottom.pop());
			/*
			 * System.out.println("Line Number  \t "+temp.getLineNumber());
			 * System.out.println("Virtual register:  "+ temp.getVROp1() +
			 * "\t Line Number for next use: \t "+temp.getNUOp1());
			 * System.out.println("Virtual register:  "+ temp.getVROp2() +
			 * "\t Line Number for next use: \t "+temp.getNUOp2());
			 * System.out.println("Virtual register:  "+ temp.getVROp3() +
			 * "\t Line Number for next use: \t "+temp.getNUOp3());
			 */

			// Add Instructions to allocation for physical register assignment
			allocation.add(temp);

		}
		assignPhysicalRegistersandPrint();
		System.out.println("//finished.");
	}

	public static void assignPhysicalRegistersandPrint() {
		String sr1 = "", sr2 = "", sr3 = "", vr1 = "", vr2 = "", vr3 = "";
		int programSize = allocation.size() - 1;
		Instruction instru;
		for (int i = programSize; 0 <= i; i--) {
			// System.out.println(allocation.get(i).getLineNumber());
			instru = allocation.get(i);
			sr1 = instru.getSROp1();
			sr2 = instru.getSROp2();
			sr3 = instru.getSROp3();
			vr1 = instru.getVROp1();
			vr2 = instru.getVROp2();
			vr3 = instru.getVROp3();
			if (sr1.contains("Empty")) {
				sr1 = "";
			}
			if (sr2.contains("Empty")) {
				sr2 = "";
			}
			if (sr3.contains("Empty")) {
				sr3 = "";
			}
			if (vr1.contains("Empty")) {
				vr1 = "";
			}
			if (vr2.contains("Empty")) {
				vr2 = "";
			}
			if (vr3.contains("Empty")) {
				vr3 = "";
			}
			System.out.println(instru.getTheOpcode() + "\t" + vr1 + " " + vr2 + "\t" + vr3
					+ "\t // " + "\t" + sr1 + " " + sr2 + "\t" + sr3 + "\t (The source registers)");
			System.out.println("");
		}
	}

	public static Instruction assignVirtualRegister(Instruction modInstruction) {
		String sourceRegister1 = "Empty", sourceRegister2 = "Empty", sourceRegister3 = "Empty";

		// Make sure that source register 1 is a register
		if (modInstruction.getSROp1().length() >= 2 && modInstruction.getSROp1().charAt(0) == 'r'
				&& Character.isDigit(modInstruction.getSROp1().charAt(1))) {
			sourceRegister1 = modInstruction.getSROp1();
		}

		// Make sure that source register 2 is a register
		if (modInstruction.getSROp2().length() >= 2 && modInstruction.getSROp2().charAt(0) == 'r'
				&& Character.isDigit(modInstruction.getSROp2().charAt(1))) {
			sourceRegister2 = modInstruction.getSROp2();
		}

		// Make sure that source register 3 is a register
		if (modInstruction.getSROp3().length() >= 2 && modInstruction.getSROp3().charAt(0) == 'r'
				&& Character.isDigit(modInstruction.getSROp3().charAt(1))) {
			sourceRegister3 = modInstruction.getSROp3();
		}


		// If source register 3 is not in the hash, add it
		if (!SRtoVR.containsKey(sourceRegister3) && !sourceRegister3.contains("Empty")) {

			SRtoVR.put(sourceRegister3, "VR" + Integer.toString(virutalRegisterNumber));

			// Save virtual register to the instruction
			modInstruction.setVROp3(SRtoVR.get(sourceRegister3));
			virutalRegisterNumber++;

		} else if (!sourceRegister3.contains("Empty")) {

			// Save virtual register to the instruction
			modInstruction.setVROp3(SRtoVR.get(sourceRegister3));
		}

		// If source register 2 equals source register 3, give source register 2 new a virtual
		// register
		if (!sourceRegister2.contains("Empty") && sourceRegister2.contains(sourceRegister3)) {
			SRtoVR.remove(sourceRegister2);
			SRtoVR.put(sourceRegister2, "VR" + Integer.toString(virutalRegisterNumber));

			// Save virtual register to the instruction
			modInstruction.setVROp2(SRtoVR.get(sourceRegister2));
			virutalRegisterNumber++;
		}

		// If source register 1 equals source register 3, give source register 1 new a virtual
		// register
		if (!sourceRegister1.contains("Empty") && sourceRegister1.contains(sourceRegister3)) {
			SRtoVR.remove(sourceRegister1);
			SRtoVR.put(sourceRegister1, "VR" + Integer.toString(virutalRegisterNumber));

			// Save virtual register to the instruction
			modInstruction.setVROp1(SRtoVR.get(sourceRegister1));
			virutalRegisterNumber++;
		}

		// If source register 2 is not in the hash, add it
		if (!SRtoVR.containsKey(sourceRegister2) && !sourceRegister2.contains("Empty")) {
			SRtoVR.put(sourceRegister2, "VR" + Integer.toString(virutalRegisterNumber));

			// Save virtual register to the instruction
			modInstruction.setVROp2(SRtoVR.get(sourceRegister2));
			virutalRegisterNumber++;
		} else if (!sourceRegister2.contains("Empty")) {

			// Save virtual register to the instruction
			modInstruction.setVROp2(SRtoVR.get(sourceRegister2));
		}

		// If source register 1 is not in the hash, add it
		if (!SRtoVR.containsKey(sourceRegister1) && !sourceRegister1.contains("Empty")) {
			SRtoVR.put(sourceRegister1, "VR" + Integer.toString(virutalRegisterNumber));

			// Save virtual register to the instruction
			modInstruction.setVROp1(SRtoVR.get(sourceRegister1));
			virutalRegisterNumber++;
		} else if (!sourceRegister1.contains("Empty")) {

			// Save virtual register to the instruction
			modInstruction.setVROp1(SRtoVR.get(sourceRegister1));
			// System.out.println(modInstruction.getVROp1());
			// System.out.println("//finished.9999999999");
		}



		// System.out.println("//finished." + virutalRegisterNumber);
		/*
		 * System.out.println("//SR1.\t " + modInstruction.getSROp1());
		 * System.out.println("//VR1.\t " + modInstruction.getVROp1());
		 * System.out.println("//SR2.\t " + modInstruction.getSROp2());
		 * System.out.println("//VR2.\t " + modInstruction.getVROp2());
		 * System.out.println("//SR3.\t " + modInstruction.getSROp3());
		 * System.out.println("//VR3.\t " + modInstruction.getVROp3());
		 */
		return assignVirtualRegistersNextUse(modInstruction);
	}

	public static Instruction assignVirtualRegistersNextUse(Instruction lineInstruction) {
		ArrayList<Integer> lineNumbersOfVirtualRegisters;
		int indexOfNextUse;

		// ** Insert the next use for the virtual registers into the instruction */
		if (virtualRegisterLineNumbers.containsKey(lineInstruction.getVROp1())
				&& !lineInstruction.getVROp1().contains("Empty")) {
			// Get it
			lineNumbersOfVirtualRegisters =
					virtualRegisterLineNumbers.get(lineInstruction.getVROp1());
			// Size it
			indexOfNextUse = lineNumbersOfVirtualRegisters.size() - 1;
			// Assign it
			lineInstruction.setNUOp1(Integer.toString(lineNumbersOfVirtualRegisters
					.get(indexOfNextUse)));
		}

		if (virtualRegisterLineNumbers.containsKey(lineInstruction.getVROp2())
				&& !lineInstruction.getVROp2().contains("Empty")) {
			// Get it
			lineNumbersOfVirtualRegisters =
					virtualRegisterLineNumbers.get(lineInstruction.getVROp2());
			// Size it
			indexOfNextUse = lineNumbersOfVirtualRegisters.size() - 1;
			// Assign it
			lineInstruction.setNUOp2(Integer.toString(lineNumbersOfVirtualRegisters
					.get(indexOfNextUse)));
		}

		if (virtualRegisterLineNumbers.containsKey(lineInstruction.getVROp3())
				&& !lineInstruction.getVROp3().contains("Empty")) {
			// Get it
			lineNumbersOfVirtualRegisters =
					virtualRegisterLineNumbers.get(lineInstruction.getVROp3());
			// Size it
			indexOfNextUse = lineNumbersOfVirtualRegisters.size() - 1;
			// Assign it
			lineInstruction.setNUOp3(Integer.toString(lineNumbersOfVirtualRegisters
					.get(indexOfNextUse)));
		}

		// ** Add the new next use into the virtual register line numbers hash */
		if (virtualRegisterLineNumbers.containsKey(lineInstruction.getVROp1())
				&& !lineInstruction.getVROp1().contains("Empty")) {
			// Get it
			lineNumbersOfVirtualRegisters =
					virtualRegisterLineNumbers.get(lineInstruction.getVROp1());
			// Add it
			lineNumbersOfVirtualRegisters.add(lineInstruction.getLineNumber());
			// Set it
			virtualRegisterLineNumbers.put(lineInstruction.getVROp1(),
					lineNumbersOfVirtualRegisters);
		}

		if (virtualRegisterLineNumbers.containsKey(lineInstruction.getVROp2())
				&& !lineInstruction.getVROp2().contains("Empty")) {
			// Get it
			lineNumbersOfVirtualRegisters =
					virtualRegisterLineNumbers.get(lineInstruction.getVROp2());
			// Add it
			lineNumbersOfVirtualRegisters.add(lineInstruction.getLineNumber());
			// Set it
			virtualRegisterLineNumbers.put(lineInstruction.getVROp2(),
					lineNumbersOfVirtualRegisters);
		}

		if (virtualRegisterLineNumbers.containsKey(lineInstruction.getVROp3())
				&& !lineInstruction.getVROp3().contains("Empty")) {
			// Get it
			lineNumbersOfVirtualRegisters =
					virtualRegisterLineNumbers.get(lineInstruction.getVROp3());
			// Add it
			lineNumbersOfVirtualRegisters.add(lineInstruction.getLineNumber());
			// Set it
			virtualRegisterLineNumbers.put(lineInstruction.getVROp3(),
					lineNumbersOfVirtualRegisters);
		}

		/** If the key does not exist, add it to the virtual register line numbers */

		if (!virtualRegisterLineNumbers.containsKey(lineInstruction.getVROp1())
				&& !lineInstruction.getVROp1().contains("Empty")) {

			// Create new ArrayList
			lineNumbersOfVirtualRegisters = new ArrayList<Integer>();

			// Add line number to ArrayList
			lineNumbersOfVirtualRegisters.add(lineInstruction.getLineNumber());

			// Place virtual register and arrayList into hash
			virtualRegisterLineNumbers.put(lineInstruction.getVROp1(),
					lineNumbersOfVirtualRegisters);
		}

		if (!virtualRegisterLineNumbers.containsKey(lineInstruction.getVROp2())
				&& !lineInstruction.getVROp2().contains("Empty")) {

			// Create new ArrayList
			lineNumbersOfVirtualRegisters = new ArrayList<Integer>();

			// Add line number to ArrayList
			lineNumbersOfVirtualRegisters.add(lineInstruction.getLineNumber());

			// Place virtual register and arrayList into hash
			virtualRegisterLineNumbers.put(lineInstruction.getVROp2(),
					lineNumbersOfVirtualRegisters);
		}

		if (!virtualRegisterLineNumbers.containsKey(lineInstruction.getVROp3())
				&& !lineInstruction.getVROp3().contains("Empty")) {

			// Create new ArrayList
			lineNumbersOfVirtualRegisters = new ArrayList<Integer>();

			// Add line number to ArrayList
			lineNumbersOfVirtualRegisters.add(lineInstruction.getLineNumber());

			// Place virtual register and arrayList into hash
			virtualRegisterLineNumbers.put(lineInstruction.getVROp3(),
					lineNumbersOfVirtualRegisters);
		}

		return lineInstruction;
	}

	/**
	 * Opens up a text file and prints all the characters in the text.
	 * 
	 * @param filename
	 * @return
	 */
	public static void openAndRead(String filename) {

		Pattern p = Pattern.compile(REGEX);
		ArrayList<String> parsedItems = new ArrayList<String>();
		Instruction instruct;

		int countItems, instructionLineNumber = 0;

		String sourceRegister1 = "", sourceRegister2 = "", sourceRegister3 = "";

		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			// read in each line from the block
			while ((line = reader.readLine()) != null) {

				// Skip pass the comment section of the file
				if (line.contains("/") && line.charAt(0) == '/') {
					continue;
				}


				// the line in the correct format
				String[] items = p.split(line);
				countItems = -1;
				for (String s : items) {
					if (s.length() > 0) {
						parsedItems.add(s.trim());
						// System.out.println(s);
						countItems++;
					}
				}



				// Set up a new instruction
				instruct = new Instruction(instructionLineNumber);

				if (countItems >= 1) {

					// OpCode Memory
					if (!parsedItems.get(1).substring(0, 1).equals("r")) {
						// Set the source register
						sourceRegister3 = parsedItems.get(1);

						instruct.setTheOpcode(parsedItems.get(0));
						instruct.setSROp3(sourceRegister3);
					}

				}
				if (countItems >= 2) {

					// OpCode Memory SR1
					if (parsedItems.get(2).substring(0, 1).equals("r")) {
						// Set the source registers
						sourceRegister1 = parsedItems.get(1);
						sourceRegister3 = parsedItems.get(2);

						instruct.setTheOpcode(parsedItems.get(0));
						instruct.setSROp1(sourceRegister1);
						instruct.setSROp3(sourceRegister3);
					}

				}

				if (countItems >= 3) {

					// OpCode SR1 SR2 SR3
					if (parsedItems.get(1).substring(0, 1).equals("r")
							&& parsedItems.get(2).substring(0, 1).equals("r")
							&& parsedItems.get(3).substring(0, 1).equals("r")) {

						// Set the source registers
						sourceRegister1 = parsedItems.get(1);
						sourceRegister2 = parsedItems.get(2);
						sourceRegister3 = parsedItems.get(3);

						instruct.setTheOpcode(parsedItems.get(0));
						instruct.setSROp1(sourceRegister1);
						instruct.setSROp2(sourceRegister2);
						instruct.setSROp3(sourceRegister3);
						// System.out.println("Full instruction");
					}
				}

				// Add the instruction to the program
				topToBottom.add(instruct);

				// Clear the contents in the vector
				parsedItems.clear();

				// Increment the number count of lines
				instructionLineNumber++;

				// Clear the sourceRegisters
				sourceRegister1 = "";
				sourceRegister2 = "";
				sourceRegister3 = "";

				// System.out.println("************** \t end of the line");
			}
			reader.close();
		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();
		}

	}

	/**
	 * Methods prints out all the available options for parameters.
	 * 
	 * @param commandLine
	 * @param exitProgram
	 * @return
	 */
	public static boolean hFlag(String[] commandLine) {
		int arrayLen = commandLine.length;
		for (int i = 0; i < arrayLen; i++) {
			if (commandLine[i] == "-h") {
				System.out.println(" ");
				System.out
						.println("Command Syntax: "
								+ "\n\t    ./412alloc k filename [-h]\n\n"
								+ "\n Required arguments:"
								+ "\n\t    k     specifies the number of register available"
								+ "\n\t filename  is the pathname (absolute or relative) to the input file\n\n"
								+ "\n Optional flags:" + "\n\t    -h    prints this message" + "");
				return true;
			}
		}
		return false;
	}

	/**
	 * Parses the command line to find the number of registers to produce and places that number of
	 * registers into the class variable Set<String> holding all the registers.
	 * 
	 * @param filePath
	 */
	public static boolean generateXRegisters(String strInt) {
		String regName = "r";
		int numRegisters;
		numRegisters = Integer.parseInt(strInt);

		// Need at least two or more registers
		if (numRegisters < 2) {
			System.out.println("Cannot allocate with fewer than 2 registers.");
			System.exit(0);
		}

		// Create X number of registers
		for (int j = 0; j < numRegisters; j++) {
			regName += Integer.toString(j);
			// add to the set
			availablePhysicalRegisters.add(regName);
			regName = "r";
		}
		return true;
	}

}
