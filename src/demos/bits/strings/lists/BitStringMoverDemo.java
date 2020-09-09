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
package demos.bits.strings.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bits.strings.BitString;
import bits.strings.lists.BitStringList;
import bits.strings.lists.BitStringListFixer;
import bits.strings.lists.BitStringMover;
import bits.strings.lists.IBitStringList;

/**
 * 
 *
 */
public class BitStringMoverDemo extends Problem implements IProblem
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

		IProblem problem = new Conjunction(new BitStringListFixer(board1), new BitStringMover(board1, board2, 4, 1));

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(((BitStringList) board1).toBits());
			System.out.println(((BitStringList) board2).toBits());
		}
		else
			System.out.println("There is no solution.");
	}

}
