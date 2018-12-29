package sets;

import bits.BitFixer;
import bits.Disjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import exceptions.sets.SetNonemptierException;

public class SetNonemptier extends Problem implements IProblem
{
	public SetNonemptier(Set X) throws Exception
	{
		if (X == null)
			throw new SetNonemptierException(
					"Null passed to constructor as Set parameter.");

		IProblem problem = Problem.newProblem();

		IBitString bs = X.getMembershipBitString();
		for (int i = 0; i < Set.getSetSupportSize(); i++)
		{
			problem = new Disjunction(problem, new BitFixer(
					bs.getBooleanVariable(i), true));
		}

		this.setClauses(problem.getClauses());
	}
}
