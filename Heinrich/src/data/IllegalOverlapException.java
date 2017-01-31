package data;

import java.util.ArrayList;

public class IllegalOverlapException extends RuntimeException{
	
	ArrayList<Integer> overlappingClassIndices;
	
	public IllegalOverlapException(String text, ArrayList<Integer> overlappingClassIndices){
		super(text);
		this.overlappingClassIndices = overlappingClassIndices;
	}

	public ArrayList<Integer> getOverlappingClasses(){
		return overlappingClassIndices;
	}
}
