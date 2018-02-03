	LOC 	#100

Main	SET	$0,0
		SET	$1,0
		SET	$2,2
		SET	$3,4
		SET	$4,8
		SET $5,16
		SET	$6,32
	

		SETH	$13,#ffff
		INCMH	$13,#ffff
		INCML	$13,#ffff
		INCL	$13,#ffff

		SETH	$14,#ffff
		INCMH	$14,#fff8
		INCML	$14,#ffff
		INCL	$14,#fff8

		SETH	$15,#0F0F
		INCMH	$15,#FFFF
		INCML	$15,#FFFF
		INCL	$15,#FFFF
	
# End of value setup

		# Shift left with immediate N

		SL		$100,$13,0
		GET     $101,rA
		PUT     rA,0

		SL		$102,$13,2
		GET     $103,rA
		PUT     rA,0

		SL		$104,$14,32
		GET     $105,rA
		PUT     rA,0

		SL		$106,$15,4
		GET     $107,rA
		PUT     rA,0

		SL		$108,$15,8
		GET     $109,rA
		PUT     rA,0

		SL		$110,$13,2
		GET     $111,rA

		# Shift left with N in a register

		SL		$120,$13,0
		GET     $121,rA
		PUT     rA,0

		SL		$122,$13,2
		GET     $123,rA
		PUT     rA,0

		SL		$124,$14,32
		GET     $125,rA
		PUT     rA,0

		SL		$126,$15,4
		GET     $127,rA
		PUT     rA,0

		SL		$128,$15,8
		GET     $129,rA
		PUT     rA,0

		SL		$130,$13,2
		GET     $131,rA
		PUT     rA,0

		# Shift right 'unsigned' with immediate N

		SRU		$160,$13,0
		GET     $161,rA
		PUT     rA,0

		SRU		$162,$14,0
		GET     $163,rA
		PUT     rA,0

		SRU		$164,$13,3
		GET     $165,rA
		PUT     rA,0

		SRU		$166,$15,4
		GET     $167,rA
		PUT     rA,0

		SRU		$168,$15,8
		GET     $169,rA
		PUT     rA,0

		SRU		$170,$13,2
		GET     $171,rA

		# Shift right 'unsigned' with N in a register

		SRU		$180,$13,0
		GET     $181,rA
		PUT     rA,0

		SRU		$182,$13,2
		GET     $183,rA
		PUT     rA,0

		SRU		$184,$14,32
		GET     $185,rA
		PUT     rA,0

		SRU		$186,$15,4
		GET     $187,rA
		PUT     rA,0

		SRU		$188,$15,8
		GET     $189,rA
		PUT     rA,0

		SRU		$190,$13,2
		GET     $191,rA
		PUT     rA,0


		# Shift right 'signed' with immediate N

		SR		$200,$13,0
		GET     $201,rA
		PUT     rA,0

		SR		$202,$14,0
		GET     $203,rA
		PUT     rA,0

		SR		$204,$13,3
		GET     $205,rA
		PUT     rA,0

		SR		$206,$15,4
		GET     $207,rA
		PUT     rA,0

		SR		$208,$15,8
		GET     $209,rA
		PUT     rA,0

		SR		$210,$13,2
		GET     $211,rA

		# Shift right 'signed' with N in a register

		SR		$220,$13,0
		GET     $221,rA
		PUT     rA,0

		SR		$222,$13,2
		GET     $223,rA
		PUT     rA,0

		SR		$224,$14,32
		GET     $225,rA
		PUT     rA,0

		SR		$226,$15,4
		GET     $227,rA
		PUT     rA,0

		SR		$228,$15,8
		GET     $229,rA
		PUT     rA,0

		SR		$230,$13,2
		GET     $231,rA
		PUT     rA,0


		SET	$255,0
		TRAP 	0