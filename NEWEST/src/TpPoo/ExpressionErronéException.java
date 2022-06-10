package TpPoo;

public class ExpressionErronéException extends Exception {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double valeur;
	 ExpressionErronéException(double val) { valeur = val; }
    public String toString() { return valeur + " n existe pas "; }
    public String getMessage() { return valeur + " n existe pas"; }

	public ExpressionErronéException(String errorMessage) {
       super(errorMessage);
   }
}
