
@echo ================================================================
@echo Iniciando Compilacion Maven para proyecto Automas AVE3 de TICXAR
@echo ================================================================

call mvn clean install -DskipTests


SET src="C:\Users\ASUS\WorkSpaces\Lavadero\lavadero\target\lavadero-0.0.1-SNAPSHOT.war"
SET dest="C:\Users\ASUS\Documents\apache-tomcat-9.0.16\webapps"
copy %src% %dest%\lavadero.war
