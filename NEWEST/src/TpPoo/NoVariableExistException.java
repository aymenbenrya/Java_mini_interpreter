package TpPoo;

public class NoVariableExistException extends Exception{
	 
	 private String variable;
     public NoVariableExistException(String i) { variable = i; }
     public String getMessage() { return "Erreur : Variable "+ variable + " non declaré"; }
	 
	private static final long serialVersionUID = 1L;

	//public NoVariableExistException(String errorMessage) {
	     //   super(errorMessage);
	  //  }

}
