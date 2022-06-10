package TpPoo;

import java.util.Scanner;

public class Tprgrm {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		while(true) {
			try {	
		System.out.println("Veuillez saisir votre command :");
		String cmd = sc.nextLine();
		Interpreteur inter=new Interpreteur(cmd);}
		catch (CmdNonExistenteException e) {
			System.out.println("Erreur : commande non validé ");
			}
		catch (NoVariableExistException e) {
		     System.out.println(e.getMessage());
		}
		}
		}
		
 }
	
