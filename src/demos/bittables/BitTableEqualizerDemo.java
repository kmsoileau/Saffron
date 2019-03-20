package demos.bittables;

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
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bittables.BitTable;
import bittables.BitTableEqualizer;
import bittables.IBitTable;

public class BitTableEqualizerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitTable X = new BitTable("X", new IBooleanVariable[3][4]);
		ArrayList<IProblem> pfix = new ArrayList<IProblem>();
		for (int row = 0; row < X.numberRows(); row++)
			for (int column = 0; column < X.numberColumns(); column++)
			{
				boolean value;
				if (Math.random() < .5)
					value = true;
				else
					value = false;
				X.setBooleanVariable(
						row,
						column,
						BooleanVariable.getBooleanVariable(X.getName() + "_"
								+ row + "_" + column));
				pfix.add(new BitFixer(X.getBooleanVariable(row, column), value));
			}

		IBitTable Y = new BitTable("Y", new IBooleanVariable[3][4]);
		for (int row = 0; row < X.numberRows(); row++)
			for (int column = 0; column < X.numberColumns(); column++)
				Y.setBooleanVariable(
						row,
						column,
						BooleanVariable.getBooleanVariable(Y.getName() + "_"
								+ row + "_" + column));

		IProblem fix = new Conjunction(pfix);
		IProblem bta = new BitTableEqualizer(X, Y);
		IProblem problem = new Conjunction(fix, bta);
		System.out.println(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X= \n" + X);
			System.out.println("Y= \n" + Y);
		}
		else
			System.out.println("No solution.");
	}
}