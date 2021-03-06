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
import bits.IBitString;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringHighPopper;

public class BitStringHighPopperDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString X = new BitString("X", new IBooleanVariable[4]);
		ArrayList<IProblem> pfix = new ArrayList<IProblem>();
		for (int i = 0; i < X.size(); i++)
		{
			boolean value = Math.random() < .5;

			X.setBooleanVariable(i, BooleanVariable.getBooleanVariable(X.getName() + "_" + i));
			pfix.add(new BitFixer(X.getBooleanVariable(i), value));
		}

		IBitString Y = new BitString("Y", new IBooleanVariable[3]);
		for (int i = 0; i < Y.size(); i++)
			Y.setBooleanVariable(i, BooleanVariable.getBooleanVariable(Y.getName() + "_" + i));

		IProblem fix = new Conjunction(pfix);

		IProblem bta = new BitStringHighPopper(X, Y);

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
	}
}