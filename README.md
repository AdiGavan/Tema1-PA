"Copyright [2018] Gavan Adrian-George, 324CA"

Nume, prenume: Gavan, Adrian-George 

Grupa, seria: 324CA

Tema 1 PA
=========


1. Frati

La problema frati am creat doua liste cu toate concursurile la care pot participa
cei doi frati. Aceste liste contin exact aceleasi elemente (aceleasi obiecte),
dar sunt sortate in ordine diferita. Una dintre valorile retinute pentru fiecare
concurs este daca a fost folosit deja sau nu. Pe ambele le-am sortat dupa suma
elementelor, insa pentru fiecare dintre ele am folosit ca parametru secundar de
obiectele preferate ale fiecarui frate.

Pentru a obtine solutia am parcurs cele doua liste alegand in mod alternativ
care obiect sa fie ales. Deoarece am sortat dupa suma alegerea va fi mereu optima
pentru ca desi unul dintre frati poate capata un numar foarte mic de obiecte,
fratele sau nu va capata mult mai multe crescand astfel diferenta dintre
cei doi. In cazul in care exista mai multe alegeri cu aceeasi suma, fiecare dintre
ei va alege concursul ce ii ofera mai multe puncte.

Algoritmul folosit este Greedy, iar complexitatea este O(n*log(N)) pentru sortarea
folosind Collections.sort, la care se adauga O(n), parcurgerea elementelor si luarea
deciziilor unde n este numarul de concursuri.


2. Ursi

Pentru problema ursi am ales o solutie de programare dinamica in care pe un index
retin la al catelea caracter ma aflu, iar pe cel de-al doilea index am pus cate
caractere '^' "deschise" am inainte. Cu alte cuvinte, dp[i][j] = numarul de moduri
in care pot forma fetele cu primele j elemente astfel incat sa am i fete deschise
(in sursa am alte denumiri pentru matrice si indici, dar aceasta este regula).

La intalnirea unui caracter "_" vom inmulti numarul de solutii pana in acel moment
cu numarul de caractere '^' deschise. Cand intalnim un caracter '^' atunci avem
doua decizii posibile. Fie inchidem oricare dintre caracterele anterioare ^ deschise
caz in care numarul de solutii se inmulteste cu numarul de caractere deshchise pana
in acel moment. A doua decizie este sa deschidem un nou caracter ^ caz in care
doar adaugam numarul de solutii pana in acel moment

Solutia finala se afla pe ultima pozitie la indexul 0. Daca avem un numar impar de
caractere ^ atunci numarul de solutii va ramane 0.

Algoritmul folosit este unul de programare dinamica, ce are complexitatea de
O(n*(n+1)/2) unde n este numarul de caractere citit, deoarece pentru fiecare
caracter citit ne ducem pana la maxim numarul_caracterului + 1 poziti sa
vedem ce operatii putem face cu el.


3. Planificare

Pentru aceasta problema am facut o dinamica pe un vector. Pozitia i din vectorul
nostru reprezinta cel mai bun cost pe care il putem obtine pana in acel moment.
La fiecare pas consider ca cel mai bun cost pe care il pot obtine este daca
pornesc un nou concurs la care adaug cel mai bun cost de la pozitia anterioara.
Apoi incerc sa optimizez acest cost uitandu-ma in spate si incercand sa adaug
probe (cat timp mai incap ca timp) din urma. Continui sa adaug probe pana ce
nu ar mai putea fi adaugate alaturi de toate probele pana la proba curenta.
Dintre toate aceste combinatii memoram doar cel mai bun cost obtinut.

Complexitatea algoritmului este O(n * (n+1)/2), unde n este numarul de probe, insa
in realitate complexitatea se apropie mult mai mult de O(n) deoarece nu ne uitam
mai mult de durataConcurs in urma. Desi pe anumite seturi de date am putea sa
reverificam combinarea cu toate probele anterioare, de cele mai multe ori verificam
un numar mic de probe in urma.


4. Numaratoare

La aceasta problema generez recursiv toate sumele posibile si in momentul in
care generez suma cu numarul x (cea cautata) atunci o afisez si ma opresc.
O optimizare posibila a acestui algoritm ar fi sa retin fiecare suma pe care
am calculat-o deja pentru un numar de pozitii.

Formula ar arata:
recurs(int sum, int nr_termeni, int maxt):
	if(nr_termeni = 2 && nr_solutie++ == solutie_cautata)
		afiseaza;
	for(i = min(sum - nr_termeni + 1, maxt) : ultimul_termen)
		recurs(sum-1, nr_termeni-1. i);
