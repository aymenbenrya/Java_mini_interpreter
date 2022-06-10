package TpPoo;

import java.util.ArrayList;
import java.util.Arrays;

public class Symbole {
	private String nom;
	static ArrayList<Variable> table_symboles=new ArrayList<Variable>(1000);
	static ArrayList<String> functs=new ArrayList<>(Arrays.asList("sin", "cos", "tan", "abs", "log", "sqrt"));
	
	public static void update_variable(String nom,Expression expres) {
		int i=0;
		while(table_symboles.get(i).getNom().compareTo(nom)!=0 && i<table_symboles.size()) {
			i++;
		}
		if(i<table_symboles.size()) {
			table_symboles.get(i).setValue(expres.evaluer());
		}
	}
	public static Boolean is_function(String str) {
		Boolean result=false;
		if(Symbole.functs.contains(str)) {
			result=true;
		}
		return result;
	}
	public Symbole(String name) {
		this.nom=name;
	}
	static boolean existe(String nom) {
		Boolean result=false;
			for (Symbole i:Symbole.table_symboles) {
				if(i.nom.compareTo(nom)==0){
					result=true;
				}
			}
		return result;
		
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	static void append_symbole(Variable var) {
		if(var!=null  ) {
			table_symboles.add(var);
		}
		 
	}

}
