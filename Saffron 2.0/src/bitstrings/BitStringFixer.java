package bitstrings;

import bits.BitFixer;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import exceptions.bitstrings.BitStringFixerException;

/**
 * An extension of the <code>Problem</code> class which imposes a constraint on
 * an <code>IBitString</code>. For example, for <code>IBitString target</code>
 * and <code>boolean[] c</code>,</code> the <code>Problem</code> instance
 * <code>p</code> defined by
 *
 * <p>
 * <tt>Problem p=new BitStringFixer(target, c);</code>
 * </p>
 *
 * is satisfied if and only if for each <code>i</code>, the <code>i</code>th bit
 * is equal to the corresponding bit <code>c[i]</code>.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2005/04/21
 */
public class BitStringFixer extends Problem implements IProblem
{
	public BitStringFixer(IBitString target) throws Exception
	{
		BitStringBitFixer[] bnnbf = new BitStringBitFixer[target.size()];
		for (int i = 0; i < bnnbf.length; i++)
			bnnbf[i] = new BitStringBitFixer(target, i, target
					.getBooleanVariable(i).getValue());

		IProblem p = new Conjunction(bnnbf);
		this.setClauses(p.getClauses());
	}

	public BitStringFixer(IBitString target, boolean[] c) throws Exception
	{
		if (target.size() != c.length)
			this.setClauses(Problem.unsolvableProblem().getClauses());
		else
		{
			BitStringBitFixer[] bnnbf = new BitStringBitFixer[c.length];
			for (int i = 0; i < bnnbf.length; i++)
				bnnbf[i] = new BitStringBitFixer(target, i, c[i]);

			IProblem p = new Conjunction(bnnbf);
			this.setClauses(p.getClauses());
		}
	}

	public BitStringFixer(IBitString target, IBitString data) throws Exception
	{
		this(target, data.getBVArray());
	}

	public BitStringFixer(IBitString target, IBooleanVariable[] data)
			throws Exception
	{
		if (target.size() != data.length)
			this.setClauses(Problem.unsolvableProblem().getClauses());
		else
		{
			BitStringBitFixer[] bnnbf = new BitStringBitFixer[data.length];
			for (int i = 0; i < bnnbf.length; i++)
				bnnbf[i] = new BitStringBitFixer(target, i, data[i].getValue());

			IProblem p = new Conjunction(bnnbf);
			this.setClauses(p.getClauses());
		}
	}

	public BitStringFixer(IBitString target, String s) throws Exception
	{
		int j = Math.min(s.length(), target.size());
		IProblem[] p = new Problem[j];

		for (int i = 0; i < j; i++)
		{
			if (s.charAt(i) != '0' && s.charAt(i) != '1')
				throw new BitStringFixerException(
						"Attempted to fix an IBitString using improperly formatted data.");
			if (s.charAt(i) == '0')
				p[i] = new BitFixer(target.getBooleanVariable(i), false);
			if (s.charAt(i) == '1')
				p[i] = new BitFixer(target.getBooleanVariable(i), true);
		}
		IProblem pp = new Conjunction(p);
		this.setClauses(pp.getClauses());
	}
}