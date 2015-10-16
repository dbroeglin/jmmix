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
B	SET	$254,#ff dummy
	BP	$2,Z
C	SET	$253,#ff dummy

Z	SET	$255,0
	TRAP 	0
