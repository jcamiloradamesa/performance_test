#!/usr/bin/env bash

java -jar code_cache.jar \
  --console.log=yes \
  --codecache.vcsCache.minFreeSpace=12 \
  --codecache.jms.commitNotificationQueue=codecache-commit-notification-queue \
  --codecache.jms.cache.update.notification.queue=codecache-cache-update-notification-queue \
  --codecache.cluster.name=codecache-test \
  --server.port=8080 \
  --codecache.vcsCache.rootDir=/home/ubuntu/projects/cache_root \
  --vcs.apiUrl=codeserver-vcs-api-dev.devfactory.com \
  --vcs.token=Y29kM3NlcnYzcjp2YyR1c2Uzcg== >> logs.txt
