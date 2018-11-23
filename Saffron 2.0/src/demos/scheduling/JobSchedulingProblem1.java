package demos.scheduling;

import java.util.ArrayList;

import naturalnumbers.NaturalNumber;

/**
*
* @author Kerry Michael Soileau
*         <p>
*         email: ksoileau2@yahoo.com
*         <p>
*         website: http://kerrysoileau.com/index.html
* @version 1.0
* @since 2018/11/22
*/
public class JobSchedulingProblem1
{
	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(100);
		
		Task FP = new Task("FP", 7);
		Task FW = new Task("FW", 7);
		Task BW = new Task("BW", 7);
		Task DE = new Task("DE", 2);
		Task GC = new Task("GC", 3);
		Task CW = new Task("CW", 2);
		Task CR = new Task("CR", 2);
		Task RP = new Task("RP", 8);
		Task LP = new Task("LP", 8);
		Task FA = new Task("FA", 18);

		Task[] tasks = new Task[]
		{ FP, FW, BW, DE, GC, CW, CR, RP, LP, FA };
		FA.setPredecessors(new Task[]
		{ FP, FW, BW, GC, DE });
		BW.setPredecessors(new Task[]
		{ GC, DE });
		GC.setPredecessors(new Task[]
		{ DE });
		CW.setPredecessors(new Task[]
		{ DE });
		LP.setPredecessors(new Task[]
		{ CR, CW, GC });
		RP.setPredecessors(new Task[]
		{ CR, CW, GC });
		CR.setPredecessors(new Task[]
		{ CW });

		Processor A1 = new Processor("A1");
		Processor A2 = new Processor("A2");
		Processor A3 = new Processor("A3");

		Processor[] procs = new Processor[]
		{ A1, A2, A3 };

		ArrayList<ArrayList<Task>> solution = Scheduler.schedule(tasks, procs, 30);
		if(solution==null)
			return;
		for (int i = 0; i < procs.length; i++)
		{
			ArrayList<Task> curr = solution.get(i);
			System.out.println("\n" + procs[i].getName() + ": ");
			for (int j = 0; j < curr.size(); j++)
			{
				Task currTask = curr.get(j);
				System.out.println(currTask.getName() + " start: "
						+ currTask.getNNStart() + " finish: "
						+ currTask.getNNFinish());
			}
		}
	}
}
