package showcase.subsetsum;

import java.util.List;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.WeightedObject;
import naturalnumbers.WeightedSet;
import sets.Set;
import sets.SubsetWeightedTotaler;
import tbs.Clock;
import tbs.Clocks;
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
		Clocks.addClock("c1");
		Clocks.addClock("c2");
		Clocks.addClock("c3");

		Clock c1 = Clocks.getClock("c1");
		Clock c2 = Clocks.getClock("c2");
		Clock c3 = Clocks.getClock("c3");

		c1.start();
		int desiredSum = 354;

		Integer[] data = new Integer[]
		{ 54, 43, 71, 39, 59, 72, 11, 20, 79, 44, 25, 74, 4, 2,/*
																 * 39, 18, 81,
																 * 62, 48, 45,
																 * 82, 94, 76,
																 * 27, 3, 77,
																 * 77, 68, 8,
																 * 50, 70, 62,
																 * 76, 10, 73,
																 * 13, 64, 28,
																 * 30, 86, 47,
																 * 59, 72, 2,
																 * 98, 44, 48,
																 * 22, 10
																 */};

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
		c1.stop();

		c2.start();
		// Combine all of these IProblems into a single IProblem
		IProblem problem = new Conjunction(problemArray);
		c2.stop();

		c3.start();
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
		c3.stop();
		System.out.println("\nc1=" + c1.getTotalElapsedTime());
		System.out.println("\nc2=" + c2.getTotalElapsedTime());
		System.out.println("\nc3=" + c3.getTotalElapsedTime());
	}
}
