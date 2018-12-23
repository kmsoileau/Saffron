package naturalnumbertables;

import naturalnumbers.NaturalNumberFixer;
import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import exceptions.naturalnumbertables.NaturalNumberTableException;
import exceptions.naturalnumbertables.NaturalNumberTableFixerException;

public class NaturalNumberTableFixer extends Problem implements IProblem
{
	public NaturalNumberTableFixer(INaturalNumberTable A, long[][] data)
			throws NaturalNumberTableException, Exception
	{
		if (A == null)
			throw new NaturalNumberTableFixerException(
					"Null INaturalNumberTable passed to a constructor.");
		if (data == null)
			throw new NaturalNumberTableFixerException(
					"Null long[][] passed to a constructor.");
		if (data == null || data.length != A.getNumberOfRows()
				|| data[0].length != A.getNumberOfColumns())
			throw new NaturalNumberTableFixerException(
					"Size mismatch between long[][] and INaturalNumberTable passed to a constructor.");
		IProblem result = null;
		for (int i = 0; i < A.getNumberOfRows(); i++)
			for (int j = 0; j < A.getNumberOfColumns(); j++)
				result = new Conjunction(result, new NaturalNumberFixer(
						A.getNaturalNumber(i, j), data[i][j]));

		this.setClauses(result.getClauses());
	}
}
