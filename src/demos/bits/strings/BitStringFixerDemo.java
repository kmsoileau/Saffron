package demos.bits.strings;

import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringFixer;

public class BitStringFixerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString samp1 = new BitString("myBitString1", new boolean[3]);
		IProblem problem = new BitStringFixer(samp1, new boolean[]
		{ true, false, true });
		System.out.print(problem);
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("\n" + samp1.getName() + "= " + samp1);

		}
		else
			System.out.println("No solution.");

		IBitString samp2 = new BitString("myBitString2", 3);
		problem = new BitStringFixer(samp2, new boolean[]
		{ true, false, true, false });
		System.out.print(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("\n" + samp2.getName() + "= " + samp2);

		}
		else
			System.out.println("No solution.");

		IBitString samp3 = new BitString("myBitString3", 3);
		problem = new BitStringFixer(samp3, new BitString("101"));
		System.out.print(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("\n" + samp3.getName() + "= " + samp3);

		}
		else
			System.out.println("No solution.");

		IBitString samp4 = new BitString("myBitString4", new boolean[]
		{ false, false, true });
		problem = new BitStringFixer(samp4, new BitString("101"));
		System.out.print(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("\n" + samp4.getName() + "= " + samp4);

		}
		else
			System.out.println("No solution.");

		IBitString samp5 = new BitString("myBitString5", new IBooleanVariable[]
		{ BooleanVariable.getBooleanVariable(true), BooleanVariable.getBooleanVariable("foo", true),
				BooleanVariable.getBooleanVariable(false) });
		problem = new BitStringFixer(samp5);
		System.out.print(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("\n" + samp5.getName() + "= " + samp5);

		}
		else
			System.out.println("No solution.");

		IBitString samp6 = new BitString("myBitString6", new IBooleanVariable[]
		{ BooleanVariable.getBooleanVariable(true), BooleanVariable.getBooleanVariable("foo", true),
				BooleanVariable.getBooleanVariable(false) });
		problem = new BitStringFixer(samp6, new boolean[]
		{ false, false, true });
		System.out.print(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("\n" + samp6.getName() + "= " + samp6);

		}
		else
			System.out.println("No solution.");
	}
}