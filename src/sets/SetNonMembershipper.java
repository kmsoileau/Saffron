package sets;

import bits.BitFixer;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import sets.exceptions.SetNonMembershipperException;

public class SetNonMembershipper extends Problem implements IProblem
{
	public SetNonMembershipper(String object, Set set) throws Exception
	{
		if (set == null)
			throw new SetNonMembershipperException("Null passed to constructor as Set parameter.");
		if (object == null)
			throw new SetNonMembershipperException("Null passed to constructor as Object parameter.");
		IBooleanVariable bv = set.contains(object);

		if (bv == null)
			throw new SetNonMembershipperException("Object " + object + " is not in the support of Set " + set);

		IProblem problem = new BitFixer(bv, false);
		this.setClauses(problem.getClauses());
	}
}