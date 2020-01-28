## Assumptions

- The data source for the purpose of this task is in a CSV file where Spark Master is running.
- 

## Testing

> sbt assembly

## Building

> sbt assembly

## Packageing

> sbt assembly

See the JAR file in `target/scala-2.X/runagg-assembly-Y.Y.jar`

## Run

docker run --rm -it -p 4040:4040 -v $(pwd)/:/runagg/ wanderijames/spark:alpine spark-submit /runagg/target/scala-2.12/runagg-assembly-0.1.jar -d 343 -i /runagg/src/test/resources/input.csv
