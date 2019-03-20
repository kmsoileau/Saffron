/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 7, 2019
 */
package bits;

import java.util.ArrayList;

public interface IProblemMessage
{
	int NOTSATISFIABLE = 0;
	int SATISFIABLE = 1;

	ArrayList<IBooleanLiteral> getLiterals();

	int getStatus();
}
