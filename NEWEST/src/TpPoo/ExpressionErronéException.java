package TpPoo;

public class ExpressionErron�Exception extends Exception {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double valeur;
	 ExpressionErron�Exception(double val) { valeur = val; }
    public String toString() { return valeur + " n existe pas "; }
    public String getMessage() { return valeur + " n existe pas"; }

	public ExpressionErron�Exception(String errorMessage) {
       super(errorMessage);
   }
}
