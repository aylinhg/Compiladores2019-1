all:
	jflex Atomos.jflex && byaccj -v -Jclass=Parser Parser.y
	javac Alexico.java && javac Parser.java

clean:
	rm -f *.class  *.java* y.tab.c