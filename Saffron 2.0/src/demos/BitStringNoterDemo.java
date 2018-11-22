package demos;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
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
import bitstrings.BitStringNoter;

public class BitStringNoterDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString X = new BitString("X", new IBooleanVariable[3]);
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

		IBitString Y = new BitString("Y", new IBooleanVariable[3]);
		for (int row = 0; row < X.size(); row++)
			Y.setBooleanVariable(row,
					BooleanVariable.getBooleanVariable(Y.getName() + "_" + row));

		IProblem problem = new Conjunction(new Conjunction(pfix),
				new BitStringNoter(X, Y));
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
	}
}