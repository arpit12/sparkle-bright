import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object WordCount {
  def main (args: Array[String]) {
    val file = args(0)        // get the file name

    val conf = new SparkConf().setAppName("Word Count")
    val sc = new SparkContext(conf)

    val data = sc.textFile(file,2).cache()
    val wordCounts = data.flatMap(tokenize)
      .map(word => (word , 1))
      .reduceByKey(_+_)

    wordCounts.saveAsTextFile(args(1))

  }

  def tokenize(line : String) : Array[String] = {
    line
      .toLowerCase
      .replaceAll("^A-Za-z0-9\\s","")
      .split("\\s+")
  }
}
