package showcase.nqueenspuzzle;

import java.util.ArrayList;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberAbsoluteDifferencer;
import naturalnumbers.NaturalNumberBounder;
import naturalnumbers.NaturalNumberPair;
import naturalnumbers.NaturalNumberUnequalizer;

public class NQueensDemo
{
	public static void main(String[] args) throws Exception
	{
		/**
		 * Set Java constants:
		 */

		int N = 20;

		/**
		 * Set globals:
		 */

		NaturalNumber.setLargestNaturalNumber(N - 1);

		/**
		 * Create Saffron objects and arrays:
		 */

		NaturalNumberPair[] placement = new NaturalNumberPair[N];
		ArrayList<IProblem> parray = new ArrayList<IProblem>();

		/**
		 * Create problems which constrain the values of Saffron objects:
		 */

		for (int i = 0; i < N; i++)
		{
			placement[i] = new NaturalNumberPair(new NaturalNumber(), new NaturalNumber());
		}

		for (int i = 0; i < N; i++)
		{
			parray.add(new NaturalNumberBounder(placement[i].getFirst(), N - 1));
			parray.add(new NaturalNumberBounder(placement[i].getSecond(), N - 1));
		}

		for (int i = 0; i < N; i++)
			for (int j = i + 1; j < N; j++)
			{
				parray.add(notAttacking(placement[i], placement[j]));
			}

		/**
		 * Create the Conjunction of all of these constraining problems:
		 */

		IProblem problem = new Conjunction(parray);

		/**
		 * Solve the Conjunction:
		 */

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("\n\tSOLUTION\n--------------------------");
			for (int i = 0; i < N; i++)
			{
				System.out.println("Queen on row " + placement[i].getSecond() + ", column " + placement[i].getFirst());
			}

			Object[][] board = new Object[N][N];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
				{
					board[i][j] = " .";
				}

			for (int i = 0; i < N; i++)
			{
				board[(int) placement[i].getFirst().getValue()][(int) placement[i].getSecond().getValue()] = " Q";
			}

			for (int i = 0; i < N; i++)
			{
				System.out.print("\n");
				for (int j = 0; j < N; j++)
				{
					System.out.print(board[j][i]);
				}
			}

		}
		else
			System.out.println("No solution.");
	}

	static IProblem notAttacking(NaturalNumberPair p1, NaturalNumberPair p2) throws Exception
	{
		return new Conjunction(notOnSameColumn(p1, p2), notOnSameRow(p1, p2), notOnSameDiagonal(p1, p2));
	}

	static IProblem notOnSameColumn(NaturalNumberPair p1, NaturalNumberPair p2) throws Exception
	{
		return new NaturalNumberUnequalizer(p1.getFirst(), p2.getFirst());
	}

	static IProblem notOnSameDiagonal(NaturalNumberPair p1, NaturalNumberPair p2) throws Exception
	{
		INaturalNumber a1 = new NaturalNumber();
		INaturalNumber a2 = new NaturalNumber();
		return new Conjunction(new NaturalNumberAbsoluteDifferencer(p1.getFirst(), p2.getFirst(), a1),
				new NaturalNumberAbsoluteDifferencer(p1.getSecond(), p2.getSecond(), a2),
				new NaturalNumberUnequalizer(a1, a2));
	}

	static IProblem notOnSameRow(NaturalNumberPair p1, NaturalNumberPair p2) throws Exception
	{
		return new NaturalNumberUnequalizer(p1.getSecond(), p2.getSecond());
	}
}
