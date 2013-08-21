package i.palmer.commons.jdbc;

public class ModelException extends RuntimeException {

	private static final long serialVersionUID = 737222748510204525L;
	
	public ModelException(String message){
		super(message);
	}
	
	public ModelException(Throwable cause){
		super(cause);
	}
	
	public ModelException(String message,Throwable cause){
		super(message,cause);
	}
	
}
