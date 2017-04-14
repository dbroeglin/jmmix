            LOC  Data_Segment
pos    	    GREG   @
Load_Test   OCTA   #8081828384858687
            OCTA   #88898a8b8c8d8e8f
sto1        GREG   @
            OCTA   #0000000000000001
            OCTA   #0000000000000001
sto2        GREG   @
            OCTA   #0000000000000001
            OCTA   #0000000000000001
sto3        GREG   @
            OCTA   #0000000000000001
            OCTA   #0000000000000001
a           GREG
b           GREG


            LOC  #100
Main        SET  b,a # dummy

            LDB  $1,pos,0
            LDB  $2,pos,1
            LDB  $3,pos,2
            LDB  $4,pos,3
            LDB  $5,pos,4
            LDB  $6,pos,5
            LDB  $7,pos,6
            LDB  $8,pos,7

            LDW  $9,pos,0
            LDW  $10,pos,4
            LDW  $11,pos,8
            LDW  $12,pos,12

            LDT  $13,pos,0
            LDT  $14,pos,8

            LDO  $15,pos,0

            SET  $20,0
            SET  $21,1
            SET  $22,2
            SET  $23,3
            SET  $24,4
            SET  $25,5
            SET  $26,6
            SET  $27,7

            SET  $28,8
            SET  $29,12

            LDB  $31,pos,$20
            LDB  $32,pos,$21
            LDB  $33,pos,$22
            LDB  $34,pos,$23
            LDB  $35,pos,$24
            LDB  $36,pos,$25
            LDB  $37,pos,$26
            LDB  $38,pos,$27

            LDW  $39,pos,$21
            LDW  $40,pos,$22
            LDW  $41,pos,$24
            LDW  $42,pos,$27

            LDT  $43,pos,$21
            LDT  $44,pos,$28

            LDO  $45,pos,$29

            SETH  $150,#ffff
            SETMH $150,#ffff
            SETML $150,#ffff
            SETL  $150,#ff00

            STB  $150,sto1,$21
            STB  $150,sto1,3
            STBU $150,sto1,5
            STBU $150,sto1,$27

            STW  $150,sto1,8
            STW  $150,sto1,29
            STWU $150,sto2,0
            STWU $150,sto2,$24

            # Load the values back in register for test validation
            LDO $200,sto1
            LDO $201,sto1,8
            LDO $202,sto2
            LDO $203,sto2,8
            LDO $204,sto3
            LDO $205,sto3,8

            TRAP 0,Halt,0
