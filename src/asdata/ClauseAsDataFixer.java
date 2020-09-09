package asdata;

import bits.BitFixer;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IClause;
import bits.IProblem;
import bits.Problem;

public class ClauseAsDataFixer extends Problem implements IProblem
{
	public ClauseAsDataFixer(IClauseAsData c, IClause clause) throws Exception
	{
		IProblem prob = null;

		label: for (IBooleanVariable bv : ClauseAsData.getVARIABLES())
		{
			IBooleanVariable occurs = c.occurrenceIndicator(bv);
			IBooleanVariable barred = c.barredIndicator(bv);

			for (int lit = 0; lit < clause.size(); lit++)
			{
				IBooleanLiteral v = clause.getLiteralAt(lit);
				if (v.getBooleanVariable().equals(bv))
				{
					prob = new Conjunction(prob, new BitFixer(occurs, true), new BitFixer(barred, v.isBarred()));
					continue label;
				}
			}
			prob = new Conjunction(prob, new BitFixer(occurs, false), new BitFixer(barred, false));
		}

		this.setClauses(prob.getClauses());
	}
}
