package demos.sets;

import java.util.List;

import naturalnumbers.ConditionalAdder;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import sets.Set;
import sets.WeightedSet;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class SubsetWeightedTotalerDemo1
{
	public static void main(String[] args) throws Exception
	{
		int desiredSum = 59;

		Set.setElementNames(new String[]
		{ "o1", "o2", "o3", "o4" });

		Integer[] weight = new Integer[]
		{ 23, 17, 9, 19 };

		int maxNN = 0;
		for (int i = 0; i < weight.length; i++)
			maxNN += weight[i];

		NaturalNumber.setLargestNaturalNumber(maxNN);

		WeightedSet subset = new WeightedSet();

		IProblem[] p1 = new IProblem[Set.getSetSupportSize()];
		for (int i = 0; i < p1.length; i++)
		{
			p1[i] = new NaturalNumberFixer(subset.getWeight(i), weight[i]);
		}

		INaturalNumber sum = new NaturalNumber();

		List<IBooleanLiteral> s = new Conjunction(new Conjunction(p1),
				new ConditionalAdder(subset.getWeights(),
						subset.getMembership(), sum), new NaturalNumberFixer(
						sum, desiredSum)).findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			if (subset.getMembership().getBooleanVariable(0).getValue())
				System.out.print(subset.getWeight(0));
			for (int i = 1; i < Set.getSetSupportSize(); i++)
				if (subset.getMembership().getBooleanVariable(i).getValue())
					System.out.print(" + " + subset.getWeight(i));
			System.out.println(" = " + sum);
		}
		else
			System.out.print("No solution.");
	}
}
