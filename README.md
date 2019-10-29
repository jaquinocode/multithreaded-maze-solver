# Multithreaded Maze Solver

### Core Design Choices:

* Data-driven design allows for a neat separation between the visual elements and the maze AI while solving.
* Multithreaded design creates the ability for the AI to continue solving despite changes to the maze during the solving procedure.

<br/>

![randomizing](https://i.imgur.com/9ueBAgf.gif)
_The agent being unbothered when randomizing the maze_

<br/>

---

![fastest solution](https://i.imgur.com/RuqJmps.gif)
_The agent returning to start through fastest found solution._

<br/>

---

![changing tiles](https://i.imgur.com/5XSRdPq.gif)
_Changing the maze in real-time w/ my mouse. This works for any maze, not just this unique example._

<br/>

---

![big boy maze](https://i.imgur.com/ETSLVQX.gif)
_Works for larger maze sizes like this (smaller too). The top-right 2 numbers show the size, 35x35 in this case. Unfortunately, at this size the canvas refreshes get really annoying._
