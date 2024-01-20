For this project, I implemented an array-based heap using Java's PriorityQueue interface.

The driver reads in any number of files containing polling information: csv files of the format
SURNAME, FULL NAME, percentage of votes received. The data is stored in Candidate objects, each of
which contains the name of the candidate, the date of the most recent poll they were a part of, and
the percentage of votes received in that poll. The Candidate objects are all stored in the array-
based heap. The driver prints out the heap.

Compile all .java files:
- ArrayBinaryTree.java
- ArrayHeap.java
- BinaryTree.java
- Candidate.java
- DriverHW06.java
- Entry.java
- PriorityQueue.java

Run: 'java DriverHW06' followed by the file name(s).
If more than one file name is inputted, the program will read in the first file
and print out the heap, then read in the next one and print out the updated heap, etc.
The flag -r will remove/ignore the names of the candidates following it.
The flag -n will print a list of the top x candidates, where x is the number
following the flag.
The flags may be entered in either order. The file name(s) must come last.

examples of acceptable inputs:
- java DriverHW06 dempres_20190218_1.csv
- java DriverHW06 -r Biden -r Harris -n 5 Desktop/dempres_20190218_1.csv
- java DriverHW06 -n 5 -r Biden Harris dempres_20190218_1.csv dempres_20190220_1.csv dempres_20190317_1.csv


ArrayHeap utilizes an Entry object which has a priority and a value.
I did this so that I could have 2 compare methods, one which compares on
the basis of priority (i.e. polling percentage) and one which compares on
the basis of value (i.e. candidate's last name).
