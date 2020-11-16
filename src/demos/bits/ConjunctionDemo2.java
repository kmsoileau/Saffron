package demos.bits;

import bits.Disjunction;
import bits.EnhancedProblem;
import bits.IProblem;

public class ConjunctionDemo2
{
	public static void main(String[] args) throws Exception
	{
		IProblem problem1 = new Disjunction(EnhancedProblem.trivialProblem(), EnhancedProblem.trivialProblem());
		System.out.println(problem1);
		IProblem problem2 = new Disjunction(EnhancedProblem.trivialProblem(), EnhancedProblem.unsolvableProblem());
		System.out.println(problem2);
		IProblem problem3

				= new Disjunction(EnhancedProblem.unsolvableProblem(), EnhancedProblem.trivialProblem());
		System.out.println(problem3);
		IProblem problem4 = new Disjunction(EnhancedProblem.unsolvableProblem(), EnhancedProblem.unsolvableProblem());
		System.out.println(problem4);
	}
}