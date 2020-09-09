package naturalnumbers;

import bits.BitAnder;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.lists.INaturalNumberList;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 3, 2005
 */
public class NaturalNumberBitMultiply extends Problem implements IProblem
{
	public NaturalNumberBitMultiply(IBooleanVariable b, INaturalNumber X, INaturalNumber Y) throws Exception
	{
		BitAnder[] ba = new BitAnder[NaturalNumber.getLength()];
		for (int i = 0; i < NaturalNumber.getLength(); i++)
			ba[i] = new BitAnder(b, X.getBooleanVariable(i), Y.getBooleanVariable(i));
		IProblem p = new Conjunction(ba);

		this.setClauses(p.getClauses());
	}

	public NaturalNumberBitMultiply(IBooleanVariable[] b, INaturalNumber[] X, INaturalNumber[] Y) throws Exception
	{
		IProblem[] stagingArray = new IProblem[X.length * NaturalNumber.getLength()];
		int stagingIndex = 0;
		for (int j = 0; j < X.length; j++)
			for (int i = 0; i < NaturalNumber.getLength(); i++)
				stagingArray[stagingIndex++] = new NaturalNumberBitMultiply(b[j], X[j], Y[j]);
		IProblem prob = new Conjunction(stagingArray);
		this.setClauses(prob.getClauses());
	}

	public NaturalNumberBitMultiply(IBooleanVariable[] b, INaturalNumberList X, INaturalNumberList Y) throws Exception
	{
		IProblem[] stagingArray = new IProblem[X.size() * NaturalNumber.getLength()];
		int stagingIndex = 0;
		for (int j = 0; j < X.size(); j++)
			for (int i = 0; i < NaturalNumber.getLength(); i++)
				stagingArray[stagingIndex++] = new NaturalNumberBitMultiply(b[j], X.getNaturalNumber(j),
						Y.getNaturalNumber(j));
		IProblem prob = new Conjunction(stagingArray);
		this.setClauses(prob.getClauses());
	}
}
