#!/bin/bash

declare -a timeValues
rm tab.txt
echo "#temps en fonction de la taille du buffer" >> tab.txt 
for i in {1..100..5}
do
	timeValues[i]=$(java CopieFile file2.1 file2.2 $(($i * 256)))
	echo ${timeValues[i]}
	echo $i \ ${timeValues[i]} >> tab.txt
done
gnuplot -p -e "plot 'tab.txt' title 'Temps de traitement en fonction de la taille du buffer' "
exit 0

