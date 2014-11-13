package utils;

@SuppressWarnings("serial")
public class ReseauException extends Exception {
	
	/** 
	* Crée une nouvelle instance de ReseauException 
	*/  
	public ReseauException() {
		
	} 
	
	/** 
	* Crée une nouvelle instance de ReseauException 
	* @param message Le message détaillant exception 
	*/  
	public ReseauException(String message) {  
		super(message); 
	} 
}
