#!/bin/sh
#内容を自動でローカルリポジトリにPUSHするスクリプト

cd /usr/local/src/wt/
git add .
git commit -m "auto by test tool"
sudo git push origin2 master
