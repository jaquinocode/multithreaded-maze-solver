<center> <h1>Multithreaded Maze Solver</h1> </center>

<center> <h3>Core Design Choices</h3> </center>

- Data-driven design allows for a neat separation between the visual elements and the maze AI while solving.
- Multithreaded design creates the ability for the AI to continue solving despite changes to the maze during the solving procedure.

<br/>

![randomizing](https://i.imgur.com/9ueBAgf.gif)
<center><i>The agent being unbothered when randomizing the maze.</i></center><br/>

---

![fastest solution](https://i.imgur.com/RuqJmps.gif)
<center><i>The agent returning to start through fastest found solution.</i></center><br/>

---

![changing tiles](https://i.imgur.com/5XSRdPq.gif)
<center><i>Changing the maze in real-time w/ my mouse. This works for any maze, not just this unique example.</i></center><br/>

---

![big boy maze](https://i.imgur.com/ETSLVQX.gif)
<center><i>Works for larger maze sizes like this (smaller too). The top-right 2 numbers show the size, 35x35 in this case. Unfortunately, at this size the canvas refreshes get really annoying.</i></center>
