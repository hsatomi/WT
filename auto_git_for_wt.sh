#!/sh/bin
#!内容を自動でローカルリポジトリにPUSHするスクリプト

cd /home/souya/wt/
git add .
git commit -m "auto by test tool"
sudo git push origin2 master