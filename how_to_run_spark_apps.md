## Package a jar containing your application
`$ sbt package`


## Use spark-submit to run your application
`$ YOUR_SPARK_HOME/bin/spark-submit \
  --class "WordCount" \
  --master local[4] \
  target/scala-2.10/sparkle-bright_2.10-1.0.jar \
  data/shakespeare.txt output/wordcount`

## In Windows, do the below in your console
`E:\spark\bin\spark-submit --class "WordCount" --master local[4] target\scala-2.10\sparkle-bright_2.10-1.0.jar
        data\shakespeare.txt output\wordcount`
  
