# backend

https://trello.com/b/PdacT58N/labsce1-wellness-bet-exercise-and-sleep
https://docs.google.com/document/d/1bgKZY6Xe_nTrXnWQKYha-q_pg9oCcakQLzXUltCpkrg/edit#heading=h.1jaf6eug9n0k
https://projects.invisionapp.com/share/EYRNKTZCJUZ#/screens/359267384
https://projects.invisionapp.com/share/A8RNKKYD4HV#/screens
🚫 Note: All lines that start with 🚫 are instructions and should be deleted before this is posted to your portfolio. This is intended to be a guideline. Feel free to add your own flare to it.

🚫 The numbers 1️⃣ through 3️⃣ next to each item represent the week that part of the docs needs to be comepleted by. Make sure to delete the numbers by the end of Labs.

🚫 Each student has a required minimum number of meaningful PRs each week per the rubric. Contributing to docs does NOT count as a PR to meet your weekly requirements.

# API Documentation

#### 1️⃣ Backend delpoyed at [🚫name service here](🚫add URL here) <br>

## Getting started

To get the server running locally:

- Download and install [Maven](https://maven.apache.org/index.html)
- Clone this repo
- Open terminal
- Follow steps to set up [MySQL DB w/ Docker for local testing](https://github.com/labsce1-wellness-bet/backend/wiki/MySQL-DB-w--Docker-for-local-testing)
- `cd` into repo
- Set up `application.yml` file
  - Open `src/main/resources`
  - Copy `application.yml.example` into a new `application.yml` file
  - Follow instructions inside file to set up
- Run `mvn package` to compile executable JAR file
- Run `java -jar target/wellness-0.0.1-SNAPSHOT.jar`
- Once done backend runs at http://localhost:5000/ or the PORT defined in `src/resources/application.properties`
- If database table doesn't migrate you may need to go into `application.properties` and set `spring.jpa.hibernate.ddl-auto=create`.

### Backend framework goes here

🚫 Why did you choose this framework?

- Point One
- Point Two
- Point Three
- Point Four

## Endpoints

🚫This is a placeholder, replace the endpoints, access controll, and descriptioin to match your project

#### Group Routes

| Method | Endpoint                             | Access Control     | Description                                |
| ------ | ------------------------------------ | ------------------ | ------------------------------------------ |
| GET    | `/api/group/all`                     | Wellness Bet Admin | Get all groups                             |
| GET    | `/api/group/id/:groupId`             | Anyone with JWT    | Gets group by id                           |
| GET    | `/id/{groupId}/public/all/user-info` | Users in groups    | Gets all users' public info in group       |
| GET    | `/all/admin`                         | Admin of group     | Gets all groups that belongs to that admin |
| POST   | `/api/group`                         | Anyone with JWT    | Creates group                              |
| PUT    | `/api/group/join-group/:secretCode`  | Anyone with JWT    | Lets user join group                       |
| PUT    | `/api/group/id/:groupId`             | Anyone             | Update group by id                         |
| DELETE | `/api/group/id/:groupId`             | Wellness Bet Admin | Delete group by id                         |
| DELETE | `/api/group/id/:groupId/admin`       | Admin of group     | Delete group by id                         |

| Method | Endpoint                          | Access Control | Description                  |
| ------ | --------------------------------- | -------------- | ---------------------------- |
| POST   | `/api/competition/group/:groupid` | Admin of group | Create Competition for group |

# Data Model

🚫This is just an example. Replace this with your data model

#### USERS

---

```
{
  id: UUID
  fname: STRING
  lname: STRING
  fullname: STRING
  email: STRING
}
```

#### GROUPS

---

```
{
  id: UUID
  name: STRING
  message:STRING
  activity:STRING
  goal: INT
  admin: STRING
  bet_amount: INT
  invite_code: STRING
  start_date: STRING
  end_date: STRING

}
```

## 2️⃣ Actions

🚫 This is an example, replace this with the actions that pertain to your backend

`getOrgs()` -> Returns all organizations

`getOrg(orgId)` -> Returns a single organization by ID

`addOrg(org)` -> Returns the created org

`updateOrg(orgId)` -> Update an organization by ID

`deleteOrg(orgId)` -> Delete an organization by ID
<br>
<br>
<br>
`getUsers(orgId)` -> if no param all users

`getUser(userId)` -> Returns a single user by user ID

`addUser(user object)` --> Creates a new user and returns that user. Also creates 7 availabilities defaulted to hours of operation for their organization.

`updateUser(userId, changes object)` -> Updates a single user by ID.

`deleteUser(userId)` -> deletes everything dependent on the user

## 3️⃣ Environment Variables

In order for the app to function correctly, the user must set up their own environment variables.

create a .env file that includes the following:

🚫 These are just examples, replace them with the specifics for your app

_ STAGING_DB - optional development db for using functionality not available in SQLite
_ NODE\*ENV - set to "development" until ready for "production"

- JWT*SECRET - you can generate this by using a python shell and running import random''.join([random.SystemRandom().choice('abcdefghijklmnopqrstuvwxyz0123456789!@#\$%^&amp;*(-_=+)') for i in range(50)])
  _ SENDGRID_API_KEY - this is generated in your Sendgrid account \* stripe_secret - this is generated in the Stripe dashboard

## Contributing

When contributing to this repository, please first discuss the change you wish to make via issue, email, or any other method with the owners of this repository before making a change.

Please note we have a [code of conduct](./code_of_conduct.md). Please follow it in all your interactions with the project.

### Issue/Bug Request

**If you are having an issue with the existing project code, please submit a bug report under the following guidelines:**

- Check first to see if your issue has already been reported.
- Check to see if the issue has recently been fixed by attempting to reproduce the issue using the latest master branch in the repository.
- Create a live example of the problem.
- Submit a detailed bug report including your environment & browser, steps to reproduce the issue, actual and expected outcomes, where you believe the issue is originating from, and any potential solutions you have considered.

### Feature Requests

We would love to hear from you about new features which would improve this app and further the aims of our project. Please provide as much detail and information as possible to show us why you think your new feature should be implemented.

### Pull Requests

If you have developed a patch, bug fix, or new feature that would improve this app, please submit a pull request. It is best to communicate your ideas with the developers first before investing a great deal of time into a pull request to ensure that it will mesh smoothly with the project.

Remember that this project is licensed under the MIT license, and by submitting a pull request, you agree that your work will be, too.

#### Pull Request Guidelines

- Ensure any install or build dependencies are removed before the end of the layer when doing a build.
- Update the README.md with details of changes to the interface, including new plist variables, exposed ports, useful file locations and container parameters.
- Ensure that your code conforms to our existing code conventions and test coverage.
- Include the relevant issue number, if applicable.
- You may merge the Pull Request in once you have the sign-off of two other developers, or if you do not have permission to do that, you may request the second reviewer to merge it for you.

### Attribution

These contribution guidelines have been adapted from [this good-Contributing.md-template](https://gist.github.com/PurpleBooth/b24679402957c63ec426).

## Documentation

See [Frontend Documentation](🚫link to your frontend readme here) for details on the fronend of our project.
🚫 Add DS iOS and/or Andriod links here if applicable.
