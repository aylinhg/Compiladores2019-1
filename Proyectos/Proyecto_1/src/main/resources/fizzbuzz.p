#FIZZBUZZ hasta 100

contador = 1
while (contador <= 100):
	if contador % 3 == 0:
  		print "fizz"	

	if contador % 5 == 0:
		print "buzz"

	if contador % 3 != 0 and contador % 5 != 0:
  		print contador

	contador = contador + 1 