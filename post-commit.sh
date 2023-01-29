#!/usr/bin/env bash
#
curl -X POST "http://jenkins:8080/git/notifyCommit?url=git@gitserver:tools/common.git"