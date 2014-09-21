
public class FindStuff {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    String a = "Here is one super cool string";
	    String b = "Here is another not so neat one";
	    String c = "It is really a lot of fun to compute the LCS";
	    String d = "But not so fun that I'd want to do it every day";
	    int lengthMax = Math.min(a.length(), b.length());
	    char[] A = new char[lengthMax];
	    for(int i = 0; i < lengthMax; i++){
	        if(a.charAt(i) == b.charAt(i)){
	            //b = (string) a.charAt(i);
	            A[i] = a.charAt(i);            
	        }
	    }
	    for(int j = 0; j < lengthMax; j++){
	        System.out.print(A[j]);
	    }
//System.out.println("printed");
	}

}
