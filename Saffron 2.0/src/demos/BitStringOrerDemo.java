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
import bitstrings.BitStringOrer;

public class BitStringOrerDemo
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
		for (int row = 0; row < Y.size(); row++)
		{
			boolean value = false;
			if (Math.random() < .5)
				value = true;
			Y.setBooleanVariable(row,
					BooleanVariable.getBooleanVariable(Y.getName() + "_" + row));
			pfix.add(new BitFixer(Y.getBooleanVariable(row), value));
		}
		IBitString Z = new BitString("Z", new IBooleanVariable[3]);
		for (int row = 0; row < X.size(); row++)
			Z.setBooleanVariable(row,
					BooleanVariable.getBooleanVariable(Z.getName() + "_" + row));

		IProblem fix = new Conjunction(pfix);
		IProblem bta = new BitStringOrer(X, Y, Z);
		IProblem problem = new Conjunction(fix, bta);
		System.out.println(problem);
		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X);
			System.out.println("Y= " + Y);
			System.out.println("Z= " + Z);
		}
		else
			System.out.println("No solution.");
	}
}