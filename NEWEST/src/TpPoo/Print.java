package TpPoo;

import java.util.ArrayList;

public class Print extends Linecmd  {
	

	public Print(String cmd) {
		super.setCmd_elems(cmd);
		String expressiontxt=cmd;
		Expression expr= new Expression(expressiontxt);
		super.setExpression(expr);
	}
    public void output() {
    	double valeur=super.getExpression().getValeur();
    	if(super.getExpression().exper_valid) {
    		System.out.println("la valeur est "+valeur);
    	}
    	else {
    		System.out.println("expression erroné");
    	}
    	
	}

}
