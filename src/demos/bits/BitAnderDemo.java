package demos.bits;

import java.util.List;

import bits.BitAnder;
import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

public class BitAnderDemo
{
	public static void main(String[] args) throws Exception
	{
		// Create the BooleanVariables:
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");
		IBooleanVariable z = BooleanVariable.getBooleanVariable("z");

		// Construct the object that implements the constraint x & y = z :
		IProblem bitAnder1 = new BitAnder(x, y, z);
		System.out.println(bitAnder1);

		// false & false = z has solution z = false :
		// Constrain the values of x and y :
		IProblem bfx1 = new BitFixer(x, false);
		IProblem bfy1 = new BitFixer(y, false);

		// Combine the constraints into a Problem object :
		IProblem p1 = new Conjunction(bitAnder1, bfx1, bfy1);
		System.out.println(p1);

		// Find a solution to the Problem object :
		IProblemMessage v1 = p1.findModel(Problem.defaultSolver());
		System.out.println(v1);

		// false & true = z has solution z = false:
		IProblem p2 = new Conjunction(bitAnder1, new BitFixer(x, false), new BitFixer(y, true));
		System.out.println(p2);
		IProblemMessage v2 = p2.findModel(Problem.defaultSolver());
		System.out.println(v2);

		// true & false = z has solution z = false:
		IProblem p3 = new Conjunction(bitAnder1, new BitFixer(x, true), new BitFixer(y, false));
		System.out.println(p3);
		IProblemMessage v3 = p3.findModel(Problem.defaultSolver());
		System.out.println(v3);

		// true & true = z has solution z = true:
		IProblem p4 = new Conjunction(bitAnder1, new BitFixer(x, true), new BitFixer(y, true));
		System.out.println(p4);
		IProblemMessage v4 = p4.findModel(Problem.defaultSolver());
		System.out.println(v4);

		// true & y = true has solution y = true:
		IProblem p5 = new Conjunction(bitAnder1, new BitFixer(x, true), new BitFixer(z, true));
		System.out.println(p5);
		IProblemMessage v5 = p5.findModel(Problem.defaultSolver());
		System.out.println(v5);

		// true & y = false has solution y = false:
		IProblem p6 = new Conjunction(bitAnder1, new BitFixer(x, true), new BitFixer(z, false));
		System.out.println(p6);
		IProblemMessage v6 = p6.findModel(Problem.defaultSolver());
		System.out.println(v6);
		BooleanLiteral.interpret(p6.findModel(Problem.defaultSolver()).getLiterals());
		System.out.println("----!------------");
		System.out.println("x = " + x.getValue());
		System.out.println("y = " + y.getValue());
		System.out.println("z = " + z.getValue());

		IProblem rp = ((Problem) p6).resolve(v6.getLiterals());
		System.out.println("::::::::::");
		System.out.println(rp);

		// false & y = true has no solution:
		IProblem p7 = new Conjunction(bitAnder1, new BitFixer(x, false), new BitFixer(z, true));
		System.out.println(p7);
		List<IBooleanLiteral> s = p7.findModel(Problem.defaultSolver()).getLiterals();

		System.out.println(s);
	}
}