SET CP="%CLASSPATH%;.\bin\;.\src\;.\lib\jersey-server-1.1.5.jar;.\lib\grizzly-servlet-webserver-1.9.18-e.jar;.\lib\jaxb-impl-2.1.12.jar;.\lib\asm-3.1.jar;.\lib\jersey-core-1.1.5.jar;.\lib\jsr311-api-1.1.jar;"
java -classpath %CP% -Xmx300M bmserv.Server
pause