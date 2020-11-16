package in_development;

import bits.EnhancedProblem;
import bits.IProblem;
import bits.ProblemDenier;

public class ProblemDenierDemo8
{
	public static void main(String[] args) throws Exception
	{
		IProblem p = EnhancedProblem.trivialProblem();
		System.out.println("Problem:" + p);

		IProblem p2 = new ProblemDenier(p);
		System.out.println(p2);
	}
}