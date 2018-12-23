package naturalnumbers;

/**
 * <p>Copyright: Copyright (c) 2005 Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import bits.BitFixer;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberFromBitConverter extends Problem implements IProblem
{
	public NaturalNumberFromBitConverter(INaturalNumber b, IBooleanVariable bv)
			throws Exception
	{
		IProblem convert = new Disjunction(new Conjunction(
				new NaturalNumberFixer(b, 0L), new BitFixer(bv, false)),
				new Conjunction(new NaturalNumberFixer(b, 1L), new BitFixer(bv,
						true)));

		this.setClauses(convert.getClauses());
	}
}