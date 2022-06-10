package TpPoo;

public abstract class Linecmd {
	private String cmd_elems;
	private Expression expression;
	public String getCmd_elems() {
		return cmd_elems;
	}
	
	public void setCmd_elems(String cmd_elems) {
		this.cmd_elems = cmd_elems;
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}



}
