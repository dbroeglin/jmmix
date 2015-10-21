z	IS	$0
test	IS	$1
	LOC	Data_Segment
	GREG	@
String	BYTE	0,#A,0

	LOC	#100
Main	SETL	z,13
	CMP	test,z,10
	BNN	test,Letter
	ADD	z,z,'0'
	JMP	Store
Letter	SUB	z,z,10
	ADD	z,z,'A'
Store	STB	z,String
	LDA	$255,String
	TRAP	0,Fputs,StdOut
	TRAP	0,Halt,0
