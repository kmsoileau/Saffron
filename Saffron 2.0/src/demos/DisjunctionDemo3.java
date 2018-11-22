package demos;

import java.util.List;

import bits.BitEqualizer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Disjunction;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IProblem;
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

		Disjunction disjunction1 = new Disjunction(new IProblem[]
		{ new BitEqualizer(X, Y), new BitEqualizer(Y, Z) },
				new IBooleanVariable[]
				{ b1 });
		System.out.println(disjunction1);
		List<IBooleanLiteral> s = disjunction1.findModel(Problem
				.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("b1= " + b1.getValue());
		}
		else
			System.out.println("No solution.");
	}
}