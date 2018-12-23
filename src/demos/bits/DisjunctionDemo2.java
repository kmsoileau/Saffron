package demos.bits;

import bits.Disjunction;
import bits.IProblem;
import bits.Problem;

/**
 * <p>
 * Title: DisjunctionDemo2
 * </p>
 * <p>
 * Description: TBS
 * </p>
 * <p>
 * Copyright (c) 2005
 * </p>
 * <p>
 * Company: Positronic Software
 * </p>
 * 
 * @author Kerry Michael Soileau
 * @version 1.0
 */
public class DisjunctionDemo2
{
	public static void main(String[] args) throws Exception
	{
		IProblem X = Problem.unsolvableProblem();
		System.out.println(X);
		IProblem Y = Problem.trivialProblem();
		System.out.println(Y);

		Disjunction disjunction1 = new Disjunction(new IProblem[]
		{ X, Y });
		System.out.println(disjunction1);
	}
}