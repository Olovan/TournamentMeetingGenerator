# Tournament Meeting Generator

This program is designed for the IHSAA cross country board to run graphical simulations to categorize schools 
for sectionals, regionals, and semi-state based on distance from a host school. Options to change host schools,
introduce a class system, add a fifth semi-state, and more are given to the user. This project is for CS 360 
Software Engineering at IPFW Fort Wayne, Indiana.

## Getting Started

When the program is run a panel with two button appears. "Generate" will ask for a file containing school
enrollment located in the "etc" folder. "Load" will ask for a pre-generated, serialized tournament file 
to display on the map on the following screen. On this screen a map is on the left and a list of host 
schools are on the right. The host schools are separated into columns containing those who will be hosting
sectionals, regionals, and semi-states. Clicking on one of these will populate the map witht their location 
as well as display the schools that will be competing in that tournament. Clicking File>Save will save the 
entire tournament as it was generated. This will be saved in the "output" folder. A text file will be saved
alongside it in order to view and print the tournament.

## Deployment

We have provided the user full customization. Changing any of the parameters used to generate the tournament
can be done in "etc/config.txt" (E.g. 5 semi-states or class system).
  
## Built With

  - Google API for rendering the map of Indiana
  
## Authors

  - Ryan Arnold
  - Quentin Lintz
  - Micah Smith
