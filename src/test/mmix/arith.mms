	LOC 	#100

Main	SET	$1,40
	SET	$2,2

	SET	$16,$0
	SET	$0,#ff

	SETH	$3,#ffff
	INCMH	$3,#ffff
	INCML	$3,#ffff
	INCL	$3,#ffff

	SETH	$4,#0123
	INCMH	$4,#4567
	INCML	$4,#89ab
	INCL	$4,#cdef
	
	# 1.5
	SETH	$200,#3ff8
	INCMH	$200,#0000
	INCML	$200,#0000
	INCL	$209,#0000

	# 10.25e15
	SETH	$201,#4342
	INCMH	$201,#3529
	INCML	$201,#0c79
	INCL	$201,#5000

	# MAX_VALUE 
	SETH	$202,#7fef
	INCMH	$202,#ffff
	INCML	$202,#ffff
	INCL	$202,#ffff
	
	
# End of value setup

	ADDU	$5,$1,$2
	ADDU	$5,$3,$2
	ADDU	$5,$1,2
	ADDU	$5,$3,2
	
	ADD	$5,$1,$2
	ADD	$5,$3,$2

	NEG	$13,0,$3
	NEG 	$14,#ff,1
	
	NEGU	$15,0,$3

	2ADDU	$20,$1,$2
	4ADDU	$21,$1,$2
	8ADDU	$22,$1,$2
	16ADDU	$23,$1,$2

	FADD	$25,$200,$201
	FADD	$26,$202,$202

	SET	$255,0
	TRAP 	0
