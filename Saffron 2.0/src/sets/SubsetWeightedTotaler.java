package sets;

import naturalnumbers.ConditionalAdder;
import naturalnumbers.NaturalNumber;
import bits.IBitString;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;

public class SubsetWeightedTotaler extends Problem implements IProblem
{
	public SubsetWeightedTotaler(WeightedSet subset) throws Exception
	{
		Set bs = subset.getBackingSet();
		java.util.Set<Object> supp = bs.getSupport();
		int siz = supp.size();
		INaturalNumber[] numbers = new INaturalNumber[siz];
		IBitString membership = new BitString(
				"SubsetWeightedTotalerMembership", siz);
		INaturalNumber conditionalSum = new NaturalNumber(
				"SubsetWeightedTotalerSum");

		int index = 0;
		for (Object o : supp)
		{
			numbers[index++] = ((WeightedObject) o).getWeight();
			membership.setBooleanVariable(index, bs.contains(o));
		}

		this.setClauses(new ConditionalAdder(numbers, membership,
				conditionalSum).getClauses());
	}
}