package sets;

import naturalnumbers.ConditionalAdder;
import naturalnumbers.WeightedObject;
import naturalnumbers.WeightedSet;
import bits.IBitString;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;
import exceptions.sets.SubsetWeightedTotalerException;

public class SubsetWeightedTotaler extends Problem implements IProblem
{
	public SubsetWeightedTotaler(WeightedSet subset, INaturalNumber weightedSum)
			throws Exception
	{
		if (subset == null)
			throw new SubsetWeightedTotalerException(
					"Null passed to constructor as WeightedSet parameter.");
		Set backingSet = subset.getBackingSet();
		if (backingSet == null)
			throw new SubsetWeightedTotalerException(
					"WeightedSet parameter has a null backing set.");
		java.util.Set<Object> supp = backingSet.getSupport();
		int siz = supp.size();
		INaturalNumber[] numbers = new INaturalNumber[siz];
		IBitString membership = new BitString(
				"SubsetWeightedTotalerMembership", siz);

		int index = 0;
		for (Object o : supp)
		{
			numbers[index] = ((WeightedObject) o).getWeight();
			membership.setBooleanVariable(index, backingSet.contains(o));
			index++;
		}

		this.setClauses(new ConditionalAdder(numbers, membership, weightedSum)
				.getClauses());
	}
}