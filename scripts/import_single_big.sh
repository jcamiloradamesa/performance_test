#!/usr/bin/env bash

curl -X POST --header 'Content-Type: application/x-www-form-urlencoded' --header 'Accept: application/json' -d 'repositoryId=11000&dfScmUrl=https%3A%2F%2Fgithub.com%2Ftrilogy-group%2Fjive-cloud-application.git%3Fbranch%3Ddevelop&branch=develop&replayHistory=true&credentialsId=1' 'http://localhost:8080/api/v1.1/repositories'
