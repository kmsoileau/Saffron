package demos;

import bits.BooleanVariable;
import bits.Clause;
import bits.IBooleanVariable;
import bits.IClause;
import bits.IProblem;
import bits.Problem;
import bits.ProblemDifferencer;

/**
 * Change History June 26, 2009 Changed "System.out.println("The difference
 * was\n"+new ProblemDifferencer(Problem.unsolvableProblem(),p2));" to
 * "System.out.println("The difference was\n"+new ProblemDifferencer(p1,p2));"
 *
 */
public class ProblemDifferencerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable[] bva = new IBooleanVariable[3];

		bva[0] = BooleanVariable.getBooleanVariable("x_1");
		bva[1] = BooleanVariable.getBooleanVariable("x_2");
		bva[2] = BooleanVariable.getBooleanVariable("x_3");

		IProblem p1 = new Problem(new IClause[]
		{
		// new Clause().or(bva[0]).orNot(bva[1]).or(bva[2]),
		new Clause().orNot(bva[0]).orNot(bva[1]).orNot(bva[2]) });
		IProblem p2 = new Problem(new IClause[]
		{ new Clause().or(bva[0]).orNot(bva[1]).or(bva[2]),
				new Clause().orNot(bva[0]).orNot(bva[1]).orNot(bva[2]) });
		System.out.println("The first IProblem was\n" + p1);
		System.out.println("The second IProblem was\n" + p2);
		System.out.println("The difference was\n"
				+ new ProblemDifferencer(p1, p2));
	}
}