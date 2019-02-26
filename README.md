The program is implemented in Java. And the run commands are given in run.sh file. 
The input and output paths are specified in the command line arguments as shown below (3). Please note that the path to the files need to be given based on the platform used. (Written for Linux Environment in general).

(1) javac ./src/CustomComparator.java
(2) javac ./src/Main.java
(3) java Main ./input/input(1).txt ./output/output(1).txt

Approach used:-
 
Using the path provided from the command line, file is read using BufferedReader and the records are iterated line by line skipping the first line (header).

Every line is read using the reader.
Two HashMaps are updated for every iteration (for every row). For both the maps, drug_name is used as the key.

For the first map :-
Key -> drug_name
value -> cost

Here the cost is updated for every record. i.e., cost=cost+new_cost for that particular drug.

For the second map :-
Key -> drug_name
value -> HashSet of doctors (first_name+last_name) who prescribed that particular drug.

Here the doctor is added to set (to maintain unique set) for that particular drug.

After the two maps are updated, the "mpprice" map is loaded into a list along with comparator (CustomComparator).
In CustomComparator the lists are sorted based on cost and then based on the drug_name if the costs are equal.

Finally, iterating through the lists the required fields are written into a file with the fields required.
The required fields are:- drug _name and cost from the list && doctor_count from the "mpdoc" for that specific drug.

I have tested the code with several types of data and it worked fine.
