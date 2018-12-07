package sets;

import naturalnumbers.ConditionalAdder;
import bits.IBitString;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;

public class SubsetWeightedTotaler extends Problem implements IProblem
{
	public SubsetWeightedTotaler(WeightedSet subset, INaturalNumber weightedSum)
			throws Exception
	{
		Set bs = subset.getBackingSet();
		java.util.Set<Object> supp = bs.getSupport();
		int siz = supp.size();
		INaturalNumber[] numbers = new INaturalNumber[siz];
		IBitString membership = new BitString(
				"SubsetWeightedTotalerMembership", siz);

		int index = 0;
		for (Object o : supp)
		{
			numbers[index] = ((WeightedObject) o).getWeight();
			membership.setBooleanVariable(index, bs.contains(o));
			index++;
		}

		this.setClauses(new ConditionalAdder(numbers, membership, weightedSum)
				.getClauses());
	}
}