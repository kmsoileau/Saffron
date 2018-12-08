package demos.subsetsum;

import java.util.List;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.WeightedObject;
import naturalnumbers.WeightedSet;
import sets.Set;
import sets.SubsetWeightedTotaler;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class SubsetSumDemo1
{
	public static void main(String[] args) throws Exception
	{
		int desiredSum = 300;

		Integer[] data = new Integer[]
		{ 54, 43, 71, 39, 59, 72, 11, 20, 79, 44, 25, 74, 4, 2, 39, 18, 81, 62,
				48, 45, 82, 94, 76, 27, 3, 77, 77, 68, 8, 50, 70, 62, 76, 10,
				73, 0, 13, 64, 28, 30, 86, 47, 59, 72, 2, 98, 44, 48, 22, 10,
				43, 0, 97, 24, 69, 28, 49, 94, 43, 5, 71, 94, 57, 78, 18, 25,
				73, 13, 58, 91, 52, 91, 13, 37, 44, 12, 72, 67, 90, 31, 79, 50,
				99, 92, 93, 85, 35, 27, 9, 2, 88, 90, 90, 1, 83, 45, 63, 83,
				33, 21 };

		// Ensure that the size of NaturalNumbers can accommodate all of the
		// weight values
		int maxSum = 0;
		for (int i = 0; i < data.length; i++)
			maxSum += data[i];
		NaturalNumber.setLargestNaturalNumber(maxSum);

		// Construct WeightedSet
		WeightedSet subset = new WeightedSet(data);

		IProblem[] problemArray = new IProblem[data.length + 2];

		// Create IProblems to bind weight values to corresponding
		// NaturalNumbers
		int index = 0;
		for (Object wo : subset.getSupport())
		{
			WeightedObject curr = (WeightedObject) wo;
			problemArray[index++] = new NaturalNumberFixer(curr.getWeight(),
					curr.getWeightValue());
		}

		// Create an IProblem to bind desired sum value to its NaturalNumber
		INaturalNumber desiredSumNN = new NaturalNumber();
		problemArray[index++] = new NaturalNumberFixer(desiredSumNN, desiredSum);

		// Create an IProblem to solve the overall problem.
		problemArray[index++] = new SubsetWeightedTotaler(subset, desiredSumNN);

		// Combine all of these IProblems into a single IProblem
		IProblem problem = new Conjunction(problemArray);

		// Solve it
		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			Set bs = subset.getBackingSet();
			System.out.print(bs);
		}
		else
			System.out.println("No solution.");
	}
}
