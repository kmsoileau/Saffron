package bitstrings;

import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;

public class BitStringArrayBasisser extends Problem implements IProblem
{
	public BitStringArrayBasisser(IBitString[] C, IBitString[] B,
			IBitString[] included) throws Exception
	{
		int size = C.length;
		IProblem[] p = new IProblem[size];
		for (int i = 0; i < size; i++)
		{
			p[i] = new BitStringConditionalOrer(B, included[i], C[i]);
		}

		this.setClauses(new Conjunction(p).getClauses());
	}
}
