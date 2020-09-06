package in_development;

import bits.BitFixer;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.ProblemDenier;

public class ProblemDenierDemo15
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable b1 = BooleanVariable.getBooleanVariable("b1");

		IProblem problem = new BitFixer(b1, false);
		IProblem qq = new ProblemDenier(problem);

		System.out.println(qq);
	}
}