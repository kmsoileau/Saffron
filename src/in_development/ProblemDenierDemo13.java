package in_development;

import bits.EnhancedProblem;
import bits.IProblem;
import bits.ProblemDenier;

public class ProblemDenierDemo13
{
	public static void main(String[] args) throws Exception
	{
		IProblem problem = EnhancedProblem.trivialProblem();
		System.out.println(problem);
		IProblem qq = new ProblemDenier(problem);

		System.out.println(qq);
	}
}