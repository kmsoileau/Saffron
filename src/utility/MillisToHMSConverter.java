/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 21, 2019
 */
package utility;

import java.util.Date;

/**
 * 
 *
 */
public class MillisToHMSConverter
{
	public static void main(String[] args)
	{
		long[] w = MillisToHMS(new Date().getTime());
		System.out.println(w[0] + ":" + w[1] + ":" + w[2]);
	}

	public static long[] MillisToHMS(long millis)
	{
		long seconds = millis / 1000;
		long hours = seconds / 3600;
		long rem1 = seconds - 3600 * hours;
		long minutes = rem1 / 60;
		long rem2 = rem1 - 60 * minutes;

		return new long[]
		{ hours, minutes, rem2 };
	}
}
