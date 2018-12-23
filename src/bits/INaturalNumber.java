package bits;

/**
 * The <code>INaturalNumber</code> interface must be implemented by any class
 * definition of <code>NaturalNumber</code> contemplated as an alternative to
 * the <code>NaturalNumber</code> class provided by this package.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.1
 * @since 2009/05/21
 */
public interface INaturalNumber extends IBitString
{
	final int DEFAULTLENGTH = 5;

	long toDecimal();
}