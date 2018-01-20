# Notely
The Notely app lets a user create, read, update and delete notes. A note has a title and description. User can also delete note.
Make it favaourite and starred.

### Run on appetize.io
Run app on the online emulators using [appetize.io](https://appetize.io/embed/w9kbd5d76y79e2mc2a33ef8qrm?device=nexus5&scale=75&orientation=portrait&osVersion=7.1)

## Designing the app
The app consists of three UI screens:

* Notes - Used to manage a list of notes.
* Note Detail - Used to read a note.
* Note compose - Used to create or edit note.

In this version of the app following basic **Model-View-Presenter (MVP)** architecture without using any architectural frameworks.
Each screen is implemented using the following classes and interfaces:

* A contract class which defines the connection between the view and the presenter.
* A Activity which implements the view interface.
* A presenter which implements the presenter interface in the corresponding contract.

A presenter typically hosts business logic associated with a particular feature, and the corresponding view handles the Android UI work. The view contains almost no logic; it converts the presenter's commands to UI actions, and listens for user actions, which are then passed to the presenter.

## Data Source
**Room Persistence Library** is used as local data source for the app.

The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.

## User interface

### Notes Screen
Shows a list with the notes' titles with last update date. At the bottom there is a button to create a new task.

<img src="https://github.com/rahulmmohan/Notely/blob/master/Export/home.png" width=200px/>

### Filter
Notes can be filter based on filter conditions.

<img src="https://github.com/rahulmmohan/Notely/blob/master/Export/filter.png" width=200px/>

### Detail Screen
Shows the title, description and the updated date. The menu shows an action to edit the note. 

<img src="https://github.com/rahulmmohan/Notely/blob/master/Export/noteDetails.png" width=200px/>

### Add/Edit Screen
Lets the user edit the title and description of a new or existing note.

<img src="https://github.com/rahulmmohan/Notely/blob/master/Export/noteCompose.png" width=200px/>

## Open in Android Studio
To open one of this code in Android Studio, begin by checking out to the master branch, and then open the **Notely/** directory in Android Studio. The following series of steps illustrate how to open this.
 
Clone the repository:
 
```
git clone https://github.com/rahulmmohan/Notely.git
```
 
1. Open Android Studio and launch the Android SDK manager from it (Tools | Android | SDK Manager)
1. Check that these two components are installed and updated to the latest version. Install or upgrade
   them if necessary.
   1. *Android SDK Platform Tools*
   2. *Android Support Library*
1. Return to Android Studio and select *Import Project*
1. Select "Import from existing model - Gradle"
1. Compile and run.
