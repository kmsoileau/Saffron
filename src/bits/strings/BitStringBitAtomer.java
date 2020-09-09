/**
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 16, 2018
 */
package bits.strings;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bits.strings.exceptions.BitStringBitAtomerException;

public class BitStringBitAtomer extends Problem implements IProblem
{
	public BitStringBitAtomer(IBitString[] bitStrings, int pos, IBitString atom) throws Exception
	{
		if (bitStrings.length == 0)
			throw (new BitStringBitAtomerException(
					"IBitString or IBooleanVariable array of zero length was passed to constructor."));
		if (atom == null)
			throw (new BitStringBitAtomerException("A null conditionalResult variable was passed to constructor."));
		if (atom.size() == 0)
			throw (new BitStringBitAtomerException("A conditionalResult of size zero was passed to constructor."));

		int stagingIndex = 0;
		IProblem[] stagingArray = new IProblem[bitStrings.length + 2];

		IBitString[] subTotal = new IBitString[bitStrings.length];
		for (int i = 0; i < bitStrings.length; i++)
			subTotal[i] = new BitString(bitStrings[0].size());

		stagingArray[stagingIndex++] = stagingArray[stagingIndex++] = new Conjunction(
				// if bitStrings[0][pos] then subTotal[0]=bitStrings[0]
				new Disjunction(new BitFixer(bitStrings[0].getBooleanVariable(pos), false),
						new BitStringEqualizer(subTotal[0], bitStrings[0])),
				// if !bitStrings[0][pos] then subTotal[0]=!bitStrings[0]
				new Disjunction(new BitFixer(bitStrings[0].getBooleanVariable(pos), true),
						new BitStringNoter(subTotal[0], bitStrings[0])));

		for (int i = 1; i < bitStrings.length; i++)
		{
			IBitString notbits = new BitString(bitStrings[i].size());
			stagingArray[stagingIndex++] = new Conjunction(
					// if bitStrings[i][pos] then subTotal[i]=subTotal[i-1] &
					// bitStrings[i]
					new Disjunction(new BitFixer(bitStrings[i].getBooleanVariable(pos), false),
							new BitStringAnder(subTotal[i - 1], bitStrings[i], subTotal[i])),
					// if !bitStrings[i][pos] then subTotal[i]=subTotal[i-1] &
					// !bitStrings[i]
					new Disjunction(new BitFixer(bitStrings[i].getBooleanVariable(pos), true),
							new BitStringAnder(subTotal[i - 1], notbits, subTotal[i])),
					new BitStringNoter(bitStrings[i], notbits));
		}

		stagingArray[stagingIndex++] = new BitStringEqualizer(atom, subTotal[bitStrings.length - 1]);

		this.setClauses(new Conjunction(stagingArray).getClauses());
	}
}
