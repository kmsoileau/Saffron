package demos.naturalnumbers;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberArrayNonrepeater;
import naturalnumbers.NaturalNumberFixer;

public class NaturalNumberArrayNonrepeaterDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber[] testList = new INaturalNumber[]
		{ new NaturalNumber(5), new NaturalNumber(6), new NaturalNumber(2), new NaturalNumber(4) };

		IProblem problem = new Conjunction(new NaturalNumberFixer(testList),
				new NaturalNumberArrayNonrepeater(testList));
		System.out.println(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.print("[");
			for (int i = 0; i < testList.length - 1; i++)
				System.out.print(testList[i] + ",");
			System.out.print(testList[testList.length - 1] + "]");
		}
		else
			System.out.println("No solution.");
	}
}