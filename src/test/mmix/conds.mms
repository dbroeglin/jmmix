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

	CSP	$30,$2,$9	
	CSP	$31,$2,#ee
	CSP	$32,$3,$9	
	CSP	$33,$3,#ee

	SET	$34,#dd
	SET	$35,#dd
	SET	$36,#dd
	SET	$37,#dd
	ZSP	$34,$2,$9	
	ZSP	$35,$2,#ee
	ZSP	$36,$3,$9	
	ZSP	$37,$3,#ee

	CSOD	$40,$1,$9	
	CSOD	$41,$1,#ee
	CSOD	$42,$2,$9	
	CSOD	$43,$2,#ee

	SET	$44,#dd
	SET	$45,#dd
	SET	$46,#dd
	SET	$47,#dd
	ZSOD	$44,$1,$9	
	ZSOD	$45,$1,#ee
	ZSOD	$46,$2,$9	
	ZSOD	$47,$2,#ee

	CSNN	$50,$1,$9	
	CSNN	$51,$1,#ee
	CSNN	$52,$2,$9	
	CSNN	$53,$2,#ee

	SET	$54,#dd
	SET	$55,#dd
	SET	$56,#dd
	SET	$57,#dd
	ZSNN	$54,$1,$9	
	ZSNN	$55,$1,#ee
	ZSNN	$56,$2,$9	
	ZSNN	$57,$2,#ee

	CSNZ	$60,$1,$9	
	CSNZ	$61,$1,#ee
	CSNZ	$62,$2,$9	
	CSNZ	$63,$2,#ee

	SET	$64,#dd
	SET	$65,#dd
	SET	$66,#dd
	SET	$67,#dd
	ZSNZ	$64,$1,$9	
	ZSNZ	$65,$1,#ee
	ZSNZ	$66,$2,$9	
	ZSNZ	$67,$2,#ee

	CSNP	$70,$1,$9	
	CSNP	$71,$1,#ee
	CSNP	$72,$2,$9	
	CSNP	$73,$2,#ee

	SET	$74,#dd
	SET	$75,#dd
	SET	$76,#dd
	SET	$77,#dd
	ZSNP	$74,$1,$9	
	ZSNP	$75,$1,#ee
	ZSNP	$76,$2,$9	
	ZSNP	$77,$2,#ee

	CSEV	$80,$1,$9	
	CSEV	$81,$1,#ee
	CSEV	$82,$2,$9	
	CSEV	$83,$2,#ee

	SET	$84,#dd
	SET	$85,#dd
	SET	$86,#dd
	SET	$87,#dd
	ZSEV	$84,$1,$9	
	ZSEV	$85,$1,#ee
	ZSEV	$86,$2,$9	
	ZSEV	$87,$2,#ee

	SET	$255,0
	TRAP 	0
