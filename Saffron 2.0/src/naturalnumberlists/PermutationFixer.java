package naturalnumberlists;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import naturalnumberlists.exceptions.PermutationFixerException;
import naturalnumbers.NaturalNumberFixer;

public class PermutationFixer extends Problem implements IProblem
{
	public PermutationFixer(Permutation perm, long[] image) throws Exception
	{
		if (perm.getOrder() != image.length)
			throw new PermutationFixerException(
					"Order of Permutation does not match length of image array.");
		if (!perm.proper(image))
			throw new PermutationFixerException(
					"Initializing range array does not produce a permutation.");
		IProblem ret = null;

		for (int i = 0; i < image.length; i++)
			ret = new Conjunction(ret, new NaturalNumberFixer(perm.get(i),
					image[i]));

		this.setClauses(ret.getClauses());
	}
}
