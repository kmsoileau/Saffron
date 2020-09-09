package showcase.fifteenpuzzle;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.IndexSwapper;
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
 * @since Jan 26, 2019
 */

public class FifteenPuzzlePositionDemo
{
	public static void main(String[] args) throws Exception
	{
		/**
		 * Set Java variables:
		 */
		Integer[] data0 = new Integer[]
		{ 0, 12, 9, 13, 15, 11, 10, 14, 3, 7, 2, 5, 4, 8, 6, 1 };

		Integer[] data1 = new Integer[]
		{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0 };

		int moves = 80;

		/**
		 * Set globals:
		 */

		NaturalNumber.setLargestNaturalNumber(15);

		/**
		 * Create Saffron objects and arrays:
		 */

		INaturalNumber[][] position = new INaturalNumber[moves + 1][];
		position[0] = new INaturalNumber[16];
		for (int j = 0; j < 16; j++)
			position[0][j] = new NaturalNumber(data0[j]);
		printBoard(position[0]);

		for (int i = 1; i < moves; i++)
		{
			position[i] = new INaturalNumber[16];
			for (int j = 0; j < 16; j++)
				position[i][j] = new NaturalNumber();
		}

		position[moves] = new INaturalNumber[16];
		for (int j = 0; j < 16; j++)
			position[moves][j] = new NaturalNumber(data1[j]);

		INaturalNumber[] Tile1 = new INaturalNumber[moves];
		INaturalNumber[] tile1Index = new INaturalNumber[moves + 1];
		INaturalNumber[] Tile2 = new INaturalNumber[moves];
		INaturalNumber[] tile2Index = new INaturalNumber[moves + 1];

		IProblem[] validProblemArray = new IProblem[moves];

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		IProblem startPositionProblem = new NaturalNumberFixer(position[0]);

		IProblem endPositionProblem = new NaturalNumberFixer(position[moves]);

		for (int i = 0; i < moves; i++)
		{
			Tile1[i] = new NaturalNumber();
			tile1Index[i] = new NaturalNumber();
			Tile2[i] = new NaturalNumber();
			tile2Index[i] = new NaturalNumber();
			IProblem cc = new Conjunction(
					new IndexSwapper(position[i], position[i + 1], Tile1[i], tile1Index[i], Tile2[i], tile2Index[i]),
					new AdjacentTiler(tile1Index[i], tile2Index[i]), new NaturalNumberFixer(Tile1[i], 0));
			validProblemArray[i] = cc;
		}
		IProblem validMovesProblem = new Conjunction(validProblemArray);

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem problem = new Conjunction(new IProblem[]
		{ startPositionProblem, endPositionProblem, validMovesProblem });

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 1; i <= moves; i++)
				printBoard(position[i]);
		}
		else
			System.out.println("No solution.");
	}

	static void printBoard(INaturalNumber[] position)
	{
		String ret = "\n";
		for (int row = 0; row < 4; row++)
		{
			ret += "\n";
			for (int col = 0; col < 4; col++)
			{
				ret += String.format("%2d ", position[4 * row + col].getValue());
			}
		}
		System.out.println(ret);
	}
}