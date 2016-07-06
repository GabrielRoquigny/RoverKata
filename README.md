# Without any if
## Kata03 : Rover on mars
Try to program without any 'if' help us to program on [Open/closed principle](https://en.wikipedia.org/wiki/Open/closed_principle).

Your mission is to program a rover. 
The rover need to move.
A rover is on map with axis X and Y. 
It have a orientation : north, south, east and west.
Example : 
X = 0, Y = 0, Orientation = North
```
-------
| | | |
-------
|^| | |
-------
```


## Goals
1. Rover need to rotate to the left. Example:*

  ```
  -------
  | | | |
  -------
  |<| | |
  -------
  ```
2. Rover need to rotate to the right. Example:

  ```
  -------
  | | | |
  -------
  |^| | |
  -------
  ```
3. Rover can move. Example:

  ```
  -------
  |^| | |
  -------
  | | | |
  -------
  ```
4. Rover can move back. Example:

  ```
  -------
  | | | |
  -------
  |^| | |
  -------
  ```
5. Rover can do 1,2,3 and 4 multiple time. Example:

  ```
  -------
  | | | |
  -------
  |>| | |
  -------
  ```
6. Rove can move to left. Example:

  ```
  -------
  |>| | |
  -------
  | | | |
  -------
  ```
7. Rove can move to right. Example:

  ```
  -------
  | | | |
  -------
  |>| | |
  -------
  ```
