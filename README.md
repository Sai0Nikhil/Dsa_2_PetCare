# PetCare - Smart Veterinary Clinic & Pet Health Management System

A **basic Java project** that demonstrates **all six DSA modules** through
a realistic veterinary-clinic management domain. Designed to be opened in
**Eclipse / IntelliJ / VS Code** with no extra configuration.

## How to run from the terminal

```bash
# Compile
javac -d out @sources.txt

# Run
java -cp out petcare.PetCareApp
```

### Helper scripts

| Script | Command | Action |
|--------|---------|--------|
| `build.bat` (CMD) | `build` | Compile only |
| | `build run` | Compile + Run |
| | `build clean` | Clean compiled classes |
| `build.ps1` (PowerShell) | `.\build.ps1` | Compile only |
| | `.\build.ps1 run` | Compile + Run |
| | `.\build.ps1 clean` | Clean compiled classes |

## Package layout

```
src/
petcare
|-- PetCareApp.java              <-- main entry
|-- models/                      domain objects (Pet, Treatment, Clinic, ...)
|-- utils/                       InputUtils, DataGenerator, DisplayUtils
|-- menu/                        CLI router + per-module sub-menus
|-- trees/                       BST, AVL, B-Tree, B+ Tree, Segment, Fenwick
|-- graphs/                      Graph, BFS, DFS, MST, Dijkstra, Bellman-Ford,
|                                Floyd-Warshall, Topological Sort
|-- sorting/                     Merge, Quick, Heap, Radix / Counting Sort
'-- algorithms/                  Activity Selection, Knapsacks, LIS
```

## Module mapping

| Module | Course Outcome              | Implementations |
|--------|-----------------------------|-----------------|
| M1     | Trees & Balanced Search     | BST, AVL, traversals |
| M2     | Multiway Trees & Ranges     | B-Tree, B+ Tree, Segment, Fenwick |
| M3     | Graph Algorithms            | BFS, DFS, MST (Kruskal/Prim) |
| M4     | Shortest Path Optimisation  | Dijkstra, Bellman-Ford, Floyd-Warshall, Topo sort |
| M5     | Sorting & Ranking           | Merge, Quick, Heap, Radix, Counting |
| M6     | Greedy & DP                 | Activity Selection, Fractional Knapsack, 0/1 Knapsack, LIS |

The CLI gives a clean nested menu - main menu picks a module; each module
exposes its individual algorithms with sample data.

## LaTeX report

A typeset report is provided at `docs/report.tex`. Compile with
`pdflatex docs/report.tex` (or Overleaf) to get `report.pdf`.
