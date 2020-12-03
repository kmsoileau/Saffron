/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 13, 2019
 */
package integers;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import integers.exceptions.IntegerMultiplierException;
import integers.lists.IIntegerList;
import naturalnumbers.NaturalNumberMultiplier;

public class IntegerMultiplier extends Problem implements IProblem
{
	public IntegerMultiplier(IInteger X, IInteger Y, IInteger Z) throws Exception
	{
		INaturalNumber Xabs = X.getAbsValue();
		IBooleanVariable Xsign = X.getSign();

		INaturalNumber Yabs = Y.getAbsValue();
		IBooleanVariable Ysign = Y.getSign();

		INaturalNumber Zabs = Z.getAbsValue();
		IBooleanVariable Zsign = Z.getSign();

		// if ((Xsign=true && Ysign=true) or (Xsign=false && Ysign=false)) then
		// Zsign=true
		// if ((Xsign=false && Ysign=true) or (Xsign=true && Ysign=false)) then
		// Zsign=false
		// Zabs=Xabs * Yabs

		IProblem problem = new Conjunction(
				new Disjunction(
						new Conjunction(new Disjunction(new BitFixer(Xsign, false), new BitFixer(Ysign, false)),
								new Disjunction(new BitFixer(Xsign, true), new BitFixer(Ysign, true))),
						new BitFixer(Zsign, true)),
				new Disjunction(
						new Conjunction(new Disjunction(new BitFixer(Xsign, true), new BitFixer(Ysign, false)),
								new Disjunction(new BitFixer(Xsign, false), new BitFixer(Ysign, true))),
						new BitFixer(Zsign, false)),
				new NaturalNumberMultiplier(Xabs, Yabs, Zabs));

		this.setClauses(problem.getClauses());
	}

	public IntegerMultiplier(IInteger X, long i, IInteger Z)
	{
		try
		{
			IInteger C1 = new Integer();
			this.setClauses(new Conjunction(new IntegerFixer(C1, i), new IntegerMultiplier(X, C1, Z)).getClauses());

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public IntegerMultiplier(IInteger[] addend, IInteger Z)
	{
		try
		{
			int sz = addend.length;
			if (sz == 0)
				throw new IntegerMultiplierException("Empty IInteger array passed to constructor.");
			else
			{
				IProblem[] p = new IProblem[sz];

				IInteger[] Zz = new IInteger[sz - 1];
				Zz[0] = new Integer();
				p[0] = new IntegerEqualizer(addend[0], Zz[0]);
				for (int i = 1; i < sz - 1; i++)
				{
					Zz[i] = new Integer();
					p[i] = new IntegerMultiplier(Zz[i - 1], addend[i], Zz[i]);
				}
				p[sz - 1] = new IntegerMultiplier(Zz[sz - 2], addend[sz - 1], Z);

				IProblem prob = new Conjunction(p);
				this.setClauses(prob.getClauses());
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public IntegerMultiplier(IIntegerList addend, IInteger Z)
	{
		this(addend.getIntegerArray(), Z);
	}
}