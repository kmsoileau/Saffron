package showcase.fifteenpuzzle;

import bits.Conjunction;
import bits.Disjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 27, 2019
 */
public class FifteenPuzzlePosition extends Problem implements IProblem
{
	public static INaturalNumber sixteen;

	static
	{
		try
		{
			sixteen = new NaturalNumber(16);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String printGame(FifteenPuzzlePosition pos)
	{
		String ret = "";
		for (int row = 0; row < 4; row++)
		{
			ret += "\n";
			for (int col = 0; col < 4; col++)
			{
				ret += pos.getTile(4 * row + col) + "\t";
			}
		}

		return ret;
	}

	private INaturalNumber[] Index;

	private INaturalNumber[] Tile;

	public FifteenPuzzlePosition() throws Exception
	{
		Index = new INaturalNumber[16];
		Tile = new INaturalNumber[16];
		for (int i = 0; i < 16; i++)
		{
			Index[i] = new NaturalNumber(i);
			Tile[i] = new NaturalNumber(0);
		}
		IProblem[] pp = new IProblem[16];
		for (int i = 0; i < 16; i++)
			pp[i] = new Conjunction(new NaturalNumberFixer(this.Index[i], i),
					new NaturalNumberFixer(this.Tile[i], 0));

		this.setClauses(new Conjunction(new NaturalNumberFixer(Index),
				new NaturalNumberFixer(Tile), new Disjunction(pp)).getClauses());
	}

	public FifteenPuzzlePosition(Integer[] position) throws Exception
	{
		Index = new INaturalNumber[16];
		Tile = new INaturalNumber[16];
		for (int i = 0; i < 16; i++)
		{
			Index[i] = new NaturalNumber(i);
			Tile[i] = new NaturalNumber(position[i]);
		}
		IProblem[] pp = new IProblem[16];
		for (int i = 0; i < 16; i++)
			pp[i] = new Conjunction(new NaturalNumberFixer(this.Index[i], i),
					new NaturalNumberFixer(this.Tile[i], position[i]));

		this.setClauses(new Conjunction(new NaturalNumberFixer(Index),
				new NaturalNumberFixer(Tile), new Disjunction(pp)).getClauses());
	}

	public INaturalNumber[] getIndex()
	{
		return Index;
	}

	public INaturalNumber getIndex(int i)
	{
		return this.Index[i];
	}

	public INaturalNumber[] getTile()
	{
		return Tile;
	}

	public INaturalNumber getTile(int i)
	{
		return this.Tile[i];
	}
}


//package showcase.fifteenpuzzle;
//
//import naturalnumbers.NaturalNumber;
//import naturalnumbers.NaturalNumberFixer;
//import bits.Conjunction;
//import bits.Disjunction;
//import bits.INaturalNumber;
//import bits.IProblem;
//import bits.Problem;
//
///**
// *
// * @author Kerry Michael Soileau
// *         <p>
// *         email: ksoileau2@yahoo.com
// *         <p>
// *         website: http://kerrysoileau.com/index.html
// * @version 1.0
// * @since Jan 27, 2019
// */
//public class FifteenPuzzlePosition extends Problem implements IProblem
//{
//	public static INaturalNumber sixteen;
//
//	static
//	{
//		try
//		{
//			sixteen = new NaturalNumber(16);
//		}
//		catch (Exception e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public static String printGame(FifteenPuzzlePosition pos)
//	{
//		String ret = "";
//		for (int row = 0; row < 4; row++)
//		{
//			ret += "\n";
//			for (int col = 0; col < 4; col++)
//			{
//				ret += pos.getTile(4 * row + col) + "\t";
//			}
//		}
//
//		return ret;
//	}
//
//	private INaturalNumber[] Index;
//
//	private INaturalNumber[] Tile;
//
//	public FifteenPuzzlePosition() throws Exception
//	{
//		Index = new INaturalNumber[16];
//		Tile = new INaturalNumber[16];
//		for (int i = 0; i < 16; i++)
//		{
//			Index[i] = new NaturalNumber(i);
//			Tile[i] = new NaturalNumber(0);
//		}
//		IProblem[] pp = new IProblem[16];
//		for (int i = 0; i < 16; i++)
//			pp[i] = new Conjunction(new NaturalNumberFixer(this.Index[i], i),
//					new NaturalNumberFixer(this.Tile[i], 0));
//
//		this.setClauses(new Conjunction(new NaturalNumberFixer(Index),
//				new NaturalNumberFixer(Tile), new Disjunction(pp)).getClauses());
//	}
//
//	public FifteenPuzzlePosition(Integer[] position) throws Exception
//	{
//		Index = new INaturalNumber[16];
//		Tile = new INaturalNumber[16];
//		for (int i = 0; i < 16; i++)
//		{
//			Index[i] = new NaturalNumber(i);
//			Tile[i] = new NaturalNumber(position[i]);
//		}
//		IProblem[] pp = new IProblem[16];
//		for (int i = 0; i < 16; i++)
//			pp[i] = new Conjunction(new NaturalNumberFixer(this.Index[i], i),
//					new NaturalNumberFixer(this.Tile[i], position[i]));
//
//		this.setClauses(new Conjunction(new NaturalNumberFixer(Index),
//				new NaturalNumberFixer(Tile), new Disjunction(pp)).getClauses());
//	}
//
//	public INaturalNumber[] getIndex()
//	{
//		return Index;
//	}
//
//	public INaturalNumber getIndex(int i)
//	{
//		return this.Index[i];
//	}
//
//	public INaturalNumber[] getTile()
//	{
//		return Tile;
//	}
//
//	public INaturalNumber getTile(int i)
//	{
//		return this.Tile[i];
//	}
//}
//

