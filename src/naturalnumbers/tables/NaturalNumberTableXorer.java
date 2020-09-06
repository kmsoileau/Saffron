package naturalnumbers.tables;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberXorer;

public class NaturalNumberTableXorer extends Problem implements IProblem
{
	public NaturalNumberTableXorer(INaturalNumberTable X,
			INaturalNumberTable Y, INaturalNumberTable Z) throws Exception
	{
		if (!X.isSameSizeAs(Y) || !X.isSameSizeAs(Z))
			this.setClauses(Problem.unsolvableProblem().getClauses());
		else
		{
			IProblem[] p = new IProblem[X.getNumberOfRows()
					* X.getNumberOfColumns()];
			int count = 0;
			for (int i = 0; i < X.getNumberOfRows(); i++)
				for (int j = 0; j < X.getNumberOfColumns(); j++)
					p[count++] = new NaturalNumberXorer(
							X.getNaturalNumber(i, i), Y.getNaturalNumber(i, j),
							Z.getNaturalNumber(i, j));
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}
