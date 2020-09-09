package in_development;

import java.util.List;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import bits.ProblemDenier;
import bits.exceptions.AlternativeDenialException;

/**
 * An extension of the <code>Problem</code> class which expresses the
 * alternative denial (also called the NAND or Sheffer stroke) of several given
 * <code>IProblem</code>s. More specifically, the <code>IProblem</code> p
 * defined by
 * <p>
 * <code>p=new AlternativeDenial(first,second,third);</code>
 * </p>
 * is satisfied by an <code>ICertificate cert</code> if and only if at least one
 * of <code>first</code>, <code>second</code> or <code>third</code> is not
 * satisfied by <code>cert</code>. It is the logical opposite of a
 * <code>Conjunction</code>.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2007/08/20
 */
public class AlternativeDenial extends Problem implements IProblem
{
	public AlternativeDenial(IProblem first, IProblem second) throws Exception
	{
		this(new IProblem[]
		{ first, second });
	}

	public AlternativeDenial(IProblem first, IProblem second, IProblem third) throws Exception
	{
		this(new IProblem[]
		{ first, second, third });
	}

	public AlternativeDenial(IProblem[] array) throws Exception
	{
		this.setClauses(new ProblemDenier(new Conjunction(array)).getClauses());
	}

	public AlternativeDenial(List<IProblem> list) throws Exception
	{
		if (list == null || list.size() == 0)
			throw new AlternativeDenialException("Null IProblem was passed to constructor.");
		else
			this.setClauses(new AlternativeDenial(list.toArray(new IProblem[0])).getClauses());
	}
}