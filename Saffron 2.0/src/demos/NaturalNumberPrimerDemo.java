package demos;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberPrimer;

public class NaturalNumberPrimerDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber Z = new NaturalNumber("Z");
		IProblem p = new Conjunction(new NaturalNumberFixer(Z, 13L),
				new NaturalNumberPrimer(Z));
		List<IBooleanLiteral> s = p.findModel();
		System.out.println(s);
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println(Z);
		}
		else
			System.out.println("No solution.");
	}
}