# Simple Views and Layouts

By now you should be familiar with a 'single-page application' in Android. That is, an app that runs within a single Activity (capital 'A'). You should have some expereience using LinearLayouts/RelativeLayouts, providing systems resources for different screen sizes etc. This week, we'll look at Activity lifecycle and how to pass data between Activities. Also, we'll examine some more layouts and widgets. 

## Lab 1 UI design

Before we go into details of what each individual widget is capable of doing, we need to have a big picture of what Android has to offer. But even before we do that, let's have a look at a popular design pattern called MVC (Model-View-Controller).

### MVC pattern

The MVC pattern, which plays a vital role in modern application programming, assigns objects in an application one of three roles: model, view, or controller:

- *Model*: Model is your data structure. It holds the relationship witin your data. Model objects encapsulate the data specific to an application and define the logic and computation that manipulate and process that data. 
- *View*: A view object is an object in an application that users can see. A view object knows how to draw itself and can respond to user actions.
- *Controller*: A controller object acts as an intermediary between one or more of an application’s view objects and one or more of its model objects. In Android, a controller is typically a subclass of Activity, Fragment, or Service.

![mvc](https://developer.apple.com/library/ios/documentation/General/Conceptual/DevPedia-CocoaCore/Art/model_view_controller.jpg)

In Adroid, especially for small-scale apps, it's often not easy to distinguish roles within the MVC pattern. But the benefits of MVC is obvious - it seprates the front-end and back-end, and make it easier to reuse your classes.

### Design principles

On the [official Android website](http://developer.android.com/design/get-started/principles.html) Google lists following principles you need to follow when designing your app, but most of them boils down to common sense.

- *Enchant me*: 'your app should strive to combine beauty, simplicity and purpose to create a magical experience that is effortless and powerful' (quoted direcitly from Google).
- *Simplify my life*: make your app easy to navigate, easy to use, easy to understand.
- *Make me amazing*: provide visual hints and/or default values for your app, break big tasks into smaller steps.

Google released the new [Material Design](https://www.google.com/design/spec/material-design/introduction.html) with android 5.0 Lollipop, which provides detailed guidlines for visual, motion etc. and covers almost everything one can think of in app design. Here I list some common requirements that you should try to meet and pitfall s that you should try to avoid in your app. Detailed requirements can be found in Material Design documentation.

- *Animation*: Changes in acceleration or deceleration should be smooth across the duration of an animation.
- *Style*: Uer system recommended color, icons, meaningful and genuine images. Use concise and simple languages.
- *Layout*: Design to suit different devices and screen-sizes. Try to avoid slicing up the interface into too many regions like in the following

    ![many regions](https://material-design.storage.googleapis.com/publish/material_v_4/material_ext_publish/0Bx4BSt6jniD7NHNfYW03U28wYnM/layout_structure_regions_guidance4.png)


- *Components*: Requirements were layed in Material Design for different UI components such as buttons or menus. Some of these requirements are very specific, e.g. button height need to be 36dp in a dialog. Some other requirements tend to be more general, e.g the rule that states don't use flat buttons in UIs where they would be difficult to see.
- *Pattern and Usability*: Material Design has guidelines for different senarios which is referred to as 'patterns'. For example, launch screens can be either placeholder UI or branded launch. Of course you can implement anything you wish, but I'd go for one of these two for your app. 

The above is a really brief 'abstract' of the Material Design documentation. The important things to remember is, if in doubt, check with the official documentation.

### Activity lifecycle

Now we'll start exploring the Activity lifecycle. As we know already, in Android each Activity is (normally) associated with a layout xml file. Before and after the layout becomes visible/disappear on the screen the system has to create or destroy the Activity object by calling some callback methods such as `onCreate`. The whole process i.e. lifecycle of an Activity involes several different stages and callback methods.

Following steps below to create a new project and insert some overriding methods:

1. Create a new project called 'My Activities'.
2. Insert the following code into the class body of 'MainActivity.java'.
    ```java
    private static final String TAG_LIFECYCLE = "TagLifecycle";
    ```
3. Insert teh following into the `onCreate` method
    ```java
    Log.d(TAG_LIFECYCLE, "In the onCreate() event");
    ```
4. Insert the following into the class body:
    ```java
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG_LIFECYCLE, "In the onStart() event");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d(TAG_LIFECYCLE, "In the onRestart() event");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG_LIFECYCLE, "In the onResume() event");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG_LIFECYCLE, "In the onPause() event");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG_LIFECYCLE, "In the onStop() event");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG_LIFECYCLE, "In the onDestroy() event");
    }
    ```
5. Start your AVD or connect you phont/tablet, and run the app. In logcat, use 'TagLifecycle' to filter the outputs. Now try to answer the following:

    * When you first start the app, which methods were called?
    * If you press 'back' button, which methods are called?
    * Now, press the app icon on your phone to start again, which methods are called?
    * If you now press the 'home' button, which methods are called?
    * Now press the app icon, what happened? What does it mean?

Talking about Activity lifecyle, there're two diagrams you have to know well:

![lifecycle](http://developer.android.com/images/activity_lifecycle.png)

----------------divider---------------------------------------------------

![stages](http://developer.android.com/images/training/basics/basic-lifecycle.png)

The upper image depicts all callback methods an Activity has to go through from the start of its life until the end. Even though there're so many different callbacks, there're only three constant states, as shown in the lower image:

- *Resumed*: The activity is in the foreground of the screen and has user focus.
- *Paused*: Another activity is in the foreground and has focus, but this one is still visible.
- *Stopped*: The activity is completely obscured by another activity.

The other two states in the lower image above i.e. created and started are transient and the system quickly moves from them to the next state by calling the next lifecycle callback method.

Associated with these callback methods are what we need to implement within them. For this, [the official guide](http://developer.android.com/guide/components/activities.html) is thorough and comprehensive. Please spend time to read. The two most important things that are ofthen confused by our students are:

1. The 'home' button doesn't destroy your activity. The system will remember your app's current state. So press 'home' button and then press your app icon to restart your app is **NOT** a way to show data persistence.
2. If the system must recover memory in an emergency, `onStop()` and `onDestroy()` might not be called. Again, to save data for the sake of persistence you'll need to do it in `onPause()`.

### FrameLayout, ScrollView, TableLayouts 

Following steps below to insert two more activities and prepare the layout file for later use.

1. In the Project tool window, right-click app, select New ==> Activity ==> Blank Activity, name it 'NoteEditingActivity'.
2. Create another new FullscreenActivity and name it 'DispalyActivity'.
3. Open activity_display.xml, you'll see the root tag is **FrameLayout**. The FrameLayout is a placeholder on screen that you can use to display a single view. Views that you add to a FrameLayout are always anchored to the top left of the layout.
4. Open content_main.xml (or activity_main.xml if you don't have content_main.xml), replace its content with the following:

    ```xml
    <?xml version="1.0" encoding="utf-8"?>

    <ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    app:layout_behavior="@string/appbar_scrolling_view_behavior"
    tools:context=".MainActivity"
    tools:showIn="@layout/activity_main">

    <TableLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:stretchColumns="1">

        <TableRow
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <TextView
                android:id="@+id/textView"
                android:layout_width="1900dp"
                android:layout_height="wrap_content"
                android:layout_column="0"
                android:layout_span="2"
                android:text="Activity No. 1"
                android:textColor="@android:color/darker_gray"
                android:textSize="24sp" />
        </TableRow>

        <TableRow>

            <TextView
                android:id="@+id/labelMake"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginLeft="19dp"
                android:layout_marginTop="10dp"
                android:width="80dp"
                android:text="Make:"
                android:textAppearance="?android:attr/textAppearanceSmall" />

            <EditText
                android:id="@+id/inputMake"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginLeft="19dp"
                android:layout_marginRight="20dp"
                android:ems="10"
                android:hint="e.g. BMW" />
        </TableRow>

        <TableRow>

            <TextView
                android:id="@+id/labelYear"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginLeft="19dp"
                android:layout_marginTop="10dp"
                android:text="Year:"
                android:textAppearance="?android:attr/textAppearanceSmall" />

            <EditText
                android:id="@+id/inputYear"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginLeft="19dp"
                android:layout_marginRight="20dp"
                android:ems="10"
                android:hint="e.g. 1980"
                android:inputType="number" />
        </TableRow>

        <TableRow>

            <TextView
                android:id="@+id/labelColor"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginLeft="19dp"
                android:layout_marginTop="10dp"
                android:text="Color:"
                android:textAppearance="?android:attr/textAppearanceSmall" />

            <EditText
                android:id="@+id/inputColor"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginLeft="19dp"
                android:layout_marginRight="20dp"
                android:ems="10"
                android:hint="e.g. Red" />
        </TableRow>

        <TableRow>

            <TextView
                android:id="@+id/labelNote"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginLeft="19dp"
                android:layout_marginTop="10dp"
                android:text="Note:"
                android:textAppearance="?android:attr/textAppearanceSmall" />

            <EditText
                android:id="@+id/inputNote"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginLeft="19dp"
                android:layout_marginRight="20dp"
                android:ems="10"
                android:hint="e.g. This is my first." />
        </TableRow>

        <TableRow>

            <TextView />

            <Button
                android:id="@+id/buttonNote"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_column="1"
                android:layout_marginLeft="40dp"
                android:layout_marginRight="20dp"
                android:gravity="center"
                android:onClick="goEdit"
                android:text="Write notes"
                android:textAppearance="?android:attr/textAppearanceSmall" />
        </TableRow>

        <View
            android:layout_height="3dp"
            android:background="@color/colorPrimary" />

        <TableRow>

            <Button
                android:id="@+id/buttonDisplay"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="5dp"
                android:layout_span="2"
                android:gravity="center"
                android:onClick="goDisplay"
                android:text="Display" />

        </TableRow>
    </TableLayout>

    </ScrollView>

    ```
    
    There're several things you need to know in this layout file:
    
    * A **ScrollView** is a subclass of FrameLayout. It's a layout container for a view hierarchy that can be scrolled by the user, allowing it to be larger than the physical display. The ScrollView can contain only one child view or ViewGroup. 
    * A **TableLayout** is like a table in spreadsheet i.e. it has columns and rows. In our example above, the first TextView i.e. with text 'Activity No.1' starts at column `android:layout_column="0"` and spans for two columns `android:layout_span="2"`.
    * If you don't want to specify starting column number, you can put a placeholder empty TextView like the one before button 'buttonNote'.
    * It's your first time to see the 'View' tag. What we did here is basically to draw a divider line with height 3dp.
    
    Your screen should now look like the following:
    
    ![a1](.md_images/a1.png)
    
5. Replace the content of content_note_editing.xml with the following
    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    app:layout_behavior="@string/appbar_scrolling_view_behavior"
    tools:context=".SecondActivity"
    tools:showIn="@layout/activity_second"
    android:minWidth="350dp">

    <TextView
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Activity No. 2"
        android:textColor="@android:color/darker_gray"
        android:textSize="24sp" />

    <TextView
        android:id="@+id/labelNote"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="19dp"
        android:layout_marginTop="36dp"
        android:text="Write your notes here:"
        android:textAppearance="?android:attr/textAppearanceSmall" />

    <EditText
        android:id="@+id/inputNote"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:layout_marginLeft="19dp"
        android:layout_marginRight="20dp"
        android:ems="10"
        android:gravity="top"
        android:hint="e.g. This is my first."
        android:minLines="4"
        />

    <Button
        android:id="@+id/buttonNoteDone"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="19dp"
        android:layout_marginRight="20dp"
        android:layout_marginBottom="30dp"
        android:gravity="center"
        android:layout_gravity="right"
        android:onClick="onDoneClick"
        android:text="Done" />

    </LinearLayout>

    ```
    This is pretty easy to understand. Note line `android:layout_height="0dp"` and `android:layout_weight="1"`. This is basically telling the EditText to occupy all available pace in its parent view. Your screen now should look like:
    
    ![a2](.md_images/a2.png)
    
6. For NoteEditingActivity, we want it to show as a dialog style. In other words, it shows up as partially occupying the screen. So add the following to styles.xml
    ```xml
    <style name="MyDialog" parent="Theme.AppCompat.Light.Dialog">
        <item name="windowNoTitle">true</item>
    </style>
    ```
    Open AdroidManifest.xml, change style of the activity to the one you just created 
    
Now we have three activities. The idea is that in the main activity, if you click 'write note' it'll take you to a second activity where you can take your notes. Once finished, if you click 'display', all the info you typed will be shown in a 3rd activitiy.

### Intents

Next, we link-up all the buttons with methods. Also, we link all three activities together.

1. 

## Lab 2 Simple and complex view

When you look at your layout files in Design view, the Palette shows evrything that is avaible to you. 

### Widgets 

### action bar

### adapter view spinner

### thread




**Refs**

[Ref.[4]](#ref4) chapter 3/4

## Lab 3 Advanced topics

For those of you who haven't completed previous labs, you can work on it if you wish. For those who have finished, in this final lab I'll ask some challenging questions for you to explore. These questions are related to previous labs, and somehow involves more efforts to complete.

### Fragments, screen orientation, 

### dialog, picker

