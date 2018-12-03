package demos.naturalnumberlists;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import java.util.List;

import naturalnumberlists.INaturalNumberList;
import naturalnumberlists.NaturalNumberList;
import naturalnumberlists.NaturalNumberListFixer;
import naturalnumbers.NaturalNumber;
import bits.BooleanLiteral;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberListFixerDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber[] theStrings = new NaturalNumber[3];
		theStrings[0] = new NaturalNumber(3);
		theStrings[1] = new NaturalNumber("named", 7);
		theStrings[2] = new NaturalNumber(6);
		INaturalNumberList samp2 = new NaturalNumberList("theList", theStrings);
		IProblem bslf2 = new NaturalNumberListFixer(samp2);
		System.out.println(bslf2);
		List<IBooleanLiteral> s = bslf2.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("samp2= " + samp2);
		}
		else
			System.out.println("No solution.");
	}
}