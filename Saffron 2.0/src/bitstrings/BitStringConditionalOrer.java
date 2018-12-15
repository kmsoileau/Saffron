package bitstrings;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringEqualizer;
import bitstrings.BitStringFixer;
import bitstrings.BitStringOrer;
import exceptions.bitstringlists.BitStringConditionalOrerException;

/**
 * Given a collection C of IBitStrings each of size n, and an IBitString
 * targetBitString of size n, does there exist a subset of C such that the
 * bitwise OR of the members of the subset evaluate to targetBitString?
 */
public class BitStringConditionalOrer extends Problem implements IProblem
{
	public BitStringConditionalOrer(IBitString[] bitStrings,
			IBitString membership, IBitString targetBitString) throws Exception
	{
		if (bitStrings.length == 0 || membership.size() == 0)
			throw (new BitStringConditionalOrerException(
					"IBitString or IBooleanVariable array of zero length was passed to constructor."));
		if (bitStrings.length != membership.size())
			throw (new BitStringConditionalOrerException(
					"IBitString or IBooleanVariable arrays of different lengths were passed to constructor."));
		if (targetBitString == null)
			throw (new BitStringConditionalOrerException(
					"A null conditionalResult variable was passed to constructor."));
		if (targetBitString.size() == 0)
			throw (new BitStringConditionalOrerException(
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

		stagingArray[stagingIndex++] = new BitStringEqualizer(targetBitString,
				subTotal[bitStrings.length - 1]);

		IProblem problem = new Conjunction(stagingArray);
		this.setClauses(problem.getClauses());
	}
}