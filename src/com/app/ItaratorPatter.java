package com.app;

class Notification
{
    // To store notification message
    String notification;
  
    public Notification(String notification)
    {
        this.notification = notification;
    }
    public String getNotification()
    {
        return notification;
    }
}
  
// Collection interface
interface Collection
{
    public Iterator createIterator();
}
  
// Collection of notifications
class NotificationCollection implements Collection
{
    static final int MAX_ITEMS = 4;
    int numberOfItems = 0;
    Notification[] balloonsList;
  
    public NotificationCollection()
    {
        balloonsList = new Notification[MAX_ITEMS];
  
        // Let us add some dummy notifications
        addItem("Ballon 1");
        addItem("Ballon 2");
        addItem("Ballon 3");
        addItem("Ballon 4");
       
    }
  
    public void addItem(String str)
    {
        Notification notification = new Notification(str);
        if (numberOfItems >= MAX_ITEMS)
            System.err.println("Full");
        else
        {
            balloonsList[numberOfItems] = notification;
            numberOfItems = numberOfItems + 1;
        }
    }
  
    public Iterator createIterator()
    {
        return new NotificationIterator(balloonsList);
    }
}
  
// We could also use Java.Util.Iterator
interface Iterator
{
    // indicates whether there are more elements to
    // iterate over
    boolean hasNext();
  
    // returns the next element
    Object next();
}
  

class NotificationIterator implements Iterator
{
    Notification[] balloonsList;
  
  
    int pos = 0;
  
    // Constructor takes the array of notifiactionList are
    // going to iterate over.
    public  NotificationIterator (Notification[] balloonsList)
    {
        this.balloonsList = balloonsList;
    }
  
    public Object next()
    {
        // return next element in the array and increment pos
        Notification notification =  balloonsList[pos];
        pos += 1;
        return notification;
    }
  
    public boolean hasNext()
    {
        if (pos >= balloonsList.length ||
            balloonsList[pos] == null)
            return false;
        else
            return true;
    }
}
  

class NotificationBar
{
    NotificationCollection notifications;
  
    public NotificationBar(NotificationCollection notifications)
    {
        this.notifications = notifications;
    }
  
    public void printNotifications()
    {
        Iterator iterator = notifications.createIterator();
        System.out.println("-------BALLOON NOTIFICATION BAR------------");
        while (iterator.hasNext())
        {
            Notification n = (Notification)iterator.next();
            System.out.println(n.getNotification());
        }
    }
}
  
// Driver class
class Main
{
    public static void main(String args[])
    {
        NotificationCollection nc = new NotificationCollection();
        NotificationBar nb = new NotificationBar(nc);
        nb.printNotifications();
    }
}