package sets;

import bits.BitFixer;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import exceptions.sets.SetNonMembershipperException;

public class SetNonMembershipper extends Problem implements IProblem
{
	public SetNonMembershipper(Object object, Set set) throws Exception
	{
		if (set == null)
			throw new SetNonMembershipperException(
					"Passed a null Set to constructor.");
		if (object == null)
			throw new SetNonMembershipperException(
					"Passed a null member Object to constructor.");
		IBooleanVariable bv = set.contains(object);
		if (bv == null)
			throw new SetNonMembershipperException("Object " + object
					+ " is not in the support of Set " + set);

		IProblem problem = new BitFixer(bv, false);
		this.setClauses(problem.getClauses());
	}
}