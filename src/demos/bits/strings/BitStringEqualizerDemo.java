package demos.bits.strings;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import java.util.ArrayList;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.EnhancedProblem;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringEqualizer;

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
			X.setBooleanVariable(i, BooleanVariable.getBooleanVariable(X.getName() + "_" + i));
			pfix.add(new BitFixer(X.getBooleanVariable(i), value));
		}

		IBitString Y = new BitString("Y", new IBooleanVariable[4]);
		for (int i = 0; i < Y.size(); i++)
			Y.setBooleanVariable(i, BooleanVariable.getBooleanVariable(Y.getName() + "_" + i));

		IProblem fix = new Conjunction(pfix);

		IProblem bta = new BitStringEqualizer(X, Y);

		IProblem problem = new Conjunction(fix, bta);
		System.out.println(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X= " + X);
			System.out.println("Y= " + Y);
		}
		else
			System.out.println("No solution.");

		System.out.println(((EnhancedProblem) problem).buildEquivalenceRelation().toString());
	}
}