/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 29, 2019
 */
package demos.naturalnumbers;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.IndexSwapper;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;

public class SwapperDemo
{
	public static void main(String[] args) throws Exception
	{
		Integer[] data0 = new Integer[]
		{ 2, 9, 8, 13, 5, 6, 11, 3, 1, 10, 0, 7, 14, 15, 12, 4 };

		Integer[] data1 = new Integer[]
		{ 5, 9, 8, 13, 2, 6, 0, 3, 1, 11, 10, 7, 14, 15, 12, 4 };

		int moves = 3;

		INaturalNumber[][] position = new INaturalNumber[moves + 1][];
		position[0] = new INaturalNumber[16];
		for (int j = 0; j < 16; j++)
			position[0][j] = new NaturalNumber(data0[j]);

		IProblem q1 = new NaturalNumberFixer(position[0]);

		for (int i = 1; i < moves; i++)
		{
			position[i] = new INaturalNumber[16];
			for (int j = 0; j < 16; j++)
				position[i][j] = new NaturalNumber();
		}

		position[moves] = new INaturalNumber[16];
		for (int j = 0; j < 16; j++)
			position[moves][j] = new NaturalNumber(data1[j]);

		IProblem q2 = new NaturalNumberFixer(position[moves]);

		INaturalNumber[] Tile1 = new INaturalNumber[moves];
		INaturalNumber[] tile1Index = new INaturalNumber[moves + 1];
		INaturalNumber[] Tile2 = new INaturalNumber[moves];
		INaturalNumber[] tile2Index = new INaturalNumber[moves + 1];

		IProblem[] validProb = new IndexSwapper[moves];
		for (int i = 0; i < moves; i++)
		{
			Tile1[i] = new NaturalNumber();
			tile1Index[i] = new NaturalNumber();
			Tile2[i] = new NaturalNumber();
			tile2Index[i] = new NaturalNumber();
			validProb[i] = new IndexSwapper(position[i], position[i + 1], Tile1[i], tile1Index[i], Tile2[i],
					tile2Index[i]);
		}

		IProblem q3 = new Conjunction(validProb);

		IProblem problem = new Conjunction(new IProblem[]
		{ q1, q2, q3 });

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < moves + 1; i++)
			{
				System.out.print("\n");
				if (i < moves)
				{
					for (int j = 0; j < 16; j++)
					{
						if (j == tile1Index[i].getValue() || j == tile2Index[i].getValue())
							System.out.print("__ ");
						else
							System.out.print("   ");
					}
				}
				System.out.print("\n");
				for (int j = 0; j < 16; j++)
					System.out.print(String.format("%02d", position[i][j].getValue()) + " ");
			}

			// System.out
			// .println("\ni\tTile1[i]\ttile1Index[i]\tTile2[i]\ttile2Index[i]");
			// for (int i = 0; i < moves; i++)
			// {
			// System.out.println(i + "\t" + Tile1[i] + "\t\t" + tile1Index[i]
			// + "\t\t" + Tile2[i] + "\t\t" + tile2Index[i]);
			// }
		}
		else
			System.out.println("No solution.");
	}
}
