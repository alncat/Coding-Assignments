import java.util.UUID;

class ThisMyMain {
  
  public static void main (String [] args) {
	  String uiHost = args[0];
	  UUID uiKey = UUID.fromString(args[1]);
    ThisMyDocumentDatabase myProgram = new ThisMyDocumentDatabase ();
    myProgram.run (uiHost, uiKey);
  }
}
