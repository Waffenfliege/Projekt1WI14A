package graphs;

public class EmpiricTupel
{
	/**
	 * @author Mathias Engmann
	 */

	private float summedQuote;
	private float width;
	
	/**
	 * Returns an object with the mathematical values needed to paint an element in the empiric distribution graph
	 * @param summedQuote the sum of the relative share of a given class and every class before that one
	 * @param lineWidth the widht of the class
	 * @author Mathias Engmann
	 */
	public EmpiricTupel(float summedQuote, float lineWidth)
	{
		this.summedQuote = summedQuote;
		this.width = lineWidth;
	}

	public float getSummedQuote()
	{
		return summedQuote;
	}

	public float getWidth()
	{
		return width;
	}

	
}
