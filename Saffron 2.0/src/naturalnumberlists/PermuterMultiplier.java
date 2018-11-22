package naturalnumberlists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bits.Conjunction;
import bits.Disjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberEqualizer;
import naturalnumbers.NaturalNumberFixer;

public class PermuterMultiplier extends Problem implements IProblem
{
	public PermuterMultiplier(INaturalNumber x, INaturalNumber y,
			Permutation perm1, Permutation perm2) throws Exception
	{
		INaturalNumber z = new NaturalNumber();
		this.setClauses(new Conjunction(new Permuter(x, z, perm1),
				new Permuter(z, y, perm2)).getClauses());
	}

	public PermuterMultiplier(List<Permutation> permutationList,
			Permutation product) throws Exception
	{
		if (permutationList.size() > 2)
		{
			Permutation partialProduct = new Permutation(product.getOrder());
			ArrayList<Permutation> copy = new ArrayList<Permutation>();
			for (int i = 1; i < permutationList.size(); i++)
				copy.add(permutationList.get(i));
			this.setClauses(new Conjunction(new PermuterMultiplier(copy,
					partialProduct), new PermuterMultiplier(permutationList
					.get(0), partialProduct, product)).getClauses());
		}
		if (permutationList.size() == 2)
			this.setClauses(new PermuterMultiplier(permutationList.get(0),
					permutationList.get(1), product).getClauses());
	}

	public PermuterMultiplier(Permutation perm1, Permutation perm2,
			Permutation product) throws Exception
	{
		IProblem ret = null;
		for (int i = 0; i < perm1.getOrder(); i++)
		{
			IProblem retint = null;
			INaturalNumber perm1num = perm1.get(i);
			INaturalNumber perm3num = product.get(i);
			for (int j = 0; j < perm1.getOrder(); j++)
			{
				retint = new Disjunction(retint, new Conjunction(
						new NaturalNumberEqualizer(perm2.get(j), perm3num),
						new NaturalNumberFixer(perm1num, j)));
			}
			ret = new Conjunction(ret, retint);
		}
		this.setClauses(ret.getClauses());
	}

	public PermuterMultiplier(Permutation[] permutations, Permutation product)
			throws Exception
	{
		this(Arrays.asList(permutations), product);
	}
}
