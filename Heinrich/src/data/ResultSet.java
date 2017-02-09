package data;

import logic.Quantile;

public class ResultSet {

	private float[] relativeOccurences;
	private float[] classMiddles;
	private int absoluteOccurences;
	private float median;
	private float arithmeticMiddle;
	private Quantile[] quantiles;
	float meanAbsoluteDeviation;
	float variance;
	float standardDeviation;
	float gini;
	
	int sampleSize;
	int classCount;
	
	public ResultSet(int classCount){
		relativeOccurences = new float[classCount];
		classMiddles = new float[classCount];
		quantiles = new Quantile[classCount];
	}

	public int getClassCount() {
		return classCount;
	}

	public void setClassCount(int classCount) {
		this.classCount = classCount;
	}

	public float[] getRelativeOccurences() {
		return relativeOccurences;
	}

	public void setRelativeOccurences(float[] relativeOccurences) {
		this.relativeOccurences = relativeOccurences;
	}

	public float[] getClassMiddles() {
		return classMiddles;
	}

	public void setClassMiddles(float[] classMiddles) {
		this.classMiddles = classMiddles;
	}

	public int getAbsoluteOccurences() {
		return absoluteOccurences;
	}

	public void setAbsoluteOccurences(int absoluteOccurences) {
		this.absoluteOccurences = absoluteOccurences;
	}

	public float getMedian() {
		return median;
	}

	public void setMedian(float median) {
		this.median = median;
	}

	public float getArithmeticMiddle() {
		return arithmeticMiddle;
	}

	public void setArithmeticMiddle(float arithmeticMiddle) {
		this.arithmeticMiddle = arithmeticMiddle;
	}

	public Quantile[] getQuantiles() {
		return quantiles;
	}

	public void setQuantiles(Quantile[] quantiles) {
		this.quantiles = quantiles;
	}

	public float getMeanAbsoluteDeviation() {
		return meanAbsoluteDeviation;
	}

	public void setMeanAbsoluteDeviation(float meanAbsoluteDeviation) {
		this.meanAbsoluteDeviation = meanAbsoluteDeviation;
	}

	public float getVariance() {
		return variance;
	}

	public void setVariance(float variance) {
		this.variance = variance;
	}

	public float getStandardDeviation() {
		return standardDeviation;
	}

	public void setStandardDeviation(float standardDeviation) {
		this.standardDeviation = standardDeviation;
	}

	public float getGini()
	{
		return gini;
	}

	public void setGini(float gini)
	{
		this.gini = gini;
	}

	public int getSampleSize() {
		return sampleSize;
	}

	public void setSampleSize(int sampleSize) {
		this.sampleSize = sampleSize;
	}
}
