package demos.sets;

import sets.Set;
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
					"Passed a null Set to constructor.");
		if (object == null)
			throw new SetMembershipperException(
					"Passed a null member Object to constructor.");
		IBooleanVariable bv = set.get(object);
		if (bv == null)
			throw new SetMembershipperException(
					"Object "+object+ " is not in the support of Set "+set);
		//{
			//set.addSupport(object);
			//bv = set.get(object);
		//}

		IProblem problem = new BitFixer(bv, true);
		this.setClauses(problem.getClauses());
	}
}