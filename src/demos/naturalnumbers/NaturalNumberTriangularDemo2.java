package demos.naturalnumbers;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberDoubler;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberIncrementer;
import naturalnumbers.NaturalNumberMultiplier;

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
		{ new NaturalNumberFixer(Two, 2), new NaturalNumberFixer(TwoZ, 12), new NaturalNumberIncrementer(X, Y),
				new NaturalNumberMultiplier(X, Y, TwoZ), new NaturalNumberFixer(Z, 6),
				new NaturalNumberDoubler(Z, TwoZ), });

		IProblemMessage s = p.findModel();
		System.out.println(p);
		System.out.println(s);
		BooleanLiteral.interpret(s.getLiterals());
		System.out.println("X = " + X);
		System.out.println("Two = " + Two);
		System.out.println("TwoZ = " + TwoZ);
		System.out.println("Y = " + Y);
		System.out.println("Z = " + Z);
	}
}