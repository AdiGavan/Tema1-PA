build: Frati.class Ursi.class numaratoare Planificare.class

Frati.class: Frati.java Obiecte.java
	javac Frati.java Obiecte.java

Ursi.class: Ursi.java
	javac Ursi.java

Planificare.class: Planificare.java Parameters.java
	javac Planificare.java Parameters.java

numaratoare: numaratoare.c
	gcc -Wall -Wextra numaratoare.c -o numaratoare

run-p1:
	java Frati

run-p2:
	java Ursi

run-p3:
	java Planificare

run-p4:
	./numaratoare

clean:
	rm -f Frati Ursi Planificare numaratoare *.class
