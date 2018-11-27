# threads
A couple of small projects that show and describing different aspects of topic of Threads

1. package find_max_value  (ExecutorService.execute(new Task)). Set -Xmx60000m before start.
    Search the max value in array contains random int values. Use 3 ways (custom by thread, stream.max, stream.parallel.max) and show time statistics.

    RESULT:
    -Xmx60000m -Xms10000m

    Free memory: 5718
    Count number in array: 1000000000
    Free memory after generate array: 5668
    ------------Find by foreach---------------
    Running Time (millis): 362
    Maximum value in array is: 2147483644
    Free memory: 5668
    ------------Find by custom thread---------------
    Running Time (millis): 185
    Maximum value in array is: 2147483644
    Free memory: 9457
    ------------Find by common stream---------------
    Running Time (millis): 522
    Maximum value in array is: 2147483644
    Free memory: 9532
    ------------Find by parallel stream---------------
    Running Time (millis): 147
    Maximum value in array is: 2147483644
    Free memory: 9357

 
