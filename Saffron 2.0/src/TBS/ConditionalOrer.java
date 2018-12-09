package TBS;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringEqualizer;
import bitstrings.BitStringFixer;
import bitstrings.BitStringOrer;

public class ConditionalOrer extends Problem implements IProblem
{
	public ConditionalOrer(IBitString[] bitStrings,
			IBooleanVariable[] membership, IBitString conditionalResult)
			throws Exception
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

		int stagingIndex = 0;
		IProblem[] stagingArray = new IProblem[bitStrings.length];

		IBitString zeroBitString = new BitString(bitStrings[0].size());
		stagingArray[stagingIndex++] = new BitStringFixer(zeroBitString);

		IBitString[] subTotal = new IBitString[bitStrings.length];
		
		stagingArray[stagingIndex++] = stagingArray[stagingIndex++] = new Conjunction(
				//if membership[0] then subTotal[0]=bitStrings[0]
				new Disjunction(
						new BitFixer(membership[0], false), new BitStringEqualizer(
						subTotal[0], bitStrings[0])), 
				//if !membership[0] then subTotal[0]=nullBitString
				new Disjunction(
						new BitFixer(membership[0], true), new BitStringEqualizer(
						subTotal[0], zeroBitString)));
		
		for (int i = 1; i < bitStrings.length; i++)
		{
			stagingArray[stagingIndex++] = new Conjunction(
					//if membership[i] then subTotal[i]=subTotal[i-1] | bitStrings[i]
					new Disjunction(
							new BitFixer(membership[i], false), new BitStringOrer(
							subTotal[i-1], bitStrings[i],subTotal[i])), 
					//if !membership[i] then subTotal[i]=subTotal[i-1]
					new Disjunction(
							new BitFixer(membership[i], true), new BitStringEqualizer(
							subTotal[i], subTotal[i-1])));
		}

		IProblem problem = new Conjunction(stagingArray);
		this.setClauses(problem.getClauses());
	}
}