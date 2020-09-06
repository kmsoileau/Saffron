package in_development;

import bits.IProblem;
import bits.Problem;
import bits.ProblemDenier;

public class ProblemDenierDemo11
{
	public static void main(String[] args) throws Exception
	{
		IProblem problem = Problem.newProblem();

		System.out.println(problem);

		IProblem qq = new ProblemDenier(problem);

		System.out.println(qq);
	}
}