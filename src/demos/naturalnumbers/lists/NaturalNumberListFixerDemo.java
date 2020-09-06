package demos.naturalnumbers.lists;

import bits.BooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import naturalnumbers.NaturalNumber;
import naturalnumbers.lists.INaturalNumberList;
import naturalnumbers.lists.NaturalNumberList;
import naturalnumbers.lists.NaturalNumberListFixer;

public class NaturalNumberListFixerDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber[] theStrings = new NaturalNumber[3];
		theStrings[0] = new NaturalNumber(3);
		theStrings[1] = new NaturalNumber("named", 7);
		theStrings[2] = new NaturalNumber(6);
		
		INaturalNumberList theList = new NaturalNumberList("theList", theStrings);
		
		IProblem bslf2 = new NaturalNumberListFixer(theList);
		
		System.out.println(bslf2);
		
		IProblemMessage s = bslf2.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("theList = " + theList);
		}
		else
			System.out.println("No solution.");
	}
}