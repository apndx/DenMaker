# Manual

Load the file [DenMaker.jar](https://github.com/apndx/DenMaker..) not there yet

## Starting the program

You can start the program from the command line using command

```
java -jar DenMaker.jar
```

## Text User Interface

The program starts from a main menu. Here you can choose to make an ASCII presentation of the den making process (1), or run a test round to test performance (2). You can also quit the program (3).

```
Welcome to DenMaker!
 
 
What to do next?
1. Create a new Den
2. Test mode
3. Quit

```

### Option 1, Den making

```
1. Create a new Den

Let's make the den area, please give measurements.
Do you want to change the default height 49?
If yes, please type a number that is  between 10 and 999.

```

Here you can either make a den with the default parametres (simply press <enter> to each of the three questions) or you can give den height, width and the amount of room installing attempts.

After the parametres are given, the program prints four scenes from the den making process:

* A den with rooms
* A den with a maze
* A den where the rooms have doors to the maze or other rooms
* A den where the extra paths have been trimmed away

After the printouts there is some statistic data from the creation, and you can choose again from the main menu how to continue.

### Option 2, Test mode

```
2. Test mode


1. Default test mode
2. Manual test mode

```

The test mode is devided to two options. Default test mode will do a default test round that is described in more detail in the [testing document](https://github.com/apndx/DenMaker/blob/master/Documentation/testing_document.md).

In the manual test mode you can choose the height, width and the amount of room adding attempts for the tested den area, and also how many test runs to perform. After this the results are given.





