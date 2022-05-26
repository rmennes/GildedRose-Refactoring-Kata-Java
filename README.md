#GildedRose-Refactoring-Kata Java solution by Ruben Mennes

## How to run

### Compile and run tests
The code could be compiled and verify using Maven

```shell
mvn verify
```

The verify command will compile the code, execute unit tests and validate correct behaviour using cucumber BDD tests.

### Run the original main method
Suppose you want to execute to original main method you can use the follwing command to execute the code
```shell
mvn compile exec:java -Dexec.mainClass=com.gildedrose.TexttestFixture
```

In case you want to provide an argument (number of days to simulate) you can extend the previous command as follows
```shell
mvn compile exec:java -Dexec.mainClass=com.gildedrose.TexttestFixture -Dexec.args=<number of days to simulate>
```