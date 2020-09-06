package naturalnumbers.lists;

import java.util.ArrayList;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberEqualizer;
import naturalnumbers.lists.exceptions.NaturalNumberListExchangerException;

public class NaturalNumberListExchanger extends Problem implements IProblem
{
	public NaturalNumberListExchanger(INaturalNumberList A,
			INaturalNumberList B, int m, int n) throws Exception
	{
		if (A == null)
			throw new NaturalNumberListExchangerException(
					"Passed a null A to constructor.");
		if (B == null)
			throw new NaturalNumberListExchangerException(
					"Passed a null B to constructor.");

		ArrayList<IProblem> p = new ArrayList<IProblem>();
		for (int i = 0; i < A.size(); i++)
			if (i != m && i != n)
				p.add(new NaturalNumberEqualizer(A.getNaturalNumber(i), B
						.getNaturalNumber(i)));
		p.add(new NaturalNumberEqualizer(A.getNaturalNumber(m), B
				.getNaturalNumber(n)));
		p.add(new NaturalNumberEqualizer(A.getNaturalNumber(n), B
				.getNaturalNumber(m)));

		this.setClauses(new Conjunction(p).getClauses());
	}
}
