package showcase.binpacking;

import java.util.ArrayList;

import naturalnumbers.binpacking.Bin;
import naturalnumbers.binpacking.BinPacker;
import naturalnumbers.binpacking.Item;

public class BinPackingDemo2
{
	public static void main(String[] args) throws Exception
	{
		long[] weights = new long[]
		{ 442L, 252L, 252L, 252L, 252L, 252L, 252L, 252L, 127L, 127L, 127L,
				127L, 127L, 106L, 106L, 106L, 106L, 85L, 84L, 46L, 37L, 37L,
				12L, 12L, 12L, 10L, 10L, 10L, 10L, 10L, 10L, 9L, 9L };

		Item[] items = new Item[weights.length];
		for (int i = 0; i < items.length; i++)
			items[i] = new Item("Item-" + i, weights[i]);

		Bin[] bins = new Bin[7];
		for (int i = 0; i < bins.length; i++)
			bins[i] = new Bin("Bin-0" + i, 523L);

		ArrayList<ArrayList<Item>> solution = BinPacker.pack(items, bins);

		System.out.println(BinPacker.getProblem());
		System.out.println(solution);
	}
}
