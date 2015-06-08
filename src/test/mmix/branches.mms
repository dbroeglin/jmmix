	LOC 	#100

Main	SETL	$1,#0
	SETL	$2,#1
	
	SETL	$5,#2a

	SETL	$10,#33
	SETL	$11,#33
	SETL	$12,#33
	SETL	$13,#33

	CSZ	$10,$1,$5
	CSZ	$11,$1,#2b
	CSZ	$12,$2,$5
	CSZ	$13,$2,#2b

	SET	$255,0
	TRAP 	0
