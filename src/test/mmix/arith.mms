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

	SET	$255,0
	TRAP 	0
