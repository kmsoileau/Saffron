package demos;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.List;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringEqualizer;

public class BitStringEqualizerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString X = new BitString("X", new IBooleanVariable[4]);
		ArrayList<IProblem> pfix = new ArrayList<IProblem>();
		for (int i = 0; i < X.size(); i++)
		{
			boolean value;
			if (Math.random() < .5)
				value = true;
			else
				value = false;
			X.setBooleanVariable(i,
					BooleanVariable.getBooleanVariable(X.getName() + "_" + i));
			pfix.add(new BitFixer(X.getBooleanVariable(i), value));
		}

		IBitString Y = new BitString("Y", new IBooleanVariable[4]);
		for (int i = 0; i < Y.size(); i++)
			Y.setBooleanVariable(i,
					BooleanVariable.getBooleanVariable(Y.getName() + "_" + i));

		IProblem fix = new Conjunction(pfix);

		IProblem bta = new BitStringEqualizer(X, Y);

		IProblem problem = new Conjunction(fix, bta);
		System.out.println(problem);
		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X);
			System.out.println("Y= " + Y);
		}
		else
			System.out.println("No solution.");

		System.out.println(((Problem) problem).buildEquivalenceRelation()
				.toString());
	}
}