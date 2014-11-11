import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.regex.Pattern;


public class schedule {
	
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

	public static void main(String[] args) {
		
		String[] inputLine = {"/Users/Ace/Downloads/lab3/report/report1.i"};
		//String[] inputLine = {"/Users/Ace/Downloads/lab3/report/report2.i"};
		//String[] inputLine = {"/Users/Ace/Downloads/lab3/report/report3.i"};
		//String[] inputLine = {"/Users/Ace/Downloads/lab3/report/report4.i"};
		//String[] inputLine = {"/Users/Ace/Downloads/lab3/report/report5.i"};
		//String[] inputLine = {"/Users/Ace/Downloads/lab3/report/report6.i"};

		//String[] inputLine = {"/Users/Ace/Downloads/lab3/report/report7.i"};
		//String[] inputLine = {"/Users/Ace/Downloads/lab3/report/report8.i"};
		//String[] inputLine = {"/Users/Ace/Downloads/lab3/report/report9.i"};
		//String[] inputLine = {"/Users/Ace/Downloads/lab3/report/report10.i"};
		//String[] inputLine = {"/Users/Ace/Downloads/lab3/report/report11.i"};
		//String[] inputLine = {"/Users/Ace/Downloads/lab3/report/report12.i"};

		//String[] inputLine = {"/Users/Ace/Downloads/lab3/report/report13.i"};
		//String[] inputLine = {"/Users/Ace/Downloads/lab3/report/report14.i"};
		//String[] inputLine = {"/Users/Ace/Downloads/lab3/report/report15.i"};
		//String[] inputLine = {"/Users/Ace/Downloads/lab3/report/report16.i"};
		//String[] inputLine = {"/Users/Ace/Downloads/lab3/report/report17.i"};
		//String[] inputLine = {"/Users/Ace/Downloads/lab3/report/report18.i"};

		//String[] inputLine = {"/Users/Ace/Downloads/lab3/report/report19.i"};
		//String[] inputLine = {"/Users/Ace/Downloads/lab3/report/report20.i"};
		
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

}
