JWT Authentication – User Service

Overview

This project implements JWT-based authentication for a user service. It includes token generation, validation, and request filtering to secure protected endpoints. The system ensures that only authenticated users can access restricted resources while allowing public access to login and registration endpoints.

⸻

Features

The application generates JWT tokens containing user identity and expiration details. It validates incoming tokens to ensure they are authentic and not expired. Public routes such as login and register are accessible without authentication, while other routes are protected. Authenticated user information is extracted from the token and attached to the request for further processing.

⸻

JWT Utility

The JWT utility class is responsible for creating and validating tokens. It uses a builder pattern to construct tokens by setting the subject, issued time, and expiration time. Each token is signed using the HS256 algorithm and a secret key to ensure integrity.

During validation, the token is parsed using a JWT parser. The parser verifies the signature using the same secret key and checks whether the token has expired. If valid, the claims are extracted, and the subject (user identity) is retrieved.

⸻

JWT Filter

A custom filter is implemented by extending OncePerRequestFilter. This filter intercepts all incoming HTTP requests before they reach the controller.

The filter first checks whether the request is targeting a public endpoint such as login or register. If so, the request is allowed to pass through without authentication.

For protected endpoints, the filter extracts the JWT token from the request cookies. If the token is missing, the request is rejected with a 401 Unauthorized response. If a token is present, it is validated using the JWT utility.

If the token is valid, the filter extracts the user identity (subject) and attaches it to the request. The request is then forwarded to the next component in the filter chain. If the token is invalid, expired, or tampered with, the filter blocks the request and returns an unauthorized response.

⸻

Request Flow

Every incoming request first passes through the JWT filter. The filter determines whether authentication is required based on the request path.

If the request is for a public endpoint, it bypasses authentication and continues directly to the controller.

For protected endpoints, the filter extracts the token from the request cookies and validates it. If validation fails, the request is immediately rejected. If validation succeeds, the user identity is extracted and attached to the request.

After successful validation, the filter passes the request forward using the filter chain, allowing the controller to handle it.

⸻

Security Flow

When a token is generated, it is signed using a secret key. This signature ensures that the token cannot be modified without detection.

During request processing, the token is parsed and its signature is verified using the same secret key. If any part of the token has been altered, the signature validation fails, and the request is rejected.

This ensures the integrity and authenticity of the token.

⸻

Concepts Used

This project uses the factory method pattern for object creation and the builder pattern for constructing JWT tokens. It uses Java generics for type-safe handling of token data and implements request filtering using Spring’s OncePerRequestFilter.

It also demonstrates cookie-based authentication and exception handling for secure request processing.