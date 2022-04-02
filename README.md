# WAA-Labs - Springboot Restful web services 
Each Lab is pushed in its own branch 

## Lab3
This is a continuation on Lab2
- Add the Comment entity
- Make a repository and database that will hold the comments
- Make the different layers for the Comments
- Make an ORM relation between Post and Comment enetities, where Post holds a collection of comments
- Cascade all the operations from User to the Posts and Posts to Comments 

#### Note: How to Test
Clone this repo locally with the -b flag `git clone -b <branchname> <github repo url>` - specific branch name is Lab3
- cd into Lab1 folder - run `docker-compose up -d` to start the postgres database container
- create an database named `lab_db`
- import the project named Lab1 to your Java IDE and test features in this specific branch
- Test the API via Postman [Download my most recent Postman collection here](./WAA - Labs.postman_collection.json)  
