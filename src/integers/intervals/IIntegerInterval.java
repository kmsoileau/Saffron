/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 25, 2019
 */
package integers.intervals;

import integers.IInteger;

/**
 * 
 *
 */
public interface IIntegerInterval
{
	IInteger getLower();

	IInteger getUpper();

	void setLower(IInteger lower);

	void setUpper(IInteger upper);

	@Override
	String toString();
}
