main:
.data
	a: .word 0
.text
mul $t1, $t1, $t2
div $t3, $t3, $t4
add $t4, $t4, $t5
end:	 jr $ra