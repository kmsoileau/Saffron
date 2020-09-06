package integers;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;

public class IntegerStrictOrderer extends Problem implements IProblem
{
	private static int nNSOCount;

	public IntegerStrictOrderer(IInteger X, IInteger Y) throws Exception
	{
		// X<Y
		// There exists Z such that Y=X+1+Z
		IInteger XX = new Integer("IntegerStrictOrderer-" + nNSOCount++);
		IProblem p1 = new IntegerIncrementer(X, XX);
		IProblem p2 = new IntegerOrderer(XX, Y);
		this.setClauses(new Conjunction(p1, p2).getClauses());
	}

	public IntegerStrictOrderer(IInteger X, long y) throws Exception
	{
		IInteger dummy = new Integer("IntegerStrictOrderer-" + nNSOCount++);
		IProblem p1 = new IntegerFixer(dummy, y);
		IProblem p2 = new IntegerStrictOrderer(X, dummy);
		this.setClauses(new Conjunction(p1, p2).getClauses());
	}

	public IntegerStrictOrderer(long x, IInteger Y) throws Exception
	{
		IInteger dummy = new Integer("IntegerStrictOrderer-" + nNSOCount++);
		IProblem p1 = new IntegerFixer(dummy, x);
		IProblem p2 = new IntegerStrictOrderer(dummy, Y);
		this.setClauses(new Conjunction(p1, p2).getClauses());
	}
}
