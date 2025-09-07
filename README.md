Sorting Algorithm Analyzer (Java, Console-Based)
📌 Project Overview

This project implements and analyzes multiple sorting algorithms in Java.
It compares them based on execution time, number of swaps, and comparisons.
The goal is to demonstrate how different algorithms perform under the same conditions.

🚀 Features

Implements Bubble Sort, Insertion Sort, Selection Sort, Merge Sort, and Quick Sort

Tracks:

Time taken (System.nanoTime())

Number of swaps

Number of comparisons

Prints results in a comparison table

Helps visualize trade-offs between simple and efficient algorithms

🛠️ Tech Stack

Java Core

Collections (optional for testing)

System.nanoTime() for benchmarking

📊 Algorithms Compared
| Algorithm          | Best Case  | Average Case | Worst Case | Space Complexity | Stability    |
| ------------------ | ---------- | ------------ | ---------- | ---------------- | ------------ |
| **Bubble Sort**    | O(n)       | O(n²)        | O(n²)      | O(1)             | ✅ Stable     |
| **Insertion Sort** | O(n)       | O(n²)        | O(n²)      | O(1)             | ✅ Stable     |
| **Selection Sort** | O(n²)      | O(n²)        | O(n²)      | O(1)             | ❌ Not Stable |
| **Merge Sort**     | O(n log n) | O(n log n)   | O(n log n) | O(n)             | ✅ Stable     |
| **Quick Sort**     | O(n log n) | O(n log n)   | O(n²)      | O(log n)         | ❌ Not Stable |


