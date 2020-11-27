/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 10, 2019
 */
package integers;

import java.util.ArrayList;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IProblem;
import bits.Problem;
import integers.exceptions.IntegerAdderException;
import integers.lists.IIntegerList;
import naturalnumbers.NaturalNumberAdder;
import naturalnumbers.NaturalNumberOrderer;
import naturalnumbers.NaturalNumberStrictOrderer;

public class IntegerAdder extends Problem implements IProblem
{
	public IntegerAdder(ArrayList<IInteger> addend, IInteger Z)
	{
		this(addend.toArray(new IInteger[0]), Z);
	}

	public IntegerAdder(IInteger X, IInteger Y, IInteger Z) throws Exception
	{
		IProblem[] p = new IProblem[6];

		IProblem pbfxf = new BitFixer(X.getSign(), false);
		IProblem pbfxt = new BitFixer(X.getSign(), true);
		IProblem pbfyf = new BitFixer(Y.getSign(), false);
		IProblem pbfyt = new BitFixer(Y.getSign(), true);
		IProblem pbfzf = new BitFixer(Z.getSign(), false);
		IProblem pbfzt = new BitFixer(Z.getSign(), true);

		IProblem addxyz = new NaturalNumberAdder(X.getAbsValue(), Y.getAbsValue(), Z.getAbsValue());

		p[0] = new Disjunction(pbfxf, pbfyf, new Conjunction(addxyz, pbfzt));
		p[1] = new Disjunction(pbfxf, pbfyt, new NaturalNumberStrictOrderer(X.getAbsValue(), Y.getAbsValue()),
				new Conjunction(new NaturalNumberAdder(Y.getAbsValue(), Z.getAbsValue(), X.getAbsValue()), pbfzt));
		p[2] = new Disjunction(pbfxf, pbfyt, new NaturalNumberOrderer(Y.getAbsValue(), X.getAbsValue()),
				new Conjunction(new NaturalNumberAdder(X.getAbsValue(), Z.getAbsValue(), Y.getAbsValue()), pbfzf));
		p[3] = new Disjunction(pbfxt, pbfyf, new NaturalNumberStrictOrderer(Y.getAbsValue(), X.getAbsValue()),
				new Conjunction(new NaturalNumberAdder(X.getAbsValue(), Z.getAbsValue(), Y.getAbsValue()), pbfzt));
		p[4] = new Disjunction(pbfxt, pbfyf, new NaturalNumberOrderer(X.getAbsValue(), Y.getAbsValue()),
				new Conjunction(new NaturalNumberAdder(Y.getAbsValue(), Z.getAbsValue(), X.getAbsValue()), pbfzf));
		p[5] = new Disjunction(pbfxt, pbfyt, new Conjunction(addxyz, pbfzf));

		this.setClauses(new Conjunction(p).getClauses());
	}

	public IntegerAdder(IInteger X, long i, IInteger Z)
	{
		try
		{
			IInteger C1 = new Integer();
			IProblem p3 = new Conjunction(new IntegerFixer(C1, i), new IntegerAdder(X, C1, Z));
			this.setClauses(p3.getClauses());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public IntegerAdder(IInteger[] addend, IInteger Z)
	{
		try
		{
			int sz = addend.length;
			if (sz == 0)
				throw new IntegerAdderException("Empty IInteger array passed to constructor.");
			else
			{
				IProblem[] p = new IProblem[sz];

				IInteger[] Zz = new IInteger[sz - 1];
				Zz[0] = new Integer();
				p[0] = new IntegerEqualizer(addend[0], Zz[0]);
				for (int i = 1; i < sz - 1; i++)
				{
					Zz[i] = new Integer();
					p[i] = new IntegerAdder(Zz[i - 1], addend[i], Zz[i]);
				}
				p[sz - 1] = new IntegerAdder(Zz[sz - 2], addend[sz - 1], Z);

				IProblem prob = new Conjunction(p);
				this.setClauses(prob.getClauses());
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public IntegerAdder(IIntegerList addend, IInteger Z)
	{
		this(addend.getIntegerArray(), Z);
	}
}
