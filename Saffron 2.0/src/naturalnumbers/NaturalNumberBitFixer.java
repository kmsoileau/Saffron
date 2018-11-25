package naturalnumbers;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import naturalnumbers.exceptions.NaturalNumberBitFixerException;
import bits.BitFixer;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberBitFixer extends Problem implements IProblem
{
	public NaturalNumberBitFixer(INaturalNumber b) throws Exception
	{
		if (b == null)
			throw new NaturalNumberBitFixerException(
					"Null INaturalNumber was passed to a constructor.");
		BitFixer[] bf = new BitFixer[b.size()];
		for (int i = 0; i < bf.length; i++)
			bf[i] = new BitFixer(b.getBooleanVariable(i));
		IProblem problem = new Conjunction(bf);
		this.setClauses(problem.getClauses());
	}

	public NaturalNumberBitFixer(INaturalNumber b, int bit, boolean val)
			throws Exception
	{
		if (b == null)
			throw new NaturalNumberBitFixerException(
					"Null INaturalNumber was passed to a constructor.");
		if (bit < 0 || bit > NaturalNumber.getLength() - 1)
			throw new NaturalNumberBitFixerException(
					"Attempt was made to index an INaturalNumber outside its range of definition.");
		BitFixer bf = new BitFixer(b.getBooleanVariable(bit), val);
		this.setClauses(((IProblem) bf).getClauses());
	}
}