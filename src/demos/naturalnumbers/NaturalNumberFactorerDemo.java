package demos.naturalnumbers;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFactorer;
import naturalnumbers.NaturalNumberFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;

public class NaturalNumberFactorerDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLength(65);
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");
		INaturalNumber Z = new NaturalNumber("Z");

		IProblem p = new Conjunction(new NaturalNumberFixer(Z, 9111L),
				new NaturalNumberFactorer(X, Y, Z));

		System.out.println(p.size() + "clauses generated...");

		IProblemMessage s = p.findModel();

		BooleanLiteral.interpret(s.getLiterals());

		System.out.println("X = " + X);
		System.out.println("Y = " + Y);
		System.out.println("Z = " + Z);
	}
}