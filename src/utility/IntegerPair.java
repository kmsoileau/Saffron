/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 5, 2019
 */
package utility;

/**
 * 
 *
 */
public class IntegerPair
{
	private int I1;
	private int I2;

	public IntegerPair(int i1, int i2)
	{
		I1 = i1;
		I2 = i2;
	}

	public int getI1()
	{
		return I1;
	}

	public int getI2()
	{
		return I2;
	}

	public void setI1(int i1)
	{
		I1 = i1;
	}

	public void setI2(int i2)
	{
		I2 = i2;
	}

	@Override
	public String toString()
	{
		return "IntegerPair [I1=" + I1 + ", I2=" + I2 + "]";
	}
}
