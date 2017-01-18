    EEEEEE  CCCCC   H   H   OOOOO
    E       C       H   H   O   O
    EEEE    C       HHHHH   O   O
    E       C       H   H   O   O
    EEEEEE  CCCCC   H   H   OOOOO

    Enterprise Connector for Hardened Objects
    
<br>

#### BASE PROJECT DESCRIPTION ##
ECHO [Enterprise Connector for Hardened Objects] is a modular RESTful API platform that encloses a stateless token-based authorization system [based on JSON Web Tokens (JWT) and Spring Security], used to handle authorization and secure protected resources.
The core of the platform is based on the open-source github project Cerberus [https://github.com/brahalla/Cerberus]
<br><br>

### AUTHENTICATION LOGIC DESCRIPTION ###
ECHO exposes two main API sections:
    1. A public API section that handles authentication based on a set of 3 parameters: username, password and application
    2. A protected API section that can be accessed only by providing a valid token in the header of the http request

Authentication mechanism is application related, so a valid token for a given user will be generated only if the user has rights to authenticate for that given application .<br>
Generated tokens contain all user security info (including username, grants and application) and it can be decoded using JWT utilities and knowing the "secret_jwt_string" (stored in the application.properties). <br>
Any request is processed through an authentication filter who checks if any token is provided in the http header and eventually decodes the token and stores all user security info in the security context. After passing through the authentication filter, requests are forwarded to the proper resources endpoint that can be secured using @PreAuthorize annotations. When reaching a secured resource, Spring Security checks the grants eventually stored in the context by the authentication filter, and compares to the expressions reported on the annotations granting the access only to authorized users
<br><br>

### PROJECT MODULES STRUCTURE ###
This project is intended as a template that can be integrated with your own specific, self-consistent API modules. ECHO is an open platform designed with the aim to aggregate several self-consistent modules who share the same web and security contexts, but are independent and decoupled one from the other at the same time.

The base structure of ECHO can be described as follows:
		
		echo [pom]
		|
		|--> echo-api [war]
		|
		|--> echo-common [jar]
		|
		|--> sso-api [jar]
		|
		|--> custom-api-1 [jar]
		|
		...
		|
		|--> custom-api-n [jar]

- The **"echo [pom]"** project is a simple parent project used as a container for all sub-modules in order to centralize maven dependencies versioning and simplify maven builds. 
    <br><br>
    - *Dependency note:* all dependencies used by any sub-module and their related version, should be centralized in the pom's dependencyManagement section of this project in order to maintain code clarity. So any new dependency should be added in "echo [pom]" parent first and then be referenced in any sub-module's pom.
<br><br>

- The **"echo-api [war]"** project is the main module of the platform. It declares the general configurations of the platform. Furthermore this war project imports as dependencies the echo-common and all the other jar modules who needs to be deployed, enclosing all APIs in a single deployable war.
<br><br>



- The **"echo-common [jar]"** contains common classes and utilities that can be used project-wide in all other modules. Out of the box it includes the following main features:
    - A global exception handling for all project controllers based on the @ControllerAdvice annotated ControllerExceptionHandler class. This class can handle all exceptions thrown by any controller by simply declaring a proper @ExceptionHandler method in it.
    - A global logging mechanism applicable project-wide to any desired method. This mechanism relies on AOP(Aspect Oriented Programming) by using the custom MethodLogger @Aspect service. This class watches for any method annotated with the custom annotation @Loggable and wraps its execution logging invokation/completition and input/output params respectively before/after method execution.
    - JWT utility class to handle jwt tokens. Main operations exposed are: token generation, token refreshing, token decoding.
    <br><br>
    - *Dependency note 1: all dependencies already used by "echo-api [war]" should have scope 'provided' in this project's pom, only project specific dependencies should have scope 'compile' in order to prevent dependencies duplication.*
    - *Dependency note 2: when importing "echo-common [jar]" as a dependency in another module, it should have scope "compile" only in "echo-api [war]" and scope "provided" in all other modules in order to prevent dependencies duplication.*
<br><br>


- The **"custom-api-1/n [jar]"** are case specific, self-consistent api projects that expose their own PROTECTED rest controllers (endpoints). 
Respecting the base package naming "it.clevercom.echo" for all sub-packges contained in these projects, will ensure that Spring will be able to lookup all @Components (@Controller, @Service, etc...) classes, making all custom JARs share the same Spring and Spring-Security context.
    <br><br>
    - *Dependency note 1: all dependencies already used by "echo-api [war]" should have scope 'provided' in this project's pom, only project specific dependencies should have scope 'compile' in order to prevent dependencies duplication.*
    - *Dependency note 2: In order to be included in the final deployment package,each custom-jar module must be included as a dependency in the "echo-api" project pom with scope compile.*
    - *Development note: as any module is designed to be independent and self-consistent, each module must contain its own data-access layer as well as specific model, entities and utility classes where needed.*
<br><br>


### AUTHENTICATION PROVIDER ###
Out of the box ECHO uses a login table on a MySQL database as user authentication source. User passwords are stored using bCrypt algorithm
<br><br>

### REQUIREMENTS ###
ECHO requires Maven and it's been developed using Java 1.8 (probably works with 1.7+)
<br><br>

### DEPLOYMENT ###
As mentioned before "echo-api [war]" module, must be configured to import as dependencies all needed custom-api jars. Doing so will ensure that all referred APIs will be deployed within the single war "echo-api". 
Any application server can be used for deployment, but the application has been developed on and tested on wildfly10. The file jboss-web.xml in "echo-api [war]" should be modified/replaced in order to deploy on a different application server. Context root for the war on wildfly10 is /echo-api
<br><br>

### OPEN POINTS ###
- [ ] Check token expiration and refreshing mechanism (think of both web and mobile clients)
<br><br>