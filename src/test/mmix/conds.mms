	LOC 	#100

Main	SET	$1,-1
	SET	$2,0
	SET	$3,1
	
	SET	$9,#ff

	CSZ	$10,$1,$9	
	CSZ	$11,$1,#ee
	CSZ	$12,$2,$9	
	CSZ	$13,$2,#ee

	SET	$14,#dd
	SET	$15,#dd
	SET	$16,#dd
	SET	$17,#dd
	ZSZ	$14,$1,$9	
	ZSZ	$15,$1,#ee
	ZSZ	$16,$2,$9	
	ZSZ	$17,$2,#ee

	CSN	$20,$1,$9	
	CSN	$21,$1,#ee
	CSN	$22,$2,$9	
	CSN	$23,$2,#ee

	SET	$24,#dd
	SET	$25,#dd
	SET	$26,#dd
	SET	$27,#dd
	ZSN	$24,$1,$9	
	ZSN	$25,$1,#ee
	ZSN	$26,$2,$9	
	ZSN	$27,$2,#ee

	SET	$255,0
	TRAP 	0
