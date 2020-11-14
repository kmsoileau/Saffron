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
import in_development.NaturalNumberPrimer;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

public class NaturalNumberPrimerDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(140);
		INaturalNumber Z = new NaturalNumber("Z");
		IProblem p = new Conjunction(new NaturalNumberFixer(Z, 133L), new NaturalNumberPrimer(Z));
		IProblemMessage s = p.findModel();
		System.out.println(s);
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(Z);
		}
		else
			System.out.println("No solution.");
	}
}