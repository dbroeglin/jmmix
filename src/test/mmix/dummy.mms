	LOC 	#100

Main 	SYNCD	128,$1,$2
% 	SYNCDI	128,$1,$2 % Does not exist ?
	SYNCID	128,$1,$2
%	SYNCIDI	128,$1,$2 % Does not exist ?
	PREST	128,$1,$2
%	PRESTI	128,1,2   % Does not exist ?

	SET	$255,0
	TRAP 	0
