COMP 310 HW03: Composition-based Ballworld
Jayson Carter, Jon Erickson

This application displays different bouncing balls. The user can write in
different update and paint strategies and add them to the dropdown lists by clicking on 
"Add to list". From there, the user can then choose a strategy in the top dropdown list 
and hit the "Make ball" button to create a ball with that strategy. 

To combine different strategies the user should select a strategy in the top and
bottom dropdown lists and then hit the "Combine!" button. This will then put 
that combination into both lists. That strategy can then be combined with other
strategies if desired. As before, the user simply selects the strategy he or she
desires in the top list and then hit the "Make ball" button to create it.

This version of Ballworld now includes ball-to-ball interactions. Aside from 
the basic Collide interaction, there are three interact strategies which we have
included. Two of these are collision-based interactions: ColorCollide changes its color
every time it collides with another ball, and AlternateSize toggles between two sizes
whenever it collides with another ball. ColorChanger is not collision-based; it randomly
changes the color of every ball on the screen after a certain interval.

Also, as one might expect, the "Clear all" button removes all the balls from
the screen.

Our application has several update strategies to choose from. There is the 
standard "Straight" strategy that follows a straight path. There is also a 
"Breathing" strategy that "breathes" by increasing and decreasing its radius as 
it moves in a straight line. The "Curve" strategy follows a circular path 
instead of going straight. The "Color" strategy rapidly changes colors. 
Lastly, the "Wandering" strategy meanders around the screen. Finally,
the switcher strategy allows one to switch the update strategy
of any switcher ball to the currently selected strategy by clicking "Switch!"
Typing the full class name of the update strategies could also work.

We also include a variety of paint strategies: A simple ball, Fish0, Fish1,
a square, an ellipse, an image of Venus, and an EyedFish0 paint strategy which
will always remain upright (so that the fish is not swimming "upside down", so
to speak).

Multiple balls of different types may be displayed at the same time. The GUI may
be resized and the balls will still follow the proper boundaries.