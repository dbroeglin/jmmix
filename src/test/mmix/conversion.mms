	LOC  Data_Segment
	GREG @
v12_0	OCTA #4028000000000000
v12_1	OCTA #4028333333333333
v12_5	OCTA #4029000000000000
v12_9	OCTA #4029cccccccccccd

mv12_0	OCTA #c028000000000000
mv12_1	OCTA #c028333333333333
mv12_5	OCTA #c029000000000000
mv12_9	OCTA #c029cccccccccccd

	LOC #100

Main	LDA $200,v12_0
	LDO $200,$200
	LDA $201,v12_1
	LDO $201,$201
	LDA $205,v12_5
	LDO $205,$205
	LDA $209,v12_9
	LDO $209,$209

	LDA $210,mv12_0
	LDO $210,$210
	LDA $211,mv12_1
	LDO $211,$211
	LDA $215,mv12_5
	LDO $215,$215
	LDA $219,mv12_9
	LDO $219,$219


	FIX $10,0,$200
	FIX $11,1,$200
	FIX $12,2,$200
	FIX $13,3,$200
	FIX $14,4,$200
	
	FIX $15,0,$201
	FIX $16,1,$201
	FIX $17,2,$201
	FIX $18,3,$201
	FIX $19,4,$201
	
	FIX $20,0,$205
	FIX $21,1,$205
	FIX $22,2,$205
	FIX $23,3,$205
	FIX $24,4,$205
	
	FIX $25,0,$209
	FIX $26,1,$209
	FIX $27,2,$209
	FIX $28,3,$209
	FIX $29,4,$209
	
	FIX $30,0,$210
	FIX $31,1,$210
	FIX $32,2,$210
	FIX $33,3,$210
	FIX $34,4,$210

	FIX $35,0,$211
	FIX $36,1,$211
	FIX $37,2,$211
	FIX $38,3,$211
	FIX $39,4,$211

	FIX $40,0,$215
	FIX $41,1,$215
	FIX $42,2,$215
	FIX $43,3,$215
	FIX $44,4,$215

	FIX $45,0,$219
	FIX $46,1,$219
	FIX $47,2,$219
	FIX $48,3,$219
	FIX $49,4,$219

	FINT $50,0,$200
	FINT $51,1,$200
	FINT $52,2,$200
	FINT $53,3,$200
	FINT $54,4,$200
	
	FINT $55,0,$201
	FINT $56,1,$201
	FINT $57,2,$201
	FINT $58,3,$201
	FINT $59,4,$201
	
	FINT $60,0,$205
	FINT $61,1,$205
	FINT $62,2,$205
	FINT $63,3,$205
	FINT $64,4,$205
	
	FINT $65,0,$209
	FINT $66,1,$209
	FINT $67,2,$209
	FINT $68,3,$209
	FINT $69,4,$209
	
	FINT $70,0,$210
	FINT $71,1,$210
	FINT $72,2,$210
	FINT $73,3,$210
	FINT $74,4,$210

	FINT $75,0,$211
	FINT $76,1,$211
	FINT $77,2,$211
	FINT $78,3,$211
	FINT $79,4,$211

	FINT $80,0,$215
	FINT $81,1,$215
	FINT $82,2,$215
	FINT $83,3,$215
	FINT $84,4,$215

	FINT $85,0,$219
	FINT $86,1,$219
	FINT $87,2,$219
	FINT $88,3,$219
	FINT $89,4,$219

	SET $2,0 # TODO : why is this one necessary ?

	SET $255,0
	TRAP 0,Halt,0
