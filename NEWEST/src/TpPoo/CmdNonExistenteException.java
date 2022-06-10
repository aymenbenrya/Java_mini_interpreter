package TpPoo;

public class CmdNonExistenteException extends Exception{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String valeur;
	 //CmdNonExistenteException() { valeur = val; }
    public CmdNonExistenteException(String cam) {
		// TODO Auto-generated constructor stub
	}
	public String toString() { return  " Cmd n existe pas "; }
    public String getMessage() { return " Cmd n existe pas"; }

	
}