package demos.bits.strings;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.BitStringFixer;
import bits.strings.BitStringSubstringer;

public class BitStringSubstringerDemo
{
	public static void main(String[] args) throws Exception
	{
		// Create the BitStrings:
		IBitString X = new BitString("X", "010001");
		IBitString Y = new BitString("Y", "001000101010101");
		// Construct the object that implements the constraint X is a substring
		// of
		// Y:
		IProblem bitSubstringer1 = new BitStringSubstringer(X, Y);
		System.out.println(bitSubstringer1);
		// Fix the values of X and Y :
		IProblem bfx1 = new BitStringFixer(X);
		IProblem bfy1 = new BitStringFixer(Y);
		// Combine the constraints into a Problem object:
		IProblem p1 = new Conjunction(bitSubstringer1, bfx1, bfy1);
		System.out.println(p1);
		// Find a solution to the Problem object :
		IProblemMessage s = p1.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X= " + X);
			System.out.println("Y= " + Y);
		}
		else
			System.out.println("No solution.");
	}
}