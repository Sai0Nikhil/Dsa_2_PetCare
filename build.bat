@echo off
REM PetCare Build & Run Script (Windows CMD)
REM =========================================

if "%1"=="run" goto RUN
if "%1"=="clean" goto CLEAN

:BUILD
echo.
echo === PetCare: Compiling ===
javac -d out @sources.txt
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo Compilation FAILED!
    exit /b 1
)
echo.
echo Compilation successful!
echo.
echo To run:  build run
echo To clean: build clean
exit /b 0

:RUN
if not exist out\com\petcare\PetCareApp.class (
    echo.
    echo No compiled classes found. Building first...
    call build
)
echo.
echo === PetCare: Running ===
java -cp out petcare.PetCareApp
exit /b 0

:CLEAN
if exist out (
    rmdir /s /q out
    echo Cleaned compiled classes.
)
exit /b 0
