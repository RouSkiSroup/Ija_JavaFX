Auto�i: 
	Daniel Kone�n�	- xkonec00
	Filip Je��bek 	- xjerab24
Vytvo�eno 3.5.2019 v r�mci z�v�re�n�ho projektu p�edm�tu IJA. VUT FIT.
=========================================
Aplikace pro p�ehr�v�n� �achov�ch parti�.
=========================================
Aplikace umo��uje na��st �achovou notaci a pot� ji p�ehr�vat. 
U�ivatel m��e hru bu� krokovat, nebo zvolit automatick� p�ehr�v�n�.
Sou��st� aplikace je tak� mo�nost realizovat tahy manu�ln�.
Pokud je proveden manu�ln� tak, pak je notace od aktu�ln�ho tahu p�emaz�na a je vytvo�ena nov�.
	Nov� vytvo�enou notaci je pak mo�no ulo�it do soubroru.

Aplikace implementuje z�kladn� tahy figur
	tj. pohyb, br�n� soupe�ovy figurky, prom�na p�ce, �ach, mat, pat.

Aplikace obsahuje validaci tah� figurkami.
	Pokud nen� mo�no s danou figurkou hnout, a� u� z d�vodu tahu protihr��e, nebo �patn�ho um�tn�n� figurky,
	vytvo�� se na tento pohyb nov� notace a vyp�e se do p�ehledu. P�i n�sleduj�c�m validn�m tahu se p�ep�e. 

Sou��st� implementace je i mo�nost m�t rozehran�ch v�ce her z�rove�, kter� jsou na sob� naprosto nez�visl�.