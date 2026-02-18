\
@echo off
set DIR=%~dp0
set JAVA_CMD=%JAVA_HOME%\bin\java.exe
if exist "%JAVA_CMD%" goto run
set JAVA_CMD=java.exe
:run
"%JAVA_CMD%" -classpath "%DIR%\gradle\wrapper\gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain %*
