	LOC 	#100

Main	SETL	$1,#0
	SETL	$2,#1
	SETL	$3,#ffff
	
	SETL	$5,#2a

	SETL	$10,#33
	SETL	$11,#33
	SETL	$12,#33
	SETL	$13,#33

	BZ	$1,A
A	BZ	$2,Z
	BN	$3,B	
	SET	$254,#ff dummy
B	BP	$2,C
	SET	$253,#ff dummy
C	BOD	$2,D
	SET	$252,#ff dummy
D	BNZ	$2,E
	SET	$251,#ff dummy
E	BNP	$2,Z
	SET	$250,#ff dummy

Z	SET	$253,#ff dummy
	SET	$255,0
	TRAP 	0
