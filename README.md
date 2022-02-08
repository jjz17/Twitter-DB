# Twitter-RDB
DS 4300 HW1: Relational vs. NoSQL Twitter Case Study

## Resources

###### Read CSV Tutorial 
https://www.javatpoint.com/how-to-read-csv-file-in-java

###### Inserting Records into MySQL 
https://www.vogella.com/tutorials/MySQLJava/article.html


## Test Results

###### Posting Tweets

[comment]: <> (Starting performance test...)

[comment]: <> (Start time: 2022/01/30 21:06:57)

[comment]: <> (End time: 2022/01/30 21:12:58)
 - Total runtime: 361.0 seconds

 - Average posts/second: 2770.083102493075

###### Retrieving Timelines

[comment]: <> (Starting performance test...)

[comment]: <> (Start time: 2022/01/30 21:16:25)

[comment]: <> (End time: 2022/01/30 21:17:25)
 - Total runtime: 60.0 seconds

 - Average home timelines retrieved/second: 872.8333333333334

<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>2.8.1</version>
</dependency>