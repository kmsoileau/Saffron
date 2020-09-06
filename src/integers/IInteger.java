package integers;

import bits.IBooleanVariable;
import bits.INaturalNumber;

/**
 * The <code>IInteger</code> interface must be implemented by any class
 * definition of <code>Integer</code> contemplated as an alternative to the
 * <code>Integer</code> class provided by this package.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2019/02/10
 */
public interface IInteger
{
	INaturalNumber getAbsValue();

	String getName();

	IBooleanVariable getSign();

	long getValue();
}