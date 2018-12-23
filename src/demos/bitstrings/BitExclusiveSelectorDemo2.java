package demos.bitstrings;

import java.util.List;

import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitExclusiveSelector;
import bitstrings.BitString;

public class BitExclusiveSelectorDemo2
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable W = BooleanVariable.getBooleanVariable("W");
		IBooleanVariable X = BooleanVariable.getBooleanVariable("X");
		IBooleanVariable Y = BooleanVariable.getBooleanVariable("Y");
		IBooleanVariable Z = BooleanVariable.getBooleanVariable("Z");

		IBitString string = new BitString(new IBooleanVariable[]{W,X,Y,Z});
		
		IProblem problem = new BitExclusiveSelector(string);

		System.out.println(problem);
		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("W= " + W.getValue());
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
	}
}