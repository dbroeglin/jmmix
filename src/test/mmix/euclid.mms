m	IS	$1
n	IS 	$2

	LOC 	100
Main	SET	m,1228
	SET	n,96
Start	DIV	m,m,n
	SET	m,n
	GET	n,rR
	BNZ	n,Start

	TRAP	0,Halt,0
