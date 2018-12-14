package bitstringlists;

import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitStringFixer;

public class BitStringCover extends Problem implements IProblem
{
	public BitStringCover(IBitString[] C, IBitString membership)
			throws Exception
	{
		int cLength = C.length;

		IProblem[] q = new IProblem[C[0].size()];
		for (int i = 0; i < C[0].size(); i++)
		{
			IProblem[] p = new IProblem[cLength];
			for (int j = 0; j < cLength; j++)
			{
				p[j] = new Conjunction(new BitFixer(
						membership.getBooleanVariable(j), true), new BitFixer(
						C[j].getBooleanVariable(i), true));
			}
			q[i] = new Disjunction(p);
		}

		this.setClauses(new Conjunction(new BitStringFixer(C), new Conjunction(
				q)).getClauses());
	}

	public BitStringCover(IBitStringList C, IBitString membership)
			throws Exception
	{
		int cLength = C.size();

		IProblem[] q = new IProblem[C.getBitString(0).size()];
		for (int i = 0; i < C.getBitString(0).size(); i++)
		{
			IProblem[] p = new IProblem[cLength];
			for (int j = 0; j < cLength; j++)
			{
				p[j] = new Conjunction(new BitFixer(
						membership.getBooleanVariable(j), true), new BitFixer(
						C.getBitString(j).getBooleanVariable(i), true));
			}
			q[i] = new Disjunction(p);
		}

		this.setClauses(new Conjunction(new BitStringListFixer(C), new Conjunction(
				q)).getClauses());
	}

}
