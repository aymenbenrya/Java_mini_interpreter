package TpPoo;

public class Let extends Linecmd  {
	private  Variable var;
	public Let(String cmd)  throws Exception{
		super.setCmd_elems(cmd);
		int i=cmd.indexOf("=");
		if (i>0) {
		String expressiontxt=cmd.substring(cmd.indexOf("=")+1,cmd.length());
		Expression expr= new Expression(expressiontxt);
		super.setExpression(expr);
		String variable_name=cmd.substring(0,cmd.indexOf("="));
		
		try { if(Variable.permited_var(variable_name)) {
			if(expr.exper_valid) {
			var=new Variable(variable_name,expr.evaluer());
			if(!Symbole.existe(variable_name)){
				Symbole.append_symbole(var);
			}
			else {
				Symbole.update_variable(variable_name, expr);
				
			}
			}
			else {
				//System.out.println("expression erroné");
				throw new ExpressionErronéException (variable_name);
			}
		}
		else {
			//exception variable n'exist pas
			throw new NoVariableExistException(variable_name);
			
		}}
		
		catch(ExpressionErronéException e) {
       	 System.out.println("Erreur : expression erroné " );
        }
		 catch (NoVariableExistException e ) {
			 System.out.println("Variable "+variable_name+" non permis voulez le changer");
		 }
		 }
		else {
			
			throw  new Exception() ;
		}
        
	}

		public Variable getVar() {
			return var;
		}
		
		public void setVar(Variable var) {
			this.var = var;
		}
		public void output() {
			if(var!=null) {
				System.out.println("ok");
			}
			}


}
