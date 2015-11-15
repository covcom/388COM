# Testing

Last week I showed that you need to be really careful with online tutorials. The (bad) example is the SharedPreferences tutorial on [TutorialsPoint](http://www.tutorialspoint.com/android/android_shared_preferences.htm) that uses MODE_WORLD_READABLE and MODE_WORLD_WRITEABLE, both of which were [deprecated in API level 17](http://developer.android.com/reference/android/content/Context.html#MODE_WORLD_READABLE). If that caused confusion already, could situation get worse? Read on.

> In general, TutorialsPoint is a good website. But some of the contents are out-of-date.

Click [this link](http://developer.android.com/training/testing/unit-testing/local-unit-tests.html) to go to the official Android training on *Building Local Unit Tests*, locate texts that read *"Your local unit test class should be written as a JUnit 4 test class"*. Next, click on [this link](http://developer.android.com/tools/testing/testing_android.html) to go to the official Android guide on *Testing Fundamentals*, locate texts that read *"Android testing API supports JUnit 3 code style, but not JUnit 4"*. To use JUnit 4, or not to use JUnit 4, that is the question. The answer? Read on.

## Lab 1 Notifications on Android wearables

### How to use this thing?

### Services

### Broadcasting

### Notifications



## Lab 2 Android wear apps



### Setting up wearable environment

### Add a wearable module

### Build an interface

### Send message back to the phone


## Lab 3 Advanced topics

A

### data
Google lists the following for your to consider regarding testing, more info can be found here](http://developer.android.com/tools/testing/what_to_test.html):

* Change in orientation

