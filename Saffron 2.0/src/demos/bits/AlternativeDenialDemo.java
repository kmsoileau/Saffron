package demos.bits;

import java.util.List;

import bits.AlternativeDenial;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Clause;
import bits.IBooleanVariable;
import bits.IClause;
import bits.IProblem;
import bits.Problem;

public class AlternativeDenialDemo
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
		IProblem p3 = new AlternativeDenial(p1, p2, Problem.unsolvableProblem());
		System.out.println("The alternative denial was\n" + p3);

		List<?> s = p3.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println(bva[0]);
			System.out.println(bva[1]);
			System.out.println(bva[2]);
		}
		else
			System.out.println("There is no solution.");
	}
}