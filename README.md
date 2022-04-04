# WAA-Labs - Springboot Restful web services 
Each Lab is pushed in its own branch 

## Lab4
This is a continuation on Lab3
- Adding some cross-cutting concerns - AOP
- Make a 'Logger' database table that will store all operations executed. The table should hold (TransactionId, Date, Time, Principle, Operation)
- Make an Aspect that has an annotation pointcut @ExecutionTime.
- When the @ExecutionTime placed on a method, it should calculate the time taken to complete that method
- Make an 'Exception' database table that will track all the exceptions that were thrown (TransactionId, Date, Time, Principle, Operation, Exception Type) 

#### Note: How to Test
Clone this repo locally with the `-b` flag `git clone -b <branchname> <github repo url>` - specific branch name is Lab4
- cd into Lab1 folder - run `docker-compose up -d` to start the postgres database container
- create a database named `lab_db`
- import the project named Lab1 to your Java IDE and test features in this specific branch
- Test the API via Postman [Download my most recent Postman collection here](WAA - Labs.postman_collection.json)  
