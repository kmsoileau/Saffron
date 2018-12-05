package demos.subsetsum;

import java.util.List;

import naturalnumbers.ConditionalAdder;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;

public class SubsetSumDemo
{
	public static void main(String[] args) throws Exception
	{
		int desiredSum = 100;
		
		Integer[] data = new Integer[]
		{ 54, 43, 71, 39, 59, 72, 11, 20, 79, 44, 25, 74, 4, 2, 39, 18, 81, 62,
				48, 45, 82, 94, 76, 27, 3, 77, 77, 68, 8, 50, 70, 62, 76, 10,
				73, 0, 13, 64, 28, 30, 86, 47, 59, 72, 2, 98, 44, 48, 22, 10,
				43, 0, 97, 24, 69, 28, 49, 94, 43, 5, 71, 94, 57, 78, 18, 25,
				73, 13, 58, 91, 52, 91, 13, 37, 44, 12, 72, 67, 90, 31, 79, 50,
				99, 92, 93, 85, 35, 27, 9, 2, 88, 90, 90, 1, 83, 45, 63, 83,
				33, 21 };
	
		int maxSum = 0;
		for (int i = 0; i < data.length; i++)
			maxSum += data[i];
		NaturalNumber.setLargestNaturalNumber(maxSum);

		INaturalNumber[] w = new INaturalNumber[data.length];
		int index = 0;
		IProblem[] problemArray = new IProblem[w.length + 2];
		for (int i = 0; i < w.length; i++)
		{
			w[i] = new NaturalNumber(data[i]);
			problemArray[index++] = new NaturalNumberFixer(w[i]);
		}

		INaturalNumber W = new NaturalNumber(desiredSum);
		problemArray[index++] = new NaturalNumberFixer(W);

		IBitString membership = new BitString(w.length);

		problemArray[index++] = new ConditionalAdder(w, membership, W);

		IProblem problem = new Conjunction(problemArray);

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			for(int i=0;i<membership.size();i++)
				if(membership.getBooleanVariable(i).getValue())
					System.out.print(data[i]+" ");
		}
		else
			System.out.println("No solution.");
	}

}
