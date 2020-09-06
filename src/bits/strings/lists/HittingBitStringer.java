/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 20, 2019
 */
package bits.strings.lists;

import bits.IBitString;
import bits.INaturalNumber;

/**
 * 
 *
 */
public class HittingBitStringer extends bits.strings.HittingBitStringer
{
	public HittingBitStringer(IBitStringList C, IBitString hittingSet)
			throws Exception
	{
		super(C.toArray(), hittingSet);
	}

	public HittingBitStringer(IBitStringList C, INaturalNumber bitSum,
			IBitString Y) throws Exception
	{
		super(C.toArray(), bitSum, Y);
	}

	public HittingBitStringer(IBitStringList C, int K, IBitString hittingSet)
			throws Exception
	{
		super(C.toArray(), K, hittingSet);
	}
}
