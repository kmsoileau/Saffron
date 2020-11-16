package demos.bits;

import bits.Disjunction;
import bits.EnhancedProblem;
import bits.IProblem;

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
		IProblem X = EnhancedProblem.unsolvableProblem();
		System.out.println(X);
		IProblem Y = EnhancedProblem.trivialProblem();
		System.out.println(Y);

		Disjunction disjunction1 = new Disjunction(new IProblem[]
		{ X, Y });
		System.out.println(disjunction1);
	}
}