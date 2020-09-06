package bits.strings.lists;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;

public class BitStringListBasiser extends Problem implements IProblem
{
	public BitStringListBasiser(IBitStringList C, IBitStringList B,
			IBitStringList included) throws Exception
	{
		IProblem[] p = new IProblem[C.size()];
		for (int i = 0; i < C.size(); i++)
		{
			p[i] = new BitStringConditionalOrer(B, included.getBitString(i),
					C.getBitString(i));
		}
		IProblem problem = new Conjunction(p);
		this.setClauses(problem.getClauses());
	}
}
