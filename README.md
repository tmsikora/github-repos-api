# GitHub Repositories Information API


## About

GitHub Repositories Information API is a Spring Boot 
application that provides a RESTful API to get repositories 
of a given GitHub user. It lists all the repositories, 
which are not forks, name of each branch and its last 
commit SHA. GitHub REST API is used as a backing API for 
the application.

## Requirements

- **Java 21** or later
- **Spring Boot 3.3.2** or later
- **Maven 4.0.0** or later


## Usage Instructions

1. Clone the repository:
```
git clone https://github.com/tmsikora/github-repos-api.git
cd github-repos-api
```
2. Build the project using Maven:
```
mvn clean install
```
3. Run the application using the Maven Spring Boot plugin:
```
mvn spring-boot:run
```


## API Endpoints

**Get User Repositories**

- Method: `GET`
- URL: `/api/github/users/{username}/repositories`
- Header: `Accept: application/json`
- Success Response:
```dtd
[
    {
        "repositoryName": "repository-name",
        "branches": [
            {
                "branchName": "master",
                "lastCommitSha": "a1234b123c12d123a1234b123c12d"
            }
        ],
        "ownerLogin": "username"
    }
]
```

- Error Response:
```
{
    "status": 404,
    "message": "User not found"
}
```

## Example

- `GET http://localhost:8080/api/github/users/octocat/repositories`
- Header: `Accept: application/json`
- Response (a part of it):
```
[
    {
        "repositoryName": "git-consortium",
        "branches": [
            {
                "branchName": "master",
                "lastCommitSha": "b33a9c7c02ad93f621fa38f0e9fc9e867e12fa0e"
            }
        ],
        "ownerLogin": "octocat"
    },
    {
        "repositoryName": "hello-worId",
        "branches": [
            {
                "branchName": "master",
                "lastCommitSha": "7e068727fdb347b685b658d2981f8c85f7bf0585"
            }
        ],
        "ownerLogin": "octocat"
    },
    ...
]
```
