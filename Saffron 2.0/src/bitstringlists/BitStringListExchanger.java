package bitstringlists;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import bitstringlists.exceptions.BitStringListException;
import bitstrings.BitStringEqualizer;

/**
 * <p>
 * Title: BitStringListExchanger
 * </p>
 * <p>
 * Description: If the IProblem p=new BitStringListExchanger(A, B, m, n), then p
 * will be satisfied when A and B are equal as lists except possibly in
 * positions m and n. For these data, the member of A with index m will equal
 * the member of B with index n, and the member of B with index m will equal the
 * member of A with index n. In case m=n, p will be satisfied when A and B are
 * equal as lists.
 * </p>
 * <p>
 * Copyright (c) 2005
 * </p>
 * <p>
 * Company: Positronic Software
 * </p>
 * 
 * @author Kerry Michael Soileau
 * @version 1.0
 */
public class BitStringListExchanger extends Problem implements IProblem
{
	public BitStringListExchanger(IBitStringList A, IBitStringList B, int m,
			int n) throws Exception
	{
		if (A == null)
			throw new BitStringListException(
					"Passed a null IBitStringList to constructor.");
		if (B == null)
			throw new BitStringListException(
					"Passed a null IBitStringList to constructor.");

		IProblem problem = null;
		for (int i = 0; i < A.size(); i++)
			if (i != m && i != n)
				problem = new Conjunction(problem, new BitStringEqualizer(
						A.getBitString(i), B.getBitString(i)));
		problem = new Conjunction(problem, new BitStringEqualizer(
				A.getBitString(m), B.getBitString(n)));
		problem = new Conjunction(problem, new BitStringEqualizer(
				A.getBitString(n), B.getBitString(m)));

		this.setClauses(problem.getClauses());
	}
}