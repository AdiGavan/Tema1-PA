// "Copyright [2018] Gavan Adrian-George, 324CA"
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Frati {
	public static void solve(BufferedWriter out, List<Obiecte> obiecteJon,
			List<Obiecte> obiecteSam) throws IOException {
		// Sortam lista de obiecte asociata lui Jon in primul rand dupa
		// suma obiectelor iar in al doilea rand dupa numarul de jocuri
		Collections.sort(obiecteJon, new Obiecte.JonComparator());

		// Lista de obiecte asociata lui Sam va fi sortata dupa suma
		// si apoi dupa numarul de benzi
		Collections.sort(obiecteSam, new Obiecte.SamComparator());

		Iterator<Obiecte> iteratorJon = obiecteJon.iterator();
		Iterator<Obiecte> iteratorSam = obiecteSam.iterator();
		Obiecte crtObiect;
		int rand = 0;
		Integer sumaSam = 0, sumaJon = 0;

		while (iteratorJon.hasNext() || iteratorSam.hasNext()) {
			// Vom alege cate o competitie pentru fiecare dintre frati pe rand
			// Deoarece sunt sortate dupa suma stim ca se va alege in permanenta
			// cea mai buna suma, iar cand sumele sunt egale fiecare dintre frati
			// va alege concursul ce ii ofera mai multe obiecte preferate
			if (rand == 0) {
				crtObiect = null;
				while (iteratorJon.hasNext()) {
					crtObiect = iteratorJon.next();
					if (crtObiect.taken.equals(0)) {
						crtObiect.taken = 1;
						break;
					} else {
						crtObiect = null;
					}
				}

				if (crtObiect != null) {
					sumaJon += crtObiect.jocuri;
				}
				rand = 1;
			} else {
				crtObiect = null;
				while (iteratorSam.hasNext()) {
					crtObiect = iteratorSam.next();
					if (crtObiect.taken.equals(0)) {
						crtObiect.taken = 1;
						break;
					} else {
						crtObiect = null;
					}
				}
				if (crtObiect != null) {
					sumaSam += crtObiect.benzi;
				}

				rand = 0;
			}
		}

		out.write(sumaJon.toString());
		out.write(" ");
		out.write(sumaSam.toString());
		out.write("\n");

	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		int nrObiecte;

		BufferedReader in = new BufferedReader(new FileReader("frati.in"));
		BufferedWriter out = new BufferedWriter(new FileWriter("frati.out"));

		nrObiecte = Integer.parseInt(in.readLine());

		List<Obiecte> obiecteJon = new ArrayList<Obiecte>(nrObiecte);
		List<Obiecte> obiecteSam = new ArrayList<Obiecte>(nrObiecte);
		String[] parts;

		// Se creeaza doua liste ce vor contine fiecare acelasi obiect
		// ce reprezinta numarul de benzi si de jocuri oferite la un concurs
		for (int i = 0; i < nrObiecte; i++) {
			parts = in.readLine().split("\\s+");

			Obiecte obiect = new Obiecte();
			obiect.jocuri = Integer.parseInt(parts[0]);
			obiect.benzi = Integer.parseInt(parts[1]);
			obiect.taken = 0;
			obiect.sum = obiect.jocuri + obiect.benzi;

			obiecteJon.add(obiect);
			obiecteSam.add(obiect);
		}

		Frati.solve(out, obiecteJon, obiecteSam);

		in.close();
		out.close();
	}

}
