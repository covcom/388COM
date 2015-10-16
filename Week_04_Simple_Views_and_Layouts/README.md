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

Google released the new Material Design with android 5.0 Lollipop, which provides detailed guidlines for visual, motion etc. and covers almost everything one can think of in app design. Here I list some common pitfall s that you should try to avoid in your app:

- *animation*: 
- *style*: 
- *layout*: 
- *components*: 
- *pattern usability*: 

### scrollview tablelayouts 





### activity lifecycle

### intents

, 

**Refs**

[Ref.[4]](#ref4) chapter 2

[Android official API guide: Material Design](http://developer.android.com/design/index.html)

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

