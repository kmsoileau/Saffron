package TBS;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberAdder;
import naturalnumbers.NaturalNumberBitMultiply;
import naturalnumbers.NaturalNumberEqualizer;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;

public class ConditionalOrer
{
	public ConditionalOrer(IBitString[] bitStrings,
			IBooleanVariable[] membership, IBitString conditionalResult)
	{
		if (bitStrings.length == 0 || membership.length == 0)
			throw (new ConditionalOrerException(
					"IBitString or IBooleanVariable array of zero length was passed to constructor."));
		if (bitStrings.length != membership.length)
			throw (new ConditionalOrerException(
					"IBitString or IBooleanVariable arrays of different lengths were passed to constructor."));
		if (conditionalResult == null)
			throw (new ConditionalOrerException(
					"A null conditionalResult variable was passed to constructor."));
		if (conditionalResult.size() == 0)
			throw (new ConditionalOrerException(
					"A conditionalResult of size zero was passed to constructor."));

		IBitString[] subAnswer = new IBitString[bitStrings.length];
		IBitString[] subTotal = new IBitString[bitStrings.length];
		subTotal[0] = new BitString();
		subAnswer[0] = new BitString();

		IProblem[] stagingArray = new IProblem[2 * bitStrings.length + 1];
		int stagingIndex = 0;
		stagingArray[stagingIndex++] = new NaturalNumberBitMultiply(
				membership[0], bitStrings[0], subAnswer[0]);
		stagingArray[stagingIndex++] = new NaturalNumberEqualizer(subTotal[0],
				subAnswer[0]);
		for (int i = 1; i < bitStrings.length; i++)
		{
			// System.out.println((System.currentTimeMillis()-startTimeMillis)/1000.+":"+"\t\t\t\t\t"+i);
			subAnswer[i] = new NaturalNumber();
			subTotal[i] = new NaturalNumber();
			stagingArray[stagingIndex++] = new NaturalNumberBitMultiply(
					membership[i], bitStrings[i], subAnswer[i]);
			stagingArray[stagingIndex++] = new NaturalNumberAdder(
					subTotal[i - 1], subAnswer[i], subTotal[i]);
			// System.out.println(stagingIndex);
		}
		// System.out.println((System.currentTimeMillis()-startTimeMillis)/1000.+":"+"\t\t\t\\t\tAdding
		// NaturalNumberEqualizer");
		stagingArray[stagingIndex++] = new NaturalNumberEqualizer(
				subTotal[bitStrings.length - 1], conditionalResult);
		// System.out.println((System.currentTimeMillis()-startTimeMillis)/1000.+":"+"\t\t\t\t\tComputing
		// staging array");
		IProblem problem = new Conjunction(stagingArray);
		this.setClauses(problem.getClauses());

		// System.out.println((System.currentTimeMillis()-startTimeMillis)/1000.+":"+"\t\t\t\tFinishing
		// ConditionalAdder...");
	}
}