package naturalnumbers;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberMultiplierDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");
		INaturalNumber Z = new NaturalNumber("Z");
		// INaturalNumber C=new NaturalNumber("C");

		INaturalNumber N1 = new NaturalNumber("N1");
		INaturalNumber N2 = new NaturalNumber("N2");
		INaturalNumber two = new NaturalNumber("two");

		IProblem p = new Conjunction(
				new IProblem[]
				{
						// new NaturalNumberMultiplier(X,Y,Z,C),
						new NaturalNumberMultiplier(X, Y, Z),
						new NaturalNumberFixer(Z, 194L),
						new NaturalNumberFixer(two, 2L),
						new NaturalNumberAdder(N1, two, X),
						new NaturalNumberAdder(N2, two, Y),
						new NaturalNumberOrderer(X, Z),
						new NaturalNumberOrderer(Y, Z) });
		System.out.println(p);

		List<IBooleanLiteral> s = p.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("N1 = " + N1);
			System.out.println("X = " + X);
			System.out.println("N2 = " + N2);
			System.out.println("Y = " + Y);
			System.out.println("Z = " + Z);
			// System.out.println("C = "+C);
		}
		else
			System.out.println("No solution.");
	}
}