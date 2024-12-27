# API documentation
In this document, we are creating a plan for our API and the different endpoints that It is going to have. As we know, we are presented with 3 main classes ( User, Team, Project and Task )

# Signup endpoints "/api/v1/auth/"

- [X] Signup with a new user "/signup"
- [X] Login with the credentials of the user "/login"
- [ ] Reset the password "/resetPassword"
- [ ] Logout the user "/logout"
- [ ] Refresh the expired token

# Admin endpoints "/api/v1/admin"
- [ ] Get a list of all the users in the database "/users"
- [ ] Get Server Logs "/logs"
- [ ] Get a list of the teams in the database "/teams"
- [ ] Get a list of the projects in the database "/projects"
- [ ] Get a list of the tasks in the database "/tasks"
- [ ] Edit a user's info "/users/{user_id}" 
- [ ] Edit a team's info "/teams/{team_id}"
- [ ] Edit a project's info "/projects/{project_id}"
- [ ] Edit a task's info "/tasks/{task_id}"
- [ ] Delete a user's info "/users/{user_id}" 
- [ ] Delete a team's info "/teams/{team_id}"
- [ ] Delete a project's info "/projects/{project_id}"
- [ ] Delete a task's info "/tasks/{task_id}"
- [ ] See a dashboard with all the insights, number of users etc "/dashboard"

# User endpoints "/api/v1/user"
- [ ] Get a user by email "/{email}"
- [ ] Edit a user's info "/{email}"
- [ ] Delete a user "{email}"
- [ ] Get users in a team "/team/{team_id}"
- [ ] Get the users in a project "/project/{project_id}"
- [ ] Get the users in a task "/task/{task_id}"
- [ ] See a dashboard with the insights about the team he's owner of "/dashboard"
- [ ] Get a list of the user's teams "/{user_id}/teams"
- [ ] Get a list of the user's projects "/{user_id}/projects"
- [ ] Get a list of the user's tasks "/{user_id}/tasks"

# Team endpoints "/api/v1/team"
- [X] Create a new team
- [X] Get a team by Id "/{id}"
- [X] Edit the info of a team "/"
- [X] See the members of the team "/members/{teamId}"
- [X] See the projects of the team "/projects/{teamId}"
- [X] Delete a team "/"

# Project endpoints "/api/v1/projects"
- [ ] Create a new project
- [ ] Get a project by Id "/{id}"
- [ ] Edit the info of a project "/"
- [ ] See the members of the projects "/members"
- [ ] See the tasks of the project "/tasks"
- [ ] Delete a project "/"

# Task endpoints "/api/v1/tasks"
- [ ] Create a task "/"
- [ ] Get a task by ID "/{id}"
- [ ] Edit the info of a task "/"
- [ ] See the members of the task "/members"
- [ ] Delete a task
