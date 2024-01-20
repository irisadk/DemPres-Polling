Iris Kim

compile:
- ArrayBinaryTree.java
- ArrayHeap.java
- BinaryTree.java
- Candidate.java
- DriverHW06.java
- Entry.java
- PriorityQueue.java

run: java DriverHW06
- supports use of flags -r and -n, and file names including paths
- supports entering the -r flag one or more times
- supports entering flags in either order
examples of acceptable inputs:
- java DriverHW06 dempres_20190218_1.csv
- java DriverHW06 -r Biden -r Harris -n 5 polls/dempres_20190218_1.csv
- java DriverHW06 -n 5 -r Biden Harris Desktop/polls/dempres_20190218_1.csv

peekTopN has a runtime of O(1) but it uses a lot of memory.
it works by copying the heap and polling the first N items from the copy,
leaving the original heap untouched while still utilizing the heap order.

ArrayHeap utilizes an Entry object which has a priority and a value.
I did this so that I could have 2 compare methods, one which compares on
the basis of priority (i.e. polling percentage) and one which compares on
the basis of value (i.e. candidate's last name).

I am aware that the cyclomatic complexity is 12 in my driver.
I do not know how to fix that.

I spent many many hours on this assignment. I no longer remember how many.