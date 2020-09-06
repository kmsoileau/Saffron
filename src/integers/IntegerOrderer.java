package integers;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;

public class IntegerOrderer extends Problem implements IProblem
{
	private static int iIOCount;

	public IntegerOrderer(IInteger X, IInteger Y) throws Exception
	{
		IInteger offset = new Integer("IntegerOrderer-" + iIOCount++);
		IProblem p1 = new Conjunction(new NonnegativeInteger(offset),
				new IntegerAdder(X, offset, Y));
		this.setClauses(p1.getClauses());
	}

	public IntegerOrderer(IInteger X, long y) throws Exception
	{
		IInteger dummy = new Integer("IntegerOrderer-" + iIOCount++);
		IProblem p1 = new IntegerFixer(dummy, y);
		IProblem p2 = new IntegerOrderer(X, dummy);
		this.setClauses(new Conjunction(p1, p2).getClauses());
	}

	public IntegerOrderer(long x, IInteger Y) throws Exception
	{
		IInteger dummy = new Integer("IntegerOrderer-" + iIOCount++);
		IProblem p1 = new IntegerFixer(dummy, x);
		IProblem p2 = new IntegerOrderer(dummy, Y);
		this.setClauses(new Conjunction(p1, p2).getClauses());
	}
}
