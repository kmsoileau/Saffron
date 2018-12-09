package bitstrings;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bitstringlists.IBitStringList;
import exceptions.bitstrings.ConditionalOrerException;

public class ConditionalOrer extends Problem implements IProblem
{
	public ConditionalOrer(IBitString[] bitStrings, IBitString membership,
			IBitString conditionalResult) throws Exception
	{
		if (bitStrings.length == 0 || membership.size() == 0)
			throw (new ConditionalOrerException(
					"IBitString or IBooleanVariable array of zero length was passed to constructor."));
		if (bitStrings.length != membership.size())
			throw (new ConditionalOrerException(
					"IBitString or IBooleanVariable arrays of different lengths were passed to constructor."));
		if (conditionalResult == null)
			throw (new ConditionalOrerException(
					"A null conditionalResult variable was passed to constructor."));
		if (conditionalResult.size() == 0)
			throw (new ConditionalOrerException(
					"A conditionalResult of size zero was passed to constructor."));

		int stagingIndex = 0;
		IProblem[] stagingArray = new IProblem[bitStrings.length + 3];

		IBitString zeroBitString = new BitString(bitStrings[0].size());
		stagingArray[stagingIndex++] = new BitStringFixer(zeroBitString);

		IBitString[] subTotal = new IBitString[bitStrings.length];
		for (int i = 0; i < bitStrings.length; i++)
			subTotal[i] = new BitString(bitStrings[0].size());

		stagingArray[stagingIndex++] = stagingArray[stagingIndex++] = new Conjunction(
		// if membership[0] then subTotal[0]=bitStrings[0]
				new Disjunction(new BitFixer(membership.getBooleanVariable(0),
						false), new BitStringEqualizer(subTotal[0],
						bitStrings[0])),
				// if !membership[0] then subTotal[0]=zeroBitString
				new Disjunction(new BitFixer(membership.getBooleanVariable(0),
						true), new BitStringEqualizer(subTotal[0],
						zeroBitString)));

		for (int i = 1; i < bitStrings.length; i++)
		{
			stagingArray[stagingIndex++] = new Conjunction(
					// if membership[i] then subTotal[i]=subTotal[i-1] |
					// bitStrings[i]
					new Disjunction(new BitFixer(
							membership.getBooleanVariable(i), false),
							new BitStringOrer(subTotal[i - 1], bitStrings[i],
									subTotal[i])),
					// if !membership[i] then subTotal[i]=subTotal[i-1]
					new Disjunction(
							new BitFixer(membership.getBooleanVariable(i), true),
							new BitStringEqualizer(subTotal[i], subTotal[i - 1])));
		}

		stagingArray[stagingIndex++] = new BitStringEqualizer(
				conditionalResult, subTotal[bitStrings.length - 1]);

		IProblem problem = new Conjunction(stagingArray);
		this.setClauses(problem.getClauses());
	}

	public ConditionalOrer(IBitStringList bitStringList, IBitString membership,
			IBitString conditionalResult) throws Exception
	{
		this(bitStringList.toList().toArray(new IBitString[0]), membership,
			conditionalResult);
	}
}