package bits.strings.lists;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import bits.strings.BitStringEqualizer;
import bits.strings.lists.exceptions.BitStringListException;

public class BitStringMover extends Problem implements IProblem
{

	public BitStringMover(IBitStringList A, IBitStringList B, int m, int n) throws Exception
	{
		if (A == null)
			throw new BitStringListException("Passed a null IBitStringList to constructor.");
		if (B == null)
			throw new BitStringListException("Passed a null IBitStringList to constructor.");

		IProblem problem = null;
		for (int i = 0; i < A.size(); i++)
			if (i != n)
				problem = new Conjunction(problem, new BitStringEqualizer(A.getBitString(i), B.getBitString(i)));
		problem = new Conjunction(problem, new BitStringEqualizer(A.getBitString(m), B.getBitString(n)));

		this.setClauses(problem.getClauses());
	}
}
