package aggregations

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

/**
 * Aggregations in Spark
 * Finding the average values by key
 * Here, we find the average rating of the movies in the MovieLens 100K  data set
 * NOTE :  For example using 'combineByKey' function, please see
 * the file - AverageUsingCombine.scala
 */
object Average {
  def main(args : Array[String]) = {

    val input = args(0)
    val output = args(1)

    val conf = new SparkConf().setAppName("Averages")
    val sc = new SparkContext(conf)

    val ratings = sc
      .textFile(input)
      .map(x => getKeyAndValue(x)) // get data from input into (movieid, rating)

    val reduced_ratings = ratings
      .mapValues(x => (x, 1)) // for each entry in the data, output the value and a 1
      .reduceByKey((x, y) => (x._1 + y._1, x._2 + y._2))  // reduce the ratings and the 1 by key. This gives
                                                          // total rating and number of times movie was rated
      .map(x => (x._1 , x._2._1 / x._2._2))               // for each movie, calculate the average
      .saveAsTextFile(output)

  }

  /**
   * Convenience method to split the input string to key and values
   */
  def getKeyAndValue(s: String) : (Int, Float) = {
    val data = s.split("\\t+").map(_.trim)

    (data(1).toInt, data(2).toFloat)

  }
}

