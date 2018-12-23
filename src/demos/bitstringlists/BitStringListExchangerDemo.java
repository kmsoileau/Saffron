package demos.bitstringlists;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;
import bitstringlists.BitStringList;
import bitstringlists.BitStringListExchanger;
import bitstringlists.BitStringListFixer;
import bitstringlists.IBitStringList;
import bitstrings.BitString;

public class BitStringListExchangerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitStringList board1 = new BitStringList(16);
		IBitString[] data1 = new IBitString[16];
		for (int j = 0; j < 16; j++)
			data1[j] = new BitString(4);
		board1 = new BitStringList("board1", data1);
		IBitStringList board2 = new BitStringList(16);
		IBitString[] data2 = new IBitString[16];
		for (int j = 0; j < 16; j++)
			data2[j] = new BitString(4);
		board2 = new BitStringList("board2", data2);

		board1.set(0, new BitString("0011"));
		board1.set(1, new BitString("0001"));
		board1.set(2, new BitString("0100"));
		board1.set(3, new BitString("0101"));
		board1.set(4, new BitString("0010"));
		board1.set(5, new BitString("0110"));
		board1.set(6, new BitString("1001"));
		board1.set(7, new BitString("1000"));
		board1.set(8, new BitString("0111"));
		board1.set(9, new BitString("0000"));
		board1.set(10, new BitString("1110"));
		board1.set(11, new BitString("1111"));
		board1.set(12, new BitString("1100"));
		board1.set(13, new BitString("1101"));
		board1.set(14, new BitString("1010"));
		board1.set(15, new BitString("1011"));

		IProblem problem = new Conjunction(new BitStringListFixer(board1),
				new BitStringListExchanger(board1, board2, 4, 1));

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println(((BitStringList) board1).toBits());
			System.out.println(((BitStringList) board2).toBits());
		}
		else
			System.out.println("There is no solution.");
	}
}
