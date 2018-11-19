package demos.scheduling;

import java.util.ArrayList;

public class JobSchedulingProblem1
{
	public static void main(String[] args) throws Exception
	{
		Task FP = new Task("FP", 7, null);
		Task FW = new Task("FW", 7, null);
		Task BW = new Task("BW", 7, null);
		Task DE = new Task("DE", 2, null);
		Task GC = new Task("GC", 3, null);
		Task CW = new Task("CW", 2, null);
		Task CR = new Task("CR", 2, null);
		Task RP = new Task("RP", 8, null);
		Task LP = new Task("LP", 8, null);
		Task FA = new Task("FA", 18, null);

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

		Processor[] procs = new Processor[]
		{ A1, A2 };

		ArrayList<ArrayList<Task>> solution = Scheduler.schedule(tasks, procs);

		System.out.println(Scheduler.getProblem());
		for(int i=0;i<procs.length;i++)
			System.out.println(procs[i].getName()+": "+solution.get(i));
	}
}