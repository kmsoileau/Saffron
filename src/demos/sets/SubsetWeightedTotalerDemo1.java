package demos.sets;

import java.util.List;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.WeightedObject;
import naturalnumbers.WeightedSet;
import sets.SubsetWeightedTotaler;
import bits.BitFixer;
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
		WeightedSet subset = new WeightedSet();
		INaturalNumber sum = new NaturalNumber();

		WeightedObject o1 = new WeightedObject("o1");
		subset.addSupport(o1);
		WeightedObject o2 = new WeightedObject("o2");
		subset.addSupport(o2);
		WeightedObject o3 = new WeightedObject("o3");
		subset.addSupport(o3);

		IProblem totalProblem = new SubsetWeightedTotaler(subset, sum);

		IProblem presenceProblem = new Conjunction(new BitFixer(subset
				.getBackingSet().contains(o1), true), new BitFixer(subset
				.getBackingSet().contains(o2), false), new BitFixer(subset
				.getBackingSet().contains(o3), true));

		IProblem weightProblem = new Conjunction(new NaturalNumberFixer(
				o1.getWeight(), 2L),
				new NaturalNumberFixer(o2.getWeight(), 13L),
				new NaturalNumberFixer(o3.getWeight(), 5L));

		List<IBooleanLiteral> s = new Conjunction(new IProblem[]
		{ totalProblem, presenceProblem, weightProblem }).findModel(Problem
				.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("subset= " + subset);
			System.out.println("sum= " + sum);
		}
		else
			System.out.print("No solution.");
	}
}
