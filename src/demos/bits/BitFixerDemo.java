package demos.bits;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class BitFixerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable x0 = BooleanVariable.getBooleanVariable("x0");
		IBooleanVariable x1 = BooleanVariable.getBooleanVariable("x1");
		IProblem bitFixerx0_0 = new BitFixer(x0, false);
		IProblem bitFixerx0_1 = new BitFixer(x0, true);
		IProblem bitFixerx1_0 = new BitFixer(x1, false);
		IProblem bitFixerx1_1 = new BitFixer(x1, true);
		IProblem p1 = new Conjunction(bitFixerx0_0, bitFixerx1_0);
		IProblem p2 = new Conjunction(bitFixerx0_0, bitFixerx1_1);
		IProblem p12 = new Disjunction(p1, p2);
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p12);
		System.out.println(bitFixerx0_1);
		BooleanLiteral.interpret(p12.findModel(Problem.defaultSolver()));
		System.out.println("x0 = " + x0.getValue());
		System.out.println("x1 = " + x1.getValue());
	}
}