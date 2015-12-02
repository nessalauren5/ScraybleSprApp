git fetch > build_log.txt 2>&1
if [ -s build_log.txt ]
then
   git pull
   mvn package && java -jar target/Scrayble-1.0.0.war
fi
