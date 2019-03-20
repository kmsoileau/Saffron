package demos.bittables;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
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
import bittables.BitTableXorer;
import bittables.IBitTable;

public class BitTableXorerDemo
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
		for (int row = 0; row < Y.numberRows(); row++)
			for (int column = 0; column < Y.numberColumns(); column++)
			{
				boolean value;
				if (Math.random() < .5)
					value = true;
				else
					value = false;
				Y.setBooleanVariable(
						row,
						column,
						BooleanVariable.getBooleanVariable(Y.getName() + "_"
								+ row + "_" + column));
				pfix.add(new BitFixer(Y.getBooleanVariable(row, column), value));
			}
		IBitTable Z = new BitTable("Z", new IBooleanVariable[3][4]);
		for (int row = 0; row < X.numberRows(); row++)
			for (int column = 0; column < X.numberColumns(); column++)
				Z.setBooleanVariable(
						row,
						column,
						BooleanVariable.getBooleanVariable(Z.getName() + "_"
								+ row + "_" + column));

		IProblem fix = new Conjunction(pfix);
		IProblem bta = new BitTableXorer(X, Y, Z);
		IProblem problem = new Conjunction(fix, bta);
		System.out.println(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X= \n" + X);
			System.out.println("Y= \n" + Y);
			System.out.println("Z= \n" + Z);
		}
		else
			System.out.println("No solution.");
	}
}