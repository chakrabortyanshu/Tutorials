#!/bin/bash

#echo 'pinging..' $1.1 'through .254'
if [$1 == '']; then
	echo 'Enter IP Address'
	echo 'Syntax: ./ipSweep.sh 192.168.1'
else
	for ip in {1..254..1} 
	do
	  ping -c 1 $1.$ip   | grep "64 bytes" | cut -d " " -f 4 | tr -d ":" &
	done
fi
exit 0



#./ipsweep.sh 192.168.1 > output.txt
#for ip in $(cat output.txt); do nmap -sS -p 80 -T4 $ip & done
