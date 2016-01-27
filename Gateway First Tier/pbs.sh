#!/bin/bash
#PBS -N Test
#PBS -l nodes=2:ppn=16
#PBS -l walltime=00:10:00
#PBS -m abe
#PBS -M pmmercha@iu.edu
#PBS -o //N//dc2//scratch//pmmercha//0daa6c5e-a65c-4828-8f2e-9e912b53e2dd//
#PBS -e //N//dc2//scratch//pmmercha//0daa6c5e-a65c-4828-8f2e-9e912b53e2dd//
mpirun -n 32 //N//dc2//scratch//pmmercha//helloapp//hello
if ls //N//dc2//scratch//pmmercha//0daa6c5e-a65c-4828-8f2e-9e912b53e2dd//Test.o* 1> /dev/null 2>&1; then
	echo "The job has been executed . The output files are attached" | mailx -r "TeamIndra" -s "[Attention] : Job Completion Mail(Attachments)" -a //N//dc2//scratch//pmmercha//0daa6c5e-a65c-4828-8f2e-9e912b53e2dd//Test.o* "pmmercha@iu.edu" 
fi