package bitstringlists;

import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;

public class SetBasiser extends Problem implements IProblem
{
	public SetBasiser(IBitStringList C, IBitStringList B, IBitString[] included)
			throws Exception
	{
		IProblem[] p = new IProblem[C.size()];
		for (int i = 0; i < C.size(); i++)
		{
			p[i] = new BitStringConditionalOrer(B, included[i],
					C.getBitString(i));
		}
		IProblem problem = new Conjunction(p);
		this.setClauses(problem.getClauses());
	}
}
