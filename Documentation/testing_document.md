# Unit testing

[Unit testing 28th September 2018](https://github.com/apndx/DenMaker/tree/master/Documentation/jacoco280918.jpg). 
[Unit testing 12th October 2018](https://github.com/apndx/DenMaker/tree/master/Documentation/jacoco121018.jpg). 

Unit tests need to be improved, more test cases need to be created, especially the logic tests (integration) are still lacking in depth and usefulness.


# Manual testing

The program has been tested manually quite a lot, as the prints of the area give a quick overall idea of how well the dungeon generation is working at the moment.

# Performance testing

First I wanted to get some test results, before I replace the ArrayList and Random Generator with my own versions.

I made a testing option to the text UI. It takes dungeon measurements, the amount of rooms and amount of test rounds as parametres.

## Test results

### These performance resulst are from tests that have been made 5.10.2018 using imported Java ArrayList and Random generator.

---------------

Area height: 49
Area width: 149
Room count: 9

This result is the average result of 5 testrounds.
Adding rooms: 6.0 ms
Making maze: 28.0 ms
Opening entrances: 3.0 ms
Trimming dead ends: 2.0 ms

---------------

Area height: 49
Area width: 149
Room count: 10

This result is the average result of 5 testrounds.
Adding rooms: 7.0 ms
Making maze: 27.0 ms
Opening entrances: 4.0 ms
Trimming dead ends: 2.0 ms

---------------

Area height: 99
Area width: 199
Room count: 43

This result is the average result of 5 testrounds.
Adding rooms: 14.0 ms
Making maze: 24.0 ms
Opening entrances: 9.0 ms
Trimming dead ends: 4.0 ms

---------------

Area height: 99
Area width: 199
Room count: 46

This result is the average result of 5 testrounds.
Adding rooms: 8.0 ms
Making maze: 23.0 ms
Opening entrances: 14.0 ms
Trimming dead ends: 6.0 ms

----------------

Area height: 999
Area width: 999
Room count: 814

This result is the average result of 5 testrounds.
Adding rooms: 19.0 ms
Making maze: 280.0 ms
Opening entrances: 3208.0 ms
Trimming dead ends: 51.0 ms

-----------------

The opening entrances algorithm seems to be working quite slowly when the area size gets bigger. This is something I will analyse further next week.

----------------

### These performance resulst are from tests that have been made 10.10.2018 using my first version of OwnArrayList

Area height: 49
Area width: 149
Room count: 9

This result is the average result of 5 testrounds.
Adding rooms: 2.8 ms
Making maze: 33.2 ms
Opening entrances: 1.4 ms
Trimming dead ends: 1.6 ms

---------------

Area height: 49
Area width: 149
Room count: 10

This result is the average result of 5 testrounds.
Adding rooms: 1.6 ms
Making maze: 29.4 ms
Opening entrances: 0.2 ms
Trimming dead ends: 1.4 ms

---------------

Area height: 99
Area width: 199
Room count: 49

This result is the average result of 5 testrounds.
Adding rooms: 1.2 ms
Making maze: 36.8 ms
Opening entrances: 4.4 ms
Trimming dead ends: 1.6 ms

-----------------

Area height: 99
Area width: 199
Room count: 48

This result is the average result of 5 testrounds.
Adding rooms: 0.4 ms
Making maze: 29.0 ms
Opening entrances: 3.8 ms
Trimming dead ends: 1.0 ms

---------------

Area height: 999
Area width: 999
Room adding attempts: 999

With these measurements the waiting for the average result of 5 testrounds took so long that it got me to ponder the philosophy of the halting problem. Finally I interrupted. It seems that this version MyArrayList scales poorly to larger DungeonAre, probably the removal is too slow:

------ 
  private void moveToLeft(int fromIndex) {
        for (int i = fromIndex; i < this.size - 1; i++) {
            this.values[i] = this.values[i + 1];
        }
    }

    public void remove(T value) {
        int valueIndex = valueIndex(value);
        if (valueIndex < 0) {
            return; // not found
        }
        moveToLeft(valueIndex);
        this.size--;
    }
-------------

One testroud brought the confirmation of this:

Area height: 999
Area width: 999
Room count: 805

This result is the average result of 1 testrounds.
Adding rooms: 17.0 ms
Making maze: 7642.0 ms
Opening entrances: 4788.0 ms
Trimming dead ends: 60.0 ms

-----------

I made changes to remove method, now the array is made smaller when it has been emptied enough.

Testresults after the change:


Area height: 49
Area width: 149
Room count: 13

This result is the average result of 5 testrounds.
Adding rooms: 0.0 ms
Making maze: 4.8 ms
Opening entrances: 0.4 ms
Trimming dead ends: 1.0 ms

----------------

Area height: 49
Area width: 149
Room count: 11

This result is the average result of 5 testrounds.
Adding rooms: 0.2 ms
Making maze: 7.6 ms
Opening entrances: 0.8 ms
Trimming dead ends: 1.2 ms

-------------------

Area height: 99
Area width: 199
Room count: 46

This result is the average result of 5 testrounds.
Adding rooms: 0.0 ms
Making maze: 19.2 ms
Opening entrances: 3.2 ms
Trimming dead ends: 1.2 ms

-----------------

Area height: 99
Area width: 199
Room count: 49

This result is the average result of 5 testrounds.
Adding rooms: 1.2 ms
Making maze: 18.0 ms
Opening entrances: 1.4 ms
Trimming dead ends: 1.0 ms

------------

Area height: 999
Area width: 999
Room count: 819

This result is the average result of 5 testrounds.
Adding rooms: 3.6 ms
Making maze: 4512.0 ms
Opening entrances: 4153.6 ms
Trimming dead ends: 45.0 ms

-----

The maze building is still slower than when using the default Java ArrayList, but not nearly as bad as it was before the change to remove method. I think I will move now to work with the random generator.

Random generator is still in progress, but I tried optimising by changing the tile content into string only when printing. Now the test perform like this:

----------------

Area height: 49
Area width: 149
Room count: 10

This result is the average result of 5 testrounds.
Adding rooms: 0.2 ms
Making maze: 4.4 ms
Opening entrances: 0.2 ms
Trimming dead ends: 0.6 ms

------------------

Area height: 49
Area width: 149
Room count: 8

This result is the average result of 5 testrounds.
Adding rooms: 1.0 ms
Making maze: 2.8 ms
Opening entrances: 0.2 ms
Trimming dead ends: 0.6 ms

----------------------

Area height: 99
Area width: 199
Room count: 48

This result is the average result of 5 testrounds.
Adding rooms: 0.8 ms
Making maze: 9.0 ms
Opening entrances: 3.6 ms
Trimming dead ends: 1.0 ms

---------------

Area height: 99
Area width: 199
Room count: 40

This result is the average result of 5 testrounds.
Adding rooms: 0.0 ms
Making maze: 3.8 ms
Opening entrances: 1.8 ms
Trimming dead ends: 0.8 ms

--------------

Area height: 999
Area width: 999
Room count: 803

This result is the average result of 5 testrounds.
Adding rooms: 3.0 ms
Making maze: 2213.2 ms
Opening entrances: 2834.6 ms
Trimming dead ends: 22.8 ms

-----------------

This test is 12.10.2018 after debugging OwnArrayList and using my new default benchmark class:


Running default test round... Please wait for the results.

Next two rounds: 5 different dens are created with these parametres: 20 room adding attempts, height 49, width 149.

This result is the average result of 5 testrounds.
Area height: 49
Area width: 149
Room count: 10.8
Adding rooms: 0.6 ms
Making maze: 11.8 ms
Opening entrances: 1.4 ms
Trimming dead ends: 2.2 ms

This result is the average result of 5 testrounds.
Area height: 49
Area width: 149
Room count: 10.2
Adding rooms: 1.4 ms
Making maze: 2.4 ms
Opening entrances: 0.8 ms
Trimming dead ends: 0.2 ms

------------------

Next two rounds: 5 different dens are created with these parametres: 100 room adding attempts, height 99, width 199.

This result is the average result of 5 testrounds.
Area height: 99
Area width: 199
Room count: 45.0
Adding rooms: 0.2 ms
Making maze: 4.4 ms
Opening entrances: 2.2 ms
Trimming dead ends: 0.4 ms

This result is the average result of 5 testrounds.
Area height: 99
Area width: 199
Room count: 45.4
Adding rooms: 0.4 ms
Making maze: 6.8 ms
Opening entrances: 3.6 ms
Trimming dead ends: 0.6 ms

-------------

Next round: 5 different dens are created with these parametres: 999 room adding attempts, height 999, width 999.
(This might take a while).

This result is the average result of 5 testrounds.
Area height: 999
Area width: 999
Room count: 810.8
Adding rooms: 2.2 ms
Making maze: 2569.6 ms
Opening entrances: 3059.4 ms
Trimming dead ends: 23.6 ms

----------------

And another try (12.10.2018):


Running default test round... Please wait for the results.

Next two rounds: 5 different dens are created with these parametres: 20 room adding attempts, height 49, width 149.

This result is the average result of 5 testrounds.
Area height: 49
Area width: 149
Room count: 10.2
Adding rooms: 2.2 ms
Making maze: 3.4 ms
Opening entrances: 0.8 ms
Trimming dead ends: 0.4 ms

This result is the average result of 5 testrounds.
Area height: 49
Area width: 149
Room count: 10.6
Adding rooms: 0.2 ms
Making maze: 1.2 ms
Opening entrances: 0.2 ms
Trimming dead ends: 0.4 ms

----------------------

Next two rounds: 5 different dens are created with these parametres: 100 room adding attempts, height 99, width 199.

This result is the average result of 5 testrounds.
Area height: 99
Area width: 199
Room count: 42.6
Adding rooms: 0.0 ms
Making maze: 4.6 ms
Opening entrances: 1.6 ms
Trimming dead ends: 0.6 ms

This result is the average result of 5 testrounds.
Area height: 99
Area width: 199
Room count: 42.2
Adding rooms: 0.0 ms
Making maze: 5.4 ms
Opening entrances: 2.6 ms
Trimming dead ends: 0.0 ms

-----------------------------

Next round: 5 different dens are created with these parametres: 999 room adding attempts, height 999, width 999.
(This might take a while).

This result is the average result of 5 testrounds.
Area height: 999
Area width: 999
Room count: 804.4
Adding rooms: 2.4 ms
Making maze: 2543.6 ms
Opening entrances: 3008.6 ms
Trimming dead ends: 30.2 ms

------------


## Screen shots of results


# How to re-test 

## Performance

Performance tests can be done from the text user interface by choosing the menu option 2. After this you can choose, if you want to run the default test, or modify the parametres.

## Unit tests

The jacoco test report can also be created from command line from the code root folder:

```
mvn jacoco:report
```

After that the report can be opened with a browser from a file _target/site/jacoco/index.html_


