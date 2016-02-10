@Echo OFF
Echo.

REM Echo LAUNCH DIRECTORY
REM Echo ----------------
REM Echo "%~dp0"
REM Echo. 

REM Echo CURRENT DIRECTORY
REM Echo -----------------
REM Echo "%CD%"
REM Echo. 

Set PD=%~dp0..
Set JAVA_BIN=%PD%\jre\bin\java.exe
Set JAVAW_BIN=%PD%\jre\bin\javaw.exe
REM Echo JAVA BIN
REM Echo --------
REM Echo "%JAVA_BIN%"
REM Echo. 

REM START RIVIERA
%JAVA_BIN% -jar %PD%\lib\${project.build.finalName}-windows.jar
