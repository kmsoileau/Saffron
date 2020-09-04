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
import naturalnumbers.NaturalNumberCompositor;
import naturalnumbers.NaturalNumberFixer;

public class NaturalNumberCompositorDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber Z = new NaturalNumber("Z");
		IProblem p = new Conjunction(new NaturalNumberFixer(Z, 30L),
				new NaturalNumberCompositor(Z));
		IProblemMessage s = p.findModel();
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(Z + " is composite.");
		}
		else
			System.out.println("No solution.");
	}
}