# CustomToastTutorial
In this Tutorial you will learn how to create a Custom Toast Image , when clicking a button or activating a toast.
---
---

# Starting Out!

When starting out , you will need to firsly install Android Studio , and once that has been taken care of your able to start creating your custom toast!
After Android Studio is installed start it up , and create a new project and the image below will help , select the Empty Views Activity.

<img src="https://github.com/ParkerMatthews/CustomToastTutorial/blob/main/img/pickinglayout.PNG" height="500px"
 width="2000px">

# Selecting Settings
After that select the following settings and the attached screen shot is there for help.

<img src="https://github.com/ParkerMatthews/CustomToastTutorial/blob/main/img/pickingsettings.PNG" height="500px"
 width="2000px">

- Name : CustomToast
+ Package Name : com.LOCATION.customtoast
* Save Location : C:\Users\StormTroopers\Desktop\CustomToast
* Langauge: Java
* Minimum SDK: API 21: Android 5.0 (Lollipop)

# Installing CheckMark Image


[Download Check PNG](https://github.com/ParkerMatthews/CustomToastTutorial/blob/main/img/tick.png)

Now , you can make this image anything you like but for this tutorial im going to be using a checkmark image.
Download the image , and then copy and paste it in the drawable-v24 folder , along with other two ic_launcher files that should already be in there.

<img src="https://github.com/ParkerMatthews/CustomToastTutorial/blob/main/img/locationofcheckmark.PNG" height="500px"
 width="500px">

# Starting The Code!
---
--- 

# Starting Activity_main.xml

Firsly, go to your main activity_main.xml and change all of the code to the photo below!


<img src="https://github.com/ParkerMatthews/CustomToastTutorial/blob/main/img/xmlfile.PNG" height="500px"
 width="2000px">

Here , is what your layout should look like now.

<img src="https://github.com/ParkerMatthews/CustomToastTutorial/blob/main/img/displayscreenlayout.PNG" height="500px"
 width="5000px">

# Finished Activity_main.xml

# Starting Toast.xml File

<img src="https://github.com/ParkerMatthews/CustomToastTutorial/blob/main/img/creatingnewtoastxmlfile.PNG" height="500px"
 width="2000px">

 Now you done with activity_main.xml file we can move on to the other files we will add a total of two more files.

 Fill in the toast.xml file with the code below.
 
 You will need to change the android:orientation="vertical" to "horizontal".

 <img src="https://github.com/ParkerMatthews/CustomToastTutorial/blob/main/img/toasxmlcode.PNG" height="500px"
 width="2000px">


# Done with Toast.xml File

# Starting and Creating toast_border.xml

Create the new toast_border.xml.

 <img src="https://github.com/ParkerMatthews/CustomToastTutorial/blob/main/img/toast_borderxmlcode.PNG" height="500px"
 width="2000px">

 Then fill in the toast_border.xml file with the code below in the screenshot.


 <img src="https://github.com/ParkerMatthews/CustomToastTutorial/blob/main/img/toast_bordermaincode.PNG" height="500px"
 width="2000px">

 Now afther this step all we need to do to finish this project is edit the MainActivity.java.


 # Finishing MainActivity.java

Add the code below in the screenshots , to the MainActivity.java

<img src="https://github.com/ParkerMatthews/CustomToastTutorial/blob/main/img/firsthalfofxmlmainactivityfile.PNG" height="500px"
 width="2000px">


<img src="https://github.com/ParkerMatthews/CustomToastTutorial/blob/main/img/lowerhalfofmainactivityxmlcode.PNG" height="500px"
 width="2000px">

 # Now test your code!

 Now run the module and hit the button and you should get this result below.

 <img src="https://github.com/ParkerMatthews/CustomToastTutorial/blob/main/img/finalresult.PNG" height="500px"
 width="500px">

# DONE

If you have any questions please check out the video tutorial.

[Video Tutorial](https://www.youtube.com/watch?v=7U3jdZyt7Ak)


