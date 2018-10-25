# Unit testing

* [Unit testing 28th September 2018](https://github.com/apndx/DenMaker/tree/master/Documentation/jacoco280918.jpg). 
* [Unit testing 12th October 2018](https://github.com/apndx/DenMaker/tree/master/Documentation/jacoco121018.jpg). 
* [Unit testing 22th October 2018](https://github.com/apndx/DenMaker/tree/master/Documentation/jacoco221018.jpg). 
* [Unit testing 25th October 2018](https://github.com/apndx/DenMaker/tree/master/Documentation/jacoco251018.jpg). 

Unit tests could to be improved, more test cases could to be created, especially the logic tests (integration testing) are still lacking in depth and usefulness.


# Manual testing

The program has been tested manually quite a lot during the whole project, as the prints of the area give a quick overall idea of how well the dungeon generation is working at the moment. This also serves as a way to see, if the user interface works as it should.

# Performance testing

First I wanted to get some test results, before I replaced the ArrayList and Random Generator with my own versions.

I made a testing option to the text UI. It takes dungeon measurements, the amount of rooms and amount of test rounds as parametres.

## Test results

### Test round 5.10.2018 

These performance resulst are from tests that have been made 5.10.2018 using imported Java ArrayList and Random generator.

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

### Test round 10.10.2018 

These performance resulst are from tests that have been made 10.10.2018 using my first version of OwnArrayList

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

### Test round 12.10.2018 

This test round has been made after debugging OwnArrayList and using my new default benchmark class:

------------------------


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

### And another try 12.10.2018


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

### Test round 20.10.2018

I continued performance testing 20th October 2018. Very soon I realised that I will make my life a lot easier, if I change the parametres to scale better than the ones I have used previously.

These are the new results:

--------------

Running default test round... Please wait for the results.

Next two rounds: 5 different dens are created with these parametres: 20 room adding attempts, height 49, width 149.

This result is the average result of 5 testrounds.
Area height: 49
Area width: 149
Room count: 11.2
Adding rooms: 2.2 ms
Making maze: 9.6 ms
Opening entrances: 0.8 ms
Trimming dead ends: 1.6 ms

This result is the average result of 5 testrounds.
Area height: 49
Area width: 149
Room count: 9.8
Adding rooms: 1.4 ms
Making maze: 4.2 ms
Opening entrances: 0.0 ms
Trimming dead ends: 1.0 ms

-----------

Next two rounds: 5 different dens are created with these parametres: 200 room adding attempts, height 149, width 490.

This result is the average result of 5 testrounds.
Area height: 149
Area width: 490
Room count: 119.0
Adding rooms: 1.0 ms
Making maze: 29.8 ms
Opening entrances: 24.0 ms
Trimming dead ends: 1.6 ms

This result is the average result of 5 testrounds.
Area height: 149
Area width: 490
Room count: 118.0
Adding rooms: 0.2 ms
Making maze: 29.4 ms
Opening entrances: 21.0 ms
Trimming dead ends: 1.2 ms

-----------

Next two rounds: 5 different dens are created with these parametres: 2000 room adding attempts, height 490, width 1490.
(This might take a while).

This result is the average result of 5 testrounds.
Area height: 490
Area width: 1490
Room count: 1246.6
Adding rooms: 5.6 ms
Making maze: 1015.8 ms
Opening entrances: 3165.0 ms
Trimming dead ends: 21.0 ms

This result is the average result of 5 testrounds.
Area height: 490
Area width: 1490
Room count: 1232.0
Adding rooms: 3.6 ms
Making maze: 938.2 ms
Opening entrances: 3135.8 ms
Trimming dead ends: 16.6 ms


--------------------
### Another test round 22.10.2018

Running default test round... Please wait for the results.

Next two rounds: 5 different dens are created with these parametres: 20 room adding attempts, height 49, width 149.

This result is the average result of 5 testrounds.
Area height: 49
Area width: 149
Room count: 9.6
Adding rooms: 2.2 ms
Making maze: 13.0 ms
Opening entrances: 1.0 ms
Trimming dead ends: 0.8 ms

This result is the average result of 5 testrounds.
Area height: 49
Area width: 149
Room count: 10.4
Adding rooms: 1.2 ms
Making maze: 1.6 ms
Opening entrances: 0.6 ms
Trimming dead ends: 0.4 ms

--------------

Next two rounds: 5 different dens are created with these parametres: 200 room adding attempts, height 149, width 490.

This result is the average result of 5 testrounds.
Area height: 149
Area width: 490
Room count: 120.8
Adding rooms: 0.4 ms
Making maze: 34.2 ms
Opening entrances: 21.4 ms
Trimming dead ends: 1.8 ms

This result is the average result of 5 testrounds.
Area height: 149
Area width: 490
Room count: 121.6
Adding rooms: 1.0 ms
Making maze: 35.8 ms
Opening entrances: 23.8 ms
Trimming dead ends: 1.2 ms

----------------

Next two rounds: 5 different dens are created with these parametres: 2000 room adding attempts, height 490, width 1490.
(This might take a while).

This result is the average result of 5 testrounds.
Area height: 490
Area width: 1490
Room count: 1231.2
Adding rooms: 6.8 ms
Making maze: 953.6 ms
Opening entrances: 3388.0 ms
Trimming dead ends: 22.2 ms

This result is the average result of 5 testrounds.
Area height: 490
Area width: 1490
Room count: 1245.4
Adding rooms: 3.4 ms
Making maze: 912.2 ms
Opening entrances: 3453.4 ms
Trimming dead ends: 15.4 ms

### Graphs of performance tests 20-22.10.2018

<img src="https://github.com/apndx/DenMaker/blob/master/Documentation/performancetest201018.jpg" width="600">

<img src="https://github.com/apndx/DenMaker/blob/master/Documentation/performancetest221018.jpg" width="600">

### Analysis of the performance tests 20-22.10.2018

The performance testing suggests that room creation and trimmig seem to scale quite well, seems that the time requirement is more or less linear O(n) for these.

The maze creation does not work as well, the time requirement seems to be around O(n^2).

The entrance creation seems to suffer most when the numbers get bigger. When te room amount rises from 120 to over 1200, and is ten times bigger, the amount of time needed is 150 times higher, so the time requirement seems to bee O(x * n^2) = O(n^2), not as high as O(n^3) though. 

### Test round 24.10.2018

After going through the code for Entrance opening process, I found the culprit for the rapidly escalating poor performance: it was the region changer. As it function was not yet completed, I decided to leave it out. The performance was improved, and the deterioration in the cave formation was not too alarming. There were stranded caves before, and now maybe a couple more that previously. I decided to get rid of a method that made changes to the regions during entrance opening. Instead I made the entrance opening method to strongly prefere opening the entrances between rooms and maze-corridors. This improved the entrance opening performance significantly and also reduced the amount of stranded rooms.

---------------------------

Running default test round... Please wait for the results.

Next two rounds: 5 different dens are created with these parametres: 20 room adding attempts, height 49, width 149.

This result is the average result of 5 testrounds.
Area height: 49
Area width: 149
Room count: 9.0
Adding rooms: 1.8 ms
Making maze: 13.4 ms
Opening entrances: 0.2 ms
Trimming dead ends: 1.6 ms

This result is the average result of 5 testrounds.
Area height: 49
Area width: 149
Room count: 11.4
Adding rooms: 1.0 ms
Making maze: 4.8 ms
Opening entrances: 0.0 ms
Trimming dead ends: 0.4 ms

Next two rounds: 5 different dens are created with these parametres: 200 room adding attempts, height 149, width 490.

This result is the average result of 5 testrounds.
Area height: 149
Area width: 490
Room count: 118.0
Adding rooms: 0.8 ms
Making maze: 37.6 ms
Opening entrances: 1.4 ms
Trimming dead ends: 1.6 ms

This result is the average result of 5 testrounds.
Area height: 149
Area width: 490
Room count: 119.2
Adding rooms: 0.2 ms
Making maze: 34.0 ms
Opening entrances: 1.4 ms
Trimming dead ends: 2.0 ms

Next two rounds: 5 different dens are created with these parametres: 2000 room adding attempts, height 490, width 1490.
(This might take a while).

This result is the average result of 5 testrounds.
Area height: 490
Area width: 1490
Room count: 1228.4
Adding rooms: 3.2 ms
Making maze: 892.6 ms
Opening entrances: 3.2 ms
Trimming dead ends: 16.0 ms

This result is the average result of 5 testrounds.
Area height: 490
Area width: 1490
Room count: 1252.6
Adding rooms: 3.4 ms
Making maze: 921.2 ms
Opening entrances: 3.0 ms
Trimming dead ends: 15.2 ms

### Graphs of performance test 24.10.2018

<img src="https://github.com/apndx/DenMaker/blob/master/Documentation/performancetest241018.jpg" width="600">

## Screen shots of default maze:

Here are some printouts to make it more tangible how the caves look like. These prints are made with the default cave:

Area height: 49
Area width: 149
Room count: 10
Adding rooms: 0.0 ms
Making maze: 5.0 ms
Opening entrances: 1.0 ms
Trimming dead ends: 0.0 ms

A Den with rooms

<img src="https://github.com/apndx/DenMaker/blob/master/Documentation/den_with_rooms.jpg" width="600">

A Den with mazes

<img src="https://github.com/apndx/DenMaker/blob/master/Documentation/den_with_maze.jpg" width="600">

A Den with entrances

<img src="https://github.com/apndx/DenMaker/blob/master/Documentation/den_with_entrances.jpg" width="600">

A trimmed Den

<img src="https://github.com/apndx/DenMaker/blob/master/Documentation/den_trimmed.jpg" width="600">


# How to re-test 

## Performance

Performance tests can be done from the text user interface by choosing the menu option 2. After this you can choose if you want to run the default test, or modify the parametres.

## Unit tests

The jacoco test report can be created from command line from the code root folder:

```
mvn jacoco:report
```

After that the report can be opened with a browser from a file _target/site/jacoco/index.html_


