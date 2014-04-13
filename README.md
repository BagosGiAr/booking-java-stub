Simplified high traffic Booking Engine - Evangelos Pappas @Hevalon
=========

The project contains the source code of the Booking engine. The app can be configured by editing the app.properties file
 in the src/main/resources folder.

___

## Architecture

The architecture decisions tha were taken upon implementation, were targeting a simplified version of a booking engine.
The structure of the projects highlights a simplified version of a web container, and an application daemon.
**Netty.io** was used to avoid any low implementation regarding http protocol. Moreover Netty provides a reactive
approach, that is taken as an advantage to serve the targeted high traffic demands.

Each action is described as a simplified transaction, that is affecting the main store (DB.BOOKINGS). The booking engine
 is using http as a composite module, in such a way that it can easily scale and use any other protocol.

The http package describes a simplified web container, were routes are registered in http.Routes, and http interactions
are made using the http.HttpResponse. engine.protocol.http describes the composition of the http with the booking
engine. Each booking call (http route) is included within this composition among with the customized HttpHandler
and HttpInitializer.

Json frameworks were avoided to be included, since the response of each call is a simplified toString result of
each model.

## File Structure
    |-main/java/com.evalonlabs.booking
        |--App -> The main method of the server
        |--http
            |---Server -> The netty bootstrap implementation - Runnable
            |---ServerInitializer -> Netty channel Initializer -
            |---Routes -> Routes register
            |---RouteHandler -> Interface that each HttpCall should implement
            |---HttpResponse -> An esy to use response tool
            |---CustomInitializer -> An interface to be implemented by each ContextSystem
            |---ContextSystem -> A System that uses this Module
        |--engine
            |---Transactions -> An enumeration of each Transactionable actions
            |---Transactionable -> A transactionable call
            |---Transaction -> An interface to be implemented by each transaction action
            |---IDGenerator -> An ID Generator abstraction
            |---CallbackHandler -> An interface to be used as a Closure
            |---BookSystem -> The booking system
            |---transaction
                |---BookTransaction
                |---CancelTransaction
                |---StatusTransaction
            |---protocol.http
                |---BookCall -> /seat/book
                |---CancelCall -> /seat/cancel/[id]
                |---HttpHandler -> A http wrap for the engine's calls
                |---HttpInitializer -> A custom initializer for the http engine resources
                |---StatusCall -> /seat
            |---model
                |---Book
                |---Status
            |---datasource
                |---DB -> A singleton the lists all the DBs
                |---Store -> A ConcurrentHashMap wrap
    |-pom.xm -> maven's pom file