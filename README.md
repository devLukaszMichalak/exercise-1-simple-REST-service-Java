# Backend exercise

Create a simple REST service, that returns:
- Identifier
- Login
- Name
- Type
- Avatar URL
- Creation date
- Calculations

Service API should look like listed below:

/GET/users/{login}

{

 ”id”: ”…” 
 
”login”: ”…”

”name”: ”…”

”type”: ”…”

”avatarUrl”: ”…”

”createdAt”: ”…”

”caalculations”: ”…”

}

The service should download data from https://api.github.com/users/{login} (e.g. https://api.github.com/users/octocat) and pass it in the API. The calculations field should return 6 / number_of_followers * (2 + number_of_public_repos).

The service should store the number of API calls for each login in the database. The database should contain two columns: LOGIN and REQUEST_COUNT. The value of REQUEST_COUNT should be incremented by one for each invocation of the service.

The service should be implemented in Java. The project should be buildable with Maven or Gradle. You are free to use any easily available technologies (engines, BD, libraries, frameworks).

Remember to follow good programming practices.

