package naturalnumberlists;

import naturalnumberlists.exceptions.NaturalNumberListExchangerException;
import naturalnumbers.NaturalNumberEqualizer;
import bits.Conjunction;
import bits.IProblem;
import bits.Problem;

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

		IProblem problem = null;
		for (int i = 0; i < A.size(); i++)
			if (i != m && i != n)
				problem = new Conjunction(problem, new NaturalNumberEqualizer(
						A.getNaturalNumber(i), B.getNaturalNumber(i)));
		problem = new Conjunction(problem, new NaturalNumberEqualizer(
				A.getNaturalNumber(m), B.getNaturalNumber(n)));
		problem = new Conjunction(problem, new NaturalNumberEqualizer(
				A.getNaturalNumber(n), B.getNaturalNumber(m)));

		this.setClauses(problem.getClauses());
	}
}
