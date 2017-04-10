            LOC  Data_Segment
pos    	    GREG   @
Load_Test   OCTA   #8081828384858687
            OCTA   #88898a8b8c8d8e8f

a           GREG   #7f6001b4c67bc809
b           GREG   #8081828384858687
c           GREG   #88898a8b8c8d8e8f
d           GREG


            LOC  #100
Main        SET  d,a # dummy
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
            TRAP 0,Halt,0
