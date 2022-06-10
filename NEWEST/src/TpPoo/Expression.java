package TpPoo;

import java.util.ArrayList;







public class Expression {
	private String express;
	private ArrayList<String> elements=new ArrayList<>(1000);
	private boolean par_ouvrant=true;
	private boolean par_fermant=true;
	boolean vars_existe=true;
	boolean exper_valid=true;
	private double valeur;
	public Expression(String exp) {
		express=exp;
		parenthese();//exception parentheses
		separer();
		try {
			replace_variables();	
		}
		catch(NoVariableExistException e) {
			System.out.println(e.getMessage());
		}
		evaluer();
		// print x*(y+4)-(log(10)
	}
	public void replace_variables() throws NoVariableExistException {
		
		for(int i=0;i<elements.size();i++) {
			boolean isNumeric = elements.get(i).chars().allMatch( Character::isDigit );
			if( !Symbole.is_function(elements.get(i)) && !isNumeric && ! Interpreteur.separateur(elements.get(i).charAt(0)) ){
				//System.out.println(elements.get(i)+" is variable");
						if(Symbole.existe(elements.get(i))) {
							//System.out.println(elements.get(i)+" exist");
							int k=0;
							while(Symbole.table_symboles.get(k).getNom().compareTo(elements.get(i))!=0 && k<Symbole.table_symboles.size()) {
								k++;
							}
						  //System.out.println("valeur" +Symbole.table_symboles.get(k).getValue());
							double val=Symbole.table_symboles.get(k).getValue();
							elements.set(i,Double.toString(val));
						}
						else {
							vars_existe=false;
							exper_valid=false;
							throw new NoVariableExistException(elements.get(i));
							//System.out.println(elements.get(i)+" n' exist pas ");
						
						}
				
	   }
		}
	}
	
	public String getExpress() {
		return express;
	}
	public void setExpress(String express) {
		this.express = express;
	}
	public double evaluer() {
		// TODO Auto-generated method stub
	    double result=0;
		if(parenthese() && vars_existe) {
           StringBuffer sb = new StringBuffer();
           for (String s : elements) {
              sb.append(s);
           }
           String str = sb.toString();
           try {
        	   result=Expression.eval(str); //we need to throw an exception her indicating that exprssion erroné
           }
         catch(Exception e) {
        	// System.out.println("expression erroné " );
        	 exper_valid=false;
         }            
		}
		else { 
			
		try{ 
			exper_valid=false;	
			if(!parenthese()) {
				if(!par_fermant) {
					
					throw new ParentheseFeramanteException ();
				}
				else {
					
					throw new ParentheseOuvranteException();
				}
				
			}
			else if(!vars_existe) {
				throw new NoVariableExistException(express);
			}
			return result;
		}
		
         catch (ParentheseFeramanteException e) {
        	System.out.println("Erreur : parenthèse fermante manquante");
        	System.out.println("hhhhh");
		 }
		 catch (ParentheseOuvranteException e ) {
			 System.out.println("Erreur : parenthèse ouvrante manquante");
		 }
		 catch (NoVariableExistException e ) {
			 //System.out.println("variable non declaré");
		 }
			}
		setValeur(result);
		return result;
	
		
	}
	public void separer() {
		int i=0,j=0;
		String element;
		while ( i<express.length()) {
			if(Interpreteur.separateur(express.charAt(i))) {
				if(i!=j) {
					element=express.substring(j, i);
					elements.add(element);
					j+=element.length()+1;
				}
				else {
					j++;
				}
				elements.add(String.valueOf(express.charAt(i)));
					
			}
			i++;
		}
		element=express.substring(j,express.length());
		if(element.compareTo("")!=0) {
			elements.add(element);
		}
		

	}
	public ArrayList<String> get_elements(){
		return elements;
	}
	public void set_elements(ArrayList<String> result){
		elements=result;
	}
	public Boolean parenthese() {
        Boolean result=true;
        int par=0;
        int i=0;
        while (i< elements.size() && par>=0) {
        	if(elements.get(i).compareTo("(")==0) {
        		par++;
        	}
        	else if (elements.get(i).compareTo(")")==0){
        		par--;
        	}
        	i++;
        }
        if (par<0 ) {
        	result=false;
        	par_ouvrant=false;
        }
        else if(par>0) {
        	result=false;
        	par_fermant=false;
        }
		
		return result;
	}
	public static double eval(final String str) {
	    return new Object() {
	        int pos = -1, ch;

	        void nextChar() {
	            ch = (++pos < str.length()) ? str.charAt(pos) : -1;
	        }

	        boolean eat(int charToEat) {
	            while (ch == ' ') nextChar();
	            if (ch == charToEat) {
	                nextChar();
	                return true;
	            }
	            return false;
	        }

	        double parse() {
	            nextChar();
	            double x = parseExpression();
	            if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
	            return x;
	        }

	        // Grammar:
	        // expression = term | expression `+` term | expression `-` term
	        // term = factor | term `*` factor | term `/` factor
	        // factor = `+` factor | `-` factor | `(` expression `)`
	        //        | number | functionName factor | factor `^` factor

	        double parseExpression() {
	            double x = parseTerm();
	            for (;;) {
	                if      (eat('+')) x += parseTerm(); // addition
	                else if (eat('-')) x -= parseTerm(); // subtraction
	                else return x;
	            }
	        }

	        double parseTerm() {
	            double x = parseFactor();
	            for (;;) {
	                if      (eat('*')) x *= parseFactor(); // multiplication
	                else if (eat('/')) x /= parseFactor(); // division
	                else return x;
	            }
	        }

	        double parseFactor() {
	            if (eat('+')) return parseFactor(); // unary plus
	            if (eat('-')) return -parseFactor(); // unary minus

	            double x;
	            int startPos = this.pos;
	            if (eat('(')) { // parentheses
	                x = parseExpression();
	                eat(')');
	            } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
	                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
	                x = Double.parseDouble(str.substring(startPos, this.pos));
	            } else if (ch >= 'a' && ch <= 'z') { // functions
	                while (ch >= 'a' && ch <= 'z') nextChar();
	                String func = str.substring(startPos, this.pos);
	                x = parseFactor();
	                if (func.equals("sqrt")) {
	                	if(x<0) {
	                		throw new RuntimeException("not defined" );
	                	}
	                	else {
	                		x = Math.sqrt(x);
	                	}
	                }
	                else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
	                else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
	                else if (func.equals("tan")) {
	                	if(x % 90 ==0) {
	                		throw new RuntimeException("not defined: tan(pi/2)" );
	                	}else {
	                		x = Math.tan(Math.toRadians(x));
	                	} 	
	                }
	                else if (func.equals("log")){
	                	if(x<=0){
	                		throw new RuntimeException("not defined" );
	                	}
	                	else {
	                		 x = Math.log10((x));
	                	}
	                }
	                else if (func.equals("abs")) x = Math.abs((x));
	                else throw new RuntimeException("Unknown function: " + func);
	            } else {
	                throw new RuntimeException("Unexpected: " + (char)ch);
	            }

	            if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

	            return x;
	        }
	    }.parse();
	}
	public boolean isPar_ouvrant() {
		return par_ouvrant;
	}
	public void setPar_ouvrant(boolean par_ouvrant) {
		this.par_ouvrant = par_ouvrant;
	}
	public boolean isPar_fermant() {
		return par_fermant;
	}
	public void setPar_fermant(boolean par_fermant) {
		this.par_fermant = par_fermant;
	}
	public double getValeur() {
		return valeur;
	}
	public void setValeur(double valeur) {
		this.valeur = valeur;
	}
	
}
