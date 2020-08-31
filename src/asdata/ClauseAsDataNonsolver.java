package asdata;

import bits.BitEqualizer;
import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class ClauseAsDataNonsolver extends Problem implements IProblem
{
	public ClauseAsDataNonsolver(IClauseAsData dataclause) throws Exception
	{
		IProblem prob = null;
		for (IBooleanVariable bv : ClauseAsData.getVARIABLES())
			prob = new Conjunction(prob, new Disjunction(new BitFixer(
					dataclause.occurrenceIndicator(bv), false),
					new BitEqualizer(bv, dataclause.barredIndicator(bv))));
		this.setClauses(prob.getClauses());
	}
}
