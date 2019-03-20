/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 19, 2018
 */
package utility;

import java.util.HashMap;

public class Clocks
{
	private static HashMap<String, Clock> clocks = new HashMap<String, Clock>();

	public static void addClock(String clockName)
	{
		clocks.put(clockName, new Clock(clockName));
	}

	public static Clock getClock(String clockName)
	{
		return clocks.get(clockName);
	}

	public static void removeClock(String clockName)
	{
		clocks.remove(clocks.get(clockName));
	}

	public Clocks()
	{
		super();
	}
}
