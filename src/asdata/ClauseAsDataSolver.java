package asdata;

import bits.BitFixer;
import bits.BitUnequalizer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class ClauseAsDataSolver extends Problem implements IProblem
{

	public ClauseAsDataSolver(IClauseAsData dataclauses) throws Exception
	{
		IProblem prob = null;
		for (IBooleanVariable bv : ClauseAsData.getVARIABLES())
			prob = new Disjunction(prob, new Conjunction(new BitFixer(dataclauses.occurrenceIndicator(bv), true),
					new BitUnequalizer(bv, dataclauses.barredIndicator(bv))));
		this.setClauses(prob.getClauses());
	}
}
