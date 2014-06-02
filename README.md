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


Update 6/2 - Tim
================

We currently have 7 of the 12 User Stories active and working. The 5 User stories we need to get working are...

Program Chair can Accept or Reject papers and Author is informed.

Reviewer can submit a review.

Author can obtain a review for a paper they submitted.

Subchair can submit recommendations to the Program chair for acceptance or rejection.

Program Chair can see papers assigned to each SubChair.

In addition to this last one we should also probably allow the SubChairs to see the papers they assigned to each reviewer, which once we figure out that last user story
should not be difficult. I'm thinking we're going to need at least one more text file per conference to store review data and paper status information.
We also might want another panel at the entry screen so subchairs can submit recommendations to the program chair. 