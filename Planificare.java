// "Copyright [2018] Gavan Adrian-George, 324CA"
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Planificare {
	static long[] solCosturi = new long[1001];
	static int[] solConcurs = new int[1001];

	public static void solve() throws IOException {
		Parameters concursNou = new Parameters();
		Parameters tempConcursRear = new Parameters();
		for (int concursCrt = 1; concursCrt <= Probe.nrProbe; concursCrt++) {
			// Calculam care ar fi costul daca incadram proba curenta
			// intr-un concurs nou
			concursNou.cost = solCosturi[concursCrt - 1]
					+ (Probe.durataConcurs - Probe.probe.get(concursCrt))
					* (Probe.durataConcurs - Probe.probe.get(concursCrt))
					* (Probe.durataConcurs - Probe.probe.get(concursCrt));

			concursNou.timpRamas = Probe.durataConcurs
					- Probe.probe.get(concursCrt);
			concursNou.idxIncConc = concursCrt;
			concursNou.nrConcursuri = solConcurs[concursCrt - 1] + 1;

			// Incercam sa vedem daca rearanjarea concursurilor ar fi mai
			// avantajoasa
			tempConcursRear.transfer(concursNou);

			while (tempConcursRear.idxIncConc > 1
					&& (tempConcursRear.timpRamas - Probe.durataPauza - Probe.probe
							.get(tempConcursRear.idxIncConc - 1)) >= 0) {
				tempConcursRear.idxIncConc--;
				tempConcursRear.timpRamas = tempConcursRear.timpRamas
						- Probe.durataPauza
						- Probe.probe.get(tempConcursRear.idxIncConc);
				tempConcursRear.cost = solCosturi[tempConcursRear.idxIncConc - 1]
						+ tempConcursRear.timpRamas
						* tempConcursRear.timpRamas
						* tempConcursRear.timpRamas;
				tempConcursRear.nrConcursuri = solConcurs[tempConcursRear.idxIncConc - 1] + 1;

				// Memoram doar cel mai bun cost
				if (tempConcursRear.cost < concursNou.cost) {
					concursNou.transfer(tempConcursRear);
				}
			}

			solCosturi[concursCrt] = concursNou.cost;
			solConcurs[concursCrt] = concursNou.nrConcursuri;
		}

		// Scadem timpul ramas la final deoarece nu va mai incepe nicio noua
		// proba
		solCosturi[Probe.nrProbe] -= (concursNou.timpRamas
				* concursNou.timpRamas * concursNou.timpRamas);

		BufferedWriter out = new BufferedWriter(new FileWriter(
				"planificare.out"));
		out.write("" + solCosturi[Probe.nrProbe] + " "
				+ solConcurs[Probe.nrProbe] + "\n");
		out.close();
	}

	public static class Probe {
		static Integer nrProbe, durataConcurs, durataPauza;
		static List<Integer> probe = new ArrayList<Integer>();
		static List<Integer> solutii = new ArrayList<Integer>();
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		Scanner in = new Scanner(new File("planificare.in"));

		Probe.nrProbe = in.nextInt();
		Probe.durataConcurs = in.nextInt();
		Probe.durataPauza = in.nextInt();
		Probe.probe.add(-1);

		for (int i = 0; i < Probe.nrProbe; i++) {
			Probe.probe.add(new Integer(in.nextInt()));
		}

		Planificare.solve();

		in.close();
	}

}
