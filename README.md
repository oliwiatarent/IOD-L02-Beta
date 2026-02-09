# Sorting Madness

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit5-f5f5f5?style=for-the-badge&logo=junit5&logoColor=dc524a)

A RESTful API application designed to sort datasets using various algorithms for the Software Engineering course at Poznan University of Technology. It assists users in evaluating which method performs best for specific cases. Numerical data is sorted normally, while text data is sorted lexicographically. The application supports 6 different sorting methods and is accessible as a remote API.

![example workflow](https://github.com/oliwiatarent/IOD-L02-Beta/actions/workflows/ci.yml/badge.svg)



# Features

* **Multiple Sorting Algorithms**:
  * Bubble Sort (1)
  * Merge Sort (2)
  * Selection Sort (3)
  * Insertion Sort (4)
  * Quick Sort (5)
  * Bogo Sort (6)


* **Automatic Algorithm Selection**: Intelligently selects the best algorithm based on data size and initial order
* **Flexible Input Data**:
  * Supports simple lists of Integers, Floats, or Strings.
  * Supports complex JSON objects, allowing sorting by a specific property.
* **Customization**: Limit the number of iterations for step-by-step visualization or debugging.
* **REST API**: Exposes a `/sort` endpoint that accepts JSON input and returns sorted results along with execution time and sorted indices.

---

# Build

You need **Maven** and **JDK 11** (or higher) installed.

```bash
mvn clean package
```


# Usage

1. Start the Application:
```bash
java -jar target/io-project-architecture-2.0.jar
```


2. Generate Test Data (optional):
Use the Python script to generate a valid `input.json` file.
```bash
python3 main.py
```


3. Send a Request:
Use `curl` or Postman to send a POST request to `http://localhost:8080/sort`.
**Example `curl` command:**
```bash
curl -X POST -H "Content-Type: application/json" -d @input.json http://localhost:8080/sort
```


3b. Example JSON Payload:
```json
{
    "list": [5, 2, 8, 1, 9],
    "ascending": true,
    "algorithm": 1,
    "iterations": 1000,
    "autoChoose": false
}
```


*. To generate and view the JavaDocs:
```
jar xf io-project-architecture-2.0-javadoc.jar
```

---

# Architecture

<p align="center">
  <img src="diagram_uml.png" width="100%" alt="UML Diagram" />
</p>
