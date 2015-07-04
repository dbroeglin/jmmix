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


max	OCTA #7fefffffffffffff
inf	OCTA #7ff0000000000000
nan	OCTA #7ff8000000000000


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

	LDA $220,max
	LDO $220,$220
	LDA $221,inf
	LDO $221,$221
	LDA $222,nan
	LDO $222,$222

	FEQL $1,$200,$200
	FEQL $2,$200,$201

	FADD $3,$220,$220 # max + max = inf
	
	FSUB $10,$209,$200
	FSUB $11,$219,$209
	FSUB $12,$220,$220
	FSUB $13,$221,$220
	FSUB $14,$221,$221

	FSUB $15,$221,$221
	FSUB $16,$222,$200

	FREM $20,$215,$201

	FUN $30,$222,$200
	FUN $31,$201,$222
	FUN $32,$222,$222
	FUN $33,$201,$202

	SET $255,0
	TRAP 0,Halt,0
