package in_development;

import bits.BitFixer;
import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.ProblemDenier;

public class ProblemDenierDemo12
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable b1 = BooleanVariable.getBooleanVariable("b1");

		IProblem problem = new BitFixer(b1, true);
		IProblem qq = new ProblemDenier(problem);

		System.out.println(qq);
	}
}