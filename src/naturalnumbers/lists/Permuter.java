package naturalnumbers.lists;

import bits.INaturalNumber;
import bits.IProblem;
import bits.Mapper;
import bits.Problem;
import bits.ProblemPair;
import naturalnumbers.NaturalNumberEqualizer;
import naturalnumbers.NaturalNumberFixer;

public class Permuter extends Problem implements IProblem
{
	public Permuter(INaturalNumber x, INaturalNumber y, Permutation perm) throws Exception
	{
		ProblemPair[] pp = new ProblemPair[perm.getOrder()];
		for (int i = 0; i < pp.length; i++)
			pp[i] = new ProblemPair(new NaturalNumberFixer(x, i), new NaturalNumberEqualizer(y, perm.get(i)));
		this.setClauses(new Mapper(pp).getClauses());
	}
}
