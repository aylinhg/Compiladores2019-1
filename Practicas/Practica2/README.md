## Práctica 2 

Para compilar el proyecto, ejecutar el archivo makefile con el comando: 
```bash
make
```

Posteriormente ejecutar: 
```bash
java Parser test.txt
```

### Nota:

La gramática 1 se encuentra en el archivo gramatica1.y
La gramática 2 se encuentra en el archivo gramatica2.y

Para poder probar la salida de la gramática 2, se debe modificar el archivo makefile, en la línea:

```bash
jflex tokens.flex && byaccj -v -Jclass=Parser gramatica2.y
```