	LOC 	#100

Main	SETH	$1,#0123
	INCMH	$1,#4567
	INCML	$1,#89ab
	INCL	$1,#cdef

	SETH	$2,#1000
	INCMH	$2,#0100
	INCML	$2,#0010
	INCL	$2,#0001

	OR	$3,$1,$2
	OR	$4,$2,#06

	AND	$5,$1,$2
	AND	$6,$1,#06
	
	NOR	$7,$1,$2
	NOR	$8,$2,#06

	NAND	$9,$1,$2
	NAND	$10,$1,#06

	ORN	$11,$1,$2
	ORN	$12,$1,#06

	ANDN	$13,$1,$2
	ANDN	$14,$1,#06

	XOR	$15,$1,$2
	XOR	$16,$1,#06

	NXOR	$17,$1,$2
	NXOR	$18,$1,#06

	SET	$255,0
	TRAP 	0
