# WAA-Labs - Springboot Restful web services 
Each Lab is pushed in its own branch 

## Lab1
A CRUD SpringBoot Restful API with a fake database (Using an ArrayList) to hold Posts 

## Lab2
This is a continuation on Lab1
- Add the User entity
- Add a database using postgres database running in a docker container 

#### Note: How to Test
Clone this repo locally with the -b flag `git clone -b <branchname> <github repo url>` - specific branch name is Lab2
- cd into Lab1 folder - run `docker-compose up -d` to start the postgres database container
- create an database named `lab_db`
- import the project named Lab1 to your Java IDE and test features in this specific branch
- Test the API via Postman  
