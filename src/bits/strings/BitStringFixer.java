package bits.strings;

import bits.BitFixer;
import bits.Conjunction;
import bits.EnhancedProblem;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import bits.strings.exceptions.BitStringFixerException;

/**
 * An extension of the <code>Problem</code> class which imposes a constraint on
 * an <code>IBitString</code>. For example, for <code>IBitString target</code>
 * and <code>boolean[] c</code>, the <code>Problem</code> instance
 * <code>p</code> defined by
 *
 * <p>
 * <code>Problem p=new BitStringFixer(target, c);</code>
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
 *
 * @version 1.0
 * @since 2005/04/21
 */
public class BitStringFixer extends Problem implements IProblem
{
	/**
	 * 
	 * @param target - IBitString
	 * @throws Exception _
	 * 
	 */
	public BitStringFixer(IBitString target) throws Exception
	{
		BitStringBitFixer[] bnnbf = new BitStringBitFixer[target.size()];
		for (int i = 0; i < bnnbf.length; i++)
			bnnbf[i] = new BitStringBitFixer(target, i, target.getBooleanVariable(i).getValue());

		IProblem p = new Conjunction(bnnbf);
		this.setClauses(p.getClauses());
	}

	/**
	 * 
	 * @param target - IBitString
	 * 
	 * @param c      - boolean[]
	 * @throws Exception _
	 */
	public BitStringFixer(IBitString target, boolean[] c) throws Exception
	{
		if (target.size() != c.length)
			this.setClauses(EnhancedProblem.unsolvableProblem().getClauses());
		else
		{
			BitStringBitFixer[] bnnbf = new BitStringBitFixer[c.length];
			for (int i = 0; i < bnnbf.length; i++)
				bnnbf[i] = new BitStringBitFixer(target, i, c[i]);

			IProblem p = new Conjunction(bnnbf);
			this.setClauses(p.getClauses());
		}
	}

	/**
	 * 
	 * @param target - IBitString
	 * 
	 * @param data   - IBitString
	 * @throws Exception _
	 */
	public BitStringFixer(IBitString target, IBitString data) throws Exception
	{
		this(target, data.getBVArray());
	}

	/**
	 * 
	 * @param target - IBitString
	 * 
	 * @param data   - IBooleanVariable[]
	 * @throws Exception _
	 */
	public BitStringFixer(IBitString target, IBooleanVariable[] data) throws Exception
	{
		if (target.size() != data.length)
			this.setClauses(EnhancedProblem.unsolvableProblem().getClauses());
		else
		{
			BitStringBitFixer[] bnnbf = new BitStringBitFixer[data.length];
			for (int i = 0; i < bnnbf.length; i++)
				bnnbf[i] = new BitStringBitFixer(target, i, data[i].getValue());

			IProblem p = new Conjunction(bnnbf);
			this.setClauses(p.getClauses());
		}
	}

	/**
	 * 
	 * Ensures that the bits in positions given in the setBits array are set, and
	 * the rest of the bits are cleared. Out of range values in the setBits array
	 * are ignored.
	 * 
	 * @param target  - IBitString
	 * @param setBits - int[]
	 * @throws Exception _
	 * 
	 */
	public BitStringFixer(IBitString target, int[] setBits) throws Exception
	{
		for (int i = 0; i < target.size(); i++)
		{
			target.getBooleanVariable(i).setValue(false);
		}
		for (int i = 0; i < setBits.length; i++)
		{
			if (0 <= setBits[i] && setBits[i] < target.size())
				target.getBooleanVariable(setBits[i]).setValue(true);
		}

		IProblem[] p = new IProblem[target.size()];
		for (int i = 0; i < target.size(); i++)
		{
			p[i] = new BitFixer(target.getBooleanVariable(i));
		}

		this.setClauses(new Conjunction(p).getClauses());
	}

	public BitStringFixer(IBitString target, String s) throws Exception
	{
		int j = Math.min(s.length(), target.size());
		IProblem[] p = new Problem[j];

		for (int i = 0; i < j; i++)
		{
			if (s.charAt(i) != '0' && s.charAt(i) != '1')
				throw new BitStringFixerException("Attempted to fix an IBitString using improperly formatted data.");
			if (s.charAt(i) == '0')
				p[i] = new BitFixer(target.getBooleanVariable(i), false);
			if (s.charAt(i) == '1')
				p[i] = new BitFixer(target.getBooleanVariable(i), true);
		}
		IProblem pp = new Conjunction(p);
		this.setClauses(pp.getClauses());
	}

	public BitStringFixer(IBitString[] X) throws Exception
	{
		IProblem ret = Problem.newProblem();
		for (int i = 0; i < X.length; i++)
			ret = new Conjunction(ret, new BitStringFixer(X[i]));
		this.setClauses(ret.getClauses());
	}
}