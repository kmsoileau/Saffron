package demos.naturalnumbers;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import java.util.List;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberDoubler;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberIncrementer;
import naturalnumbers.NaturalNumberMultiplier;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;

public class NaturalNumberTriangularDemo2
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Two = new NaturalNumber("Two");
		INaturalNumber TwoZ = new NaturalNumber("TwoZ");
		INaturalNumber Y = new NaturalNumber("Y");
		INaturalNumber Z = new NaturalNumber("Z");

		IProblem p = new Conjunction(new IProblem[]
		{ new NaturalNumberFixer(Two, 2), new NaturalNumberFixer(TwoZ, 12),
				new NaturalNumberIncrementer(X, Y),
				new NaturalNumberMultiplier(X, Y, TwoZ),
				new NaturalNumberFixer(Z, 6),
				new NaturalNumberDoubler(Z, TwoZ),
		// new NaturalNumberMultiplier(Two,Z,TwoZ),
				});

		/*
		 * p=new Conjunction(new NaturalNumberFixer(Two), new
		 * NaturalNumberFixer(Z,6), new
		 * NaturalNumberTriangular(X,Two,TwoZ,Y,Z));
		 */
		List<IBooleanLiteral> s = p.findModel();
		System.out.println(p);
		System.out.println(s);
		BooleanLiteral.interpret(s);
		System.out.println("X = " + X);
		System.out.println("Two = " + Two);
		System.out.println("TwoZ = " + TwoZ);
		System.out.println("Y = " + Y);
		System.out.println("Z = " + Z);

		/*
		 * PartialSolution ps=new PartialSolution(new ArrayListSet(),p);
		 * ps.eliminateSingletons();
		 * System.out.println(ps.getBooleanLiterals());
		 * System.out.println(ps.getProblem());
		 */

	}
}