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

		# Shift with immediate N

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

		# Shift with N in a register

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

		SET	$255,0
		TRAP 	0