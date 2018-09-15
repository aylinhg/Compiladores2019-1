all:
	jflex tokens.flex && byaccj -v -Jclass=Parser gramatica1.y
	javac Letras.java && javac Parser.java

test:
	java Parser test.txt

clean:
	rm -f *.class  *.java* y.tab.c