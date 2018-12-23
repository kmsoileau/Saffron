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
import naturalnumbers.NaturalNumberCompositor;
import naturalnumbers.NaturalNumberFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;

public class NaturalNumberCompositorDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber Z = new NaturalNumber("Z");
		IProblem p = new Conjunction(new NaturalNumberFixer(Z, 30L),
				new NaturalNumberCompositor(Z));
		List<IBooleanLiteral> s = p.findModel();
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println(Z + " is composite.");
		}
		else
			System.out.println("No solution.");
	}
}