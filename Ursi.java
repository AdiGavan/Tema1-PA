// "Copyright [2018] Gavan Adrian-George, 324CA"
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ursi {
	// Primul indice este numarul de caractere '^' deschise
	// Al doilea indice este la al catelea caracter ne aflam
	static long[][] matrix = new long[2003][2003];
	static int modulus = 1000000007;

	public static void solve(BufferedWriter out, String input)
			throws IOException {
		int crt_caracter = 0;

		// Iteram prin stringul citit
		for (char ch : input.toCharArray()) {
			if (crt_caracter == 0) {
				// Daca primul caracter este un '_' atunci nu avem solutie
				if (ch == '_') {
					return;
				}

				matrix[0][0] = 1;
				crt_caracter++;
			}

			for (int poz = 0; poz <= crt_caracter; poz++) {
				// Daca intalnim un caracter de tip '_' atunci suma solutiilor
				// este egala cu suma solutiilor de pe acea pozitie la care
				// adaugam toate posibilitatile de inchidere pana in acel moment
				if (ch == '_') {
					matrix[poz][crt_caracter] += poz
							* matrix[poz][crt_caracter - 1];
					matrix[poz][crt_caracter] %= modulus;
				} else {
					// Daca am intalnit un caracter '^' atunci putem considera
					// ca am inchis
					// toate caracterele ^ deschise anterior cu acesta.
					matrix[poz][crt_caracter] += (poz + 1)
							* matrix[poz + 1][crt_caracter - 1];
					matrix[poz][crt_caracter] %= modulus;

					// Putem de asemenea sa punem ca si cum deschidem un nou
					// caracter
					// ^ si doar vom incrementa valorile de pe pozitia curenta
					if (poz != 0) {
						matrix[poz][crt_caracter] += matrix[poz - 1][crt_caracter - 1];
						matrix[poz][crt_caracter] %= modulus;
					}
				}
			}

			crt_caracter++;
		}

		out.write("" + matrix[0][crt_caracter - 1] + "\n");

	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader in = new BufferedReader(new FileReader("ursi.in"));
		BufferedWriter out = new BufferedWriter(new FileWriter("ursi.out"));

		String line = in.readLine();
		Ursi.solve(out, line);

		in.close();
		out.close();
	}

}
