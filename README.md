TCSS360
=======

mu

To solve conflicts:

-Back-up your exisitng files.

-Delete project from eclipse (yes to delete contents on disk)

-Import the project from git, as a general project.

-It will say .project already exists. Go to the directory of the project and move the .project and .classpath files from the directory to the desktop.

-Hit back in eclipse wizard and then forward. Then finish. Put the .project and .classpath files back into the project directory and reload eclipse.

-Go through the files and edit as needed.

Update 5/31 - Nick
==================
I extrapolated the view into more seperated classes. Basically one panel for each view. I also implemented inner action listeners to take care of the button events on each panel.

Submitting papers and removing papers works so far. The submit panel is the only fully working panel besides the entry panel and login panel.

I also fixed various bugs I found along the way.

Sorry for no comments. I will go through tomorrow and comment things more.


Update 6/1 - Tim
================

I connected the remove function to the textfile. I've commented up the Controller and file controller classes. The submit function should be full operational now as well. To me 

it seems easiest for us to do assignments with papers to the subchair and reviewer with our text file. They wont submit papers anyway so we can just use the paper slots in 

the text file to make for there assignments. I just also fixed various issues in the gui to make sure we fulfill all the user stories.