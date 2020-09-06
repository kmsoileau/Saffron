package demos.bits.strings;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.11
 * @since Mar 5, 2019
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
import bits.strings.BitStringSubtractor;

public class BitStringSubtractorDemo
{
	public static void main(String[] args) throws Exception
	{
		int size=13;
		
		IBitString X = new BitString("X", new boolean[size]);
		ArrayList<IProblem> pfix = new ArrayList<IProblem>();
		for (int row = 0; row < X.size(); row++)
		{
			boolean value = false;
			if (Math.random() < .5)
				value = true;
			X.setBooleanVariable(row,
					BooleanVariable.getBooleanVariable(X.getName() + "_" + row));
			pfix.add(new BitFixer(X.getBooleanVariable(row), value));
		}
		IBitString Y = new BitString("Y", new IBooleanVariable[size]);
		for (int row = 0; row < Y.size(); row++)
		{
			boolean value = false;
			if (Math.random() < .5)
				value = true;
			Y.setBooleanVariable(row,
					BooleanVariable.getBooleanVariable(Y.getName() + "_" + row));
			pfix.add(new BitFixer(Y.getBooleanVariable(row), value));
		}
		IBitString Z = new BitString("Z", new IBooleanVariable[size]);
		for (int row = 0; row < X.size(); row++)
			Z.setBooleanVariable(row,
					BooleanVariable.getBooleanVariable(Z.getName() + "_" + row));

		IProblem fix = new Conjunction(pfix);
		IProblem bta = new BitStringSubtractor(X, Y, Z);
		IProblem problem = new Conjunction(fix, bta);
		System.out.println(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X= " + X);
			System.out.println("Y= " + Y);
			System.out.println("Z= " + Z);
		}
		else
			System.out.println("No solution.");
	}
}