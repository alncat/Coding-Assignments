import java.util.*;
import java.io.*;

import javax.swing.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

class ThisMyDocumentDatabase{
  //WordCounter Ojbect
  private WordMounter wordCount = new WordMounter();
  private FileReader file = null;
  private BufferedReader reader = null;
  //Dictionary that contains the words from top k
  private ArrayList<String> wordDict, secondWordDic;
  private Map<String, Integer> wordDictMap, secondOtherWordDicMap;
  //the alpha Vector and betaVec
  private IDoubleVector alphaVec, betaVec; //secondAlpha, secondBeta;
  //Words In Document A and topic Probs B
  private IDoubleVector wIDA, tPB;
  //PRNG
  private IPRNG random = new PRNG(12);
  //LDALearner
  private SCLDALearner myLearning, secLearner;
  //Number of topics to index
  private int numTopicsToIndex, secondNumberWords, secondNumberTopics;
  //ith Topic Vector in the matix of WordProbs
  private IDoubleVector ithTopicVec;
  //(2)Set the number of query results to return
  private int numResultsReturnFromQuery = 1;
  //homeDirectory
  private String homeDirectory, secondLearnerString;
  private double secSummationVal, secondAlpha, secondBeta;
  
  private WordMounter secwordCount;
  private SaveComponents saved;
  private File homeDirectoryFile;
  
  private String[] stopWords = new String[]{ "a", "about", "above", "above", "across", "after", "afterwards", "again", "against", "all", "almost", "alone", "along", "already", "also","although","always","am","among", "amongst", "amoungst", "amount",  "an", "and", "another", "any","anyhow","anyone","anything","anyway", "anywhere", "are", "around", "as",  "at", "back","be","became", "because","become","becomes", "becoming", "been", "before", "beforehand", "behind", "being", "below", "beside", "besides", "between", "beyond", "bill", "both", "bottom","but", "by", "call", "can", "cannot", "cant", "co", "con", "could", "couldnt", "cry", "de", "describe", "detail", "do", "done", "down", "due", "during", "each", "eg", "eight", "either", "eleven","else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every", "everyone", "everything", "everywhere", "except", "few", "fifteen", "fify", "fill", "find", "fire", "first", "five", "for", "former", "formerly", "forty", "found", "four", "from", "front", "full", "further", "get", "give", "go", "had", "has", "hasnt", "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "him", "himself", "his", "how", "however", "hundred", "ie", "if", "in", "inc", "indeed", "interest", "into", "is", "it", "its", "itself", "keep", "last", "latter", "latterly", "least", "less", "ltd", "made", "many", "may", "me", "meanwhile", "might", "mill", "mine", "more", "moreover", "most", "mostly", "move", "much", "must", "my", "myself", "name", "namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody", "none", "noone", "nor", "not", "nothing", "now", "nowhere", "of", "off", "often", "on", "once", "one", "only", "onto", "or", "other", "others", "otherwise", "our", "ours", "ourselves", "out", "over", "own","part", "per", "perhaps", "please", "put", "rather", "re", "same", "see", "seem", "seemed", "seeming", "seems", "serious", "several", "she", "should", "show", "side", "since", "sincere", "six", "sixty", "so", "some", "somehow", "someone", "something", "sometime", "sometimes", "somewhere", "still", "such", "system", "take", "ten", "than", "that", "the", "their", "them", "themselves", "then", "thence", "there", "thereafter", "thereby", "therefore", "therein", "thereupon", "these", "they", "thickv", "thin", "third", "this", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "top", "toward", "towards", "twelve", "twenty", "two", "un", "under", "until", "up", "upon", "us", "very", "via", "was", "we", "well", "were", "what", "whatever", "when", "whence", "whenever", "where", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within", "without", "would", "yet", "you", "your", "yours", "yourself", "yourselves", "the" };
  public ThisMyDocumentDatabase(){}
  
  Scanner input = new Scanner(System.in);
  public void run(String uiHost, UUID uiKey){
    //After hit run this is what shows up
    System.out.println("Do you want to:");
    System.out.println("\t(1) Index a new corpus");
    System.out.println("\t(2) Use/manage an already-index one:");
    while(true){ //LeveL 000000000000000000000000
      int selection = input.nextInt();
      //first level
      if (selection == 1 || selection == 2){
        if (selection == 1){
          System.out.println("Enter the directory where the corpus is located: ");
          //!!!!!!JFileChooser fc = new JFileChooser ();
        //!!!!!! fc.setDialogTitle ("Where is the directory where the corpus is located? ");
        //!!!!!!fc.setFileSelectionMode (JFileChooser.DIRECTORIES_ONLY);
        //!!!!!!fc.showOpenDialog(null);
          FileChooser fc = new FileChooser();
        //!!!!!! File result = fc.getSelectedFile ();
          String[] inputString = new String[2];
          inputString[0] = uiHost;
          inputString[1] = uiKey.toString();
        //!!!!!!File result = fc.main(inputString);
          
			try {
				 homeDirectoryFile = fc.main(inputString);
			} catch (IOException e1) {
				System.out.println("An error has occurred with the file path...");
			}
        //!!!!!! try{
        //!!!!!!    String homeDirectory = result.getCanonicalPath();
          //!!!!!!     System.out.println(homeDirectory);
          //!!!!!!   }catch(IOException E){}
			System.out.println("The home directory: " + homeDirectoryFile.getName());
			System.out.println("The length of the file: " + homeDirectoryFile.length() );
          System.out.println("Counting the number of occurs of each word in the corpus...");
          //get all the words
        File[] fileString = new File[0]; //!!!!!!result.listFiles();
          //total number of files
          int totalFiles = fileString.length;
          //loop through each file
        //!!!!!! for(int ithFile = 0; ithFile < totalFiles; ithFile++){
            File thisFile = homeDirectoryFile; //!!!!!!fileString[ithFile];
            
            BufferedReader inputStream = null;
            
            try {
              inputStream =
                new BufferedReader(new FileReader(thisFile.toString()));
              
              String l;
              while ((l = inputStream.readLine()) != null){//(l = inputStream.readLine()) != null
            	  l = inputStream.readLine();
                countChris(l, wordCount);
              }
            }catch(IOException E){} finally {
              if (inputStream != null) {
                try{
                  inputStream.close();
                }catch(IOException E){}
              }
              
            }
            
          //!!!!!!}
          int totalUniqueWords = wordCount.uniqueWords();
          System.out.println("Found " + totalUniqueWords + " unique words in the corpus.");
          System.out.println("How many of those words do you want to use to index the docs");
          int numWordsToIndex = input.nextInt();
          //Set<String> theWordsFound = wordCount.returnKeys();
          //Iterator<String> wordsLooping = theWordsFound.iterator();
          wordDict = new ArrayList<String>();
          wordDictMap = new HashMap<String, Integer>();
          int indexWordPostion = 0;
          //Iterator anotherIt = wordCount.getTopKWordsThere();
          ArrayList<String> fullOfWords = wordCount.getKthMostFrequent();
          while(indexWordPostion < numWordsToIndex){
            //Map.Entry m =(Map.Entry) anotherIt.next();
            String pullthisWord = fullOfWords.get(indexWordPostion);
            wordDict.add(pullthisWord);
            wordDictMap.put(pullthisWord, indexWordPostion);
            //System.out.println(indexWordPostion);
            indexWordPostion++;
          }
          
          
//          for(int i = 0; i < numWordsToIndex; i++){
//            wordDict.add(wordCount.getKthMostFrequent(i));
//          }
          //if you input any integers this is what comes up next
          System.out.println("");
          System.out.println("Done creating the dictionary.");
          System.out.println("How many topics do you want to use to index the docs?");
          int numTopicsToIndex = input.nextInt();//used for the top k machine
          
          //input the number then shows up next
          System.out.println("What value should I use in the alpha vector?");
          double valAlphaVec = input.nextDouble();
          alphaVec = new SparseDoubleVector(numWordsToIndex,valAlphaVec);
          //input the alpha parameter
          System.out.println("What value should I use in the beta vector?");
          double valBetaVec = input.nextDouble();
          System.out.println("Loading Documents into LDALearner...");
          betaVec = new SparseDoubleVector(numTopicsToIndex,valBetaVec);
          myLearning = new SCLDALearner (alphaVec, betaVec, random);
          //________________________________From the A8 Tester first method________________________
          
          // this is the true word_prob matrix, and the initial one
          IDoubleMatrix trueWordProbs = new RowMajorDoubleMatrix (numTopicsToIndex, numWordsToIndex, 0.0);
          IDoubleMatrix initialWordProbs = new RowMajorDoubleMatrix (numTopicsToIndex, numWordsToIndex, 0.0);
          
          try {
            // and load it up with data... there is no overalap among topics here
            for (int i = 0; i < numTopicsToIndex; i++) {
              IDoubleVector myWordProbs = new SparseDoubleVector (numWordsToIndex, 0.02);
              int mult = numWordsToIndex / numTopicsToIndex;
              for (int j = i * mult; j < (i + 1) * mult; j++) {
                myWordProbs.setItem (j, 1.0); 
              }
              myWordProbs.normalize ();
              trueWordProbs.setRow (i, myWordProbs);
              
              // choose an initial word_probs vector
              Dirichlet myDirichlet = new Dirichlet (random, new DirichletParam (new SparseDoubleVector (numWordsToIndex, 1), 10e-99, 1000));
              IDoubleVector initialProbs = myDirichlet.getNext ();
              myWordProbs.addMyselfToHim (initialProbs);
              initialProbs.normalize ();
              initialWordProbs.setRow (i, initialProbs);
            }
            
            // load up the initial word_probs matrix
            myLearning.loadUpWordProbs (initialWordProbs);
            //System.out.println("____________gets here______________");
            // now we create the docs
            for (int i = 0; i < totalFiles; i++) {
              File thisFile0 = fileString[i];
              //System.out.println("File Number: " + i);
              IDoubleVector wordsInDoc = new SparseDoubleVector(numWordsToIndex, 0.0);
              BufferedReader inputStream0 = null;
              PrintWriter outputStream0 = null;
              try {
                inputStream =
                  new BufferedReader(new FileReader(thisFile.toString()));
                
                String l;
                while ((l = inputStream.readLine()) != null){
                  wordsInDoc = countWordInDocument (l, wordsInDoc);
                }
              }catch(IOException E){} finally {
                if (inputStream != null) {
                  try{
                    inputStream.close();
                  }catch(IOException E){}
                }
                
              }
              // choose an initial topic_probs vector
              Dirichlet myDirichlet = new Dirichlet (random, new DirichletParam (new SparseDoubleVector (numTopicsToIndex, 1), 10e-99, 1000));
              IDoubleVector initialTopicProbs = myDirichlet.getNext ();
              
              myLearning.loadUpAnotherDoc (thisFile.toString() + "", wordsInDoc, initialTopicProbs);
            }
          } catch (Exception e) {
            e.printStackTrace ();
            
          }
          
          
          //_______________________________________________From the A8 Tester first method________________________
          double summationVal = 0.0;
          Iterator <DocSignature> allDocuments = myLearning.iterator();
          while(allDocuments.hasNext()){
            DocSignature singleDocument = allDocuments.next();
            IDoubleVector wordVecDoc = singleDocument.getWordsInDoc();
            summationVal += wordVecDoc.l1Norm();
          }
          System.out.println("Done loading all of the docs.");
          
          System.out.println("And now I've initialized the word_probs matrix.");
          
          while(true){
            
            while(true){//second while loop
              System.out.println("Do you want to:");
              System.out.println("\t(1)Update/view/save the learned model");
              System.out.println("\t(2) Use the learned model to search the corpus");
              System.out.println("\t(3)Exit:");
              
              selection = input.nextInt();
              //this is correct
              if(selection == 1){//"\t(1)Update/view/save the learned model"
                while(true){
                  System.out.println("Do you want to:");
                  System.out.println("\t(1) Run some iterations of the learner");
                  System.out.println("\t(2) Print out the current topics");
                  System.out.println("\t(3) Write the current model to a file");
                  System.out.println("\t(4) Stop updating/viewing/saving the model:");
                  selection = input.nextInt();
                  if(selection == 1){//t(1) Run some iterations of the learner
                    // selection 1 gets you this
                    System.out.println("How many iters do you want to run?");
                    //enter an integer and it gets you this printed out based on the number of n iterations
                    int numIterations = input.nextInt();
                    //if you select 1 the bottom shows up
                    for(int i = 0; i < numIterations; i++){
                      
                      System.out.println("Running iter "+i+" ..."); //THIS LOOPS FROM 1 TO NUMBER OF INPUTED INTERATIONS
                      System.out.println("\tUpdating produced... ");//THIS LOOPS FROM 1 TO NUMBER OF INPUTED INTERATION
                      myLearning.updateProduced();
                      System.out.println("\tUpdating topic_probs... ");
                      myLearning.updateTopicProbs();
                      System.out.println("\tUpdating word_probs... ");
                      myLearning.updateWordProbs();
                    }
                  }//========================
                  if(selection == 2){//\t(2) Print out the current topics
                    // selection 2 gets you this from Print out the current topics
                    System.out.println("How many of the top words do you want for each topic?");
                    //lets say that you input 5, you get 0 to n topics that five words long
                    int numTopWordsEachTopic = input.nextInt();
                    int ithTopic = 0;
                    int numWordsInDict = wordDict.size();
                    while(ithTopic < numTopicsToIndex){
                      AVLTopKMachine jmachine = new AVLTopKMachine(numTopWordsEachTopic);
                      try{
                        
                        
                        for(int j = 0; j < numWordsInDict; j++){
                          
                          double pt = wordCount.giveMeVal(wordDict.get(j))/summationVal;
                          double pc = myLearning.getWordProbs().getEntry(ithTopic, j);
                          double formula = pt*Math.log(pt/pc) + (1 - pc)*Math.log((1-pt)/(1-pc));
                          
                          jmachine.insert(formula, wordDict.get(j));
                          
                        }
                      }catch(OutOfBoundsException E){
                        System.out.println("Trouble inserting into the machine.");
                      }
                      
                      ArrayList topicWords = jmachine.getTopK();
                      int numWords = topicWords.size();
                      String topicString = " ";
                      for(int x = 0; x < numWords; x++){
                        topicString += " " + topicWords.get(x);
                      }
                      System.out.println("topic " +ithTopic+ topicString);
                      ithTopic++;
                    }
                    
                  }
                  //THIS IS CORRECT
                  if(selection == 3){//\t(3) Write the current model to a file
                    // selection 3 gets you this from (3) Write the current model to a file"
                    System.out.println("What diretory should I write to?");
                    //select the file location in the window pane, after that
                    JFileChooser fcNew = new JFileChooser ();
                    fcNew.setDialogTitle ("Select the directory location.");
                    fcNew.setFileSelectionMode (JFileChooser.DIRECTORIES_ONLY);
                    fcNew.showOpenDialog(null);
                    File resultNew = fcNew.getSelectedFile ();
                    try{
                      String homeDirectory = resultNew.getCanonicalPath(); //WE NEED TO GET THIS TO WORK AAHAHAHAHAHAHHAHAHAH
                      System.out.println(homeDirectory);
                      System.out.println("What is the name of the XML file where the topic model should be written?");
                      String thisFileXName = input.next();
                      myLearning.writeToFile(homeDirectory+"\\"+thisFileXName);
                      
                      System.out.println("And where should the binary file with the other meta-data be written?");
                      String thisFileName = input.next();
                      SaveComponents saveForLater = new SaveComponents();
//                      saveForLater.theWordDicArrayList = wordDict;
//                      saveForLater.theOtherWordDic = wordDictMap;
//                      saveForLater.thenumberOfWords = numWordsToIndex;
                      saveForLater.thenumberOfTopics = numTopicsToIndex;
                      //saveForLater.theAlpha = alphaVec;
                      //saveForLater.theBeta = betaVec;
//                      saveForLater.nameXMLFile = homeDirectory+"\\"+thisFileName;
//                      saveForLater.thesummation = summationVal;
//                      saveForLater.thewordCount = wordCount;
                      FileOutputStream fileOut = new FileOutputStream(homeDirectory+"\\"+thisFileName);
                      ObjectOutputStream out = new ObjectOutputStream(fileOut);
                      out.writeObject(wordDict);
                      out.writeObject(wordDictMap);
                      out.writeObject(numWordsToIndex);
                      out.writeObject(numTopicsToIndex);
                      out.writeObject(valAlphaVec);
                      out.writeObject(valBetaVec);
                      out.writeObject(homeDirectory+"\\"+thisFileXName);
                      out.writeObject(summationVal);
                      out.writeObject(wordCount);
                      out.close();
                      fileOut.close();
                    }catch(IOException E){}
                    selection = -11;//________________________________________________________________________________
                    
                  }
                  if(selection == 4){//\t(4) Stop updating/viewing/saving the model:"
                    break;
                  }
                }
              }
              
              if(selection == 2){//(2) Use the learned model to search the corpus
                System.out.println("OK. Building an M-Tree index...");
                SCMTree treeLearning = new SCMTree(3,4);
                allDocuments = myLearning.iterator();
                while(allDocuments.hasNext()){
                  DocSignature singleDocument = allDocuments.next();
                  treeLearning.insert(new MultiDimPoint(singleDocument.getTopicProbs()),singleDocument.getFName());
                }
                System.out.println("Done buidling an M-Tree index.");
                while(true){//whileXXY
                  
                  System.out.println("Do you want to:");
                  System.out.println("\t(1)Search the database");
                  System.out.println("\t(2) Set the number of query results to return");
                  System.out.println("\t(3)Stop searching the database:");
                  selection = input.nextInt();
                  if(selection == 1){//(1)Search the database"
                    try{
                      WordMounter singleCounter = new WordMounter();
                      JFileChooser fcNew = new JFileChooser ();
                      fcNew.setDialogTitle ("What file do you want to use to find related documents?");
                      fcNew.setFileSelectionMode (JFileChooser.FILES_ONLY);
                      fcNew.showOpenDialog(null);
                      File resultNew = fcNew.getSelectedFile ();
                      String homeDirectory = resultNew.getCanonicalPath(); //WE NEED TO GET THIS TO WORK AAHAHAHAHAHAHHAHAHAH
                      SubstringGetter windowToFile = new SubstringGetter(homeDirectory);
                      readSingleLine(windowToFile.getSubstring());
                      Dirichlet myDirichlet = new Dirichlet (random, new DirichletParam (new SparseDoubleVector (betaVec.getLength(), 1), 10e-99, 1000));
                      tPB = myDirichlet.getNext();
                      
                      myLearning.loadUpAnotherDoc("query", wIDA, tPB);
                      
                      int countUp = 0;
                      //System.out.println("___________nope3______________");
                      while(countUp < 100){
                        
                        myLearning.updateProduced ("query");
                        myLearning.updateTopicProbs("query");
                        countUp++;
                      }
                      DocSignature getQueryDoc = myLearning.getSignature("query");
                      ArrayList <DataWrapper <MultiDimPoint, String>> similarDocs = treeLearning.findKClosest( new MultiDimPoint(getQueryDoc.getTopicProbs()),numResultsReturnFromQuery);
                      int numDocsHere1 = similarDocs.size();
                      ArrayList<String> docFeeder = new ArrayList<String>();
                      
                      for(int i = 0; i < numDocsHere1; i++){
                        docFeeder.add(similarDocs.get(i).getData());
                        //System.out.println("___________nope1______________");
                      }
                      ShowFiles popUp = new ShowFiles(docFeeder);
                      System.out.println(homeDirectory);
                    }catch(IOException E){}
                    
                  }
                  if(selection == 2){//(2)Set the number of query results to return
                    System.out.println("How many results to return?");
                    numResultsReturnFromQuery = input.nextInt();
                    
                  }
                  if(selection == 3){//t(3)Stop searching the database:"
                    selection = -1;//VERY IMPORTANT DO NOT CHANGE, SINCE 3 AND 3 FOR SELECTION'S IT WHILE STOP THE PROGRAM IF 3 MAKE IT TO OUTER LOOP
                    break;///breaks out whileXXY
                  }
                }
              }//// End of this(2) Use the learned model to search the corpus
              if(selection == 3){/////(3)Exit:
                break;
              }////////////////end of this (3)Exit:
            }//end of second while
            
            
            break;
            
          }
          
        }
        
        //this is correct
        if (selection == 2){//_____________________________________Second Half_______________________________________________________________
          JFileChooser fc = new JFileChooser ();
          fc.setDialogTitle ("Where the corpus is located? ");
          fc.setFileSelectionMode (JFileChooser.FILES_ONLY);
          fc.showOpenDialog(null);
          File result = fc.getSelectedFile ();
          try{
            String homeDirectory = result.getCanonicalPath();
            System.out.println(homeDirectory);
            //}catch(IOException E){}
            //___________________________________________________________
            SaveComponents saved = null;
            
            
            FileInputStream fileIn = new FileInputStream(homeDirectory);
            ObjectInputStream in = new ObjectInputStream(fileIn);
//            secondWordDic = (SaveComponents) in.readObject();
//            secondOtherWordDicMap = (SaveComponents) in.readObject();
//            saved = (SaveComponents) in.readObject();
            secondWordDic = (ArrayList<String>) in.readObject();
            secondOtherWordDicMap = (Map<String, Integer>) in.readObject();
            secondNumberWords = (int) in.readObject();
            secondNumberTopics = (int) in.readObject();
            double secondAlpha = (double) in.readObject();
            double secondBeta = (double) in.readObject();
            secondLearnerString = (String) in.readObject();
            secSummationVal = (double) in.readObject();
            secwordCount = (WordMounter) in.readObject();
            IDoubleVector first = new SparseDoubleVector(secondNumberWords,secondAlpha); 
            IDoubleVector second = new SparseDoubleVector(secondNumberTopics, secondBeta);
            secLearner = new SCLDALearner(first, second, random);
            System.out.println("______________the name of the xml: " + secondLearnerString);
            secLearner.loadFromFile(secondLearnerString);
            System.out.println("_____________________");
            
            
            System.out.println("________________4354_______");
//            out.writeObject(wordDict);
//                      out.writeObject(wordDictMap);
//                      out.writeObject(numTopicToIndex);
//                      out.writeObject(numWordsToIndex);
//                      out.writeObject(valAlphaVec);
//                      out.writeObject(valBetaVec);
//                      out.writeObject(homeDirectory+"\\"+thisFileName);
//                      out.writeObject(summationVal);
            
            in.close();
            fileIn.close();
          }catch(IOException i)
          {
            i.printStackTrace();
            return;
          }catch(ClassNotFoundException c)
          {
            System.out.println(" class not found");
            c.printStackTrace();
            return;
          }
//           fc = new JFileChooser ();
//          fc.setDialogTitle ("Where the XML is located? ");
//          fc.setFileSelectionMode (JFileChooser.FILES_ONLY);
//          fc.showOpenDialog(null);
//           result = fc.getSelectedFile ();
//          try{
//            String homeDirectory = result.getCanonicalPath();
//          secLearner.loadFromFile(homeDirectory);
//          }catch(IOException i)
//          {
//            i.printStackTrace();
//            return;
//          } 
          //___________________________________________________________
          
          
          //secondWordDic = saved.theWordDicArrayList;
          //secondOtherWordDicMap = saved.theOtherWordDic;
          //secondNumberWords = saved.thenumberOfWords;
          //int secondNumberTopics = saved.thenumberOfTopics;
          //secondAlpha = saved.theAlpha;
          //secondBeta = saved.theBeta;
          //secondLearnerString = saved.nameXMLFile;
          //secwordCount = saved.thewordCount;
          //secSummationVal = saved.thesummation;
          //secLearner = new SCLDALearner(secondAlpha, secondBeta, random);
          //secLearner.loadFromFile(secondLearnerString);
          while(true){
            while(true){//second while loop
              System.out.println("Do you want to:");
              System.out.println("\t(1)Update/view/save the learned model");
              System.out.println("\t(2) Use the learned model to search the corpus");
              System.out.println("\t(3)Exit:");
              
              selection = input.nextInt();
              //this is correct
              
                if(selection == 1){
                  while(true){
                  System.out.println("Do you want to:");
                  System.out.println("\t(1) Run some iterations of the learner");
                  System.out.println("\t(2) Print out the current topics");
                  System.out.println("\t(3) Write the current model to a file");
                  System.out.println("\t(4) Stop updating/viewing/saving the model:");
                  selection = input.nextInt();
                  if(selection == 1){
                    // selection 1 gets you this
                    System.out.println("How many iters do you want to run?");
                    //enter an integer and it gets you this printed out based on the number of n iterations
                    int numIterations = input.nextInt();
                    //if you select 1 the bottom shows up
                    
                    for(int i = 0; i < numIterations; i++){
                      
                      System.out.println("Running iter "+i+" ...");
                      System.out.println("\tUpdating produced... ");
                      secLearner.updateProduced();
                      System.out.println("\tUpdating topic_probs... ");
                      secLearner.updateTopicProbs();
                      System.out.println("\tUpdating word_probs... ");
                      secLearner.updateWordProbs();
                    }
                  }
                  if(selection == 2){
                    // selection 2 gets you this from Print out the current topics
                    System.out.println("How many of the top words do you want for each topic?");
                    //lets say that you input 5, you get 0 to n topics that five words long
                    int numTopWordsEachTopic = input.nextInt();
                    int ithTopic = 0;
                    int numWordsInDict = secondNumberWords;
                    while(ithTopic < secondNumberTopics){
                      AVLTopKMachine jmachine = new AVLTopKMachine(numTopWordsEachTopic);
                      try{
                        
                        
                        for(int j = 0; j < secondNumberWords; j++){
                          
                          double pt = secwordCount.giveMeVal(secondWordDic.get(j))/secSummationVal;
                          double pc = secLearner.getWordProbs().getEntry(ithTopic, j);
                          double formula = pt*Math.log(pt/pc) + (1 - pc)*Math.log((1-pt)/(1-pc));
                          
                          jmachine.insert(formula, secondWordDic.get(j));
                          
                        }
                      }catch(OutOfBoundsException E){
                        System.out.println("Trouble inserting into the machine.");
                      }
                      
                      ArrayList topicWords = jmachine.getTopK();
                      int numWords = topicWords.size();
                      String topicString = " ";
                      for(int x = 0; x < numWords; x++){
                        topicString += " " + topicWords.get(x);
                      }
                      System.out.println("topic " +ithTopic+ topicString);
                      ithTopic++;
                    }
                    System.out.print("______________");
                    selection = -11;
                    break;
                    
                  }//----------------------
                  //THIS IS CORRECT
                  if(selection == 3){
                    // selection 3 gets you this from (3) Write the current model to a file"
                    System.out.println("What diretory should I write to");
                    //select the file location in the window pane, after that
                    JFileChooser fcNew = new JFileChooser ();
                    fcNew.setDialogTitle ("Select the directory location.");
                    fcNew.setFileSelectionMode (JFileChooser.DIRECTORIES_ONLY);
                    fcNew.showOpenDialog(null);
                    File resultNew = fcNew.getSelectedFile ();
                    try{
                      String homeDirectory = resultNew.getCanonicalPath(); //WE NEED TO GET THIS TO WORK AAHAHAHAHAHAHHAHAHAH
                      System.out.println(homeDirectory);
                      System.out.println("What is the name of the XML file where the topic model should be written?");
                      String thisFileXName = input.next();
                      secLearner.writeToFile(homeDirectory+"\\"+thisFileXName);
                      
                      System.out.println("And where should the binary file with the other meta-data be written?");
                      String thisFileName = input.next();
                      FileOutputStream fileOut = new FileOutputStream(homeDirectory+"\\"+thisFileName);
                      ObjectOutputStream out = new ObjectOutputStream(fileOut);
                      SaveComponents saveForLater = new SaveComponents();
//                      saveForLater.theWordDicArrayList = secondWordDic;
//                      saveForLater.theOtherWordDic = secondOtherWordDicMap;
//                      saveForLater.thenumberOfWords = secondNumberWords;
//                      saveForLater.thenumberOfTopics = secondNumberTopics;
//                      //saveForLater.theAlpha = secondAlpha;
//                      //saveForLater.theBeta = secondBeta;
//                      saveForLater.nameXMLFile = homeDirectory+"\\"+"+thisFileName";
//                      saveForLater.thesummation = secSummationVal;
//                      saveForLater.thewordCount = wordCount;
                      //out.writeObject(saveForLater);
                      out.writeObject(secondWordDic);
                      out.writeObject(secondOtherWordDicMap);
                      out.writeObject(secondNumberWords);
                      out.writeObject(secondNumberTopics);
                      out.writeObject(secondAlpha);
                      out.writeObject(secondBeta);
                      out.writeObject(homeDirectory+"\\"+thisFileXName);
                      out.writeObject(secSummationVal);
                      out.writeObject(wordCount);
                      
                      out.close();
                      fileOut.close();
                      break;
                      
                    }catch(IOException E){}
                  }
                  if(selection == 4){
                    break;
                  }
                }
              }
              if(selection == 2){//(2) Use the learned model to search the corpus
                System.out.println("OK. Building an M-Tree index...");
                SCMTree treeLearning = new SCMTree(3,4);
                Iterator<DocSignature> allDocuments = secLearner.iterator();
                while(allDocuments.hasNext()){
                  DocSignature singleDocument = allDocuments.next();
                  treeLearning.insert(new MultiDimPoint(singleDocument.getTopicProbs()),singleDocument.getFName());
                }
                System.out.println("Done buidling an M-Tree index.");
                while(true){
                  System.out.println("Do you want to:");
                  System.out.println("\t(1)Search the database");
                  System.out.println("\t(2) Set the number of query results to return");
                  System.out.println("\t(3)Stop searching the database:");
                  selection = input.nextInt();
                  if(selection == 1){//(1)Search the database"
                    try{
                      WordMounter singleCounter = new WordMounter();
                      JFileChooser fcNew = new JFileChooser ();
                      fcNew.setDialogTitle ("What file do you want to use to find related documents?");
                      fcNew.setFileSelectionMode (JFileChooser.FILES_ONLY);
                      fcNew.showOpenDialog(null);
                      File resultNew = fcNew.getSelectedFile ();
                      String homeDirectory = resultNew.getCanonicalPath(); //WE NEED TO GET THIS TO WORK AAHAHAHAHAHAHHAHAHAH
                      SubstringGetter windowToFile = new SubstringGetter(homeDirectory);
                      readSingleLine2(windowToFile.getSubstring());
                      Dirichlet myDirichlet = new Dirichlet (random, new DirichletParam (new SparseDoubleVector (secondNumberTopics, 1), 10e-99, 1000));
                      tPB = myDirichlet.getNext();
                      //System.out.println("___________nope4______________");
                      secLearner.loadUpAnotherDoc("query", wIDA, tPB);
                      
                      int countUp = 0;
                      //System.out.println("___________nope3______________");
                      while(countUp < 100){
                        
                        secLearner.updateProduced ("query");
                        secLearner.updateTopicProbs("query");
                        countUp++;
                      }
                      DocSignature getQueryDoc = secLearner.getSignature("query");
                      System.out.println("How many results to return?");
                    int qReturn = input.nextInt();
                      ArrayList <DataWrapper <MultiDimPoint, String>> similarDocs = treeLearning.findKClosest( new MultiDimPoint(getQueryDoc.getTopicProbs()),qReturn);
                      int numDocsHere1 = similarDocs.size();
                      ArrayList<String> docFeeder = new ArrayList<String>();
                      
                      for(int i = 0; i < numDocsHere1; i++){
                        docFeeder.add(similarDocs.get(i).getData());
                        //System.out.println("___________nope1______________");
                      }
                      ShowFiles popUp = new ShowFiles(docFeeder);
                      System.out.println(homeDirectory);
                    }catch(IOException E){}
                  }
                  if(selection == 2){//(2)Set the number of query results to return
                    System.out.println("How many results to return?");
                    int qReturn = input.nextInt();
                   
                    
                  }
                  if(selection == 3){
                    break;
                  }
                }
              }// End of this(2) Use the learned model to search the corpus
              if(selection == 3){//(3)Exit:
                break;
              }//end of this (3)Exit:
            }//end of second while
          }
        }
        break;
        
      }//end of LeveL 1B
      System.out.println("Bad input! Select 1 or 2.");
      System.out.println("\t(1) Index a new corpus");
      System.out.println("\t(2) Use/manage an already-index one:");   
    }
  }//end of LeveL 0
  
  
  private void openFile(String fileName) {      
    try {
      file = new FileReader(fileName);
      reader = new BufferedReader(file);
    } catch (Exception e) {
      System.err.format("Problem opening %s file\n", fileName);
      System.err.println(e);
      e.printStackTrace();
      //fail();
    }
  }
  
  private void readWords(WordMounter counter, int numWords) {
    try {
      int numStopWords = stopWords.length;
      HashSet<String> notWords = new HashSet<String>();
      for(int y = 0; y < numStopWords; y++){
        notWords.add(stopWords[y]);
      }
      
      String textLine = reader.readLine();
      
      countChris(textLine, counter);
      
    } catch (Exception e) {
      System.err.println("Problem reading____ file");
      System.err.println(e);
      e.printStackTrace();
      
    }
  }
  
  public int countChris (String inMe, WordMounter counter) {
    Pattern pattern = Pattern.compile ("[a-zA-Z]*");
    Matcher matcher = pattern.matcher (inMe);
    int returnVal = 0;
    for (; matcher.find (); returnVal++) {
      counter.insert(matcher.group().toLowerCase());
      
    }
    return returnVal;
  }
  
  public IDoubleVector countWordInDocument (String inMe, IDoubleVector inputVec) {
    Pattern pattern = Pattern.compile ("[a-zA-Z]*");
    Matcher matcher = pattern.matcher (inMe);
    int returnVal = 0;
    for (; matcher.find (); returnVal++) {
      try{
        //System.out.println("Start");
        if(wordDictMap.containsKey(matcher.group().toLowerCase()) == true){
          int numIndex = (int) wordDictMap.get(matcher.group().toLowerCase());
          //System.out.println("End");
          inputVec.setItem(numIndex, inputVec.getItem(numIndex)+1.0);
          //System.out.println("Done");
        }
      }catch(OutOfBoundsException E){}
    }
    return inputVec;
  }
  
  private void closeFiles() {
    try {
      if (file != null) {
        file.close();
        file = null;
      }
      if (reader != null) {
        reader.close();
        reader = null;
      }
    } catch (Exception e) {
      System.err.println("Problem closing file");
      System.err.println(e);
      e.printStackTrace();
    }
  }
  
  private void readSingleLine(String line) {
    try {
      int numStopWords = stopWords.length;
      HashSet<String> notWords = new HashSet<String>();
      for(int y = 0; y < numStopWords; y++){
        notWords.add(stopWords[y]);
      }
      
      String textLine = line;
      
      String[] wordsInLine = textLine.split("\\W");
      int numWordsLine = wordsInLine.length;
      wIDA = new SparseDoubleVector(wordDict.size(),0.0);
      
      for(int j = 0; j < numWordsLine; j++){
        
        //System.out.println(wordsInLine[j]);
        if(notWords.contains(wordsInLine[j]) != true ){
          if (wordsInLine[j] == null) {
            return;
          }
          
          int incrementWord = wordDict.indexOf(wordsInLine[j].toLowerCase());
          wIDA.setItem(incrementWord, wIDA.getItem(incrementWord)+1.0);
        }
      }
      //}
    } catch (Exception e) {
      System.err.println("Problem reading____ file");
      System.err.println(e);
      e.printStackTrace();
      //fail();
    }
  }
  
  private void readSingleLine2(String line) {
    try {
      int numStopWords = stopWords.length;
      HashSet<String> notWords = new HashSet<String>();
      for(int y = 0; y < numStopWords; y++){
        notWords.add(stopWords[y]);
      }
      
      String textLine = line;
      
      String[] wordsInLine = textLine.split("\\W");
      int numWordsLine = wordsInLine.length;
      wIDA = new SparseDoubleVector(secondWordDic.size(),0.0);
      
      for(int j = 0; j < numWordsLine; j++){
        
        //System.out.println(wordsInLine[j]);
        if(notWords.contains(wordsInLine[j]) != true ){
          if (wordsInLine[j] == null) {
            return;
          }
          
          int incrementWord = secondWordDic.indexOf(wordsInLine[j].toLowerCase());
          wIDA.setItem(incrementWord, wIDA.getItem(incrementWord)+1.0);
        }
      }
      //}
    } catch (Exception e) {
      System.err.println("Problem reading____ file");
      System.err.println(e);
      e.printStackTrace();
      //fail();
    }
  }
  
}


