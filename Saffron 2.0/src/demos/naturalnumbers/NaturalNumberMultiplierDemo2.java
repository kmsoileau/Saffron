package demos.naturalnumbers;

import java.util.List;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberMultiplier;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberMultiplierDemo2
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");
		INaturalNumber Z = new NaturalNumber("Z");
		// INaturalNumber C=new NaturalNumber("C");

		IProblem p;
		List<IBooleanLiteral> s;

		p = new Conjunction(new IProblem[]
		{ new NaturalNumberMultiplier(X, Y, Z), new NaturalNumberFixer(X, 8),
				new NaturalNumberFixer(Y, 1), });
		// System.out.println(p);

		s = p.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.print("\nX = " + X);
			System.out.print("\tY = " + Y);
			System.out.print("\tZ = " + Z);
		}
		else
			System.out.println("No solution.");
	}
}