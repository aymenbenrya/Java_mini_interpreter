package TpPoo;

import java.util.ArrayList;
import java.util.Arrays;

public class Variable extends Symbole{

private double value;

public static Boolean permited_var(String str) {
	Boolean result=true;
	String character_sspec_middle="*-/+)\" \" (!^[]%.-'&\\	";
	String character_sspec_first="0123456789*-/+)\" \" (!^[]%.-'&\\		";
	str.compareTo("let");
	String first_char=Character.toString(str.charAt(0));
	if(Symbole.is_function(str)) {
		result=false;
	} 
	else if(str.compareTo("let")==0 ||str.compareTo("print")==0 ){
		result=false;
	}
	else {
		if(character_sspec_first.contains(first_char)) {
			result=false;
		}
		else {
			for(char i:str.toCharArray()) {
				if(character_sspec_middle.contains(Character.toString(i))){
					result=false;
				}
			}
		}
		
	}
    if(str.isBlank()) { 
		result=false;
	}
	//System.out.println(result);
	return result;
}
public Variable(String nom,double val){
	super(nom);
	this.value=val;
	
}
public double getValue() {
	return value;
}

public void setValue(double value) {
	this.value = value;
}
}
