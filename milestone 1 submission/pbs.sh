#!/bin/bash
#PBS -N Test
#PBS -l nodes=2:ppn=16
#PBS -l walltime=00:10:00
#PBS -m abe
#PBS -M pmmercha@iu.edu
mpirun -n 32 //N//dc2//scratch//pmmercha//helloapp//hello
if ls //N//dc2//scratch//pmmercha//3525bbcb-e7bd-47c1-bde3-7959360d75e1//Test.o* 1> /dev/null 2>&1; then
	echo "The job has been executed . The output files are attached" | mailx -r "TeamIndra" -s "[Attention] : Job Completion Mail(Attachments)" -a //N//dc2//scratch//pmmercha//3525bbcb-e7bd-47c1-bde3-7959360d75e1//Test.o* "pmmercha@iu.edu" 
fi