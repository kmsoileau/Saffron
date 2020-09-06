package integers;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;

public class IntegerIncrementer extends Problem implements IProblem
{
	public IntegerIncrementer(IInteger X, IInteger Y) throws Exception
	{
		IInteger one = new Integer(1);

		this.setClauses(new Conjunction(new IntegerFixer(one),
				new IntegerAdder(X, one, Y)).getClauses());
	}
}