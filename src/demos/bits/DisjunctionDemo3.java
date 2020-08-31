package demos.bits;

import bits.BitEqualizer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

/**
 * <p>
 * Title: DisjunctionDemo3
 * </p>
 * <p>
 * Description: TBS
 * </p>
 * <p>
 * Copyright (c) 2010
 * </p>
 * <p>
 * Company: Positronic Software
 * </p>
 * 
 * @author Kerry Michael Soileau
 * @version 1.0
 */

public class DisjunctionDemo3
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable X = BooleanVariable.getBooleanVariable("X");
		IBooleanVariable Y = BooleanVariable.getBooleanVariable("Y");
		IBooleanVariable Z = BooleanVariable.getBooleanVariable("Z");
		IBooleanVariable b1 = BooleanVariable.getBooleanVariable("b1");

		Disjunction disjunction1 = new Disjunction(new IBooleanVariable[]
		{ b1 }, new IProblem[]
		{ new BitEqualizer(X, Y), new BitEqualizer(Y, Z) });
		System.out.println(disjunction1);
		IProblemMessage s = disjunction1.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("b1= " + b1.getValue());
		}
		else
			System.out.println("No solution.");
	}
}