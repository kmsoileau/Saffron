package sets;

import bits.BitFixer;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import exceptions.sets.SetMembershipperException;

public class SetMembershipper extends Problem implements IProblem
{
	public SetMembershipper(Object object, Set set) throws Exception
	{
		if (set == null)
			throw new SetMembershipperException(
					"Null passed to constructor as Set parameter.");
		if (object == null)
			throw new SetMembershipperException(
					"Null passed to constructor as Object parameter.");
		IBooleanVariable bv = set.contains(object);
		if (bv == null)
			throw new SetMembershipperException("Object " + object
					+ " is not in the support of Set " + set);

		IProblem problem = new BitFixer(bv, true);
		this.setClauses(problem.getClauses());
	}
}