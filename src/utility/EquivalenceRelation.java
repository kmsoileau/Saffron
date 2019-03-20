package utility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implements the concept of an equivalence relation R over a set of elements X.
 * For any x,y in X, xRy means "x is equivalent to y."
 *
 * An equivalence relation R over a set X has the following properties:
 *
 * For any x in X, xRx. For any x,y in X, xRy if and only if yRx. For any x,y,z
 * in X, xRy and yRz implies xRz.
 *
 * To use this class, one creates an instance of the class, then passes
 * equivalent objects to the instance by means of the add method. The result is
 * the smallest/simplest equivalence relation consistent with the object pairs
 * added by the add method.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2004/08/26
 */
public class EquivalenceRelation extends HashMap<Object, Set<Object>>
{
	private static final long serialVersionUID = -9098553501315972641L;

	public static EquivalenceRelation intersect(EquivalenceRelation rel1,
			EquivalenceRelation rel2)
	{
		return EquivalenceRelation.intersect(new EquivalenceRelation[]
		{ rel1, rel2 });
	}

	public static EquivalenceRelation intersect(EquivalenceRelation rel1,
			EquivalenceRelation rel2, EquivalenceRelation rel3)
	{
		return EquivalenceRelation.intersect(new EquivalenceRelation[]
		{ rel1, rel2, rel3 });
	}

	public static EquivalenceRelation intersect(EquivalenceRelation[] rels)
	{
		EquivalenceRelation ret = new EquivalenceRelation();
		Set<Object> intersect = rels[0].keySet();
		for (int i = 1; i < rels.length; i++)
			intersect.retainAll(rels[i].keySet());
		if (intersect == null || intersect.size() == 0)
			return null;

		for (Object o : intersect)
		{
			Set<Object> clss = rels[0].get(o);
			for (int i = 1; i < rels.length; i++)
				clss.retainAll(rels[i].get(o));
			if (clss == null || clss.size() == 0)
				continue;
			for (Object h : clss)
				ret.add(o, h);
		}
		return ret;
	}

	public static void main(String[] args)
	{
		EquivalenceRelation digits = new EquivalenceRelation();

		for (int i = 40; i < 110; i++)
			for (int j = 67; j < 123; j++)
				if (("" + i).length() == ("" + j).length())
					digits.add("" + i, "" + j);

		System.out.println("-------------------------");
		System.out.println(digits.equivalenceClasses());
	}

	/*
	 * public static void main(String[] args) { EquivalenceRelation polygon1 =
	 * new EquivalenceRelation(); EquivalenceRelation polygon2 = new
	 * EquivalenceRelation();
	 * 
	 * polygon1.add("a","b"); polygon1.add("a","d"); polygon1.add("a","e");
	 * polygon1.add("a","h"); polygon1.add("a","i"); polygon1.add("c","k");
	 * polygon1.add("c","j"); polygon1.add("c","f"); polygon1.add("c","g");
	 * 
	 * polygon2.add("a","b"); polygon2.add("a","c"); polygon2.add("a","k");
	 * polygon2.add("a","d"); polygon2.add("a","f"); polygon2.add("a","i");
	 * polygon2.add("e","j"); polygon2.add("e","g"); polygon2.add("e","h");
	 * 
	 * //System.out.println(polygon1);
	 * System.out.println("-------------------------");
	 * System.out.println(polygon1.equivalenceClasses());
	 * System.out.println(polygon2.equivalenceClasses());
	 * System.out.println(EquivalenceRelation.intersect(polygon1,polygon2)
	 * .equivalenceClasses());
	 * 
	 * }
	 */

	/**
	 * Changes this instance to the smallest/simplest equivalence relation
	 * consistent with the previously added object pairs and the object pair
	 * currently added.
	 * 
	 * @param object1
	 *            Object
	 * @param object2
	 *            Object
	 */
	public void add(Object object1, Object object2)
	{
		if (object1 == null || object2 == null)
			return;

		HashSet<Object> object1Class = (HashSet<Object>) (super.get(object1));
		if (object1Class == null)
		{
			object1Class = new HashSet<Object>();
			object1Class.add(object1);
			super.put(object1, object1Class);
		}
		HashSet<Object> object2Class = (HashSet<Object>) (super.get(object2));
		if (object2Class == null)
		{
			object2Class = new HashSet<Object>();
			object2Class.add(object2);
			super.put(object2, object2Class);
		}
		HashSet<Object> union = new HashSet<Object>();
		if (object1Class != null)
			union.addAll(object1Class);
		if (object2Class != null)
			union.addAll(object2Class);
		super.put(object1, union);
		super.put(object2, union);

		Iterator<Object> i1 = super.keySet().iterator();
		while (i1.hasNext())
		{
			Object element = i1.next();
			HashSet<Object> equivalenceClass = (HashSet<Object>) (super
					.get(element));
			Iterator<Object> i2 = equivalenceClass.iterator();
			while (i2.hasNext())
			{
				HashSet<Object> equivalenceClassClone = extracted(equivalenceClass);
				equivalenceClassClone.addAll((super.get(i2.next())));
				equivalenceClass = equivalenceClassClone;
			}
			super.put(element, equivalenceClass);
		}
	}

	/**
	 * Returns <code>true</code> if o1 R o2, i.e. if o1 is equivalent to o2
	 * under this equivalence relation, otherwise <code>false</code> is
	 * returned.
	 *
	 * @return <code>true</code> if o1 is equivalent to o2 under this
	 *         equivalence relation.
	 * @param o1
	 *            Object
	 * @param o2
	 *            Object
	 */
	public boolean areRelated(Object o1, Object o2)
	{
		return equivalenceClass(o1).equals(equivalenceClass(o2));
	}

	/**
	 * Returns a HashSet containing the equivalence class of the object x,
	 * namely the set of objects y such that xRy.
	 *
	 * @return A HashSet containing the equivalence class of the object x,
	 *         namely the set of objects y such that xRy.
	 * @param x
	 *            Object
	 */
	public HashSet<Object> equivalenceClass(Object x)
	{
		return (HashSet<Object>) (super.get(x));
	}

	/**
	 * Returns a HashSet containing the equivalence classes for the equivalence
	 * relation R. This is a disjoint partition of the total set X.
	 *
	 * @return A HashSet containing the equivalence classes for the equivalence
	 *         relation R.
	 */
	public HashSet<Object> equivalenceClasses()
	{
		HashSet<Object> classes = new HashSet<Object>();
		Iterator<Object> sit = super.keySet().iterator();
		while (sit.hasNext())
			classes.add(super.get(sit.next()));
		return classes;
	}

	@SuppressWarnings("unchecked")
	private HashSet<Object> extracted(HashSet<Object> equivalenceClass)
	{
		return (HashSet<Object>) (equivalenceClass.clone());
	}

	@SuppressWarnings("unchecked")
	private HashSet<Object> extracted(Iterator<Object> it)
	{
		return (HashSet<Object>) it.next();
	}

	public Object[][] toArray()
	{
		Object[][] res = new Object[this.equivalenceClasses().size()][];
		int count = 0;
		Iterator<Object> it = this.equivalenceClasses().iterator();
		while (it.hasNext())
		{
			HashSet<Object> hashSet = extracted(it);
			Object[] hsarray = hashSet.toArray();
			if (hsarray.length > 0 && hsarray[0] instanceof Comparable)
				Arrays.sort(hsarray);
			res[count++] = hsarray;
		}
		return res;
	}

	/**
	 * Returns a HashMap representing the equivalence classes for the
	 * equivalence relation R. The Object[][] ary is computed using the
	 * toArray() method. For the equivalence class corresponding to ary[i], the
	 * representative of that class is defined to be the Object ary[i][0]. So
	 * each member ary[i][j] is HashMapped to its representative ary[i][0]
	 *
	 * @return A HashMap taking each member to its representative.
	 */
	public HashMap<Object, Object> toHashMap()
	{
		return toHashMap(this.toArray());
	}

	/**
	 * Returns a HashMap in which each member ary[i][j] is HashMapped to its
	 * representative ary[i][0].
	 *
	 * @return A HashMap.
	 * @param ary
	 *            Object[][]
	 */
	public HashMap<Object, Object> toHashMap(Object[][] ary)
	{
		HashMap<Object, Object> res = new HashMap<Object, Object>();
		for (int i = 0; i < ary.length; i++)
			for (int j = 0; j < ary[i].length; j++)
				res.put(ary[i][j], ary[i][0]);

		return res;
	}

	@Override
	public String toString()
	{
		String res = "";
		Iterator<Object> it = super.keySet().iterator();
		while (it.hasNext())
		{
			Object key = it.next();
			res += key + "\t->\t" + (super.get(key)) + "\n";
		}
		return res;
	}
}