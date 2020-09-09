package demos.bits;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberAdder;
import naturalnumbers.NaturalNumberFixer;

public class FindTwoModelsDemo
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLength(9);
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");
		INaturalNumber Z = new NaturalNumber("Z");
		INaturalNumber C = new NaturalNumber("C");

		// NaturalNumberFixer bnnfx=new NaturalNumberFixer(X,127);
		NaturalNumberFixer bnnfy = new NaturalNumberFixer(Y, 121);

		NaturalNumberAdder NaturalNumberAdder1 = new NaturalNumberAdder(X, Y, Z, C);

		// IProblem p=new Conjunction(new
		// Conjunction(bnnfx,bnnfy),NaturalNumberAdder1);
		IProblem p = new Conjunction(bnnfy, NaturalNumberAdder1);

		System.out.println(p);
		IProblemMessage[] s = p.findTwoModels(X.getBooleanVariable(3));
		System.out.println("First model found:");
		if (s[0].getLiterals() != null && s[0].getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s[0].getLiterals());
			System.out.println("X = " + X);
			System.out.println("Y = " + Y);
			System.out.println("Z = " + Z);
			System.out.println("C = " + C);
		}
		else
			System.out.println("No solution.");
		System.out.println("Second model found:");
		if (s[1].getLiterals() != null && s[1].getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s[1].getLiterals());
			System.out.println("X = " + X);
			System.out.println("Y = " + Y);
			System.out.println("Z = " + Z);
			System.out.println("C = " + C);
		}
		else
			System.out.println("No solution.");
	}
}
