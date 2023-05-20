# Solution
In order to achieve the usage of Role over the Team Members, I create two tables. The first is called Roles, where we store the role definition (id, name) and the second is called Membership Roles, where we keep the association between the Role and Membership (User in a Team).

Users that do not belong to a Team cannot have a Role, I decided this base on the definition of a Team Member.

The solutions REST are:

* Create a new role
  * **POST** _v1/roles_ - ```{"name":"e-code"}```
    * The name is required and has a max of 20 chars.
* Assign a role to a team member 
    * **POST** _v1/teams/{teamId}/users/{userId}/roles/{roleId}_ - ```EMPTY BODY```
      * The team, user and role must exist
* Look up a role for a membership
    * **GET** _v1/teams/{teamId}/users/{userId}/roles_
      * The team and user must exist.
* Look up memberships for a role
    * **GET** _v1/roles/{roleId}_
      * The role must exist.

## Cache

I did not have enough time to add a modern cache provider like Redis, but it's using the default one for now.
      
## Scheduler

I created a scheduler to check if Team or User has deleted and then remove any Membership Role.

# Suggestion

I believe the Membership needs to be created when a User is assigned to a Team, in short, the Membership is the product
of the *N:N* relationship between the User and Team. Then with Membership's id, we can simplify the Role service.

# Running

## Requirements
* JDK 11
* Maven

## Build Project
 On the source folder run ```mvn clean install```

## Docker
The source has a docker-compose file with the DB and credentials that will speed up your test.
On the source folder run ```docker-compose up```
In case you want to use your DB, please change the configuration on the *application.yml*
