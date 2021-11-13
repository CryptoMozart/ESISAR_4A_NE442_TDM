#!/bin/bash

declare -a timeValues
rm tab.txt
echo "#temps en fonction du nombre de thread" >> tab.txt 
for i in {2..64..2}
do
	timeValues[i]=$(java PiMultiThreadAdapt $i)
	echo ${timeValues[i]}
	echo $i \ ${timeValues[i]} >> tab.txt
done
gnuplot -p -e "plot 'tab.txt' title 'Temps de traitement en fonction du nombre de thread' "
exit 0

