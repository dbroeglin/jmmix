; does absolutely nothing and exits with status 0
	LOC 	#100

Main	SET $255,5
	TRAP 0,Halt,0
