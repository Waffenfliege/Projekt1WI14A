package data;

import java.util.ArrayList;

/**
 * Exception used to designate value overlaps for statistical classes
 * @author Mathias Engmann
 *
 */
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
