t	IS	$255
Hello	BYTE	"Hello World!",#a,0

	LOC 	#100

Main 	SET	t,Hello
	TRAP	0,Fputs,StdErr
	SET	$255,0
	TRAP 	0

