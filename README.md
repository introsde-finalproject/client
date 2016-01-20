# Final project description

#### The Virtual LifeCoach system

The Virtual Lifecoach system helps users to track their health measures such as weight and height and to define healthy lifestyle goals that user wants to achieve. The aim of this system is to help to adopt healthier lifestyle and to motivate through this tough process. 

There are three types of goals that user can define: 
+ Water: amount of liters that user want to drink daily
+ Sleep: amount of hour that user want to sleep daily
+ Steps amount of steps that user wants to walk daily

The system keeps track of goals that were defined by user and through the notifications informs him about how many liters/steps/hours left to achieve his goals.

To keep user motivated the Virtual Lifecoach system shows the motivational quote every time he/she uses the system. 

Also the system keeps track of the user activities and every time when he/she inserts his/her current progress the Virtual Lifecoach gives a feedback about the goal progress, whether it is not yet achieved, it is achieved or overachieved. If the goal was achieved or overachieved the user gets congratulations.

#### Architecture

![Architecture](https://pp.vk.me/c627428/v627428082/2c858/ykb_UKySvdc.jpg)

The system consists of 6 services and a Client application.

#### Client

The Client implements console user interface which let user to navigate through the system and to choose the action that he/she wants to perform. Main menu allows user to check and update his/her personal info and measures, check his current goals and enter his current progress in goals.

#### Services

+ **Process Centric Service (SOAP)**

This services redirects requests that are coming from the user interface application to the appropriate service which has to handle the request.

Service implements such methods and redirects their calls either to Business logic service or to the Storage service:
-	getUserGoals
-	getUserInfo
-	getUserMeasureHistory
-	getUserNotifications
-	updateUserInfo
-	updateUserGoal
-	deleteUserGoal
-	updateGoalProgress


+  **Business Logic Service (SOAP)**

This service handles the decision making part. Decision are made based on the data provided by user, data stored in the database and the logic of the system. It chooses the methods which should be executed next. 

Service implements the following methods:
-	getUserNotifications – allows user to see current progress 
-	updateUserInfo – allows user to update personal info
-	updateUserGoal – allows user to update his/her goals
-	deleteUserGoal – allows user to delete goal 
-	updateGoalProgress – allows user to inert his progress


+  **Storage Service (SOAP)**

This service fetches data from the local database, transforms data and also make REST requests to the ASCII service and quotes service.

Service implements such methods:

-	getUserGoals
-	getUserInfo
-	getUserMeasureHistory
-	getUser 
-	saveUser
-	getRandomQuote
-	getCongratsPicture

+  **Local Database Service  (SOAP)**

This service is responsible for the operations with the data, such as retrieving, updating, deleting and getting. It stores data about user’s current health, health history, goals, and goals progress. 

Such tables were created: Goal, GoalProgress, Measure, User and due to the ManyToMany relationship were creted tables HealthMeasureHistory and CurrentHealth. 

+  **Quotes Service (REST)**

This service randomly takes quote using API of the forismatic.com. The purpose of the service is to inspire user with the motivational quotes that will be shown every time the user open the service. GET method retrieves quotes.

+  **ASCII Art Service (REST)**

This service is used to congratulate user when he/she achieves the goal. 

It reads images from the directory “img” with the .jpg extension and then it determines color of each pixel and depending on the color it replaces it with some specific ASCII sign. In the end we got picture that consist from the ASCII code symbols.



