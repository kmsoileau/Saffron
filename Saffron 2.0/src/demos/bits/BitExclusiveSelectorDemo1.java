package demos.bits;

import java.util.ArrayList;
import java.util.List;

import bits.BitExclusiveSelector;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class BitExclusiveSelectorDemo1
{
	public static void main(String[] args) throws Exception
	{
		ArrayList<IBooleanVariable> bitArrayList = new ArrayList<IBooleanVariable>();

		IBooleanVariable W = BooleanVariable.getBooleanVariable("W");
		IBooleanVariable X = BooleanVariable.getBooleanVariable("X");
		IBooleanVariable Y = BooleanVariable.getBooleanVariable("Y");
		IBooleanVariable Z = BooleanVariable.getBooleanVariable("Z");

		bitArrayList.add(W);
		bitArrayList.add(X);
		bitArrayList.add(Y);
		bitArrayList.add(Z);

		IProblem problem = new BitExclusiveSelector(bitArrayList);

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