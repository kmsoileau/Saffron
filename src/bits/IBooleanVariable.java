package bits;

/**
 * The <code>IBooleanVariable</code> interface must be implemented by any class
 * definition of <code>BooleanVariable</code> contemplated as an alternative to
 * the <code>BooleanVariable</code> class provided by this package.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.11
 * @since 2004/08/26
 */
public interface IBooleanVariable extends Comparable<Object>
{
	/**
	 * Two IBooleanVariables x and y are equal if and only if
	 * x.getName().compareTo(y.getName())==0.
	 * 
	 * @return <code>true</code> if this.getName().compareTo(o.getName())==0.
	 */
	boolean equals(Object o);

	/**
	 * Returns the name as String.
	 *
	 * @return the name as String.
	 */
	String getName();

	/**
	 * Returns true if the logical value is true, otherwise returns false.
	 *
	 * @return logical value: <code>true</code> or <code>false</code>.
	 */
	boolean getValue();

	/**
	 * Sets the logical value: <code>true</code> or <code>false</code>.
	 * 
	 * @param x
	 *            A boolean
	 */
	void setValue(boolean x);
}