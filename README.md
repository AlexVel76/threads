# threads
A couple of small projects that show and describing different aspects of topic of Threads

1. package find_max_value  (ExecutorService.execute(new Task)). Set -Xmx60000m before start.
    Search the max value in array contains random int values. Use 3 ways (custom by thread, stream.max, stream.parallel.max) and show time statistics.

    RESULT:
    -Xmx60000m -Xms10000m

    Count number in array: 1 000 000 000
    ------------Find by foreach---------------
    Running Time (millis): 362
    Maximum value in array is: 2147483644
    ------------Find by custom thread---------------
    Running Time (millis): 185
    Maximum value in array is: 2147483644
    ------------Find by common stream---------------
    Running Time (millis): 522
    Maximum value in array is: 2147483644
    ------------Find by parallel stream---------------
    Running Time (millis): 147
    Maximum value in array is: 2147483644


Count number in array: 10 000 000
------------Find by foreach---------------
Running Time (millis): 15
Maximum value in array is: 2147483547
------------Find by custom thread---------------
Running Time (millis): 31
Maximum value in array is: 2147483547
------------Find by common stream---------------
Running Time (millis): 0
Maximum value in array is: 2147483547
------------Find by parallel stream---------------
Running Time (millis): 19
Maximum value in array is: 2147483547


Count number in array: 100000000
------------Find by foreach---------------
Running Time (millis): 37
Maximum value in array is: 2147483606
------------Find by custom thread---------------
cpu count: 8
Running treads: 8
Running Time (millis): 32
Maximum value in array is: 2147483606
------------Find by common stream---------------
Running Time (millis): 65
Maximum value in array is: 2147483606
------------Find by parallel stream---------------
Running Time (millis): 19
Maximum value in array is: 2147483606

 
