# nohup java -jar eladmin-system-2.4.jar --spring.profiles.active=prod &
nohup java -jar -Xms64m -Xmx128m iforms-system-1.0.0.jar --spring.profiles.active=prd > /opt/iforms/nohup.txt 2>&1 &