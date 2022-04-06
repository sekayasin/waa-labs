# WAA-Labs - Springboot Restful web services 
Each Lab is pushed in its own branch 

## Lab5 - Security
This is a continuation on Lab4
- Add Authentication to all Actions that were previously made
- There will be two roles (USER, ADMIN).
- The ADMIN should access all the paths that a user can access plus an /admin path.
- The USER should access all paths except the /admin path
- Mapping the authentication with our JPA database 
- Add login path /authentication where user enters his/her creds. if valid creds, it should generate a token.
- When a user makes a post, it should read the user info and when it saves the post, it will also store the user that made the post. 
   

#### Note: How to Test
Clone this repo locally with the `-b` flag `git clone -b <branchname> <github repo url>` - specific branch name is Lab5
- cd into Lab1 folder - run `docker-compose up -d` to start the postgres database container
- create a database named `lab_db`
- import the project named Lab1 to your Java IDE and test features in this specific branch
- Test the API via Postman [Download my most recent Postman collection here](WAA - Labs.postman_collection.json)  
