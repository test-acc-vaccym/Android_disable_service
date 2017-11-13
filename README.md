
Appreciate your flexibility.  Assignment I was thinking of is,
 
1.        Android Service listens to various triggers ( e.g. SIM change  & Remote server messages ) and it will disable or enable Apps depending on triggers.
 
For simplicity sake:
 
1.       Assume that On detecting  ATT SIM, service disable apps like  Youtube, Gallery Messaging.
2.       On detecting TMO SIM , service disables Calender , Clock only  ( Make sure any previously disable apps are enabled ).
 
Remote server : have some JSON / XML file hosted somewhere  ( Feel free to use Firebase or any other push message  )
 
Service listens to events from server push message.
 
3.       If service gets push message ATT then do same as #1.
4.       If service receives TMO message then do same as #2.
 
 
Assume your service is going to be preloaded so feel free to use elevated permission or shared UIDs if required.
 
