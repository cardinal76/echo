#!/bin/sh

# this script assumes that you have installed wildfly on path $HOME/bin/wildfly 

artifact=$(ls ../target/*.war)
echo "deploying artifact $artifact on 192.168.10.242"
sh $HOME/bin/wildfly/bin/jboss-cli.sh -c controller=192.168.10.242:9995 --user=admin --password=admin --command="deploy $artifact --force"
