package demos.bits;

import java.util.ArrayList;
import java.util.List;

import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IClause;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class RandomProblemDemo
{
	static Object[] convert(IClause c)
	{
		ArrayList<IBooleanVariable> barredSet = new ArrayList<IBooleanVariable>();
		ArrayList<IBooleanVariable> unBarredSet = new ArrayList<IBooleanVariable>();
		for (IBooleanLiteral bl : c.toArray())
		{
			boolean barred = bl.isBarred();
			IBooleanVariable bv = bl.getBooleanVariable();
			if (barred)
				barredSet.add(bv);
			else
				unBarredSet.add(bv);
		}

		return new Object[]
		{ barredSet, unBarredSet };
	}

	public static void main(String[] args) throws Exception
	{
		IBooleanVariable[] bv = new IBooleanVariable[]
		{ BooleanVariable.getBooleanVariable("a"), BooleanVariable.getBooleanVariable("b"),
				BooleanVariable.getBooleanVariable("c"), BooleanVariable.getBooleanVariable("d"),
				BooleanVariable.getBooleanVariable("e"), BooleanVariable.getBooleanVariable("f"),
				BooleanVariable.getBooleanVariable("g"), BooleanVariable.getBooleanVariable("h"),
				BooleanVariable.getBooleanVariable("i"), BooleanVariable.getBooleanVariable("j") };

		IProblem problem = Problem.randomProblem(bv, 30);

		System.out.println(problem);

		IClause cl = problem.getClause(0);

		System.out.println(toMathematica(cl));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			// System.out.println(Arrays.asList(bv));
		}
		else
			System.out.print("No solution.");
	}

	@SuppressWarnings("rawtypes")
	static String toMathematica(IClause c)
	{
		Object[] res = convert(c);
		List barredSet = (List) res[0];
		List unBarredSet = (List) res[1];

		String ret = "{{";
		ret += ((IBooleanVariable) barredSet.get(0)).getName();
		for (int i = 1; i < barredSet.size(); i++)
		{
			ret += "," + ((IBooleanVariable) barredSet.get(i)).getName();
		}
		ret += "},{";
		ret += ((IBooleanVariable) unBarredSet.get(0)).getName();
		for (int i = 1; i < unBarredSet.size(); i++)
		{
			ret += "," + ((IBooleanVariable) unBarredSet.get(i)).getName();
		}
		ret += "}}";

		return ret;
	}
}