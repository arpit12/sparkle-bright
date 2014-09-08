package aggregations

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

/**
 * Aggregations in Spark
 * Finding the average values by key
 * Here, we find the average rating of the movies in the MovieLens 100K  data set
 * NOTE : This program will work well only if the all the operation is done in 1 Worker node.
 * For example using 'reduceByKey' function , please see the file - Average.scala
 */
object AverageUsingCombine {
  def main(args : Array[String]) = {

    val input = args(0)
    val output = args(1)

    val conf = new SparkConf().setAppName("AverageUsingCombine")
    val sc = new SparkContext(conf)

    val ratings = sc
      .textFile(input)
      .map(x => getKeyAndValue(x)) // get data from input into (movieid, rating)

    val combined_ratings = ratings
      .combineByKey(
        (v) => (v, 1),            // createCombiner
        (acc: (Int, Int), v) => (acc._1 + v, acc._2 + 1), // mergeValue
        (acc1: (Int, Int), acc2: (Int, Int)) => (acc1._1 + acc2._1, acc1._2 + acc2._2)) // mergeCombiners
      .map{ case(key, value) => (key, value._1 / value._2.toFloat)}
      .saveAsTextFile(output)

  }

  /**
   * Convenience method to split the input string to key and values
   */
  def getKeyAndValue(s: String) : (Int, Int) = {
    val data = s.split("\\t+").map(_.trim)

    (data(1).toInt, data(2).toInt)

  }
}

