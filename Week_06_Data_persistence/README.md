# Data persistence

Data persistence refers to the fact that data need to persist through app life cycle changes. In other words, even if you close the app completely the data need to be restored when the user navigates back to the app. 

The most common mistake people make when demonstrating data persistence is that they press the Home button when they finish saving data and then tap the app incon again to bring the app back. NO! That's NOT how you demonstrate data persistence! The proper way to desmontrate data persitence is, after saving your data, you close the app completely using app switcher e.g. the square icon for Nexus devices and then start your app again by tapping the icon.

To understand why this is wrong we need to remind ourselves about Activity Lifecycle. 

## Lab 1 SharedPreferences and files


## Lab 2 SQLite Databases


## Lab 3 Advanced topics

![morphing](.md_images/morphing.png)


In a similar module run by Professor Andrew T. Campbell at Dartmouth College, they used some material from the official Android samples. Follow the tutorial at [here](http://www.cs.dartmouth.edu/~campbell/cs65/lecture09/lecture09.html) and answer the following questions:

* What did they use to manage state between orientation flips?
* How did they achieve fragment transition from TitlesFragment to DetailsFragment?

