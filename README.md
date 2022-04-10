# WAA-Labs - Springboot Restful web services 
Each Lab is pushed in its own branch 

## Lab6 - Refresh Token 
This is a continuation on Lab5v2
- Implementation of refresh Token - This renew the access token when expired without the user interference

### Implementation of the Refresh Token
#### What Is A Token
Tokens are pieces of data that carry just enough information to facilitate the process of 
determining a user's identity or authorizing a user to perform an action. All in all, tokens are artifacts that allow application systems to perform the authorization and authentication process. 

#### What's an access token?
When a user logins, an access token is issued, which is an artifact that client applications can use to make secure calls to an API. The access token lets the client signal to the server that it has received authorization by the user to perform certain tasks or access certain resources.

#### What is a Refresh Token?
For security purposes, access tokens may be valid for a short amount of time. Once they expire, client applications can use a refresh token to "refresh" the access token, that is a refresh token is a credential artifact that lets a client application get new access tokens without having to ask the user to log in again.

#### Implementation
Once the access token is expired as we limit it's time duration to a short lifespan, the application will deny any further access using the expired access token. 
Then to get a new access token, we use the refresh token of the expired access token to generate a new access token. 

#### Using java-jwt library
#### Verify a Token
First, we create a `JWTVerifier` instance by calling `JWT.require()` and passing the `Algorithm` instance.
If the token has an invalid signature or the Claim requirement is not met, a `JWTVerificationException` will raise.

#### Token Best Practices
- Keep it secret. Keep it safe.
- Do not add sensitive data to the payload
- Give tokens an expiration
- Embrac HTTPS
- Consider all of your authorization use cases
- Store and reuse

### Basic Implementation Using HashMaps
Refresh tokens are bearer tokens. It's impossible for the authorization server to know who is legitimate or malicious when receiving a new access token request. We could then treat all users as potentially malicious. 

You can store Refresh Token in Local Storage, Yes browser local storage provides persistence across page refreshes and browser tabs, however if malicious users managed to run scripts using XSS attack, they could retrieve the tokens stored in local storage. 

Having an HashMap implementation - a key-value pair of the recently expired access token, its expiration At value can be used as a mechanism for the refresh token to first compare to successful issue an new access token.  


### Note: How to Test
Clone this repo locally with the `-b` flag `git clone -b <branchname> <github repo url>` - specific branch name is Lab6
- cd into Lab1 folder - run `docker-compose up -d` to start the postgres database container
- create a database named `lab_db`
- import the project named Lab1 to your Java IDE and test features in this specific branch
- Test the API via Postman [Download my most recent Postman collection here](WAA - Labs.postman_collection.json)  
