package data;

public class IllegalOverlapException extends RuntimeException{
	
	int[] overlappingClassIndices;
	
	public IllegalOverlapException(String text, int[] overlappingClassIndices){
		super(text);
		this.overlappingClassIndices = overlappingClassIndices;
	}

	public int[] getOverlappingClasses(){
		return overlappingClassIndices;
	}
}
