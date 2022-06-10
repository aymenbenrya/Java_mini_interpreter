package TpPoo;

public class Interpreteur {
	private String cmd;
	static Boolean separateur(char i) {
		String separateurs=" +-()/*^=";
		if(separateurs.indexOf(i)<0) {
			return false;
		}
		else {
			return true;
		}
	}
	static String nospace(String str) {
		 char[] strArray = str.toCharArray();  
	        StringBuffer stringBuffer = new StringBuffer();  
	        for (int i = 0; i < strArray.length; i++) {  
	            if ((strArray[i] != ' ') && (strArray[i] != '\t')) {  
	                stringBuffer.append(strArray[i]);  
	            }  
	        }  
	        String noSpaceStr2 = stringBuffer.toString();  
	        return noSpaceStr2;  
	    }  
	public Interpreteur(String cmd) throws NoVariableExistException, CmdNonExistenteException {
		this.cmd=cmd ;
		int i=cmd.indexOf(" ");
		if(i>0) {
			String cam=cmd.substring(0,i);
			String reste=cmd.substring(i+1,cmd.length());
			reste=Interpreteur.nospace(reste);
			//System.out.println(reste);
			Let letcmd=null;
			if (cam.compareTo("let")==0) {
				try {
					 letcmd=new Let(reste);
					 letcmd.output();
				}
				catch(Exception e) {
					System.out.println("Erreur : commande non validé ");
				}
				
				//here you need an exception non existing variable
			}
			else if(cam.compareTo("print")==0) {
				Print printcmd=new Print(reste);
				printcmd.output();
				
			}
			else {
				
				//System.out.println("invalid commande");
				throw new CmdNonExistenteException(cam);
			}}
		else if(i==0){
			
			System.out.println("invalid commande ne mettre pas un espace dans le debut de cmd");
		}
		
		else  {
			if(cmd.compareTo("end")==0) {
				System.out.println("le program a été terminé");
				//System.out.println(Symbole.table_symboles);
				System.exit(0);
			}
			else {
				throw new CmdNonExistenteException("s");
				//System.out.println("invalide commande ");
			}
			
		}


	
	}

	
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	//Let x = sin(y+45)+cos(90)
	
}
