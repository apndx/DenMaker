# Unit testing

[Unit testing 28th September 2018](https://github.com/apndx/DenMaker/tree/master/Documentation/jacoco280918.jpg). Unit tests need to be improved, more test cases need to be created, especially the logic tests (integration) are still lacking in depth and usefulness.

# Manual testing

The program has been tested manually quite a lot, as the prints of the area give a quick overall idea of how well the dungeon generation is working at the moment.

# Performance testing

First I wanted to get some test results, before I replace the ArrayList and Random Generator with my own versions.

I made a testing option to the text UI. It takes dungeon measurements, the amount of rooms and amount of test rounds as parametres.

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

## Screen shots of results



# How to re-test

Performance tests can be done from the text user interface by choosing the menu option 2. 
