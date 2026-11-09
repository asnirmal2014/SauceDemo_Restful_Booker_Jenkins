set projectLocation=D:\eclipse-workspace-test\MVN_POM_JENKINS_1
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java org.testng.TestNG %projectLocation%\TestNG.xml
pause
