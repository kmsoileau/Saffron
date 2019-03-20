package demos.bitstrings;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringConcatenator;
import bitstrings.BitStringFixer;

public class BitStringConcatenatorDemo
{
	public static void main(String[] args) throws Exception
	{
		// Create the BitStrings:
		IBitString X = new BitString("X", "001000101010101");
		IBitString Y = new BitString("Y", "101");
		IBitString Z = new BitString("Z", new boolean[18]);
		// Construct the object that implements the constraint X + Y = Z :
		IProblem bitAnder1 = new BitStringConcatenator(X, Y, Z);
		System.out.println(bitAnder1);

		// Fix the values of X and Y :
		IProblem bfx1 = new BitStringFixer(X);
		IProblem bfy1 = new BitStringFixer(Y);

		// Combine the constraints into a Problem object :
		IProblem p1 = new Conjunction(bitAnder1, bfx1, bfy1);
		System.out.println(p1);
		// Find a solution to the Problem object :
		IProblemMessage v1 = p1.findModel(Problem.defaultSolver());
		BooleanLiteral.interpret(v1.getLiterals());
		System.out.println(X);
		System.out.println(Y);
		System.out.println(Z);
	}
}