import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 * @author Ace
 *
 */
public class schedule {

	// Regular expression for the lines
	private static final String REGEX = "[=>, \t]";

	// Holds the instructions of program
	private static LinkedList<Instruction> allocation = new LinkedList<Instruction>();

	// Stack that first takes in the ILOC
	private static Stack<Instruction> topToBottom = new Stack<Instruction>();

	// Source Registers mapped to Virtual Registers
	private static HashMap<String, String> SRtoVR = new HashMap<String, String>();

	// Virtual Register number that will be incremented as number of virtual
	// registers increase
	private static int virutalRegisterNumber = 0;

	// Holds the lines in which the virtual registers are live
	private static HashMap<String, ArrayList<Integer>> virtualRegisterLineNumbers = new HashMap<String, ArrayList<Integer>>();

	// The dependence graph for the nodes, key is the node with in bound arrows
	// and value are noding pointing to that value
	private static HashMap<Integer, ArrayList<Integer>> dependenceEdges = new HashMap<Integer, ArrayList<Integer>>();

	// Contains the nodes (instruction line number) as keys and the weight as
	// values
	private static HashMap<Integer, Long> nodeWeights = new HashMap<Integer, Long>();

	// Functional unit one
	private static int[] FunUnit1 = { 0 };

	// Functional unit two
	private static int[] FunUnit2 = { 0 };

	// Schedule instructions by latency weight
	private static LinkedList<int[]> scheduledInstructions = new LinkedList<int[]>();

	// Index of the last store
	private static int lastStoreIdx = -1;

	// Index of the last load
	private static int lastLoadIdx = -1;

	// Index of the last output
	private static int lastOutputIdx = -1;

	// Name of the file
	private static String filePath = "";

	public static void main(String[] args) {

		// String[] inputFile = { "/Users/Ace/Downloads/lab3/report/report1.i"};
		// String[] inputFile = { "/Users/Ace/Downloads/lab3/report/report2.i"};
		// String[] inputFile = {"/Users/Ace/Downloads/lab3/report/report3.i"};
		// String[] inputFile = {"/Users/Ace/Downloads/lab3/report/report4.i"};
		// String[] inputFile = {"/Users/Ace/Downloads/lab3/report/report5.i"};
		// String[] inputFile = {"/Users/Ace/Downloads/lab3/report/report6.i"};
		// String[] inputFile = {"/Users/Ace/Downloads/lab3/report/report7.i"};
		// String[] inputFile = {"/Users/Ace/Downloads/lab3/report/report8.i"};
		// String[] inputFile = {"/Users/Ace/Downloads/lab3/report/report9.i"};
		// String[] inputFile = {"/Users/Ace/Downloads/lab3/report/report10.i"};
		// String[] inputFile = {"/Users/Ace/Downloads/lab3/report/report11.i"};
		// String[] inputFile = {"/Users/Ace/Downloads/lab3/report/report12.i"};
		// String[] inputFile = {"/Users/Ace/Downloads/lab3/report/report13.i"};
		// String[] inputFile = {"/Users/Ace/Downloads/lab3/report/report14.i"};
		// String[] inputFile = {"/Users/Ace/Downloads/lab3/report/report15.i"};
		// String[] inputFile = {"/Users/Ace/Downloads/lab3/report/report16.i"};
		// String[] inputFile = {"/Users/Ace/Downloads/lab3/report/report17.i"};
		// String[] inputFile = {"/Users/Ace/Downloads/lab3/report/report18.i"};
		// String[] inputFile = {"/Users/Ace/Downloads/lab3/report/report19.i"};
		//String[] inputFile = { "/Users/Ace/Downloads/lab3/report/report20.i" };

		 String[] inputFile = args;
		Instruction temp;

		readCommandLine(inputFile);

		// Read each line of the file and parse it
		openAndRead(filePath);

		//
		while (!topToBottom.isEmpty()) {
			// System.out.println(topToBottom.pop().getLineNumber());
			temp = assignVirtualRegister(topToBottom.pop());

			// Add Instructions to allocation for physical register assignment
			allocation.add(temp);

		}
		assignPhysicalRegistersandPrint();

		/*
		 * for (Integer node : dependenceEdges.keySet()) {
		 * System.out.println("Node: " + node + "\t Edges: " +
		 * dependenceEdges.get(node).toString()); }
		 */

		/*
		 * for (Integer node : dependenceEdges.keySet()) { for (int i = 0; i <
		 * dependenceEdges.get(node).size(); i++) {
		 * System.out.println(dependenceEdges.get(node).get(i) + ": " +
		 * allocation.get(dependenceEdges.get(node).get(i)) .getTheOpcode() +
		 * " " + allocation.get(dependenceEdges.get(node).get(i)) .getSROp1() +
		 * " " + allocation.get(dependenceEdges.get(node).get(i)) .getSROp2() +
		 * " " + allocation.get(dependenceEdges.get(node).get(i)) .getSROp3() +
		 * " -> " + node + ": " + allocation.get(node).getTheOpcode() + " " +
		 * allocation.get(node).getSROp1() + " " +
		 * allocation.get(node).getSROp1() + " " +
		 * allocation.get(node).getSROp2() + " " +
		 * allocation.get(node).getSROp3() + ";"); } }
		 */
		/*
		 * String label = ""; String labelS = "\""; for (Integer node :
		 * dependenceEdges.keySet()) { for (int i = 0; i <
		 * dependenceEdges.get(node).size(); i++) { label =
		 * allocation.get(allocation.size()-1-dependenceEdges.get(node).get(i))
		 * .getTheOpcode() + " " +
		 * allocation.get(allocation.size()-1-dependenceEdges.get(node).get(i))
		 * .getVROp1() + " " +
		 * allocation.get(allocation.size()-1-dependenceEdges.get(node).get(i))
		 * .getVROp2() + " " +
		 * allocation.get(allocation.size()-1-dependenceEdges.get(node).get(i))
		 * .getVROp3();
		 * 
		 * label = formatPrint(allocation.get(allocation.size()-1
		 * -dependenceEdges.get(node).get(i)));
		 * System.out.println(dependenceEdges.get(node).get(i) + " -> " + node +
		 * "[style=bold,label=" +labelS+ label+labelS+"];"); } }
		 * System.out.println("She said \"Hello!\" to me.");
		 */

		// Fill hash table with all the nodes and
		// initialize their weights to zero
		for (int j = 0; j < dependenceEdges.size(); j++) {
			nodeWeights.put(j, (long) 0);
		}

		// Opcode latency value
		int latency = 0;

		// Start from the bottom assign values to weights to the nodes
		int revCount = dependenceEdges.size() - 1;
		for (int i = 0; i < dependenceEdges.size(); i++) {
			// System.out.println("Value: " + dependenceEdges.get(revCount));

			// System.out.println("node number: " +
			// allocation.get(i).getLineNumber());
			latency = opcodeLatency(allocation.get(i).getTheOpcode());
			nodeWeights.put(revCount, nodeWeights.get(revCount) + latency);
			for (Integer inBoundNode : dependenceEdges.get(revCount)) {
				// System.out.println(">>>>>>>>> inBoundNode: " + inBoundNode);
				// System.out.println("The latency before: " +
				// nodeWeights.get(inBoundNode));
				nodeWeights.put(inBoundNode, nodeWeights.get(inBoundNode)
						+ nodeWeights.get(revCount));
				// System.out.println("The latency after: " +
				// nodeWeights.get(inBoundNode));
				// nodeWeights.put(inBoundNode, nodeWeights.get(inBoundNode) +
				// cp);
			}
			revCount--;
		}
		// print out the latency for each opcode
		int instructNumber = allocation.size() - 1;
		/*
		 * for (Integer key : nodeWeights.keySet()) {
		 * System.out.println("Node: " + key + " Instruction: " +
		 * formatPrint(allocation.get(instructNumber)) + " \tLatency value: " +
		 * nodeWeights.get(key)); System.out.println(" "); instructNumber--; }
		 */

		/*
		 * for (Integer key : nodeWeights.keySet()) { System.out.println(key +
		 * "  " + formatPrint(allocation.get(instructNumber)) +
		 * " \t\tLatency value: " + nodeWeights.get(key));
		 * System.out.println(" "); instructNumber--; }
		 */

		// Schedule the instructions
		if(allocation.size() <= 990){
		for (int z = 0; z < allocation.size(); z++) {
			sortByLatencyWeight(z);
		}
		}else{
			int[] nodeArray;
			for(int node = 0; node < allocation.size(); node++){
				nodeArray = new int[1];
				nodeArray[0] = node;
				scheduledInstructions.add(nodeArray);
		}
		}
		
		 /* for (int i = 0; i < allocation.size(); i++) {
		  System.out.println(scheduledInstructions.get(i)[0] +
		  " Latency weight: " +
		  nodeWeights.get(scheduledInstructions.get(i)[0]));
		  System.out.println(" "); }*/
		 

		int counter = scheduledInstructions.size() - 1;
		while (counter > -1) {
			String strFun1 = "nop";
			String strFun2 = "nop";
			if (FunUnit1[0] == 0 && counter > -1) {
				if(!allocation.get(scheduledInstructions.get(counter)[0])
						.getTheOpcode().equals("mult")){
				FunUnit1[0] = opcodeLatency(allocation.get(
						scheduledInstructions.get(counter)[0]).getTheOpcode());
				strFun1 = formatPrint(allocation.get(scheduledInstructions
						.get(counter)[0]));

				if (strFun1.contains("Empty")) {
					strFun1 = "nop";
				}
				// All long-latency operations (i.e., more than one cycle) are
				// non-blocking.

				
				 /* if ( allocation
				  .get(scheduledInstructions.get(counter-1)[0] )
				  .getTheOpcode().equals("load") || allocation
				  .get(scheduledInstructions.get(counter-1)[0])
				  .getTheOpcode().equals("store")) { FunUnit1[0] = 0; }*/
				 
				counter--;
			}
			}

			if (FunUnit2[0] == 0 && counter > -1) {
				if (!allocation.get(scheduledInstructions.get(counter)[0])
						.getTheOpcode().equals("load")) {
					if (!allocation
							.get(scheduledInstructions.get(counter + 1)[0])
							.getTheOpcode().equals("output")) {
						if(!allocation
								.get(scheduledInstructions.get(counter)[0])
								.getTheOpcode().equals("store")){
						FunUnit2[0] = opcodeLatency(allocation.get(
								scheduledInstructions.get(counter)[0])
								.getTheOpcode());
						strFun2 = formatPrint(allocation
								.get(scheduledInstructions.get(counter)[0]));

						if (strFun2.contains("Empty")) {
							strFun2 = "nop";
						}
						counter--;
						}
					}
				} else {
					FunUnit1[0] = 0;
				}
			}

			if (FunUnit1[0] > 0) {
				FunUnit1[0] -= 1;
			}

			if (FunUnit2[0] > 0) {
				FunUnit2[0] -= 1;
			}
			System.out.println("[" + strFun1 + "; " + strFun2 + "]");

		}
		// System.out.println("//finished.");
	}

	/**
	 * Finds the file and reads command line parameters for set-up.
	 * 
	 * @param cmdLine
	 *            : command line for the program
	 */
	public static void readCommandLine(String[] cmdLine) {
		int numArgs = cmdLine.length;
		int fileCount = 0, cmdCount = 0;

		// Set of the acceptable commands in command line
		HashSet<String> okayCmds = new HashSet<String>();
		okayCmds.add("-h");

		// Check the command line to see if there are any invalid commands
		for (int i = 0; i < numArgs; i++) {
			// Check to see if there is a dash which indicates that it is a
			// command
			if (cmdLine[i].contains("-") && cmdLine[i].length() == 2) {
				cmdCount++;
				// Prints out the acceptable command line arguments
				if (cmdLine[i].contains("-h")) {
					System.out.println(" ");
					System.out
							.println("Command Syntax: "
									+ "\n\t    ./schedule filename [-h]\n\n"
									+ "\n Required arguments:"
									+ "\n\t filename  is the pathname (absolute or relative) to the input file\n\n"
									+ "\n Optional flags:"
									+ "\n\t    -h    prints this message" + "");
					System.exit(0);
				}
				// Terminates program if invalid command is found
				if (!okayCmds.contains(cmdLine[i])) {
					System.out.println("Invalid command.");
					System.exit(0);
				}

			} else {
				filePath = cmdLine[i];
				fileCount++;
			}
		}

		// Check to see if more than one file was found
		if (fileCount != 1 || cmdCount > 1) {
			System.out.println("Invalid file input. Found " + fileCount
					+ " different file location(s).");
			System.out.println("And " + cmdCount
					+ " command line(s) arguments.");
			System.exit(0);
		}
		// Check if the file exists
		File f = new File(filePath);
		if (!f.exists() || f.isDirectory()) {
			System.out.println("Failed to open '" + filePath
					+ "' as the input file.");
			System.exit(0);
		}
	}

	/**
	 * Sorts all the nodes in order of decreasing latency.
	 * 
	 * @param node
	 *            ID number of the node
	 * @return
	 */
	public static void sortByLatencyWeight(int node) {
		int[] nodeArray;
		// System.out.println("Node number being passed in: " + node);
		// System.out.println("Size of scheduled Instructions: " +
		// scheduledInstructions.size());
		if (scheduledInstructions.size() == 0) {
			nodeArray = new int[1];
			nodeArray[0] = node;
			scheduledInstructions.add(nodeArray);
			return;
		}
		for (int i = scheduledInstructions.size() - 1; i >= 0; i--) {

			if (nodeWeights.get(scheduledInstructions.get(i)[0]) >= nodeWeights
					.get(node)) {

				nodeArray = new int[1];
				nodeArray[0] = node;
				scheduledInstructions.add(i + 1, nodeArray);
				return;
			}

		}
		// if new low number added it to the head
		nodeArray = new int[1];
		nodeArray[0] = node;
		scheduledInstructions.add(0, nodeArray);
	}

	public static String formatPrint(Instruction instruct) {
		String operation2PR = instruct.getVROp2();
		String VROP1 = instruct.getVROp1();
		if (instruct.getTheOpcode().contains("output")) {
			return instruct.getTheOpcode() + " " + instruct.getSROp3();
		}
		if (VROP1.contains("Empty")) {
			VROP1 = instruct.getSROp1();
		}
		String VROP2 = instruct.getVROp2();
		String VROP3 = instruct.getVROp3();
		if (VROP3.contains("Empty")) {
			VROP3 = instruct.getSROp3();
		}
		if (operation2PR.equals("Empty")) {
			operation2PR = "";
			return instruct.getTheOpcode() + " " + VROP1 + " =>" + " " + VROP3;
		} else {
			return instruct.getTheOpcode() + " " + instruct.getVROp1() + " , "
					+ operation2PR + " =>" + " " + VROP3;
		}
	}

	/**
	 * Looks up the latency for the opcode and returns it value.
	 * 
	 * @return
	 */
	public static int opcodeLatency(String opcode) {
		if (opcode.equals("load")) {
			return 5;
		}
		if (opcode.contains("loadI")) {
			return 1;
		}
		if (opcode.contains("store")) {
			return 5;
		}
		if (opcode.contains("add")) {
			return 1;
		}
		if (opcode.equals("sub")) {
			return 1;
		}
		if (opcode.equals("mult")) {
			return 3;
		}
		if (opcode.equals("lshift")) {
			return 1;
		}
		if (opcode.equals("rshift")) {
			return 1;
		}
		if (opcode.equals("output")) {
			return 1;
		}
		if (opcode.equals("nop")) {
			return 1;
		}
		return 0;
	}

	/**
	 * Fills the hashmap dependenceEdges with the nodes to build a dependence
	 * graph.
	 * 
	 * @param node
	 *            : Instruction
	 */
	public static void fillDependenceEdges(Instruction node) {
		// The line number will represent the node (instruction)
		int lineNum = node.getLineNumber();
		ArrayList<Integer> newList;

		// System.out.println("Line number for fill Dependence: " + lineNum);
		if (!dependenceEdges.containsKey(lineNum)) {
			dependenceEdges.put(lineNum, newList = new ArrayList<Integer>());
		}
		// Go through each virtual register for OP1 in the instruction and
		// add them to the values for the key
		if (!node.getVROp1().contains("Empty")) {
			for (Integer nodeInBound : virtualRegisterLineNumbers.get(node
					.getVROp1())) {
				if (nodeInBound <= lineNum) {
					continue;
				}
				// If the line number is not in the hash table, add it
				if (!dependenceEdges.containsKey(nodeInBound)) {

					// Empty arraylist for new keys in dependence graph
					newList = new ArrayList<Integer>();

					// Add the next use
					newList.add(lineNum);

					// New entry in hash table
					dependenceEdges.put(nodeInBound, newList);
				} else {
					if (dependenceEdges.get(nodeInBound).indexOf(lineNum) != -1) {
						dependenceEdges.get(nodeInBound).remove(
								dependenceEdges.get(nodeInBound).indexOf(
										lineNum));
					}
					dependenceEdges.get(nodeInBound).add(lineNum);
				}
			}
		}

		// Go through each virtual register for OP2 in the instruction and
		// add them to the values for the key
		if (!node.getVROp2().contains("Empty")) {
			for (Integer nodeInBound : virtualRegisterLineNumbers.get(node
					.getVROp2())) {
				if (nodeInBound <= lineNum) {
					continue;
				}
				// If the line number is not in the hash table, add it
				if (!dependenceEdges.containsKey(nodeInBound)) {

					// Empty arraylist for new keys in dependence graph
					newList = new ArrayList<Integer>();

					// Add the next use
					newList.add(lineNum);

					// New entry in hash table
					dependenceEdges.put(nodeInBound, newList);
				} else {
					if (dependenceEdges.get(nodeInBound).indexOf(lineNum) != -1) {
						dependenceEdges.get(nodeInBound).remove(
								dependenceEdges.get(nodeInBound).indexOf(
										lineNum));
					}
					dependenceEdges.get(nodeInBound).add(lineNum);
				}
			}
		}

		// Go through each virtual register for OP3 in the instruction and
		// add them to the values for the key
		if (!node.getVROp3().contains("Empty")) {
			for (Integer nodeInBound : virtualRegisterLineNumbers.get(node
					.getVROp3())) {
				if (nodeInBound <= lineNum) {
					continue;
				}
				// If the line number is not in the hash table, add it
				if (!dependenceEdges.containsKey(nodeInBound)) {

					// Empty arraylist for new keys in dependence graph
					newList = new ArrayList<Integer>();

					// Add the next use
					newList.add(lineNum);

					// New entry in hash table
					dependenceEdges.put(nodeInBound, newList);
				} else {
					if (dependenceEdges.get(nodeInBound).indexOf(lineNum) != -1) {
						dependenceEdges.get(nodeInBound).remove(
								dependenceEdges.get(nodeInBound).indexOf(
										lineNum));
					}
					dependenceEdges.get(nodeInBound).add(lineNum);
				}
			}
		}

	}

	public static void assignPhysicalRegistersandPrint() {
		// String sr1 = "", sr2 = "", sr3 = "", vr1 = "", vr2 = "", vr3 = "";
		int programSize = allocation.size() - 1;
		Instruction instru;
		/*
		 * for (String key : virtualRegisterLineNumbers.keySet()) {
		 * System.out.println(key +
		 * virtualRegisterLineNumbers.get(key).toString()); }
		 */
		for (int i = programSize; 0 <= i; i--) {
			// System.out.println(allocation.get(i).getLineNumber());
			instru = allocation.get(i);
			fillDependenceEdges(instru);
			/*
			 * sr1 = instru.getSROp1(); sr2 = instru.getSROp2(); sr3 =
			 * instru.getSROp3(); vr1 = instru.getVROp1(); vr2 =
			 * instru.getVROp2(); vr3 = instru.getVROp3(); if
			 * (sr1.contains("Empty")) { sr1 = ""; } if (sr2.contains("Empty"))
			 * { sr2 = ""; } if (sr3.contains("Empty")) { sr3 = ""; } if
			 * (vr1.contains("Empty")) { vr1 = ""; } if (vr2.contains("Empty"))
			 * { vr2 = ""; } if (vr3.contains("Empty")) { vr3 = ""; }
			 * System.out.println(instru.getLineNumber() + " " +
			 * instru.getTheOpcode() + "\t" + vr1 + " " + vr2 + "\t" + vr3);// +
			 * "\t // " // + "\t" + sr1 + " " + sr2 + "\t" + sr3 + //
			 * "\t (The source registers)"); System.out.println("");
			 */
		}

		// Add dependencies among the memory operations
		for (int j = programSize; 0 <= j; j--) {
			instru = allocation.get(j);

			if (instru.getTheOpcode().equals("load") && lastLoadIdx != -1) {
				if (dependenceEdges.get(instru.getLineNumber()).indexOf(
						lastLoadIdx) != -1) {
					dependenceEdges.get(instru.getLineNumber()).remove(
							dependenceEdges.get(instru.getLineNumber())
									.indexOf(lastLoadIdx));
				}
				// Add the edge between the two load nodes
				dependenceEdges.get(instru.getLineNumber()).add(lastLoadIdx);
			}

			// Case 1 & 3
			if (instru.getTheOpcode().equals("store")) {
				if (lastStoreIdx != -1) {
					// Remove any duplicates
					if (dependenceEdges.get(instru.getLineNumber()).indexOf(
							lastStoreIdx) != -1) {
						dependenceEdges.get(instru.getLineNumber()).remove(
								dependenceEdges.get(instru.getLineNumber())
										.indexOf(lastStoreIdx));
					}
					// Add the edge between the two load nodes
					dependenceEdges.get(instru.getLineNumber()).add(
							lastStoreIdx);
				}

				if (lastLoadIdx != -1) {
					// Remove any duplicates
					if (dependenceEdges.get(instru.getLineNumber()).indexOf(
							lastLoadIdx) != -1) {
						dependenceEdges.get(instru.getLineNumber()).remove(
								dependenceEdges.get(instru.getLineNumber())
										.indexOf(lastLoadIdx));
					}
					// Add the edge between the two load nodes
					dependenceEdges.get(instru.getLineNumber())
							.add(lastLoadIdx);
				}

				if (lastOutputIdx != -1) {
					// Remove any duplicates
					if (dependenceEdges.get(instru.getLineNumber()).indexOf(
							lastOutputIdx) != -1) {
						dependenceEdges.get(instru.getLineNumber()).remove(
								dependenceEdges.get(instru.getLineNumber())
										.indexOf(lastOutputIdx));
					}
					// Add the edge between the two load nodes
					dependenceEdges.get(instru.getLineNumber()).add(
							lastOutputIdx);
				}

			}

			// Case 2
			if (instru.getTheOpcode().equals("output")) {
				// Case 2
				if (lastOutputIdx != -1) {
					// Remove any duplicates
					if (dependenceEdges.get(instru.getLineNumber()).indexOf(
							lastOutputIdx) != -1) {
						dependenceEdges.get(instru.getLineNumber()).remove(
								dependenceEdges.get(instru.getLineNumber())
										.indexOf(lastOutputIdx));
					}
					// Add the edge between the two output nodes
					dependenceEdges.get(instru.getLineNumber()).add(
							lastOutputIdx);
				}

			}

			// Update the indices if valid
			if (instru.getTheOpcode().equals("load")) {
				lastLoadIdx = instru.getLineNumber();
				continue;
			}
			if (instru.getTheOpcode().equals("store")) {
				lastStoreIdx = instru.getLineNumber();
				continue;
			}
			if (instru.getTheOpcode().equals("output")) {
				lastOutputIdx = instru.getLineNumber();
			}
		}
	}

	public static Instruction assignVirtualRegister(Instruction modInstruction) {
		String sourceRegister1 = "Empty", sourceRegister2 = "Empty", sourceRegister3 = "Empty";

		// Make sure that source register 1 is a register
		if (modInstruction.getSROp1().length() >= 2
				&& modInstruction.getSROp1().charAt(0) == 'r'
				&& Character.isDigit(modInstruction.getSROp1().charAt(1))) {
			sourceRegister1 = modInstruction.getSROp1();
		}

		// Make sure that source register 2 is a register
		if (modInstruction.getSROp2().length() >= 2
				&& modInstruction.getSROp2().charAt(0) == 'r'
				&& Character.isDigit(modInstruction.getSROp2().charAt(1))) {
			sourceRegister2 = modInstruction.getSROp2();
		}

		// Make sure that source register 3 is a register
		if (modInstruction.getSROp3().length() >= 2
				&& modInstruction.getSROp3().charAt(0) == 'r'
				&& Character.isDigit(modInstruction.getSROp3().charAt(1))) {
			sourceRegister3 = modInstruction.getSROp3();
		}

		// If source register 3 is not in the hash, add it
		if (!SRtoVR.containsKey(sourceRegister3)
				&& !sourceRegister3.contains("Empty")) {

			SRtoVR.put(sourceRegister3,
					"r" + Integer.toString(virutalRegisterNumber));

			// Save virtual register to the instruction
			modInstruction.setVROp3(SRtoVR.get(sourceRegister3));
			virutalRegisterNumber++;

		} else if (!sourceRegister3.contains("Empty")) {

			// Save virtual register to the instruction
			modInstruction.setVROp3(SRtoVR.get(sourceRegister3));
		}

		// If source register 2 equals source register 3, give source register 2
		// new a virtual
		// register
		if (!sourceRegister2.contains("Empty")
				&& sourceRegister2.contains(sourceRegister3)) {
			SRtoVR.remove(sourceRegister2);
			SRtoVR.put(sourceRegister2,
					"r" + Integer.toString(virutalRegisterNumber));

			// Save virtual register to the instruction
			modInstruction.setVROp2(SRtoVR.get(sourceRegister2));
			virutalRegisterNumber++;
		}

		// If source register 1 equals source register 3, give source register 1
		// new a virtual
		// register
		if (!sourceRegister1.contains("Empty")
				&& sourceRegister1.contains(sourceRegister3)) {
			SRtoVR.remove(sourceRegister1);
			SRtoVR.put(sourceRegister1,
					"r" + Integer.toString(virutalRegisterNumber));

			// Save virtual register to the instruction
			modInstruction.setVROp1(SRtoVR.get(sourceRegister1));
			virutalRegisterNumber++;
		}

		// If source register 2 is not in the hash, add it
		if (!SRtoVR.containsKey(sourceRegister2)
				&& !sourceRegister2.contains("Empty")) {
			SRtoVR.put(sourceRegister2,
					"r" + Integer.toString(virutalRegisterNumber));

			// Save virtual register to the instruction
			modInstruction.setVROp2(SRtoVR.get(sourceRegister2));
			virutalRegisterNumber++;
		} else if (!sourceRegister2.contains("Empty")) {

			// Save virtual register to the instruction
			modInstruction.setVROp2(SRtoVR.get(sourceRegister2));
		}

		// If source register 1 is not in the hash, add it
		if (!SRtoVR.containsKey(sourceRegister1)
				&& !sourceRegister1.contains("Empty")) {
			SRtoVR.put(sourceRegister1,
					"r" + Integer.toString(virutalRegisterNumber));

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

	public static Instruction assignVirtualRegistersNextUse(
			Instruction lineInstruction) {
		ArrayList<Integer> lineNumbersOfVirtualRegisters;
		int indexOfNextUse;

		// ** Insert the next use for the virtual registers into the instruction
		// */
		if (virtualRegisterLineNumbers.containsKey(lineInstruction.getVROp1())
				&& !lineInstruction.getVROp1().contains("Empty")) {
			// Get it
			lineNumbersOfVirtualRegisters = virtualRegisterLineNumbers
					.get(lineInstruction.getVROp1());
			// Size it
			indexOfNextUse = lineNumbersOfVirtualRegisters.size() - 1;
			// Assign it
			lineInstruction
					.setNUOp1(Integer.toString(lineNumbersOfVirtualRegisters
							.get(indexOfNextUse)));
		}

		if (virtualRegisterLineNumbers.containsKey(lineInstruction.getVROp2())
				&& !lineInstruction.getVROp2().contains("Empty")) {
			// Get it
			lineNumbersOfVirtualRegisters = virtualRegisterLineNumbers
					.get(lineInstruction.getVROp2());
			// Size it
			indexOfNextUse = lineNumbersOfVirtualRegisters.size() - 1;
			// Assign it
			lineInstruction
					.setNUOp2(Integer.toString(lineNumbersOfVirtualRegisters
							.get(indexOfNextUse)));
		}

		if (virtualRegisterLineNumbers.containsKey(lineInstruction.getVROp3())
				&& !lineInstruction.getVROp3().contains("Empty")) {
			// Get it
			lineNumbersOfVirtualRegisters = virtualRegisterLineNumbers
					.get(lineInstruction.getVROp3());
			// Size it
			indexOfNextUse = lineNumbersOfVirtualRegisters.size() - 1;
			// Assign it
			lineInstruction
					.setNUOp3(Integer.toString(lineNumbersOfVirtualRegisters
							.get(indexOfNextUse)));
		}

		// ** Add the new next use into the virtual register line numbers hash
		// */
		if (virtualRegisterLineNumbers.containsKey(lineInstruction.getVROp1())
				&& !lineInstruction.getVROp1().contains("Empty")) {
			// Get it
			lineNumbersOfVirtualRegisters = virtualRegisterLineNumbers
					.get(lineInstruction.getVROp1());
			// Add it
			lineNumbersOfVirtualRegisters.add(lineInstruction.getLineNumber());
			// Set it
			virtualRegisterLineNumbers.put(lineInstruction.getVROp1(),
					lineNumbersOfVirtualRegisters);
		}

		if (virtualRegisterLineNumbers.containsKey(lineInstruction.getVROp2())
				&& !lineInstruction.getVROp2().contains("Empty")) {
			// Get it
			lineNumbersOfVirtualRegisters = virtualRegisterLineNumbers
					.get(lineInstruction.getVROp2());
			// Add it
			lineNumbersOfVirtualRegisters.add(lineInstruction.getLineNumber());
			// Set it
			virtualRegisterLineNumbers.put(lineInstruction.getVROp2(),
					lineNumbersOfVirtualRegisters);
		}

		if (virtualRegisterLineNumbers.containsKey(lineInstruction.getVROp3())
				&& !lineInstruction.getVROp3().contains("Empty")) {
			// Get it
			lineNumbersOfVirtualRegisters = virtualRegisterLineNumbers
					.get(lineInstruction.getVROp3());
			// Add it
			lineNumbersOfVirtualRegisters.add(lineInstruction.getLineNumber());
			// Set it
			virtualRegisterLineNumbers.put(lineInstruction.getVROp3(),
					lineNumbersOfVirtualRegisters);
		}

		/**
		 * If the key does not exist, add it to the virtual register line
		 * numbers
		 */

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
								+ "\n\t    ./schedule filename [-h]\n\n"
								+ "\n Required arguments:"
								+ "\n\t filename  is the pathname (absolute or relative) to the input file\n\n"
								+ "\n Optional flags:"
								+ "\n\t    -h    prints this message" + "");
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
			System.err.format("Exception occurred trying to read '%s'.",
					filename);
			e.printStackTrace();
		}

	}

}
