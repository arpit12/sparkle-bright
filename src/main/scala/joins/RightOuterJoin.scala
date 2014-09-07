package joins

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

/**
  * Joins (a.k.a. co-groups) in Spark
  * Used for grouping two or more data sets by a common key
 * Here, we do an 'Right Outer Join' on offers & transactions datasets
 * All records of the transaction dataset will appear along with the records
 * with matching keys in both
  */
object RightOuterJoin {
   def main(args : Array[String]) = {

     // get and store the argument values
     val offerFile = args(0)
     val transactionFile = args(1)
     val output = args(2)

     // initializing Spark
     val conf = new SparkConf().setAppName("RightOuterJoin")
     val sc = new SparkContext(conf)

     // load and split offers into key and values
     val offers = sc
       .textFile(offerFile)
       .map(x => (x.split(",")(0).toLong, x))

     // load and split transactions into key and values
     val transactions = sc
       .textFile(transactionFile)
       .map(x => (x.split(",")(2).toLong, x))

     // right outer join
     val offer_transactions = offers
       .rightOuterJoin(transactions)
       .values
       .map(x => x._1 + "," + x._2)
       .saveAsTextFile(output)

   }
 }
