// "Copyright [2018] Gavan Adrian-George, 324CA"
#include <stdio.h>
#include <stdlib.h>

long long search_sum, crt_sum_num;
int solutie[150], nr_total_term, S;
FILE *input, *output;

// Iteram prin intreg spatiul de adrese
void recurs(int sum, int nr_termeni, int maxt) {
	int termen, ultimul_termen, iter;

	// Daca am ajuns la capat verificam daca solutia obtinuta
	// este cea valida
	if (nr_termeni == 2) {
		crt_sum_num++;
		if(crt_sum_num > search_sum) {
			solutie[nr_total_term - 2] = sum / 2;
			if (sum % 2 != 0)
				solutie[nr_total_term - 2]++;
			solutie[nr_total_term - 1] = sum / 2;
			fprintf(output, "%d=", S);
			for(iter = 0; iter < nr_total_term - 1; iter++)
				fprintf(output, "%d+", solutie[iter]);
			fprintf(output, "%d", solutie[nr_total_term - 1]);
			exit(0);
		}
		return;
	}

	// Iteram prin toate valorile posibile ale
	// termenului curent
	ultimul_termen = sum / nr_termeni;
	if (sum % nr_termeni != 0)
		ultimul_termen++;

	termen = sum - nr_termeni + 1;
	if (termen > maxt)
		termen = maxt;

	for(; termen >= ultimul_termen; termen--) {
		solutie[nr_total_term - nr_termeni] = termen;
		recurs(sum - termen, nr_termeni - 1, termen);
	}
}

int main() {
	input = fopen("numaratoare.in", "r");
	output = fopen("numaratoare.out", "w");

	fscanf(input, "%d%d%lld", &S, &nr_total_term, &search_sum);

	// Daca avem un singur termen atunci nu putem forma
	// decat o singura suma
	if (nr_total_term == 1) {
		if(search_sum == 0) {
			fprintf(output, "%d=%d\n", S, S);
		} else {
			fprintf(output, "-\n");
		}
		return 0;
	}

	crt_sum_num = 0;
	recurs(S, nr_total_term, 151);

	fprintf(output, "-\n");

	return 0;
}
