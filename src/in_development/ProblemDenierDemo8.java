package in_development;

import bits.IProblem;
import bits.Problem;
import bits.ProblemDenier;

public class ProblemDenierDemo8
{
	public static void main(String[] args) throws Exception
	{
		IProblem p = Problem.trivialProblem();
		System.out.println("Problem:" + p);

		IProblem p2 = new ProblemDenier(p);
		System.out.println(p2);
	}
}