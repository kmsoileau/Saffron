package showcase.binpacking;

import java.util.ArrayList;

import naturalnumbers.binpacking.Bin;
import naturalnumbers.binpacking.BinPacker;
import naturalnumbers.binpacking.Item;

public class BinPackingDemo6
{
	public static void main(String[] args) throws Exception
	{
		long[] weights = new long[]
		{ 1415926535L, 8214808651L, 4428810975L, 7245870066L, 3305727036L, 9333673362L, 5681271L, 4201995611L,
				5024459455L, 5982534904L, 628620899L, 8521105559L, 4543266432L, 4882046652L, 1885752724L, 2931767523L,
				4654958537L, 2978049951L, 5875332083L, 9550031194L, 5820974944L, 4811174502L, 4564856692L, 7892590360L,
				744623799L, 6094370277L, 1468440901L, 5187072113L, 7101000313L, 8903894223L, 5028841971L, 5058223172L,
				3786783165L, 9628292540L, 8193261179L, 6395224737L, 7577896091L, 5981362977L, 3344685035L, 8823537875L,
				8979323846L, 3282306647L, 6659334461L, 631558817L, 5759591953L, 4406566430L, 4526356082L, 2129021960L,
				3469083026L, 2875546873L, 8628034825L, 6446229489L, 1339360726L, 1384146951L, 6912279381L, 8467481846L,
				1050792279L, 597317328L, 8142061717L, 6252505467L, 5923078164L, 8410270193L, 3460348610L, 113305305L,
				6274956735L, 539217176L, 2249534301L, 4999999837L, 7836752886L, 2858849455L, 6939937510L, 5359408128L,
				2712019091L, 9171536436L, 3105118548L, 1907021798L, 7363717872L, 4771309960L, 2619311881L, 9375195778L,
				2643333279L, 938446095L, 2847564823L, 4881520920L, 921861173L, 8602139494L, 7785771342L, 8640344181L,
				4252230825L, 1159562863L, 3421170679L, 5493038196L, 249141273L, 9415116094L, 8301194912L, 7669405132L,
				6892589235L, 1609631859L, 7669147303L, 4157424218L };

		Item[] items = new Item[weights.length];
		for (int i = 0; i < items.length; i++)
		{
			items[i] = new Item("Item-" + i, (long) Math.ceil(weights[i] / 1000000000d));
			System.out.println(i + "\t" + items[i]);
		}

		Bin[] bins = new Bin[10];
		for (int i = 0; i < bins.length; i++)
			bins[i] = new Bin("Bin-0" + i, 50L);

		ArrayList<ArrayList<Item>> solution = BinPacker.pack(items, bins);

		System.out.println(BinPacker.getProblem());
		System.out.println(solution);
	}
}