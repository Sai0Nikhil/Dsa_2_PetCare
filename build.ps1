# PetCare Build & Run Script (PowerShell)
# ========================================

param(
    [ValidateSet("build","run","clean")]
    [string]$Action = "build"
)

switch ($Action) {
    "build" {
        Write-Host "`n=== PetCare: Compiling ===" -ForegroundColor Cyan
        javac -d out "@sources.txt"
        if ($LASTEXITCODE -ne 0) {
            Write-Host "`nCompilation FAILED!" -ForegroundColor Red
            exit 1
        }
        Write-Host "`n✅ Compilation successful!" -ForegroundColor Green
        Write-Host "`nTo run:  .\build.ps1 run"
        Write-Host "To clean: .\build.ps1 clean"
    }
    "run" {
        if (-not (Test-Path "out\com\petcare\PetCareApp.class")) {
            Write-Host "No compiled classes found. Building first..." -ForegroundColor Yellow
            & $PSCommandPath build
            if ($LASTEXITCODE -ne 0) { exit 1 }
        }
        Write-Host "`n=== PetCare: Running ===" -ForegroundColor Cyan
        java -cp out petcare.PetCareApp
    }
    "clean" {
        if (Test-Path "out") {
            Remove-Item -Recurse -Force "out"
            Write-Host "✅ Cleaned compiled classes." -ForegroundColor Green
        }
    }
}
