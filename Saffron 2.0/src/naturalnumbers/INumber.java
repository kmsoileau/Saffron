package naturalnumbers;

import exceptions.naturalnumbers.NumberException;

/**
 * The <code>INumber</code> interface must be implemented by any class
 * definition of <code>Number</code> contemplated as an alternative to the
 * <code>Number</code> class provided by this package.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.01
 * @since 2005/05/04
 */
public interface INumber
{
	public boolean equals(Object anObject);

	public boolean getBit(int i) throws NumberException;

	public boolean[] getBitArray();

	public String getName();

	public int getSize();

	public void setBit(int i, boolean b) throws NumberException;

	public void setBitArray(boolean[] bitArray) throws NumberException;

	public void setName(String name);

	public void setOverflow(boolean overflow);
}