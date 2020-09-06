/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 11, 2019
 */
package demos.naturalnumbers;

import naturalnumbers.NaturalNumber;
import naturalnumbers.Number;
import naturalnumbers.exceptions.NaturalNumberException;
import naturalnumbers.exceptions.NumberException;

/**
 * 
 *
 */
public class NumberDemo
{
	public static void main(String[] args) throws NumberException, NaturalNumberException
	{
		NaturalNumber.setLargestNaturalNumber(25000);
		Number number1 = new Number(2401l);
		Number number2 = new Number(number1, 12);
		System.out.println(number1.toString());
		System.out.println(number2.toString());
	}
}
