# Direct JWT Token Generation Demo

A small Spring Boot project that accepts a JSON user payload, generates a signed JWT using an HMAC secret key, and exposes the endpoint in Swagger UI.

## Requirements

- JDK 17 or later
- Maven 3.6+ (or use the Maven wrapper if you add one)
- Internet connection for Maven to download dependencies

## Run in IntelliJ / Eclipse / Spring Tool Suite

1. Extract `jwt-direct-token-demo.zip`.
2. Open the extracted `jwt-direct-token-demo` folder as an existing Maven project.
   - Eclipse / STS: `File > Import > Existing Maven Projects`, select the extracted folder, then Finish.
   - IntelliJ: `File > Open`, select the folder, and load it as a Maven project.
3. Wait for Maven dependencies to finish downloading.
4. Make sure the project is using JDK 17 or later.
5. Run `JwtApplication.java` as a Java application / Spring Boot App.

## Run from terminal

Open a terminal in the folder containing `pom.xml`, then run:

```bash
mvn spring-boot:run
```

## Test in Swagger UI

1. Start the application.
2. Open: http://localhost:8080/swagger-ui/index.html
3. Expand `POST /api/auth/token`.
4. Click **Try it out**.
5. Enter this JSON:

```json
{
  "username": "surya",
  "email": "surya@gmail.com"
}
```

6. Click **Execute**.
7. A successful response contains a signed JWT:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "tokenType": "Bearer"
}
```

The actual token will be longer and different each time because its issued-at time changes.

## Endpoint summary

- Method: `POST`
- URL: `/api/auth/token`
- Content-Type: `application/json`
- Input: `username`, `email`
- Output: signed JWT and token type

## How the code works

1. `User.java` represents the JSON request body.
2. `JwtController.java` receives the JSON using `@RequestBody`.
3. `JwtService.java` creates the JWT, adds the username as the subject and email as a claim, sets a 24-hour expiry, and signs it with HS256.
4. Swagger UI lets you submit the JSON and view the response.

## Important security note

This is a learning/demo endpoint: it generates a token directly from the submitted username and email. It does **not** verify a password or authenticate the user's identity. Do not use it as production authentication. For production, verify credentials first, store a strong secret outside source control (for example, an environment variable or secret manager), and never put passwords or other secrets in JWT claims.
