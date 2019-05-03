Autoøi: 
	Daniel Koneènı	- xkonec00
	Filip Jeøábek 	- xjerab24
Vytvoøeno 3.5.2019 v rámci závìreèného projektu pøedmìtu IJA. VUT FIT.
=========================================
Aplikace pro pøehrávání šachovıch partií.
=========================================
Aplikace umoòuje naèíst šachovou notaci a poté ji pøehrávat. 
Uivatel mùe hru buï krokovat, nebo zvolit automatické pøehrávání.
Souèástí aplikace je také monost realizovat tahy manuálnì.
Pokud je proveden manuální tak, pak je notace od aktuálního tahu pøemazána a je vytvoøena nová.
	Novì vytvoøenou notaci je pak mono uloit do soubroru.

Aplikace implementuje základní tahy figur
	tj. pohyb, brání soupeøovy figurky, promìna pìšce, šach, mat, pat.

Aplikace obsahuje validaci tahù figurkami.
	Pokud není mono s danou figurkou hnout, a u z dùvodu tahu protihráèe, nebo špatného umítnìní figurky,
	vytvoøí se na tento pohyb nová notace a vypíše se do pøehledu. Pøi následujícím validním tahu se pøepíše. 

Souèástí implementace je i monost mít rozehranıch více her zároveò, které jsou na sobì naprosto nezávislé.