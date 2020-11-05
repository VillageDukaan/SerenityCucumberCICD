Feature: User flow
As a user I want to register to Pet Store


Scenario Outline: User registration
When I create a new user with <username> <firstName> <lastName> <email> <password> <phone>
Then I verify that the user with <username> is registered

Examples:
	|username | firstName | lastName | email          | password | phone       |
	|lova     | fName     | lName    | lova@gmail.com | password | 9182818131  |
	


Scenario Outline: Login User
When I login using <username> and <password>, user should see 200 status code


Examples:
	|username | password |
	|lova     | password |
	
Scenario Outline: Delete Registered User
When I hit the delete endpoint with <username>
Then I verify that the user with <username> is removed from system


Examples:
	|username |
	|lova     |
	
	