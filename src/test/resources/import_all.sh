#!/usr/bin/env bash

curl -X POST --header 'Content-Type: application/x-www-form-urlencoded' --header 'Accept: application/json' -d 'repositoryId=2000&dfScmUrl=https%3A%2F%2Fgithub.com%2Fapache%2Fnutch.git%3Fbranch%3Dmaster&branch=master&replayHistory=true' 'http://localhost:8080/api/v1.1/repositories'
curl -X POST --header 'Content-Type: application/x-www-form-urlencoded' --header 'Accept: application/json' -d 'repositoryId=3000&dfScmUrl=https%3A%2F%2Fgithub.com%2Fapache%2Fparquet-mr.git%3Fbranch%3Dmaster&branch=master&replayHistory=true' 'http://localhost:8080/api/v1.1/repositories'
curl -X POST --header 'Content-Type: application/x-www-form-urlencoded' --header 'Accept: application/json' -d 'repositoryId=4000&dfScmUrl=https%3A%2F%2Fgithub.com%2Fapache%2Fzookeeper.git%3Fbranch%3Dmaster&branch=master&replayHistory=true' 'http://localhost:8080/api/v1.1/repositories'
