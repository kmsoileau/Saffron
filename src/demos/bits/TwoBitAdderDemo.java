package demos.bits;

import java.util.List;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import bits.TwoBitAdder;

public class TwoBitAdderDemo
{
	private static String doCase(IBooleanVariable[] bva, boolean b1, boolean b2)
			throws Exception
	{
		IProblem problemBase = new TwoBitAdder(bva[0], bva[1], bva[2], bva[3]);
		IProblem problemSpecific = new Conjunction(new BitFixer(bva[0], b1),
				new BitFixer(bva[1], b2));
		IProblem problem = new Conjunction(problemBase, problemSpecific);

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			String ret = "";
			BooleanLiteral.interpret(s);
			ret += bva[0].getValue() ? "1" : "0";
			ret += bva[1].getValue() ? "1" : "0";
			ret += bva[2].getValue() ? "1" : "0";
			ret += bva[3].getValue() ? "1" : "0";
			return ret;
		}
		else
			return null;
	}

	public static void main(String[] args) throws Exception
	{
		IBooleanVariable[] bva =
		{ BooleanVariable.getBooleanVariable("X"),
				BooleanVariable.getBooleanVariable("Y"),
				BooleanVariable.getBooleanVariable("Z"),
				BooleanVariable.getBooleanVariable("C") };

		System.out.println("XYZC");

		for (Boolean curr1 : new Boolean[]
		{ false, true })
			for (Boolean curr2 : new Boolean[]
			{ false, true })
			{
				System.out.println(doCase(bva, curr1, curr2));
			}
	}
}