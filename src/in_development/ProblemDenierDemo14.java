package in_development;

import bits.BitFixer;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.ProblemDenier;

public class ProblemDenierDemo14
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable b1 = BooleanVariable.getBooleanVariable("b1");
		IBooleanVariable b2 = BooleanVariable.getBooleanVariable("b2");

		IProblem problem = new Conjunction(new BitFixer(b1, false), new BitFixer(b2, false));
		IProblem qq = new ProblemDenier(problem);

		System.out.println(qq);
	}
}