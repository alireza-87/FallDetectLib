# FallDetectLib
An Android library for free fall detection

This library detect free fall and save it inside local db
        
The library use Dagger 2 as dependency injection alsi we use MVVM  as architectura.
Android Room Used as databse.

Usage:
  initialize lib in your Application class
        LibLoader.builder().setContext(getApplicationContext()).setStayAwake(true).setShowNotification(true).build();
  if you want to show Notification:
        pass setShowNotification(true)
  if you wawnt to use service inorder to detect fall when application is close:
        setStayAwake(true)
  Get Free fall history
        FallHandler.getInstance().getFallList()
        To get a history and new free fall detection, just put an observable on this method
          
        
