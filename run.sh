#!/bin/bash
#
# Use this shell script to compile (if necessary) your code and then execute it. Below is an example of what might be found in this file if your program was written in Python
#
#python ./src/pharmacy_counting.py ./input/itcont.txt ./output/top_cost_drug.txt

javac ./src/CustomComparator.java
javac ./src/Main.java
java Main ./input/input(1).txt ./output/output(1).txt