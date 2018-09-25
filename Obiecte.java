// "Copyright [2018] Gavan Adrian-George, 324CA"
import java.util.Comparator;

class Obiecte {
	Integer jocuri, benzi, taken, sum;

	public static class JonComparator implements Comparator<Obiecte> {
		public int compare(Obiecte ob1, Obiecte ob2) {
			if (ob1.sum.equals(ob2.sum)) {
				return ob2.jocuri - ob1.jocuri;
			}
			return ob2.sum - ob1.sum;
		}
	}

	public static class SamComparator implements Comparator<Obiecte> {
		public int compare(Obiecte ob1, Obiecte ob2) {
			if (ob1.sum.equals(ob2.sum)) {
				return ob2.benzi - ob1.benzi;
			}
			return ob2.sum - ob1.sum;
		}
	}
}
